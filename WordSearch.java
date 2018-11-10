import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class WordSearch{
    private char[][]data;
    private long seed;
    private ArrayList<String> words;
    private Random rand;

    public WordSearch(int rows,int cols, String filename){
    	data = new char[rows][cols];
    	clear();
    	seed = System.currentTimeMillis();
    	rand = new Random(seed);
    	words = new ArrayList<>();
    	setWords(filename);
    }
    public WordSearch(int rows,int cols, String filename, long randSeed){
    	data = new char[rows][cols];
    	clear();
    	seed = randSeed;
    	rand = new Random(seed);
    	words = new ArrayList<>();
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
    			words.add(word);
    		}
    	}catch (FileNotFoundException e){
    		System.out.println("File not found: " + filename);
    		System.exit(1);
    	}
    }
    private String getWords(){
    	String s = ("Words: ");
	    for (int i = 0; i < words.size() - 1; i++){
	      s+=(words.get(i) + ", ");
	    }
	    if (words.size() > 0) s+=(words.get(words.size() - 1));
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
    	out+= getWords();
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
    public boolean addWord( int row, int col, String word, int rin, int cin){
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
    	return true;
    }

    private boolean addAllWords(){
    	
    	return true;
    }
}
