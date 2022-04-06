package Topic_M;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class FileProcessor
{
	/* TAKES IN FILE PATH, READS IN FILE, STORE ELEMENTS IN PASSED ON ARRAYLIST, RETURNS THE LIST */
	public static ArrayList<String> FiletoList(String filepath, ArrayList<String> fileList) throws FileNotFoundException
	{
		Scanner s = new Scanner(new File(filepath));
		
		while (s.hasNext())
		{
		    fileList.add(s.next());
		}
		s.close();		
		
		/* Method inside to convert all elements in fileList to Lower Case - to then return list*/
		for (String i : fileList) 
		{
            String newValue = i.toLowerCase(Locale.ROOT);
            fileList.set(fileList.indexOf(i), newValue);
        }
		
		return fileList;
	}
	
	/* SEPERATE FUNCTION (USER MAY ADD TO LIST) TO: REMOVE STOPWORDS FROM PASSED ON FILE ARRAYLIST */
	public static ArrayList<String> RemoveStopWords(ArrayList<String> fileList, ArrayList<String> stopList)
	{
		fileList.removeAll(stopList);
		
		return fileList;
	}
	
	/* SAVE TOP N WORDS IN EACH FILE + FILE SIMILARITY VERDICT + NUMBER OF COMMON WORDS AMONG THE 2 FILES - TO A .csv FILE */
	public static File SaveRecords(ArrayList<String> file1List, ArrayList<String> file2List, float verdict, int duplicateNum) throws FileNotFoundException
	{
		File csvFile = new File("progress.csv");
		
		try
		{
			
			FileWriter fw = new FileWriter(csvFile, false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			for (int i = 0; i < file1List.size(); i++)
			{
			    pw.write(file1List.get(i).toString() + "\n");
			}
			pw.print(",");
			for (int i = 0; i < file2List.size(); i++)
			{
			    pw.write(file2List.get(i).toString() + "\n");
			}
			
			pw.write(verdict + "\n");
			
			pw.println(duplicateNum + "\n");

			
			//pw.println(file1List);
			//pw.println(file2List);
			//pw.println(verdict);
			//pw.println(duplicateNum);
			pw.flush();
			pw.close();
			
			JOptionPane.showMessageDialog(null," Records saved !");
		}
		catch(Exception E)
		{
			JOptionPane.showMessageDialog(null,"Records NOT saved !");
		}
		
		return csvFile;
	}
	
	@SuppressWarnings("resource")
	/* READ CSV FILE LINE BY LINE & ASSIGN EACH LINE TO A VARIABLE */
	public static String [][] ViewRecords(String csvFilePath) throws IOException
	{
		BufferedReader br = null;
		String line = "";

			br = new BufferedReader(new FileReader(csvFilePath));
			
			// file1List
			line = br.readLine();
			String[] row1 = line.split(",");
			
			// file2List
			line= br.readLine();
			String[] row2 = line.split(",");
			
			// Verdict - IN String Array
			line= br.readLine();
			// ASSIGNING TO AN ARRAY - SO RETURN STATEMENT WOULD CONSISTS OF SAME DATATYPES
			String[] row3 = line.split(" ");
			
			// DuplicateNum - IN String Array
			line = br.readLine();
			String[] row4 = line.split(" ");
			
			// Adding String Array variables into a MultiDimentional Array - TO RETURN MULTIPLE VARIABLE
			String [][] recordsMultiArray = {row1,row2,row3,row4};
			
			
			return recordsMultiArray;
			
			
			
			
//			System.out.println("FIRST LIST:");
//			for(String i : row1)
//			{
//				System.out.printf("%-10s", i);
//			}
//			
//			System.out.println(" ");
//			
//			System.out.println("SECOND LIST:");
//			for(String i : row2)
//			{
//				System.out.printf("%-10s", i);
//			}
//			
//			System.out.println(" ");
//			
//			System.out.println("Verdict: ");
//			System.out.println(row3);
//			
//			System.out.println("DuplicateNum: ");
//			System.out.println(row4);
			
			
	}
	
}
