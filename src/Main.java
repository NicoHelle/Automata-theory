
import java.util.Scanner;
//CLASS: Main method which starts the user interface.
public class Main {

//------------------------------------------------------------------------------------------------------------------
//FUNCTIONS: switch function to choose calculation options -> NOT FINISHED (got replaced by GUI).
	
	public static void mainswitch () {
		boolean quit = false;
		do {
		System.out.println("Choose the Function");
		System.out.println("--------------------------------------------------------");
		System.out.println("1: Calculate all equations up to Equation.size s and for state machine detph z");
		System.out.println("2: Calculate the number of equations");
		System.out.println("3: Filter equations ");
		System.out.println("4: Change the display of the equations");
		System.out.println("5: Check if two Words are an equation");
		System.out.println("6: Remove Redundancy from a filtered equation list");
		System.out.println("7: Change Options");
		System.out.println("8: Display State Machine");
		System.out.println("9: Quit");
		Scanner eingabewert = new Scanner(System.in);
		int i = eingabewert.nextInt();

	    switch (i) {
	            case 1: 
	            		System.out.println("--------------------------------------------------------");
	            		System.out.println("Choose your s");
	            		int s = eingabewert.nextInt();
	            		Equation.size = s;
	            		System.out.println("--------------------------------------------------------");
	            		String[] x = Equation.GenerateWords(s);
	            		Equation.printEquations(Equation.compare(Equation.calculateEndingStates(x)));
	                    break;
	            case 2:  System.out.println("--------------------------------------------------------");
	            		 System.out.println("Choose the Word length");
	            		 int wordlength = eingabewert.nextInt();
	            		 System.out.println("--------------------------------------------------------");
	             		Equation.compare(Equation.calculateEndingStates(Equation.GenerateWords(wordlength)));
	             		 System.out.println("There are " + Equation.equationnumber/2 + " equations for the wordlength " + wordlength);
	             		 System.out.println("--------------------------------------------------------");
	                     break;
	            case 3:
	            	System.out.println("--------------------------------------------------------");
	            	System.out.println("1: Filter Equations that  dont contain a certain substring ");
	            	System.out.println("2: Filter Equations that contain a certain substring ");
	            	System.out.println("3: Filter Equations that contain a substring in a certain position ");
	            	System.out.println("--------------------------------------------------------");
	            		int outer = eingabewert.nextInt();         
	            		switch (outer) {         
	            			case 1:  
	            					System.out.println("--------------------------------------------------------");
	            					System.out.println("Choose the Word length");
	            					System.out.print("input: ");
	            					int s4 = eingabewert.nextInt();
	            					System.out.println("--------------------------------------------------------");
	            					System.out.println("Choose the filter string");
	            					String filterstring = eingabewert.next();
	            					String[] x4 = Equation.GenerateWords(s4);
	            					String [] zwischen = (Equation.compare(Equation.calculateEndingStates(Filter.notcontainedfilter(x4, filterstring))));
	            					Equation.printEquations(zwischen);
	            					System.out.println("There are " + Equation.equationnumber/2 + " Equations which do not contain " + filterstring);
	            					
	            					break;
	            			case 2: System.out.println("--------------------------------------------------------");
			 	 		 	 		System.out.println("Choose the Word length");
			 	 		 	 		System.out.print("input: ");
			 	 		 	 		int s5 = eingabewert.nextInt();
			 	 		 	 		System.out.println("--------------------------------------------------------");
			 	 		 	 		System.out.println("Choose the filter string");
			 	 		 	 		String  filterstring2 = eingabewert.next();
			 	 		 	 		String[] x5 = Equation.GenerateWords(s5);
			 	 		 	 		String[] zwischen2 = (Equation.compare(Equation.calculateEndingStates(Filter.containedfilter(x5, filterstring2))));
			 	 		 	 		System.out.println("--------------------------------------------------------");
			 	 		 	 		System.out.println("There are " + Equation.equationnumber/2 + " Equations which do contain " + filterstring2);
			 	 		 	 		Equation.printEquations(zwischen2);
			 	 		 	 		break;
	            			case 3: System.out.println("--------------------------------------------------------");
	 	 		 		 	 		System.out.println("Choose the Word length");
	 	 		 		 	 		int s6 = eingabewert.nextInt();
	 	 		 		 	 		Equation.size = s6;
	 	 		 		 	 		System.out.println("--------------------------------------------------------");
	 	 		 		 	 		String[] x6 = Equation.GenerateWords(s6);
	 	 		 		 	 		String [] ListofEquations4 =Equation.compare(Equation.calculateEndingStates(x6));
	 	 		 		 	 		System.out.println("Choose the filter string");
	 	 		 		 	 		System.out.println("--------------------------------------------------------");
	 	 		 		 	 		String filterword = eingabewert.next();
	 	 		 		 	 		System.out.println("Choose the starting index");
	 	 		 		 	 		System.out.println("--------------------------------------------------------");
	 	 		 		 	 		int startindex = eingabewert.nextInt();
	 	 		 		 	 		System.out.println("Choose the ending index");
	 	 		 		 	 		System.out.println("--------------------------------------------------------");
	 	 		 		 	 		int endindex = eingabewert.nextInt();
	 	 		 		 	 		Filter.startingfilter(ListofEquations4, filterword, startindex, endindex);
	 	 		 		 	 		break;
	            } 
	             break;
	             case 4:  
	            	 	 System.out.println("--------------------------------------------------------");
				 	 	 System.out.println("Choose the Word length");
	 			         int filterinput = eingabewert.nextInt();
	 			         Equation.size = filterinput;
	 			         System.out.println("--------------------------------------------------------");
	 			         System.out.println("Choose the filter string");
	 			         String[] filterwordarray = Equation.GenerateWords(filterinput);
	 			         String [] filteredwordarray = Filter.notcontainedfilter(filterwordarray, eingabewert.next());
	 			         String [] result =Equation.compare(Equation.calculateEndingStates(filteredwordarray));
	 			         Filter.rewrite(result);
	 			         System.out.println("--------------------------------------------------------");
	 			         System.out.println("Explanation: ");
	 			         System.out.println("1 = bab, 2 = babab, 3 = babab etc.");
	 			         System.out.println("A = aa, B = aaa, C = aaaa etc.");
	 			         System.out.println("--------------------------------------------------------");
	 			         break;
	             case 5: System.out.println("--------------------------------------------------------");
		 	 	 		 System.out.println("Choose the first word");
		 	 	 		 String firstword = eingabewert.next();
		 	 	 		 System.out.println("--------------------------------------------------------");
		 	 	 		 System.out.println("Choose the second word");
		 	 	 		 String secondword = eingabewert.next();
		 	 	 		 Equation.compare2Words(firstword, secondword);
		 	 	 		 
		 	 	 		 break;
	             case 6: System.out.println("--------------------------------------------------------");
		 	 	  	     System.out.println("Choose the Word length");
		 	 	  	     int s10 = eingabewert.nextInt();
		 	 	  	     Equation.size = s10;
		 	 	  	     System.out.println("--------------------------------------------------------");
		 	 	  	     System.out.println("Choose the filter string");
		 	 	  	     String[] x10 = Equation.GenerateWords(s10);
		 	 	  	     String [] zwischen2 = Filter.notcontainedfilter(x10, eingabewert.next());
		 	 	  	    // Equation.printEquations(Redundancy.removeConcat(Redundancy.remove(Equation.compare(Equation.calculateEndingStates(zwischen2))));
		 	 	  	     Equation.reset();
		 	 	  	     break; 
		 	 	  	     
	             case 7: 
	            	 	 System.out.println("Choose the Function");
	            	 	 System.out.println("--------------------------------------------------------");
	            	 	 System.out.println("1: Change the search Equation.depth");
	            	 	 System.out.println("2: Change the State Machine (n>0)");
	            	 
	            	 	 int j = eingabewert.nextInt();
	            	 	 switch (j)  {
	            		 		
	            	 	 	case 1: System.out.println("--------------------------------------------------------");
			 		 	 		 	System.out.println("Note: should be greater than half the wordlength, ohterwise some equations will be wrong");
			 		 	 		 	Equation.depth = eingabewert.nextInt();
			 		 	 		 	Equation.depthchange = true;
			 		 	 		 	continue;
	            	 	 	case 2: System.out.println("--------------------------------------------------------");
	             		 		 	System.out.println("1: Normal, 2: Diffrent State Machine Version (n>0)");
	             		 		 	int StateMachineVersion = eingabewert.nextInt();
	             		 		 	if (StateMachineVersion == 1) Equation.A1=false;
	             		 		 	if (StateMachineVersion == 2) Equation.A1=true;
	             		 		 	continue;}
	             case 8: new DisplayStateMachine (); break;
	             case 9: quit = true; eingabewert.close(); break; 
	             default: System.out.println("--------------------------------------------------------");
	            	 	  System.out.println(i + " is no valid input. Only numbers between 1 and 9 are allowed!");
	            	 	  System.out.println("--------------------------------------------------------");
	            	 	  break;
	     		
	             		 
	             		 
	        }} while (!quit);     
	   		 	
		System.exit(0);
		}
// ------------------------------------------------------------------------------------------------------------------------------------------------------------	
// MAIN METHOD: Comment in the choosen user interface
		
		public static void main(String[] args) {
			Equation.Injektiv();
			   GUI.main(args); //
			   //mainswitch();
			  // String t1 = "ababab";
			  // String t2 = "abababa";
			  // if (t1.substring(0,t2.length()-1).equals(t1)) System.out.println("true");
			 //Equation.Injektiv();
		}}
