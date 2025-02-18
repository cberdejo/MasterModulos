from pydantic import BaseModel


class LogTime(BaseModel):
    hour:int
    minute:int | None
    seconds: int | None


class Project(BaseModel):
    name: str
    time: int