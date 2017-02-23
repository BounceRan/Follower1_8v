
int time=0;
int realtimer=10;
int lastTimer=3;
void timer() {
  int m=millis();

  if ( (m-time>=1000)&&realtimer>=-1) {
player2.rewind();
    realtimer--;
    time=m;
  }
 


  if ( (m-time>=1000)&&realtimer<=0&& lastTimer>=-18) {

    lastTimer--;
    time=m;
  }
}