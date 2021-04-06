package ArrayList;

public class MyArrayList2 {
	
	private Integer[] arr;
	private int capacity = 10;
	private int size = 0; //내가 현재 넣은 값의 갯수
		
	public MyArrayList2() {
		arr = new Integer[capacity];
	}
		
	public void add(Integer value) {
		//현재 용량으로 추가되는 값을 수용할 수 있으면
		if (size>=capacity) {
			//원래 이 위치에 else문이 있었음(size<capacity 시절에)
			//수용이 불가하면 arr배열의 크기를 늘려야 한다
				capacity = capacity+capacity/2;
//				arr = new Integer[capacity]; //이케하면 안의 데이터가 사라짐
				Integer[] tmp = new Integer[capacity];
				//원래 값을 tmp로 복사
				for (int i=0; i<size; i++) {
					tmp[i] = arr[i];
				}
				//arr이 용량이 늘어난 배열을 가르키도록 한다
				arr = tmp;
//				arr [size++] = value; //근디 위에 if랑 같은 기능이자너... 밑으로 빼자 걍 -> 그면 if문이 필요가 없네 -> (size<capacity)의 조건을 변경 후 else문도 삭제
			}
		arr[size] = value;
		size = size+1;
	}
		
	public int size() { //내가 몇 개의 숫자를 넣었는지
		return size;
	}
	
	public Integer get(int idx) { //idx에 어떤 숫자가 들어 있는지
		return arr[idx];
	}
	
	@Override
	public String toString() {
		if (size==0) return "[]"; //얠 안해주면 사이즈가 없을 때 에러가 난다
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
		
		System.out.print(list); // toString을 override 해서 배열의 모습을 갖춤
	}
}