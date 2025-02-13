package Negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class PeajeO {
    
    private String nombre;
    private String ciudad;
    private String ubicacion;
    private int capacidad;
    private int numCabinas;
    private String estado;
    private String fechaEstado;
    
    private static final String URL = "jdbc:mysql://localhost:3306/Transporte";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "admin";

    public PeajeO() {
    }

    public PeajeO(String nombre, String ciudad, String ubicacion, int capacidad, int numCabinas, String estado, String fechaEstado) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.numCabinas = numCabinas;
        this.estado = estado;
        this.fechaEstado = fechaEstado;
    }
    
    public void insertarEnBaseDeDatos() {
        String sql = "INSERT INTO peaje (nombre, ciudad, ubicacion, capacidad, num_cabinas, estado, fecha_estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setString(1, this.nombre);
            pstmt.setString(2, this.ciudad);
            pstmt.setString(3, this.ubicacion);
            pstmt.setInt(4, this.capacidad);
            pstmt.setInt(5, this.numCabinas);
            pstmt.setString(6, this.estado);
            pstmt.setString(7, this.fechaEstado);

            int filasInsertadas = pstmt.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("¡Peaje insertado exitosamente!");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar el peaje.");
            e.printStackTrace();
        }
    }
    
    public void buscarPorNombre(String nombre) {
        this.nombre = nombre;
        String sql = "SELECT * FROM peaje WHERE nombre = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                this.ciudad = rs.getString("ciudad");
                this.ubicacion = rs.getString("ubicacion");
                this.capacidad = rs.getInt("capacidad");
                this.numCabinas = rs.getInt("num_cabinas");
                this.estado = rs.getString("estado");
                this.fechaEstado = rs.getString("fecha_estado");
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar el peaje.");
            e.printStackTrace();
        }
    }

    public void actualizarPeaje(String nombre, int capacidad, int numCabinas, String estado, String fechaEstado) {
        this.buscarPorNombre(nombre);
        String sql = "UPDATE peaje SET capacidad = ?, num_cabinas = ?, estado = ?, fecha_estado = ? WHERE nombre = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setInt(1, capacidad);
            pstmt.setInt(2, numCabinas);
            pstmt.setString(3, estado);
            pstmt.setString(4, fechaEstado);
            pstmt.setString(5, nombre);

            int filasActualizadas = pstmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Peaje actualizado correctamente.");
            } else {
                System.out.println("No se encontró ningún peaje con ese nombre.");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar el peaje.");
            e.printStackTrace();
        }
    }

    public void eliminarPeaje(String nombre) {
        String sql = "DELETE FROM peaje WHERE nombre = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setString(1, nombre);

            int filasEliminadas = pstmt.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("Peaje eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún peaje con ese nombre.");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar el peaje.");
            e.printStackTrace();
        }
    }

    
    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getNumCabinas() {
        return numCabinas;
    }

    public String getEstado() {
        return estado;
    }

    public String getFechaEstado() {
        return fechaEstado;
    }

    public static String getURL() {
        return URL;
    }

    public static String getUSUARIO() {
        return USUARIO;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }
    
    
    @Override
    public String toString() {
        return "Envio{" +
                "nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", numcabinas=" + numCabinas +
                ", capacidad=" + capacidad +
                ", estado='" + estado + '\'' +
                ", fechaEstado=" + fechaEstado +
                '}';
    }
    
    
}
