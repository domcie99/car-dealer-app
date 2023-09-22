package pl.ciesielski.dominik.app.cardealerapp.dao.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.ciesielski.dominik.app.cardealerapp.dao.entity.SellerEntity;
import pl.ciesielski.dominik.app.cardealerapp.dao.utils.SessionFactoryManager;

import java.util.logging.Logger;

public class SellerRepository {

    private static final Logger LOGGER = Logger.getLogger(SellerRepository.class.getName());

    private SessionFactory sessionFactory;

    public SellerRepository() {
        sessionFactory = SessionFactoryManager.getSessionFactory();
    }

    public SellerEntity create(SellerEntity sellerEntity) {
        LOGGER.info("create(" + sellerEntity + ")");

        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            session.persist(sellerEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

        LOGGER.info("create(...)=" + sellerEntity);
        return sellerEntity;
    }

    public SellerEntity read(Long id) {
        LOGGER.info("read(" + id + ")");

        Session session = sessionFactory.openSession();
        try {
            SellerEntity sellerEntity = session.get(SellerEntity.class, id);
            if (sellerEntity == null) {
                LOGGER.warning("SellerEntity with ID " + id + " not found.");
            }
            return sellerEntity;
        } finally {
            session.close();
        }
    }

    public void update(SellerEntity sellerEntity) {
        LOGGER.info("update(" + sellerEntity + ")");

        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            session.merge(sellerEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

        LOGGER.info("update(...)=" + sellerEntity);
    }

    public void delete(Long id) {
        LOGGER.info("delete(" + id + ")");

        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            SellerEntity sellerEntity = session.get(SellerEntity.class, id);
            if (sellerEntity != null) {
                session.delete(sellerEntity);
                session.getTransaction().commit();
            } else {
                LOGGER.warning("SellerEntity with ID " + id + " not found, delete operation skipped.");
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        LOGGER.info("delete(...)=" + id);
    }
}
