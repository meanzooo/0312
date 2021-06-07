import java.net.*;

import com.google.gson.Gson;

import java.io.*;


public class JSONReader {
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
   }
}