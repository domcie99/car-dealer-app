package pl.ciesielski.dominik.app.cardealerapp.dao;

import pl.ciesielski.dominik.app.cardealerapp.dao.utils.DatabaseConnectionManager;
import pl.ciesielski.dominik.app.cardealerapp.model.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerDao {

    private Connection getConnection(){
        return DatabaseConnectionManager.getInstance().getConnection();
    }

}
