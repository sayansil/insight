#include<SoftwareSerial.h>

char c;
int x=3;
int y=5;
int z=6;
int a = 8;
int b= 10;
int d =11;

void setup() 
{
  pinMode(x, OUTPUT);
  pinMode(y, OUTPUT);
  pinMode(z, OUTPUT);
  pinMode(a, OUTPUT);
  pinMode(b, OUTPUT);
  pinMode(d, OUTPUT);
  Serial.begin(9600);
}

void loop() 
{  
  while (Serial.available()>0) 
  {
    delay(30);
      c = Serial.read();
 
  if (c == 'r') 
  {
    digitalWrite(z, HIGH);
    digitalWrite(d, HIGH);
    digitalWrite(a, LOW);
    digitalWrite(b, LOW);
    digitalWrite(x, LOW);
    digitalWrite(y, LOW);    
  } 
  if (c == 'g') 
  {

    digitalWrite(y, HIGH);
    digitalWrite(b, HIGH);
    digitalWrite(a, LOW);
    digitalWrite(d, LOW);
    digitalWrite(x, LOW);
    digitalWrite(z, LOW);
  } 
  if (c == 'b') 
  {
      digitalWrite(x, HIGH);
      digitalWrite(a, HIGH);
    digitalWrite(d, LOW);
    digitalWrite(b, LOW);
    digitalWrite(z, LOW);
    digitalWrite(y, LOW);      
  }
 }
}

