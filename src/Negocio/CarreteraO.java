package Negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarreteraO {
    
    private String nombre;
    private String ubicacion;
    private int numcarriles;
    private String estado;
    private String fechaEstado;
    
    private static final String URL = "jdbc:mysql://localhost:3306/Transporte";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "admin";

    public CarreteraO() {
    }

    public CarreteraO(String nombre, String ubicacion, int numcarriles, String estado, String fechaEstado) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.numcarriles = numcarriles;
        this.estado = estado;
        this.fechaEstado = fechaEstado;
    }

    public void insertarEnBaseDeDatos() {
        String sql = "INSERT INTO carretera (nombre, ubicacion, numcarriles, estado, fecha_estado) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setString(1, this.nombre);
            pstmt.setString(2, this.ubicacion);
            pstmt.setInt(3, this.numcarriles);
            pstmt.setString(4, this.estado);
            pstmt.setString(5, this.fechaEstado);

            int filasInsertadas = pstmt.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("¡Inserción exitosa!");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar datos.");
            e.printStackTrace();
        }
    }
    
    public void buscarPorNombre(String nombre) {
        this.nombre = nombre;
        String sql = "SELECT * FROM carretera WHERE nombre = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                this.ubicacion = rs.getString("ubicacion");
                this.numcarriles = rs.getInt("numcarriles");
                this.estado = rs.getString("estado");
                this.fechaEstado = rs.getString("fecha_estado");
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar datos.");
            e.printStackTrace();
        }
    }
    
    public void actualizarCarretera(String nombre, int numcarriles, String estado, String fechaEstado) {
        this.buscarPorNombre(nombre);
        
        String sql = "UPDATE carretera SET numcarriles = ?, estado = ?, fecha_estado = ? WHERE nombre = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setInt(1, numcarriles);
            pstmt.setString(2, estado);
            pstmt.setString(3, fechaEstado);
            pstmt.setString(4, nombre);

            int filasActualizadas = pstmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Carretera actualizada correctamente.");
            } else {
                System.out.println("No se encontró ninguna carretera con ese nombre.");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar datos.");
            e.printStackTrace();
        }
    }
    
    public void eliminarCarretera(String nombre) {
    String sql = "DELETE FROM carretera WHERE nombre = ?";

    try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
         PreparedStatement pstmt = conexion.prepareStatement(sql)) {

        pstmt.setString(1, nombre);

        int filasEliminadas = pstmt.executeUpdate();
        if (filasEliminadas > 0) {
            System.out.println("Carretera eliminada correctamente.");
        } else {
            System.out.println("No se encontró ninguna carretera con ese nombre.");
        }

    } catch (SQLException e) {
        System.out.println("Error al eliminar la carretera.");
        e.printStackTrace();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getNumcarriles() {
        return numcarriles;
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
        return "CarreteraO{" +
                "nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", numcarriles=" + numcarriles +
                ", estado='" + estado + '\'' +
                ", fechaEstado=" + fechaEstado +
                '}';
    }
}
