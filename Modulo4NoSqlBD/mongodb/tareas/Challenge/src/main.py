import os
from pymongo import MongoClient
import requests
from dotenv import load_dotenv
from datetime import datetime
import time

load_dotenv()

# Variables de entorno
db_url = os.getenv("DATABASE_URL")
db_name = os.getenv("DATABASE_NAME")
db_collection_name = os.getenv("DATABASE_COLLECTION_NAME")

def get_data():
    """Llamada a la API para obtener la posición actual de la estación espacial internacional."""
    try:
        response = requests.get("http://api.open-notify.org/iss-now.json")

        # Verificar si la solicitud fue exitosa (código 200)
        if response.status_code == 200:
            return response.json()
        else:
            print('Error en la solicitud:', response.status_code)
            return None
    except requests.exceptions.RequestException as e:
        # Manejar errores relacionados con la red
        print('Error de red:', e)
        return None

def process_data(data):
    """
    Procesa los datos para transformar el timestamp en una fecha legible
    y formatear las coordenadas en GeoJSON para 2dsphere.
    """
    # Convertir el timestamp en una fecha legible
    timestamp = data.get('timestamp')
    if timestamp is not None:
        date_str = datetime.utcfromtimestamp(timestamp).strftime('%Y-%m-%d %H:%M:%S')
    else:
        date_str = None

    # Extraer las coordenadas de iss_position
    iss_position = data.get('iss_position', {})
    latitude = float(iss_position.get('latitude', 0))  # Convertir a float
    longitude = float(iss_position.get('longitude', 0))  # Convertir a float

    # Construir el nuevo diccionario con el formato GeoJSON
    processed_data = {
        'date': date_str,
        'coordinates': {
            'type': "Point",  # Formato requerido para GeoJSON
            'coordinates': [longitude, latitude]  # Orden: [longitud, latitud]
        },
        'api_message': data.get('message')
    }

    return processed_data

def save_data(data):
    """Guarda los datos en la base de datos MongoDB."""
    client = MongoClient(db_url)  # Crear conexión
    try:
        db = client[db_name]
        collection = db[db_collection_name]
        
        # Crear índice geoespacial si no existe
        collection.create_index([("coordinates", "2dsphere")])
        
        collection.insert_one(data)  # Guardar los datos
        print(f"Datos guardados: {data}")
    finally:
        client.close()  # Cerrar la conexión

def main():
    """Lógica principal del script."""
    data = get_data()
    if data is not None:
        processed_data = process_data(data)
        save_data(processed_data)

if __name__ == "__main__":
    while True:
        main()
        time.sleep(20)  # Esperar 60 segundos antes de la siguiente ejecución
