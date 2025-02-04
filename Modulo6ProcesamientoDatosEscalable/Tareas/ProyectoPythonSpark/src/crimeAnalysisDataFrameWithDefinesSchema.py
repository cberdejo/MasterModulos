from pyspark.sql import SparkSession
from pyspark.sql.functions import col
import time

# Inicializar la sesión de Spark
spark = SparkSession.builder \
    .appName("Python Spark SQL. Example of reading a CSV file") \
    .master("local[8]") \
    .getOrCreate()

# Desactivar los logs de INFO y WARN
spark.sparkContext.setLogLevel("ERROR")

start_time = time.time()

# Definir el esquema explícito para el DataFrame
schema = "ID INT, `Case Number` STRING, Date STRING, Block STRING, IUCR STRING, " \
         "`Primary Type` STRING, Description STRING, `Location Description` STRING, " \
         "Arrest BOOLEAN, Domestic BOOLEAN, Beat INT, District INT, Ward INT, " \
         "`Community Area` INT, `FBI Code` STRING, `X Coordinate` INT, `Y Coordinate` INT, " \
         "Year INT, `Updated On` STRING, Latitude DOUBLE, Longitude DOUBLE, Location STRING"

# Leer el archivo CSV con el esquema definido
df = spark.read \
    .format("csv") \
    .option("header", "true") \
    .option("inferSchema", "false") \
    .schema(schema) \
    .load("data/Crimes_-_2001_to_Present.csv") \
    .cache()

# Mostrar el esquema y los primeros registros del DataFrame
df.printSchema()
df.show()

print(f"Time before grouping: {time.time() - start_time}")

# Agrupar por 'Primary Type' y contar, luego ordenar por el conteo en orden descendente
categories = df.groupBy("Primary Type").count().sort(col("count").desc())

# Mostrar el esquema y los resultados de la agrupación
categories.printSchema()
categories.show()

print(f"Time before filtering: {time.time() - start_time}")

# Filtrar los registros donde 'Primary Type' es 'THEFT' y contar
number_of_crimes_of_primary_type = df.filter(col("Primary Type") == "THEFT").count()

print(f"Total time: {time.time() - start_time}")
print(f"Number of crimes of type THEFT: {number_of_crimes_of_primary_type}")

# Detener la sesión de Spark
spark.stop()