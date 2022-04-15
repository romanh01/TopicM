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
import java.util.Collection;
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
	
	/* SEPERATE FUNCTION (USER MAY ADD TO LIST) TO: REMOVE STOPWORDS & PUNCTUATION FROM PASSED ON FILE ARRAYLIST */
	public static ArrayList<String> RemoveStopWords(ArrayList<String> fileList, ArrayList<String> stopList)
	{
		ArrayList<String> file2List = new ArrayList<String>();
		
		fileList.removeAll(stopList);
		
		for(String s : fileList)
		{
			// KEEP ONLY (REMOVE PUNCTUATION):
			s = s.replaceAll("[^A-Za-z0-9]", "");
			file2List.add(s);
		}
		
		return file2List;
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
			
			// 1st ROW: f1,f2,verdict,duplicateNum
			// Then: f1,f2
			for (int i = 0; i < file1List.size(); i++)
			{
			    pw.write(file1List.get(i).toString() + ",");
			    pw.write(file2List.get(i).toString() + ",");
			    
			    // WILL ONLY INSERT ONCE - AS 1 PAIR OF STATS
			    if(i==0)
			    {
				    pw.write(verdict +"%" + ",");
				    pw.println(duplicateNum);
			    }
			    else
			    {
			    	 pw.write("\n");
			    }
			}
			
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
	
	/* READ CSV FILE LINE BY LINE & ASSIGN EACH LINE TO A VARIABLE */
	public static String [][] ViewRecords(String csvFilePath) throws IOException
	{
		String currentLine = "";
		String [][] multiArray;
		
		FileReader fr = new FileReader(csvFilePath);
		try (BufferedReader br = new BufferedReader(fr)) 
		{
			Collection<String[]> lines = new ArrayList<>();
			
			// IDEA ABOUT ARGUMENTS IN THE BELOW FOR LOOP WERE TAKEN FROM: - Method NOT directly copied
			// https://stackoverflow.com/a/33035265/18583576
			for (currentLine = br.readLine(); currentLine != null; currentLine = br.readLine()) 
			{
				lines.add(currentLine.split(","));
			}
			
			// ADDING ARRAYLIST TO MULTI-ARRAY - TO USE IN JTABLE - FOR ROWS
			multiArray = lines.toArray(new String[lines.size()][]);
		}

	    return multiArray;
	}
	
}
