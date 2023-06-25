package ru.stqa.java_learn.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        goToGroupPage();
        fillGroupForm(new GroupDate("test2", "test3", "test4"));
        submitGroupCreation();
        returnToGroupPage();
    }

}
