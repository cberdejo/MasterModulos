# Imports
from pyspark import SparkConf, SparkContext
from pyspark.sql import SparkSession

# Crear SparkSession
spark_session = SparkSession.builder \
    .appName("cleanData") \
    .getOrCreate()

# Obtener SparkContext desde SparkSession
spark_context = spark_session.sparkContext
spark_context.setLogLevel("ERROR")

# Lectura
path_file = "./data/bridge.csv"
rdd = spark_context.textFile(path_file)

# Obtener la cabecera
header = rdd.first()

# Función para verificar si un valor puede convertirse en float
def is_float(value):
    try:
        float(value)  # Intenta convertir a float
        return True
    except ValueError:
        return False

# Filtrar datos y limpiar el RDD
rdd = rdd.filter(lambda line: line != header) \
    .map(lambda line: line.split(",")) \
    .filter(lambda line: len(line) == 2 and all(is_float(x.strip()) for x in line)) \
    .map(lambda line: tuple(float(x.strip()) for x in line))\
    .distinct()

data = rdd.collect()

import matplotlib.pyplot as plt


# Separar valores en listas de X (Weight) y Y (Deformation)
x_values = [row[0] for row in data]
y_values = [row[1] for row in data]

# Crear el gráfico
plt.figure(figsize=(6, 4))
plt.plot(x_values, y_values, 'r+', markersize=5)  # Puntos rojos con cruces ('+')

# Configurar etiquetas y límites
plt.xlim(min(x_values), max(x_values))
plt.ylim(min(y_values), max(y_values))
plt.xlabel("Weight")
plt.ylabel("Deformation")



# Mostrar el gráfico
plt.show()


print(rdd.take(5))