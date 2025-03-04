from fastapi import FastAPI
from bd import mysql_lifespan
from controller import router


app = FastAPI(lifespan=mysql_lifespan)

app.include_router(router)


if __name__ == '__main__':
    import uvicorn 
    uvicorn.run("app:app", reload=True)