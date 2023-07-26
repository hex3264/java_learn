package ru.stqa.java_learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.java_learn.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("ivan", "ivanovich", "testadd", "test@test.ru", "[none]");
        app.getContactHelper().createContact(contact);
        List<ContactData> after = app.getContactHelper().getContactList();

        contact.setId(after.stream().max((Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
