package spark;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

import java.time.Instant;


public class AddNumbersFromFile {
    //static Logger log = Logger.getLogger(CrimeAnalysis.class.getName());

    public static void main(String[] args) {
        Logger.getLogger("org").setLevel(Level.OFF) ;

        if (args.length < 1) {
            System.err.println("Syntax Error: there must be one argument (a file name or a directory)")  ;
            throw new RuntimeException();
        }

        // Step 1: create a SparkConf object
        SparkConf conf = new SparkConf()
                .setAppName("Add numbers from files")
                .setMaster("local[8]") ;

        // Step 2: create a Java Spark Context
        JavaSparkContext context = new JavaSparkContext(conf) ;

        long startComputingTime = Instant.now().toEpochMilli() ;

        /* Step 3: read the data and perform de sum */
        double sum = context.textFile(args[0])
                .map(number -> Double.valueOf(number))
                .reduce((n1, n2) -> n1 + n2) ;

        System.out.println("Sum: " + sum);

        // Step 4: stop the spark context
        context.stop() ;

        System.out.println("Computing time (ms): " + (Instant.now().toEpochMilli() - startComputingTime)) ;
    }
}