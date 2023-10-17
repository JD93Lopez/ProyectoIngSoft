package Database;

import clases.Cliente;
import clases.Persona;

import java.sql.*;

public class Update {
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
    public static boolean restarExistencias(int cantidadVendida, String idproducto) {
        boolean bool = false;
        PreparedStatement preparedStatement = null;
        try {
            conectar();

            String sql = "UPDATE productos SET existencia = existencia - ? WHERE idproducto = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,cantidadVendida);
            preparedStatement.setString(2,idproducto);
            preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return bool;
        }

    }
}
