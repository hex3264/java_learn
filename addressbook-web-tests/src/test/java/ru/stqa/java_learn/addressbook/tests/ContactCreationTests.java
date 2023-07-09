package ru.stqa.java_learn.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.java_learn.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToAddNewContact();
        app.getContactHelper().fillContactForm(new ContactData("ivan", "ivanovich", "testadd", "777777", "8910111111", "test@test.ru","test2"), true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoHomePage();
    }

}
