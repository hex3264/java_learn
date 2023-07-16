package ru.stqa.java_learn.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.java_learn.addressbook.model.ContactData;
import ru.stqa.java_learn.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test2", null, null));
        }
        app.getNavigationHelper().goToAddNewContact();
        app.getContactHelper().createContact(new ContactData("ivan", "ivanovich", "testadd", "777777", "8910111111", "test@test.ru","test2"));
    }

}
