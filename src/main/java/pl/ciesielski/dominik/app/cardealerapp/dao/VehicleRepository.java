package pl.ciesielski.dominik.app.cardealerapp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import pl.ciesielski.dominik.app.cardealerapp.dao.entity.VehicleEntity;

import java.util.logging.Logger;

public class VehicleRepository {

    private static final Logger LOGGER = Logger.getLogger(VehicleRepository.class.getName());

    private SessionFactory sessionFactory;

    public VehicleRepository() {
        // TODO: 23.08.2023 Stworzyć singleton dla poniższego kodu. SessionFactoryManager
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

    public void create(VehicleEntity vehicleEntity) {
        LOGGER.info("create(" + vehicleEntity + ")");

        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            session.persist(vehicleEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

        LOGGER.info("create(...)=" + vehicleEntity);
    }
}
