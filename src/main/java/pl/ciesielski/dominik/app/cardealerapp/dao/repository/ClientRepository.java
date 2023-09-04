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
        // TODO: 23.08.2023 Stworzyć singleton dla poniższego kodu. SessionFactoryManager
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

}
