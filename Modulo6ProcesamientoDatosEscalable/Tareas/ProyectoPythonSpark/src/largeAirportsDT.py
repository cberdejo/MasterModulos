from pyspark.sql import SparkSession
from pyspark.sql.functions import col

def main():
    # Crear SparkSession
    spark_session = SparkSession.builder \
        .appName("Join Airports and Countries") \
        .getOrCreate()

    spark_session.sparkContext.setLogLevel("ERROR")

    path_file_airports = "./data/airports.csv"
    path_file_countries = "./data/countries.csv"

    # Leer archivos 
    data_frame_airports = spark_session \
        .read \
        .options(header='true', inferschema='true') \
        .option("delimiter", ",") \
        .csv(path_file_airports) \
        .persist()

    data_frame_countries = spark_session \
        .read \
        .options(header='true', inferschema='true') \
        .option("delimiter", ",") \
        .csv(path_file_countries) \
        .persist()
    
    # Renombrar "name" en data_frame_countries para evitar conflictos antes del join
    data_frame_countries = data_frame_countries.withColumnRenamed("name", "country_name")

    # Realizar el join: iso_country (airports.csv) con code (countries.csv)
    data_frame = data_frame_airports.join(
        data_frame_countries,
        data_frame_airports.iso_country == data_frame_countries.code,
        how="left"
    )

    # Mostrar los aeropuertos con más registros por país
    print("Cantidad de aeropuertos por país:")
    data_frame.groupBy("country_name").count().orderBy(col("count").desc()).show()

if __name__ == "__main__":
    main()
