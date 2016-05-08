package persistance;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by Lora on 2/8/16.
 */
public class SessionFactoryProvider {

    private static SessionFactory sessionFactory;

    public static void createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()). buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            createSessionFactory();
        }
        return sessionFactory;

    }
}
