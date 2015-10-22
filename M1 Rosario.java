////Midterm - Ryan Anthony Rosario

/////Globals 

/////balls
float rasberryX, rasberryY, rasberryDX, rasberryDY;
float augustX, augustY, augustDX, augustDY;
float rasinX, rasinY, rasinDX, rasinDY;
////Strings
String title= "CST112 MIDTERM EXAM";
String news= "Click any ball to reset it to right half of table. (r resets all.) Press w to turn off wall, and p to turn pink. Press q to exit";
String author="Ryan Rosario";
////table
float left, right, top, bottom;
float middle;
boolean wall=true;

int tableRed=31, tableGreen=206, tableBlue=150;       //Green-Blue pool table
int score=0, m=0, k=0;


/////Setup - Table
void setup() {
  size(640, 480);
  left= 50;
  right= width-50;
  top= 100;
  bottom= height-50;
  middle= right - left/2;
  //
  reset();
}
  
 void reset() {
    // Random positions.
  rasberryX=  random( middle,right );   rasberryY=  random( top, bottom );
  augustX=  random( middle,right );   augustY=  random( top, bottom );
  rasinX=  random( middle,right );  rasinY=  random( top, bottom );
   // Random speeds
  rasberryDX=  random( 1,3 );   rasberryDY=  random( 1,3 );
  augustDX=  random( 1,3 );   augustDY=  random( 1,3 );
  rasinDX=  random( 1,3 );   rasinDY=  random( 1,3 );
  ///wall
   wall=true;
 ///color
   tableRed=31; tableGreen=206; tableBlue=150;
  ///count
  score=0;
  
}
 
  ////Next Frame
  void draw(){
     background( 250,250,200 );
     rectMode( CORNERS );
     table( left, top, right, bottom );
     bounce();
     collisions();
     show();
     messages();
  }
  
  ///HANDLERS: keys
  void keyPressed() {
    if (key == 'q') {exit(); }
    if( key == 'r') { reset();}
    if ( key == 'w') {wall=false;} ///turn off wall
    if (key == 'p') {tableRed=255; tableGreen=41; tableBlue=159;}  //change color to pink
}
  //Show - Table
 void table( float east, float north, float west, float south ) {
  fill( tableRed, tableGreen, tableBlue );    // pool table
  strokeWeight(20);
  stroke( 127, 0, 0 );      // Brown walls
  rect( east-20, north-20, west+20, south+20 );
 
  ///Wall
 if (wall) {
    float middle=  (east+west)/2;    
    stroke( 0, 127, 0 );
    line( middle,north+10, middle,south-10 );
  } else wall = false; 
   stroke(0);
   strokeWeight(1);
 }
 
//////Action
void bounce() {
  ///// Table borders
  rasberryX += rasberryDX;  if ( rasberryX<left || rasberryX>right ) rasberryDX *= -1;
  rasberryY += rasberryDY;  if ( rasberryY<top || rasberryY>bottom ) rasberryDY *=  -1;

  augustX += augustDX;  if ( augustX<left || augustX>right ) augustDX *= -1;
  augustY += augustDY;  if ( augustY<top || augustY>bottom ) augustDY *=  -1;


  rasinX +=rasinDX;  if ( rasinX<left || rasinX>right ) rasinDX *= -1;
  rasinY += rasinDY;  if ( rasinY<top || rasinY>bottom ) rasinDY *=  -1;
//////Wall - didnt finish
  //rasberryX += rasberryDX;  if ( rasberryX<left || rasberryX>right ) rasberryDX *= -1;
  //rasberryY += rasberryDY;  if ( rasberryY<top || rasberryY>bottom ) rasberryDY *=  -1;

 // augustX += augustDX;  if ( augustX<left || augustX>right ) augustDX *= -1;
  //augustY += augustDY;  if ( augustY<top || augustY>bottom ) augustDY *=  -1;


  //rasinX +=rasinDX;  if ( rasinX<left || rasinX>right ) rasinDX *= -1;
  //rasinY += rasinDY;  if ( rasinY<top || rasinY>bottom ) rasinDY *=  -1;


}

void collisions() {
  float tmp;
  // Swap velocities!
  if ( dist( rasberryX,rasberryY, augustX,augustY ) < 30 ) {
    tmp=augustDX;  augustDX=rasberryDX;  rasberryDX=tmp;
    tmp=augustDY;  augustDY=rasberryDY;  rasberryDY=tmp;
  }
  if ( dist( rasberryX,rasberryY, rasinX,rasinY ) < 30 ) {
    tmp=rasinDX; rasinDX=rasberryDX;  rasberryDX=tmp;
    tmp=rasinDY;  rasinDY=rasberryDY;  rasberryDY=tmp;
  }
  if ( dist( augustX,augustY, rasinX,rasinY ) < 30 ) {
    tmp=rasinDX;  rasinDX=augustDX;  augustDX=tmp;
    tmp=rasinDY;  rasinDY=augustDY;  augustDY=tmp;
  }
}
  
  ////Show - messages and balls
void show() {
  fill( #FC1717 );    ellipse( rasberryX, rasberryY, 30, 30 );
  fill( #F8FC17 );    ellipse( augustX, augustY, 30,30 );
  fill( #1720FC );    ellipse( rasinX, rasinY, 30, 30);
  ////numbers
  fill(0);             text("1", rasberryX, rasberryY);
  fill(0);             text("2", augustX, augustY);
  fill(0);             text("3", rasinX, rasinY);
}
void messages() {
  fill(0);
  text( title, width/3, 15 );
  text( news, width/25, 30 );
  text( author, 10, height-5 );
}



  
  
