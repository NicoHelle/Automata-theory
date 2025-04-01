//CLASS: This class is used to filter equations or to change their representation. 
import java.util.ArrayList;
import java.util.List;

public class Filter {
	
//------------------------------------------------------------------------------------------------------------------
//GLOBAL VARIABLES:	
	
static int counter = 0; //counter for storing the total wordcount. 
static public List<String> filtered = new ArrayList<String>(); // filtered words are getting added on to the list. 

//------------------------------------------------------------------------------------------------------------------
//FUNCTIONS: FILTER

// takes an array and prints all equations which do contain the  input filterstring
static public String [] containedfilter (String[] array, String filterstring) {
	filtered = new ArrayList<String>();
	for (int i = 0; i < array.length;i++) {
		if (array[i]==null) break;
		if (array[i].contains(filterstring)) {
			filtered.add(array[i]);
			counter++;
		}		
	}
	Equation.totalWordcount = counter;
	
	return filtered.toArray(new String[0]);
	}

//takes an array and prints all equations which do not contain the  input filterstring
static public String [] notcontainedfilter (String[] array, String filterstring) {
	filtered = new ArrayList<String>();
	if (!filterstring.contains("b") && (!filterstring.contains("a"))) return array;
	for (int i = 0; i < array.length;i++) {
		if (array[i]==null) break;
		if (!array[i].contains(filterstring)) {
				filtered.add (array[i]);		
		}			
	}
	
	return filtered.toArray(new String[0]);
	}


// takes an array and two integer and prints all equations which do contain the input filterstring between those two integers.
static public String[] startingfilter (String[] wordarray, String filterstring, int startindex, int endindex) {
	startindex--;
	filtered = new ArrayList<String>();
	for (int i = 0; i < wordarray.length;i++) {
		if (wordarray[i]==null) break;
		if(wordarray[i].length() < endindex) continue;
		String Pos = wordarray[i].substring(startindex, endindex);
		if (Pos.contains(filterstring)) {
				filtered.add(wordarray[i]);
		}
		i++;			
	}
	return filtered.toArray(new String[0]);
	//System.out.println("There are " + counter/2 + " equations which do contain '" |TEST/DEBUG
	//+ filterstring + "' on starting position: " + startindex + " to endposition: " + endindex); |TEST/DEBUG
	}

static public String[] NotStartToEndFilter(String[] wordarray, String filterstring, int startindex, int endindex) {
	startindex--;
	filtered = new ArrayList<String>();
	for (int i = 0; i < wordarray.length;i++) {
		if (wordarray[i]==null) break;
		if(wordarray[i].length() < endindex) continue;
		String Pos = wordarray[i].substring(startindex, endindex);
		if (!Pos.contains(filterstring)) {
				filtered.add(wordarray[i]);
		}
		i++;			
	}
	return filtered.toArray(new String[0]);
	//System.out.println("There are " + counter/2 + " equations which do not contain '" |TEST/DEBUG
	//+ filterstring + "' on starting position: " + startindex + " to endposition: " + endindex); |TEST/DEBUG
	}




//------------------------------------------------------------------------------------------------------------------
//FUNCTIONS: CHANGE OF REPRESENTATION

// Takes an array of equations an changes the representation of the words in thoose equations. 
static public void rewrite (String[] array) {
	for (int i = 0; i < array.length && array[i+1]!=null;i=i+2) {
		if (array[i]==null) break;
		array[i] = array[i].replaceAll("bababababab", "5");
		array[i] = array[i].replaceAll("babababab", "4");
		array[i] = array[i].replaceAll("bababab", "3");
		array[i] = array[i].replaceAll("babab", "2");
		array[i] = array[i].replaceAll("bab", "1");
		array[i] = array[i].replaceAll("aaaaa", "D");
		array[i] = array[i].replaceAll("aaaa", "C");
		array[i] = array[i].replaceAll("aaa", "B");
		array[i] = array[i].replaceAll("aa", "A");
		array[i+1] = array[i+1].replaceAll("bababababab", "5");
		array[i+1] = array[i+1].replaceAll("babababab", "4");
		array[i+1] = array[i+1].replaceAll("bababab", "3");
		array[i+1] = array[i+1].replaceAll("babab", "2");
		array[i+1] = array[i+1].replaceAll("bab", "1");
		array[i+1] = array[i+1].replaceAll("aaaaa", "D");
		array[i+1] = array[i+1].replaceAll("aaaa", "C");
		array[i+1] = array[i+1].replaceAll("aaa", "B");
		array[i+1] = array[i+1].replaceAll("aa", "A");
	  //System.out.println(array[i] + " == " + array[i+1]); |TEST/DEBUG
	}}

// Takes a word and changes it's representation. 
static public String rewrite (String word) {
		if (word!=null) {
		word = word.replaceAll("bababababababab", "7");
		word = word.replaceAll("babababababab", "6");
		word = word.replaceAll("bababababab", "5");
		word = word.replaceAll("babababab", "4");
		word = word.replaceAll("bababab", "3");
		word = word.replaceAll("babab", "2");
		word = word.replaceAll("bab", "1");
	    //word = word.replaceAll("b", "0");
		word = word.replaceAll("aaaaaaaa", "G");
		word = word.replaceAll("aaaaaaa", "F");
		word = word.replaceAll("aaaaaa", "E");
		word = word.replaceAll("aaaaa", "D");
		word = word.replaceAll("aaaa", "C");
		word = word.replaceAll("aaa", "B");
		word = word.replaceAll("aa", "A");
	
		}
		return word;
}








}