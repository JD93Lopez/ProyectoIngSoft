package Client;

import clases.*;
import interfaces.RMIAdmin;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.LinkedList;

public class Client implements RMIAdmin {

    public static Client client = new Client("localhost","5002","servicioAdmin");
    private RMIAdmin service;
    private String ip;
    private String port;
    private String serviceName;
    private String url;

    public Client(String ip, String port, String serviceName){
        this.service = null;
        this.ip = ip;
        this.port = port;
        this.serviceName = serviceName;
        this.url = "rmi://" + ip + ":" + port + "/" + serviceName;
    }

    public boolean isConnected(){
        try{
            service = (RMIAdmin) Naming.lookup(url);
            return true;
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            return false;
        }
    }
    @Override
    public boolean iniciarSesion(String usuario, String contrasena) throws RemoteException {
        try{
            service = (RMIAdmin) Naming.lookup(url);
            return service.iniciarSesion(usuario,contrasena);
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String informeBalanceMensual() throws RemoteException {
        try {
            service = (RMIAdmin) Naming.lookup(url);
            return service.informeBalanceMensual();
        }catch (MalformedURLException | RemoteException | NotBoundException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String informeDevoluciones() throws RemoteException {
        try {
            service = (RMIAdmin) Naming.lookup(url);
            return service.informeDevoluciones();
        }catch (MalformedURLException | RemoteException | NotBoundException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Cliente buscarCliente(String telefono) throws RemoteException {
        try {
            service = (RMIAdmin) Naming.lookup(url);
            return service.buscarCliente(telefono);
        }catch (MalformedURLException | RemoteException | NotBoundException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Usuario buscarUsuario(String telefono) throws RemoteException {
        try {
            service = (RMIAdmin) Naming.lookup(url);
            return service.buscarUsuario(telefono);
        }catch (MalformedURLException | RemoteException | NotBoundException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean actualizarCliente(Cliente cliente) throws RemoteException {
        try {
            service = (RMIAdmin) Naming.lookup(url);
            return service.actualizarCliente(cliente);
        }catch (MalformedURLException | RemoteException | NotBoundException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarUsuario(Usuario usuario) throws RemoteException {
        try {
            service = (RMIAdmin) Naming.lookup(url);
            return service.actualizarUsuario(usuario);
        }catch (MalformedURLException | RemoteException | NotBoundException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean crearUsuario(Usuario usuario) throws RemoteException {
        try {
            service = (RMIAdmin) Naming.lookup(url);
            return service.crearUsuario(usuario);
        }catch (MalformedURLException | RemoteException | NotBoundException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Usuario obtenerUsuario(String nUser, String pass) {
        try {
            service = (RMIAdmin) Naming.lookup(url);
            return service.obtenerUsuario(nUser,pass);
        }catch (MalformedURLException | RemoteException | NotBoundException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public LinkedList<ProductoVenta> informeVentas(String fecha) throws RemoteException {
        try {
            service = (RMIAdmin) Naming.lookup(url);
            return service.informeVentas(fecha);
        }catch (MalformedURLException | RemoteException | NotBoundException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Vendedor informeVendedorMes() throws RemoteException {
        try {
            service = (RMIAdmin) Naming.lookup(url);
            return service.informeVendedorMes();
        }catch (MalformedURLException | RemoteException | NotBoundException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public LinkedList<Vendedor> informeTopVendedoresMes() throws RemoteException {
        try {
            service = (RMIAdmin) Naming.lookup(url);
            return service.informeTopVendedoresMes();
        }catch (MalformedURLException | RemoteException | NotBoundException e){
            e.printStackTrace();
            return null;
        }
    }


}
