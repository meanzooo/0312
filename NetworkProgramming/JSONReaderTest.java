import java.net.*;

import com.google.gson.Gson;

import java.io.*;


public class JSONReaderTest {
   public static void  main(String args[]) throws Exception{
      Gson gson = new Gson();
      String json = "[{'userId': 1, 'id':1 ,'title': 'test', 'body':'test body'},{'userId': 2, 'id':2 ,'title': 'test2', 'body':'test2 body'}]";
      Post[] posts = gson.fromJson(json, Post[].class);
      
      /*
       * Post post = new Post();
       * post.setYserId(1);
       * post.setId(l);
       * post.setTitel("title");
       * post.setBody("test body");
       * return post;
       */
      
      for (Post post : posts) {
      System.out.println(post.getUserId());
      System.out.println(post.getId());
      System.out.println(post.getTitle());
      System.out.println(post.getBody());
      System.out.println("######################");
      }
   }
}