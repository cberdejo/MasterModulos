from fastapi import APIRouter, HTTPException, Request
from interfaces import LogTime, Project
from service import get_project, create_project_service,delete_project_service, list_all_projects_service, log_time_service

router = APIRouter()


@router.post("/api/v1/{project_name}")
async def create_project(request: Request, project_name: str):
    db_connection = request.app.state.db

    if get_project(connection=db_connection, project_name=project_name):
        raise HTTPException(status_code=418, detail="The project already exists")
    create_project_service(db_connection,project_name=project_name)
    return {"message": "Project created successfully"}
   
 
@router.post("/api/v1/{project_name}/log")
async def log_time(request: Request, project_name: str, time: LogTime):
    db_connection = request.app.state.db
    log_time_service(db_connection, project_name=project_name, time=time)
    return {"message": "Time logged successfully"}


@router.get("/api/v1/project")
def list_all_projects(request:Request) -> list[Project]:
    db_connection = request.app.state.db
    projects = list_all_projects_service(db_connection)
    return projects


@router.delete("/api/v1/{project_name}")
def delete_project(request: Request, project_name: str):
    db_connection = request.app.state.db
    delete_project_service(db_connection, project_name=project_name)
    return {"message": "Project deleted successfully"}


@router.get("/api/v1/{project_name}/snapshot")
def snapshot(request:Request, project_name:str):
    db_connection = request.app.state.db
    projects = list_all_projects_service(db_connection)
    file_content = "NAME,TIME\n"
