import streamlit as st
import requests

API_HOST = "http://localhost:8000"
API_VERSION = "/api/v1"

@st.cache_data
def get_all_projects():
    response = requests.get(API_HOST + API_VERSION + "/project")
    if not response.ok:
        st.warning("Project list endpoint failed")
        st.stop()
    project_list = response.json()
    return project_list

##PAGE

st.markdown("# My App")

with st.sidebar:
    st.markdown("## Create a new Project")

    # Usar st.session_state para evitar dobles inputs
    if "create_project" not in st.session_state:
        st.session_state.create_project = False

    # Input del nombre del proyecto
    project_name = st.text_input("Project name")

    # Botón para crear el proyecto
    create_project = st.button("Create")

    # Verificar que se presionó el botón y el nombre del proyecto no está vacío
    if create_project and project_name.strip():
        response = requests.post(f"{API_HOST}{API_VERSION}/{project_name}")

        if not response.ok:
            st.warning("Project creation endpoint failed")
            st.stop()

        # Limpiar caché correctamente
        st.cache_data.clear()

        st.success("Project creates successfully")
        # Resetear estado del botón

        st.session_state.create_project = False
    elif create_project and not project_name.strip():
        st.warning("Project name cannot be empty")

project_list = get_all_projects()

project_names = [project["name"] for project in project_list]

selected_project = st.selectbox(
    "What project do you want to log hours into?",
    project_names,
)

if st.button("Delete Project"):
    response = requests.delete(f"{API_HOST}{API_VERSION}/{selected_project}")

    # Limpiar caché correctamente
    st.cache_data.clear()

    if not response.ok:
        st.warning("Project deletion endpoint failed")
        st.stop()
    else:
        st.success("Project deleted successfully")
        project_list = get_all_projects()  # Reload the projects

if selected_project:
    st.markdown(f"## Details on project {selected_project} ")
    current_project = next((project for project in project_list if project["name"] == selected_project), None)
    time = current_project['time'] if current_project else 0

    hours = time // 3600
    minutes = (time % 3600) // 60
    seconds = time % 60

    hours_input = st.number_input("Hours spent on project", min_value=0, value=hours, step=1)
    minutes_input = st.number_input("Minutes spent on project", min_value=0, value=minutes, step=1)
    seconds_input = st.number_input("Seconds spent on project", min_value=0, value=seconds, step=1)

    # Check if the input values have changed
    values_changed = (hours_input != hours or minutes_input != minutes or seconds_input != seconds)

    if st.button("Update Time", disabled=not values_changed):
        response = requests.put(
            API_HOST + API_VERSION + f"/{current_project['name']}/log",
            json={"hour": hours_input, "minute": minutes_input, "seconds": seconds_input}
        )

        if not response.ok:
            st.warning("Time update endpoint failed")
            st.stop()
        st.success("Time updated successfully")
