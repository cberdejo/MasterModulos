import redis
import os
from dotenv import load_dotenv

load_dotenv(override=True)

db_host = os.getenv("DB_HOSTNAME")
db_port = int(os.getenv("DB_PORT", 6379))

rdb = redis.Redis(decode_responses=True)


def init_db():
    """Database initialization function."""
    global rdb
    if db_host is not None:
        rdb = redis.Redis(decode_responses=True, host=db_host, port=db_port)
    else:
        rdb = redis.Redis(decode_responses=True)
