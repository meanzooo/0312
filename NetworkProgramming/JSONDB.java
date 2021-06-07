import java.net.*;
import java.io.*;
import java.sql.*;
import com.google.gson.Gson;

public class JSONDB {
   public static void  main(String args[]) throws Exception{ 
	  String site = "https://jsonplaceholder.typicode.com/posts";
      URL url = new URL(site);
      
      URLConnection con = url.openConnection();
      
      InputStream stream = con.getInputStream();
      InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
      
      BufferedReader bufReader = new BufferedReader(reader);
      
      String line = null;
      String jsonString = "";
      while((line = bufReader.readLine()) != null) {
    	  jsonString += line;
      }
      
      Gson gson = new Gson();
      Post[] posts = gson.fromJson(jsonString, Post[].class);
      
      for (Post post : posts) {
      System.out.println(post.getUserId());
      System.out.println(post.getId());
      System.out.println(post.getTitle());
      System.out.println(post.getBody());
      System.out.println("######################");
      }
      
      insertIntoDB(posts);
   }
   
   private static void insertIntoDB(Post[] posts) throws Exception {
	   /*
	    * 1. Class.forName(...); //JDBC ����̹� �޸𸮿� ����
	    * 2. Connection con =
	    * 		DriverManger.getConnection(...); DB������ ����
	    * 3. String sql = "insert into posts(userId, id, title, body)
	    * 						values(?,?,?,?);
	    * PreparedStatement pstmt = con.prepareStatement(sql);
	    * 
	    * 4. ?�ڸ��� �� ���� �����Ѵ�.
	    *  pstmt.setInt(1, post.getUserId());
	    *  pstmt.setInt(2,post.getId());
	    *  pstmt.setstring(3, post.getTitle());
	    *  pstmt.setString(4,post.getBody());
	    *  
	    * 5. ������ �����û
	    *   pstmt.executeUpdate();
	    * 6. con.close();
	    */
   
	   Class.forName("oracle.jdbc.driver.OracleDriver");
	   String url = "jdbc:oracle:thin:@localhost:1521:xe";
	   String id = "insa";
	   String pw = "insa";
	   Connection con = DriverManager.getConnection(url,id,pw);
	   String sql = "insert into posts(userId, id, title, body) values (?,?,?,?)";
	   PreparedStatement pstmt = con.prepareStatement(sql);
	   
	   for (Post post: posts) {
		   pstmt.setInt(1, post.getUserId());
		   pstmt.setInt(2,post.getId());
		   pstmt.setString(3, post.getTitle());
		   pstmt.setString(4,post.getBody());
		   pstmt.executeUpdate();
	   }
   }
}