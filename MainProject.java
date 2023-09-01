import java.sql.SQLException;

public class MainProject {

    public static void main(String[] args) throws SQLException {
        // creando instancia de factoría abstracta
        DAOFactory factorOFfactories = new DAOFactory();

        // creando insrtancia de factoría concreta para conecciones MySQL
        MySQLfactory sqlFactory = factorOFfactories.getMySQLfactory();

        // obteniendo instancio de DAO para entidad Persona
        DAOpersona daoPersona = sqlFactory.getDAOPersona();

        // usando DAO para obtener la persona con ID: 1
        Persona p = daoPersona.getPersona(1);

        // imprimiendo resultado
        System.out.println(p);
    }
}
