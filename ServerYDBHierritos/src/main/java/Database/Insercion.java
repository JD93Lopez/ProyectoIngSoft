package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insercion {

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


    public static boolean nuevoUsuario(
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

    public static boolean nuevoCliente(String nombres,String telefono,
            String tipoDocumento,String numDocumento,String direccion,
            String correo,String tipoPersona,String responsableDeIva, String clienteFrecuente)

    {
        conectar();
        // TODO Verificar que los argumentos no sean nulos o vacíos


        // Consulta SQL para insertar un nuevo cliente
        String sql = "INSERT INTO clientes (nombres,telefono,tipoDocumento,numDocumento," +
                "direccion,correo,tipoPersona,responsableDeIva,clienteFrecuente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombres);
            statement.setString(2, telefono);
            statement.setString(3, tipoDocumento);
            statement.setString(4, numDocumento);
            statement.setString(5, direccion);
            statement.setString(6, correo);
            statement.setString(7, tipoPersona);
            statement.setString(8, responsableDeIva);
            statement.setString(9, clienteFrecuente);

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

    public static boolean empresas_proveedoras(
            String idempresaProveedora,String nombre,String nit,
            String banco,String cuentaBancaria,String formasDePago)

    {
        conectar();
        // TODO Verificar que los argumentos no sean nulos o vacíos


        // Consulta SQL para insertar un nuevo cliente
        String sql = "INSERT INTO empresas_proveedoras (idempresaProveedora,nombre,nit,banco,cuentaBancaria,formasDePago" +
                ")VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, idempresaProveedora);
            statement.setString(2, nombre);
            statement.setString(3, nit);
            statement.setString(4, banco);
            statement.setString(5, cuentaBancaria);
            statement.setString(6, formasDePago);

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

    public static boolean facturasDeCompra(String idfacturaDeCompra,String nombreVendedor,
                                    String formasDePago,String fechaYHora,String total)
    {
        conectar();

        String sql = "INSERT INTO facturas_de_compra (idfacturaDeCompra,nombreVendedor,formasDePago,fechaYHora,total"+
                ")VALUES ( ?, ?, ?, ?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, idfacturaDeCompra);
            statement.setString(2, nombreVendedor);
            statement.setString(3, formasDePago);
            statement.setString(4, fechaYHora);
            statement.setString(5, total);

            int filasAfectadas = statement.executeUpdate();

            connection.close();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean facturasDeVenta(String idfacturaDeVenta,String fechaYHora,
                                   String consecutivoDian,String formasDePago,String total)
    {
        conectar();

        String sql = "INSERT INTO facturas_de_venta (idfacturaDeVenta,fechaYHora,consecutivoDian,formasDePago,total"+
                ")VALUES ( ?, ?, ?, ?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, idfacturaDeVenta);
            statement.setString(2, fechaYHora);
            statement.setString(3, consecutivoDian);
            statement.setString(4, formasDePago);
            statement.setString(5, total);

            int filasAfectadas = statement.executeUpdate();

            connection.close();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean ferreterias(String idferreteria,String nombre,
                               String telefono,String nit,String direccion,String correo)
    {
        conectar();

        String sql = "INSERT INTO ferreterias (idfacturaDeVenta,fechaYHora,consecutivoDian,formasDePago,total"+
                "correo)VALUES ( ?, ?, ?, ?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, idferreteria);
            statement.setString(2, nombre);
            statement.setString(3, telefono);
            statement.setString(4, nit);
            statement.setString(5, direccion);
            statement.setString(1,correo);

            int filasAfectadas = statement.executeUpdate();

            connection.close();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean inventarios(String idinventario,String valorVentas,
                               String fecha)
    {
        conectar();

        String sql = "INSERT INTO inventarios (idinventario,valorVentas,fecha"+")VALUES ( ?, ?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, idinventario);
            statement.setString(2, valorVentas);
            statement.setString(3, fecha);

            int filasAfectadas = statement.executeUpdate();

            connection.close();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean productos(String idproducto,String codigo,String nombre,
                             String descripcion,String existencia,String pDescuento,
                             String pIva,String precioCompra,String precioVenta,
                             String cantidadMinima,String cantidadMaxima)
    {
        conectar();

        String sql = "INSERT INTO productos (idproducto,codigo,nombre,descripcion,existencia," +
                "pDescuento,pIva,precioCompra,precioVenta,cantidadMinima,cantidadMaxima) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, idproducto);
            statement.setString(2, codigo);
            statement.setString(3, nombre);
            statement.setString(4, descripcion);
            statement.setString(5, existencia);
            statement.setString(6, pDescuento);
            statement.setString(7, pIva);
            statement.setString(8, precioCompra);
            statement.setString(9, precioVenta);
            statement.setString(10, cantidadMinima);
            statement.setString(11, cantidadMaxima);


            int filasAfectadas = statement.executeUpdate();

            connection.close();
            // Si al menos una fila se vio afectada, se considera exitoso
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
         Insercion.nuevoUsuario("7","nombres","telefono","tipoDocumento","numDocumento",
        "direccion","correo","tipoUsuario","nombreUsuario","contrasena");
    }

}