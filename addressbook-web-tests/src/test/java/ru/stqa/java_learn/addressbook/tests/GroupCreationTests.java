package ru.stqa.java_learn.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.java_learn.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", null, null));
        app.getGroupHelper().submitGroupCreation();
        app.getNavigationHelper().gotoHomePage();
    }

}
