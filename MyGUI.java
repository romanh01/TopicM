package Topic_M;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyGUI extends JFrame implements ActionListener 
{
	
	private static final long serialVersionUID = 1L;
	
	JFrame frame;
	
	JLabel label1;
	
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JButton button6;

	JPanel redpanel;
	JPanel greenpanel;
	JPanel bluepanel;
	JPanel yellowpanel;
	JPanel magentapanel;
	
	JTextField topNField;
	JTextField stopWordField;

	
	int topN = 10;
	float[] statsArray;
	float verdict;
	float duplicateNum;
	File csvfile;
	String file1path = "file1.txt";
	String file2path = "file2.txt";
	String stopPath = "stop_words.txt";
	
	ArrayList<String> file1List = new ArrayList<String>();
	ArrayList<String> file2List = new ArrayList<String>();
	ArrayList<String> stopList = new ArrayList<String>();

	
	public MyGUI(String title)  
	{
		super (title);

		// IN PANEL 1 - FOR BUTTON 1 - file1path
		button1 = new JButton();
		button1.setText("Select 1st File");
		button1.setToolTipText("Choose file path of 1st file");
		button1.addActionListener(this);
		
		// IN PANEL 1 - FOR BUTTON 1 - file2path
		button2 = new JButton();
		button2.setText("Select 2nd File");
		button2.setToolTipText("Choose file path of 2nd file");
		button2.addActionListener(this);
		
		// IN PANEL 2 
		button3 = new JButton();
		button3.setText("Submit");
		button3.setToolTipText("Submit Top N words to compare in both files");
		button3.addActionListener(this);
		
		// IN PANEL 3
		button4 = new JButton();
		button4.setText("Add");
		button4.setToolTipText("Add to a stop word to stop words list");
		button4.addActionListener(this);
		
		// IN PANEL 4 - CLICK FOR: FiletoList, RemoveStopWords, CountDuplicates, CompareLists
		button5 = new JButton();
		button5.setText("Compare both files");
		button5.setToolTipText("Start comparison and return");
		button5.addActionListener(this);
		
		// IN PANEL 5 - CLICK TO: DISPLAY PREVIOUS RECORD
		button6 = new JButton();
		button6.setText("Load Previous Record");
		button6.setToolTipText("Previous record of top file1 & file2 contents and its stats");
		button6.addActionListener(this);
		
		// TEXTFIELD - topNField - PANEL 2
		topNField = new JTextField("N");
		topNField.setPreferredSize(new Dimension(50,50));
		topNField.setFont(new Font("consolas",Font.PLAIN,25));
		topNField.setForeground(new Color(0x00FF00)); // GREEN FONT
		topNField.setBackground(Color.black);
		topNField.setCaretColor(Color.white);
		
		// TEXTFILED - stopWordField - PANEL 3
		stopWordField= new JTextField("stop word");
		stopWordField.setPreferredSize(new Dimension(200,50));
		stopWordField.setFont(new Font("consolas",Font.PLAIN,25));
		stopWordField.setForeground(Color.WHITE);
		stopWordField.setBackground(Color.BLACK);
		stopWordField.setCaretColor(Color.WHITE);
		
		/* PANEL SETTINGS */
		redpanel = new JPanel();
		redpanel.setBackground(Color.RED);
		redpanel.setBounds(150, 10, 500, 75);
		
		greenpanel = new JPanel();
		greenpanel.setBackground(Color.GREEN);
		greenpanel.setBounds(150, 100, 500, 75);
		
		bluepanel = new JPanel();
		bluepanel.setBackground(Color.BLUE);
		bluepanel.setBounds(150, 210, 500, 75);
		
		yellowpanel = new JPanel();
		yellowpanel.setBackground(Color.YELLOW);
		yellowpanel.setBounds(150, 310, 500, 75);
		
		magentapanel = new JPanel();
		magentapanel.setBackground(Color.MAGENTA);
		magentapanel.setBounds(150, 410, 500, 75);
		
		/* 1. LEAVE ME HERE */
		frame = new JFrame("Topic Modeller");
		frame.setDefaultCloseOperation(MyGUI.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(750,750);
		frame.setVisible(true);
		
		/* 2. ADDING SWING COMPONENTS TO PANELS*/
		redpanel.add(button1);
		redpanel.add(button2);
		
		greenpanel.add(topNField);
		greenpanel.add(button3);
		
		bluepanel.add(stopWordField);
		bluepanel.add(button4);
		
		yellowpanel.add(button5);
		
		magentapanel.add(button6);
		
		/* 3. ADDING PANELS TO FRAME */
		frame.add(redpanel);
		frame.add(greenpanel);
		frame.add(bluepanel);
		frame.add(yellowpanel);
	
	}


	public void actionPerformed(ActionEvent e) 
	{
		/* SELECT 1ST FILE'S PATH */
		if(e.getSource() == button1)
		{
			JFileChooser chooseFile1 = new JFileChooser();
			
			// SETTING CURRENT DIRECTORY : PROJECT FOLDER
			chooseFile1.setCurrentDirectory(new File("."));
			
			// SELECT FILE TO OPEN
			int response = chooseFile1.showOpenDialog(null);
			
			// GET FILE PATH IF FILE CHOSEN
			if(response == JFileChooser.APPROVE_OPTION)
			{
				File file1 = new File(chooseFile1.getSelectedFile().getAbsolutePath());
				
				file1path = file1.getAbsolutePath();
			}
			
			try 
			{
				// PASSING FILE1 TO FiletoList Method in FileProcessor class
				file1List = FileProcessor.FiletoList(file1path,file1List);
			} 
			catch (FileNotFoundException e1) 
			{
				e1.printStackTrace();
			}
		}
		
		/* SELECT 2ND FILE'S PATH - VERY SIMILAR TO BUTTON 1 */
		else if(e.getSource() == button2)
		{
			JFileChooser chooseFile2 = new JFileChooser();
			
			// SETTING CURRENT DIRECTORY : PROJECT FOLDER
			chooseFile2.setCurrentDirectory(new File("."));
			
			// SELECT FILE TO OPEN
			int response = chooseFile2.showOpenDialog(null);
			
			// GET FILE PATH IF FILE CHOSEN
			if(response == JFileChooser.APPROVE_OPTION)
			{
				File file1 = new File(chooseFile2.getSelectedFile().getAbsolutePath());
				
				file2path = file1.getAbsolutePath();
			}
			
			try 
			{
				// PASSING FILE2 TO FiletoList Method in FileProcessor class
				file2List = FileProcessor.FiletoList(file2path,file2List);
			}
			catch (FileNotFoundException e1)
			{
				e1.printStackTrace();
			}
		}
		
		/* ALTER topN VALUE */
		else if(e.getSource() == button3)
		{
			String topNText = topNField.getText();
			int convertMe = 0;
			
			JOptionPane.showConfirmDialog(null, "size1:" + file1List.size() + "size2" + file2List.size());
			
			// ERROR CHECKING - MUST BE OF TYPE INT
			if(Pattern.matches("\\d+", (CharSequence) topNText))
			{
				convertMe = Integer.parseInt(topNText); // Text to integer
				
				// ERROR CHECKING - topN MUST BE SMALLER THEN THE ARRAY SIZE
				if(convertMe < (file1List.size() & file2List.size()))
				{
					topN = convertMe;	
				}
				else if(convertMe > file1List.size())
				{
					JOptionPane.showConfirmDialog(null, "Number must be smaller", "Size Error", JOptionPane.WARNING_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showConfirmDialog(null, "Must be a positive number", "Type Error", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		/* CALL FiletoList METHOD TO CONVERT stopWords text file TO AN ARRAYLIST */
		/* THEN ADD stopWord submitted to ArrayList */
		else if(e.getSource() == button4)
		{
			String stopWordText = stopWordField.getText();
			
			// PASSING stopList TO FiletoList Method in FileProcessor class
			try 
			{
				stopList = FileProcessor.FiletoList(stopPath,stopList);
			} 
			catch (FileNotFoundException e1) 
			{
				e1.printStackTrace();
			}
			// WORD SENT IN LOWERCASE
			String lowercaseMe = stopWordText.toLowerCase();
			stopList.add(lowercaseMe);
		}
		else if(e.getSource() == button5)
		{

			
			// REMOVE Stop Words from File1
			file1List = FileProcessor.RemoveStopWords(file1List,stopList);
			
			// REMOVE Stop Words from File2
			file2List = FileProcessor.RemoveStopWords(file2List,stopList);
			
			// COUNT DUPLICATES IN file1List WITH A LINKEDHASHMAP + SORT IN DESCENDING ORDER + FETCH FIRST N
			file1List = DuplicateProcessor.CountDuplicates(file1List, topN);
			
			// COUNT DUPLICATES IN file2List WITH A LINKEDHASHMAP + SORT IN DESCENDING ORDER + FETCH FIRST N
			file2List = DuplicateProcessor.CountDuplicates(file2List, topN);
			
			// TAKES IN 2 ARRAYLISTS AND COMPARES WORDS IN EACH AND CALCULATES % OF SIMILARITY
			//RETURNS VERDICT & RETAINLIST SIZE IN AN ARRAY
			statsArray = DuplicateProcessor.CompareLists(file1List,file2List);
			
			verdict = statsArray[0];
			duplicateNum = statsArray[1];
			
			
			// FLOAT ELEMENT FROM FLOAT ARRAY TO INT
			int duplicateInt = (int)duplicateNum;
			
			
			if(duplicateInt == 1)
			{
				JOptionPane.showMessageDialog(null,duplicateInt + " word was in both files, resulting in a file similarity of " + verdict + "%");
			}
			else
			{
				JOptionPane.showMessageDialog(null,duplicateInt + " words were in both files, resulting in a file similarity of " + verdict + "%");
			}
			
			try
			{
				csvfile = FileProcessor.SaveRecords(file1List, file2List, verdict, duplicateInt);
				
				FileProcessor.ViewRecords(csvfile);
			} 
			catch (FileNotFoundException e1)
			{
				e1.printStackTrace();
			}

		}
		else if(e.getSource() == button6)
		{
			FileProcessor.ViewRecords(csvfile);
		}

	}

}
