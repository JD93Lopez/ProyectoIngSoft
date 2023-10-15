package Database;
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

    public static void desconectar(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String obtenerContraseñaPorNombre(String nombreDeUsuario) {
        String contrasena = null;
//        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            if(nombreDeUsuario!=null) {
                /*String url = "jdbc:mysql://localhost:3306/db_hierritos?serverTimezone=UTC";
                String usuarioDB = "root";
                String contrasenaDB = "root";

                connection = DriverManager.getConnection(url, usuarioDB, contrasenaDB);*/
                String sql = "SELECT contrasena FROM usuarios WHERE nombres = ?";

                conectar();

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, nombreDeUsuario);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    contrasena = resultSet.getString("contrasena");
                }

                desconectar();
            }else{
                return "";
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

    public boolean nuevoUsuario(
            String idusuario,String nombres,String telefono,
            String tipoDocumento,String numDocumento,String direccion,
            String correo,String tipoUsuario,String nombreUsuario,
            String contrasena
    ) {
        conectar();
        // TODO Verificar que los argumentos no sean nulos o vacíos
       /* if (nombre == null || email == null || contrasena == null) {
            return false;
        }*/

        // Consulta SQL para insertar un nuevo usuario
        String sql = "INSERT INTO usuarios (idusuario,nombres,telefono,tipoDocumento,numDocumento," +
                "direccion,correo,tipoUsuario,nombreUsuario,contrasena) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, idusuario);
            statement.setString(2, nombres);
            statement.setString(3, telefono);
            statement.setString(4, tipoDocumento);
            statement.setString(5, numDocumento);
            statement.setString(6, direccion);
            statement.setString(7, correo);
            statement.setString(8, tipoUsuario);
            statement.setString(9, nombreUsuario);
            statement.setString(10, contrasena);

            // Ejecutar la consulta de inserción
            int filasAfectadas = statement.executeUpdate();

            connection.close();
            // Si al menos una fila se vio afectada, se considera exitoso
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

/*    public static void main(String[] args) {
        Consulta consulta = new Consulta();
        consulta.nuevoUsuario("6","nombres","telefono","tipoDocumento","numDocumento",
                "direccion","correo","tipoUsuario","nombreUsuario","contrasena");
    }*/

    public static void main(String[] args) {
        Consulta consulta = new Consulta();
        String nombreDeUsuario = "Juan";

        String contrasena = consulta.obtenerContraseñaPorNombre(nombreDeUsuario);

        if (contrasena != null) {
            System.out.println("La contraseña para " + nombreDeUsuario + " es: " + contrasena);
        } else {
            System.out.println("El usuario no se encontró en la base de datos.");
        }
    }



}
