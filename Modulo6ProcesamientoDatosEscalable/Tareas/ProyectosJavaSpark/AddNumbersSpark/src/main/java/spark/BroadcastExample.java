package org.masterbigdata.rdd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BroadcastExample {
  public static void main(String[] args) {
    // Create Spark configuration and context
    SparkConf sparkConf = new SparkConf().setAppName("Broadcast Example").setMaster("local[*]");
    JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

    // Create a lookup table that we want to broadcast
    Map<Integer, String> lookupTable = new HashMap<>();
    lookupTable.put(1, "One");
    lookupTable.put(2, "Two");
    lookupTable.put(3, "Three");
    lookupTable.put(4, "Four");
    lookupTable.put(5, "Five");

    // Broadcast the lookup table to all nodes
    Broadcast<Map<Integer, String>> broadcastLookup = sparkContext.broadcast(lookupTable);

    // Create an RDD with some integer data
    JavaRDD<Integer> numbersRDD = sparkContext.parallelize(Arrays.asList(1, 2, 3, 4, 5, 6, 3, 1));

    // Use the broadcast variable in a transformation
    JavaRDD<String> wordsRDD =
        numbersRDD.map(
            num -> {
              // Access the broadcast variable using value()
              Map<Integer, String> lookupMap = broadcastLookup.value();
              return lookupMap.getOrDefault(num, "Unknown");
            });

    // Collect and print the results
    System.out.println("Numbers mapped to words:");
    wordsRDD.collect().forEach(System.out::println);

    // Stop the Spark context
    sparkContext.stop();
  }
}
