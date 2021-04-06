package ArrayList;
//뒤에서부터 정렬

public class MyArrayListToGeneric<T> {
	
	private Object[] arr;
	private int capacity = 10;
	private int size = 0;
		
	public MyArrayListToGeneric() {
		arr = new Object[capacity]; // 그냥 new T 하면 오류가 뜬다... 때문에 이땐 일단은 타입을 Object로 준다
	}
	
	private void increaseCapacity() {
		capacity = capacity+capacity/2;
		Object[] tmp = new Integer[capacity];
		for (int i=0; i<size; i++) {
			tmp[i] = arr[i];
		}
		arr = tmp;
	}
	
	public void add(T value) {
		if (size>=capacity) {
			increaseCapacity();
		}
		arr[size++] = value;
	}
		
	public int size() {
		return size;
	}
	
	public T get(int idx) {
		return (T)arr[idx];
	}
	
	public void add(int idx, Integer value) {
		//용량이 이미 꽉 차있으면 용량을 50% 늘리고 아래 코드를 실행한다 -> 코드가 중복되니 따로 메소드를 만든다
		if (size == capacity) {
			increaseCapacity();
		}
		
		//맨 뒤에 있는 원소부터 오른쪽으로 한 칸씩 민다
		for (int i = size-1; i>=idx; i--) {
			arr[i+1] = arr[i];
		}
		
		//idx 자리에 value를 넣는다
		arr[idx] = value;
		size++;
	}
	
//	사이즈 보고 접근하기 때문에 remove의 필요는 없당
//	public void remove() {
//		if (size>0) size--;
//	}
	
	@Override
	public String toString() {
		if (size==0) return "[]";
		String result = "[";
		for (int i=0; i<size-1; i++) {
			result += arr[i]+",";
		}
		result += arr[size-1];
		result += "]";
		return result;
	}
	
	public static void main(String[] args) {
		
		MyArrayListToGeneric<String> list1 = new MyArrayListToGeneric<>();
		list1.add("홍길동");
		list1.add("강감찬");
		System.out.println(list1);
	}
}