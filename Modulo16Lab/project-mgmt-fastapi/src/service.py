from fastapi import HTTPException

from interfaces import LogTime, Project


def get_project(connection,project_name:str): 
    cursor = connection.cursor()
    try:
        cursor.execute("SELECT name from Project p where p.name = '?'",{project_name})
    except Exception as e:
        connection.rollback()
        raise HTTPException(status_code=500, detail="Failed to get project by name")
    finally:
        cursor.close()   
    

def create_project_service(connection,project_name:str):
    cursor = connection.cursor()
    try:
        cursor.execute("""
            INSERT INTO projects (name) VALUES (%s)
        """, (project_name,))
        connection.commit()
    except Exception as e:
        connection.rollback()
        raise HTTPException(status_code=500, detail="Failed to create project")
    finally:
        cursor.close()

def delete_project_service(connection,project_name:str):
    cursor = connection.cursor()
    try:
        cursor.execute("""
            DELETE FROM projects WHERE name =(%s)
        """, (project_name,))
        connection.commit()
    except Exception as e:
        connection.rollback()
        raise HTTPException(status_code=500, detail="Failed to create project")
    finally:
        cursor.close()

def log_time_service(connection, project_name: str, time: LogTime):
    cursor = connection.cursor()
    try:
        cursor.execute("SELECT time FROM projects WHERE name = %s", (project_name,))
        result = cursor.fetchone()
        if result is None:
            raise HTTPException(status_code=404, detail="Project not found")
        
        current_time = result[0] if result[0] is not None else 0
        total_seconds = time.hours * 3600 + time.minutes * 60 + time.seconds
        new_time = current_time + total_seconds

        cursor.execute("""
            UPDATE projects SET time = %s WHERE name = %s
        """, (new_time, project_name))
        connection.commit()
    except Exception as e:
        connection.rollback()
        raise HTTPException(status_code=500, detail="Failed to log time")
    finally:
        cursor.close()

def list_all_projects_service(connection) -> list[Project]:
    cursor = connection.cursor()
    try:
        cursor.execute("SELECT name, time FROM projects")
        projects = cursor.fetchall()
        return [Project(name=row[0], time=row[1]) for row in projects]
    except Exception as e:
        connection.rollback()
        raise HTTPException(status_code=500, detail="Failed to list all projects")
    finally:
        cursor.close()