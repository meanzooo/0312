package Exception;

import java.io.*;
public class FileError2 {
    public static void main(String[] args) {
        writeList();
    }
    private static void writeList() {
        PrintWriter out = null;
        // AutoClosable 인터페이스를 구현해야 한다
        // 그 객체는 try with resources 절에 사용될 수 있다.
        try (FileWriter fw = new FileWriter("out3.txt")) {
            out = new PrintWriter(fw);
            out.println("Hello? I love you.");
            System.out.println("작업종료...");
            // out.close();
        }catch(IOException e) {
            System.out.println("catch:"+e.getMessage());
        } 
    }  
}