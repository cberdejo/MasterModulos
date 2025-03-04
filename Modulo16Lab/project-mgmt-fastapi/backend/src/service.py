from fastapi import HTTPException

from interfaces import LogTime, Project



def get_project_by_name_service(connection, project_name: str) -> Project:
    cursor = connection.cursor()
    try:
        cursor.execute("SELECT name, time FROM PROJECT WHERE name = %s", (project_name,))
        result = cursor.fetchone()
        if result is None:
            return None
        return Project(name=result[0], time=result[1])
    except Exception as e:
        print(f"Error en la base de datos: {e}")
        raise HTTPException(status_code=500, detail="Failed to get project by name")
    finally:
        cursor.close()

    

def create_project_service(connection,project_name:str):
    cursor = connection.cursor()
    try:
        cursor.execute("INSERT INTO PROJECT (name, time) VALUES (%s, %s)", (project_name, 0))
        connection.commit()
    except Exception as e:
        print(f"Error en la base de datos: {e}")
        connection.rollback()
        raise HTTPException(status_code=500, detail="Failed to create project")
    finally:
        cursor.close()


def delete_project_service(connection,project_name:str):
    cursor = connection.cursor()
    try:
        cursor.execute("""
            DELETE FROM PROJECT WHERE name =(%s)
        """, (project_name,))
        connection.commit()
    except Exception as e:
        connection.rollback()
        raise HTTPException(status_code=500, detail="Failed to create project")
    finally:
        cursor.close()

def add_time_service(connection, project_name: str, log_time_obj: LogTime):
    cursor = connection.cursor()
    try:
      
        # Obtener tiempo actual del proyecto
        cursor.execute("SELECT time FROM PROJECT WHERE name = %s", (project_name,))
        result = cursor.fetchone()

        if result is None:
            raise HTTPException(status_code=404, detail="Project not found")

        # Convertir a un valor seguro (evitar None)
        current_time = result[0] if result[0] is not None else 0

        # Convertir tiempo a segundos
        total_seconds = log_time_obj.hour * 3600 + log_time_obj.minute * 60 + log_time_obj.seconds
        new_time = current_time + total_seconds

        # Actualizar el tiempo en la base de datos
        cursor.execute("""
            UPDATE PROJECT SET time = %s WHERE name = %s
        """, (new_time, project_name))
        connection.commit()


    except Exception as e:
        print(f"Error al registrar el tiempo: {e}")
        connection.rollback()
        raise HTTPException(status_code=500, detail=f"Failed to log time: {str(e)}")

    finally:
        cursor.close()

def list_all_projects_service(connection) -> list[Project]:
    cursor = connection.cursor()
    try:
        cursor.execute("SELECT name, time FROM PROJECT")
        projects = cursor.fetchall()
        return [Project(name=row[0], time=row[1]) for row in projects]
    except Exception as e:
        print(f"Error en la base de datos: {e}")  
        raise HTTPException(status_code=500, detail="Failed to list all projects")
    finally:
        cursor.close()

def update_time_service(connection, project_name: str, new_time: LogTime):
    cursor = connection.cursor()
    try:
        cursor.execute("SELECT name FROM PROJECT WHERE name = %s", (project_name,))
        result = cursor.fetchone()

        if result is None:
            raise HTTPException(status_code=404, detail="Project not found")

        # Convertir tiempo a segundos
        total_seconds = new_time.hour * 3600 + new_time.minute * 60 + new_time.seconds

        cursor.execute("UPDATE PROJECT SET time = %s WHERE name = %s", (total_seconds, project_name))
        connection.commit()
    except Exception as e:
        print(f"Error al actualizar el tiempo: {e}")
        connection.rollback()
        raise HTTPException(status_code=500, detail=f"Failed to update time: {str(e)}")
    finally:
        cursor.close()