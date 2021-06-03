import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLPostTest {
   
   public static void main(String[] args) throws Exception{
      String site = "http://localhost:9090/todos";
      URL url = new URL(site);
      
      HttpURLConnection con = (HttpURLConnection)url.openConnection();
      con.setDoOutput(true); // OutputStream 은 기본적으로 막혀있음
      con.setRequestMethod("POST");
      con.setRequestProperty("content-type", "application/x-www-form-urlencoded"); // 헤더정보
      
      // id = mzkang, pw = 1234
      String data = "id=mzkang&pw=1234";
      OutputStream stream = con.getOutputStream();
      OutputStreamWriter owriter = new OutputStreamWriter(stream, "UTF-8");
      PrintWriter writer = new PrintWriter(owriter); //println 를 사용할 수 있음
      writer.println(data);
      writer.flush();
      
      InputStream rstream = con.getInputStream();
      InputStreamReader streamReader = new InputStreamReader(rstream);
      
      BufferedReader reader = new BufferedReader(streamReader);
      String line;
      while((line = reader.readLine()) != null) {
         System.out.println(line);
      }
      System.out.println();
      
   }
}
