# sudo apt install imagemagick

#----- A simple TCP client program in Python using send() function -----

from difflib import restore
import socket
import json
import base64
from tokenize import String
from turtle import distance 

from gpiozero import Button
from picamera import PiCamera
from datetime import datetime
from signal import pause
from subprocess import check_call

import sys 
import os

sys.path.append(os.path.abspath("./"))
from distance import *


shutdown_btn = Button(17, hold_time=2)
y = str()
camera_button = Button(2)
glasses_led = LED(17)
camera = PiCamera()
imageString = str()
serialDevice = "/dev/ttyAMA0"

with open('./images/1.jpg', mode='rb') as file:
    img = file.read()


def capture():
  # timestamp = datetime.now().isoformat()
  camera.capture('./images/1.jpg')

# camera_button.when_pressed = capture

def image_reduce_size():
  check_call(['convert', './images/1.jpg' , '-quality', '30%' , '/images/1.jpg'])
# convert <INPUT_FILE> -quality 10% <OUTPUT_FILE>

def send_photo():
  capture()
  image_reduce_size()
  imageString = base64.encodebytes(img).decode('utf-8')
  x = {
    "image": imageString,
  }
  y = json.dumps(x)
  clientSocket.send(y.encode())


def shutdown():
  check_call(['sudo', 'poweroff'])



def measure_distance():
  measure(serialDevice)

def info_rasperry():
  x = {
    "battery": 15,
  }
  y = json.dumps(x)
  clientSocket.send(y.encode())

# Create a client socket
clientSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM);

 

#clientSocket.connect(("127.0.0.1",9090));
clientSocket.connect(("192.168.1.78",2222))

glasses_led.on
# button.when_pressed = glasses_led.on
# button.when_released = glasses_led.off
 
while True:
  dataFromServer = clientSocket.recv(1024)
  response = dataFromServer.decode()
  print(dataFromServer.decode())

  if response == "distance":
    measure_distance()
  if response == "info":
    info_rasperry()
  if response == "photo":
    send_photo()
  if response == "shutdown":
    shutdown_btn.when_held = shutdown
    break
  if response == "quit":
    break

pause()
glasses_led.off
print("client is quited!")

