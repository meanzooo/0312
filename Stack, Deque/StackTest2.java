import java.util.*;

public class StackTest2 {
	public static void main(String[] args) {
		String str = "apple banana carrot grape melon";
		
//		String s = str.substring(0,"apple".length());
//		System.out.println(s);
//		�ٵ� �̷��� ���� �� �˾ƾߵ��ڳ� �迭�� ������??
//		�׷��� ���� ���ڸ� �������� �ϳ��� �ڸ����� �Ѵ�.
//		StringTokennize�� �� ������ ���ش�.
		StringTokenizer st = new StringTokenizer(str, " ");
		
		// ��ū ���
		String s = st.nextToken();
		System.out.println(s);
		s = st.nextToken();
		System.out.println(s);
		
		System.out.println("token��: " + st.countTokens());
		
		// ��ū�� �� ���� ���
		// 1) ��ū�� �� ��ŭ for���� ����
		// 2) ��ū �ֳ�? ���� �ٷ�?
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			System.out.println("[" + token + "]");
		}
	}
}
