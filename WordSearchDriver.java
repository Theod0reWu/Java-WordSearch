public class WordSearchDriver{
  public static void main(String[] args){
  	WordSearch ws = new WordSearch(0,0);
  	try {
  		int rows = Integer.parseInt(args[0]);
  		int cols = Integer.parseInt(args[1]);
  		ws = new WordSearch(rows,cols);
  	}
  	catch (Exception e){
  		System.out.println("\nWrong Input!!! \nFirst enter the number of rows you would like in your word search");
  		System.out.println("Then the number of columns, seperated by spaces");
  		System.exit(1);
  	}
  	System.out.println(ws);

  	// the letters are 97: a through 122: z
  	// caps are 65: A through 90: Z
  	// str.toUpperCase();
  	

  }
}
