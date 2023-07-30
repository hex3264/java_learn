package ru.stqa.java_learn.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.java_learn.addressbook.model.ContactData;
import ru.stqa.java_learn.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
            Contacts before = app.contact().all();
            ContactData contact = new ContactData()
                    .withFirstname("ivan").withLastname("ivanovich").withEmail("test@test.ru").withAddress("testadd")
                    .withGroup("[none]");
            app.contact().create(contact);
            Contacts after = app.contact().all();
            Assert.assertEquals(after.size(), before.size() + 1);

            assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

        }

    }
