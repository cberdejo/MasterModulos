
from pyspark.sql import SparkSession, functions
from pyspark.sql.functions import to_timestamp

"""
This program receives a parameter a data file called "simple.csv" with this content:

Name,Age,Weight,HasACar,BirthDate
Luis,23,84.5,TRUE,2019-02-28 
Lola,42,70.2,false,2000-10-01
Paco,66,90.1,False,1905-12-03
Manuel,68,75.3,true,2000-01-04
"""


def main() -> None:
    spark_session = SparkSession \
        .builder \
        .master("local[4]")\
        .appName("Simple program to show the basics of Spark dataframes") \
        .getOrCreate()

    spark_session.sparkContext.setLogLevel("ERROR")

    data_frame = spark_session \
        .read \
        .options(header='true', inferschema='true') \
        .option("delimiter", ",") \
        .option("timestampFormat", "yyyy-MM-dd") \
        .csv("data/personalData.csv") \
        .persist()
    print("Data frame schema") 
    data_frame.printSchema()
    print ("Data frame")
    data_frame.show()

    # Print the data types of the data frame
    print("data types: " + str(data_frame.dtypes))

    # Describe the dataframe
    print ("Describe the dataframe")
    data_frame \
        .describe() \
        .show()

    # Explain the dataframe
    print ("Explain the dataframe")
    data_frame \
        .explain()

    # Transform the date to the format used in Spark 3.0
    print ("Transform the date to the format used in Spark 3.0")
    data_frame = data_frame.withColumn("BirthDate", to_timestamp("BirthDate", "yyyy-MM-dd"))
    data_frame.printSchema()
    data_frame.show()

    # Select
    print ("Select the name and age columns")
    data_frame.select("Name", "Age").show()

    # Select columns name and age, but adding 1 to age
    print ("Select columns name and age, but adding 1 to age")
    data_frame.select("Name", data_frame["Age"] + 1) \
        .show()

    # Indicates whether the rows have a name length > 4
    print ("Indicates whether the rows have a name length > 4")
    data_frame.select(functions.length(data_frame["Name"]) > 4).show()

    # Indicates whether the names start with L
    print ("Indicates whether the names start with L")
    data_frame.select(data_frame["name"], data_frame["name"].startswith("L")) \
        .show()

    # Add a new column "Senior" containing true if the person age is > 45
    print ("Add a new column Senior containing true if the person age is > 45")
    data_frame.withColumn("Senior", data_frame["Age"] > 45) \
        .show()

    # Rename column HasACar as Owner
    print ("Rename column HasACar as Owner")
    data_frame.withColumnRenamed("HasACar", "Owner") \
        .show()

    # Remove column DateBirth
    print ("Remove column DateBirth")
    data_frame.drop("BirthDate") \
        .show()

    # Sort by age (descending)
    print ("Sort by age (descending)")
    data_frame.sort(data_frame.Age.desc()).show()
    data_frame.sort("Age", ascending=False).show()
    data_frame.orderBy(["Age", "Weight"], ascending=[0, 1]).show()

    # Get a RDD
    rdd_from_dataframe = data_frame \
        .rdd \
        .persist()

    for i in rdd_from_dataframe.collect():
        print(i)

    # Sum all the weights (RDD)
    sum_of_weights = rdd_from_dataframe \
        .map(lambda row: row[2]) \
        .reduce(lambda x, y: x + y)  # sum()
    print("Sum of weights (RDDs): " + str(sum_of_weights))

    # Sum all the weights (dataframe)
    weights = data_frame \
        .select("Weight") \
        .groupBy() \
        .sum() \
        .collect()

    print(weights)

    print("Sum of weights (dataframe): " + str(weights[0][0]))

    data_frame.select(functions.sum(data_frame["Weight"])).show()
    data_frame.agg({"Weight": "sum", "Age": "min"}).show()

    # Get the mean age (RDD)
    total_age = rdd_from_dataframe \
        .map(lambda row: row[1]) \
        .reduce(lambda x, y: x + y)

    mean_age = total_age / rdd_from_dataframe.count()

    print("Mean age (RDDs): " + str(mean_age))

    # Get the mean age (dataframe)
    data_frame.select(functions.avg(data_frame["Weight"])) \
        .withColumnRenamed("avg(Weight)", "Average") \
        .show()

    data_frame.agg({"Weight": "avg"}).show()

    # Compute the number of row having and not having a car
    data_frame.groupBy("HasACar")\
        .count()\
        .show()

    # Write to a json file
    data_frame\
        .write \
        .mode("overwrite") \
        .save("output.json", format="json")

    # Write to a CSV file
    data_frame\
        .write\
        .format("csv")\
        .option("header", "true") \
        .mode("overwrite")\
        .save("output.csv")

if __name__ == '__main__':
    main()
