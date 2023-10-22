package Database;

import clases.Cliente;
import clases.EmpresaProveedora;
import clases.Persona;
import clases.Usuario;

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
            preparedStatement.executeUpdate();
            bool = true;
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

    public static boolean sumarExistencias(int cantidadComprada, String idproducto) {
        boolean bool = false;
        PreparedStatement preparedStatement = null;
        try {
            conectar();

            String sql = "UPDATE productos SET existencia = existencia + ? WHERE idproducto = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,cantidadComprada);
            preparedStatement.setString(2,idproducto);
            preparedStatement.executeUpdate();
            bool = true;
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
    public static boolean consecutivoDian(String id,int cosecutivo) {
        boolean bool = false;
        PreparedStatement preparedStatement = null;
        try {
            conectar();

            String sql = "UPDATE `db_hierritos`.`facturas_de_venta` SET `consecutivoDian` = ? WHERE (`idfacturaDeVenta` = ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,cosecutivo);
            preparedStatement.setString(2,id);
            preparedStatement.executeUpdate();

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
    public static boolean cambiarFormaDePagoFacturaVenta(String id,String formaDePago) {
        boolean bool = false;
        PreparedStatement preparedStatement = null;
        try {
            conectar();

            String sql = "UPDATE `db_hierritos`.`facturas_de_venta` SET `formaDePago` = ? WHERE (`idfacturaDeVenta` = ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,formaDePago);
            preparedStatement.setString(2,id);
            preparedStatement.executeUpdate();

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
    /*UPDATE `db_hierritos`.`facturas_de_venta` SET `consecutivoDian` = '2' WHERE (`idfacturaDeVenta` = '1');*/

    public static void main(String[] args) {
        Update.restarExistencias(20,"1");
        Update.sumarExistencias(20,"1");
    }

    public static void setExistencias(String text, int idProducto) {
        PreparedStatement preparedStatement = null;
        try {
            conectar();

            String sql = "UPDATE productos SET existencia = ? WHERE idproducto = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(text));
            preparedStatement.setInt(2, idProducto);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void actualizarUsuario(Usuario usuario) {
        PreparedStatement preparedStatement = null;
        try {
            conectar();

            String sql = "UPDATE usuarios SET nombres = ?,telefono = ?,"+
                    "tipoDocumento = ?,numDocumento=?,"+
                    "tipoUsuario=?,nombreUsuario=?,contrasena=? WHERE (idusuario = ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,usuario.getNombres());
            preparedStatement.setString(2,usuario.getTelefono());
            preparedStatement.setString(3,usuario.getTipoDocumento().toString());
            preparedStatement.setString(4,usuario.getNumDocumento());
            preparedStatement.setString(5,usuario.getTipoUsuario().toString());
            preparedStatement.setString(6,usuario.getNombreUsuario());
            preparedStatement.setString(7,usuario.getContrasena());
            preparedStatement.setInt(8,Integer.valueOf(usuario.getId()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void actualizarUsuarioSinContrasena(Usuario usuario) {
        PreparedStatement preparedStatement = null;
        try {
            conectar();

            String sql = "UPDATE usuarios SET nombres = ?,telefono = ?,"+
                    "tipoDocumento = ?,numDocumento=?,"+
                    "tipoUsuario=?,nombreUsuario=? WHERE (idusuario = ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,usuario.getNombres());
            preparedStatement.setString(2,usuario.getTelefono());
            preparedStatement.setString(3,usuario.getTipoDocumento().toString());
            preparedStatement.setString(4,usuario.getNumDocumento());
            preparedStatement.setString(5,usuario.getTipoUsuario().toString());
            preparedStatement.setString(6,usuario.getNombreUsuario());
            preparedStatement.setInt(7,Integer.valueOf(usuario.getId()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void actualizarCliente(Cliente cliente) {
        PreparedStatement preparedStatement = null;
        try {
            conectar();

            String sql = "UPDATE clientes SET nombres = ?,telefono = ?,"+
                    "tipoDocumento = ?,numDocumento=?,direccion=?,correo=?,"+
                    "tipoPersona=?,responsableDeIva=?,clienteFrecuente=? WHERE (idcliente = ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,cliente.getNombres());
            preparedStatement.setString(2,cliente.getTelefono());
            preparedStatement.setString(3,cliente.getTipoDocumento().toString());
            preparedStatement.setString(4,cliente.getNumDocumento());
            preparedStatement.setString(5,cliente.getDireccion());
            preparedStatement.setString(6,cliente.getCorreo());
            preparedStatement.setString(7,cliente.getTipoPersona().toString());
            preparedStatement.setBoolean(8,cliente.getResponsableDeIva());
            preparedStatement.setBoolean(9,false);
            preparedStatement.setInt(10,Integer.valueOf(cliente.getId()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void actualizarEmpresaProveedora(EmpresaProveedora empresaProveedora) {
        PreparedStatement preparedStatement = null;
        try {
            conectar();

            String sql = "UPDATE empresas_proveedoras SET nombre = ?,nit = ?,"+
                    "banco = ?,cuentaBancaria=?,pDescuento=? WHERE (idempresaProveedora = ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,empresaProveedora.getNombre());
            preparedStatement.setString(2,empresaProveedora.getNit());
            preparedStatement.setString(3,empresaProveedora.getBanco());
            preparedStatement.setString(4,empresaProveedora.getCuentaBancaria());
            preparedStatement.setDouble(5,empresaProveedora.getpDescuento());
            preparedStatement.setInt(6,empresaProveedora.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
