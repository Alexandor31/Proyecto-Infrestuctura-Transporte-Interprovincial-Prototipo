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
public class TerminalO {
    
    private String nombre;
    private String ciudad;
    private String ubicacion;
    private int andenes;
    private int capacidad;
    private String estado;
    private String fechaEstado;
    
    private static final String URL = "jdbc:mysql://localhost:3306/Transporte";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "admin";

    public TerminalO() {
    }

    public TerminalO(String nombre, String ciudad, String ubicacion, int andenes, int capacidad, String estado, String fechaEstado) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.ubicacion = ubicacion;
        this.andenes = andenes;
        this.capacidad = capacidad;
        this.estado = estado;
        this.fechaEstado = fechaEstado;
    }
    
    public void insertarEnBaseDeDatos() {
        String sql = "INSERT INTO terminal (nombre, ciudad, ubicacion, andenes, capacidad, estado, fecha_estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setString(1, this.nombre);
            pstmt.setString(2, this.ciudad);
            pstmt.setString(3, this.ubicacion);
            pstmt.setInt(4, this.andenes);
            pstmt.setInt(5, this.capacidad);
            pstmt.setString(6, this.estado);
            pstmt.setString(7, this.fechaEstado);

            int filasInsertadas = pstmt.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("¡Terminal insertado exitosamente!");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar el terminal.");
            e.printStackTrace();
        }
    }

    public void buscarPorNombre(String nombre) {
        this.nombre = nombre;
        String sql = "SELECT * FROM terminal WHERE nombre = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                this.ciudad = rs.getString("ciudad");
                this.ubicacion = rs.getString("ubicacion");
                this.andenes = rs.getInt("andenes");
                this.capacidad = rs.getInt("capacidad");
                this.estado = rs.getString("estado");
                this.fechaEstado = rs.getString("fecha_estado");
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar el terminal.");
            e.printStackTrace();
        }
    }

    public void actualizarTerminal(String nombre, int andenes, int capacidad, String estado, String fechaEstado) {
        this.buscarPorNombre(nombre);

        String sql = "UPDATE terminal SET andenes = ?, capacidad = ?, estado = ?, fecha_estado = ? WHERE nombre = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setInt(1, andenes);
            pstmt.setInt(2, capacidad);
            pstmt.setString(3, estado);
            pstmt.setString(4, fechaEstado);
            pstmt.setString(5, nombre);

            int filasActualizadas = pstmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Terminal actualizado correctamente.");
            } else {
                System.out.println("No se encontró ningún terminal con ese nombre.");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar el terminal.");
            e.printStackTrace();
        }
    }

    public void eliminarTerminal(String nombre) {
        String sql = "DELETE FROM terminal WHERE nombre = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setString(1, nombre);

            int filasEliminadas = pstmt.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("Terminal eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún terminal con ese nombre.");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar el terminal.");
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

    public int getAndenes() {
        return andenes;
    }

    public int getCapacidad() {
        return capacidad;
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
                ", andenes=" + andenes +
                ", capacidad=" + capacidad +
                ", estado='" + estado + '\'' +
                ", fechaEstado=" + fechaEstado +
                '}';
    }
    
    
}
