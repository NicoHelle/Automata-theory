//----------------------------------------------------------------------------------------------------------------------
// CLASS: This class is used to remove Redundancy in equations which appear due to transitivity or concatenation.
//----------------------------------------------------------------------------------------------------------------------
public class Redundancy {

//----------------------------------------------------------------------------------------------------------------------	
//FUNCTIONS:

	
	// takes a 1D-Array and returns the length of the array without empty spaces.
	static public int getArrayLength (String [] wordarray) {
		int count = 0;
		for (int i = 0; i < wordarray.length;i++) {
			if (wordarray[i]!=null) count++;	
		}
		System.out.println(count);
		return count;
	}
	
	
	
	// takes an 2D-Array and prints out all it's content. 
		static public void print2D (String[][] wordarray) {
			for (int i = 0; i < wordarray.length;i++) {
				if (wordarray[i]==null) continue;
					for (int j = 0; j < wordarray[i].length;j++)	{ 
						if (wordarray[i][0]==null) return;
						System.out.print(wordarray[i][j] + " : ");
					}
			}
		}
		
		
	// takes an 2D-Array and prints out all it's content. 
		static public String[] printTextArea (String[][] wordarray) {
			Equation.equationnumber=0;
			int counter = 0; 
			String [] result = new String[Equation.wordcount+5]; 
			for (int i = 0; i < wordarray.length;i++) {
				if (wordarray[i]==null) continue;
				if (wordarray[i][0] == null) continue; 
				result[counter] = "\n"+wordarray[i][0] + " (" + Filter.rewrite(wordarray[i][0]) + ")";
				counter++;
				Equation.equationnumber++;
				for (int j = 1; j < wordarray[i].length;j++)	{ 
					if (wordarray[i][j]==null) continue;
					result[counter]=(" == " +wordarray[i][j] + " (" + Filter.rewrite(wordarray[i][j])+ ")");
					counter++;

					}
				}
					Equation.wordcount=counter;
					return result; 
		}	
		
		
	
	//takes an array of equations and removes all duplicate words from the equations. 
	static public String[] remove (String[] wordarray) {
		wordarray = removeDuplciates(wordarray);
		int counter = 0;
		String res [] = new String [wordarray.length];
		for (int i = 0; i < wordarray.length && wordarray[i]!=null;i++) {
			if (wordarray[i] == null) break;
			boolean cont = false;
			for (int j = 0; j < counter; j++) {
				if (wordarray[j] == null) continue;
				if (wordarray[i] == res[j]) {cont = true; break;}
		}
			if (cont == false) {
			res[counter] = wordarray[i];

			// System.out.println(res[counter]); |TEST/DEBUG
			counter++;}
		}

		System.out.println(counter);
		System.out.println(wordarray.length);

		return res;
	}
	
	
	
	//takes an array of equations and removes all duplicate words from the equations. 
		static public String[] removeDuplciates (String[] wordarray) {
			int counter = 0; 
			String res [] = new String [wordarray.length];
			for (int i = 0; i < wordarray.length; i=i+2) {
				if  (wordarray[i]==null) continue; 
				boolean cont = false; 
				for (int j = i+2; j < wordarray.length; j=j+2) {
					if (wordarray[j]== null) continue; 
					if (wordarray[i].substring(1).equals(wordarray[j]) && wordarray[i+1].substring(1).equals(wordarray[j+1])) {
						cont = true; break;
					}
					
				}
					
				if (!cont) {
					res[counter] = wordarray[i];
					res[counter+1] = wordarray[i+1];
				counter=counter+2;
				}
				}
			
			return res; 
		}
	
	
	
	
	
	//Removes equations which are concatenated versions of an already existing equation. 
	//Only applies if the to be deleted equation does not contain a word, which is not contained in any word of both equations. 
	static public String[][] removeConcat (String[][] array) {
		for (int i = 0; i < array.length;i++) {
			for (int j = i+1; j < array.length;j++)	{ 
				if (array[i][0]==null || array [j][0] == null) continue;
				boolean cond = true;
					for (int k = 0; k < array[i].length; k++) {
						if (array[i][k] == null || array[j][k] == null) continue;
						if (!array[i][k].contains(array[j][k])) {
							cond = false;
						}
					}
					if (cond) {
						//if (getArrayLength(array[i])<=getArrayLength(array[j])) {

							array[i]=null; break;
							}
						
					}	
			}
		
		return array;
	}	
	
	

	
	
	
	// takes an 2D-Array uses the transitivity of equations to reduce the size of the equation list
	// if w1 = w2, w1 = w3, then w2 = w3 is also true and the following output will be produced: w1 = w2 = w3.
	static public String[] removeTransitivity (String [] equationarray) {
		int equationcount = 0;
		int counter = 0;
		String result [] = new String [equationarray.length];
		for (int i = 0; i < equationarray.length;i++) {
			if (equationarray[i] == null) {continue;}
			result[counter] = "\n"+equationarray[i] + " (" + Filter.rewrite(equationarray[i]) + ")";
			//System.out.println(result[counter]); |TEST/DEBUG
			equationcount++;
			counter++;
			for (int j = i+1; j < equationarray.length; j++) {
				if (equationarray[j] == null) continue;
				if (Equation.compare2Words(equationarray[i],equationarray[j]) && equationarray[j]!=null) {
					result [counter] = (" == " +equationarray[j] + " (" + Filter.rewrite(equationarray[j])+ ")");

					//System.out.println(result[counter]); |TEST/DEBUG
					
					equationarray[j]=null;
					counter++;	
				}	
			}

			//System.out.println(""); |TEST/DEBUG
			//System.out.println("--------------------------------------------------------"); |TEST/DEBUG
		}
		Equation.equationnumber = equationcount;
		Equation.wordcount = counter;
		//System.out.println("There are " + counter + " unique words in " + equationcount +  " equations"); |TEST/DEBUG
		//System.out.println("--------------------------------------------------------"); |TEST/DEBUG
		
		return result;
	}
	
	
	
	
	
	
	// takes an 2D-Array uses the transitivity of equations to reduce the size of the equation list
		// if w1 = w2, w1 = w3, then w2 = w3 is also true and the following output will be produced: w1 = w2 = w3.
		static public String[] removeAllRedundancy (String [] equationarray) {
			
			String EqResult [][] = new String[equationarray.length][500];
			int equationcount = 0;
			int counter = 0;
			int counter2 = 1;
			String result [] = new String [equationarray.length];
			for (int i = 0; i < equationarray.length;i++) {
				if (equationarray[i] == null) {continue;}
				//System.out.println(result[counter]); |TEST/DEBUG
				EqResult[equationcount][0] = equationarray[i];
				equationcount++;
				counter++;
				for (int j = i+1; j < equationarray.length; j++) {
					if (equationarray[j] == null) continue;
					if (Equation.compare2Words(equationarray[i],equationarray[j]) && equationarray[j]!=null) {
						EqResult [equationcount-1][counter2] = equationarray[j];
						//System.out.println(result[counter]); |TEST/DEBUG
						counter2++;
						equationarray[j]=null;
						counter++;	
					}	
				}
				counter2 = 1;
				//System.out.println(""); |TEST/DEBUG
				//System.out.println("--------------------------------------------------------"); |TEST/DEBUG
			}
			Equation.equationnumber = equationcount;
			Equation.wordcount = counter;
			//System.out.println("There are " + counter + " unique words in " + equationcount +  " equations"); |TEST/DEBUG
			//System.out.println("--------------------------------------------------------"); |TEST/DEBUG
			result   = printTextArea(removeConcat(EqResult));
			return result;
		}
	
	
	
	
	
}
