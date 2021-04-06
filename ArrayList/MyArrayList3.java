package ArrayList;
//�ڿ������� ����

public class MyArrayList3 {
	
	private Integer[] arr;
	private int capacity = 10; //�뷮��
	private int size = 0; //���Ұ� �� �� �ֳ�
		
	public MyArrayList3() {
		arr = new Integer[capacity];
	}
	
	private void increaseCapacity() {
		capacity = capacity+capacity/2;
		Integer[] tmp = new Integer[capacity];
		for (int i=0; i<size; i++) {
			tmp[i] = arr[i];
		}
		arr = tmp;
	}
	
	public void add(Integer value) {
		if (size>=capacity) {
			increaseCapacity();
		}
		arr[size++] = value;
	}
		
	public int size() {
		return size;
	}
	
	public Integer get(int idx) {
		return arr[idx];
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
	
	public void remove(int idx) {
		for (int i = idx+1; i<=size-1; i++) {
			arr[idx] = arr[i];
			idx++;
		}
		size--;
	}
	
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
		MyArrayList3 list = new MyArrayList3();
		
		for (int i=0; i<10; i++) {
			list.add(i);
		}
		System.out.print(list);
		list.remove(7);
		System.out.print(list);
	}
}