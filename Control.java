package Topic_M;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Control 
{

	public static void main(String[] args) throws FileNotFoundException
	{	
		int topN = 10;
		String file1path = "file1.txt";
		String file2path = "file2.txt";
		String stopPath = "stop_words.txt";
		
		ArrayList<String> file1List = new ArrayList<String>();
		ArrayList<String> file2List = new ArrayList<String>();
		ArrayList<String> stopList = new ArrayList<String>();
		
		// PASSING FILE1 TO FiletoList Method in FileProcessor class
		file1List = FileProcessor.FiletoList(file1path,file1List);
		
		// PASSING FILE2 TO FiletoList Method in FileProcessor class
		file2List = FileProcessor.FiletoList(file2path,file2List);
		
		// PASSING stopList TO FiletoList Method in FileProcessor class
		stopList = FileProcessor.FiletoList(stopPath,stopList);
		
		// REMOVE Stop Words from File1
		file1List = FileProcessor.RemoveStopWords(file1List,stopList);
		
		// REMOVE Stop Words from File2
		file2List = FileProcessor.RemoveStopWords(file2List,stopList);
		
		// COUNT DUPLICATES IN file1List WITH A LINKEDHASHMAP + SORT IN DESCENDING ORDER + FETCH FIRST N
		file1List = DuplicateProcessor.CountDuplicates(file1List, topN);
		
		// COUNT DUPLICATES IN file2List WITH A LINKEDHASHMAP + SORT IN DESCENDING ORDER + FETCH FIRST N
		file2List = DuplicateProcessor.CountDuplicates(file2List, topN);
		
		// TAKES IN 2 ARRAYLISTS AND COMPARES WORDS IN EACH AND CALCULATES % OF SIMILARITY
		DuplicateProcessor.CompareLists(file1List,file2List);
		
		// Print File1 contents
		for(String i : file1List)
		{
			System.out.println(i);
		}
		
		System.out.println("-----");
		
		// Print File2 contents
		for(String i : file2List)
		{
			System.out.println(i);
		}
		
	}

}
