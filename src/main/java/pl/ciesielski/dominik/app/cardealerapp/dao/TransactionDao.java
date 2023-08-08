package pl.ciesielski.dominik.app.cardealerapp.dao;

import pl.ciesielski.dominik.app.cardealerapp.dao.utils.DatabaseConnectionManager;
import pl.ciesielski.dominik.app.cardealerapp.model.Transaction;
import pl.ciesielski.dominik.app.cardealerapp.model.Client;
import pl.ciesielski.dominik.app.cardealerapp.model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao{

    private Connection getConnection(){
        return DatabaseConnectionManager.getInstance().getConnection();
    }

}
