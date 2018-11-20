import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class WordSearch{
    private char[][]data;
    private long seed;
    private ArrayList<String> words;
    private ArrayList<String> addedWords;
    private Random rand;
    public WordSearch(int rows,int cols, String filename, long randSeed, boolean key) throws FileNotFoundException{
    	data = new char[rows][cols]; clear();
    	seed = randSeed;
    	rand = new Random(seed);
    	words = new ArrayList<>();
    	addedWords = new ArrayList<>();
    	setWords(filename);
    	addAllWords();
    	if (!key) fillIn();
    }
    private void setWords(String filename) throws FileNotFoundException{
    		File f = new File(filename);
    		Scanner in = new Scanner(f);
    		while (in.hasNext()){
    			String word = in.next().trim().toUpperCase();
    			if (word.charAt(word.length() - 1) == ','){
    				word = word.substring(0,word.length()-1);
    			}
    			if (word.length() <= data.length || word.length() <= data[0] .length){// if words are too long they are denied
    				words.add(word);
    			}
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
    			if (data[i][e] != '_') {out += data[i][e];}
			else {out += " ";}
    			if (e != data[i].length - 1) {out += " ";}
    		}
    		out += "|\n";
    	}
    	out+= getAddedWords() + " (" + addedWords.size() + " words)" + " (seed: " + seed + ")";
    	return out;
    }
    private boolean addWord( int row, int col, String word, int rin, int cin){
		for (int r = row, c = col, e = 0; e < word.length(); r+=rin, c+=cin, e++){
    		if (data[r][c] != word.charAt(e) && data[r][c] != '_') { 
    			return false;}
    	}
    	for (int r = row, c = col, e = 0; e < word.length(); r+=rin, c+=cin, e++){
    		if (data[r][c] == ('_')) {data[r][c] = word.charAt(e);}
    	}

    	return true;
    }
    private boolean addAllWords(){
    	int s = words.size();
    	for (int w = 0; w < s; w++){
	    	String rWord = words.get(rand.nextInt(words.size()));
            boolean[][] directionTried = new boolean[3][3];
            directionTried[1][1] = true;
            for (int d = 0; d < 8; d++){
    	    	int rXin = 0, rYin = 0;
    	    	while (directionTried[rXin+1][rYin+1]){
    		    		rXin = rand.nextInt(3) - 1;
    		    		rYin = rand.nextInt(3) - 1;
    		    	}
                directionTried[rXin+1][rYin+1] = true;
    		    boolean[][] ps = new boolean[data.length][data[0].length];
    		    int wl = rWord.length();
    		    int rt, yt;
    	        if (rXin != -1) {rt = ps.length - (wl);}else {rt = (wl-1);}
    	        if (rYin != -1) {yt = ps[0].length - (wl);}else {yt = (wl-1);}
    	        int totalP = ps.length * ps[0].length;
    		    for (int x = 0; x < ps.length; x++){
    		    	for (int y = 0; y < ps[0].length; y++){
    		    		if (x*rXin > rt*rXin || y*rYin > yt*rYin) {
    		    			ps[x][y] = true;
    		    			--totalP;
    		    		}
    		    		if ((data[x][y] != rWord.charAt(0) && data[x][y] != '_') && !ps[x][y]) {
    		    			ps[x][y] = true;
    		    			--totalP;
    		    		}
    		    	}
    		    }
    	    	for (int i = 0; i < totalP; i++){
    		    	int rX = rand.nextInt(data.length); 
    		    	int rY = rand.nextInt(data[0].length);
    		    	while (ps[rX][rY]){
    		    		rX = rand.nextInt(data.length); 
    		    		rY = rand.nextInt(data[0].length);
    		    	}
    		    	ps[rX][rY] = true;
    		    	if (addWord(rX,rY,rWord,rXin, rYin)){
    		    		words.remove(rWord);
        				addedWords.add(rWord);
        				i = totalP;
                        d = 8;
    		    	}
    		    	if (i == totalP - 1 && d == 7) {words.remove(rWord);}
    			}
    	    }
        }
    	return true;
    }
    private boolean fillIn(){
    	for(int x = 0; x < data.length; x++){
    		for (int y = 0; y < data[0].length; y++){
    			if (data[x][y] == '_') data[x][y] = (char) (rand.nextInt(26) + 65);
    		}
    	}
    	return true;
	}
	public static void main(String[] args){
	  	try {
	  		int rows = Integer.parseInt(args[0]);
	  		int cols = Integer.parseInt(args[1]);
	  		String file = args[2];
	  		long s = (long) (Math.random() * 10000);
	  		boolean k = false;
	  		WordSearch ws;
	  		if (args.length >= 4){s = Long.parseLong(args[3]);}
	  		if (s < 0 || s > 10000) {throw new IllegalArgumentException();}
			if (args.length >= 5){k = args[4].equals("key");}
	  		ws = new WordSearch(rows,cols,file,s,k);
	  		System.out.println(ws);
	  	}
	  	catch (Exception e){
            //e.printStackTrace();
	  		System.out.println("Wrong Input!!! \n************************\nFirst enter  a valid number of rows you would like in your word search.");
	  		System.out.println("Next a valid number of columns, and then an existing .txt file containing the words you wish to find.");
	  		System.out.println("If you have a valid seed (in the range 0 to 10000) enter it after the column. if you wish to see the answers type \"key\" last. Each input should be seperated by spaces");
	  		System.exit(1);
	  	}
  }
}
