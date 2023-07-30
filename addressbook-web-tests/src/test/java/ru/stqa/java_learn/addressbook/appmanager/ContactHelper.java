package ru.stqa.java_learn.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.java_learn.addressbook.model.ContactData;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.java_learn.addressbook.model.Contacts;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void goToAddNewContact() {
        click(By.linkText("add new"));
    }

    public void returnHomePage() {
        click(By.linkText("home page"));
    }

    public void selectContactById(int id) {

        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillTheForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String fname = cells.get(2).getText();
            String lname = cells.get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withFirstname(fname).withLastname(lname);
            contacts.add(contact);
        }
        return contacts;
    }

    public void clickDeleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void closeDeleteAlert() {
        wd.switchTo().alert().accept();
    }

    public void clickEditContactById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void updateChanges() {
        click(By.name("update"));
    }

    public void submitChanges() {
        click(By.name("submit"));
    }

    public void create(ContactData contact) {
        goToAddNewContact();
        fillTheForm(contact, true);
        submitContactCreation();
        returnHomePage();
    }

    public void modify(ContactData contact) {
        clickEditContactById(contact.getId());
        fillTheForm(contact, false);
        updateChanges();
        returnHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        clickDeleteContact();
        closeDeleteAlert();
    }
}