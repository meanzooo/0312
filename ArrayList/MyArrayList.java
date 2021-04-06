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
		
		list.add(3,100); //원래 3의 자리에 있던 것은 뒤로 한 칸 밀린다.
		
		System.out.println(list);
		
//		list.remove(3);
		list.remove(Integer.valueOf(100)); //100을 숫자형으로 변환 후 제거
		System.out.println(list);
	}
}
