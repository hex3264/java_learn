package ru.stqa.java_learn.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.java_learn.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification() {
        app.getContactHelper().clickEditContact();
        app.getContactHelper().fillContactForm(new ContactData("Vasiliy", "Petrovich", "test1", "777777", "8910111111", "test@test.ru", null), false);
        app.getContactHelper().submitContactUpdate();
        app.getNavigationHelper().gotoHomePage();
    }
}
