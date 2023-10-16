package Database;
import clases.*;

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

    public static Cliente obtenerClientePorId(String idcliente) {
        Cliente cliente  = new Cliente();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conectar();

            String sql = "SELECT * FROM clientes WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,idcliente);
            resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                cliente.setNombres(resultSet.getString("nombres"));
                cliente.setDireccion(resultSet.getString("direccion"));
                cliente.setCorreo(resultSet.getString("correo"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setNumDocumento(resultSet.getString("numDocumento"));
                cliente.setClienteFrecuente(resultSet.getBoolean("cliente frecuente"));
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

    public static EmpresaProveedora obtenerEmpresaProveedoraPorId(String idcliente) {
        EmpresaProveedora empresaProveedora  = new EmpresaProveedora();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conectar();

            String sql = "SELECT * FROM empresas_proveedoras WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,idcliente);
            resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                empresaProveedora.setNombre(resultSet.getString("nombre"));
                empresaProveedora.setNit(resultSet.getString("nit"));
                empresaProveedora.setBanco(resultSet.getString("banco"));
                empresaProveedora.setCuentaBancaria(resultSet.getString("cuentaBancaria"));
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
            return empresaProveedora;
        }

    }

    public static FacturaCompra obtenerFacturaCompraPorId(String idfacturaDeCompra) {
        FacturaCompra facturaCompra  = new FacturaCompra();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conectar();

            String sql = "SELECT * FROM facturas_de_compra WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,idfacturaDeCompra);
            resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                facturaCompra.setNombreVendedor(resultSet.getString("nombreVendedor"));
//              facturaCompra.setFormaDePago(resultSet.getString("formaDePago"));
//              facturaCompra.setFechaYHora(resultSet.getString("fechaYHora"));
                facturaCompra.setTotal(Double.parseDouble(resultSet.getString("total")));


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
            return facturaCompra;
        }

    }

    public static FacturaVenta obtenerFacturaVentaPorId(String idfacturaDeVenta) {
        FacturaVenta facturaVenta  = new FacturaVenta();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conectar();

            String sql = "SELECT * FROM empresas_proveedoras WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,idfacturaDeVenta);
            resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                facturaVenta.setFechaYHora(resultSet.getString("fechaYHora"));
                facturaVenta.setConsecutivoDian(Integer.parseInt(resultSet.getString("consecutivoDian")));
                facturaVenta.setFormaDePago(Enum.valueOf(EmpresaProveedora.FormaDePago.class,resultSet.getString("formaDePago")));
                facturaVenta.setTotal(Double.parseDouble(resultSet.getString("cuentaBancaria")));


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
            return facturaVenta;
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
