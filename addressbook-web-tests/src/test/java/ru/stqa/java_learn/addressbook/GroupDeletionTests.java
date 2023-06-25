package ru.stqa.java_learn.addressbook;

import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        goToGroupPage();
        selectGroup();
        deleteSelectedGroups();
        returnToGroupPage();
    }

}



