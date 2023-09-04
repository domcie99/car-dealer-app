package pl.ciesielski.dominik.app.cardealerapp.dao.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.ciesielski.dominik.app.cardealerapp.dao.entity.VehicleEntity;
import pl.ciesielski.dominik.app.cardealerapp.dao.utils.SessionFactoryManager;

import java.util.logging.Logger;

public class VehicleRepository {

    private static final Logger LOGGER = Logger.getLogger(VehicleRepository.class.getName());

    private SessionFactory sessionFactory;

    public VehicleRepository() {
        // TODO: 23.08.2023 Stworzyć singleton dla poniższego kodu. SessionFactoryManager
        sessionFactory = SessionFactoryManager.getSessionFactory();
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

    public void read(Long id) {
    }

    public void update(VehicleEntity vehicleEntity) {
        LOGGER.info("update(" + vehicleEntity + ")");

        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            session.merge(vehicleEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

        LOGGER.info("update(...)=" + vehicleEntity);
    }
}
