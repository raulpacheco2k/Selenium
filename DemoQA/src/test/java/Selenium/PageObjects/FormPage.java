package Selenium.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FormPage {

    private static final String PAGE = "https://demoqa.com/automation-practice-form";
    private final WebDriver webDriver;

    public static final int MALE = 1;
    public static final int FEMALE = 2;
    public static final int OTHER = 3;

    private final By firstNameBy = By.id("firstName");
    private final By lastNameBy = By.id("lastName");
    private final By userNumberBy = By.id("userNumber");
    private final By modalBy = By.xpath("/html/body/div[4]/div/div");
    private final By tableBy = By.xpath("/html/body/div[4]/div/div/div[2]/div/table");
    private final By tableNameBy = By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[1]/td[2]");
    private final By tableGenderBy = By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[3]/td[2]");
    private final By tableNumberBy = By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[4]/td[2]");

    public FormPage() {
        System.setProperty("webdriver.chrome.driver", "../docs/drivers/chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    public FormPage(WebDriver webDriver1) {
        System.setProperty("webdriver.chrome.driver", "../docs/drivers/chromedriver.exe");
        webDriver = webDriver1;
    }

    public void beforeEach() {
        webDriver.manage().window().maximize();
        webDriver.navigate().to(PAGE);
    }

    public void afterEach() {
        webDriver.quit();
    }

    private By resolverGender(Integer number) {
        return switch (number) {
            case MALE -> By.xpath("//*[@id=\"genterWrapper\"]/div[2]/div[1]/label");
            case FEMALE -> By.xpath("//*[@id=\"genterWrapper\"]/div[2]/div[2]/label");
            case OTHER -> By.xpath("//*[@id=\"genterWrapper\"]/div[2]/div[3]/label");
            default -> null;
        };
    }

    public void fillForm(String firstName, String lastName, Integer gender, String phone) {
        webDriver.findElement(firstNameBy).sendKeys(firstName);
        webDriver.findElement(lastNameBy).sendKeys(lastName);
        webDriver.findElement(resolverGender(gender)).click();
        webDriver.findElement(userNumberBy).sendKeys(phone);
    }

    public void submitForm() {
        webDriver.findElement(By.id("userForm")).submit();
    }

    public WebElement getModal() {
        try {
            return webDriver.findElement(modalBy);
        } catch (NoSuchElementException noSuchElementException) {
            return null;
        }
    }

    public WebElement getTable() {
        return webDriver.findElement(tableBy);
    }

    public String getTableName() {
        return webDriver.findElement(tableNameBy).getText();
    }

    public String getTableGender() {
        return webDriver.findElement(tableGenderBy).getText();
    }

    public String getTableNumber() {
        return webDriver.findElement(tableNumberBy).getText();
    }

    public boolean hasText(String text) {
        return webDriver.getPageSource().contains(text);
    }
}
