package jsonDemo;

import com.google.gson.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

class Response {

  User[] users;

  public void setUsers(User[] users) {
    this.users = users;
  }
}

public class Main {

  public static void main(String[] args) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    BufferedReader br;
    try {
      br = new BufferedReader(new FileReader("src/data.json"));
      JsonParser jsonParser = new JsonParser();
      JsonObject obj = jsonParser.parse(br).getAsJsonObject();

      User[] users = gson.fromJson(obj.get("users"), User[].class);
      User.loadAll();

      // grab all users
      Response response = new Response();
      response.setUsers(users);
      String jsonString = gson.toJson(response);
      System.out.println("Printing all Users");
      System.out.println(jsonString);
      System.out.println("-------------------");
      // Getting a user by id
      Response response2 = new Response();
      response2.setUsers(new User[]{User.getUser(2)});
      String jsonString2 = gson.toJson(response2);
      System.out.println("Printing Single User");
      System.out.println(jsonString2);
      System.out.println("-------------------");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }
}