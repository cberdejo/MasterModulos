package spark;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.storage.StorageLevel;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.sum;

/// Este programa recibe como parámetro un archo de datos "personalData.csv"
///
/// Name,Age,Weight, HasACar,BirthDate
public class BasicOperations {

    public static void main(String[] args) {
        //Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getRootLogger().setLevel(Level.WARN);

        SparkSession sparkSession =
                SparkSession.builder()
                        .appName("Java Spark dataframes examples")
                        .master("local[8]")
                        .getOrCreate();

        Dataset<Row> dataFrame =
                sparkSession
                        .read()
                        .format("csv")
                        .option("header", "true")
                        .option("inferSchema", "true")
                        .option("timestampFormat", "yyyy-MM-dd")
                        .load("./src/main/resources/personalData.csv")
                        .cache();
        //View DF
        System.out.println( "Dataframe");
        dataFrame.show();

        System.out.println("Dataframe stats");
        //View statistics DF
        dataFrame.describe().show();

        //Select a column from df
        System.out.println(" select a column with age plus 1");
        dataFrame.select(col("Name"), col("Age").plus(1)).show();


        System.out.println(" select a column with number of characters greater than 4");
        dataFrame.select(functions.length(col("Name")).alias("length").gt(4)).show();

        System.out.println("Names that start with L");
        dataFrame.filter(col("Name").startsWith("L")).show();

        System.out.println("Add a new Column 'Senior' containing true if person age is > 45");
        dataFrame.withColumn("Senior", col("Age").gt(45)).show();

        System.out.println("Remove column BirthDate");
        dataFrame.drop("BirthDate").show();

        System.out.println("Sort by age in descending order and then by weight in ascending order");
        dataFrame.sort(col("Age").desc(), col("Weight").asc()).show();

        //Get a RDD
        System.out.println("Get a RDD");
        JavaRDD<Row> rows = dataFrame.toJavaRDD().persist(StorageLevel.MEMORY_AND_DISK());
        rows.foreach(System.out::println);

        // Sum all the weights (RDD)
        /*
        double sumOfWeights =
                rddFromDataFrame
                        .map(row -> Double.valueOf(row.getString(2)))
                        .reduce((x, y) -> x + y);

        System.out.println("Sum of weights (RDDs): " + sumOfWeights);
        */

        // Sum all the weights (dataframe)
        dataFrame
                .select("Weight")
                .groupBy()
                .sum()
                .show();

        dataFrame.select(sum("weight")).show();
        dataFrame.agg(sum("weight")).show();

        System.out.println(
                "Sum of weights (Dataframe): "
                        + dataFrame.agg(sum("weight")).collectAsList().get(0).get(0));

        /*
        // Get the mean age (RDD)
        int totalAge =
            rddFromDataFrame
                    .map(row -> Integer.valueOf((String) row.get(1)))
                    .reduce((x, y) -> x + y);

        double meanAge = 1.0 * totalAge / rddFromDataFrame.count();

        System.out.println("Mean age (RDDs): " + meanAge);
        */

        // Get the mean age (dataframe)
        dataFrame
                .select(functions.avg("Weight"))
                .withColumnRenamed("avg(Weight)", "Average").show();


        // Compute the number of rows having and not having a car
        dataFrame.groupBy("HasACar")
                .count()
                .show() ;

        // Write to a json file
        dataFrame
                .write()
                .mode("overwrite")
                .option("header", "true")
                .json("output.json");

        // Write to a CSV file
        dataFrame
                .write()
                .mode("overwrite")
                .option("header", "true")
                .csv("output.csv");
    }
}
