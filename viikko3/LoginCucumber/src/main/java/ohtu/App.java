package ohtu;

import ohtu.data_access.InMemoryUserDao;
import ohtu.data_access.UserDao;
import ohtu.io.ConsoleIO;
import ohtu.io.IO;
import ohtu.services.AuthenticationService;
import java.util.Arrays;

public class App {

  private IO io;
  private AuthenticationService auth;

  public App(IO io, AuthenticationService auth) {
    this.io = io;
    this.auth = auth;
  }

  public String[] ask() {
    String[] userPwd = new String[2];
    userPwd[0] = this.io.readLine("username:");
    userPwd[1] = this.io.readLine("password:");
    return userPwd;
  }

  public void run() {
    while (true) {
      String command = this.io.readLine("komento (new tai login):");
      if (command.isEmpty()) {
        break;
      }
      if (command.equals("new")) {
        String[] usernameAndPasword = ask();
        if (this.auth.createUser(usernameAndPasword[0], usernameAndPasword[1])) {
          this.io.print("new user registered");
        } else {
          this.io.print("new user not registered");
        }
      } else if (command.equals("login")) {
        String[] usernameAndPasword = ask();
        if (this.auth.logIn(usernameAndPasword[0], usernameAndPasword[1])) {
          this.io.print("logged in");
        } else {
          this.io.print("wrong username or password");
        }
      }
    }
  }

  public static void main(String[] args) {
    UserDao dao = new InMemoryUserDao();
    IO io = new ConsoleIO();
    AuthenticationService auth = new AuthenticationService(dao);
    new App(io, auth).run();
  }
      
  // testejä debugatessa saattaa olla hyödyllistä testata ohjelman ajamista
  // samoin kuin testi tekee, eli injektoimalla käyttäjän syötteen StubIO:n avulla
  //
  // UserDao dao = new InMemoryUserDao();  
  // StubIO io = new StubIO(Arrays.asList("new", "eero", "sala1nen"));   
  //  AuthenticationService auth = new AuthenticationService(dao);
  // new App(io, auth).run();
  // System.out.println(io.getPrints());
}
