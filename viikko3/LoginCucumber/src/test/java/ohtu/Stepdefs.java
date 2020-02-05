package ohtu;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import ohtu.io.*;
import ohtu.data_access.*;
import ohtu.services.*;

public class Stepdefs {

  App app;
  StubIO io;
  UserDao userDao;
  AuthenticationService auth;
  List<String> inputLines;

  @Before
  public void setup(){
    this.userDao = new InMemoryUserDao();
    this.auth = new AuthenticationService(this.userDao);
    this.inputLines = new ArrayList<>();
  }

  @Given("^command new is selected$")
  public void commandNewSelected() throws Throwable {
    this.inputLines.add("new");
  }

  @Given("^command login is selected$")
  public void commandLoginSelected() throws Throwable {
    this.inputLines.add("login");
  }

  @When("user {string} with password {string} is created")
  public void userCreatedWithUsernameAndPassword(String username, String password) {
    this.inputLines.add(username);
    this.inputLines.add(password);
    this.io = new StubIO(inputLines);
    this.app = new App(io, auth);
    this.app.run();
  }

  @When("username {string} and password {string} are entered")
  public void usernameAndPasswordAreEntered(String username, String password) {
    this.inputLines.add(username);
    this.inputLines.add(password);
    this.io = new StubIO(inputLines);
    this.app = new App(io, auth);
    this.app.run();
  }

  @Then("system will respond with {string}")
  public void systemWillRespondWith(String expectedOutput) {
    assertTrue(this.io.getPrints().contains(expectedOutput));
  }
}
