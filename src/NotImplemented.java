import java.util.ArrayList;
import java.util.List;

// This class contains all functions which are either:
// 1: not implemented
// 2: used for testing or debugging
// 3: do not work the intended way

public class NotImplemented {

	//1: first version of RemoveConcat
	static public void printRedundancy (String[][] list) {
		for (int s = 0; s < list[s].length;s++) {
		 for (int k = 0; k < list.length;k++) {
			if (list[k][s]==null) continue;
			if (list[k][s].substring(1).contains(list[0][s])) {
				list[k]=null; 
				break;
				}
			}
		 }
		}
	
	//1: alternative version of RemoveTransitivity
	static public void removeRedundancy (String [] equationlist) {
		int inscounter = 0;
		String firstwords [] = new String [100];
		for (int i = 0; i < equationlist.length && equationlist[i]!=null; i=i+2) {
			nextword: 
				for (int g = 0; g < inscounter; g++) {
					if (firstwords[g] == equationlist[i]) {i=i+2; continue nextword;}
				}
				firstwords[inscounter] = equationlist[i];
				inscounter++;
				int counter = 1;
				//System.out.print(equationlist[i] + " has following equations: "); |TEST/DEBUG
				String resultarr [] = new String [50];
				resultarr[0] = equationlist[i];
				for (int j = i+1; j < equationlist.length && equationlist[j]!=null; j++) {
					boolean dont = false;
					for (int s = 0; s < counter; s++) {
						if (resultarr[s] == equationlist[j])
						dont = true;
				}
		
				if (Equation.compare2Words(equationlist[i],equationlist[j]) == true && dont == false) {
					resultarr[counter] = equationlist[j];
					//System.out.print(equationlist[j] + " ,"); |TEST/DEBUG
					counter++;
				}	
				}
				//filter.rewritesingle(resultarr); |TEST/DEBUG
		
			System.out.println("");
			System.out.println("--------------------------------------------------------");
		}
		System.out.println("--------------------------------------------------------");
	}
	
	//3: Used to filter the concatenated words before filtering transitivity. Does not work when filtering for transitivity afterwards
	static public String [] RemoveConcat (String[] wordlist) {
		String res [] = new String [wordlist.length];
		int counter = 0; 
		for (int i = 0; i < wordlist.length; i=i+2) {
			if (wordlist[i]==null) continue; 
			for (int j = i+2; j < wordlist.length; j=j+2) {
				if (wordlist[j]==null) continue; 
				if (wordlist[i]==wordlist[j]) {wordlist[i]=null;}
				if (wordlist[i+1]==wordlist[j+1]) {wordlist[i+1]=null;}
				if (wordlist[i] == null || wordlist[i+1] == null) break;
				if ((wordlist[i].substring(1).equals(wordlist[j]) && wordlist[i+1].substring(1).equals(wordlist[j+1])) 
					|| (wordlist[i].substring(0, wordlist[i].length()-1).equals(wordlist[j])) 
					&& (wordlist[i+1].substring(0, wordlist[i+1].length()-1).equals(wordlist[j+1]))) {
					wordlist [i] = null;
					wordlist [i+1] = null;
					break;
				}
					//if (wordlist[i].length()>=wordlist[j].length()) {wordlist[i]=null;}
				//else {wordlist[j]=null;}
			}
		}

			for (int k = 0; k < wordlist.length;k++) {
				if (wordlist[k]!= null) {res[counter]=wordlist[k]; counter++;}
			}

		
		return res;
	}
	
	//1: got replaced by the array version because of calculation time
	static public List<String> remove2 (List<String> wordlist) {
		int counter = 0;
		for (int i = 0; i < wordlist.size();i++) {
			for (int j = 0; j < counter; j++) {
				if (wordlist.get(i) == wordlist.get(j)) {
				wordlist.remove(i);
				break;
				}}
			counter++;
		}
		return wordlist; 
	}	
	
	//3: first version of removeTransitivity, does not work.
	static public void RemoveRedundancy3 (List<String> equationlist) {
		int equationcount = 0;
		int counter = 0;
		for (int i = 0; i < equationlist.size();i++) {
			System.out.print(equationlist.get(i) + " (" + Filter.rewrite(equationlist.get(i)) + ") ");
			equationcount++;
			counter++;
			for (int j = i+1; j < equationlist.size(); j++) {
				if (Equation.compare2Words(equationlist.get(i),equationlist.get(j)) == true) {
					System.out.print(" == " +equationlist.get(j) + " (" + Filter.rewrite(equationlist.get(j))+ ")");
					equationlist.remove(j);
					counter++;
					
				}
				
			}
			System.out.println("");
			System.out.println("--------------------------------------------------------");
		}
		System.out.println("There are " + counter + " unique words in " + equationcount +  " equations");
		System.out.println("--------------------------------------------------------");
	}
	
	//1: version for filtering without using the built in "contains" function. 
	static public void prefilter (String[] List, String Eingabe) {
		int counter = 0; 
		char filterchars [] =  new char [Eingabe.length()];
		for (int k = 0; k < Eingabe.length();k++) {
			filterchars[k] = Eingabe.charAt(k);
		}
			
		for (int i = 0; i < List.length;i++) {
			if (List[i]==null) break;
			boolean contains = false;
			String x = List[i];
			for (int j = 0; j < x.length()-1;j++) {
				for (int f = 0; f < Eingabe.length(); f++) {
					if (List[i].charAt(j) != Eingabe.charAt(f)) {contains = true; break;}
					else j++;
				}
				j = 0;
			}
				
				if (contains==false) {
					System.out.println(List[i] + " = " + List[i+1]);
					counter=counter+2;
			}
			i++;	
				
		}
		System.out.println("Es wurden " + counter/2 + " Gleichungen gefunden die kein " + 
		Eingabe + " hintereinander enthalten");
		}

	//1: version of a filter which was used to filter equations which contain bb. 
	static public void prefilter (String[] List, char first, char second) {
		int counter = 0; 
		for (int i = 0; i < List.length;i++) {
			if (List[i]==null) break;
			boolean contains = false;
			String x = List[i];
			for (int j = 0; j < x.length()-2;j++) {
			if (List[i].charAt(j) == first && List[i].charAt(j+1) == second) {
						contains = true;
						break;
				}
				
			}
				if (contains==false) {
					System.out.println(List[i] + " = " + List[i+1]);
					counter=counter+2;
				
			}
			i++;	
				
		}
		System.out.println("Es wurden " + counter/2 + " Gleichungen gefunden die kein " + 
		first + " und " + second + " hintereinander enthalten");
		}

	//1: Alternative version which uses a list as input.
	static public List<String> notcontainedfilter (List <String> List, String filterstring) {
		int counter = 0; 
		List<String> filtered = new ArrayList<String>();
		for (int i = 0; i < List.size();i++) {
			if (!List.get(i).contains(filterstring)) {
					filtered.add (List.get(i));
					//filtered[counter+1] = List[i+1];
					// System.out.println(filtered[counter] + " = " + filtered[counter+1]);
					counter++;			
			}			
		}
		//System.out.println("There are " + counter/2 + " equations which do not contain " + filterstring);
		Equation.totalWordcount = counter;
		String [] result = filtered.toArray(new String[0]);
		return filtered;
		}
	
	
}
