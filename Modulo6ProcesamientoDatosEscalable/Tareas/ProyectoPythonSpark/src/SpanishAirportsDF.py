#Install dependencies
from pyspark.sql import SparkSession, functions
from pyspark.sql.functions import to_timestamp

def main():
    """
    read the airports.csv file and counts the number of spanish airports
    for each of the four types, writing the results in a text file.
    """
 
    # Crear SparkSession
    spark_session = SparkSession.builder \
        .appName("AddNumbers") \
        .getOrCreate()

    # Obtener SparkContext desde SparkSession
    spark_context = spark_session.sparkContext

    spark_context.setLogLevel("ERROR")

    path_file = "./data/airports.csv"

    #lectura
    data_frame = spark_session \
        .read \
        .options(header='true', inferschema='true') \
        .option("delimiter", ",") \
        .option("timestampFormat", "yyyy-MM-dd") \
        .csv(path_file) \
        .persist()

     # Agrupar por tipo y contar
    result_df = data_frame.groupBy("type").count().orderBy("count", ascending=False)
    result_df.show()

    result_df.coalesce(1).write.option("header", "true").csv("result")

if __name__ == "__main__":
    main()