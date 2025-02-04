from datetime import datetime
db = [
    {
        "id": 1,
        "name": "John Doe",
        "access_time": datetime.datetime.now()
    },
    {
        "id": 2,
        "name": "Jane Doe",
        "access_time": datetime.datetime.now()
    },
    {
        "id": 3,
        "name": "John Smith",
        "access_time": datetime.datetime.now()
    }
]


#Basic
def bring_data_from_database() -> list[tuple[int,str,datetime]] :
    global db 

    return [ (data["id"], data["name"], data["access_time"]) for data in db]

#Pandas 
import pandas as pd

def bring_data_from_databases() -> pd.DataFrame:
    global db
    return pd.DataFrame(db)


from collections import namedtuple 

CV_ACCESS = namedtuple("CV_ACCESS","id","name","access_time")

def bring_data_from_databasess() -> list[CV_ACCESS]:
    global db
    return [ CV_ACCESS(data["id"], data["name"], data["access_time"]) for data in db]

#class 
class CV_ACCESS_Class:
    def __init__(self, id:int, name:str, access_time:datetime):
        self.id = id
        self.name = name
        self.access_time = access_time
    def __str__ (self):
        return f"{self.id} - {self.name} - {self.access_time}"
    def __repr__(self):
        return f"{self.id} - {self.name} - {self.access_time}"
    

def bring_data_from_databasess() -> list[CV_ACCESS_Class]:
    global db
    return [ CV_ACCESS_Class(data["id"], data["name"], data["access_time"]) for data in db]

#Dataclass
from dataclasses import dataclass

@dataclass
class CV_ACCESS_Dataclass:
    id: int
    name: str
    access_time: datetime
 
def bring_data_from_databasess() -> list[CV_ACCESS_Dataclass]:
    global db
    return [ CV_ACCESS_Dataclass(data["id"], data["name"], data["access_time"]) for data in db]

#Pydantic
from pydantic import BaseModel
 
class CV_ACCESS_Pydantic(BaseModel):
    id: int
    name: str
    access_time: datetime

def bring_data_from_databasesss() -> list[CV_ACCESS_Pydantic]:
    global db
    return [ CV_ACCESS_Pydantic(**data) for data in db]