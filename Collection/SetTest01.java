package Collection;

import java.util.*;

public class SetTest01 {
	//종류로는 HashSet, TreeSet, LinkedHashSet이 있음
	//LinkedHashSet은 입력순대로, TreeSet은 값에 따라 정렬된 순대로 인출됨.
	public static void main(String[] args) {
		test1();
	}
	public static void test1() {
		/*
		 * set1 = {2,3,4,5,6,8,9,10}
		 * set2 = {1,3,5,7,9}
		 * 합(addAll), 차(removeAll), 교(retainAll)집합
		 */
		
		// Generic 클래스 객체를 생성 할 때 Type 파라미터는
		// 프리미티브 타입(ex:int)을 줄 수 X, 객체 타입만 가능
		Set<Integer> set1 = new HashSet<>();
		// 배열을 Colletion 객체로 만들어주는 메소드가 있다.
		List<Integer> list = Arrays.asList(2,3,4,5,6,8,9,10);
		set1.addAll(list);
		
		Set<Integer> set2 = new HashSet<>();
		List<Integer> list2 = Arrays.asList(1,3,5,7,9);
		set2.addAll(list2);
		
		System.out.println("Set1:" + set1);
		System.out.println("Set2:" + set2);
		
		//합집합
		Set<Integer> unionSet = new HashSet<>(set1);
		unionSet.addAll(set2); //원본을 남기기 위해
		System.out.println("둘의 합집합:" + set1);
		
		//교집합
		Set<Integer> intersectionSet = new HashSet<>(set1);
		intersectionSet.retainAll(set2);
		System.out.println("둘의 교집합:"+intersectionSet);
		
		//차집합
		Set<Integer> diffSet = new HashSet<>(set1);
		diffSet.removeAll(set2);
		System.out.println("둘의 차집합:"+diffSet);
		
		//Iterator
		System.out.println();
//		for (Integer val : diffSet) {
//			//val 값을 적절히 처리해a
//			System.out.print(val + " ");
//		}
		Iterator<Integer> iter = diffSet.iterator(); //diffSet이 Int니까 <>안에 Integer
		while (iter.hasNext()) { //있으면 true. 있는 동안 next에서 가져오고...
			Integer val = iter.next();
			//val 적절히 처리
			System.out.print(val + " ");
		}
	}
	
}