package pl.ciesielski.dominik.app.cardealerapp.dao.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.ciesielski.dominik.app.cardealerapp.dao.entity.TransactionEntity;
import pl.ciesielski.dominik.app.cardealerapp.dao.utils.SessionFactoryManager;

import java.util.logging.Logger;

public class TransactionRepository {

    private static final Logger LOGGER = Logger.getLogger(TransactionRepository.class.getName());

    private SessionFactory sessionFactory;

    public TransactionRepository() {
        sessionFactory = SessionFactoryManager.getSessionFactory();
    }

    public void create(TransactionEntity transactionEntity) {
        LOGGER.info("create(" + transactionEntity + ")");

        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            session.persist(transactionEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

        LOGGER.info("create(...)=" + transactionEntity);
    }

    public TransactionEntity read(Long id) {
        LOGGER.info("read(" + id + ")");

        Session session = sessionFactory.openSession();
        try {
            TransactionEntity transactionEntity = session.get(TransactionEntity.class, id);
            if (transactionEntity == null) {
                LOGGER.warning("TransactionEntity with ID " + id + " not found.");
            }
            return transactionEntity;
        } finally {
            session.close();
        }
    }

    public void update(TransactionEntity transactionEntity) {
        LOGGER.info("update(" + transactionEntity + ")");

        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            session.merge(transactionEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

        LOGGER.info("update(...)=" + transactionEntity);
    }

    public void delete(Long id) {
        LOGGER.info("delete(" + id + ")");

        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            TransactionEntity transactionEntity = session.get(TransactionEntity.class, id);
            if (transactionEntity != null) {
                session.delete(transactionEntity);
                session.getTransaction().commit();
            } else {
                LOGGER.warning("TransactionEntity with ID " + id + " not found, delete operation skipped.");
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        LOGGER.info("delete(...)=" + id);
    }
}
