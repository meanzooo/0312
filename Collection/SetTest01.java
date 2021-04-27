package Collection;

import java.util.*;

public class SetTest01 {
	//�����δ� HashSet, TreeSet, LinkedHashSet�� ����
	//LinkedHashSet�� �Է¼����, TreeSet�� ���� ���� ���ĵ� ����� �����.
	public static void main(String[] args) {
		test1();
	}
	public static void test1() {
		/*
		 * set1 = {2,3,4,5,6,8,9,10}
		 * set2 = {1,3,5,7,9}
		 * ��(addAll), ��(removeAll), ��(retainAll)����
		 */
		
		// Generic Ŭ���� ��ü�� ���� �� �� Type �Ķ���ʹ�
		// ������Ƽ�� Ÿ��(ex:int)�� �� �� X, ��ü Ÿ�Ը� ����
		Set<Integer> set1 = new HashSet<>();
		// �迭�� Colletion ��ü�� ������ִ� �޼ҵ尡 �ִ�.
		List<Integer> list = Arrays.asList(2,3,4,5,6,8,9,10);
		set1.addAll(list);
		
		Set<Integer> set2 = new HashSet<>();
		List<Integer> list2 = Arrays.asList(1,3,5,7,9);
		set2.addAll(list2);
		
		System.out.println("Set1:" + set1);
		System.out.println("Set2:" + set2);
		
		//������
		Set<Integer> unionSet = new HashSet<>(set1);
		unionSet.addAll(set2); //������ ����� ����
		System.out.println("���� ������:" + set1);
		
		//������
		Set<Integer> intersectionSet = new HashSet<>(set1);
		intersectionSet.retainAll(set2);
		System.out.println("���� ������:"+intersectionSet);
		
		//������
		Set<Integer> diffSet = new HashSet<>(set1);
		diffSet.removeAll(set2);
		System.out.println("���� ������:"+diffSet);
		
		//Iterator
		System.out.println();
//		for (Integer val : diffSet) {
//			//val ���� ������ ó����a
//			System.out.print(val + " ");
//		}
		Iterator<Integer> iter = diffSet.iterator(); //diffSet�� Int�ϱ� <>�ȿ� Integer
		while (iter.hasNext()) { //������ true. �ִ� ���� next���� ��������...
			Integer val = iter.next();
			//val ������ ó��
			System.out.print(val + " ");
		}
	}
	
}