package ru.stqa.java_learn.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.java_learn.mantis.model.Issue;
import ru.stqa.java_learn.mantis.model.Project;
import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {
    private ApplicationManager app;

    public SoapHelper(ApplicationManager app)  {
        this.app=app;
    }

    public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
        MantisConnectPortType mc = connect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible(app.getProperty("soap.user"), app.getProperty("soap.password"));
        return Arrays.asList(projects).stream().map((p)->new Project().withId(p.getId().intValue()).withName(p.getName()))
                .collect(Collectors.toSet());
    }

    public MantisConnectPortType connect() throws ServiceException, MalformedURLException {
        MantisConnectPortType mc = new MantisConnectLocator().getMantisConnectPort(new URL(app.getProperty("soap.URL")));
        return mc;
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc =connect();
        String [] categories =mc.mc_project_get_categories(app.getProperty("soap.user"), app.getProperty("soap.password"),BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData=new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId=mc.mc_issue_add(app.getProperty("soap.user"), app.getProperty("soap.password"), issueData);
        IssueData createdIssueData=mc.mc_issue_get(app.getProperty("soap.user"), app.getProperty("soap.password"), issueId);
        return new Issue().withId(createdIssueData.getId().intValue()).withSummary(createdIssueData.getSummary()).
                withDescription(createdIssueData.getDescription()).withProject(new Project().withId(createdIssueData.getProject().
                        getId().intValue()).withName(createdIssueData.getProject().getName()));
    }
}