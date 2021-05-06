package Collection;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SimpleDictionary extends JPanel implements ActionListener{
	/*
	 * �ܾ� �Է¹��� �� �ִ� JTextField
	 * �˻� ��ư
	 * �߰� ��ư
	 * �ܾ��� ������ ���� �ڷᱸ���� Map ��ü
	 */
	
	private JTextField inputField = new JTextField(30);
	private JButton searchBtn = new JButton("�˻�");
	private JButton addBtn = new JButton("�߰�");
	
	//Map ��ü�� �ܾ��� ������ ����� ����.
	//<key,value>���� key�� �ѱ� �ܾ�, value�� �����Ǵ� ����ܾ�� ����.
	private Map<String, String> dict = new HashMap<>();
	
	public SimpleDictionary() {
		//Panel�� �⺻ ���̾ƿ��� FlowLayout.
		this.add(inputField);
		this.add(searchBtn);
		this.add(addBtn);
		
		//searchBtn, addBtn�� Ŭ�� �̺�Ʈ�� �߻����� �� ó���� �����ʸ� ����.
		searchBtn.addActionListener(this);
		addBtn.addActionListener(this);
		
		this.setPreferredSize(new Dimension(600,50));
		
		//���Ͽ� key=value ���·� ����� ��Ʈ������ �о, dict�� ��������.
		
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
		 * �Էµ� �ܾ �����ϰ�
		 * �� �ܾ key������ ������ �����Ǵ� �� ��Ʈ���� �ִ��� �˻� -> dict.get(�ܾ�);
		 * 		�� �ܾ �����Ǵ� ���� ������ JOptionPane.showMessageDialog()	�޼��带 ȣ���ؼ� �� �����Ǵ� ���� �����ش�.
		 * 		������(=null�̸�) JOptionPane.showMessageDialog() �޼��带 ȣ���ؼ� '�ܾ ã�� �� �����ϴ�' ��� ������ش�.
		 * inputField�� �� ���ڿ��� ����
		 */
		System.out.println("["+key+"]");
		String value = dict.get(key);
		if (value != null) { //�����Ǵ� �ܾ �ִ� ���
			JOptionPane.showMessageDialog(this,value,key,JOptionPane.INFORMATION_MESSAGE);
		} else { //�����Ǵ� �ܾ ���� ���
			JOptionPane.showMessageDialog(this, "�ܾ ã�� �� �����ϴ�.", key,
											JOptionPane.ERROR_MESSAGE);
				}

		} else if (e.getSource()==addBtn) {
			/*
			 * �Էµ� �ܾ ����
			 * String value = JOptionPane.showInputDialog();
			 * �޼��带 ȣ���ؼ� �߰��� ���� �ܾ �Է¹޴´�.
			 * dict.put(�Է��ʵ忡 �Էµ� �ܾ�, inputDialog�� �Էµ� �ܾ�);
			 * inputField�� �� ���ڿ��� ����
			 */
			String value = JOptionPane.showInputDialog(this, key + "�� �����Ǵ� ����ܾ �Է��ϼ���.");
			if (value.trim().length() == 0) return;
			dict.put(key, value);
			
			//���Ͽ��� key=value�� ������ ����ص���
			
			
			JOptionPane.showMessageDialog(this, value + "���� �ܾ �߰��Ǿ����ϴ�.", key,
											JOptionPane.INFORMATION_MESSAGE);
			}
		inputField.setText("");
		}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		SimpleDictionary dictPanel = new SimpleDictionary();
		frame.add(dictPanel);
		frame.setTitle("���� �ѿ� ����");
		
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
