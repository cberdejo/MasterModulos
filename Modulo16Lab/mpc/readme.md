# 📊 Mobile Price Calification

Este proyecto utiliza un **Random Forest Classifier** para predecir la categoría de precio de un conjunto de datos de teléfonos móviles. Se realiza un análisis exploratorio, optimización de hiperparámetros mediante **GridSearchCV**, evaluación del modelo y guardado del modelo entrenado.

---

# 📂 Estructura del Proyecto

```plaintext
train 
    ├── main.py
    ├── dataset
        |── train.csv 
app 
    ├── app.py
```
(Descargar dataset desde: https://www.kaggle.com/datasets/iabhishekofficial/mobile-price-classification)

#Entrenamiento
--- 
## 🏗️ Flujo del entreno
1. Carga del dataset: Se carga el archivo train.csv que contiene las características de los teléfonos móviles junto con su categoría de precio.

1. Análisis exploratorio:Se muestran estadísticas del conjunto de datos, incluyendo tipos de datos y valores nulos.

1. División de datos:Se dividen los datos en 70% entrenamiento y 30% test con train_test_split.

1. Optimización de Hiperparámetros: Se utiliza GridSearchCV con validación cruzada (cv=5) para encontrar los mejores valores de:

    - n_estimators
    - max_depth
    - min_samples_split
    - min_samples_leaf
    - Entrenamiento del modelo:
    - Se entrena el modelo con los mejores hiperparámetros encontrados.

5. Evaluación del modelo:
Se calcula:
    - Accuracy
    - Recall
    - F1-score
    - Matriz de confusión (guardada como confusion_matrix.png)
6. Guardado del modelo: El modelo entrenado se guarda en model_file.pkl usando pickle. Se guarda usando minio. Se puede bajar en docker usando el comando

` docker run -p 9000:9000 -p 9001:9001   quay.io/minio/minio server /data --console-address ":9001"`


## 🏃‍♂️ Ejecución de entreno
Para entrenar el modelo y generar los resultados, ejecuta:

bash:
`python train/main.py`

# 🚀 Aplicación 
Descarga el modelo guardado y crea una api para predecir la categoría de precio de un teléfono móvil, dado inputs de características.

## 📜 Licencia
Este proyecto es de uso libre bajo la MIT License.