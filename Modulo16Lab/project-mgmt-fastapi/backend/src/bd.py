import mysql.connector
from contextlib import asynccontextmanager
import sys
from fastapi import FastAPI

@asynccontextmanager
async def mysql_lifespan(app: FastAPI):
    try:
        # Conexión inicial sin base de datos para verificar su existencia
        init_connection = mysql.connector.connect(
            host="127.0.0.1",
            user="root",
            password="my-secret-pw",
            port=3306
        )
        sys.stdout.write("Conexión inicial a MySQL establecida\n")

        cursor = init_connection.cursor()
        cursor.execute("CREATE DATABASE IF NOT EXISTS db;")
        init_connection.commit()
        cursor.close()
        init_connection.close()
        sys.stdout.write("Base de datos 'db' creada o ya existente\n")

        # Ahora conectarse a la base de datos
        db_connection = mysql.connector.connect(
            host="127.0.0.1",
            user="root",
            password="my-secret-pw",
            database="db",
            port=3306
        )
        sys.stdout.write("Conexión a la base de datos MySQL establecida\n")

        cursor = db_connection.cursor()
        cursor.execute("""
            CREATE TABLE IF NOT EXISTS PROJECT (
                name VARCHAR(255) PRIMARY KEY, 
                time INT         
            )               
        """)
        db_connection.commit()
        sys.stdout.write("Tabla 'PROJECT' creada o ya existente\n")

        # Guardar la conexión en `app.state.db`
        app.state.db = db_connection

        yield

    finally:
        if app.state.db:
            app.state.db.close()
            sys.stdout.write("Conexión a la base de datos cerrada\n")
