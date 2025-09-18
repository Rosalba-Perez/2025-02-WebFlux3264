package taller;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class EstudianteDAO {
    public void insertar(Estudiante e){
        String sql = "INSERT INTO estudiantes (nombre, apellido, correo, edad, estadocivil) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexiondb.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, e.getNombre());
                ps.setString(2, e.getApellido());
                ps.setString(3, e.getCorreo());
                ps.setInt(4, e.getEdad());
                ps.setString(5, e.getEstadoCivil());
                ps.executeUpdate();
                System.out.println("Estudiante insertado correctamente");
            } catch(SQLException ex){
                System.out.println("Error al insertar estudiante:" + ex.getMessage());
            }
        }
    
    public void actualizar(Estudiante e){
        String sql = "UPDATE estudiantes SET nombre = ?, apellido = ?, edad = ?, estadocivil = ? WHERE correo = ?";
        try(Connection conn = Conexiondb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellido());
            ps.setInt(3, e.getEdad());
            ps.setString(4, e.getEstadoCivil());
            ps.setString(5, e.getCorreo());
            int filas = ps.executeUpdate();
            if(filas > 0)
                System.out.println("Estudiante actualizado correctamente");
            else 
                System.out.println("No se encontró estudiante con ese correo");
        }catch(SQLException ex){
            System.out.println("Error al actualizar estudiante:" + ex.getMessage());
        }
    }

    public void eliminar(String correo){
        String sql = "DELETE FROM estudiantes WHERE correo=?";
        try(Connection conn = Conexiondb.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, correo);
            int filas = ps.executeUpdate();
            if(filas > 0 )
                System.out.println("estudiante eliminado");
            else
                System.out.println("no existe estudiante con ese correo");
        } catch(SQLException ex){
            System.out.println("error al eliminar estudiante" + ex.getMessage());
        }
    }

    public List<Estudiante> consultarTodos() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes";
        try (Connection conn = Conexiondb.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getInt("edad"),
                        rs.getString("estadocivil")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("error al consultar: " + ex.getMessage());
        }
        return lista;
    }

    public Estudiante consultarPorCorreo(String correo){
        String sql = "SELECT * FROM estudiantes WHERE correo=?";
        try(Connection conn = Conexiondb.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getInt("edad"),
                        rs.getString("estadocivil")
                );
            }
        }catch(SQLException ex){
            System.out.println("error al consultar por correo" + ex.getMessage());
        } return null;
    }
}
