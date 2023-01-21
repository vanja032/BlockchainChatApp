import subprocess
import json
import time
from flask import *
from flask_api import status
from flask_cors import CORS, cross_origin
from encrypt import AES
import re


app = Flask(__name__)
CORS(app)
app.config['CORS_HEADERS'] = 'Content-Type'

#private_key = "<app_account_private_key"
#private_key = "5JXtd4JYEHDSURvdT1bZyS7PBJg6V63xgWiSVCnMcvU2upvkGtx"
#private_key = 0x354a587464344a594548445355527664
private_key = "5JXtd4JYEHDSURvdT1bZyS7PBJg6V63x"

regex = r'\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Z|a-z]{2,}\b'
actions = ["adduser", "addmessage", "addconnect", "removeuser"]
accounts = ["chatapp"]
tables = ["users"]

encrypt = AES(private_key)

@app.route('/adduser', methods=['POST', 'PUT'])
@cross_origin()
def AddUser():
    try:
        data = str(request.data.decode("utf-8"))
        #print(data)
        #print(encrypt.decrypt(data, private_key))
        #data = json.loads(encrypt.decrypt(data, private_key))
        data = json.loads(data)
        add_user(accounts[0], actions[0], data)
        return "{\"message\": \"successful\"}"
    except Exception as ex:
        print(ex)
        return "{\"message\": \"failed\"}", status.HTTP_400_BAD_REQUEST

def add_user(account, action, data):
    #subprocess.run(["cleos", "wallet", "unlock", "--password", "<wallet_password>"])
    subprocess.run(["cleos", "wallet", "unlock", "--password", "PW5JqmN1MrA9rUPe8TF4GYBv3qK5BRZawrWm9R3kgDgVDg2kGRYuc"])
    username = data["username"]
    password = str(encrypt.encrypt(data["password"]))
    email = str(encrypt.encrypt(data["email"]))
    f_name = str(encrypt.encrypt(data["f_name"]))
    l_name = str(encrypt.encrypt(data["l_name"]))
    picture = str(encrypt.encrypt(data["picture"]))
    subprocess.run(["cleos", "push", "action", str(account), str(action), "[\"" + username + "\", \"" + password + "\", \"" + email + "\", \"" + f_name + "\", \"" + l_name + "\", \"" + picture + "\"]", "-p", account + "@active"])


@app.route('/addmessage', methods=['POST', 'PUT'])
@cross_origin()
def AddMessage():
    try:
        data = str(request.data.decode("utf-8"))
        #print(data)
        #print(encrypt.decrypt(data, private_key))
        #data = json.loads(encrypt.decrypt(data, private_key))
        data = json.loads(data)
        add_message(accounts[0], actions[1], data)
        return "{\"message\": \"successful\"}"
    except Exception as ex:
        print(ex)
        return "{\"message\": \"failed\"}", status.HTTP_400_BAD_REQUEST

def add_message(account, action, data):
    #subprocess.run(["cleos", "wallet", "unlock", "--password", "<wallet_password>"])
    subprocess.run(["cleos", "wallet", "unlock", "--password", "PW5JqmN1MrA9rUPe8TF4GYBv3qK5BRZawrWm9R3kgDgVDg2kGRYuc"])
    from_user = data["from"]
    to_user = data["to"]
    message = str(encrypt.encrypt(data["message"]))
    username = data["username"]
    password = str(encrypt.encrypt(data["password"]))
    subprocess.run(["cleos", "push", "action", str(account), str(action), "[\"" + from_user + "\", \"" + to_user + "\", \"" + message + "\", \"" + username + "\", \"" + password + "\"]", "-p", account + "@active"])


@app.route('/addconnection', methods=['POST', 'PUT'])
@cross_origin()
def AddConnection():
    try:
        data = str(request.data.decode("utf-8"))
        #print(data)
        #print(encrypt.decrypt(data, private_key))
        #data = json.loads(encrypt.decrypt(data, private_key))
        data = json.loads(data)
        add_connection(accounts[0], actions[2], data)
        return "{\"message\": \"successful\"}"
    except Exception as ex:
        print(ex)
        return "{\"message\": \"failed\"}", status.HTTP_400_BAD_REQUEST

def add_connection(account, action, data):
    #subprocess.run(["cleos", "wallet", "unlock", "--password", "<wallet_password>"])
    subprocess.run(["cleos", "wallet", "unlock", "--password", "PW5JqmN1MrA9rUPe8TF4GYBv3qK5BRZawrWm9R3kgDgVDg2kGRYuc"])
    id = data["id"]
    connection = data["connection"]
    username = data["username"]
    password = str(encrypt.encrypt(data["password"]))
    subprocess.run(["cleos", "push", "action", str(account), str(action), "[\"" + id + "\", \"" + connection + "\", \"" + username + "\", \"" + password + "\"]", "-p", account + "@active"])


@app.route('/removeuser', methods=['POST', 'PUT'])
@cross_origin()
def RemoveUser():
    try:
        data = str(request.data.decode("utf-8"))
        #print(data)
        #print(encrypt.decrypt(data, private_key))
        #data = json.loads(encrypt.decrypt(data, private_key))
        data = json.loads(data)
        remove_user(accounts[0], actions[3], data)
        return "{\"message\": \"successful\"}"
    except Exception as ex:
        print(ex)
        return "{\"message\": \"failed\"}", status.HTTP_400_BAD_REQUEST

def remove_user(account, action, data):
    #subprocess.run(["cleos", "wallet", "unlock", "--password", "<wallet_password>"])
    subprocess.run(["cleos", "wallet", "unlock", "--password", "PW5JqmN1MrA9rUPe8TF4GYBv3qK5BRZawrWm9R3kgDgVDg2kGRYuc"])
    id = data["id"]
    username = data["username"]
    password = str(encrypt.encrypt(data["password"]))
    print(encrypt.encrypt(data["password"]))
    subprocess.run(["cleos", "push", "action", str(account), str(action), "[\"" + id + "\", \"" + username + "\", \"" + password + "\"]", "-p", account + "@active"])


@app.route('/getuser', methods=['POST', 'PUT'])
@cross_origin()
def GetUser():
    try:
        data = str(request.data.decode("utf-8"))
        #print(data)
        #print(encrypt.decrypt(data, private_key))
        #data = json.loads(encrypt.decrypt(data, private_key))
        data = json.loads(data)
        return get_user(accounts[0], tables[0], data)
    except Exception as ex:
        print(ex)
        return "Request has not been processed.", status.HTTP_400_BAD_REQUEST

def get_user(account, table, data):
    username = data["username"]
    password = str(encrypt.encrypt(data["password"]))
    output = json.loads(subprocess.Popen(["cleos", "get", "table", str(account), str(account), str(table), "--lower", username, "--index", "2", "--key-type", "i64"], stdout=subprocess.PIPE).communicate()[0].decode())
    if len(output["rows"]) > 0:
        #print(output["rows"][0]["password"])
        #print(password)
        if output["rows"][0]["password"] == password and output["rows"][0]["username"] == username:
            #Decrypt fetched data
            response = {
            "user_id":output["rows"][0]["user_id"], 
            "username":output["rows"][0]["username"], 
            "password":encrypt.decrypt(output["rows"][0]["password"]),
            "email":encrypt.decrypt(output["rows"][0]["email"]),
            "f_name":encrypt.decrypt(output["rows"][0]["f_name"]),
            "l_name":encrypt.decrypt(output["rows"][0]["l_name"]),
            "picture":encrypt.decrypt(output["rows"][0]["picture"])}
            messages = []
            for message in output["rows"][0]["messages"]:
                messages.append(
                {"subject":message["subject"],
                "status":message["status"],
                "username":message["username"],
                "f_name":encrypt.decrypt(message["f_name"]),
                "l_name":encrypt.decrypt(message["l_name"]),
                "picture":encrypt.decrypt(message["picture"]),
                "message":encrypt.decrypt(message["message"]),
                "time":message["time"]}
                )
            response["messages"] = messages
            connections = []
            for connection in output["rows"][0]["connections"]:
                connections.append(
                {"connection":connection["connection"],
                "username":connection["username"],
                "f_name":encrypt.decrypt(connection["f_name"]),
                "l_name":encrypt.decrypt(connection["l_name"]),
                "picture":encrypt.decrypt(connection["picture"]),
                "time":connection["time"]}
                )
            response["connections"] = connections
            #print(response)
            return str(response)
        else:
            raise Exception("No record has been found.")
    else:
        raise Exception("No record has been found.")

if __name__ == "__main__":
    #app.run(host="0.0.0.0", port="443", ssl_context=("<cert.pem>", "<privkey.pem>"))
    app.run(host="0.0.0.0", port="33333")
