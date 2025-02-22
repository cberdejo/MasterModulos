from pyspark import SparkContext, SparkConf

def main() -> None:
    """
    Python program that uses Apache Spark to sum a list of numbers
    """
    spark_conf = SparkConf()\
        .setAppName("AddNumbers")#\
        # .setMaster("local[4]")

    spark_context = SparkContext(conf=spark_conf)

    spark_context.setLogLevel("ERROR")

    list_data = [1, 2, 3, 4, 5, 6, 7, 8]

    # distributed_data: RDD of integers
    distributed_data = spark_context.parallelize(list_data)

    total = distributed_data.reduce(lambda s1, s2: s1 + s2)

    print("The sum is " + str(total))


if __name__ == '__main__':
    main()
