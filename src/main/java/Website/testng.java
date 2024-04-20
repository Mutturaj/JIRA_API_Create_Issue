package Website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class testng {
    @Test
    public void creat(){
        WebDriver driver=new ChromeDriver();
        driver.get("https://mvnrepository.com/");
        System.out.println("Launch");
    }
}
