package fr.epsi.jeeProject.dao.HSQLImpl;

public class PersistenceManager {
    private String driverName = "com.mysql.jdbc.Driver";

    void loadDriver() throws ClassNotFoundException {
        Class.forName(driverName);
    }
}
