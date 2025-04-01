import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.text.Highlighter;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.JToolBar;
import javax.swing.JRadioButtonMenuItem;


//CLASS: Provides a grahical user interface to calculate Equations and apply functions on them.  
public class GUI {

	private JFrame frame;
	private JTextField txtwordlength;
	private JTextField txtfilterstring;
	private JTextField txtendingindex;
	private JTextField txtstartingindex;
	private JTextField txtfirstword;
	private JTextField txtsecondword;
	private JTextField txtfilename;
	private JTextField txtpath;
	private JTextField txtword;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
//----------------------------------------------------------------------------------------------------------------------
//FUNCTIONS: 	
	
	//prints an array into the TextArea.
	public void printer (String[] array , JTextArea TextArea) {
		for(int i = 0; i < array.length; i=i+2) {
			TextArea.append(array[i] + " == " + array[i+1] + "\n");
		}
	}
	
	
	
	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	//Create the application.
	public GUI() {
		initialize();
	}

	static public void printEndingStates (Coordinates[][] result, JTextArea textArea) {
	for (int j = 0; j<result.length;j++) {
			textArea.append((j + 1) + ": " + result[j] + " -> " + result[j][0] + ",dfd" +  result[j][1] + "\n");
		}}
	
	
	
	//Initialize the contents of the frame.
	// ------------------------------------------------------------------------------------------------------------------------------
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 709, 1038);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(255, 255, 240));
		textArea.setColumns(20);
		textArea.setRows(50);
		
		JButton CalcEquationsButton = new JButton("Calculate Equations");
		CalcEquationsButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		CalcEquationsButton.setForeground(Color.BLACK);
		CalcEquationsButton.setBackground(new Color(255, 255, 0));
		CalcEquationsButton.setBounds(10, 47, 298, 23);
		
		
		ButtonGroup gruppe = new ButtonGroup();
		
		JRadioButton nofilter = new JRadioButton("dont filter");
		nofilter.setSelected(true);
		gruppe.add(nofilter);
		JRadioButton dontcontainsfilter = new JRadioButton("Filter equations which dont contain the string");
		gruppe.add(dontcontainsfilter);
		
		JLabel lblNewLabel_1 = new JLabel("Word length");
		
		JRadioButton containsfilter = new JRadioButton("Filter equations which contain the string");
		

		gruppe.add(containsfilter);
		
		JRadioButton startendfilter = new JRadioButton("filter from a starting point to an end point (contained)");
		gruppe.add(startendfilter);
		
		
		JRadioButton testnew = new JRadioButton("filter from a starting point to an end point (not contained)");
		gruppe.add(testnew);
		
		
		
		
		// ------------------------------------------------------------------------------------------------------------------------------
		//If the Button is clicked, all equations (up to the specified wordlength) are printed out in the TextArea.
		CalcEquationsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int wordlength = Integer.parseInt(txtwordlength.getText());
				String filterstring = txtfilterstring.getText();
				
				textArea.setText("");
				String [] result = null; 
				String further = ""; 
				int counter = 0;
				if (nofilter.isSelected()) {
					result = Equation.compare(Equation.calculateEndingStates(Equation.GenerateWords(wordlength)));}
					
				if (containsfilter.isSelected()) {
					result = Equation.compare(Equation.calculateEndingStates(Filter.containedfilter(Equation.GenerateWords(wordlength), filterstring)));
					further = "\n which do contain " + filterstring;
				}
				if (dontcontainsfilter.isSelected()) {
					result = Equation.compare(Equation.calculateEndingStates(Filter.notcontainedfilter(Equation.GenerateWords(wordlength), filterstring)));
					further = "\n which dont contain " + filterstring;
				}
	
				if (startendfilter.isSelected()) {
					int startindex = Integer.parseInt(txtstartingindex.getText());
					int endindex = Integer.parseInt(txtendingindex.getText());
					if (1 + endindex - startindex < filterstring.length()) {
						 	JDialog AreaDialog = new JDialog();
						 	AreaDialog.setTitle("wrong input");
						 	AreaDialog.setSize(500,100);
						 	AreaDialog.getContentPane().add(new JLabel ("The choosen filterstring is either to long or the area of startindex to endindex is to big"));
						 	AreaDialog.setModal(true);
					        AreaDialog.setLocationRelativeTo(null);
						 	AreaDialog.setVisible(true);
						
					}
					result = Equation.compare(Equation.calculateEndingStates(Filter.startingfilter(Equation.GenerateWords(wordlength), filterstring, startindex, endindex)));
					further = "\n which do contain " + filterstring + " on  position " + startindex + " to " + endindex ;
					
					
				}
				
				if (testnew.isSelected()) {
					int startindex = Integer.parseInt(txtstartingindex.getText());
					int endindex = Integer.parseInt(txtendingindex.getText());
					if (1 + endindex - startindex < filterstring.length()) {
						 	JDialog AreaDialog = new JDialog();
						 	AreaDialog.setTitle("wrong input");
						 	AreaDialog.setSize(500,100);
						 	AreaDialog.getContentPane().add(new JLabel ("The choosen filterstring is either to long or the area of startindex to endindex is to big"));
						 	AreaDialog.setModal(true);
					        AreaDialog.setLocationRelativeTo(null);
						 	AreaDialog.setVisible(true);
						
					}
					result = Equation.compare(Equation.calculateEndingStates(Filter.NotStartToEndFilter(Equation.GenerateWords(wordlength), filterstring, startindex, endindex)));
					further = "\n which do not contain " + filterstring + " on  position " + startindex + " to " + endindex ;
					
					
				}
				
				for (int i=0; i<result.length-1; i=i+2) {
					textArea.append(i/2 + 1 + ": " + result[i] + " == " + result[i+1] + "\n");
					counter++;
				}
				
				 
				textArea.append("\n -----------------------------------------------------------\n");
				textArea.append(" There are " + counter + " Equations for the wordlength " + wordlength + further);
				textArea.append("\n -----------------------------------------------------------\n");
				
				
				    }
			}
		);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(CalcEquationsButton);

		frame.getContentPane().setLayout(null);
		
		
		
		// ------------------------------------------------------------------------------------------------------------------------------
		//If the button is clicked, the TextArea shows the total number of Equations.
		JButton CalcNumberEquationsButton = new JButton("Calculate number of Equations");
		CalcNumberEquationsButton.setForeground(Color.BLACK);
		CalcNumberEquationsButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		CalcNumberEquationsButton.setBackground(new Color(255, 255, 0));
		CalcNumberEquationsButton.setBounds(10, 81, 298, 23);
		CalcNumberEquationsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int wordlength = Integer.parseInt(txtwordlength.getText());
				String filterstring = txtfilterstring.getText();
				
				textArea.setText("");
				String [] result = null; 
				String further = ""; 
				int counter = 0;
				if (nofilter.isSelected()) {
					result = Equation.compare(Equation.calculateEndingStates(Equation.GenerateWords(wordlength)));}
					
				if (containsfilter.isSelected()) {
					result = Equation.compare(Equation.calculateEndingStates(Filter.containedfilter(Equation.GenerateWords(wordlength), filterstring)));
					further = "\n which do contain " + filterstring;
				}
				if (dontcontainsfilter.isSelected()) {
					result = Equation.compare(Equation.calculateEndingStates(Filter.notcontainedfilter(Equation.GenerateWords(wordlength), filterstring)));
					further = "\n which dont contain " + filterstring;
				}
	
				if (startendfilter.isSelected()) {
					int startindex = Integer.parseInt(txtstartingindex.getText());
					int endindex = Integer.parseInt(txtendingindex.getText());
					result = Equation.compare(Equation.calculateEndingStates(Filter.startingfilter(Equation.GenerateWords(wordlength), filterstring, startindex, endindex)));
					further = "\n which do contain " + filterstring + " on  position " + startindex + " to " + endindex ;
				}
				
				if (testnew.isSelected()) {
					int startindex = Integer.parseInt(txtstartingindex.getText());
					int endindex = Integer.parseInt(txtendingindex.getText());
					result = Equation.compare(Equation.calculateEndingStates(Filter.NotStartToEndFilter(Equation.GenerateWords(wordlength), filterstring, startindex, endindex)));
					further = "\n which do not contain " + filterstring + " on  position " + startindex + " to " + endindex ;
					
					
				}
			
				 
				textArea.append("\n -----------------------------------------------------------\n");
				textArea.append(" There are " + Equation.equationnumber/2 + " Equations for the wordlength " + wordlength + further);
				textArea.append("\n -----------------------------------------------------------\n");
				
				  }
				
			
		});
		frame.getContentPane().add(CalcNumberEquationsButton);
		
		txtwordlength = new JTextField();
		txtwordlength.setBounds(81, 12, 55, 24);
		txtwordlength.setColumns(10);
		frame.getContentPane().add(txtwordlength);
		
		txtfilterstring = new JTextField();
		txtfilterstring.setBackground(Color.WHITE);
		txtfilterstring.setBounds(221, 12, 86, 25);
		frame.getContentPane().add(txtfilterstring);
		txtfilterstring.setColumns(10);

	

		JScrollPane scrollBar = new JScrollPane(textArea);
		scrollBar.setBounds(318, 5, 350, 967);
		scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollBar);
		
		JMenuBar FilterMenu = new JMenuBar();
		FilterMenu.setBackground(Color.YELLOW);
		FilterMenu.setForeground(Color.ORANGE);
		FilterMenu.setBounds(10, 281, 298, 23);
		frame.getContentPane().add(FilterMenu);
		
		JMenu FilterMenuUnder = new JMenu("                                      Filter options");
		FilterMenuUnder.setHorizontalAlignment(SwingConstants.CENTER);
		FilterMenuUnder.setFont(new Font("Tahoma", Font.PLAIN, 11));
		FilterMenuUnder.setBackground(Color.RED);
		FilterMenuUnder.setHorizontalAlignment(SwingConstants.RIGHT);

		FilterMenu.add(FilterMenuUnder);
		

		


		FilterMenuUnder.add(nofilter);

		FilterMenuUnder.add(dontcontainsfilter);

		FilterMenuUnder.add(containsfilter);
		

		FilterMenuUnder.add(startendfilter);
		

		FilterMenuUnder.add(testnew);
		
		
		
		
		
		
		
		JLabel startingindexlabel = new JLabel("starting index");
		FilterMenuUnder.add(startingindexlabel);
		
		txtstartingindex = new JTextField();
		FilterMenuUnder.add(txtstartingindex);
		txtstartingindex.setToolTipText("");
		txtstartingindex.setColumns(10);
		
		JLabel endingindexlabel = new JLabel("ending index");
		FilterMenuUnder.add(endingindexlabel);
		
		txtendingindex = new JTextField();
		FilterMenuUnder.add(txtendingindex);
		txtendingindex.setColumns(10);
		
		JLabel FilterStringLabel = new JLabel("Filter String");
		FilterStringLabel.setHorizontalAlignment(SwingConstants.LEFT);
		FilterStringLabel.setLabelFor(txtfilterstring);
	
		FilterStringLabel.setBounds(146, 14, 65, 20);
		frame.getContentPane().add(FilterStringLabel);
		
		JLabel WordLengthLabel = new JLabel("Word length");
		WordLengthLabel.setHorizontalAlignment(SwingConstants.CENTER);
		WordLengthLabel.setBounds(1, 14, 70, 20);
		frame.getContentPane().add(WordLengthLabel);
		
		
		
		// ------------------------------------------------------------------------------------------------------------------------------
		//If the button is clicked, the equations (up to specified wordlength) 
		// representation gets changed and printed out in the TextArea. 
		JButton CalcRewriteButton = new JButton("Rewrite Equations");
		CalcRewriteButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		CalcRewriteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int wordlength = Integer.parseInt(txtwordlength.getText());
				String filterstring = txtfilterstring.getText();
				
				textArea.setText("");
				String [] result = null; 
				String further = ""; 
				int counter = 0;
				if (nofilter.isSelected()) {
					result = Equation.compare(Equation.calculateEndingStates(Equation.GenerateWords(wordlength)));}
					
				if (containsfilter.isSelected()) {
					result = Equation.compare(Equation.calculateEndingStates(Filter.containedfilter(Equation.GenerateWords(wordlength), filterstring)));
					further = "\n which do contain " + filterstring;
				}
				if (dontcontainsfilter.isSelected()) {
					result = Equation.compare(Equation.calculateEndingStates(Filter.notcontainedfilter(Equation.GenerateWords(wordlength), filterstring)));
					further = "\n which dont contain " + filterstring;
				}
	
				if (startendfilter.isSelected()) {
					int startindex = Integer.parseInt(txtstartingindex.getText());
					int endindex = Integer.parseInt(txtendingindex.getText());
					result = Equation.compare(Equation.calculateEndingStates(Filter.startingfilter(Equation.GenerateWords(wordlength), filterstring, startindex, endindex)));
					further = "\n which do contain " + filterstring + " on  position " + startindex + " to " + endindex ;
				}
				
				if (testnew.isSelected()) {
					int startindex = Integer.parseInt(txtstartingindex.getText());
					int endindex = Integer.parseInt(txtendingindex.getText());
					result = Equation.compare(Equation.calculateEndingStates(Filter.NotStartToEndFilter(Equation.GenerateWords(wordlength), filterstring, startindex, endindex)));
					further = "\n which do not contain " + filterstring + " on  position " + startindex + " to " + endindex ;
					
					
				}
				
				for (int i=0; i<result.length-1; i=i+2) {
					textArea.append(i/2+1 + ": " + Filter.rewrite(result[i]) + " == " + Filter.rewrite(result[i+1]) + "\n");
					System.out.print(i/2+1 + ": " + Filter.rewrite(result[i]) + " == " + Filter.rewrite(result[i+1]) + "\n");
					counter++;
				}
				
				 
				textArea.append("\n -----------------------------------------------------------\n");
				textArea.append(" There are " + counter + " Equations for the wordlength " + wordlength + further);
				textArea.append("\n -----------------------------------------------------------\n");
			}
		});
			
		
		CalcRewriteButton.setBackground(new Color(255, 255, 0));
		CalcRewriteButton.setBounds(10, 115, 298, 23);
		frame.getContentPane().add(CalcRewriteButton);
		
		
		
		
		// ------------------------------------------------------------------------------------------------------------------------------
		//If the button is clicked, the redundancy of all Equations (up to a specified wordlength) gets removed.
		// afterwards the (new) equations are printed out in the TextArea.
		JButton CalcRedundancyButton = new JButton("Remove Redundancy ");
		CalcRedundancyButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		CalcRedundancyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int wordlength = Integer.parseInt(txtwordlength.getText());
				String filterstring = txtfilterstring.getText();
				
				textArea.setText("");
				String [] result = null; 
				String further = ""; 
				int counter = 0;
				if (nofilter.isSelected()) {
					result = Equation.compare(Equation.calculateEndingStates(Equation.GenerateWords(wordlength)));}
					
				if (containsfilter.isSelected()) {
					result = Equation.compare(Equation.calculateEndingStates(Filter.containedfilter(Equation.GenerateWords(wordlength), filterstring)));
					further = "\n which do c1ontain " + filterstring;
				}
				if (dontcontainsfilter.isSelected()) {
					result = Equation.compare(Equation.calculateEndingStates(Filter.notcontainedfilter(Equation.GenerateWords(wordlength), filterstring)));
					further = "\n which dont contain " + filterstring;
				}
	
				if (startendfilter.isSelected()) {
					int startindex = Integer.parseInt(txtstartingindex.getText());
					int endindex = Integer.parseInt(txtendingindex.getText());
					result = Equation.compare(Equation.calculateEndingStates(Filter.startingfilter(Equation.GenerateWords(wordlength), filterstring, startindex, endindex)));
					further = "\n which do contain " + filterstring + " on  position " + startindex + " to " + endindex ;
				}
				
				if (testnew.isSelected()) {
					int startindex = Integer.parseInt(txtstartingindex.getText());
					int endindex = Integer.parseInt(txtendingindex.getText());
					result = Equation.compare(Equation.calculateEndingStates(Filter.NotStartToEndFilter(Equation.GenerateWords(wordlength), filterstring, startindex, endindex)));
					further = "\n which do not contain " + filterstring + " on  position " + startindex + " to " + endindex ;
					
					
				}
				result = (Redundancy.removeTransitivity(Redundancy.remove(result)));
				
				for (int i=0; i<result.length;i++) {
					if (result[i]==null)continue;
					textArea.append(result[i]);
					counter++;
				}
				
				 
				textArea.append("\n -----------------------------------------------------------\n");
				textArea.append(" There are " + Equation.equationnumber + " unique Equations with " + Equation.wordcount +"\n words for the wordlength " + wordlength + further);
				textArea.append("\n -----------------------------------------------------------\n");
			}
		});
		CalcRedundancyButton.setBackground(new Color(255, 255, 0));
		CalcRedundancyButton.setBounds(10, 147, 298, 23);
		frame.getContentPane().add(CalcRedundancyButton);
		
		JMenuBar TwoWordsEquation = new JMenuBar();
		TwoWordsEquation.setBackground(new Color(255, 255, 0));
		TwoWordsEquation.setBounds(10, 181, 298, 23);
		frame.getContentPane().add(TwoWordsEquation);
		
		JMenu Choose2WordsMenu = new JMenu("                                Equation of 2 Words");
		Choose2WordsMenu.setHorizontalAlignment(SwingConstants.CENTER);
		Choose2WordsMenu.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Choose2WordsMenu.setBackground(Color.ORANGE);
		//Choose2WordsMenu.setVerticalAlignment(CENTER);
		TwoWordsEquation.add(Choose2WordsMenu);
		
		JLabel firstwordlabel = new JLabel("First word");
		Choose2WordsMenu.add(firstwordlabel);
		
		txtfirstword = new JTextField();
		Choose2WordsMenu.add(txtfirstword);
		txtfirstword.setColumns(10);
		
		JLabel secondwordlabel = new JLabel("Second word");
		Choose2WordsMenu.add(secondwordlabel);
		
		txtsecondword = new JTextField();
		Choose2WordsMenu.add(txtsecondword);
		txtsecondword.setColumns(10);
		
		
		
		// ------------------------------------------------------------------------------------------------------------------------------
		//If two words are written in the TextFields, the function prints out in the TextField whether those 
		// two words are an equation or not. 
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Equation.Endstate2Words = true; 
				textArea.setText("");
				boolean result = false; 
				result = Equation.compare2Words(txtfirstword.getText(),txtsecondword.getText());
				if(result) textArea.append(txtfirstword.getText() + " and " + txtsecondword.getText() + " are an Equation:: \n");
				if(!result) textArea.append(txtfirstword.getText() + " and " + txtsecondword.getText() + " are not an Equation \n");
				textArea.append("----------------------- \n");
				textArea.append("Einding States:  \n");
				for (int i=0; i < Equation.endingstates2[0].length;i++) {
					if (Equation.endingstates2[0][i]==null)continue;
					textArea.append("("+String.valueOf(Equation.endingstates2[0][i].getCoordinates().getRow()));
					textArea.append( ", "+String.valueOf(Equation.endingstates2[0][i].getCoordinates().getColumn()) + ")");
					textArea.append(" : ");
					textArea.append("("  +String.valueOf(Equation.endingstates2[1][i].getCoordinates().getRow()));
					textArea.append( ", "+String.valueOf(Equation.endingstates2[1][i].getCoordinates().getColumn()) + ")");
					
					textArea.append( "\n");
				}
				

			}
		});	

		
		Choose2WordsMenu.add(btnCalculate);
		
		//If the button is clicked, a new window shows the current state machine. 
		JButton DisplayStateMachineButton = new JButton("Display State Machine");
		DisplayStateMachineButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		DisplayStateMachineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.append("\n Loading state machine image!");
				new DisplayStateMachine();
				
				
			}});	

		DisplayStateMachineButton.setBackground(new Color(255, 255, 0));
		DisplayStateMachineButton.setBounds(10, 215, 298, 23);
		frame.getContentPane().add(DisplayStateMachineButton);
		
		JMenuBar SaveFileMenu = new JMenuBar();
		SaveFileMenu.setBackground(new Color(255, 255, 0));
		
		SaveFileMenu.setBounds(10, 249, 298, 23);
		frame.getContentPane().add(SaveFileMenu);
		
		JMenu SaveFileMenu_1 = new JMenu("                                     Save as txt file");
		SaveFileMenu_1.setHorizontalAlignment(SwingConstants.CENTER);
		SaveFileMenu_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		SaveFileMenu_1.setBackground(Color.ORANGE);
		SaveFileMenu.add(SaveFileMenu_1);
		
		JLabel pathlabel = new JLabel("Filename");
		SaveFileMenu_1.add(pathlabel);
		
		txtfilename = new JTextField();
		SaveFileMenu_1.add(txtfilename);
		txtfilename.setColumns(10);
		
		JLabel FileNameLabel = new JLabel("Filepath");
		SaveFileMenu_1.add(FileNameLabel);
		
		txtpath = new JTextField();
		SaveFileMenu_1.add(txtpath);
		txtpath.setColumns(10);
		
		JButton SaveFileButton = new JButton("save file");
		SaveFileMenu_1.add(SaveFileButton);
		
		//exit Button 
		JButton ExitButton = new JButton("Exit");
		ExitButton.setBackground(new Color(128, 128, 128));
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}});
			
		ExitButton.setBounds(10, 451, 89, 23);
		frame.getContentPane().add(ExitButton);
		
		//// ------------------------------------------------------------------------------------------------------------------------------
		// removes conactatenation and transitivity from equations
		JButton CalcAllRedundancyButton = new JButton("Remove all redundancy");
		CalcAllRedundancyButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		CalcAllRedundancyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int wordlength = Integer.parseInt(txtwordlength.getText());
				String filterstring = txtfilterstring.getText();
				
				textArea.setText("");
				String [] result = null; 
				String further = ""; 
				int counter = 0;
				if (nofilter.isSelected()) {
					result = Equation.compare(Equation.calculateEndingStates(Equation.GenerateWords(wordlength)));}
					
				if (containsfilter.isSelected()) {
					result = Equation.compare(Equation.calculateEndingStates(Filter.containedfilter(Equation.GenerateWords(wordlength), filterstring)));
					further = "\n which do c1ontain " + filterstring;
				}
				if (dontcontainsfilter.isSelected()) {
					result = Equation.compare(Equation.calculateEndingStates(Filter.notcontainedfilter(Equation.GenerateWords(wordlength), filterstring)));
					further = "\n which dont contain " + filterstring;
				}
	
				if (startendfilter.isSelected()) {
					int startindex = Integer.parseInt(txtstartingindex.getText());
					int endindex = Integer.parseInt(txtendingindex.getText());
					result = Equation.compare(Equation.calculateEndingStates(Filter.startingfilter(Equation.GenerateWords(wordlength), filterstring, startindex, endindex)));
					further = "\n which do contain " + filterstring + " on  position " + startindex + " to " + endindex ;
				}
				
				if (testnew.isSelected()) {
					int startindex = Integer.parseInt(txtstartingindex.getText());
					int endindex = Integer.parseInt(txtendingindex.getText());
					result = Equation.compare(Equation.calculateEndingStates(Filter.NotStartToEndFilter(Equation.GenerateWords(wordlength), filterstring, startindex, endindex)));
					further = "\n which do not contain " + filterstring + " on  position " + startindex + " to " + endindex ;
					
					
				}
				result = Redundancy.removeAllRedundancy(Redundancy.remove(result));
				for (int i=0; i<result.length;i++) {
					if (result[i]==null)break;
					textArea.append(result[i]);
					counter++;
				}
				
				 
				textArea.append("\n -----------------------------------------------------------\n");
				textArea.append(" There are " + Equation.equationnumber + " unique Equations with " + Equation.wordcount +"\n words for the wordlength " + wordlength + further);
				textArea.append("\n -----------------------------------------------------------\n");
			}
		});
		CalcAllRedundancyButton.setBackground(new Color(255, 255, 0));
		CalcAllRedundancyButton.setBounds(10, 315, 298, 23);
		frame.getContentPane().add(CalcAllRedundancyButton);
		
		JButton ShowEndingStatesButton = new JButton("Show ending states");
		ShowEndingStatesButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ShowEndingStatesButton.setBackground(Color.YELLOW);
		ShowEndingStatesButton.setBounds(10, 349, 298, 23);
		ShowEndingStatesButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			int wordlength = Integer.parseInt(txtwordlength.getText());
			String filterstring = txtfilterstring.getText();
			
			textArea.setText("");
			Coordinates[][] result = null; 
			String further = ""; 
			int counter = 0;
			if (nofilter.isSelected()) {
				result = Equation.calculateEndingStates(Equation.GenerateWords(wordlength));}
				
			if (containsfilter.isSelected()) {
				result = Equation.calculateEndingStates(Filter.containedfilter(Equation.GenerateWords(wordlength), filterstring));
				further = "\n which do contain " + filterstring;
			}
			if (dontcontainsfilter.isSelected()) {
				result = Equation.calculateEndingStates(Filter.notcontainedfilter(Equation.GenerateWords(wordlength), filterstring));
				further = "\n which dont contain " + filterstring;
			}

			if (startendfilter.isSelected()) {
				int startindex = Integer.parseInt(txtstartingindex.getText());
				int endindex = Integer.parseInt(txtendingindex.getText());
				if (1 + endindex - startindex < filterstring.length()) {
					 	JDialog AreaDialog = new JDialog();
					 	AreaDialog.setTitle("wrong input");
					 	AreaDialog.setSize(500,100);
					 	AreaDialog.getContentPane().add(new JLabel ("The choosen filterstring is either to long or the area of startindex to endindex is to big"));
					 	AreaDialog.setModal(true);
				        AreaDialog.setLocationRelativeTo(null);
					 	AreaDialog.setVisible(true);
					
				}
				result = Equation.calculateEndingStates(Filter.startingfilter(Equation.GenerateWords(wordlength), filterstring, startindex, endindex));
				further = "\n which do contain " + filterstring + " on  position " + startindex + " to " + endindex ;
			}
			
			if (testnew.isSelected()) {
				int startindex = Integer.parseInt(txtstartingindex.getText());
				int endindex = Integer.parseInt(txtendingindex.getText());
				result = Equation.calculateEndingStates(Filter.NotStartToEndFilter(Equation.GenerateWords(wordlength), filterstring, startindex, endindex));
				further = "\n which do not contain " + filterstring + " on  position " + startindex + " to " + endindex ;
				
				
			}
			
			for (int j = 0; j<result.length;j++) {
				for (int i = 1; i<Equation.depth*3-2;i++) {
				textArea.append((j + 1) + ": " + result[j][i].getword() + " -> " + result[j][i].getCoordinates().getRow() +" , " +result[j][i].getCoordinates().getColumn() + "\n");
			}}
			
			 
		}});
			
			
			    
		

		frame.getContentPane().add(ShowEndingStatesButton);
		
		JMenuBar SearchWord = new JMenuBar();
		SearchWord.setFont(new Font("Tahoma", Font.PLAIN, 11));
		SearchWord.setBackground(Color.YELLOW);
		SearchWord.setBounds(10, 383, 298, 21);
		
		
		
		frame.getContentPane().add(SearchWord);
		
		JMenu SearchWordUnder = new JMenu("                          Search for word in text area");
		SearchWordUnder.setHorizontalAlignment(SwingConstants.CENTER);
		SearchWordUnder.setFont(new Font("Tahoma", Font.PLAIN, 11));
		SearchWord.add(SearchWordUnder);
		
		JLabel WordLabel = new JLabel("Word");
		SearchWordUnder.add(WordLabel);
		
		txtword = new JTextField();
		SearchWordUnder.add(txtword);
		txtword.setColumns(10);
		
		JButton SearchWordButton = new JButton("search");
		SearchWordUnder.add(SearchWordButton);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.GRAY);
		menuBar.setBackground(Color.YELLOW);
		menuBar.setBounds(10, 418, 298, 22);
		frame.getContentPane().add(menuBar);
		
		
		// ------------------------------------------------------------------------------------------------------------------------------
		//switch the state machine
		JMenu mnNewMenu = new JMenu("                              Choose state machine");
		mnNewMenu.setFont(new Font("Tahoma", Font.PLAIN, 11));
		menuBar.add(mnNewMenu);
		
		
		
		JRadioButton anstatemachine = new JRadioButton("State machine An");
		buttonGroup.add(anstatemachine);
		mnNewMenu.add(anstatemachine);
		anstatemachine.setSelected(true);
		Equation.An = true; 
		anstatemachine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (anstatemachine.isSelected()) {
				Equation.An = true;
				Equation.A1 = false; 
				Equation.A2 = false; 
				Equation.A3 = false; 
				Equation.customIteration = 3;
				
				}}}
				);
		
		JRadioButton a1statemachine = new JRadioButton("State machine A1");
		buttonGroup.add(a1statemachine);
		mnNewMenu.add(a1statemachine);
		a1statemachine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (a1statemachine.isSelected()) {
					Equation.An = false;
					Equation.A1 = true; 
					Equation.A2 = false; 
					Equation.A3 = false; 
					Equation.customIteration = 3;
			}}});
		
		JRadioButton a2statemachine = new JRadioButton("State Machine A2");
		buttonGroup.add(a2statemachine);
		mnNewMenu.add(a2statemachine);
		a2statemachine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (a2statemachine.isSelected()) {
					Equation.An = false;
					Equation.A1 = false; 
					Equation.A2 = true; 
					Equation.A3 = false;
					Equation.customIteration = 2;
				}}});
		
		JRadioButton a3statemachine = new JRadioButton("State machine A3");
		buttonGroup.add(a3statemachine);
		mnNewMenu.add(a3statemachine);
		a3statemachine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (a3statemachine.isSelected()) {
					Equation.An = false;
					Equation.A1 = false; 
					Equation.A2 = false; 
					Equation.A3 = true; 
					Equation.customIteration = 2;
				}}});
		
		
		// ------------------------------------------------------------------------------------------------------------------------------
		// Highlighter function to highlight certain words. 
		SearchWordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Highlighter textmarker= textArea.getHighlighter();
				HighlightPainter redmark = new DefaultHighlighter.DefaultHighlightPainter(Color.red);
				textmarker.removeAllHighlights();
				int wordlength = Integer.parseInt(txtwordlength.getText());
				String filterstring = txtfilterstring.getText();
				String searchword = txtword.getText();
				String f = textArea.getText(); 
				int c = f.indexOf(searchword);
				int counter = 0; 
				int searchwordlength = searchword.length();
				do {
				try {
					textmarker.addHighlight(c,searchwordlength+c,redmark);
				} catch (Exception e) {}
					c = f.indexOf(searchword,c+searchword.length());
					System.out.println(c);
					counter++;
				
				} while ( c >= 0);
				if (f.contains(searchword)) {
					textArea.append("The string is contained");
				}
				
				 
			}});
		
		
		
		
		
		
		//button to save the current TextArea in a .txt file.
		SaveFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String filename = "myequationlist.txt";
				if (txtpath.getText().isEmpty()) {filename = txtfilename.getText() + ".txt";}
				else { filename = txtpath.getText() + txtfilename.getText() + ".txt";}
				new SaveFile(textArea.getText(),filename);

			}});	
		
	
		

		

	}
	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}
