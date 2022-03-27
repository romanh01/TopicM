package Topic_M;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

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
	
	
}
