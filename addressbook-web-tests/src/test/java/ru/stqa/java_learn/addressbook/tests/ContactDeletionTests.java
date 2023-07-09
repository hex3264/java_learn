package ru.stqa.java_learn.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.java_learn.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("ivan", "ivanovich", "testadd", "777777", "8910111111", "test@test.ru","test2"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().confirmContactDeletion();
    }
}
