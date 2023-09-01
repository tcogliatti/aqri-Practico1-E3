import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

public class MySQLfactory {
    private static final MySQLfactory instance = new MySQLfactory();
    private final String driver = "com.mysql.cj.jdbc.Driver";

    private String uri;

    private MySQLfactory() {
        // set lenguage & regiona config compatible
        Locale.setDefault(new Locale("en", "US"));
        this.uri = "jdbc:mysql://localhost:3306/db_tp2e2_arqui";

        try {
            Class.forName(this.driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static MySQLfactory getInstance() {
        return instance;
    }

    public void setUrlMySQL(String url) {
        this.uri = "jdbc:mysql://" + url;
    }

    public DAOpersona getDAOPersona() {
        return new MySQLdaoPersona();
    }

    protected Connection createConn() throws SQLException {
        Connection conn = DriverManager.getConnection(uri, "root", "");
        conn.setAutoCommit(false);
        return conn;
    }

    protected void closeConnection(Connection conn) throws SQLException {
        conn.close();
    }

// ---------------------------------------------------------------------------------//
    public class MySQLdaoPersona extends MySQLfactory implements DAOpersona {

        @Override
        public ArrayList<Persona> getAllPersonas() throws SQLException {
            Connection conn = super.createConn();
            String select = "SELECT p.*, d.* FROM persona";
//            String select = "SELECT p.*, d.* FROM persona p INNER JOIN direccion d ON p.direccion_id = d.id";

            PreparedStatement ps = conn.prepareStatement(select);
            ResultSet rs = ps.executeQuery();

            ArrayList<Persona> personas = new ArrayList<>();
            while (rs.next()) {
                Persona p = new Persona(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
                personas.add(p);
            }

            super.closeConnection(conn);

            return personas;
        }

        @Override
        public Persona getPersona(int id) throws SQLException {
            Connection conn = super.createConn();
            try {
                String select = "SELECT p.* FROM persona p WHERE p.id = ?";

                PreparedStatement ps = conn.prepareStatement(select);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if (rs.next())
                    return new Persona(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
                else
                    return null;
            } finally {
                super.closeConnection(conn);
            }
        }

        @Override
        public void setPersona(Persona p) throws SQLException {
            Connection conn = super.createConn();

            String insert = "INSERT INTO persona(nombre, edad, direccion_id) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getEdad());
            ps.setInt(3, p.getDir());
            ps.executeUpdate();
            ps.close();
            conn.commit();

            super.closeConnection(conn);
        }
    }

}
