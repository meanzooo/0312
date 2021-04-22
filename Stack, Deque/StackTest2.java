import java.util.*;

public class StackTest2 {
	public static void main(String[] args) {
		String str = "apple banana carrot grape melon";
		
//		String s = str.substring(0,"apple".length());
//		System.out.println(s);
//		근데 이러면 내가 다 알아야되자너 배열의 값들을??
//		그래서 공백 문자를 기준으로 하나씩 자르려고 한다.
//		StringTokennize가 그 역할을 해준다.
		StringTokenizer st = new StringTokenizer(str, " ");
		
		// 토큰 출력
		String s = st.nextToken();
		System.out.println(s);
		s = st.nextToken();
		System.out.println(s);
		
		System.out.println("token수: " + st.countTokens());
		
		// 토큰을 다 찍어보는 방법
		// 1) 토큰의 수 만큼 for문을 돈다
		// 2) 토큰 있냥? 있음 줄래?
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			System.out.println("[" + token + "]");
		}
	}
}
