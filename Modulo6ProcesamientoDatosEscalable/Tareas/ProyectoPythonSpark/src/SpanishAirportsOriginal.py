from pyspark import SparkConf, SparkContext
import os

def main():
    """
    Read the airports.csv file and count the number of Spanish airports
    for each of the six types, writing the results in a text file.
    """
    spark_conf = SparkConf().setAppName("AirportCount")
    spark_context = SparkContext(conf=spark_conf)

    spark_context.setLogLevel("ERROR")

    path_file = os.path.abspath("./data/airports.csv")
    print(f"Reading file from: {path_file}")

    if not os.path.exists(path_file):
        print(f"Error: The file '{path_file}' does not exist.")
        return

    rdd = spark_context.textFile(path_file)

    header = rdd.first()
    rdd = rdd.filter(lambda line: line != header)

    airport_counts = {
        "small_airport": rdd.filter(lambda line: "small_airport" in line).count(),
        "heliport": rdd.filter(lambda line: "heliport" in line).count(),
        "medium_airport": rdd.filter(lambda line: "medium_airport" in line).count(),
        "closed": rdd.filter(lambda line: "closed" in line).count(),
        "large_airport": rdd.filter(lambda line: "large_airport" in line).count(),
        "seaplane_base": rdd.filter(lambda line: "seaplane_base" in line).count(),
    }

    for key, value in airport_counts.items():
        print(f"{key}: {value}")

    output_path = "./output/airport_counts"
    spark_context.parallelize([f"{k}: {v}" for k, v in airport_counts.items()]) \
        .saveAsTextFile(output_path)

    print(f"Results saved to: {output_path}")

    spark_context.stop()

if __name__ == "__main__":
    main()
