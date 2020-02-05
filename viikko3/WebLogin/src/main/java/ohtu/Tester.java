package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import java.util.Random;

public class Tester {

  public static void main(String[] args) {
    OperaOptions options = new OperaOptions();
    options.setBinary("C:\\Users\\Pyigyli\\AppData\\Local\\Programs\\Opera\\66.0.3515.44\\opera.exe");
    WebDriver driver = new OperaDriver(options);
    driver.get("http://localhost:4567");
    Random r = new Random();
    sleep(2);

    // WebElement element = driver.findElement(By.linkText("login"));
    // element.click();
    // sleep(2);
    // element = driver.findElement(By.name("username"));
    // element.sendKeys("pekka");
    // element = driver.findElement(By.name("password"));
    // element.sendKeys("akkep");
    // element = driver.findElement(By.name("login"));
    // sleep(2);
    // element.submit();

    // WebElement element = driver.findElement(By.linkText("login"));
    // element.click();
    // sleep(2);
    // element = driver.findElement(By.name("username"));
    // element.sendKeys("pekka");
    // element = driver.findElement(By.name("password"));
    // element.sendKeys("notpekka");
    // element = driver.findElement(By.name("login"));
    // sleep(2);
    // element.submit();

    WebElement element = driver.findElement(By.linkText("register new user"));
    element.click();
    sleep(2);
    element = driver.findElement(By.name("username"));
    element.sendKeys("teppo" + r.nextInt(100000));
    element = driver.findElement(By.name("password"));
    element.sendKeys("akkep");
    element = driver.findElement(By.name("passwordConfirmation"));
    element.sendKeys("akkep");
    element = driver.findElement(By.name("signup"));
    sleep(2);
    element.submit();
    element = driver.findElement(By.linkText("continue to application mainpage"));
    element.click();
    element = driver.findElement(By.linkText("logout"));
    element.click();

    sleep(3);
    driver.quit();
  }
      
  private static void sleep(int n){
    try{
      Thread.sleep(n*1000);
    } catch (Exception e) {}
  }
}
