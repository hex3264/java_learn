package ru.stqa.java_learn.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.java_learn.mantis.model.UserData;
import ru.stqa.java_learn.mantis.model.Users;
import java.util.List;

public class DbHelper {

    private SessionFactory sessionFactory;
    private final ApplicationManager app;

    public DbHelper(ApplicationManager app) {
        this.app = app;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Users users() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery( "from UserData" ).list();
        session.getTransaction().commit();
        session.close();
        return new Users(result);
    }

    public void testHbConnection() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery("from UserData").list();
        for (UserData user : result) {
            System.out.println(user);
        }
        session.getTransaction().commit();
        session.close();
    }
}