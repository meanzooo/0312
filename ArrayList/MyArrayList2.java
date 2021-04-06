package ArrayList;

public class MyArrayList2 {
	
	private Integer[] arr;
	private int capacity = 10;
	private int size = 0; //���� ���� ���� ���� ����
		
	public MyArrayList2() {
		arr = new Integer[capacity];
	}
		
	public void add(Integer value) {
		//���� �뷮���� �߰��Ǵ� ���� ������ �� ������
		if (size>=capacity) {
			//���� �� ��ġ�� else���� �־���(size<capacity ������)
			//������ �Ұ��ϸ� arr�迭�� ũ�⸦ �÷��� �Ѵ�
				capacity = capacity+capacity/2;
//				arr = new Integer[capacity]; //�����ϸ� ���� �����Ͱ� �����
				Integer[] tmp = new Integer[capacity];
				//���� ���� tmp�� ����
				for (int i=0; i<size; i++) {
					tmp[i] = arr[i];
				}
				//arr�� �뷮�� �þ �迭�� ����Ű���� �Ѵ�
				arr = tmp;
//				arr [size++] = value; //�ٵ� ���� if�� ���� ������ڳ�... ������ ���� �� -> �׸� if���� �ʿ䰡 ���� -> (size<capacity)�� ������ ���� �� else���� ����
			}
		arr[size] = value;
		size = size+1;
	}
		
	public int size() { //���� �� ���� ���ڸ� �־�����
		return size;
	}
	
	public Integer get(int idx) { //idx�� � ���ڰ� ��� �ִ���
		return arr[idx];
	}
	
	@Override
	public String toString() {
		if (size==0) return "[]"; //�� �����ָ� ����� ���� �� ������ ����
		String result = "[";
		for (int i=0; i<size-1; i++) {
			result += arr[i]+",";    
			//if ((i+1)%10 == 0) result += "\n";
		}
		result += arr[size-1];
		result += "]";
		return result;
	}
	
	public static void main(String[] args) {
		//ArrayList<Integer> list = new ArrayList<>();
		MyArrayList2 list = new MyArrayList2();
		
		for (int i=0; i<10; i++) {
			list.add(i);
		}
		
		System.out.print(list); // toString�� override �ؼ� �迭�� ����� ����
	}
}