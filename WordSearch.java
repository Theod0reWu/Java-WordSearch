import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
public class WordSearch{
    private char[][]data;
    private long seed;
    private ArrayList<String> words;
    private ArrayList<String> addedWords;
    private Random rand;

    public WordSearch(int rows,int cols, String filename){
    	data = new char[rows][cols];
    	clear();
    	seed = System.currentTimeMillis();
    	rand = new Random(seed);
    	words = new ArrayList<>();
    	addedWords = new ArrayList<>();
    	setWords(filename);
    	addAllWords();
    }
    public WordSearch(int rows,int cols, String filename, long randSeed){
    	data = new char[rows][cols];
    	clear();
    	seed = randSeed;
    	rand = new Random(seed);
    	words = new ArrayList<>();
    	addedWords = new ArrayList<>();
    	setWords(filename);
    }
    public long getSeed(){
    	return seed;
    }
    private void setWords(String filename){
    	try{
    		File f = new File(filename);
    		Scanner in = new Scanner(f);
    		while (in.hasNext()){
    			String word = in.next().trim().toUpperCase();
    			if (word.charAt(word.length() - 1) == ','){
    				word = word.substring(0,word.length()-1);
    			}
    			if (word.length() <= data.length || word.length() <= data[0] .length){
    				// if words are too long they are denied
    				words.add(word);
    			}
    		}
    	}catch (FileNotFoundException e){
    		System.out.println("File not found: " + filename);
    		System.exit(1);
    	}
    }
    private String getAddedWords(){
    	String s = ("Words: ");
	    for (int i = 0; i < addedWords.size() - 1; i++){
	      s+=(addedWords.get(i) + ", ");
	    }
	    if (addedWords.size() > 0) s+=(addedWords.get(addedWords.size() - 1));
	    return s;
    }
    private void clear(){
    	for (int i = 0; i < data.length; i++){
    		for (int e = 0; e < data[i].length; e++){
    			data[i][e] = '_';
    		}
    	}
    }
    public String toString(){
    	String out = "";
    	for (int i = 0; i < data.length; i++){
    		out += "|";
    		for (int e = 0; e < data[i].length; e++){
    			out += data[i][e];
    			if (e != data[i].length - 1) {out += " ";}
    		}
    		out += "|\n";
    	}
    	out+= getAddedWords();
    	return out;
    }
    private char[][] copy(){
    	char[][] old = new char[data.length][data[0].length];
    	for (int i = 0; i < data.length; i++){
    		for (int e = 0; e < data[i].length; e++){
    			old[i][e] = data[i][e];
    		}
    	}
    	return old;
    }
    private boolean addWord( int row, int col, String word, int rin, int cin){
    	if (row < 0 || col < 0 || (rin == 0 && cin == 0)) return false;
    	if (row >= data.length|| col >= data[0].length) return false;
    	if (data[row].length - (col*cin) < word.length() || data.length - (row*rin) < word.length()) return false;
    	char[][] old = copy();

    	for (int r = row, c = col, e = 0; e < word.length(); r+=rin, c+=cin, e++){
    		if (data[r][c] == ('_')) {data[r][c] = word.charAt(e);}
    		if (data[r][c] != word.charAt(e)) { 
    			data = old;
    			return false;}
    	}
    	words.remove(word);
    	addedWords.add(word);
    	return true;
    }
    private boolean addAllWords(){
    	for (int w = 0; w < words.size(); w++){
	    	String rWord = words.get(rand.nextInt(words.size()));
	    	int l = addedWords.size();
	    	int rXin = 0, rYin = 0;
	    	while (rX == 0 & rY == 0){
		    		rXin = rand.nextInt(3) - 2;
		    		rYin = rand.nextInt(3) - 2;
		    	}
		    HashMap<Integer, Integer> used = new HashMap<>();
	    	while (addedWords.size() == l){
		    	int rX = rand.nextInt(data.length); 
		    	int rY = rand.nextInt(data[0].length);
		    	while (used.containsKey(rX) && used.get(rX) == rY){
		    		int rX = rand.nextInt(data.length); 
		    		int rY = rand.nextInt(data[0].length);
		    	}
		    	
			}
	    }
    	return true;
    }
}
