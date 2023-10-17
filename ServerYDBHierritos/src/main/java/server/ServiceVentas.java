package server;

import Database.Consulta;
import Database.Insercion;
import clases.Cliente;
import clases.Producto;
import clases.Usuario;
import interfaces.RMIVentas;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServiceVentas extends UnicastRemoteObject implements RMIVentas {

    private boolean sesionIniciada = false;
    protected ServiceVentas() throws RemoteException {

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
    public List listaProductosInventario() throws RemoteException {
        return Consulta.listaProductosStock();
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
    public Producto buscarProducto(String codigo) throws RemoteException {
        Producto productoPrueba = new Producto("Codigo2","nombreTornillo",  "descripcionLargo",  200,  3,  0.19,  100);
        return productoPrueba;
    }

    @Override
    public int agregarCliente(Cliente cliente) throws RemoteException {
        int bool = -2;
        try{
            Insercion.nuevoCliente(
                    cliente.getNombres(),
                    cliente.getTelefono(),
                    cliente.getTipoDocumento().toString(),
                    cliente.getNumDocumento(),
                    cliente.getDireccion(),
                    cliente.getCorreo(),
                    cliente.getTipoPersona().toString(),
                    (cliente.getResponsableDeIva()?1:0)+"",
                    ""+(cliente.getClienteFrecuente()?1:0)
            );

            cliente = Consulta.obtenerClientePorTelefono(cliente.getTelefono());
            try{
                bool = Integer.valueOf(cliente.getId());
            }catch (Exception e){
                bool = -3;
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return bool;
        }
    }

}
