from pyspark import SparkConf, SparkContext



def main():
    """
    read the airports.csv file and counts the number of spanish airports
    for each of the four types, writing the results in a text file.
    """
    spark_conf = SparkConf()\
            .setAppName("AddNumbers")

    spark_context = SparkContext(conf=spark_conf)

    spark_context.setLogLevel("ERROR")

    #lectura
    path_file = "./data/airports.csv"
    rdd = spark_context.textFile(path_file)

   
    # Obtener la cabecera
    header = rdd.first()

    # Filtrar la cabecera del RDD
    rdd = rdd.filter(lambda line: line != header)

    #count of words thar contains "Quijote" 
    small_airport = spark_context.textFile(path_file)\
        .filter( lambda line: "small_airport" in line)\
        .count()
    heliport = spark_context.textFile(path_file)\
        .filter( lambda line: "heliport" in line)\
        .count()
    
    medium_airport =  spark_context.textFile(path_file)\
        .filter( lambda line: "medium_airport" in line)\
        .count()
    
    closed =  spark_context.textFile(path_file)\
        .filter( lambda line: "closed" in line)\
        .count()
    large_airport =  spark_context.textFile(path_file)\
        .filter( lambda line: "large_airport" in line)\
        .count()
    
    seaplane_base =  spark_context.textFile(path_file)\
        .filter( lambda line: "seaplane_base" in line)\
        .count()
    
    #print result in a file
     
    with open("result.txt", "w") as file:
        file.write("small_airport: " + str(small_airport) + "\n")
        file.write("heliport: " + str(heliport) + "\n")
        file.write("medium_airport: " + str(medium_airport) + "\n")
        file.write("closed: " + str(closed) + "\n")
        file.write("large_airport: " + str(large_airport) + "\n")
        file.write("seaplane_base: " + str(seaplane_base) + "\n")

    
    spark_context.stop()

if __name__ == "__main__":
    main()