package pl.ciesielski.dominik.app.cardealerapp.dao;

import pl.ciesielski.dominik.app.cardealerapp.dao.utils.DatabaseConnectionManager;

import java.sql.Connection;

public class SellerDao {

    private Connection getConnection(){
        return DatabaseConnectionManager.getInstance().getConnection();
    }

}
