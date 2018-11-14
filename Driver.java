public class Driver{
	public static void main(String[] args){
		try {
	  		int rows = Integer.parseInt(args[0]);
	  		int cols = Integer.parseInt(args[1]);
	  		String file = args[2];
	  		WordSearch ws;
	  		if (args.length == 4){
	  			ws = new WordSearch(rows,cols,file,Long.parseLong(args[3]));
	  		}
			else if (args.length == 5){
				ws = new WordSearch(rows,cols,file,Long.parseLong(args[3]),args[4].equals("key"));
			}
	  		else{
	  		ws = new WordSearch(rows,cols,file);
	  		}
	  		System.out.println(ws);
	  	}
	  	catch (Exception e){
	  		//e.printStackTrace();
	  		System.out.println("Wrong Input!!! \n************************\nFirst enter the number of rows you would like in your word search.");
	  		System.out.println("Next the number of columns, then the .txt file containing the words yo wish to find.");
	  		System.out.println("If you have a seed enter it after the column. Each input should be seperated by spaces");
	  		System.exit(1);
	  	}

  }
}
	
