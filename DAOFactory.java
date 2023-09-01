public class DAOFactory {
    public DAOFactory() {
    }

    public DerbyFactory getDerbyFactory() {
        return new DerbyFactory();
    }

    public MySQLfactory getMySQLfactory() {
        return MySQLfactory.getInstance();
    }
}
