





void joyStick(boolean up_, boolean down_, boolean left_, boolean right_) {

  boolean up=up_;
  boolean down=down_;
  boolean right=right_;
  boolean left=left_;



  if (up==true && y>20) {
   buttonState=true;
    
   y-=7.0;
  } 

  if (down==true && y<height-20) {
    buttonState=true;
   y+=7.0;
  } 
  if (left==true&& x>20) {
    buttonState=true;
   x-=7.0;
  } 
  if (right ==true && x<width-20) {
    buttonState=true;

   x+=7.0;
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

void keyPressed() {



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


void keyReleased(){

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