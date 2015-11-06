////Midterm - Ryan Anthony Rosario

/////Globals 

/////balls
float rasberryX, rasberryY, rasberryDX, rasberryDY; ///red
float augustX, augustY, augustDX, augustDY;  ///yellow
float rasinX, rasinY, rasinDX, rasinDY; ////blue
////Rodent
float rodentX, rodentY, rodentDX;
////Strings
String title= "CST112 MIDTERM EXAM";
String news= "Click any ball to reset it to right half of table. (r resets all.) Press w to turn off wall, and p to turn pink. Press q to exit";
String news2= "Press the 1,2, and 3 key to reset ball with corresponding number";
String news3= "Press m for a little furry suprise"; 
String author="Ryan Rosario";
////table
float left, right, top, bottom;
float middle;
boolean wall=true;
////color
int tableRed=31, tableGreen=206, tableBlue=150;       //Green-Blue pool table
////count
int score=0;


/////Setup - Table
void setup() {
  size(700, 500);
  left= 50;
  right= width-50;
  top= 100;
  bottom= height-50;
  middle= right - left/2;
  //
  reset();
}
  
 void reset() {
 /// Random ball positions.
    ///red
  rasberryX=  random( middle,right );   rasberryY=  random( top, bottom );
   ///yellow
  augustX=  random( middle,right );   augustY=  random( top, bottom );
   ///blue
  rasinX=  random( middle,right );  rasinY=  random( top, bottom );
 /// Random ball speeds
   ///red
  rasberryDX=  random( 1,3 );   rasberryDY=  random( 1,3 );
  ///yellow
  augustDX=  random( 1,3 );   augustDY=  random( 1,3 );
  ///blue
  rasinDX=  random( 1,3 );   rasinDY=  random( 1,3 );
 
///Rodent postion
  rodentX= width-760;  
  rodentY= height-35;
  ///Rodent speed
  rodentDX=0;  

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
     balls();
     rodent();
     messages();

}
  
  ///HANDLERS: keys
  void keyPressed() {
    if (key == 'q') {exit(); }
    if( key == 'r') { reset();}
    if ( key == 'w') {wall=false;} ///turn off wall
    if (key == 'p') {tableRed=255; tableGreen=41; tableBlue=159;} ///fill table pink
    if (key == 'm') {rodentDX=7;} //// release Rodent
    
    
    
  //change color to pink
      //reset position of ball with corresponding number
    if(key == '1') { rasberryX=  random( middle,right );   rasberryY=  random( top, bottom );}
    if(key == '2') {  augustX=  random( middle,right );   augustY=  random( top, bottom );}
    if(key == '3') {  rasinX=  random( middle,right );  rasinY=  random( top, bottom );}
}
  ///Clicks
  void mousePressed() {
    if (dist( mouseX, mouseY, rasberryX, rasberryY)<30) { 
      rasberryX=  random( middle,right );   rasberryY=  random( top, bottom );}   ////reset red ball
   
   if (dist( mouseX, mouseY, augustX, augustY)<30) {
       augustX=  random( middle,right );   augustY=  random( top, bottom );}     //// reset yellow ball

  if (dist( mouseX, mouseY, rasinX, rasinY)<30) {
      rasinX=  random( middle,right );   rasinY=  random( top, bottom );}       //// reset blue ball
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
  ////rodent move across screen
  rodentX += rodentDX;
  /////bounce off of wall and borders
  if (wall) {
    ///red
  rasberryX += rasberryDX;  if ( rasberryX<width/2 || rasberryX>right ) rasberryDX *= -1;
  rasberryY += rasberryDY;  if ( rasberryY<top || rasberryY>bottom ) rasberryDY *=  -1;
    ///yellow
  augustX += augustDX;  if ( augustX<width/2 || augustX>right ) augustDX *= -1;
  augustY += augustDY;  if ( augustY<top || augustY>bottom ) augustDY *=  -1;

    ///blue
  rasinX +=rasinDX;  if ( rasinX<width/2 || rasinX>right ) rasinDX *= -1;
  rasinY += rasinDY;  if ( rasinY<top || rasinY>bottom ) rasinDY *=  -1;
  }
  else {
  ///// bounce off of Table borders
    ///red
  rasberryX += rasberryDX;  if ( rasberryX<left || rasberryX>right ) rasberryDX *= -1;
  rasberryY += rasberryDY;  if ( rasberryY<top || rasberryY>bottom ) rasberryDY *=  -1;
   ///yellow
  augustX += augustDX;  if ( augustX<left || augustX>right ) augustDX *= -1;
  augustY += augustDY;  if ( augustY<top || augustY>bottom ) augustDY *=  -1;
  ///blue
  rasinX +=rasinDX;  if ( rasinX<left || rasinX>right ) rasinDX *= -1;
  rasinY += rasinDY;  if ( rasinY<top || rasinY>bottom ) rasinDY *=  -1;
  }
}


void collisions() {
  float tmp;
  // Swap velocities!
    ///between red and yellow
  if ( dist( rasberryX,rasberryY, augustX,augustY ) < 30 ) {
    tmp=augustDX;  augustDX=rasberryDX;  rasberryDX=tmp;
    tmp=augustDY;  augustDY=rasberryDY;  rasberryDY=tmp;
    score=score + 1; ///add a point when they touch
}
    ///between red and blue
  if ( dist( rasberryX,rasberryY, rasinX,rasinY ) < 30 ) {
    tmp=rasinDX; rasinDX=rasberryDX;  rasberryDX=tmp;
    tmp=rasinDY;  rasinDY=rasberryDY;  rasberryDY=tmp;
    score=score + 1;///add a point when they touch
}
    ////between yellow and blue
  if ( dist( augustX,augustY, rasinX,rasinY ) < 30 ) {
    tmp=rasinDX;  rasinDX=augustDX;  augustDX=tmp;
    tmp=rasinDY;  rasinDY=augustDY;  augustDY=tmp;
    score=score + 1;///add a point when they touch
  }
}
 



  
  ////Show - balls, rodent, and messages
void balls() {
  fill( #FC1717 );    ellipse( rasberryX, rasberryY, 30, 30 ); ///red ball
  fill( #F8FC17 );    ellipse( augustX, augustY, 30,30 ); ////yellow ball
  fill( #1720FC );    ellipse( rasinX, rasinY, 30, 30);/////blue ball
  ////numbers
  fill(0);                           text("1", rasberryX, rasberryY); ///give red ball #1 
  fill(0);                           text("2", augustX, augustY);//// give yellow ball #2
  fill(0);                           text("3", rasinX, rasinY);//// give blue ball #3
}
void rodent() {
 fill(#AA5D29);                     ellipse(rodentX, rodentY, 60, 30);//// Rodent body
 fill(#FF55F7);                     ellipse(rodentX+20, rodentY-15, 10,20);//// Rodent left ear
 fill(#FF55F7);                     ellipse(rodentX+40, rodentY-15, 10,20);//// Rodent right ear
 fill( #AA5D29);                    ellipse(rodentX+30, rodentY-10, 40,20); ///// Rodent head
 fill(#FF55F7);                     ellipse(rodentX+50, rodentY-10, 5,5);   ///// Rodent nose
 fill(0);                           ellipse(rodentX+35, rodentY-15, 5,5);  ///// Rodent eye
 stroke(#FF55F7); strokeWeight(2);  line(rodentX-30, rodentY, rodentX-50, rodentY+30); ////Rodent tail
 stroke(0);///color Rodent legs black
//////Rodent animation: leg movement
if(frameCount % 30 > 15){ 
///Rodent legs first position
  line( rodentX-20, rodentY+10, rodentX-30, rodentY+20);
  line(rodentX+20, rodentY+10, rodentX+30, rodentY+20);}
///Rodent leg second position
  else{line( rodentX-10, rodentY+10, rodentX-10, rodentY+20);} 
}




void messages() {
  fill(#1444A2); text( title, width/2.6, 15 ); //// fill tile with blue
  fill(#08982A); text( news, width/20, 30 ); /// fill 1st row of instruc. with green
  fill(#F70A0A); text( news2, width/4, 42);//// fill 2nd row of instruc. with red
  fill(#F7610A); text( news3, width/2.7, 54);//// fill 2rd row of instruc. with orange 
  fill(0);       text( author, 10, height-5 ); //// fill author name with black
  fill(0);       text(score, 590, height-5);  ////fill count with black
  
}



  
  


