package ru.stqa.java_learn.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.java_learn.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToAddNewContact();
        app.getContactHelper().fillContactForm(new ContactData("ivan", "ivanovich", "test1", "777777", "8910111111", "test@test.ru"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
    }

}
