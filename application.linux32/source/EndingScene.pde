class Ending{
PVector EndLocation;

int rad;
 float x;
  float y;
  
  
  color Ccolor;
  
Ending(float _x, float _y, int _rad){

 x= _x;
 y= _y;
  EndLocation = new PVector(x,y);
  rad= _rad;
  


}


  void drawCircle(color _Ccolor) {

//strokeWeight(3);
    Ccolor=_Ccolor;
    noStroke();
    //stroke(200);
    //noFill();
fill(Ccolor,240);
    ellipse(x, y, rad, rad);
  }

}