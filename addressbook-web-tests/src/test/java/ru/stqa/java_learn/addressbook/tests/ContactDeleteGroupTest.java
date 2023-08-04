package ru.stqa.java_learn.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.java_learn.addressbook.model.ContactData;
import ru.stqa.java_learn.addressbook.model.GroupData;

import java.util.NoSuchElementException;

public class ContactDeleteGroupTest extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("name 1").withHeader("header 1").withFooter("footer 1"));
            app.goTo().homePage();
        }

        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withFirstname("ivan").withLastname("Petrov").withMobilePhone("222")
                    .withEmail1("test@test.ru").inGroup(app.db().groups().iterator().next()));
        }
    }

    @Test
    public void testRemoveContactFromGroup() {
        ContactData before;

        try {
            before = app.db().contacts().stream().filter((c) -> c.getGroups().size() != 0).findFirst().get();
        } catch (NoSuchElementException e) {
            before = prepareContactForRemoveFromGroup(app.db().contacts().iterator().next());
        }

        GroupData groupForRemove = before.getGroups().iterator().next();
        app.contact().removeFromGroup(before, groupForRemove);
        ContactData finalBefore = before;
        ContactData after = app.db().contacts().stream().filter((c) -> c.getId() == finalBefore.getId()).findFirst().get();

        assert(!after.getGroups().contains(groupForRemove));
    }

    public ContactData prepareContactForRemoveFromGroup(ContactData contact) {
        app.contact().addContactToGroup(contact, app.db().groups().iterator().next());
        app.goTo().homePage();
        return app.db().contacts().stream().filter((c) -> c.getId() == contact.getId()).findFirst().get();
    }
}
