"""

Original file is located at
    https://colab.research.google.com/drive/1wf0YLhk4cGSWDyBUuGYElxkUXrQcOBna

"
Reads the airports.csv file and counts the number of Spanish airports
for each type, writing the results in a text file.
""
"""

from pyspark import SparkConf, SparkContext
from pyspark.sql import SparkSession

# Configurar Spark

spark = SparkSession.builder \
    .appName("MyApp") \
    .config("spark.driver.memory", "4g") \
    .config("spark.executor.memory", "4g") \
    .getOrCreate()

spark_context =  spark.sparkContext
spark_context.setLogLevel("ERROR")

# Leer el archivo
path_file = "C:/Users/cberd/Downloads/airports.csv"

rdd = spark_context.textFile(path_file).cache()


# Obtener la cabecera y limpiarla de comillas
header = rdd.first()


column_names = header.split(",")
print(column_names)

# Obtener los índices correctos

index_iso_country = column_names.index('"iso_country"')
index_type = column_names.index('"type"')

filtered_rdd = (
    rdd
    .filter(lambda row: row != header)
    .map(lambda row: row.split(","))
    .filter(lambda cols: cols[index_iso_country] == '"ES"')
)

# Mapear cada fila al tipo de aeropuerto como clave y contar
type_counts = (
    filtered_rdd
    .map(lambda cols: (cols[index_type], 1))  # Crear pares clave-valor (type, 1)
    .reduceByKey(lambda a, b: a + b)  # Sumar los valores por clave
    .collect()  # Traer el resultado a Python
)

# Convertir el resultado en un diccionario para mejor visualización
type_counts_dict = dict(type_counts)

# Imprimir los conteos
print(type_counts_dict)

# Guardar resultados en un archivo
with open("result.txt", "w") as file:
    file.write(str(type_counts_dict))


spark_context.stop()