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
  color newColor;


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

  void changeColor() {
    newColor=color(random(100, 150), random(50), random(50));
  }


  void drawMorph () {

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
      v2.lerp(v1, 0.1);
      // Check how far we are from target
      // totalDistance += PVector.dist(v1, v2);
    }
  }

  void morphDisplay(float x_, float y_) {
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

  void morphTrans() {

    state = 1;
  }
}