int nCircle=25;

Ending ending[]= new Ending[nCircle];
Ending ending2[]= new Ending[nCircle];
Ending endingCover;
Ending ending3[]= new Ending[nCircle];
Ending ending4[]= new Ending[nCircle];
Ending ending5[]= new Ending[nCircle];




//GPIO

import processing.io.*;

boolean buttonState=false;

Morph gamer;

Circle mainPC;
int nFollower=15;

Circle follower[]=new Circle[nFollower];


Button button;

GameText gameText;


int gameState=0;

//player starting point
float x=.5*width;
float y=.5*height;

void setup() {

musicSetup();

  surface.setSize(800, 480);
  //surface.setResizable(true);
  frameRate(30);
  gamer= new Morph();
  mainPC= new Circle(70);
  gameText= new GameText();

  for (int i=0; i<follower.length; i++) {
    follower[i]=new Circle(int(random(15, 30)));
  }

gpioSetup();
  button= new Button();
  
    //ending scene
  for (int i=0; i<nCircle; i++){

ending[i] = new Ending(i*40,height*2/3, int(random(15,30))); 
ending2[i] = new Ending(i*40,height*1.5/3, int(random(15,30))); 
ending3[i] = new Ending(i*40,height*1/3, int(random(15,30))); 
 ending4[i] = new Ending(i*40,height*2.5/3, int(random(15,30))); 
  ending5[i] = new Ending(i*40,height*.5/3, int(random(15,30))); 
}


  endingCover= new Ending((nCircle/2)*40, height*2/3, int(30) );

  
  
  
  
  
  
  
  
}

void draw() {

  noCursor();


  background(0);

  //mainGame();

  //Player---------------------------------------  
  //joyStick(w, s, a, d);
  timer();
drawTime();
  switch(gameState) {
  case 0:
  //player.pause();


 

  
 
  
  
  
  
    gamer.changeColor();
    x=.5*width;
    y=.5*height;
    lastTimer=3;
    gameText.startingText();

    gpioFunction();
    joyStick(gpioUp, gpioDown, gpioRight, gpioLeft);

    joyStick(w, s, a, d);
    break;

  case 1:
  musicPlay();
  
 
  
   gpioFunction();
    joyStick(gpioUp, gpioDown, gpioRight, gpioLeft);

    joyStick(w, s, a, d);
    mainGame();
    break;

  case 2:
    
    
  
    
    
    
    mainPC.drawCircle();
    gamer.drawMorph();


    for (int i =0; i<follower.length; i++) {
      //follower[i].driveCircle(mainPC.location.x+random(-20*i,20*i), 
      //                        mainPC.location.y+random(-20*i,20*i),
      //                        random(.05, .1));
      follower[i].drawCircle();
      // follower[i].moveCircle();
    }
    pushMatrix();
    gamer.drawMorph();
    gamer.morphDisplay(x, y);
    popMatrix();


    gameText.endingText();

    //noLoop();
    break;
        
    case 3:
    
    
    for (int i =0; i<nCircle; i++) {
      
     ending[i].drawCircle(230);
       ending2[i].drawCircle(230);
         ending3[i].drawCircle(230);
           ending4[i].drawCircle(230);
           ending5[i].drawCircle(230);
    }
    
    endingCover.drawCircle(0);
    
    
     pushMatrix();
    gamer.drawMorph();
    gamer.morphDisplay((nCircle/2)*40, height*2/3);
    popMatrix();

    gameText.endingText();
    
  
  break;
    
    
    
    
    
  }
 if (buttonState==true) {
    gameState=1;
    buttonState=false;
  } else if (lastTimer<=-1 && lastTimer>=-8) {

    // noLoop();
    gameState=2;
  } else if (lastTimer<-8 &&lastTimer>-18) {

    gameState=3;
  } else if (lastTimer<=-18){
  
  gameState=0;
  
  }



//println(lastTimer);





  //println(button.circleOver);
}


//void mouseClicked() {
//  gamer.morphTrans();
//}