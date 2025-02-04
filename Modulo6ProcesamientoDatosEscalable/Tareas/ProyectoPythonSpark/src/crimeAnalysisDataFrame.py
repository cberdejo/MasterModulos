
from pyspark.sql import SparkSession

import time

"""
This program counts the number of crimes contained in the csv file included in this site
https://data.cityofchicago.org/Public-Safety/Crimes-2001-to-Present/ijzp-q8t2the 

The crimes are grouped by primary type.
"""

def main() -> None:
    spark_session = SparkSession \
        .builder \
        .master("local[8]") \
        .getOrCreate()

    spark_session.sparkContext.setLogLevel("ERROR")

    start_computing_time = time.time()

    data_frame = spark_session \
        .read \
        .format("csv") \
        .options(header='true', inferschema='true') \
        .load("data/Crimes_-_2001_to_Present.csv")

    data_frame.printSchema()
    data_frame.show()

    print("Time before grouping: " + str(time.time() - start_computing_time))

    data_frame \
        .groupBy("Primary Type") \
        .count() \
        .sort("count", ascending=False) \
        .show()

    print("Time before filtering: " + str(time.time() - start_computing_time))

    number_of_crimes_of_type_theft = data_frame \
                .filter(data_frame["Primary type"] == "THEFT") \
                .count()
    
    total_computing_time = time.time() - start_computing_time
    print("Computing time: ", str(total_computing_time))
    
    print("Number of crimes of type THEFT: ", number_of_crimes_of_type_theft)
    
if __name__ == '__main__':
    main()