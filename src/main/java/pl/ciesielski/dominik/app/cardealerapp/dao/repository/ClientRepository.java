package pl.ciesielski.dominik.app.cardealerapp.dao.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.ciesielski.dominik.app.cardealerapp.dao.entity.ClientEntity;
import pl.ciesielski.dominik.app.cardealerapp.dao.utils.SessionFactoryManager;

import java.util.logging.Logger;

public class ClientRepository {

    private static final Logger LOGGER = Logger.getLogger(ClientRepository.class.getName());

    private SessionFactory sessionFactory;

    public ClientRepository() {
        sessionFactory = SessionFactoryManager.getSessionFactory();
    }

    public void create(ClientEntity clientEntity) {
        LOGGER.info("create(" + clientEntity + ")");

        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            session.persist(clientEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

        LOGGER.info("create(...)=" + clientEntity);
    }

    public ClientEntity read(Long id) {
        LOGGER.info("read(" + id + ")");

        Session session = sessionFactory.openSession();
        try {
            ClientEntity clientEntity = session.get(ClientEntity.class, id);
            if (clientEntity == null) {
                LOGGER.warning("ClientEntity with ID " + id + " not found.");
            }
            return clientEntity;
        } finally {
            session.close();
        }
    }

    public void update(ClientEntity clientEntity) {
        LOGGER.info("update(" + clientEntity + ")");

        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            session.merge(clientEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

        LOGGER.info("update(...)=" + clientEntity);
    }

    public void delete(Long id) {
        LOGGER.info("delete(" + id + ")");

        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            ClientEntity clientEntity = session.get(ClientEntity.class, id);
            if (clientEntity != null) {
                session.delete(clientEntity);
                session.getTransaction().commit();
            } else {
                LOGGER.warning("ClientEntity with ID " + id + " not found, delete operation skipped.");
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        LOGGER.info("delete(...)=" + id);
    }
}
