package lecture_1_2st;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class FileChooserDemo extends JFrame implements ActionListener{
   private JButton openBtn, saveBtn;
   JFileChooser fileChooser;
   
   public FileChooserDemo() {
      this.setTitle("파일 선택기 테스트");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(250,200);
      fileChooser = new JFileChooser();
      
      JLabel label = new JLabel("파일 선택기 컴포넌트 테스트입니다.");
      openBtn = new JButton("파일 오픈");
      openBtn.addActionListener(this);
      
      saveBtn = new JButton("파일 저장");
      saveBtn.addActionListener(this);
      
      JPanel panel = new JPanel();
      panel.add(label);
      panel.add(openBtn);
      panel.add(saveBtn);
      
      this.add(panel);
      this.setVisible(true);
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == openBtn) {
         int returnVal = fileChooser.showOpenDialog(this);
         if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            System.out.println("open file: " + file.getAbsolutePath());
         } else {
            System.out.println("No file selected");
         }
      } else if (e.getSource() == saveBtn) {
         int returnVal = fileChooser.showSaveDialog(this);
         if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            System.out.println("save to " + file.getAbsolutePath());
         } else {
            System.out.println("Save canceled...");
         }
      }
   }
   
   public static void main(String[] args) {
      new FileChooserDemo();
   }
}