import ddf.minim.*;
import ddf.minim.ugens.*;

Minim       minim;
AudioOutput out;
Oscil       wave;


void musicSetup(){


 minim = new Minim(this);
  
 // use the getLineOut method of the Minim object to get an AudioOutput object
 out = minim.getLineOut();
  
 // create a sine wave Oscil, set to 440 Hz, at 0.5 amplitude
 wave = new Oscil( 100, 0.5f, Waves.SINE );
 // patch the Oscil to the output
 wave.patch( out );
  
   
 player2 = minim.loadFile("endup.wav");

}

void musicPlay(){


 // Map mouseY from 0.0 to 1.0 for amplitude
  
 // Map mouseX from -1.0 to 1.0 for left to right 
 //pos=map((mainPC.location.x-x), -500, 500, -1.0, .8);
 //sine.pan(pos);
float amp = map(abs(mainPC.location.y-y), -300, 150, .4, 0.8);          
 wave.setAmplitude( amp );
  
 float freq = map((abs(mainPC.location.x-x)*.5+abs(mainPC.location.y-y)*0.5), 0, 100, 50.0, 300.0 );
 wave.setFrequency( freq );

println(abs(mainPC.location.x-x));


}