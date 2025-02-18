import mysql.connector
from contextlib import asynccontextmanager
import sys
from fastapi import FastAPI

DB_CONNECTION = None

@asynccontextmanager
async def mysql_lifespan(app: FastAPI):
    global DB_CONNECTION
    try:
        # Conexión a MySQL
        DB_CONNECTION = mysql.connector.connect(
            host="localhost",  
            user="root",
            password="1234",
        )
        sys.stdout.write("Conexión a la base de datos MySQL establecida\n")

        cursor = DB_CONNECTION.cursor()
        cursor.execute("""
            CREATE TABLE IF NOT EXISTS PROJECT (
                name VARCHAR(255) PRIMARY KEY, 
                time INT         
            )               
        """)
        DB_CONNECTION.commit()
        yield
    finally:
        if DB_CONNECTION:
            DB_CONNECTION.close()
            sys.stdout.write("Conexión a la base de datos cerrada\n")
