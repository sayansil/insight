import serial
import time

s = serial.Serial(
    '/dev/ttyACM0',
    9600,
    timeout=5)

time.sleep(2)

while True:
    file = open("rgb.txt", "r")
    data = file.read(1)
    file.close()
    s.write(data.encode())
    time.sleep(1)