package br.com.raulpacheco;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {

    private static final String PAGE = "https://demoqa.com/automation-practice-form";
    private final WebDriver webDriver;

    public LoginPage() {
        System.setProperty("webdriver.chrome.driver", "../docs/drivers/chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    public void beforeEach() {
        webDriver.manage().window().maximize();
        webDriver.navigate().to(PAGE);
    }

    public void afterEach() {
        webDriver.quit();
    }

    public void fillForm(String firstName, String lastName, Integer gender, String phone) {
        webDriver.findElement(By.id("firstName")).sendKeys(firstName);
        webDriver.findElement(By.id("lastName")).sendKeys(lastName);
        webDriver.findElement(By.xpath("//*[@id=\"genterWrapper\"]/div[2]/div[1]/label")).click();
        webDriver.findElement(By.id("userNumber")).sendKeys(phone);
    }

    public void submitForm() {
        webDriver.findElement(By.id("userForm")).submit();
    }

    public WebElement getModal() {
        try {
            return webDriver.findElement(By.xpath("/html/body/div[4]/div/div"));
        } catch (NoSuchElementException noSuchElementException) {
            return null;
        }
    }

    public WebElement getTable() {
        return webDriver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/table"));
    }

    public String getTableName() {
        return webDriver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[1]/td[2]")).getText();
    }

    public String getTableGender() {
        return webDriver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[3]/td[2]")).getText();
    }

    public String getTablePhone() {
        return webDriver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[4]/td[2]")).getText();
    }

    public boolean hasText(String text) {
        return webDriver.getPageSource().contains(text);
    }
}
