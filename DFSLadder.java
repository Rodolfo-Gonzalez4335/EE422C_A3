package assignment3;

import java.util.*;

public class DFSLadder {
	private static ArrayList<String> DFSstack = new ArrayList<String>();
	private static ArrayList<String> DFSvisited = new ArrayList<String>();
	
	
	public DFSLadder(String s, String e) {
		
	}
	
	public static boolean generateLadder(String start, String end, Set<String> dict){
		DFSstack.add(start);
		DFSvisited.add(start);
		if(start.equals(end))
			return true;
		
		char temp;
		char[] cStart = start.toCharArray();
		for(int i=0; i<cStart.length; i++){
			temp = cStart[i];
			for(char c='A'; c<='Z'; c++){
				cStart[i] = c;
				String newStart = new String(cStart);
				if(dict.contains(newStart))
					if(!(DFSvisited.contains(newStart)))
						if(generateLadder(newStart, end, dict) == false)
							DFSstack.remove(DFSstack.size()-1);
						else
							return true;
			}
			cStart[i] = temp;
		}
		return false;
	}
	
	public static void clearStack(){
		DFSstack.clear();
		DFSvisited.clear();
	}
	public static ArrayList<String> getLadder(){
		if(DFSstack.size() == 1)
			DFSstack.remove(DFSstack.size()-1);
		return DFSstack;
	}
	
}
