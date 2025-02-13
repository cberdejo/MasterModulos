
 ![FastAPI](https://img.shields.io/badge/FastAPI-005571?style=for-the-badge&logo=fastapi)
 ![Redis](https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white)
 [![Ruff](https://img.shields.io/endpoint?url=https://raw.githubusercontent.com/astral-sh/ruff/main/assets/badge/v2.json)](https://github.com/astral-sh/ruff)                                                 


# Fast api to manage value-key
api to manage value-key using redis


## Setup
### 1. Install Dependencies:

Ensure Python is installed.


### 2. Install required packages:

``` sh 
$ pip install -r requirements.txt
```
and for developing (tests and contributing), execute
``` sh 
$ pip install -r requirements-dev.txt
```

### 3. Environment Variables:


Create a `.env` file based on the `env.template` file provided in the project.


#### Define the following variables:

- DB_USER=<your_db_user>
- DB_HOSTNAME=<your_db_hostname>


### Image Redis (DB)
Redis:
``` sh 
$ docker run --name some-redis -p 6379:6379  -d redis
```
Por defecto no

## Execute the script:
Use 
``` sh 
python ./src/app.py
```

## Client
There is a client as en example of how to use de api using the "requests" package

## LICENSE: MIT License file.
License
This project is licensed under the MIT License. See the LICENSE file for details.