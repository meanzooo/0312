package Colletion;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class SimpleDicUseProps2 extends JPanel implements ActionListener{
	/*
	 * 단어 입력받을 수 있는 JTextField
	 * 검색 버튼
	 * 추가 버튼
	 * 단어장 구현을 위한 자료구조로 Map 객체
	 */
	
	private JTextField inputField = new JTextField(30);
	private JButton searchBtn = new JButton("검색");
	private JButton addBtn = new JButton("추가");
	
	//Map 객체를 단어장 구현에 사용할 것임.
	//<key,value>에서 key는 한글 단어, value는 대응되는 영어단어로 저장.
	private Map<String, String> dict = new HashMap<>();
	private static final String DIC_FILE_NAME = "dict.props";
	
	public SimpleDicUseProps2() {
		//Panel의 기본 레이아웃은 FlowLayout.
		this.add(inputField);
		this.add(searchBtn);
		this.add(addBtn);
		
		//searchBtn, addBtn에 클릭 이벤트가 발생했을 때 처리할 리스너를 지정.
		searchBtn.addActionListener(this);
		addBtn.addActionListener(this);
		
		this.setPreferredSize(new Dimension(600,50));
		
		//파일에 key=value 형태로 저장된 엔트리들을 읽어서, dict를 구성하자.
		buildDictionaryFromFile();
	}
	
	private void buildDictionaryFromFile() {
		// Properties : 일종의 Map인데 key, value의 타입이 각각 String, String으로 고정된 일종의 Map이다. 
		Properties props = new Properties();
//		props.put("사과", "apple");
//		System.out.println("사과"); 이렇게 할 수도 있지만
		
		//파일에서 읽어서 props 객체의 <key, value> 쌍을 구성할 수 있다.
//		FileReader fReader = new FileReader(DIC_FILE_NAME);
//		props.load(fReader);
		try (FileReader fReader = new FileReader(DIC_FILE_NAME)) {
			props.load(fReader);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		Set<Object> set = props.keySet();
		for (Object obj : set) {
			dict.put((String)obj,(String)props.get(obj));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String key = inputField.getText();
		if (key.trim().length()==0) {
			return;
		}
		if (e.getSource()==searchBtn) {
		/*
		 * 입력된 단어를 추출하고
		 * 그 단어를 key값으로 가지는 대응되는 맵 엔트리가 있는지 검사 -> dict.get(단어);
		 * 		그 단어에 대응되는 값이 있으면 JOptionPane.showMessageDialog()	메서드를 호출해서 그 대응되는 값을 보여준다.
		 * 		없으면(=null이면) JOptionPane.showMessageDialog() 메서드를 호출해서 '단어를 찾을 수 없습니다' 라고 출력해준다.
		 * inputField를 빈 문자열로 설정
		 */
		System.out.println("["+key+"]");
		String value = dict.get(key);
		if (value != null) { //대응되는 단어가 있는 경우
			JOptionPane.showMessageDialog(this,value,key,JOptionPane.INFORMATION_MESSAGE);
		} else { //대응되는 단어가 없는 경우
			JOptionPane.showMessageDialog(this, "단어를 찾을 수 없습니다.", key,
											JOptionPane.ERROR_MESSAGE);
				}

		} else if (e.getSource()==addBtn) {
			/*
			 * 입력된 단어를 추출
			 * String value = JOptionPane.showInputDialog();
			 * 메서드를 호출해서 추가할 영어 단어를 입력받는다.
			 * dict.put(입력필드에 입력된 단어, inputDialog에 입력된 단어);
			 * inputField를 빈 문자열로 설정
			 */
			String value = JOptionPane.showInputDialog(this, key + "에 대응되는 영어단어를 입력하세요.");
			if (value.trim().length() == 0) return;
			dict.put(key, value);
			addWordToFile(key,value);
			
			//파일에도 key=value의 쌍으로 기록해두자
			
			
			JOptionPane.showMessageDialog(this, value + " 영어 단어가 추가되었습니다.", key,
											JOptionPane.INFORMATION_MESSAGE);
			}
//		inputField.setText("");
		}
	
	private void addWordToFile(String key, String value) {
		try(FileWriter fwriter = new FileWriter(DIC_FILE_NAME,true)) {
			fwriter.write("\n" + key + "=" + value);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		SimpleDicUseProps2 dictPanel = new SimpleDicUseProps2();
		frame.add(dictPanel);
		frame.setTitle("나의 한영 사전");
		
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}