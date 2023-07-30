package ru.stqa.java_learn.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.java_learn.addressbook.model.ContactData;
import ru.stqa.java_learn.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
            Contacts before = app.contact().all();
            ContactData contact = new ContactData()
                    .withFirstname("ivan").withLastname("ivanovich").withEmail1("test@test.ru").withAddress("testadd")
                    .withGroup("[none]").withWorkPhone("111").withMobilePhone("222").withHomePhone("333");
        app.contact().create(contact);
        assertEquals(app.contact().count(), before.size() + 1);
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

        }

    }
