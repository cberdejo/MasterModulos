from fastapi import FastAPI, Depends
from app.validate import validateGivenXY
from minio import Minio
import pandas as pd
import io
from train import train_with_grid_search
from models import PhoneCharacteristicsInput
import pickle
import matplotlib.pyplot as plt
import seaborn as sns

BUCKET_NAME = "mobiledata"

def connect_to_minio():
    client = Minio(
        "localhost:9000",
        access_key="minioadmin",
        secret_key="minioadmin",
        secure=False,
    )
    yield client

    del client


app = FastAPI()


def get_train_data(excluded_date: list[str], client: Minio) -> pd.DataFrame:
    final_data = []

    for data_entry in client.list_objects(BUCKET_NAME, recursive=False):
        if data_entry.object_name.removesuffix("/") not in excluded_date:
            object_name = data_entry.object_name + "data.csv"

            response = client.get_object(BUCKET_NAME, object_name)

            if response.status == 200:
                df = pd.read_csv(io.StringIO(response.data.decode()))

            final_data.append(df)

    return pd.concat(final_data)



# Train
@app.post("/api/v1/mpc/train")
def train_model(
    excluded_date: list[str], client: Minio = Depends(connect_to_minio)
):  # Model to use, hyper parameters
    training_data = get_train_data(excluded_date=excluded_date, client=client)

    model_id = train_with_grid_search(training_data, client)
    return {"status":"Ok", "trained_model_id":model_id}



DEFAULT_MODEL_ID = "2025-02-26T20:38:24.243698"
MODELS = {}

def load_model(model: str | None, client: Minio):
    # FIXME: Check if len(MODELS) > 10, unload the older one
    if model is None:
        model = DEFAULT_MODEL_ID

    if model not in MODELS:
        response = client.get_object(
            "mpc",
            f"model/{model}/model.pkl"
        )
        
        if response.status == 200:
            file_like_data = io.BytesIO(response.data)
            model = pickle.load(file_like_data)
            MODELS["model"] = model
    

    return MODELS["model"]



@app.post("/api/v1/mpc/predict")
def predict(pc: list[PhoneCharacteristicsInput], model_id:str | None, client: Minio = Depends(connect_to_minio) ):

    model = load_model(model_id, client=client)
    data = pd.DataFrame([ pc.model_dump()])
    pred = model.predict(data)

    
    return {"predictions": pred.tolist()}

def upload_results(client, model_id, accuracy, recall, f1, conf_matrix):

    # Save the scoring results in memory
    scoring_results_buffer = io.StringIO()
    scoring_results_buffer.write(f"Accuracy: {accuracy:.2f}\n")
    scoring_results_buffer.write(f"Recall: {recall:.2f}\n")
    scoring_results_buffer.write(f"F1 Score: {f1:.2f}\n")
    scoring_results_buffer.seek(0)

    # Save the confusion matrix as an image
    plt.figure(figsize=(10, 7))
    sns.heatmap(conf_matrix, annot=True, fmt="d")
    img_buffer = io.BytesIO()
    plt.savefig(img_buffer, format='png')
    img_buffer.seek(0)

    # Upload results to Minio
    client.put_object(BUCKET_NAME, f"model/{model_id}/scoring_results.txt", scoring_results_buffer, len(scoring_results_buffer.getvalue()))
    client.put_object(BUCKET_NAME, f"model/{model_id}/confusion_matrix.png", img_buffer, len(img_buffer.getvalue()))
        
@app.post("/api/v1/mpc/models")
def validate(model_id: str | None, excluded_dates: list[str], client: Minio = Depends(connect_to_minio)):
    model = load_model(model_id, client=client)
    validation_data = get_train_data(excluded_date=excluded_dates, client=client)

    X = validation_data.drop(columns=["price_range"])
    y = validation_data["price_range"]

    accuracy, recall, f1, conf_matrix = validateGivenXY(X, y, model)

    upload_results(accuracy=accuracy, recall=recall, f1=f1, conf_matrix=conf_matrix, model_id=model_id,client=client)

    return {"status": "Ok", "accuracy": accuracy, "recall": recall, "f1_score": f1}

if __name__ == "__main__":
    import uvicorn 
    uvicorn.run("app:app", reload=True)



