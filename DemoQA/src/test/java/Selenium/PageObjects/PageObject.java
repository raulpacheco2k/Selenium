package Selenium.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public abstract class PageObject {

    protected WebDriver webDriver;
    protected String urlPage;

    public PageObject(String urlPage) {
        System.setProperty("webdriver.chrome.driver", "../docs/drivers/chromedriver.exe");
        this.webDriver = new ChromeDriver();
        this.urlPage = urlPage;

        this.webDriver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(3))
                .pageLoadTimeout(Duration.ofSeconds(10));
    }

    public PageObject(WebDriver webDriver) {
        System.setProperty("webdriver.chrome.driver", "../docs/drivers/chromedriver.exe");
        this.webDriver = webDriver;
    }

    public String getUrlPage() {
        return urlPage;
    }

    public void beforeEach() {
        webDriver.manage().window().maximize();
        webDriver.navigate().to(urlPage);
    }

    public void afterEach() {
        webDriver.quit();
    }
}
