package Topic_M;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MyGUI extends JFrame implements ActionListener 
{
	
	private static final long serialVersionUID = 1L;
	
	JFrame frame;
	
	JLabel label1;
	
	ImageIcon logo;
	
	JTable table1;
	
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JButton button6;
	
	JPanel blackpanel;
	JPanel redpanel;
	JPanel greenpanel;
	JPanel bluepanel;
	JPanel yellowpanel;
	JPanel magentapanel;
	JPanel cyanpanel;
	
	JTextField topNField;
	JTextField stopWordField;
	
	GridBagConstraints gbc1 = new GridBagConstraints();
	GridBagConstraints gbc2 = new GridBagConstraints();
	GridBagConstraints gbc3 = new GridBagConstraints();
	GridBagConstraints gbc4 = new GridBagConstraints();
	GridBagConstraints gbc5 = new GridBagConstraints();
	GridBagConstraints gbc6 = new GridBagConstraints();
	GridBagConstraints gbc7 = new GridBagConstraints();
	GridBagLayout layout = new GridBagLayout();
	

	
	int topN = 10;
	float[] statsArray;
	String recordsColNames [] = {"File 1","File 2", "Verdict","Duplicate Count"};
	String [][] recordsMultiArray = {{ "","","",""}};
	
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

		// IN PANEL 1 - FOR JLABEL - label1 - GUI LOGO
		logo = new ImageIcon("file_text_compare_black.png");
		logo.setImage(logo.getImage().getScaledInstance(500,250,Image.SCALE_SMOOTH));
		label1 = new JLabel(logo);
		
		// IN PANEL 2 - FOR BUTTON 1 - file1path
		button1 = new JButton();
		button1.setText("Select 1st File");
		button1.setToolTipText("Choose file path of 1st file");
		button1.addActionListener(this);
		
		// IN PANEL 2 - FOR BUTTON 1 - file2path
		button2 = new JButton();
		button2.setText("Select 2nd File");
		button2.setToolTipText("Choose file path of 2nd file");
		button2.addActionListener(this);
		
		// IN PANEL 3 - FOR TEXTFIELD - topNField 
		topNField = new JTextField("N");
		topNField.setPreferredSize(new Dimension(50,50));
		topNField.setFont(new Font("consolas",Font.PLAIN,25));
		topNField.setForeground(new Color(0x00FF00)); // GREEN FONT
		topNField.setBackground(Color.black);
		topNField.setCaretColor(Color.white);
		
		// IN PANEL 3
		button3 = new JButton();
		button3.setText("Submit");
		button3.setToolTipText("Submit Top N words to compare in both files");
		button3.addActionListener(this);
		
		// IN PANEL 4 - FOR TEXTFILED - stopWordField
		stopWordField= new JTextField("stop word");
		stopWordField.setPreferredSize(new Dimension(200,50));
		stopWordField.setFont(new Font("consolas",Font.PLAIN,25));
		stopWordField.setForeground(Color.WHITE);
		stopWordField.setBackground(Color.BLACK);
		stopWordField.setCaretColor(Color.WHITE);
		
		// IN PANEL 4
		button4 = new JButton();
		button4.setText("Add");
		button4.setToolTipText("Add to a stop word to stop words list");
		button4.addActionListener(this);
		
		// IN PANEL 5 - CLICK FOR: FiletoList, RemoveStopWords, CountDuplicates, CompareLists
		button5 = new JButton();
		button5.setText("Compare both files");
		button5.setToolTipText("Start comparison and return");
		button5.addActionListener(this);
		
		// IN PANEL 6 - CLICK TO: DISPLAY PREVIOUS RECORD
		button6 = new JButton();
		button6.setText("Load Previous Record");
		button6.setToolTipText("Previous record of top file1 & file2 contents and its stats");
		button6.addActionListener(this);
				
		// IN PANEL 7 JTABLE - Display returned recordsList DATA - cyanPanel
		table1 = new JTable(recordsMultiArray,recordsColNames);
		table1.setPreferredScrollableViewportSize(new Dimension(500,400));
		table1.setFillsViewportHeight(true);
		JScrollPane scroll1 = new JScrollPane(table1);
		
		
		
		/* PANEL SETTINGS */
        blackpanel = new JPanel();
        blackpanel.setBackground(Color.BLACK);
	    gbc1.fill = GridBagConstraints.BOTH;
	    gbc1.weightx = 0.7f;
	    gbc1.weighty = 0.2f;
	    gbc1.gridx = 0;
	    gbc1.gridy = 0;
	    gbc1.gridwidth = 2; 
	    
		redpanel = new JPanel();
		redpanel.setBackground(Color.RED);
	    gbc2.fill = GridBagConstraints.BOTH;
	    gbc2.weightx = 0.7f;
	    gbc2.weighty = 0.8f;
	    gbc2.gridx = 0;
	    gbc2.gridy = 1;
	
		greenpanel = new JPanel();
		greenpanel.setBackground(Color.GREEN);
	    gbc3.fill = GridBagConstraints.BOTH;
	    gbc3.weightx = 0.7f;
	    gbc3.weighty = 0.8f;
	    gbc3.gridx = 0;
	    gbc3.gridy = 3;	    

		bluepanel = new JPanel();
		bluepanel.setBackground(Color.BLUE);
	    gbc4.fill = GridBagConstraints.BOTH;
	    gbc4.weightx = 0.7f;
	    gbc4.weighty = 0.8f;
	    gbc4.gridx = 0;
	    gbc4.gridy = 4;
		
		yellowpanel = new JPanel();
		yellowpanel.setBackground(Color.YELLOW);
	    gbc5.fill = GridBagConstraints.BOTH;
	    gbc5.weightx = 0.7f;
	    gbc5.weighty = 0.8f;
	    gbc5.gridx = 0;
	    gbc5.gridy = 5;
	
		magentapanel = new JPanel();
		magentapanel.setBackground(Color.MAGENTA);
	    gbc6.fill = GridBagConstraints.BOTH;
	    gbc6.weightx = 0.7f;
	    gbc6.weighty = 0.8f;
	    gbc6.gridx = 0;
	    gbc6.gridy = 6;
	
		cyanpanel = new JPanel();
		cyanpanel.setBackground(Color.CYAN);
	    gbc7.fill = GridBagConstraints.BOTH;
	    gbc7.weightx = 0.3f;
	    gbc7.weighty = 1f;
	    gbc7.gridx = 1;
	    gbc7.gridy = 1;
	    gbc7.gridheight = 6;

		
		/* 1. LEAVE ME HERE */
		frame = new JFrame("Topic Modeller");
		frame.setDefaultCloseOperation(MyGUI.EXIT_ON_CLOSE);
		frame.setLayout(layout);
		//frame.setLayout(new GridLayout(7,1,10,10));
		frame.setSize(950,950);
		frame.setVisible(true);
		
		/* 2. ADDING SWING COMPONENTS TO PANELS*/
		
		blackpanel.add(label1);
		
		redpanel.add(button1);
		redpanel.add(button2);
		
		greenpanel.add(topNField);
		greenpanel.add(button3);
		
		bluepanel.add(stopWordField);
		bluepanel.add(button4);
		
		yellowpanel.add(button5);
		
		magentapanel.add(button6);
		
		cyanpanel.add(scroll1);
		
		/* 3. ADDING PANELS TO FRAME */
		frame.add(blackpanel,gbc1);
		frame.add(redpanel,gbc2);
		frame.add(greenpanel,gbc3);
		frame.add(bluepanel,gbc4);
		frame.add(yellowpanel,gbc5);
		frame.add(magentapanel,gbc6);
		frame.add(cyanpanel,gbc7);
	
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
			// ACCEPTS ONLY POSITIVE INTEGERS
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

			
			// REMOVE Stop Words & Punctuation from File1
			file1List = FileProcessor.RemoveStopWords(file1List,stopList);
			
			// REMOVE Stop Words & Punctuation from File2
			file2List = FileProcessor.RemoveStopWords(file2List,stopList);
			
			// COUNT DUPLICATES IN file1List WITH A LINKEDHASHMAP + SORT IN DESCENDING ORDER + FETCH FIRST N
			file1List = DuplicateProcessor.CountDuplicates(file1List, topN);
			
			// COUNT DUPLICATES IN file2List WITH A LINKEDHASHMAP + SORT IN DESCENDING ORDER + FETCH FIRST N
			file2List = DuplicateProcessor.CountDuplicates(file2List, topN);
			
			// TAKES IN 2 ARRAYLISTS AND COMPARES WORDS IN EACH AND CALCULATES % OF SIMILARITY
			//RETURNS VERDICT & RETAINLIST SIZE IN AN ARRAY
			statsArray = DuplicateProcessor.CompareLists(file1List,file2List,topN);
			
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
				
			} 
			catch (FileNotFoundException e1)
			{
				e1.printStackTrace();
			}

		}
		else if(e.getSource() == button6)
		{
			String csvFilePath = "progress.csv";
			try
			{
				recordsMultiArray = FileProcessor.ViewRecords(csvFilePath);
				
				table1.setModel(new DefaultTableModel(recordsMultiArray, recordsColNames));
				
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
		}

	}

}
