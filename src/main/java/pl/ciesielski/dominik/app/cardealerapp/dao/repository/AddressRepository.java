package pl.ciesielski.dominik.app.cardealerapp.dao.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.ciesielski.dominik.app.cardealerapp.dao.entity.AddressEntity;
import pl.ciesielski.dominik.app.cardealerapp.dao.utils.SessionFactoryManager;

import java.util.logging.Logger;

public class AddressRepository {

    private static final Logger LOGGER = Logger.getLogger(AddressRepository.class.getName());

    private SessionFactory sessionFactory;

    public AddressRepository() {
        sessionFactory = SessionFactoryManager.getSessionFactory();
    }

    public void create(AddressEntity addressEntity) {
        LOGGER.info("create(" + addressEntity + ")");

        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            session.persist(addressEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

        LOGGER.info("create(...)=" + addressEntity);
    }

    public AddressEntity read(Long id) {
        LOGGER.info("read(" + id + ")");

        Session session = sessionFactory.openSession();
        try {
            AddressEntity addressEntity = session.get(AddressEntity.class, id);
            if (addressEntity == null) {
                LOGGER.warning("AddressEntity with ID " + id + " not found.");
            }
            return addressEntity;
        } finally {
            session.close();
        }
    }

    public void update(AddressEntity addressEntity) {
        LOGGER.info("update(" + addressEntity + ")");

        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            session.merge(addressEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

        LOGGER.info("update(...)=" + addressEntity);
    }

    public void delete(Long id) {
        LOGGER.info("delete(" + id + ")");

        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            AddressEntity addressEntity = session.get(AddressEntity.class, id);
            if (addressEntity != null) {
                session.delete(addressEntity);
                session.getTransaction().commit();
            } else {
                LOGGER.warning("AddressEntity with ID " + id + " not found, delete operation skipped.");
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        LOGGER.info("delete(...)=" + id);
    }
}
