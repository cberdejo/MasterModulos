package org.masterbigdata.rdd;

import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.util.LongAccumulator;

/**
 * Program that sums a list of numbers using Apache Spark
 *
 * @author Antonio J. Nebro
 */
public class AddNumbersWithAccumulator {

  public static void main(String[] args) {
    Logger.getLogger("org").setLevel(Level.OFF);

    SparkConf sparkConf = new SparkConf()
            .setAppName("Accumulators")
            .setMaster("local[4]");

    JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

    List<Integer> integerList = List.of(1, 2, 3, 4, 5, 6, 7, 8);

    LongAccumulator accumulator = sparkContext.sc().longAccumulator();

    int sum =
        sparkContext
            .parallelize(integerList)
            .reduce(
                (integer, integer2) -> {
                  accumulator.add(1);
                  return integer + integer2;
                });

    System.out.println("The sum is: " + sum);
    System.out.println("Number of reduces: " + accumulator.value());

    // Step 7: stop the spark context
    sparkContext.stop();
  }
}
