 
#----- A simple TCP client program in Python using send() function -----

import socket
import json
import base64 


with open('image.jpg', mode='rb') as file:
    img = file.read()
imageString = base64.encodebytes(img).decode('utf-8')

x = {
  "name": "ahmet",
  "age": 24,
  "city": "istanbul",
  "image": imageString
}

y = json.dumps(x)

# Create a client socket
clientSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM);

 

# Connect to the server
#clientSocket.connect(("127.0.0.1",9090));
clientSocket.connect(("192.168.43.1",2222));
 

# Send data to server
clientSocket.send(y.encode());
 

# Receive data from server
dataFromServer = clientSocket.recv(1024);

 

# Print to the console
print(dataFromServer.decode());

