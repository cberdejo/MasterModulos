

from fastapi import requests


HOST = 'localhost:8000'

def main(): 
    print("Welcome to the key-value store client")
    print("Commands:")
    print("1. GET key")
    print("2. SET key value")
    print("3. DELETE key")
    print("4. EXIT")

    while True:
        command = input("Enter command: ")
        command = command.split(" ")

        if command[0] == "GET":
            key = command[1]
            response = requests.get(f"http://{HOST}/api/v1/key/{key}")
            print(response.json())

        elif command[0] == "SET":
            key = command[1]
            value = command[2]
            response = requests.post(f"http://{HOST}/api/v1/key/{key}", json={"value": value})
            print(response.json())

        elif command[0] == "DELETE":
            key = command[1]
            response = requests.delete(f"http://{HOST}/api/v1/delete/key/{key}")
            print(response.json())

        elif command[0] == "EXIT":
            break

        else:
            print("Invalid command")

