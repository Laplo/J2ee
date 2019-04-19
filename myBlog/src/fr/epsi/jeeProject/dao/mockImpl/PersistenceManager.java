package fr.epsi.jeeProject.dao.mockImpl;

public class PersistenceManager {
    private String driverName = "com.mysql.jdbc.Driver";

    void loadDriver() throws ClassNotFoundException {
        Class.forName(driverName);
    }
}
