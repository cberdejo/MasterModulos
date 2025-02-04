# -*- coding: utf-8 -*-
"""
Script de comparación de performance entre distintas librerías/sistemas:

1. PySpark Dataframes (CSV y Parquet)
2. PySpark RDD (CSV y Parquet)
3. Pandas (CSV y Parquet)
4. Polars (CSV y Parquet)

Autoría original:
- Pablo Nieto Rodríguez
- Pablo Fontádez
- Christian Berdejo Sánchez

Adaptado para un único script y organizado en funciones.

Se miden los tiempos de:
1. Lectura
2. Filtrado (Arrest = true)
3. Agrupación (Group by District)
4. Conteo
5. Ordenamiento

Al final se muestra un gráfico comparativo con los tiempos medidos.
"""

import time
import pandas as pd
import polars as pl
import matplotlib.pyplot as plt
import numpy as np

# PySpark
from pyspark.sql import SparkSession
from pyspark.sql import functions
from pyspark.sql.functions import to_timestamp
from pyspark import SparkConf, SparkContext


############################
#         FUNCIONES        #
############################

def measure_spark_df_csv(path_file):
    """
    Mide los tiempos de lectura, filtrado, group by, count y sort usando Spark DataFrame con un archivo CSV.
    Devuelve un diccionario con cada operación y su tiempo.
    """
    spark_session = SparkSession \
        .builder \
        .appName("SparkDataFrameCSV") \
        .getOrCreate()

    spark_session.sparkContext.setLogLevel("ERROR")

    tiempos = {}

    # Lectura
    start_time = time.time()
    data_frame = (spark_session.read
                               .options(header='true', inferschema='true')
                               .option("delimiter", ",")
                               .option("timestampFormat", "yyyy-MM-dd")
                               .csv(path_file)
                               .persist())
    tiempos["Reading"] = time.time() - start_time

    # Filtrado
    start_time = time.time()
    filtered_df = data_frame.filter("Arrest = true")
    tiempos["Filtering"] = time.time() - start_time

    # Group by
    start_time = time.time()
    grouped_df = filtered_df.groupby("District")
    tiempos["Grouping"] = time.time() - start_time

    # Count
    start_time = time.time()
    counted_df = grouped_df.count()
    tiempos["Counting"] = time.time() - start_time

    # Sort
    start_time = time.time()
    sorted_df = counted_df.orderBy("count", ascending=False)
    tiempos["Sorting"] = time.time() - start_time

    return tiempos


def measure_spark_rdd_csv(path_file):
    """
    Mide los tiempos de lectura, filtrado, group by, count y sort usando Spark RDD con un archivo CSV.
    Devuelve un diccionario con cada operación y su tiempo.
    """
    spark_session = SparkSession \
        .builder \
        .appName("SparkRDDCSV") \
        .getOrCreate()

    spark_context = spark_session.sparkContext
    spark_context.setLogLevel("ERROR")

    tiempos = {}

    # Lectura
    start_time = time.time()
    rdd = spark_context.textFile(path_file)
    tiempos["Reading"] = time.time() - start_time

    header = rdd.first()
    columns = header.split(",")

    try:
        arrest_idx = columns.index("Arrest")
        district_idx = columns.index("District")
    except ValueError:
        raise ValueError("Verifica que las columnas 'Arrest' y 'District' existan en el CSV.")

    rdd_no_header = rdd.filter(lambda line: line != header)
    rdd_parsed = rdd_no_header.map(lambda line: line.split(","))

    # Filtrado
    start_time = time.time()
    rdd_filtered = rdd_parsed.filter(
        lambda row: len(row) > max(arrest_idx, district_idx) and row[arrest_idx].lower() == "true"
    )
    tiempos["Filtering"] = time.time() - start_time

    # Group by (aquí en realidad se mapea a (district,1))
    start_time = time.time()
    rdd_mapped = rdd_filtered.map(lambda row: (row[district_idx], 1))
    tiempos["Grouping"] = time.time() - start_time

    # Count (reduceByKey)
    start_time = time.time()
    rdd_counted = rdd_mapped.reduceByKey(lambda a, b: a + b)
    tiempos["Counting"] = time.time() - start_time

    # Sort
    start_time = time.time()
    rdd_sorted = rdd_counted.sortBy(lambda x: x[1], ascending=False)
    tiempos["Sorting"] = time.time() - start_time

    return tiempos


def measure_pandas_csv(path_file):
    """
    Mide los tiempos de lectura, filtrado, group by, count y sort usando Pandas con un archivo CSV.
    Devuelve un diccionario con cada operación y su tiempo.
    """
    tiempos = {}

    # Lectura
    start_time = time.time()
    df = pd.read_csv(path_file, delimiter=",", low_memory=False)
    tiempos["Reading"] = time.time() - start_time

    # Filtrado
    start_time = time.time()
    filtered_df = df[df["Arrest"] == True]
    tiempos["Filtering"] = time.time() - start_time

    # Agrupación
    start_time = time.time()
    grouped_df = filtered_df.groupby("District").size()
    tiempos["Grouping"] = time.time() - start_time

    # Conteo (en este caso, el size ya devolvió el conteo, pero separamos la medición)
    start_time = time.time()
    counted_df = grouped_df.reset_index(name="count")
    tiempos["Counting"] = time.time() - start_time

    # Ordenamiento
    start_time = time.time()
    sorted_df = counted_df.sort_values(by="count", ascending=False)
    tiempos["Sorting"] = time.time() - start_time

    return tiempos


def measure_polars_csv(path_file):
    """
    Mide los tiempos de lectura, filtrado, group by, count y sort usando Polars con un archivo CSV.
    Devuelve un diccionario con cada operación y su tiempo.
    """
    tiempos = {}

    # Lectura
    start_time = time.time()
    df = pl.read_csv(path_file)
    tiempos["Reading"] = time.time() - start_time

    # Filtrado
    start_time = time.time()
    filter_df = df.filter(pl.col("Arrest") == True)
    tiempos["Filtering"] = time.time() - start_time

    # Agrupación (group_by)
    start_time = time.time()
    grouped_df = filter_df.group_by("District")
    tiempos["Grouping"] = time.time() - start_time

    # Conteo
    start_time = time.time()
    count_df = grouped_df.agg(pl.count().alias("count"))
    tiempos["Counting"] = time.time() - start_time

    # Ordenamiento
    start_time = time.time()
    sort_df = count_df.sort("count", descending=True)
    tiempos["Sorting"] = time.time() - start_time

    return tiempos


def transform_csv_to_parquet(path_file_csv, path_file_parquet="data_parquet"):
    """
    Lee un CSV usando Pandas y lo transforma a parquet (usando pyarrow) en la ruta especificada.
    """
    df = pd.read_csv(path_file_csv)
    df.to_parquet(path_file_parquet, engine='pyarrow')


def measure_spark_df_parquet(path_parquet):
    """
    Mide los tiempos de lectura, filtrado, group by, count y sort usando Spark DataFrame con un archivo Parquet.
    Devuelve un diccionario con cada operación y su tiempo.
    """
    spark_session = SparkSession \
        .builder \
        .appName("SparkDataFrameParquet") \
        .getOrCreate()

    spark_session.sparkContext.setLogLevel("ERROR")

    tiempos = {}

    # Lectura
    start_time = time.time()
    data_frame = (spark_session.read
                               .options(header='true', inferschema='true')
                               .option("delimiter", ",")
                               .option("timestampFormat", "yyyy-MM-dd")
                               .parquet(path_parquet)
                               .persist())
    tiempos["Reading"] = time.time() - start_time

    # Filtrado
    start_time = time.time()
    filtered_df = data_frame.filter("Arrest = true")
    tiempos["Filtering"] = time.time() - start_time

    # Group by
    start_time = time.time()
    grouped_df = filtered_df.groupby("District")
    tiempos["Grouping"] = time.time() - start_time

    # Count
    start_time = time.time()
    counted_df = grouped_df.count()
    tiempos["Counting"] = time.time() - start_time

    # Sort
    start_time = time.time()
    sorted_df = counted_df.orderBy("count", ascending=False)
    tiempos["Sorting"] = time.time() - start_time

    return tiempos


def measure_spark_rdd_parquet(path_parquet):
    """
    Mide los tiempos de lectura, filtrado, group by, count y sort usando Spark RDD
    leyendo primero un DataFrame parquet y convirtiéndolo a RDD.
    Devuelve un diccionario con cada operación y su tiempo.
    """
    spark_session = SparkSession \
        .builder \
        .appName("SparkRDDParquet") \
        .getOrCreate()

    spark_context = spark_session.sparkContext
    spark_context.setLogLevel("ERROR")

    tiempos = {}

    # Lectura (Spark no lee parquet con RDD directamente, se lee DF y se convierte a RDD)
    start_time = time.time()
    spark_df = spark_session.read.parquet(path_parquet)
    rdd = spark_df.rdd
    tiempos["Reading"] = time.time() - start_time

    # Filtrado
    start_time = time.time()
    rdd_filtered = rdd.filter(lambda row: row["Arrest"] == True)
    tiempos["Filtering"] = time.time() - start_time

    # Group by (map -> reduceByKey)
    start_time = time.time()
    rdd_mapped = rdd_filtered.map(lambda row: (row["District"], 1))
    tiempos["Grouping"] = time.time() - start_time

    # Count
    start_time = time.time()
    rdd_counted = rdd_mapped.reduceByKey(lambda a, b: a + b)
    tiempos["Counting"] = time.time() - start_time

    # Sort
    start_time = time.time()
    rdd_sorted = rdd_counted.sortBy(lambda x: x[1], ascending=False)
    tiempos["Sorting"] = time.time() - start_time

    return tiempos


def measure_pandas_parquet(path_parquet):
    """
    Mide los tiempos de lectura, filtrado, group by, count y sort usando Pandas con un archivo Parquet.
    Devuelve un diccionario con cada operación y su tiempo.
    """
    tiempos = {}

    # Lectura
    start_time = time.time()
    df = pd.read_parquet(path_parquet, engine="pyarrow")
    tiempos["Reading"] = time.time() - start_time

    # Filtrado
    start_time = time.time()
    df_filtrado = df[df["Arrest"] == True]
    tiempos["Filtering"] = time.time() - start_time

    # Agrupación
    start_time = time.time()
    df_agrupado = df_filtrado.groupby("District").size().reset_index(name="count")
    tiempos["Grouping"] = time.time() - start_time

    # Conteo (aquí está incluido en size() y reset_index)
    start_time = time.time()
    df_count = df_filtrado.groupby("District")["Arrest"].count()
    tiempos["Counting"] = time.time() - start_time

    # Ordenamiento
    start_time = time.time()
    df_sorted = df_agrupado.sort_values(by="count", ascending=False)
    tiempos["Sorting"] = time.time() - start_time

    return tiempos


def measure_polars_parquet(path_parquet):
    """
    Mide los tiempos de lectura, filtrado, group by, count y sort usando Polars con un archivo Parquet.
    Devuelve un diccionario con cada operación y su tiempo.
    """
    tiempos = {}

    # Lectura
    start_time = time.time()
    df = pl.read_parquet(path_parquet)
    tiempos["Reading"] = time.time() - start_time

    # Filtrado
    start_time = time.time()
    filter_df = df.filter(pl.col("Arrest") == True)
    tiempos["Filtering"] = time.time() - start_time

    # Group by
    start_time = time.time()
    grouped_df = filter_df.group_by("District")
    tiempos["Grouping"] = time.time() - start_time

    # Count
    start_time = time.time()
    count_df = grouped_df.agg(pl.count().alias("count"))
    tiempos["Counting"] = time.time() - start_time

    # Sort
    start_time = time.time()
    sort_df = count_df.sort("count", descending=True)
    tiempos["Sorting"] = time.time() - start_time

    return tiempos


def show_comparison_chart(results_df):
    """
    Genera y muestra el gráfico de barras apiladas para comparar los tiempos de cada método.
    """
    colors = ["red", "steelblue", "green", "purple", "orange", "brown", "magenta", "cyan"]
    operations = ["Reading", "Filtering", "Grouping", "Counting", "Sorting"]

    # Crear vector de ceros para la parte "bottom" del gráfico apilado
    bottom = np.zeros(len(results_df["Method"]))

    fig, ax = plt.subplots(figsize=(9, 6))
    # Graficar cada operación como una capa apilada
    for i, operation in enumerate(operations):
        ax.bar(results_df["Method"], results_df[operation], 
               bottom=bottom, label=operation, color=colors[i])
        bottom += results_df[operation]

    ax.set_title("Performance Comparison")
    ax.set_ylabel("Total Time (s)")
    ax.legend(title="Operations", loc="upper right")
    plt.xticks(rotation=15)
    plt.tight_layout()
    plt.show()


############################
#          MAIN            #
############################

def main():
    # Ajusta estas rutas a tus necesidades
    path_file_csv = "./data/Crimes_-_2001_to_Present.csv"   # CSV original
    path_parquet_file = "./data_parquet"                       # Se genera a partir del CSV

    # 1) Transformar CSV a Parquet (descomentar si no existe el archivo parquet)
    transform_csv_to_parquet(path_file_csv, path_parquet_file)

    # 2) Medición de tiempos para cada método y cada formato
    rdd_csv_times = measure_spark_rdd_csv(path_file_csv)
    sparkdf_csv_times = measure_spark_df_csv(path_file_csv)
    pandas_csv_times = measure_pandas_csv(path_file_csv)
    polars_csv_times = measure_polars_csv(path_file_csv)

    rdd_parquet_times = measure_spark_rdd_parquet(path_parquet_file)
    sparkdf_parquet_times = measure_spark_df_parquet(path_parquet_file)
    pandas_parquet_times = measure_pandas_parquet(path_parquet_file)
    polars_parquet_times = measure_polars_parquet(path_parquet_file)

    # 3) Construir un DataFrame de Pandas con los resultados
    data = {
        "Method": [
            "RDD CSV",
            "SparkDF CSV",
            "Pandas CSV",
            "Polars CSV",
            "RDD Parquet",
            "SparkDF Parquet",
            "Pandas Parquet",
            "Polars Parquet"
        ],
        "Reading": [
            rdd_csv_times["Reading"],
            sparkdf_csv_times["Reading"],
            pandas_csv_times["Reading"],
            polars_csv_times["Reading"],
            rdd_parquet_times["Reading"],
            sparkdf_parquet_times["Reading"],
            pandas_parquet_times["Reading"],
            polars_parquet_times["Reading"]
        ],
        "Filtering": [
            rdd_csv_times["Filtering"],
            sparkdf_csv_times["Filtering"],
            pandas_csv_times["Filtering"],
            polars_csv_times["Filtering"],
            rdd_parquet_times["Filtering"],
            sparkdf_parquet_times["Filtering"],
            pandas_parquet_times["Filtering"],
            polars_parquet_times["Filtering"]
        ],
        "Grouping": [
            rdd_csv_times["Grouping"],
            sparkdf_csv_times["Grouping"],
            pandas_csv_times["Grouping"],
            polars_csv_times["Grouping"],
            rdd_parquet_times["Grouping"],
            sparkdf_parquet_times["Grouping"],
            pandas_parquet_times["Grouping"],
            polars_parquet_times["Grouping"]
        ],
        "Counting": [
            rdd_csv_times["Counting"],
            sparkdf_csv_times["Counting"],
            pandas_csv_times["Counting"],
            polars_csv_times["Counting"],
            rdd_parquet_times["Counting"],
            sparkdf_parquet_times["Counting"],
            pandas_parquet_times["Counting"],
            polars_parquet_times["Counting"]
        ],
        "Sorting": [
            rdd_csv_times["Sorting"],
            sparkdf_csv_times["Sorting"],
            pandas_csv_times["Sorting"],
            polars_csv_times["Sorting"],
            rdd_parquet_times["Sorting"],
            sparkdf_parquet_times["Sorting"],
            pandas_parquet_times["Sorting"],
            polars_parquet_times["Sorting"]
        ]
    }

    results_df = pd.DataFrame(data)

    print("\n====================== RESULTADOS ======================")
    print(results_df)
    print("========================================================\n")

    # 4) Mostrar el gráfico comparativo
    show_comparison_chart(results_df)


# Punto de entrada del script
if __name__ == "__main__":
    main()
