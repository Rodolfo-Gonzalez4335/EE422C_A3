package assignment3;

import java.util.*;

public class BFS {
	
	private String start;
	private String end;
	private boolean quit=false;
	private Set<String> Dictionary= new HashSet<String>(); 
	private String abc_s="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private HashMap<String,String> AdjencyList = new HashMap<String,String>();
	private ArrayList<String> Ladder= new ArrayList<String>();
	
	
	/**
	 * This constructor sets the constant values given
	 * */	
	
	public BFS(String start, String end, Set<String> Dictionary)
	{
		if (start.equals("\\quit") || end.equals("\\quit"))
			quit=true;
		this.Dictionary.addAll(Dictionary);
		this.start= start;
		this.end=end;	
	}
	
	/**
	 * This method decides on what letter to start changing.So for 
	 * convenience we start at the index value of the string end
	 * since that is what we want to check first.
	 * 
	 * @param i is the index value to change the word
	 * @param index is the index value of the abc_s string
	 * 
	 * @return return character changed
	 * */
	
	private char character_to_change(int i,int index){
		if (index==0) return end.charAt(i);
		else
		{
			return abc_s.charAt((abc_s.indexOf(end.charAt(i))+index)%(abc_s.length()-1));
		}
	}
	
	/**
	 * This method replaces a character from a given String 
	 * with another letter
	 * 
	 * @param i is the size of the index
	 * @param j is the index of the Alphabet
	 * @param temp is the given string to be changed
	 * 
	 * @return returns the changed string
	 * */	
	
	private String replaceString(int i, int j, String temp){
		String dummy=temp;
		char change_char= character_to_change(i,j);
		
		if (i==0)
			dummy= String.valueOf(change_char)+dummy.substring(i+1, temp.length());
		
		else if (i<=temp.length()-1)
			dummy= dummy.substring(0,i)+ String.valueOf(change_char)
			+ dummy.substring(i+1,temp.length());
		
		else 
			dummy= dummy.substring(0,i)+String.valueOf(change_char);

		return dummy;
	}
	
	/**
	 * This method creates the data structure that results whether on knowing if we
	 * got a solution or not
	 * 
	 * @return true if successful false if not successful
	 */
	
	private boolean Algorithm()
	{
		String wordChange,temp=start;
		abc_s=abc_s.toUpperCase();
		ArrayList<String> queue= new ArrayList<String>();
		queue.add(start);
		
		//the loop exits out immediately after
		while (true)
		{
			//adding elements to queue
			if (queue.size()!=0)
				temp = queue.remove(0);
			//case where queue goes empty meaning no solution
			else
				return false;

			for (int i=0; i<start.length(); i++)
			{
				for (int j=0; j<abc_s.length(); j++)
				{
					
					wordChange=replaceString(i,j,temp);
					
					if (Dictionary.contains(wordChange)&& !wordChange.equals(start))
					{
							
						if(AdjencyList.containsKey(wordChange)==false)
						{
							if (wordChange.equals(end)){AdjencyList.put(wordChange,temp); return true;}
							AdjencyList.put(wordChange,temp);
							queue.add(wordChange);
						}
					}
				}
			}
			
		}
		
	}
	
	/**
	 * This method creates the ladder 
	 */
	
	private void MakeLadder()
	{
		String dummy=end;
		Ladder.add(dummy);
		
		while(dummy!= start)
		{
			
			Ladder.add(AdjencyList.get(dummy));
			dummy= AdjencyList.get(dummy);
		}
		Collections.reverse(Ladder);
	}
	
	/**
	 * This method creates sets the ladder solution
	 * 
	 * @return true if successful false if it failed to set ladder
	 * */
	
	public boolean setLadder()
	{
		if (quit==true) return false;
		if (Algorithm()==false) 
			return false;
		MakeLadder();
		return true;
	}
	
	/**
	 * This method gets the ladder variable.
	 * */
	
	public ArrayList<String> getLadder(){
		if (quit==true) return null;
		return Ladder;
	}

}
