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

    public VehicleEntity read(Long id) {
        LOGGER.info("read(" + id + ")");

        Session session = sessionFactory.openSession();
        try {
            VehicleEntity vehicleEntity = session.get(VehicleEntity.class, id);
            if (vehicleEntity == null) {
                LOGGER.warning("VehicleEntity with ID " + id + " not found.");
            }
            return vehicleEntity;
        } finally {
            session.close();
        }
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

    public void delete(Long id) {
        LOGGER.info("delete(" + id + ")");

        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            VehicleEntity vehicleEntity = session.get(VehicleEntity.class, id);
            if (vehicleEntity != null) {
                session.delete(vehicleEntity);
                session.getTransaction().commit();
            } else {
                LOGGER.warning("VehicleEntity with ID " + id + " not found, delete operation skipped.");
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        LOGGER.info("delete(...)=" + id);
    }

}
