package spark;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.col;
/// file: https://data.cityofchicago.org/Public-Safety/Crimes-2001-to-Present/ijzp-q8t2/about_data
///
public class ExampleCSVChicagoCrimes {

    public static void main(String[] args) {

        Logger.getLogger("org").setLevel(Level.OFF);

        SparkSession sparkSession =
                SparkSession
                        .builder()
                        .appName("Java Spark SQL. Example of reading a CSV file")
                        .master("local[8]")
                        .getOrCreate();

        long startTime = System.currentTimeMillis();

        Dataset<Row> dataFrame =
                sparkSession
                        .read()
                        .format("csv")
                        .option("header", "true")
                        .option("inferSchema", "true")
                        .load("./src/main/resources/Crimes_-_2001_to_Present.csv")
                        .cache();

        dataFrame.printSchema();
        dataFrame.show();

        System.out.println("Time before grouping: " + (System.currentTimeMillis() - startTime));

        Dataset<Row> categories =
                dataFrame
                        .groupBy("Primary type")
                        .count()
                        .sort(col("count").desc());

        categories.printSchema() ;
        categories.show();

        System.out.println("Time before filtering: " + (System.currentTimeMillis() - startTime)) ;
        long numberOfCrimesOfPrimaryType =
                dataFrame
                        .filter(col("Primary type").equalTo("THEFT"))
                        .count();

        System.out.println("Total time: " + (System.currentTimeMillis() - startTime)) ;

        System.out.println(
                "Number of crimes of type THEFT: " + numberOfCrimesOfPrimaryType);

    }
}