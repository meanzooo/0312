package Collection;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.*;

public class SimpleDicUseDB extends JPanel implements ActionListener{
	/* 입력필드, 버튼 2개 */
	private JTextField inputField = new JTextField(30);
	private JButton searchBtn = new JButton("검색");
	private JButton addBtn = new JButton("추가"); 
	
	private static final String JDBC_CLASS_NAME 
							= "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL =
			"jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_USER = "insa";
	private static final String DB_PASSWORD = "insa";
	
	// 한영사전 : 한글단어와 대응되는 영어단어의 쌍을 저장
	private Map<String, String> dict = new HashMap<>();
	private static final String DIC_FILE_NAME="resources/dict.props";
	
	public SimpleDicUseDB() {
		this.add(inputField);
		this.add(searchBtn);
		this.add(addBtn);
		searchBtn.addActionListener(this);
		addBtn.addActionListener(this);
		
		this.setPreferredSize(new Dimension(600, 50));
//		buildDictionaryFromFile();
		
		try {
			// JDBC 드라이버를 메모리에 적재하기.
			// JDBC 드라이버 클래스 이름은 DBMS 마다 다르다. 
			Class.forName(JDBC_CLASS_NAME);
			buildDictionaryFromDB();
		} catch(Exception e) {
			System.out.println(e.getMessage());
			//System.exit(1);
		}
	}
	
	private void buildDictionaryFromDB() {
		// 데이터베이스에 연결하기.
		try (Connection con = 
			DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			String sql = "select * from dict";
			// PreparedStatement 객체는 실행준비가 완료된 SQL 객체.
			PreparedStatement pstmt 
					= con.prepareStatement(sql);
			// Insert, Delete, Update 문의 실행은
			// executeUpdate() 메서드를 호출하고
			// select 문의 실행은 executeQuery() 메서드를 호출한다. 
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				//rs.getString(1);
				String key = rs.getString("hword");
				String value = rs.getString("eword");
				dict.put(key, value);
				dict.put(value, key);
				System.out.println(key + " : " + value);
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void buildDictionaryFromFile() {
		Properties props = new Properties();
		try(FileReader fReader = new FileReader(DIC_FILE_NAME)) {
			props.load(fReader);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		Set<Object> set = props.keySet();
		for (Object key : set) {
			System.out.println("key:" +key);
			Object value = props.get(key);
			dict.put((String)key, (String)value);
			dict.put((String)value, (String)key);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String key = inputField.getText();
		if (key.trim().length() == 0) return;
		System.out.println("["+key+"]");
		
		// search button or add button detection.
		if (e.getSource() == searchBtn) {	// 검색 버튼이 클릭된 경우
			/*
			 * inputField에 입력된 단어를 추출
			 * dict 맵 객체에서 그 단어에 대응되는 영어단어를 찾아 디스플레이.
			 */
			String value = dict.get(key);
			if (value == null) { // 그 key에 대응되는 영단어가 없는 경우.
				// 없다고 디스플레이
				JOptionPane.showMessageDialog(this, "단어를 찾을 수 없습니다", 
									key, JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, value, key, 
									JOptionPane.INFORMATION_MESSAGE);
			}

		} else if (e.getSource() == addBtn) { // 추가 버튼이 클릭된 경우
			/*
			 * inputField에 입력된 단어를 추출
			 * 그 단어에 대응되는 영어단어를 입력받고 
			 * dict 맵 객체에 <한글단어, 영어단어>의 쌍을 추가 
			 */
			String value = 
				JOptionPane.showInputDialog(this, key + "에 대응되는 영어단어를 입력하세요");
			if (value == null || value.trim().length() == 0) return;
			dict.put(key, value);
			dict.put(value, key);
//			addWordToFile(key, value);
			addWordToDB(key, value);
			JOptionPane.showMessageDialog(this, "영어단어가 추가되었습니다", "성공", 
					JOptionPane.INFORMATION_MESSAGE);
		}
		inputField.requestFocus();
	}
	
	private void addWordToDB(String key, String value) {
		/* 드라이버를 메모리에 적재한다. <- 메모리 적재는 한 번만 하면 되고, 
		 * 이미 생성자에서 했다. 
		 * DB에 연결해서 Connection 객체를 반환받는다. 
		 * Connection 객체에게 PreparedStatement 객체를 요청한다.
		 * PreparedStatement 객체의 executeUpdate() 메서드를
		 * 호출해서 DB에 저장한다.   
		 * 
		 */
		try(Connection con = 
			DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			// place holder
			String sql = "insert into dict values(?, ?)";
			/* 
			 * 실행 준비
			 *  1. 문법검사
			 *  2. 정당성검사(테이블, 칼럼 등이 실제로 있는지, 있다면 이 사용자가 레코드를 삽입할 권한이 있는지 등...
			 *  3. 실행계획을 세운다. (execution plan)
			 */
			PreparedStatement pstmt = con.prepareStatement(sql);
			// ? 자리의 칼럼 데이터 타입에 따라
			// 적절한 setXXX() 메서드를 호출해야 한다. 
			// 예를 들어, 칼럼이 char 또는 varchar 타입이면 setString()
			// 칼럼이 TimeStamp 타입이면 setDate(), setTimestamp(),
			// 칼럼이 int 타입이면 setInt()... 
			pstmt.setString(1, key);
			pstmt.setString(2, value);
			// Insert, Delete, Update 문을 실행할 때는 executeUpdate() 메서드를 호출한다.
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void addWordToFile(String key, String value) {
		try(FileWriter fWriter = new FileWriter(DIC_FILE_NAME, true)) {
			String str = key+"="+value + "\n";
			fWriter.write(str);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new SimpleDicUseDB());
		frame.setTitle("나의 한영사전");
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
