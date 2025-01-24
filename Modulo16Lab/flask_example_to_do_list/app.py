from flask import Flask, request

endpoint = "http://127.0.0.1:5000"
app = Flask(__name__)

completed_tasks = []
todo_tasks=[]


@app.get("/api/todo/v1/tasks")
def get_tasks():
   return {"completed":completed_tasks,"todo":todo_tasks}

@app.post("/api/todo/v1/tasks")
def create_task():
    task = request.get_json()
    validation_result = validate_create_task(task)
    if validation_result is None:
        if task in todo_tasks or task in completed_tasks:
            return "Conflict",409
        todo_tasks.append(task)
        return get_tasks() 
    else:
        return validation_result
    

@app.put("/api/todo/v1/tasks/mark_as_complete/<tid>")
def mark_as_complete(tid:str):
    task = [t for t in todo_tasks if t["id"] == tid]
    if len(task) == 0:
        return "Not Found",404
    todo_tasks.remove(task[0])
    completed_tasks.append(task[0])
    return get_tasks()
@app.put("/api/todo/v1/tasks/mark_as_todo/<tid>")
def mark_as_todo(tid:str):
    task = [t for t in completed_tasks if t["id"] == tid]
    if len(task) == 0:
        return "Not Found",404
    completed_tasks.remove(task[0])
    todo_tasks.append(task[0])
    return get_tasks()

def validate_create_task(task):
    if task:
        
        (id,task) = task
        if not isinstance(id,str) and not isinstance(task,str):
            return "task type not correct",400
   
        else:
            None 
    else: 
        return "task was not sended", 400

@app.get("/healtz")
def version():
    return {"status":"ok","db":"ok"}

@app.get("/api/version")
def version():
    return "v.1.0.0"