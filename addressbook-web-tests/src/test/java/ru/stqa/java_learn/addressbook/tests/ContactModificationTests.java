package ru.stqa.java_learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.java_learn.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("ivan", "ivanovich", "testadd", "test@test.ru", "[none]"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().clickEditContact(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Vasiliy", "Petrovich", "testadd2", "test1@test.ru", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactUpdate();
        app.getContactHelper().gotoHomePage();
        int index = before.size() - 1;
        List<ContactData> after = app.getContactHelper().getContactList();

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}