package org.masterbigdata.sparksql;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.col;

public class ExampleJSON {

  public static void main(String[] args) {
    Logger.getLogger("org").setLevel(Level.OFF);

    SparkSession sparkSession = SparkSession
        .builder()
        .appName("Java Spark SQL basic example")
            .master("local[2]")
        .getOrCreate();

    Dataset<Row> dataFrame = sparkSession
        .read()
        .json("data/primer-dataset.json");

    dataFrame.printSchema();
    dataFrame.show();

    dataFrame.groupBy(col("borough"))
        .count()
        .show();
  }
}
