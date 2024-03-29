package ru.stqa.java_learn.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.java_learn.mantis.model.Issue;
import ru.stqa.java_learn.mantis.model.Project;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase{

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for(Project project:projects){
            System.out.println(project.getName());
        }

    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        skipIfNotFixed(	0000001);
        Set<Project> projects =app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test summary")
                .withDescription("Test description").withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        Assert.assertEquals(issue.getSummary(), created.getSummary());

    }
}
