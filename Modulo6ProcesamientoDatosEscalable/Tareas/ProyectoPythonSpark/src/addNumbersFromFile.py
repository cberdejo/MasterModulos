
import time

from pyspark import SparkConf, SparkContext

def main(file_name: str) -> None:
    spark_conf = SparkConf()\
        .setAppName("AddNumbers")\
        .setMaster("local[8]")

    spark_context = SparkContext(conf=spark_conf)

    spark_context.setLogLevel("ERROR")

    start_computing_time = time.time()

    # lines: String RDD
    lines = spark_context.textFile(file_name)

    # number: integer RDD
    numbers = lines\
        .map(lambda line: int(line))

    # integer
    result = numbers\
        .reduce(lambda x,y: x + y)

    total_computing_time = time.time() - start_computing_time

    print("Sum: ", result)
    print("Computing time: ", str(total_computing_time))

    spark_context.stop()


if __name__ == "__main__":
    """
    Python program that uses Apache Spark to sum a list of numbers stored in files
    """

    main("./data/manyNumbers/manyNumbers.txt")
