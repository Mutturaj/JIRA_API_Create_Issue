package Website;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class generic_methods {
    private WebDriver driver;
    private By locator;
    private String definition;

    public generic_methods(WebDriver driver,By locator, String definition) {
        this.driver = driver;
        this.locator=locator;
        this.definition=definition;

    }
    public WebElement getElementByLocatorDetails(By locator, String definition) {
        // Optionally, you can perform additional handling or logging here
        return driver.findElement(locator);
    }

    // Example method for using XPath
    public WebElement getElementByXPath(String xpath, String definition) {
        return getElementByLocatorDetails(By.xpath(xpath), definition);
    }
}
