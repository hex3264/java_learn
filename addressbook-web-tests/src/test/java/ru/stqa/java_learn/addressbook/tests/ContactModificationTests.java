package ru.stqa.java_learn.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.java_learn.addressbook.model.ContactData;
import ru.stqa.java_learn.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("ivan", "ivanovich", "testadd", "777777", "8910111111", "test@test.ru","test2"));
        }
        app.getContactHelper().clickEditContact();
        app.getContactHelper().fillContactForm(new ContactData("Vasiliy", "Petrovich", "test1", "777777", "8910111111", "test@test.ru", null), false);
        app.getContactHelper().submitContactUpdate();
        app.getNavigationHelper().gotoHomePage();
    }
}
