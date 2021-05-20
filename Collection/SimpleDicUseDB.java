package Collection;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.*;

public class SimpleDicUseDB extends JPanel implements ActionListener{
	/* �Է��ʵ�, ��ư 2�� */
	private JTextField inputField = new JTextField(30);
	private JButton searchBtn = new JButton("�˻�");
	private JButton addBtn = new JButton("�߰�"); 
	
	private static final String JDBC_CLASS_NAME 
							= "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL =
			"jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_USER = "insa";
	private static final String DB_PASSWORD = "insa";
	
	// �ѿ����� : �ѱ۴ܾ�� �����Ǵ� ����ܾ��� ���� ����
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
			// JDBC ����̹��� �޸𸮿� �����ϱ�.
			// JDBC ����̹� Ŭ���� �̸��� DBMS ���� �ٸ���. 
			Class.forName(JDBC_CLASS_NAME);
			buildDictionaryFromDB();
		} catch(Exception e) {
			System.out.println(e.getMessage());
			//System.exit(1);
		}
	}
	
	private void buildDictionaryFromDB() {
		// �����ͺ��̽��� �����ϱ�.
		try (Connection con = 
			DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			String sql = "select * from dict";
			// PreparedStatement ��ü�� �����غ� �Ϸ�� SQL ��ü.
			PreparedStatement pstmt 
					= con.prepareStatement(sql);
			// Insert, Delete, Update ���� ������
			// executeUpdate() �޼��带 ȣ���ϰ�
			// select ���� ������ executeQuery() �޼��带 ȣ���Ѵ�. 
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
		if (e.getSource() == searchBtn) {	// �˻� ��ư�� Ŭ���� ���
			/*
			 * inputField�� �Էµ� �ܾ ����
			 * dict �� ��ü���� �� �ܾ �����Ǵ� ����ܾ ã�� ���÷���.
			 */
			String value = dict.get(key);
			if (value == null) { // �� key�� �����Ǵ� ���ܾ ���� ���.
				// ���ٰ� ���÷���
				JOptionPane.showMessageDialog(this, "�ܾ ã�� �� �����ϴ�", 
									key, JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, value, key, 
									JOptionPane.INFORMATION_MESSAGE);
			}

		} else if (e.getSource() == addBtn) { // �߰� ��ư�� Ŭ���� ���
			/*
			 * inputField�� �Էµ� �ܾ ����
			 * �� �ܾ �����Ǵ� ����ܾ �Է¹ް� 
			 * dict �� ��ü�� <�ѱ۴ܾ�, ����ܾ�>�� ���� �߰� 
			 */
			String value = 
				JOptionPane.showInputDialog(this, key + "�� �����Ǵ� ����ܾ �Է��ϼ���");
			if (value == null || value.trim().length() == 0) return;
			dict.put(key, value);
			dict.put(value, key);
//			addWordToFile(key, value);
			addWordToDB(key, value);
			JOptionPane.showMessageDialog(this, "����ܾ �߰��Ǿ����ϴ�", "����", 
					JOptionPane.INFORMATION_MESSAGE);
		}
		inputField.requestFocus();
	}
	
	private void addWordToDB(String key, String value) {
		/* ����̹��� �޸𸮿� �����Ѵ�. <- �޸� ����� �� ���� �ϸ� �ǰ�, 
		 * �̹� �����ڿ��� �ߴ�. 
		 * DB�� �����ؼ� Connection ��ü�� ��ȯ�޴´�. 
		 * Connection ��ü���� PreparedStatement ��ü�� ��û�Ѵ�.
		 * PreparedStatement ��ü�� executeUpdate() �޼��带
		 * ȣ���ؼ� DB�� �����Ѵ�.   
		 * 
		 */
		try(Connection con = 
			DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			// place holder
			String sql = "insert into dict values(?, ?)";
			/* 
			 * ���� �غ�
			 *  1. �����˻�
			 *  2. ���缺�˻�(���̺�, Į�� ���� ������ �ִ���, �ִٸ� �� ����ڰ� ���ڵ带 ������ ������ �ִ��� ��...
			 *  3. �����ȹ�� �����. (execution plan)
			 */
			PreparedStatement pstmt = con.prepareStatement(sql);
			// ? �ڸ��� Į�� ������ Ÿ�Կ� ����
			// ������ setXXX() �޼��带 ȣ���ؾ� �Ѵ�. 
			// ���� ���, Į���� char �Ǵ� varchar Ÿ���̸� setString()
			// Į���� TimeStamp Ÿ���̸� setDate(), setTimestamp(),
			// Į���� int Ÿ���̸� setInt()... 
			pstmt.setString(1, key);
			pstmt.setString(2, value);
			// Insert, Delete, Update ���� ������ ���� executeUpdate() �޼��带 ȣ���Ѵ�.
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
		frame.setTitle("���� �ѿ�����");
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
