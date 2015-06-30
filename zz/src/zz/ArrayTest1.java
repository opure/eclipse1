package zz;

public class ArrayTest1 {
	public static void main(String[] args) {
		ArrayTest at=new ArrayTest(100);
		at.insert(1223);
		at.insert(1223);
		at.insert(1211);
		at.insert(122);
		at.insert(123);
		at.insert(122);
		at.insert(124);
		at.insert(13);
		at.insert(1);
		at.delete(13);
		at.display();
		at.insert1(12555);
		if(at.find(13))
		  System.out.println("ур╣╫ак");
	}

}
