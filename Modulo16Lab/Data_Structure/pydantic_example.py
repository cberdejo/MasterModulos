from pydantic import BaseModel, ConfigDict 

class Student (BaseModel):
    model_config = ConfigDict(extra='forbid') # This will raise an error if there are extra fields
    name: str
    age: int | None = None
    surname: list[str]

class Teacher (BaseModel):
    model_config = ConfigDict(extra='allow') # This will allow extra fields
    name: str
    age: int | None = None
    surname: list[str]
    students: list[Student]

students = [
    {
        "name": "John",
        "age": 20,
        "surname": ["Doe"]
    },
    {
        "name": "Jane",
        "age": 21,
        "surname": ["Doe"]
    }
]

teacher = Teacher(name="John", surname=["Doe"], students=students)

print(teacher.model_dump())

data = { "name": "John", "surname": ["Doe"], "students": students, "extra_field": "extra" }
teacher = Teacher(**data) 
# ** packing and unpacking arguments