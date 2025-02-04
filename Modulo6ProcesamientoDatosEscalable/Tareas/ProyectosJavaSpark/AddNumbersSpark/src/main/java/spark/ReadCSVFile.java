package spark;

import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.storage.StorageLevel;


public class ReadCSVFile {

    public static void main(String[] args) {
        Logger.getLogger("org").setLevel(Level.OFF);

        // Step 1: create a SparkConf object
        SparkConf conf = new SparkConf()
                .setAppName("Read CSV file")
                .setMaster("local[*]");

        // Step 2: create a Java Spark Context
        JavaSparkContext sparkContext = new JavaSparkContext(conf);

        /* Step 3: read the data and perform de sum */
        JavaRDD<String> lines = sparkContext
                .textFile("./src/main/resources/Film_Locations_in_San_Francisco_20250123.csv")
                .persist(StorageLevel.MEMORY_ONLY());
        String header = lines.first();

        JavaRDD<String[]> fields = lines.filter(line -> !line.equals(header)).map(line -> line.split(","));

        JavaRDD<String> filmNames = fields.map(array -> array[0]);

        List<String> result = filmNames
                .distinct()
                .sortBy(name -> name, false, 1)
                .take(20);

        result.forEach(System.out::println);

        sparkContext.stop();
    }
}