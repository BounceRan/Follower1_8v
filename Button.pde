class Button {
  float circleX, circleY ; 
  float playerXX, playerYY; 
  color circleColor;
  boolean circleOver = false;
  color circleHighlight;
  int circleSize = 250;   // Diameter of circle




  Button() {
    circleColor = color(0, 50);
    circleHighlight = color(204, 50);
  }


  void drawButton(float buttonX_, float buttonY_, float playerXX_, float playerYY_) {

    update(playerXX, playerYY);

    circleX = buttonX_;
    circleY = buttonY_;
    playerXX=playerXX_;
    playerYY=playerYY_;

    if (circleOver) {
     // fill(circleHighlight);
    } else {
      //fill(circleColor);
    }
    noStroke();
    noFill();
    ellipse(circleX, circleY, circleSize, circleSize);
  }


  void update(float x, float y) {
    if ( overCircle(circleX, circleY, circleSize, playerXX, playerYY) ) {
      circleOver = true;
    } else {
      circleOver =  false;
    }
  }


  boolean overCircle(float x, float y, int diameter, float playerX_, float playerY_) {
    float playerX= playerX_;
    float playerY= playerY_;
    float disX = x - playerX;
    float disY = y - playerY;
    if (sqrt(sq(disX) + sq(disY)) < diameter/2 ) {
      return true;
    } else {
      return false;
    }
  }
}