char c;
int x;
int y;
int z;

void setup() {
  pinMode(13, OUTPUT);
  Serial.begin(9600);

  x = 11;
  y = 12;
  z = 13;
}

void loop() {
  while (!Serial.available()) {}
  
  while (Serial.available()) 
  {
    delay(30);
    if (Serial.available() >0)
      c = Serial.read();
  }
  if (c == 'r') {
    blink(x);
  } else if (c == 'g') {
    blink(y);
  } else if (c == 'b') {
    blink(z);
  }
}

void blink(int pin) {
    digitalWrite(pin, HIGH);
    delay(1000);
    digitalWrite(pin, LOW);
}
