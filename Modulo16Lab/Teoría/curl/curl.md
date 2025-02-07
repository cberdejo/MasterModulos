# CURL
https://curl.se/

command line tool and library for transferring data with URLs 

Example domain: http://example.com/

Python alternative: requests library, more information at https://requests.readthedocs.io/en/latest/

Alternative with a GUI: Postman, more information and download at https://www.postman.com/downloads/

# Get request
```shell
$ curl http://www.example.com/
```

# Get request saving detailed logs
```shell
$ curl --trace-ascii debugdump.txt http://www.example.com/
```

# Get request storing to disk

Using same name (only available if a name is available):
```shell
$ curl -O http://www.example.com/
```

With custom name:
```shell
$ curl -o test.html http://www.example.com/
```

# Authentication
```shell
$ curl --user name:password http://www.example.com
```


```shell
$ curl  http://user:password@example.org/
```

```shell
$ curl -H "Authorization: Bearer USERS_ACCESS_TOKEN" http://example.org/
```

# Other types of requests
POST
```shell
$ curl -X POST -d "Hey, this is message is the data sent" http://user:password@example.org/
```

PUT
```shell
$ curl -X PUT -H "Content-Type: application/json"  -d '{"maybe": "json formated"}' http://user:password@example.org/
```

More examples: https://reqbin.com/req/c-d4os3720/curl-put-example

# Custom user agent
```shell
$ curl --user-agent "curl - example in class" http://example.com
```

Examples of user agents: https://deviceatlas.com/blog/list-of-user-agent-strings
