package pl.ciesielski.dominik.app.cardealerapp.dao.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.logging.Logger;

public class SessionFactoryManager {

    private static final Logger LOGGER = Logger.getLogger(SessionFactoryManager.class.getName());
    private static SessionFactory sessionFactory;

    private SessionFactoryManager() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            initializeSessionFactory();
        }
        return sessionFactory;
    }

    private static void initializeSessionFactory() {
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        try {
            sessionFactory = new MetadataSources(serviceRegistry)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
            LOGGER.info("SessionFactory has been closed.");
        }
    }
}
