#include <Arduino.h>

#define MOTOR_OFF 0
#define MOTOR_ON 1

class Motor {
  private:
    int pin;
    byte state;

  public:
    Motor(int pin);
    void init();
    void on();
    void off();
    byte getState();
};
