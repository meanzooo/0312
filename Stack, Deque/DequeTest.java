import java.util.*;

public class DequeTest {
	public static void main(String[] args) {
		/*
		 * Deque는 인터페이스
		 * 이놈을 구현한 구현 클래스는 ArrayDeque
		 * 인터페이스의 인스턴스 생성은 X (new ...)
		 * 후손들은 다 담을 수 있음
		 */

		Queue<Integer> queue = new LinkedList<>();
		
		/*
		 * Queue : a, b 메소드 있다면
		 * ArrayDeque : a,b,c,d,e 메소드 가능
		 * 
		 * q.a(), q.b()
		 * q.c() -> X
		 */
		
		// 1. 큐에 임의의 수 10개를 넣고
		// 2. 안에 어떤 순서로 들어가 있는지 보고
		// 3. 하나씩 인출해보자 (FIFO순으로 나오는지 확인)
		for (int i=0; i<10; i++) {
			queue.add(i+1);
		}
		System.out.println(queue);
		
		// 큐에 원소가 있으면 인출하자
		while (!queue.isEmpty()) {
		// queue.size() > 0 , queue.isEmpty()==false
			Integer val = queue.poll(); //큐가 -1 돼감
			System.out.print(val+" ");
		}
		System.out.println("\n큐의 상태...");
		System.out.println(queue);
	}
}
