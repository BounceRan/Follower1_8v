long t, tPrev;
long tInterval = 1000;
int counter = 0;

void drawTime() {
  if (millis() > tPrev + tInterval) {
    tPrev = millis();
    counter +=1;
    //println ("counter = " + counter);
  }
}