import pandas as pd
import matplotlib.pyplot as plt
from io import BytesIO, StringIO

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
from minio import Minio
import pickle
from pandas import DataFrame

from datetime import datetime

SEED = 42

def train_with_grid_search(df: DataFrame, client: Minio):

    # Análisis Exploratorio
    print(df.info())

    # Split in Train/test
    X = df.drop(["price_range"], axis=1)
    y = df["price_range"]

    X_train, X_test, y_train, y_test = train_test_split(X, y, train_size=0.7, shuffle=True, random_state=SEED)


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
   
    # Save Model in memory
    model_buffer = BytesIO()
    pickle.dump(model, model_buffer)
    model_buffer.seek(0)

    # Save using minio
    bucket_name = "mpc"
    id_ = datetime.now().isoformat()

    # Save the train and test datasets in memory
    X_train_buffer = StringIO()
    y_train_buffer = StringIO()
    X_test_buffer = StringIO()
    y_test_buffer = StringIO()

    X_train.to_csv(X_train_buffer, index=False)
    y_train.to_csv(y_train_buffer, index=False)
    X_test.to_csv(X_test_buffer, index=False)
    y_test.to_csv(y_test_buffer, index=False)

    X_train_buffer.seek(0)
    y_train_buffer.seek(0)
    X_test_buffer.seek(0)
    y_test_buffer.seek(0)

    # Save the hyperparameters used in memory
    hyperparams_buffer = StringIO()
    hyperparams_buffer.write(f"Best Parameters: {best_params}\n")
    hyperparams_buffer.seek(0)

  

    client.put_object(bucket_name, f"model/{id_}/random_forest.pkl", model_buffer, len(model_buffer.getvalue()))

    client.put_object(bucket_name, f"model/{id_}/X_train.csv", X_train_buffer, len(X_train_buffer.getvalue()))
    client.put_object(bucket_name, f"model/{id_}/y_train.csv", y_train_buffer, len(y_train_buffer.getvalue()))
    client.put_object(bucket_name, f"model/{id_}/X_test.csv", X_test_buffer, len(X_test_buffer.getvalue()))
    client.put_object(bucket_name, f"model/{id_}/y_test.csv", y_test_buffer, len(y_test_buffer.getvalue()))
    client.put_object(bucket_name, f"model/{id_}/hyperparams.txt", hyperparams_buffer, len(hyperparams_buffer.getvalue()))

    return id_