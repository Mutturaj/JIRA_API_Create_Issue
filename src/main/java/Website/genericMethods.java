package Website;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class genericMethods {

    public static void main(String[] args) throws InterruptedException {
        linkedinUpdate();
    }

    static void linkedinUpdate() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            loginToLinkedIn(driver);
            navigateToJobs(driver, wait, js);
            searchAndApplyForJobs(driver, wait, js);
        } finally {
            driver.quit();
        }
    }

    static void loginToLinkedIn(WebDriver driver) throws InterruptedException {
        driver.get("https://www.linkedin.com/uas/login?session_redirect=https%3A%2F%2Fwww.linkedin.com%2Fnotifications%2F%3Ffilter%3Dall");
        driver.manage().window().maximize();
        Thread.sleep(2000); // Use WebDriverWait for better practice
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys(System.getenv("LINKEDIN_USERNAME"));
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(System.getenv("LINKEDIN_PASSWORD"));
        Thread.sleep(2000); // Use WebDriverWait for better practice
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    static void navigateToJobs(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//li-icon[@type='job']//*[name()='svg' and @class='mercado-match']")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[name()='svg' and @class='jobs-search-box__search-icon--custom']")).click();
        WebElement overlay = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("search-global-typeahead__overlay")));
        js.executeScript("arguments[0].style.display='none';", overlay);
        driver.findElement(By.xpath("//a[@aria-label=\"Show all Job picks for you\"]//span[text()='Show all']")).click();
        Thread.sleep(9000);
    }

    static void searchAndApplyForJobs(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) throws InterruptedException {
        WebElement titleElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Title, skill or company']")));
        js.executeScript("arguments[0].removeAttribute('disabled');", titleElement);
        js.executeScript("arguments[0].removeAttribute('aria-hidden');", titleElement);
        js.executeScript("arguments[0].scrollIntoView(true);", titleElement);
        titleElement.sendKeys("software testing");

        WebElement locationElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='City, state, or zip code']")));
        js.executeScript("arguments[0].removeAttribute('disabled');", locationElement);
        js.executeScript("arguments[0].removeAttribute('aria-hidden');", locationElement);
        js.executeScript("arguments[0].scrollIntoView(true);", locationElement);
        locationElement.sendKeys("Karnataka, India");
        Thread.sleep(2000); // Use WebDriverWait for better practice
        driver.findElement(By.xpath("//button[text()='Search']")).click();
        Thread.sleep(4000); // Use WebDriverWait for better practice

        applyForJobs(driver, wait, js);
    }

    static void applyForJobs(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) throws InterruptedException {
        while (true) {
            List<WebElement> jobs = driver.findElements(By.xpath("//li[@class='job-card-container__apply-method job-card-container__footer-item inline-flex align-items-center']"));

            for (WebElement job : jobs) {
                if (!jobs.isEmpty()) {
                    job.click();
                    Thread.sleep(5000); // Use WebDriverWait for better practice
                    driver.findElement(By.xpath("//button[@class='jobs-apply-button artdeco-button artdeco-button--3 artdeco-button--primary ember-view']//span[text()='Easy Apply' and @class='artdeco-button__text']")).click();
                    Thread.sleep(3000); // Use WebDriverWait for better practice

                    applyForJobSteps(driver, wait, js);
                    Thread.sleep(5000); // Use WebDriverWait for better practice
                    driver.findElement(By.xpath("//button[@aria-label=\"Dismiss\"]//*[name()='svg']")).click();
                    Thread.sleep(2000); // Use WebDriverWait for better practice
                } else {
                    js.executeScript("document.querySelector('.scaffold-layout__list .jobs-search-results-list').scrollTop += 550;");
                    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@class='job-card-container__apply-method job-card-container__footer-item inline-flex align-items-center']")));
                }
            }
        }
    }

    static void applyForJobSteps(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) throws InterruptedException {
        while (true) {
            if (!driver.findElements(By.xpath("//button[@aria-label=\"Continue to next step\"]//span[text()='Next']")).isEmpty()) {
                driver.findElement(By.xpath("//button[@aria-label=\"Continue to next step\"]//span[text()='Next']")).click();
            } else if (!driver.findElements(By.xpath("//span[text()='Review']")).isEmpty()) {
                List<WebElement> questions = driver.findElements(By.xpath("//input[@class=' artdeco-text-input--input']"));
                for (WebElement send : questions) {
                    send.clear();
                    send.sendKeys("2");
                }
                Thread.sleep(2000); // Use WebDriverWait for better practice
                driver.findElement(By.xpath("//span[text()='Review']")).click();
            } else if (!driver.findElements(By.xpath("//span[text()='Submit application']")).isEmpty()) {
                driver.findElement(By.xpath("//span[text()='Submit application']")).click();
                break;
            } else {
                break;
            }
            Thread.sleep(2000); // Use WebDriverWait for better practice
        }
    }
}
