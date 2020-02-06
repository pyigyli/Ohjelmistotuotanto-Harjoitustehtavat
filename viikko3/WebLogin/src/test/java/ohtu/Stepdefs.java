package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
  //WebDriver driver = new ChromeDriver();
  WebDriver driver = new HtmlUnitDriver();
  String baseUrl = "http://localhost:4567";

  @Given("login is selected")
  public void loginIsSelected() {
    this.driver.get(this.baseUrl);
    WebElement element = this.driver.findElement(By.linkText("login"));
    element.click();
  }

  @Given("command new user is selected")
  public void newUserIsSelected() {
    this.driver.get(this.baseUrl);
    WebElement element = this.driver.findElement(By.linkText("register new user"));
    element.click();
  }

  @Given("user with username {string} with password {string} is successfully created")
  public void newUserCreatedSuccessfully(String username, String password) {
    this.driver.get(this.baseUrl);
    this.newUserIsSelected();
    this.createUserWith(username, password, password);
    this.driver.get(this.baseUrl);
  }

  @Given("user with username {string} and password {string} is tried to be created")
  public void newUserCreatedUnsuccessfully(String username, String password) {
    this.driver.get(this.baseUrl);
    this.newUserIsSelected();
    this.createUserWith(username, password, password);
    this.driver.get(this.baseUrl);
  }

  @When("correct username {string} and password {string} are given")
  public void correctUsernameAndPasswordAreGiven(String username, String password) {
    logInWith(username, password);
  }

  @Then("user is logged in")
  public void userIsLoggedIn() {
    pageHasContent("Ohtu Application main page");
  }

  @Then("a new user is created")
  public void userIsCreated() {
    pageHasContent("Welcome to Ohtu Application!");
  }

  @When("correct username {string} and incorrect password {string} are given")
  public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
    logInWith(username, password);
  }

  @When("nonexistent username {string} and password {string} are given")
  public void nonexistentUsernameAndPasswordAreGiven(String username, String password) {
    logInWith(username, password);
  }

  @When("trying to log in with created username {string} and password {string}")
  public void loginWithCreatedUser(String username, String password) {
    logInWith(username, password);
  }

  @When("a valid username {string} and password {string} and matching password confirmation are entered")
  public void validUsernameAndPasswordAreGiven(String username, String password) {
    createUserWith(username, password, password);
  }

  @When("too short username {string} and password {string} and matching password confirmation are entered")
  public void shortUsernameAndPasswordAreGiven(String username, String password) {
    createUserWith(username, password, password);
  }

  @When("a valid username {string} and too short password {string} and matching password confirmation are entered")
  public void validUsernameAndShortPasswordAreGiven(String username, String password) {
    createUserWith(username, password, password);
  }

  @When("a valid username {string} and password {string} and non-matching password confirmation are entered")
  public void validUsernameAndPasswordAndNotMatchingConfirmationAreGiven(String username, String password) {
    createUserWith(username, password, password + "a1");
  }

  @Then("user is not logged in and error message is given")
  public void userIsNotLoggedInAndErrorMessageIsGiven() {
    pageHasContent("invalid username or password");
    pageHasContent("Give your credentials to login");
  }

  @Then("system will respond {string}")
  public void systemWillRespondToLogin(String pageContent) throws Throwable {
    assertTrue(this.driver.getPageSource().contains(pageContent));
  }

  @Then("user is not created and error {string} is reported")
  public void systemWillRespondToCreation(String pageContent) throws Throwable {
    assertTrue(this.driver.getPageSource().contains(pageContent));
  }

  @After
  public void tearDown(){
    this.driver.quit();
  }

  private void pageHasContent(String content) {
    assertTrue(this.driver.getPageSource().contains(content));
  }

  private void logInWith(String username, String password) {
    assertTrue(this.driver.getPageSource().contains("Give your credentials to login"));
    WebElement element = this.driver.findElement(By.name("username"));
    element.sendKeys(username);
    element = this.driver.findElement(By.name("password"));
    element.sendKeys(password);
    element = this.driver.findElement(By.name("login"));
    element.submit();
  }

  private void createUserWith(String username, String password, String passwordConfirmation) {
    assertTrue(this.driver.getPageSource().contains("Create username and give password"));
    WebElement element = this.driver.findElement(By.name("username"));
    element.sendKeys(username);
    element = this.driver.findElement(By.name("password"));
    element.sendKeys(password);
    element = this.driver.findElement(By.name("passwordConfirmation"));
    element.sendKeys(passwordConfirmation);
    element = this.driver.findElement(By.name("signup"));
    element.submit();
  } 
}
