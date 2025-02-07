from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from bd import init_db
from bd import rdb

from typing import Literal 


class GetResponseModel(BaseModel):
    key: str
    value: int 
    

class PostResponseModel(BaseModel):
    status: {Literal["ok"]}
    key: str
    value: int

class DeleteResponseModel(BaseModel):
    status: {Literal["ok"]}

init_db()


app = FastAPI()


@app.get("/api/v1/key/{key}") 
def get_key(key: str)-> GetResponseModel:
    value = rdb.get(key)
    if key is None:
        raise HTTPException(status_code=404, detail="Value not found for key {key}")
    return {"key": key, "value": value}


@app.post("/api/v1/key/{key}") 
def set_key(key: str, value: int)-> PostResponseModel:
    rdb.set(key, value)
    return {"status": "ok", "key": key, "value": value}


@app.delete("/api/v1/delete/key/{key}")
def delete_key(key: str):
    if rdb.get(key) is None:
        raise HTTPException(status_code=404, detail="Value not found for key {key}")

    rdb.delete(key)
    return {"status": "ok"}


if __name__ == "__main__":
    import uvicorn

    uvicorn.run("app:app", reload=True)
