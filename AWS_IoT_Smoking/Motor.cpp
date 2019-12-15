#include "Motor.h"

Motor::Motor(int pin) {
  // Use 'this->' to make the difference between the
  // 'pin' attribute of the class and the 
  // local variable 'pin' created from the parameter.
  this->pin = pin;
  init();
}
void Motor::init() {
  pinMode(pin, OUTPUT);
  pinMode(11,OUTPUT);
  // Always try to avoid duplicate code.
  // Instead of writing digitalWrite(pin, LOW) here,
  // call the function off() which already does that
  off();
  state = MOTOR_OFF;
}
void Motor::on() {
  digitalWrite(pin,LOW);
  digitalWrite(11,LOW);
  state = MOTOR_ON;
}
void Motor::off() {
  digitalWrite(pin,HIGH);
  digitalWrite(11,LOW);
  state = MOTOR_OFF;
}

byte Motor::getState() {
  return state;
}
