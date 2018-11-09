import java.util.Random;
public class WordSearchDriver{
  public static void main(String[] args){
  	WordSearch ws = new WordSearch(0,0);
  	long seed = -1;
  	try {
  		int rows = Integer.parseInt(args[0]);
  		int cols = Integer.parseInt(args[1]);
  		if (args.length > 2){
  			seed = Long.parseLong(args[2]);
  		}
  		ws = new WordSearch(rows,cols);
  	}
  	catch (Exception e){
  		System.out.println("\nWrong Input!!! \nFirst enter the number of rows you would like in your word search");
  		System.out.println("Then the number of columns each input seperated by spaces");
  		System.out.println("If you have a seed enter it after the column");
  		System.exit(1);
  	}
  	System.out.println(ws);

  	// the letters are 97: a through 122: z
  	// caps are 65: A through 90: Z
  	// str.toUpperCase();
  	if (seed == -1) {seed = System.currentTimeMillis();}
  	System.out.println("seed: "+seed);
  	Random rand = new Random(seed);
  	//rand.setSeed(seed);
  	System.out.println(rand.nextInt(10));
  }
}
