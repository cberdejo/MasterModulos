from pyspark.sql import SparkSession


def main() -> None:
    # Create a Spark session to interact with the Spark DataFrame API
    spark_session = SparkSession \
        .builder \
        .getOrCreate()

    # Set the log level to "ERROR" to reduce unnecessary log messages
    spark_session.sparkContext.setLogLevel("ERROR")

    # Read a JSON file into a DataFrame
    data_frame = spark_session \
        .read \
        .json("File:///Users/josemanuelmuelas/Downloads/primer-dataset.json")

    # Print the schema of the DataFrame
    data_frame.printSchema()

    # Display the first few rows of the DataFrame
    data_frame.show()

    # Select and display the names of all restaurants (column "name")
    data_frame.select("name").show()

    # Select and display the names and cuisine types of all restaurants (columns "name" and "cuisine")
    data_frame.select("name", "cuisine").show()

    # Filter and display only the restaurants that serve American cuisine
    data_frame.filter(data_frame["cuisine"] == "American").show()

    # Group restaurants by borough (column "borough") and count them
    data_frame.groupBy("borough") \
        .count() \
        .show()

    # Create a temporary SQL view from the DataFrame
    data_frame.createOrReplaceTempView("data_table")

    # Execute an SQL query to count restaurants per borough
    result = spark_session.sql("""
        SELECT borough, COUNT(*) as count
        FROM data_table
        GROUP BY borough
    """)

    # Show the result of the SQL query
    result.show()

    """
    # Save the DataFrame as a JSON file (currently commented out)
    data_frame \
        .write \
        .save("data_frame.json", format="json")
    """


if __name__ == '__main__':
    main()
    