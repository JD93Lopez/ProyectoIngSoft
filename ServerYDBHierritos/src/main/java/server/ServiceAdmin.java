package server;

import Database.Consulta;
import Database.Insercion;
import Database.Update;
import clases.Cliente;
import clases.Persona;
import clases.Usuario;
import clases.Vendedor;
import interfaces.RMIAdmin;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
    public Vendedor obtenerVendedorMes() throws RemoteException {

        Vendedor vendedorPrueba = new Vendedor(Usuario.TipoUsuario.VENDEDOR, "PepeVentas" , "1234", "Pepe", Persona.TipoDocumento.CEDULA_CIUDADANIA, "63517971");
        return vendedorPrueba;

    }

    @Override
    public String informeVentas() throws RemoteException {
        String test = "Informe Ventas";

        return test;
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
                    ""+usuario.getNombreUsuario(),
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


}