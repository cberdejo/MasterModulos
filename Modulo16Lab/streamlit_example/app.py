import streamlit as st

st.title("CRUD App with Streamlit")
st.write("This is a simple CRUD app created using Streamlit")
st.subheader("Instructions")
st.write("To create a new album, type 'create' in the text box below and fill in the details.")
st.write("To read an existing album, type 'read' in the text box below and enter the album name.")


st.subheader("Enter your command below")

user_prompt = st.text_input("Enter your prompt here")
if user_prompt.lower() == "pringado":
    st.write("pringado eres tu")
elif user_prompt == "create":
    album_name = st.text_input("Enter the album name")
    artist = st.text_input("Enter the artist name")