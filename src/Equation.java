//------------------------------------------------------------------------------------------------------------------
//CLASS: This class provides the basic methods to calculate equations. 
//------------------------------------------------------------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;

public class Equation {
	
//------------------------------------------------------------------------------------------------------------------
// GLOBAL VARIABLES:
	
	static public int counter = 0; 		        // unspecific counter;
	static public int size = 0; 		 		//wordlength parameter for the switch function
	static public int totalWordcount = 0; 		//total wordcount
	static public int equationnumber=0; 		//total number of Equations
	static public int wordcount=0; 				//total bumer of Words
	static public int depth = 25; 				//starting depth of the State Machine
	static public boolean depthchange = false;   //custom depth for switch loop
	static public boolean An= true;         	//is state machine An used?
	static public boolean A1 = false;			//is state machine A1 used?
	static public boolean A2 = false;			//is state machine A2 used?
	static public boolean A3 = false;			//is state machine A3 used?
	static public int customIteration = 3;		//= 3 for An/A1 and = 2 for A2/A3
	static public boolean Endstate2Words = false;

	
	static public Coordinates [][] endingstates2 = new Coordinates[2][depth*3+10]; //for the compare2Words function
	static public Coordinates [][][] startandendingstates2;
	
	// Used to reset the global variables after one iteration of the switch loop
	static public void reset () {
		totalWordcount = 0; 
		equationnumber=0; 
		depth = 50; 
		Filter.counter = 0;
	}
	
//------------------------------------------------------------------------------------------------------------------
// FUNCTIONS: generate the words and calculate the equations. 
	
	// Takes an integer size and calculates all words consisting of 'a' and 'b' letters up to the maximimum wordlength (specified by size). 
	static public String[] GenerateWords (int size) {
		depth = size/2+2;
		int counter = 0;
		int size2 = size;
		int arraysize = (int)Math.pow(2, size);
		String words [] = new String [arraysize*2-2];
		for (int j = 0; j < size2; j++) {
			arraysize = (int)Math.pow(2, size);
			for (int i = arraysize-1; i>=0; i--) {
				String binaryword = Integer.toBinaryString(i);
				//System.out.print(i + ":::"); //|TEST/DEBUG
				//System.out.print(binaryword); // |TEST/DEBUG
				while (binaryword.length()<size) {
					binaryword = "0"+binaryword;
				}
				binaryword = binaryword.replaceAll("0", "a");
				binaryword = binaryword.replaceAll("1", "b");
				//System.out.println(binaryword + ":::"); |TEST/DEBUG
				System.out.println(counter+ ":" + binaryword);
				words[counter] = binaryword;
				counter++;
				//System.out.println(counter + binaryword); |TEST/DEBUG
			}
		size--; 
		}
		totalWordcount = words.length;
		return words;
	}
	
	
	// Takes two words and decides whether those 2 words are an equation or not. 
	static public boolean compare2Words (String word1, String word2) {
		String result [] = {word1, word2};
		totalWordcount = 2;
		if (word1.length() > word2.length()) {depth = word1.length()/2+5;}
		else {depth = word2.length()/2+5;}
		String [] compareresult = compare(calculateEndingStates(result));
		if (compareresult.length > 1) {	
		//System.out.println	("The words " + result[0] + " and " + result[1] + " are an equation"); |TEST/DEBUG
			return true; 
		}
		else {//System.out.println("The words " + result[0] + " and " + result[1] + " are NOT an equation"); |TEST/DEBUG
			   //System.out.println("--------------------------------------------------------"); |TEST/DEBUG
			return false;
		}
	}

	
	static public void Injektiv () {
		int wordcounter = 0; 
		int startingstates = 0;
		int endingstates = 0;
	
		ArrayList<Coordinates> alreadyvisited = new ArrayList<Coordinates>();
		Coordinates [][][] fullarray = new Coordinates [200][2000][2000];
		Coordinates [] codearray = new Coordinates [200000];
		calculateEndingStates(GenerateWords(2));
		for (int i = 0; i < startandendingstates2.length; i++) {
			System.out.println("");
			System.out.println("---------------------------------------");
			System.out.println(startandendingstates2[i][0][0].getword());
			
			for (int j = 0; j < startandendingstates2[i].length; j++) {
				if (startandendingstates2[i][j][j] != null &&!alreadyvisited.contains(startandendingstates2[i][j][j])) {
			
				// codearray[wordcounter++] = startandendingstates2[i][j][j].getstartCoordinates();
				alreadyvisited.add(startandendingstates2[i][j][j]);
				System.out.println("");
				//System.out.println("Endkoordinaten: " + startandendingstates2[i][j][j].getCoordinates().getRow() + ":" + startandendingstates2[i][j][j].getCoordinates().getColumn());
				
				//System.out.print(startandendingstates2[i][j][j].getstartCoordinates().getRow() + ":" + startandendingstates2[i][j][j].getstartCoordinates().getColumn()+ " ### ");
				fullarray[wordcounter][endingstates][startingstates] = startandendingstates2[i][j][j];
				System.out.println("Endkoordinaten: " + fullarray[wordcounter][endingstates][startingstates].getCoordinates().getRow() + ":" + fullarray[wordcounter][endingstates][startingstates].getCoordinates().getColumn());
				System.out.print("Startkoordinaten: "+ fullarray[wordcounter][endingstates][startingstates].getstartCoordinates().getRow() + ":" + startandendingstates2[i][j][j].getstartCoordinates().getColumn()+ " ### ");
				startingstates++;
				}
				for (int k = j+1; k < startandendingstates2[i].length; k++) {

				if (startandendingstates2[i][k][k] != null) {
					//System.out.println("test");
				
					
				if (startandendingstates2[i][j][j].getCoordinates().getRow() == startandendingstates2[i][k][k].getCoordinates().getRow() && startandendingstates2[i][j][j].getCoordinates().getColumn() == startandendingstates2[i][k][k].getCoordinates().getColumn() && !alreadyvisited.contains(startandendingstates2[i][k][k])) {	
					//System.out.println(startandendingstates2[i][j][j].getword());
					//System.out.println(startandendingstates2[i][j][j].getCoordinates().getRow() + ":" + startandendingstates2[i][j][j].getCoordinates().getColumn());
					//System.out.println(startandendingstates2[i][j][j].getCoordinates().getRow() +""+ startandendingstates2[i][k][k].getCoordinates().getRow() +""+ startandendingstates2[i][j][j].getCoordinates().getColumn() +""+ startandendingstates2[i][k][k].getCoordinates().getColumn());				
					//System.out.println(counter + " ----------------------------------------------------");
					//System.out.println("1st state: " + " Word: " + startandendingstates2[i][j][j].getword() + " Startingstates: " + startandendingstates2[i][j][j].getstartCoordinates().getRow() + ":" +  startandendingstates2[i][j][j].getstartCoordinates().getColumn() + " Endingstates: " +  startandendingstates2[i][j][j].getCoordinates().getRow() + ":" + startandendingstates2[i][j][j].getCoordinates().getColumn());
					//System.out.println("2nd state: " + " Word: " + startandendingstates2[i][k][k].getword() + " Startingstates: " + startandendingstates2[i][k][k].getstartCoordinates().getRow() + ":" +  startandendingstates2[i][k][k].getstartCoordinates().getColumn() + " Endingstates: " +  startandendingstates2[i][j][j].getCoordinates().getRow() + ":" + startandendingstates2[i][k][k].getCoordinates().getColumn());
					//System.out.println(startandendingstates2[i][j][j].getword());
					fullarray[wordcounter][endingstates][startingstates] = startandendingstates2[i][k][k];
					System.out.print(fullarray[wordcounter][endingstates][startingstates].getstartCoordinates().getRow() + ":" + fullarray[wordcounter][endingstates][startingstates].getstartCoordinates().getColumn() + " ### ");
					startingstates++;
					// codearray[wordcounter++] = startandendingstates2[i][k][k].getstartCoordinates();
					alreadyvisited.add(startandendingstates2[i][k][k]);
					//System.out.print(startandendingstates2[i][k][k].getstartCoordinates().getRow() + ":" + startandendingstates2[i][k][k].getstartCoordinates().getColumn() + " ### ");
					//if (startandendingstates2[i][j][k].getRow() == 1 && startandendingstates2[i][j][k].getColumn() == 1) {
			    	 // System.out.println(startandendingstates2[i][j][k]);
			      //}
				}
				}
			}
			
			}
			endingstates++;
		}
			
			}
	
	
	
	/**static public void Injektiv () {
		Coordinates Arr [][] = calculateEndingStates(GenerateWords(5));
		for (int i = 0; i < Arr.length;i++) {
			boolean t = false;
			for (int j = 0; j < depth*3-2;j++) {
				for (int k = j+1; k < depth*3-2;k++) {
					 if (Arr[i][j].getCoordinates().getRow() == Arr[i][k].getCoordinates().getRow() && Arr[i][j].getCoordinates().getColumn() == Arr[i][k].getCoordinates().getColumn()) {
						System.out.println(Arr[i][0].getword()+ " : " + Arr[i][j].getCoordinates().getRow() + " == " + Arr[i][k].getCoordinates().getRow() + " und " + Arr[i][j].getCoordinates().getColumn() + " =? " + Arr[i][k].getCoordinates().getColumn());
						t = true; 
				
					 }	
			
		}
					
			}
			 if (t = false) System.out.println(Arr[i][0].getword()+ " ist injektiv");
		}}
		/**		
	
	
	
	
	
	
	
	/*run time:ca. 2^n * n^2 * 6
	Takes an array and calculates every ending state for a defined number of starting states (specified through "depth")  
	of the state machine. The function determines the starting states and 
	calculates the next state for the current character of the word for every starting state and for every word until there are
	no more characters left to read. Then the ending state (together with the word) will be stored in a two dimensional array. 
	*/
	static public Coordinates[][] calculateEndingStates (String[] wordarray) {
		Coordinates [][]startingstates = new Coordinates [wordarray.length][depth*3+5];
		Coordinates [][][]startandendingstates = new Coordinates[wordarray.length][depth*3+5][depth*3+5];
		Coordinates [][] endingstates = new Coordinates[wordarray.length][depth*3+5]; 
		// int  = 0; |TEST/DEBUG
		for (int wordcount = 0; wordcount < wordarray.length;wordcount++) {
			String currentword = wordarray[wordcount];
			if (currentword == null) break;
				int x_coordinate = 0;
				int y_coordinate = 0;	
				int counter = 0;
			for (int j = 0; j < depth; j++) {
					//++; |TEST/DEBUG
					y_coordinate = j;
				for (int icount = 0; icount < customIteration; icount++) {
					// ++; |TEST/DEBUG
					if (j == 0 && icount > 0) break;
						x_coordinate = icount;
						y_coordinate = j;
						startingstates[wordcount][counter] =new Coordinates(currentword,  new Coordinates(x_coordinate, y_coordinate));
						System.out.print(currentword + " Startkoordinaten: " + icount + ":" + j + " Endingstates: "); 
						
							for (int f = 0; f < currentword.length(); f++) {
								//t++; |TEST/DEBUG
								char c = currentword.charAt(f);
			
								if (x_coordinate == 0 && c=='a') {y_coordinate++; continue;}
								// if (y_coordinate >= 0 && x_coordinate==0 && c =='a') {y_coordinate++; continue;}
								if (y_coordinate > 0 && x_coordinate==0 && c =='b') {x_coordinate++; continue;}
								if ((A2 == true || A3 == true) && y_coordinate == 1 && x_coordinate == 1 && c =='b') {y_coordinate=0; x_coordinate=0; continue;}
								if ((A2 == true || A3 == true) && y_coordinate > 1 && x_coordinate == 1 && c =='b') {y_coordinate--; continue;}
								if ((An == true || A1 == true) && y_coordinate > 0 && x_coordinate==1 && c =='a') {x_coordinate++; continue;}
								if ((An == true || A1 == true) && y_coordinate > 0 && x_coordinate==1 && c == 'b') {x_coordinate=-1; y_coordinate=-1;break;}
								if ((An == true || A1 == true) && y_coordinate == 1 && x_coordinate==2 && c == 'b') {x_coordinate=0; y_coordinate=0; continue;}
								if ((An == true || A1 == true) && y_coordinate > 0 && x_coordinate==2 && c == 'b') {x_coordinate--; y_coordinate--; continue;}
								if ((A1 == true || A3 == true) && y_coordinate == 0 && x_coordinate == 0 && c =='b') {x_coordinate=-1; y_coordinate=-1; break;}
								
							 
							}
							
							startandendingstates[wordcount][counter][counter] = new Coordinates (currentword, new Coordinates (icount,j), new Coordinates (x_coordinate,y_coordinate));
							endingstates[wordcount][counter] = new Coordinates (currentword, new Coordinates (x_coordinate,y_coordinate));
							 System.out.println(startandendingstates[wordcount][counter][counter].getCoordinates().getRow() + ":" 
									 
						 + startandendingstates[wordcount][counter][counter].getCoordinates().getColumn()); 
									
							
							counter++;
							//System.out.println("Zielkoordinaten: " + EndCords[s].getRow() + ":" + EndCords[s].getColumn()); |TEST/DEBUG
							
						}
			
					
		}}
		// System.out.println(); |TEST/DEBUG
		if (Endstate2Words == true) endingstates2 = endingstates ;
		startandendingstates2 = startandendingstates; 
		return endingstates;
		}
	

	/*run time:ca. 2 * 2^(2n) * 3n
	 * Takes the ending states from the calculateEndingStates function and compares them to each other. If the ending states of 
	 * two words match then those two words are an equation and will be stored in the result array. 
	*/
	static public String[] compare (Coordinates[][] endingstates) {
		int counter = 0;
		List<String> wordarray = new ArrayList<String>();
		for (int i = 0; i<endingstates.length;i++) {
			//runtime++; |TEST/DEBUG
				for (int j = i+1; j<endingstates.length;j++) {
					//runtime++; |TEST/DEBUG
					boolean isequal = true;
					for (int k = 1; k<depth*customIteration-2;k++) {
						//runtime++; |TEST/DEBUG
						if (endingstates[i][k].getCoordinates().getRow() !=endingstates[j][k].getCoordinates().getRow() ||
							endingstates[i][k].getCoordinates().getColumn()!=endingstates[j][k].getCoordinates().getColumn()) 
								isequal = false;	
					}
					if (isequal == true) {
						wordarray.add (endingstates[i][0].getword());
						wordarray.add (endingstates[j][0].getword());
						// System.out.println ("Meine Liste: " + result[counter] + " und " + result[counter+1]); |TEST/DEBUG
						counter = counter + 2;
						}
					}
			}
		// System.out.println(runtime);|TEST/DEBUG
		equationnumber = counter;
		String[] array = wordarray.toArray(new String[0]);
		return array;
	}
	
	
	// basic function to output Equations from an array
	static public void printEquations (String[] array) {
	 for (int i = 0; i < array.length && array[i]!=null;i=i+2) {
		 System.out.println(array[i] +" == " + array[i+1]);
	 }}
	
	
	
	
	}

	
