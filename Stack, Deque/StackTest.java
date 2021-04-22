import java.util.*;

public class StackTest {
	public static void main(String[] args) {
		/*
		 * stack : LIFO(Last In First Out)
		 * 
		 * Generic : Ŭ���� ���ο��� ����� ������ Ÿ���� ������ ����
		 * 			 �ƴϰ�, �� Ŭ������ ��ü�� ������ �� ������ Ÿ����
		 * 			 ������ �� �ֵ���, ����� ������ Ÿ���� �Ķ���ͷ�
		 * 			 �޾Ƽ� ��ü�� �����ϴ� �� (Type parameter)
		 * ���� generic�� primitive Ÿ���� �ȵȴ�(String...)
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
