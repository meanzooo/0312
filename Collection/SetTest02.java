package Collection;

import java.util.*;

public class SetTest02 {
	public static void main(String[] args) {
		test2();
	}
	
	public static void test2() {
		//LinkedHashSet
		List<Integer> list = Arrays.asList(10,4,532,7,5,3,2,1);
		Set<Integer> set1 = new LinkedHashSet<>();
		set1.addAll(list);
		Iterator<Integer> iter = set1.iterator();
		//입력된 순대로 나오는지 확인
		while(iter.hasNext()) {
			System.out.print(iter.next()+" ");
		}
		
		System.out.println();
		
		//TreeSet
		List<Integer> list2 = Arrays.asList(10,4,532,7,5,3,2,1);
		Set<Integer> set2 = new TreeSet<>();
		set2.addAll(list2);
		Iterator<Integer> iter2 = set2.iterator();
		//값의 순서대로 나오는지 확인
		while(iter2.hasNext()) {
			System.out.print(iter2.next()+" ");
		}
		
	}
}