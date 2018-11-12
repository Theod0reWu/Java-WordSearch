public class Driver{
	public static String toString(boolean[][] data){
    	String out = "";
    	for (int i = 0; i < data.length; i++){
    		out += "|";
    		for (int e = 0; e < data[i].length; e++){
    			out += data[i][e];
    			if (e != data[i].length - 1) {out += " ";}
    		}
    		out += "|\n";
    	}
    	return out;
    }
	public static void main(String[] args){
		WordSearch one = new WordSearch(24,24, "words.txt");
		System.out.println("Initializing a 11 by 11, WordSearch");
		System.out.println("Should print out a 10 X 10 array of underscores: ");
		System.out.println(one);
		System.out.println("*******************************");
		
  		WordSearch two = new WordSearch(5,5,"words.txt");
  		System.out.println(two);

  		//System.out.println(two.addWord(4,0,"hello",-1,1));
  		//System.out.println(two);

  }
}
	
