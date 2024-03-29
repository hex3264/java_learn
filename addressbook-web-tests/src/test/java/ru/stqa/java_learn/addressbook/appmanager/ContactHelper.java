package ru.stqa.java_learn.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.java_learn.addressbook.model.ContactData;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.java_learn.addressbook.model.Contacts;
import ru.stqa.java_learn.addressbook.model.GroupData;

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
        type(By.name("email"), contactData.getEmail1());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"),contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            } }
        else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    private Contacts contactsCache = null;


    public Contacts all() {
        if (contactsCache != null) {
            return new Contacts(contactsCache);
        }
        contactsCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String fname = cells.get(2).getText();
            String lname = cells.get(1).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            String address = cells.get(3).getText();
            contactsCache.add(new ContactData().withId(id).withFirstname(fname).withLastname(lname)
                    .withAllPhones(allPhones).withAllEmail(allEmails)
                    .withAddress(address));
        }
        return new Contacts(contactsCache);
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
        contactsCache = null;
        returnHomePage();
    }

    public int count() {
        return wd.findElements(By.name("entry")).size();
    }

    public void modify(ContactData contact) {
        clickEditContactById(contact.getId());
        fillTheForm(contact, false);
        updateChanges();
        contactsCache = null;
        returnHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        clickDeleteContact();
        contactsCache = null;
        closeDeleteAlert();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        clickEditContactById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String homephone = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstName).withLastname(lastName)
                .withHomePhone(homephone).withMobilePhone(mobile).withWorkPhone(work)
                .withEmail1(email1).withEmail2(email2).withEmail3(email3)
                .withAddress(address);

    }

    public void addContactToGroup(ContactData before, GroupData groupAdd) {
        selectContactById(before.getId());
        selectGroupForAdd(groupAdd.getId());
        submitAddingContactsToGroup();
        returnToGroupPage(groupAdd);
    }

    private void selectGroupForAdd(int id) {
        new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(id));
    }

    private void selectGroupForRemove(int id) {
        new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(id));
    }
    private void submitAddingContactsToGroup() {
        wd.findElement(By.name("add")).click();
    }
    private void returnToGroupPage(GroupData groupAdd) {
        wd.findElement(By.linkText("group page \"" + groupAdd.getName() + "\"")).click();
    }


    public void removeFromGroup(ContactData contact, GroupData groupForRemove) {
        selectGroupForRemove(groupForRemove.getId());
        selectContactById(contact.getId());
        submitRemove();
        returnToGroupPage(groupForRemove);
    }

    private void submitRemove() {
        wd.findElement(By.name("remove")).click();
    }

}