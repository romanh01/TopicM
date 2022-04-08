package Topic_M;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class DuplicateProcessor 
{
	/* USING A HASHMAP TO COUNT DUPLICATES AS KEYS FOR EVERY WORD AS VALUES */
	public static ArrayList<String> CountDuplicates(ArrayList<String> fileList, int topN)
	{
		LinkedHashMap<String, Integer> DuplicateMap = new LinkedHashMap<>();

		  for (String s: fileList) 
		  {
			  Integer word_count = DuplicateMap.get(s);
			  	
			  if(word_count == null)
			  {
				  word_count = 0;
			  }
			  DuplicateMap.put(s, word_count +1);
		  }
		  System.out.println(DuplicateMap);
		  System.out.println("-----");
		  
		  //LinkedHashMap preserve the ordering of elements in which they are inserted
		  LinkedHashMap<String, Integer> SortedDuplicateMap = new LinkedHashMap<>();
		   
		  //Use Comparator.reverseOrder() for reverse ordering
		  DuplicateMap.entrySet()
		    .stream()
		    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
		    .forEachOrdered(i -> SortedDuplicateMap.put(i.getKey(), i.getValue()));
		   
		  System.out.println("Sorted Duplicates" + SortedDuplicateMap);
		  System.out.println("-----");
		  
		  /* CONVERT LINKEDHASHMAP TO ARRAYLIST (TEMPORARY) - DuplicateKeys - INSERTION ORDER IS KEPT */
		  ArrayList<String> DuplicateKeys =new ArrayList<>(SortedDuplicateMap.keySet());
		  System.out.println("Whats inside this arraylist that was converted from a linkedhashmap: " + DuplicateKeys);
		  System.out.println("-----");
		    
		  // Clear fileList Elements to then populate it with sorted by duplicate count elements
		  fileList.clear();
		  
		  
		  /* FOR LOOP that iterates N amount of times (DuplicateKeys) and stores first N elements in fileList */
	       for(int i = 0; i < topN; i++)
	       {  
	    	   fileList.add(DuplicateKeys.get(i));  
	       }
		  
		return fileList;
	}
	

	/* CALCULATES ARRAYLIST SIMILARITY % - RETURNS STATS (VERDICT & retainListsize) IN A List */
	@SuppressWarnings("unchecked")
	public static float[] CompareLists(ArrayList<String> fileList1, ArrayList<String> fileList2,int topN)
	{ 
		float verdict;
		ArrayList<String> retainList = new ArrayList<String>();
		
		retainList = (ArrayList<String>)fileList1.clone();
		
		retainList.retainAll(fileList2);
		//System.out.println("After retainAll: " + retainList);
		
		float retainListSize = retainList.size();
		verdict = ((retainListSize/topN)*100);
		System.out.println("Similarity:" +verdict +"%");
		
		float[] statsArray = {verdict,retainListSize};
		
		// inserted into JOptionPane - BUTTON 5
		return statsArray;
	}
}
