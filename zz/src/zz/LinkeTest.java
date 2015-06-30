package zz;

import java.util.LinkedHashSet;
import java.util.LinkedList;

public class LinkeTest {
	public static void main(String[] args) {
		LinkedList linke=new LinkedList();
		LinkedHashSet lhs=new LinkedHashSet();
		linke.add("A");
		linke.add("b");
		lhs.add("zzzzzzzz");
		lhs.add("zzz");
		lhs.add("zzzz");
		lhs.add("zzzzz");
		linke.add("c");

			System.out.println(lhs);

		System.out.println(linke.size());
		for(int i=0;i<linke.size()+2;i++)
			System.out.println(linke.poll());
	}

}
