package server;

import Database.Consulta;
import Database.Insercion;
import Database.Update;
import clases.*;
import interfaces.RMIAdmin;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.LinkedList;

public class ServiceAdmin extends UnicastRemoteObject implements RMIAdmin {
    private boolean sesionIniciada = false;

    protected ServiceAdmin() throws RemoteException {

    }
    @Override
    public boolean iniciarSesion(String usuario, String contrasena) throws RemoteException {
        Consulta consulta = new Consulta();
        String contrasenaDB = consulta.obtenerContraseñaPorNombre(usuario);
        if(contrasenaDB!=null&&contrasena!=null){
            if(contrasenaDB.equals(contrasena)){
                return true;
            }
        }
        return false;
    }


    @Override
    public String informeBalanceMensual() throws RemoteException {
        String test = "Informe Balance Mensual";

        return test;
    }

    @Override
    public String informeDevoluciones() throws RemoteException {
        String test = "Informe Devoluciones";

        return test;
    }

    @Override
    public Cliente buscarCliente(String telefono) throws RemoteException {

        Cliente cliente = null;
        try {
            cliente = Consulta.obtenerClientePorCedula(telefono);
            if (cliente.getId() == null) {
                cliente = Consulta.obtenerClientePorTelefono(telefono);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return cliente;
    }

    @Override
    public Usuario buscarUsuario(String telefono) throws RemoteException {
        Usuario usuario = null;
        try {
            usuario = Consulta.obtenerUsuarioPorNombre(telefono,"");
            if(usuario==null) {
                usuario = Consulta.obtenerUsuarioPorId(telefono);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return usuario;
    }

    @Override
    public boolean actualizarCliente(Cliente cliente) throws RemoteException {
        if(cliente.getId()!=null){
            Update.actualizarCliente(cliente);
        }else{
            Insercion.nuevoCliente(
                    ""+cliente.getNombres(),
                    ""+cliente.getTelefono(),
                    ""+cliente.getTipoDocumento(),
                    ""+cliente.getNumDocumento(),
                    ""+cliente.getDireccion(),
                    ""+cliente.getCorreo(),
                    ""+cliente.getTipoPersona(),
                    ""+cliente.getResponsableDeIva(),
                    "0"
            );
        }
        return true;
    }

    @Override
    public boolean actualizarUsuario(Usuario usuario) throws RemoteException {
        boolean ack=false;
        try {
            if(usuario.getContrasena().equals("")){
                Update.actualizarUsuarioSinContrasena(usuario);
            }else {
                Update.actualizarUsuario(usuario);
            }
            ack=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return ack;
    }

    @Override
    public boolean crearUsuario(Usuario usuario) throws RemoteException {
        boolean ack=false;
        try {
            Insercion.nuevoUsuario(
                    ""+usuario.getNombres(),
                    ""+usuario.getTelefono(),
                    ""+usuario.getTipoDocumento(),
                    ""+usuario.getNumDocumento(),
                    "",
                    "",
                    ""+usuario.getTipoUsuario(),
                    ""+usuario.getNombreUsuario(),
                    ""+usuario.getContrasena()
            );
            ack=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return ack;
    }

    @Override
    public Usuario obtenerUsuario(String nUser, String pass) {
        return Consulta.obtenerUsuarioPorNombre(nUser,pass);
    }

    public LinkedList<ProductoVenta>  informeVentas ( ) {
        LinkedList<ProductoVenta> listProductVenta = new LinkedList<>();
        try {

            listProductVenta = Consulta.obtenerVentasPorProducto();

        } catch (Exception e){
        e.printStackTrace();
        }
        return listProductVenta;
    }

    public Vendedor informeVendedorMes() {
        Calendar calendar = Calendar.getInstance();
        // Obtén el año actual
        int year = calendar.get(Calendar.YEAR);
        // Obtén el mes actual
        int month = calendar.get(Calendar.MONTH+1);
        return Consulta.obtenerVendedorMes(year, month);
    }
    public LinkedList<Vendedor> informeTopVendedoresMes() {
        Calendar calendar = Calendar.getInstance();
        // Obtén el año actual
        int year = calendar.get(Calendar.YEAR);
        // Obtén el mes actual
        int month = calendar.get(Calendar.MONTH) + 1;

        LinkedList list = Consulta.obtenerTopVendedoresMes(year, month);

        return Consulta.obtenerTopVendedoresMes(year, month);
    }

    public LinkedList<ProductoVenta> obtenerComprasPorProducto() {
        LinkedList<ProductoVenta> listProductCompra = new LinkedList<>();
        try {
            listProductCompra = Consulta.obtenerComprasPorProducto();
        } catch (Exception e){
            e.printStackTrace();
        }
        return listProductCompra;
    }

    @Override
    public void actualizarDescuentoFrecuente(String text) throws RemoteException {
        Update.setDescuentoFrecuente(text);
    }

    @Override
    public void actualizarCorreo(String text) throws RemoteException {
        Update.setCorreo(text);
    }

    @Override
    public void actualizarNombre(String text) throws RemoteException {
        Update.setNombre(text);
    }

    @Override
    public void actualizarNit(String text) throws RemoteException {
        Update.setNit(text);
    }

    @Override
    public void actualizarDireccion(String text) throws RemoteException {
        Update.setDireccion(text);
    }

    @Override
    public void actualizarTelefono(String text) throws RemoteException {
        Update.setTelefono(text);
    }


}