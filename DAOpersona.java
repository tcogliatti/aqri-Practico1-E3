import java.sql.SQLException;
import java.util.ArrayList;

public interface DAOpersona {
    ArrayList<Persona> getAllPersonas() throws SQLException;

    Persona getPersona(int id) throws SQLException;

    void setPersona(Persona p) throws SQLException;

}
