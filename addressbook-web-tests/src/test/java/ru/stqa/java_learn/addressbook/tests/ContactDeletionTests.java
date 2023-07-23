package ru.stqa.java_learn.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.java_learn.addressbook.model.ContactData;
import ru.stqa.java_learn.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToGroupPage();
            app.getGroupHelper().createGroup(new GroupData("test2", null, null));
            app.getContactHelper().createContact(new ContactData("ivan", "ivanovich", "testadd", "777777", "8910111111", "test@test.ru","test"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().confirmContactDeletion();
    }
}