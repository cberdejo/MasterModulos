import pandas as pd
import matplotlib.pyplot as plt

from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import (
    accuracy_score,
    recall_score,
    f1_score,
    confusion_matrix,
    ConfusionMatrixDisplay,
)
from sklearn.model_selection import GridSearchCV

import pickle

from datetime import datetime

from minio import Minio



client = Minio(
    "localhost:9000",
    access_key="minioadmin",
    secret_key="minioadmin",
    secure=False,
)
SEED = 42
df = pd.read_csv("./dataset/train.csv")

# Análisis Exploratorio
print(df.info())

# Historamas y tal ahora

# Split in Train/test
X = df.drop(["price_range"], axis=1)
y = df["price_range"]

X_train, X_test, y_train, y_test = train_test_split(
    X, y, train_size=0.7, shuffle=True, random_state=SEED
)

# Define the parameter grid
param_grid = {
    "n_estimators": [100, 200, 300],
    "max_depth": [None, 10, 20, 30],
    "min_samples_split": [2, 5, 10],
    "min_samples_leaf": [1, 2, 4],
}

# Initialize the GridSearchCV object
grid_search = GridSearchCV(
    estimator=RandomForestClassifier(random_state=SEED),
    param_grid=param_grid,
    cv=5,
    n_jobs=-1,
    verbose=2,
    scoring="accuracy",
)

# Fit the grid search to the data
grid_search.fit(X_train, y_train)

# Get the best parameters and best estimator
best_params = grid_search.best_params_
best_model = grid_search.best_estimator_

print(f"Best Parameters: {best_params}")

# Use the best model for predictions
model = best_model
model = RandomForestClassifier(random_state=SEED)

model.fit(X_train, y_train)

y_pred = model.predict(X_test)

accuracy = accuracy_score(y_test, y_pred)
recall = recall_score(y_test, y_pred, average="macro")
f1 = f1_score(y_test, y_pred, average="macro")

print(f"Recall: {recall:.2f}")
print(f"F1 Score: {f1:.2f}")
print(f"Accuracy: {accuracy:.2f}")

conf_matrix = confusion_matrix(y_test, y_pred)
plt.figure(figsize=(10, 7))
disp = ConfusionMatrixDisplay(
    confusion_matrix=conf_matrix, display_labels=model.classes_
)
disp.plot(cmap=plt.cm.Blues)
plt.xlabel("Predicted")
plt.ylabel("Actual")
plt.title("Confusion Matrix")

# Save the confusion matrix as an image file
plt.savefig("./confusion_matrix.png")
plt.close()

# Save Model
with open("./result/model_file.pkl", mode="wb") as file_:
    pickle.dump(model, file_)

# Save using minio
bucket_name = "mpc"
id_ = datetime.now().isoformat()


# Save the train and test datasets
X_train.to_csv("./result/X_train.csv", index=False)
y_train.to_csv("./result/y_train.csv", index=False)
X_test.to_csv("./result/X_test.csv", index=False)
y_test.to_csv("./result/y_test.csv", index=False)

# Save the hyperparameters used
with open("./result/hyperparams.txt", mode="w") as file_:
    file_.write(f"Best Parameters: {best_params}\n")

with open("./result/scoring_results.txt", mode="w") as file_:
    file_.write(f"Accuracy: {accuracy:.2f}\n")
    file_.write(f"Recall: {recall:.2f}\n")
    file_.write(f"F1 Score: {f1:.2f}\n")
    

client.fput_object(bucket_name, f"model/{id_}/random_forest.pkl", "./result/model_file.pkl")

client.fput_object(bucket_name, f"model/{id_}/X_train.csv", "./result/X_train.csv")
client.fput_object(bucket_name, f"model/{id_}/y_train.csv", "./result/y_train.csv")
client.fput_object(bucket_name, f"model/{id_}/X_test.csv", "./result/X_test.csv")
client.fput_object(bucket_name, f"model/{id_}/y_test.csv", "./result/y_test.csv")
client.fput_object(bucket_name, f"model/{id_}/hyperparams.txt", "./result/hyperparams.txt")

client.fput_object(bucket_name, f"model/{id_}/scoring_results.txt", "./result/scoring_results.txt")