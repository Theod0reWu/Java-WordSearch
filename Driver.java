public class Driver{
	public static void main(String[] args){
		WordSearch one = new WordSearch(10,10);
		System.out.println(one);
		System.out.println(one.addWordHorizontal("hello",0,4));
		System.out.println(one);
		System.out.println(one.addWordHorizontal("oS",0,7));
		System.out.println(one);
		System.out.println(one.addWordHorizontal("oS",0,8));
		System.out.println(one);
		System.out.println("*******************************");
		System.out.println(one.addWordVertical("elloHay",0,5));
		System.out.println(one);
		System.out.println(one.addWordVertical("LosCabos", 0, 7));
		System.out.println(one);
		
	}
}
