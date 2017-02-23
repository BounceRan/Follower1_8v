class GameText {
  PFont mes;

  String[] message={"AdobeGothicStd-Bold", "JCHEadA"  };


  GameText() {

    mes=createFont(message[0], 16, true);
    //mes2=createFont(meesage[1],16,true);
  }

  void countText() {

    textFont(mes, 150);
    fill(200, 200, 200,100);
    textAlign(CENTER);
    text(realtimer, .5*width, .6*height);
  }


  void lastCount() {

    textFont(mes, 200);
    fill(255, 50, 50, 90);
    textAlign(CENTER);
    text(lastTimer, .5*width, .6*height);
  }


  void startingText() {
 noStroke();
rectMode(CENTER);
    fill(30,80);
    rect(.5*width,.5*height,width,150);
    textFont(mes, 25);
    fill(240);
    textAlign(CENTER, BOTTOM);
    text("FOLLOWER",.5*width, .45*height);
    textFont(mes, 15);
    textAlign(CENTER);
       text("Move the joystick to follow the leader and be properly assimilated in Flatland society.", .5*width, .5*height);

    
    
  }

  void endingText() {
    noStroke();
rectMode(CENTER);
    fill(20,200);
    rect(.5*width,.45*height,450,200);
  textFont(mes, 30);
    fill(220,20,20);
    textAlign(CENTER, BOTTOM);
    text("CONGRATULATIONS",.5*width, .46*height);
    textFont(mes, 15);
    fill(250);
    textAlign(CENTER);
    text("You become a member of Flatland.", .5*width, .5*height);
    
    
    
  }
}