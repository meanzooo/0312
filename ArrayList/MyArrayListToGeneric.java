package ArrayList;
//�ڿ������� ����

public class MyArrayListToGeneric<T> {
	
	private Object[] arr;
	private int capacity = 10;
	private int size = 0;
		
	public MyArrayListToGeneric() {
		arr = new Object[capacity]; // �׳� new T �ϸ� ������ ���... ������ �̶� �ϴ��� Ÿ���� Object�� �ش�
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
		//�뷮�� �̹� �� �������� �뷮�� 50% �ø��� �Ʒ� �ڵ带 �����Ѵ� -> �ڵ尡 �ߺ��Ǵ� ���� �޼ҵ带 �����
		if (size == capacity) {
			increaseCapacity();
		}
		
		//�� �ڿ� �ִ� ���Һ��� ���������� �� ĭ�� �δ�
		for (int i = size-1; i>=idx; i--) {
			arr[i+1] = arr[i];
		}
		
		//idx �ڸ��� value�� �ִ´�
		arr[idx] = value;
		size++;
	}
	
//	������ ���� �����ϱ� ������ remove�� �ʿ�� ����
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
		list1.add("ȫ�浿");
		list1.add("������");
		System.out.println(list1);
	}
}