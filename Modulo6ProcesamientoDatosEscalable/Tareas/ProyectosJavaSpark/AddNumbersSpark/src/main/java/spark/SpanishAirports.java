package spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SpanishAirports {

    public static void writeResult(long smallAirport, long heliport, long mediumAirport, long closed, long largeAirport, long seaplaneBase) {
        // Guardar resultados en un archivo
        try (FileWriter writer = new FileWriter("./src/main/resources/result.txt")) {
            writer.write("small_airport: " + smallAirport + "\n");
            writer.write("heliport: " + heliport + "\n");
            writer.write("medium_airport: " + mediumAirport + "\n");
            writer.write("closed: " + closed + "\n");
            writer.write("large_airport: " + largeAirport + "\n");
            writer.write("seaplane_base: " + seaplaneBase + "\n");
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

    }

    public static  void main(String[] args) {
        // Configurar Spark
        SparkConf sparkConf = new SparkConf().setAppName("Airports").setMaster("local[*]");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
        sparkContext.setLogLevel("ERROR");

        // Definir la ruta del archivo
        String pathFile = "./src/main/resources/airports.csv";

        // Leer el archivo como un RDD
        JavaRDD<String> rdd = sparkContext.textFile(pathFile);

        // Eliminar cabecera
        String header = rdd.first();
        JavaRDD<String[]> data = rdd
                .filter(line -> !line.equals(header))
                .map(line -> line.split(","));

        // Obtener índice de la columna "iso_country"

        // Limpiar los nombres de las columnas eliminando comillas
        List<String> columnNames = Arrays.stream(header.split(","))
                .map(s -> s.replaceAll("^\"|\"$", "")) // Elimina comillas dobles al inicio y al final
                .collect(Collectors.toList());

        // Obtener índice de la columna "iso_country"
        int indexIsoCountry = columnNames.indexOf("iso_country");
        int indexType = columnNames.indexOf("type");

        if (indexIsoCountry == -1 || indexType == -1) {
            System.err.println("Error: No se encontraron las columnas 'iso_country' o 'type' en el archivo CSV.");
            sparkContext.close();
            return;
        }

        // Filtrar aeropuertos de España
        JavaRDD<String[]> spanishAirports = data.filter(cols -> cols[indexIsoCountry].equals("ES"));

        // Contar cada tipo de aeropuerto
        long smallAirport = spanishAirports.filter(cols -> cols[indexType].contains("small_airport")).count();
        long heliport = spanishAirports.filter(cols -> cols[indexType].contains("heliport")).count();
        long mediumAirport = spanishAirports.filter(cols -> cols[indexType].contains("medium_airport")).count();
        long closed = spanishAirports.filter(cols -> cols[indexType].contains("closed")).count();
        long largeAirport = spanishAirports.filter(cols -> cols[indexType].contains("large_airport")).count();
        long seaplaneBase = spanishAirports.filter(cols -> cols[indexType].contains("seaplane_base")).count();

        System.out.println( "small_airport: " + smallAirport);
        System.out.println( "heliport: " + heliport);
        System.out.println( "medium_airport: " + mediumAirport);
        System.out.println( "closed: " + closed);
        System.out.println( "large_airport: " + largeAirport);
        System.out.println( "seaplane_base: " + seaplaneBase);

        writeResult(smallAirport, heliport, mediumAirport, closed, largeAirport, seaplaneBase);


        // Cerrar el contexto de Spark
        sparkContext.close();
    }
}
