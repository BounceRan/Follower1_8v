// GPIO numbers refer to different phyiscal pins on various boards
// On the Raspberry Pi GPIO 4 is physical pin 7 on the header

void gpioSetup() {

  GPIO.pinMode(17, GPIO.INPUT);
  GPIO.pinMode(27, GPIO.INPUT);
  GPIO.pinMode(22, GPIO.INPUT);
  GPIO.pinMode(23, GPIO.INPUT);
}


boolean gpioUp= true;
boolean gpioDown=true;
boolean gpioRight=true;
boolean gpioLeft=true;

void gpioFunction() {

//buttonState=false;
  if (GPIO.digitalRead(17) == GPIO.HIGH) {
    gpioUp=false;
    
   // buttonState=true;
  }// else 
  if ( GPIO.digitalRead(17) == GPIO.LOW){
 // else { 
   gpioUp=true;
  }

  if (GPIO.digitalRead(27) == GPIO.HIGH) {
    gpioDown=false;
  } //else 
  if ( GPIO.digitalRead(27) == GPIO.LOW){
  //else {
   gpioDown=true;
  }
  if (GPIO.digitalRead(22) == GPIO.HIGH) {
    gpioLeft=false;
  } //else
  if ( GPIO.digitalRead(22) == GPIO.LOW){
  //else {
   gpioLeft=true;
  }
  if (GPIO.digitalRead(23) == GPIO.HIGH) {
    gpioRight=false;
  } //else 
  if ( GPIO.digitalRead(23) == GPIO.LOW){
  //else {

   gpioRight=true;
  }
}