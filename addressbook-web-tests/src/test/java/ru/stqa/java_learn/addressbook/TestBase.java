package ru.stqa.java_learn.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {
    private WebDriver wd;
    private JavascriptExecutor js;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        js = (JavascriptExecutor) wd;
        wd.get("http://localhost/addressbook/group.php");
        login();
    }

    private void login() {
        login("admin", "secret");
    }

    private void login(String username, String password) {
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    protected void returnToGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    protected void submitGroupCreation() {
        wd.findElement(By.name("submit")).click();
    }

    protected void fillGroupForm(GroupDate groupDate) {
        wd.findElement(By.linkText("groups")).click();
        wd.findElement(By.name("new")).click();
        wd.findElement(By.name("group_name")).clear();
        wd.findElement(By.name("group_name")).sendKeys(groupDate.getName());
        wd.findElement(By.name("group_header")).click();
        wd.findElement(By.name("group_header")).clear();
        wd.findElement(By.name("group_header")).sendKeys(groupDate.getHeader());
        wd.findElement(By.name("group_footer")).click();
        wd.findElement(By.name("group_footer")).clear();
        wd.findElement(By.name("group_footer")).sendKeys(groupDate.getFooter());
    }

    protected void goToGroupPage() {
        wd.get("http://localhost/addressbook/group.php");
    }

    protected void deleteSelectedGroups() {
        wd.findElement(By.name("delete")).click();
    }

    protected void selectGroup() {
        wd.findElement(By.name("selected[]")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        logout();
        wd.quit();
    }

    private void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }


}
