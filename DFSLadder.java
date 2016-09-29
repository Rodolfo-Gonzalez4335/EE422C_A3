/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Rodolfo Gonzalez
 * rg36763
 * <Student1 5-digit Unique No.>
 * Rohan Kondetimmanahalli
 * rak2369
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Git URL: https://github.com/Rodolfo-Gonzalez4335/EE422C_A3/
 * Fall 2016
 */

package assignment3;

import java.util.*;

public class DFSLadder {
	private static ArrayList<String> DFSstack = new ArrayList<String>();		//Current stack path
	private static ArrayList<String> DFSvisited = new ArrayList<String>();		//All visited nodes
	
	
	public DFSLadder(String s, String e) {
		
	}
	
	public static boolean generateLadder(String start, String end, Set<String> dict){
		DFSstack.add(start);
		DFSvisited.add(start);
		if(start.equals(end))			//If solution is found, return true.
			return true;
		
		char temp;
		char[] cStart = start.toCharArray();
		for(int i=0; i<cStart.length; i++){
			temp = cStart[i];
			for(char c='A'; c<='Z'; c++){
				cStart[i] = c;
				String newStart = new String(cStart);
				if(dict.contains(newStart))								//Checks Dictionary	
					if(!(DFSvisited.contains(newStart)))				//Checks to make sure word is not repeated
						if(generateLadder(newStart, end, dict) == false)//Recursive step with new word
							DFSstack.remove(DFSstack.size()-1);
						else
							return true;
			}
			cStart[i] = temp;
		}
		return false;
	}
	
	public static void clearStack(){					//Since this class is static, the static attributes 
														//must be cleared before every use.
		DFSstack.clear();
		DFSvisited.clear();
	}
	public static ArrayList<String> getLadder(){		//Returns the DFSstack, which is the correct solution
		if(DFSstack.size() == 1)
			DFSstack.remove(DFSstack.size()-1);
		return DFSstack;
	}
	
}
