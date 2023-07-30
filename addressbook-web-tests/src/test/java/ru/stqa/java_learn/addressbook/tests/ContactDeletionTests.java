package ru.stqa.java_learn.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.java_learn.addressbook.model.ContactData;
import ru.stqa.java_learn.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstname("ivan").withLastname("ivanovich").withEmail1("test@test.ru").withAddress("testadd")
                    .withGroup("[none]").withWorkPhone("111").withMobilePhone("222").withHomePhone("333"));
        }
    }

    @Test
    public void testContactDeletion() {
            Contacts before = app.contact().all();
            ContactData deletedContact = before.iterator().next();
            app.contact().delete(deletedContact);
            app.goTo().homePage();
            assertEquals(app.contact().count(), before.size()-1);
            Contacts after = app.contact().all();
            assertThat(after, equalTo(before.without(deletedContact)));
        }
}