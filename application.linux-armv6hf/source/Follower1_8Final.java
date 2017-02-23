import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.io.*; 
import ddf.minim.*; 
import ddf.minim.ugens.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Follower1_8Final extends PApplet {

int nCircle=25;

Ending ending[]= new Ending[nCircle];
Ending ending2[]= new Ending[nCircle];
Ending endingCover;
Ending ending3[]= new Ending[nCircle];
Ending ending4[]= new Ending[nCircle];
Ending ending5[]= new Ending[nCircle];




//GPIO



boolean buttonState=false;

Morph gamer;

Circle mainPC;
int nFollower=15;

Circle follower[]=new Circle[nFollower];


Button button;

GameText gameText;


int gameState=0;

//player starting point
float x=.5f*width;
float y=.5f*height;

public void setup() {

musicSetup();

  surface.setSize(800, 480);
  //surface.setResizable(true);
  frameRate(30);
  gamer= new Morph();
  mainPC= new Circle(70);
  gameText= new GameText();

  for (int i=0; i<follower.length; i++) {
    follower[i]=new Circle(PApplet.parseInt(random(15, 30)));
  }

gpioSetup();
  button= new Button();
  
    //ending scene
  for (int i=0; i<nCircle; i++){

ending[i] = new Ending(i*40,height*2/3, PApplet.parseInt(random(15,30))); 
ending2[i] = new Ending(i*40,height*1.5f/3, PApplet.parseInt(random(15,30))); 
ending3[i] = new Ending(i*40,height*1/3, PApplet.parseInt(random(15,30))); 
 ending4[i] = new Ending(i*40,height*2.5f/3, PApplet.parseInt(random(15,30))); 
  ending5[i] = new Ending(i*40,height*.5f/3, PApplet.parseInt(random(15,30))); 
}


  endingCover= new Ending((nCircle/2)*40, height*2/3, PApplet.parseInt(30) );

  
  
  
  
  
  
  
  
}

public void draw() {

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
    x=.5f*width;
    y=.5f*height;
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
class Button {
  float circleX, circleY ; 
  float playerXX, playerYY; 
  int circleColor;
  boolean circleOver = false;
  int circleHighlight;
  int circleSize = 250;   // Diameter of circle




  Button() {
    circleColor = color(0, 50);
    circleHighlight = color(204, 50);
  }


  public void drawButton(float buttonX_, float buttonY_, float playerXX_, float playerYY_) {

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


  public void update(float x, float y) {
    if ( overCircle(circleX, circleY, circleSize, playerXX, playerYY) ) {
      circleOver = true;
    } else {
      circleOver =  false;
    }
  }


  public boolean overCircle(float x, float y, int diameter, float playerX_, float playerY_) {
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
class Circle {

  PVector location;
  PVector direction;
  PVector velocity;

  float speed; 
  int rad;
  float forceN;








  Circle(int rad_) {
    float x=random(-1, 1);
    float y=random(-1, 1);
    rad=rad_;
    location= new PVector(random(width), random(height));
    direction= new PVector(x, y);
    velocity=new PVector(0, 0);

    //force of turn
    
    speed=4;
  }


  public void driveCircle( float x, float y,float forceN_) {

    // direction.mult(.5);
    float angle = atan2 (y-location.y, x -location.x);

    PVector force = new PVector (cos (angle), sin (angle));

    force.mult(forceN);

    direction.add (force);
    direction.normalize();

    location.add(direction);

  forceN=forceN_;
   
  }


  public void drawCircle() {

//strokeWeight(3);
    
    noStroke();
    //stroke(200);
    //noFill();
fill(200,240);
    ellipse(location.x, location.y, rad, rad);
  }




  public void moveCircle() {

    PVector velocity=direction.copy();
    velocity.mult(speed);
    location.add(velocity);
  }




  public void bounce() {

    if (location.x > width-rad*5||location.x<rad*5) {
      velocity.x*=-1;
    }

    if (location.y > height-rad*5|| location.y<rad*5) {
      velocity.y*=-1;
    }
  }
}
class Ending{
PVector EndLocation;

int rad;
 float x;
  float y;
  
  
  int Ccolor;
  
Ending(float _x, float _y, int _rad){

 x= _x;
 y= _y;
  EndLocation = new PVector(x,y);
  rad= _rad;
  


}


  public void drawCircle(int _Ccolor) {

//strokeWeight(3);
    Ccolor=_Ccolor;
    noStroke();
    //stroke(200);
    //noFill();
fill(Ccolor,240);
    ellipse(x, y, rad, rad);
  }

}
class GameText {
  PFont mes;

  String[] message={"AdobeGothicStd-Bold", "JCHEadA"  };


  GameText() {

    mes=createFont(message[0], 16, true);
    //mes2=createFont(meesage[1],16,true);
  }

  public void countText() {

    textFont(mes, 150);
    fill(200, 200, 200,100);
    textAlign(CENTER);
    text(realtimer, .5f*width, .6f*height);
  }


  public void lastCount() {

    textFont(mes, 200);
    fill(255, 50, 50, 90);
    textAlign(CENTER);
    text(lastTimer, .5f*width, .6f*height);
  }


  public void startingText() {
 noStroke();
rectMode(CENTER);
    fill(30,80);
    rect(.5f*width,.5f*height,width,150);
    textFont(mes, 25);
    fill(240);
    textAlign(CENTER, BOTTOM);
    text("FOLLOWER",.5f*width, .45f*height);
    textFont(mes, 15);
    textAlign(CENTER);
       text("Move the joystick to follow the leader and be properly assimilated in Flatland society.", .5f*width, .5f*height);

    
    
  }

  public void endingText() {
    noStroke();
rectMode(CENTER);
    fill(20,200);
    rect(.5f*width,.45f*height,450,200);
  textFont(mes, 30);
    fill(220,20,20);
    textAlign(CENTER, BOTTOM);
    text("CONGRATULATIONS",.5f*width, .46f*height);
    textFont(mes, 15);
    fill(250);
    textAlign(CENTER);
    text("You become a member of Flatland.", .5f*width, .5f*height);
    
    
    
  }
}
class Morph {

  // Two ArrayLists to store the vertices for two shapes
  // This example assumes that each shape will have the same
  // number of vertices, i.e. the size of each ArrayList will be the same
  ArrayList<PVector> circle = new ArrayList<PVector>();
  ArrayList<PVector> square = new ArrayList<PVector>();

  // An ArrayList for a third set of vertices, the ones we will be drawing
  // in the window
  ArrayList<PVector> morph = new ArrayList<PVector>();

  int state = 0;
  int newColor;


  Morph() {



    // Create a circle using vectors pointing from center
    for (int angle = 0; angle < 360; angle += 9) {
      // Note we are not starting from 0 in order to match the
      // path of a circle.  
      PVector v = PVector.fromAngle(radians(angle-135));
      v.mult(20);
      circle.add(v);
      // Let's fill out morph ArrayList with blank PVectors while we are at it
      morph.add(new PVector());
    }

    // A square is a bunch of vertices along straight lines
    // Top of square
    for (int x = -20; x < 20; x += 4) {
      square.add(new PVector(x, -20));
    }
    // Right side
    for (int y = -20; y < 20; y += 4) {
      square.add(new PVector(20, y));
    }
    // Bottom
    for (int x = 20; x > -20; x -= 4) {
      square.add(new PVector(x, 20));
    }
    // Left side
    for (int y = 20; y > -20; y -= 4) {
      square.add(new PVector(-20, y));
    }
  }

  public void changeColor() {
    newColor=color(random(100, 150), random(50), random(50));
  }


  public void drawMorph () {

    // Look at each vertex
    for (int i = 0; i < circle.size(); i++) {
      PVector v1;
      // Are we lerping to the circle or square?
      //if (state) {
      //  v1 = circle.get(i);
      //} else {
      //  v1 = square.get(i);
      //}



      if (state==1) {
        v1 = circle.get(i);
      } else {
        v1 = square.get(i);
      }




      // Get the vertex we will draw
      PVector v2 = morph.get(i);
      // Lerp to the target
      v2.lerp(v1, 0.1f);
      // Check how far we are from target
      // totalDistance += PVector.dist(v1, v2);
    }
  }

  public void morphDisplay(float x_, float y_) {
    float x= x_;
    float y=y_;


    translate(x, y);
    strokeWeight(3);
    // Draw a polygon that makes up all the vertices
    beginShape();
    //noFill();
    if (state==1) {
      fill(255);
      
      //stroke(255);
    } else if (state==0) {
      //stroke(newColor);
  fill(newColor);  
  }
    
    if (counter % 2 == 0 && button.circleOver==true) {
      float f = map(realtimer, 10,0,100,255);
        
        //stroke(f,f,f);
        fill(f,0,0);
    } else {
    //  noFill();
      
      //stroke(200);
    }


    //stroke(255);
    for (PVector v : morph) {
      vertex(v.x, v.y);
    }
    endShape(CLOSE);
  }

  public void morphTrans() {

    state = 1;
  }
}
long t, tPrev;
long tInterval = 1000;
int counter = 0;

public void drawTime() {
  if (millis() > tPrev + tInterval) {
    tPrev = millis();
    counter +=1;
    //println ("counter = " + counter);
  }
}
// GPIO numbers refer to different phyiscal pins on various boards
// On the Raspberry Pi GPIO 4 is physical pin 7 on the header

public void gpioSetup() {

  GPIO.pinMode(17, GPIO.INPUT);
  GPIO.pinMode(27, GPIO.INPUT);
  GPIO.pinMode(22, GPIO.INPUT);
  GPIO.pinMode(23, GPIO.INPUT);
}


boolean gpioUp= true;
boolean gpioDown=true;
boolean gpioRight=true;
boolean gpioLeft=true;

public void gpioFunction() {

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






public void joyStick(boolean up_, boolean down_, boolean left_, boolean right_) {

  boolean up=up_;
  boolean down=down_;
  boolean right=right_;
  boolean left=left_;



  if (up==true && y>20) {
   buttonState=true;
    
   y-=7.0f;
  } 

  if (down==true && y<height-20) {
    buttonState=true;
   y+=7.0f;
  } 
  if (left==true&& x>20) {
    buttonState=true;
   x-=7.0f;
  } 
  if (right ==true && x<width-20) {
    buttonState=true;

   x+=7.0f;
  }

  //if (up==true && right==true &&x<width-20 && y>20) {
  //  y-=7.0;
  //  x+=7.0;
  //}

  //if ( up==true && left==true && y>20 && x>20) {

  //  y-=7.0;
  //  x-=7.0;
  //}

  //if ( down==true && y<height-20 && right== true && x<width-20) {
  //  y+=7.0;
  //  x+=7.0;
  //}

  //if (down==true && y<height-20 && left==true&& x>20) {
  //  y+=7.0;
  //  x-=7.0;
  //}
}


boolean w=false;
boolean s=false;
boolean a=false;
boolean d=false;

public void keyPressed() {



  if (key=='w' ) {
    w=true;
  }
  if (key=='s') {
    s=true;
  } 
  if (key=='a') {
    a=true;
  } 
  if (key=='d') {
    d=true;
  } 
}


public void keyReleased(){

  if (key=='w' ) {
    w=false;
  }
  if (key=='s') {
    s=false;
  } 
 if (key=='a') {
    a=false;
  } 
   if (key=='d') {
    d=false;
  } 
  

}
int state=0;
public void mainGame() {
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
  mainPC.driveCircle(mainPCX, mainPCY,.2f);
  mainPC.drawCircle();
  mainPC.bounce();
  mainPC.moveCircle();




  
  //println(mainPC.location.x);

  //follower--------------------------------------
  for (int i =0; i<follower.length; i++) {
    follower[i].driveCircle(mainPC.location.x+random(-20*i,20*i), 
                            mainPC.location.y+random(-20*i,20*i),
                            random(.05f, .1f));
    follower[i].drawCircle();
    follower[i].moveCircle();
  }
  
  button.drawButton(mainPC.location.x, mainPC.location.y, x,y );
  
    pushMatrix();
  gamer.drawMorph();
  gamer.morphDisplay(x, y);
  popMatrix();
  
  
}



Minim       minim;
AudioOutput out;
Oscil       wave;


public void musicSetup(){


 minim = new Minim(this);
  
 // use the getLineOut method of the Minim object to get an AudioOutput object
 out = minim.getLineOut();
  
 // create a sine wave Oscil, set to 440 Hz, at 0.5 amplitude
 wave = new Oscil( 100, 0.5f, Waves.SINE );
 // patch the Oscil to the output
 wave.patch( out );
  
   
 player2 = minim.loadFile("endup.wav");

}

public void musicPlay(){


 // Map mouseY from 0.0 to 1.0 for amplitude
  
 // Map mouseX from -1.0 to 1.0 for left to right 
 //pos=map((mainPC.location.x-x), -500, 500, -1.0, .8);
 //sine.pan(pos);
float amp = map(abs(mainPC.location.y-y), -300, 150, .4f, 0.8f);          
 wave.setAmplitude( amp );
  
 float freq = map((abs(mainPC.location.x-x)*.5f+abs(mainPC.location.y-y)*0.5f), 0, 100, 50.0f, 300.0f );
 wave.setFrequency( freq );

println(abs(mainPC.location.x-x));


}

//import ddf.minim.*;

//Minim minim;

AudioPlayer player2;








public void playsong2(){


player2.loop();

}

int time=0;
int realtimer=10;
int lastTimer=3;
public void timer() {
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
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#030303", "--hide-stop", "Follower1_8Final" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
