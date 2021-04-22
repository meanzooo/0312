import java.util.*;

public class StackTest {
	public static void main(String[] args) {
		/*
		 * stack : LIFO(Last In First Out)
		 * 
		 * Generic : 클래스 내부에서 사용할 데이터 타입이 정해진 것이
		 * 			 아니고, 그 클래스의 객체를 생성할 때 데이터 타입을
		 * 			 결정할 수 있도록, 사용할 데이터 타입을 파라미터로
		 * 			 받아서 객체를 생성하는 것 (Type parameter)
		 * 또한 generic은 primitive 타입은 안된다(String...)
		 */
		
		Stack<Integer> stack = new Stack<>();
		for (int i=0; i<10; i++) {
			stack.push(i+1);
		}
		System.out.println(stack);
		
		while(!stack.isEmpty()) {
			Integer val = stack.pop();
			System.out.print(val+" ");
		}
	}
}
