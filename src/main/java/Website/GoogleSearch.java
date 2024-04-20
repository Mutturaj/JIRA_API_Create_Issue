package Website;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class GoogleSearch {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        Thread.sleep(2000);
        driver.findElement(By.name("q")).sendKeys("sandelwood");
        Thread.sleep(3000);
        List<WebElement> suggestionss = driver.findElements(By.xpath("//div[@class='pcTkSc']"));
        int suggestionCount1 = suggestionss.size();

        int numIterations = suggestionCount1; // Number of times to iterate

        for (int j = 0; j < numIterations; j++) {
            List<WebElement> suggestions = driver.findElements(By.xpath("//div[@class='pcTkSc']"));
            int suggestionCount = suggestions.size();

            for (int i = 0; i < suggestionCount; i++) {
                suggestions.get(i).click();
                Thread.sleep(2000);

                driver.navigate().back();
                Thread.sleep(2000);

                // Re-find the search input element after navigating back
                driver.findElement(By.name("q")).sendKeys("sandelwood");
                Thread.sleep(3000);

                // Re-capture the suggestions after re-searching
                suggestions = driver.findElements(By.xpath("//div[@class='pcTkSc']"));
                suggestionCount = suggestions.size();
            }
        }

        driver.quit();
    }
}
