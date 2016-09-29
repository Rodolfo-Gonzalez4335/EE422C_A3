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
import java.io.*;

public class Main {
	static String start,end;
	
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
		initialize();
		
		Scanner input = new Scanner(System.in);
		
		while (true)
		{
			ArrayList<String> lettercheck=(parse(input));
			printLadder(getWordLadderBFS(lettercheck.get(0), lettercheck.get(1)));
			System.out.println("=========================");
			printLadder(getWordLadderDFS(lettercheck.get(0), lettercheck.get(1)));
		}
		
	}
	
	public static void initialize() {
		
	}
	
	/**
	 * Method reads the input.
	 * 
	 * @param keyboard Scanner connected to System.in
	 * 
	 * @return ArrayList of 2 Strings containing start word and end word.  
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		
		ArrayList<String> inputs = new ArrayList<String>();
		
		start=keyboard.nextLine();
		start= start.toUpperCase();
		
		end = keyboard.nextLine();
		end = end.toUpperCase();
		
		inputs.add(start);
		inputs.add(end);
		
		if (start.equals("/quit")|| end.equals("/quit"))
			System.exit(0);
		
		return inputs;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		Set<String> dict = makeDictionary();
		DFSLadder.clearStack();
		DFSLadder.generateLadder(start,end,dict);
	
		return DFSLadder.getLadder();
		
	}
	
	/**
	 * This method gets a word ladder using the BFS algorithm.
	 * 
	 * @param start beginning of the word ladder to be found
	 * @param end  ending of the word ladder to be found
	 * 
	 * @return the word ladder list gotten
	 * */
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		
    	Set<String> dict = makeDictionary();
		BFS idk= new BFS(start,end,dict);
		idk.setLadder();
		//idk.reverseLadder();//this happens because the word ladder is gotten backwards
		return idk.getLadder();
		
	}
    
    /**
     * This method reads a dictionary file and stores it in a set
     * 
     * @return the set read
     * @return failed exits 1
     * */
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	/**
	 * This method prints the ladder given
	 * 
	 * @param ladder: string list to be printed
	 * */
	public static void printLadder(ArrayList<String> ladder) {
		
		if (ladder.isEmpty())
		{
			System.out.println("no word ladder can be found between " 
					+ start + " and " + end + ".");
		}
		
		else
		{
			System.out.println("A " + (ladder.size()-2)+"-rung "
					+ "word ladder exists between "+ start + " and " +end+".");
			for (int i=0; i<ladder.size();i++)
				System.out.println(ladder.get(i));
		}
	}
	
}
