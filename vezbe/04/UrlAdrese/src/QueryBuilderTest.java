import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Random;
import java.util.Scanner;

public class QueryBuilderTest {
  public static void main(String[] args) {
    int jokeId = new Random().nextInt(500) + 1;
    String endpoint = "http://api.icndb.com/jokes/" + jokeId + "/";

    QueryBuilder qb = new QueryBuilder(endpoint);

    try (Scanner sc = new Scanner(System.in)) {
      System.out.println("Enter first name: ");
      qb.append("firstName", sc.nextLine());
      System.out.println("Enter last name: ");
      qb.append("lastName", sc.nextLine());
    }

    System.err.println("Sending GET request...");
    System.err.println(qb);

    try {
      URL u = qb.toUrl();

      String json;
      try (BufferedReader in = new BufferedReader(new InputStreamReader(u.openStream()))) {
        json = in.readLine();

        System.out.println(json);

        int jokeFieldPos = json.indexOf("joke") + 8;
        String joke = json.substring(jokeFieldPos, json.indexOf('"', jokeFieldPos));
        System.out.println("A random ");
        System.out.println(joke);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
