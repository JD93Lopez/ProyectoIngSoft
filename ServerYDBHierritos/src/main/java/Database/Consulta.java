package Database;
import clases.Cliente;
import clases.Persona;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Consulta {

    public static Connection connection;
    public static void conectar(){
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_hierritos?serverTimezone=UTC",
                    "root",
                    "root"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String obtenerContraseñaPorNombre(String nombreDeUsuario) {
        String contrasena = null;
//        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

/*          String url = "jdbc:mysql://localhost:3306/db_hierritos?serverTimezone=UTC";
            String usuarioDB = "root";
            String contrasenaDB = "root";

            connection = DriverManager.getConnection(url, usuarioDB, contrasenaDB);*/
            conectar();

            String sql = "SELECT contrasena FROM usuarios WHERE nombres = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,nombreDeUsuario);
            resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                contrasena = resultSet.getString("contrasena");
                //TODO recibir tipoUsuario
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {


            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return contrasena;
        }

    }

    public static Cliente obtenerClientePorTelefono(String telefono) {
        Cliente cliente  = new Cliente();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conectar();

            String sql = "SELECT * FROM clientes WHERE telefono = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,telefono);
            resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                cliente.setNombres(resultSet.getString("nombres"));
                cliente.setDireccion(resultSet.getString("direccion"));
                cliente.setCorreo(resultSet.getString("correo"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setNumDocumento(resultSet.getString("numDocumento"));
                String tipoPersona=resultSet.getString("tipoPersona");
                cliente.setTipoDocumento(Enum.valueOf(Persona.TipoDocumento.class,resultSet.getString("tipoDocumento")));
                cliente.setTipoPersona(Enum.valueOf(Cliente.TipoPersona.class,resultSet.getString("tipoPersona")));
                cliente.setResponsableDeIva(resultSet.getBoolean("responsableDeIva"));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {


            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return cliente;
        }

    }

    public static void main(String[] args) {
        Cliente cliente = Consulta.obtenerClientePorTelefono("3016995315");
        System.out.println(cliente.getNombres()+" "+cliente.getResponsableDeIva()+" "+cliente.getTipoDocumento()+cliente.getTipoPersona());
    }

/*    public static void main(String[] args) {
        String nombreDeUsuario = "Juan";

        String contrasena = Consulta.obtenerContraseñaPorNombre(nombreDeUsuario);

        if (contrasena != null) {
            System.out.println("La contraseña para " + nombreDeUsuario + " es: " + contrasena);
        } else {
            System.out.println("El usuario no se encontró en la base de datos.");
        }
    }*/



}
