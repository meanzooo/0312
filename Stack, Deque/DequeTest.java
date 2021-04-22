import java.util.*;

public class DequeTest {
	public static void main(String[] args) {
		/*
		 * Deque�� �������̽�
		 * �̳��� ������ ���� Ŭ������ ArrayDeque
		 * �������̽��� �ν��Ͻ� ������ X (new ...)
		 * �ļյ��� �� ���� �� ����
		 */

		Queue<Integer> queue = new LinkedList<>();
		
		/*
		 * Queue : a, b �޼ҵ� �ִٸ�
		 * ArrayDeque : a,b,c,d,e �޼ҵ� ����
		 * 
		 * q.a(), q.b()
		 * q.c() -> X
		 */
		
		// 1. ť�� ������ �� 10���� �ְ�
		// 2. �ȿ� � ������ �� �ִ��� ����
		// 3. �ϳ��� �����غ��� (FIFO������ �������� Ȯ��)
		for (int i=0; i<10; i++) {
			queue.add(i+1);
		}
		System.out.println(queue);
		
		// ť�� ���Ұ� ������ ��������
		while (!queue.isEmpty()) {
		// queue.size() > 0 , queue.isEmpty()==false
			Integer val = queue.poll(); //ť�� -1 �Ű�
			System.out.print(val+" ");
		}
		System.out.println("\nť�� ����...");
		System.out.println(queue);
	}
}
