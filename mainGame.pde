int state=0;
void mainGame() {
  if (button.circleOver) {

    //rtimer();
    if (realtimer>-1) {
      gameText.countText();
    }
  } else  {
    realtimer=10;
    gamer.state=0;
    background(random(155), random(50), random(50));
    
    lastTimer=3;
  }

if (realtimer<=-1 && lastTimer>=0) {

    gamer.morphTrans();
    gameText.lastCount();
    
    
   // player2.play();
  
  } 
  
  
  
  
  // Target get random position 
  float mainPCX=random(width);
  float mainPCY=random(height);

   //mainPC.moveCircle(mouseX,mouseY);
  mainPC.driveCircle(mainPCX, mainPCY,.2);
  mainPC.drawCircle();
  mainPC.bounce();
  mainPC.moveCircle();




  
  //println(mainPC.location.x);

  //follower--------------------------------------
  for (int i =0; i<follower.length; i++) {
    follower[i].driveCircle(mainPC.location.x+random(-20*i,20*i), 
                            mainPC.location.y+random(-20*i,20*i),
                            random(.05, .1));
    follower[i].drawCircle();
    follower[i].moveCircle();
  }
  
  button.drawButton(mainPC.location.x, mainPC.location.y, x,y );
  
    pushMatrix();
  gamer.drawMorph();
  gamer.morphDisplay(x, y);
  popMatrix();
  
  
}