//CLASS: This class is used to store the current word and the x,y coordinates of all ending states.
public class Coordinates {
	
//--------------------------------------------------------------------------------------------------
//INSTANCE VARIABLES: 
	
String word; // the word 
Coordinates endingstate; 
Coordinates startingstate;// the ending coordinates
int x_coordinate; //x coordinate
int y_coordinate; // y coordinate

//--------------------------------------------------------------------------------------------------
//CONSTRUCTOR:

public Coordinates (int x_coordinate, int y_coordinate) {
	this.x_coordinate = x_coordinate;
	this.y_coordinate = y_coordinate;
}

public Coordinates (String word, Coordinates end) {
	this.word = word;
	this.endingstate = end;
}

public Coordinates (String word, Coordinates start, Coordinates end) {
	this.word = word;
	this.startingstate = start;
	this.endingstate = end;
	
}
//--------------------------------------------------------------------------------------------------
//GET/SET FUNCTIONS:

public void setColumn(int y_coordinate){
    this.y_coordinate = y_coordinate;
}

public void setRow(int x_coordinate){
    this.x_coordinate = x_coordinate;
}

public int getRow(){
    return x_coordinate;
}

public int getColumn(){
    return y_coordinate;
}

public void setword(String word){
    this.word = word;
}

public String getword(){
    return word;
}

public Coordinates getCoordinates(){
    return endingstate;
}

public Coordinates getstartCoordinates(){
    return startingstate;
}

}