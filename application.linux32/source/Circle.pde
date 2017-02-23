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


  void driveCircle( float x, float y,float forceN_) {

    // direction.mult(.5);
    float angle = atan2 (y-location.y, x -location.x);

    PVector force = new PVector (cos (angle), sin (angle));

    force.mult(forceN);

    direction.add (force);
    direction.normalize();

    location.add(direction);

  forceN=forceN_;
   
  }


  void drawCircle() {

//strokeWeight(3);
    
    noStroke();
    //stroke(200);
    //noFill();
fill(200,240);
    ellipse(location.x, location.y, rad, rad);
  }




  void moveCircle() {

    PVector velocity=direction.copy();
    velocity.mult(speed);
    location.add(velocity);
  }




  void bounce() {

    if (location.x > width-rad*5||location.x<rad*5) {
      velocity.x*=-1;
    }

    if (location.y > height-rad*5|| location.y<rad*5) {
      velocity.y*=-1;
    }
  }
}