
import time

from pyspark import SparkConf, SparkContext

def main(file_name: str) -> None:
    spark_conf = SparkConf()\
        .setAppName("AddNumbers")\
        .setMaster("local[8]")

    spark_context = SparkContext(conf=spark_conf)

    spark_context.setLogLevel("ERROR")

    start_computing_time = time.time()
    

    #count of words thar contains "Quijote" 
    quijote = spark_context.textFile(file_name)\
        .filter( lambda line: "Quijote" in line)\
        .count()
    sancho = spark_context.textFile(file_name)\
        .filter( lambda line: "Sancho" in line)\
        .count()
    
    dulcinea =  spark_context.textFile(file_name)\
        .filter( lambda line: "Dulcinea" in line)\
        .saveAsTextFile("Dulcinea.txt")
    total_computing_time = time.time() - start_computing_time

    print("Quijote: ", quijote)
    print("Sancho: ", sancho)
    print("Computing time: ", str(total_computing_time))

    spark_context.stop()


if __name__ == "__main__":
    """
    Python program that uses Apache Spark to sum a list of numbers stored in files
    """

    main("./data/quijote.txt")
