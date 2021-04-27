package Collection;

import java.util.*;

public class PQTest {
	public static void main(String[] args) {
		test1();
	}
	
	public static void test1() {
		Queue<String> q = new PriorityQueue<>();
		q.offer("PineApple"); // = q.add()
		q.offer("Apple");
		q.offer("Grape");
		q.offer("Lime");
		q.offer("Coconut");
		
		//¿³º¸±â. ÀÎÃâÀº ¾Æ´Ô.
		System.out.print(q.peek()+" "); // = q.element()
		System.out.print(q.peek()+" ");
		System.out.print(q.peek()); // ´Ù °°Àº °Å ³ª¿È
		System.out.println();
		
		while(q.size()>0) {
			System.out.println(q.poll()); // = q.remove()
		}
	}
}