package spark;
import static org.apache.spark.sql.functions.col;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class ExampleCSVWithExplicitSchemeChicagoCrimes {

    public static void main(String[] args) {

        Logger.getLogger("org").setLevel(Level.OFF);

        SparkSession sparkSession =
                SparkSession.builder()
                        .appName("Java Spark SQL. Example of reading a CSV file")
                        .master("local[8]")
                        .getOrCreate();

        long startTime = System.currentTimeMillis();

        StructType schema =
                new StructType(
                        new StructField[] {
                                new StructField("ID", DataTypes.IntegerType, true, Metadata.empty()),
                                new StructField("Case Number", DataTypes.StringType, true, Metadata.empty()),
                                new StructField("Date", DataTypes.StringType, true, Metadata.empty()),
                                new StructField("Block", DataTypes.StringType, true, Metadata.empty()),
                                new StructField("IUCR", DataTypes.StringType, true, Metadata.empty()),
                                new StructField("Primary Type", DataTypes.StringType, true, Metadata.empty()),
                                new StructField("Description", DataTypes.StringType, true, Metadata.empty()),
                                new StructField("Location Description", DataTypes.StringType, true, Metadata.empty()),
                                new StructField("Arrest", DataTypes.BooleanType, true, Metadata.empty()),
                                new StructField("Domestic", DataTypes.BooleanType, true, Metadata.empty()),
                                new StructField("Beat", DataTypes.IntegerType, true, Metadata.empty()),
                                new StructField("District", DataTypes.IntegerType, true, Metadata.empty()),
                                new StructField("Ward", DataTypes.IntegerType, true, Metadata.empty()),
                                new StructField("Community Area", DataTypes.IntegerType, true, Metadata.empty()),
                                new StructField("FBI Code", DataTypes.StringType, true, Metadata.empty()),
                                new StructField("X Coordinate", DataTypes.IntegerType, true, Metadata.empty()),
                                new StructField("Y Coordinate", DataTypes.IntegerType, true, Metadata.empty()),
                                new StructField("Year", DataTypes.IntegerType, true, Metadata.empty()),
                                new StructField("Updated On", DataTypes.StringType, true, Metadata.empty()),
                                new StructField("Latitude", DataTypes.DoubleType, true, Metadata.empty()),
                                new StructField("Longitude", DataTypes.DoubleType, true, Metadata.empty()),
                                new StructField("Location", DataTypes.StringType, true, Metadata.empty())
                        });

        Dataset<Row> dataFrame =
                sparkSession
                        .read()
                        .format("csv")
                        .option("header", "true")
                        .option("inferSchema", "false")
                        .schema(schema)
                        .load("./src/main/resources/Crimes_-_2001_to_Present.csv")
                        .cache();

        dataFrame.printSchema();
        dataFrame.show();

        System.out.println("Time before grouping: " + (System.currentTimeMillis() - startTime));

        Dataset<Row> categories = dataFrame.groupBy("Primary type").count().sort(col("count").desc());

        categories.printSchema();
        categories.show();

        System.out.println("Time before filtering: " + (System.currentTimeMillis() - startTime));

        long numberOfCrimesOfPrimaryType =
                dataFrame.filter(col("Primary type").equalTo("THEFT")).count();

        System.out.println("Total time: " + (System.currentTimeMillis() - startTime));

        System.out.println("Number of crimes of type THEFT: " + numberOfCrimesOfPrimaryType);
    }
}

