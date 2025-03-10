import io
import pandas as pd
from utils import generate_random_dates
from minio import Minio


# Imagine Data is coming from a scraping bot
data_file = "./dataset/train.csv"
BUCKET_NAME = "mobile-data"

client = Minio(
    "localhost:9000",
    access_key="minioadmin",
    secret_key="minioadmin",
    secure=False,
)

if not client.bucket_exists(BUCKET_NAME):
    client.make_bucket(BUCKET_NAME)

df = pd.read_csv(data_file)
print(df.head())


chunk_length = 100

chunks = [df[i : i + chunk_length] for i in range(0, len(df), chunk_length)]

for i, chunk in enumerate(chunks):
    # simulate data comes from different dates
    data_date = generate_random_dates()
    csv_data = chunk.to_csv(index=False)

    file_like_data = io.BytesIO(csv_data.encode())

    client.put_object(
        bucket_name=BUCKET_NAME,
        object_name=f"{data_date.isoformat()}/data.csv",
        data=file_like_data,
        length=len(csv_data),
        content_type="text",
    )

    print(f"Data for {data_date.isoformat()} stored in minio. Chunk {i}")
