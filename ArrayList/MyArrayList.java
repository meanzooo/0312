package ArrayList;

import java.util.ArrayList;

public class MyArrayList {
	
	public static void main(String[] args) {
		
		ArrayList<Integer> list = new ArrayList<>();
		for (int i=0; i<10; i++) {
			list.add(i);
		}
		
		/* for (int i=0; i<list.size(); i++) {
		 *    System.out.println(list.get(i));
		 *   }
		 */
		
		System.out.println(list);
		
		list.add(3,100); //���� 3�� �ڸ��� �ִ� ���� �ڷ� �� ĭ �и���.
		
		System.out.println(list);
		
//		list.remove(3);
		list.remove(Integer.valueOf(100)); //100�� ���������� ��ȯ �� ����
		System.out.println(list);
	}
}
