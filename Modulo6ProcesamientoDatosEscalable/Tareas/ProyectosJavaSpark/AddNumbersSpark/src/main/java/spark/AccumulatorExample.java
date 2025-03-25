package org.masterbigdata.rdd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.util.LongAccumulator;

import java.util.Arrays;
import java.util.List;

/**
 * Program that sums a list of numbers using Apache Spark
 *
 * @author Antonio J. Nebro
 */
public class AccumulatorExample {
  public static void main(String[] args) {
    // Create Spark configuration and context
    SparkConf sparkConf = new SparkConf().setAppName("Accumulator Example").setMaster("local[*]");
    JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

    // Create accumulators to track metrics
    LongAccumulator invalidValuesCount = sparkContext.sc().longAccumulator("Invalid Values");
    LongAccumulator valuesGreaterThan10 = sparkContext.sc().longAccumulator("Values > 10");

    // Sample data with some invalid entries (represented as strings)
    List<String> data = Arrays.asList("15", "8", "invalid", "25", "5", "error", "12", "7");
    JavaRDD<String> dataRDD = sparkContext.parallelize(data);

    // Process RDD and use accumulators to track metrics
    JavaRDD<Integer> validNumbers =
        dataRDD
            .filter(
                item -> {
                  try {
                    int value = Integer.parseInt(item);

                    // Count values greater than 10
                    if (value > 10) {
                      valuesGreaterThan10.add(1);
                    }

                    return true;
                  } catch (NumberFormatException e) {
                    // Count invalid values
                    invalidValuesCount.add(1);
                    return false;
                  }
                })
            .map(Integer::parseInt);

    // Force execution with an action
    long sum = validNumbers.reduce(Integer::sum);

    // Print results
    System.out.println("Sum of valid numbers: " + sum);
    System.out.println("Count of invalid values: " + invalidValuesCount.value());
    System.out.println("Count of values greater than 10: " + valuesGreaterThan10.value());

    sparkContext.stop();
  }
}
