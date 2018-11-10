public class WordSearch{
    private char[][]data;

    public WordSearch(int rows,int cols){
    	data = new char[rows][cols];
    	clear();
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
}
