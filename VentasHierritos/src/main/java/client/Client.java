package client;

import clases.Cliente;
import clases.Producto;
import interfaces.RMIVentas;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class Client implements RMIVentas {

    public static Client client = new Client("localhost","5001","servicioVentas");
    private RMIVentas service;
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
            service = (RMIVentas) Naming.lookup(url);
            return true;
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            return false;
        }
    }

    @Override
    public boolean iniciarSesion(String usuario, String contrasena) throws RemoteException {
        try{
            service = (RMIVentas) Naming.lookup(url);
            return service.iniciarSesion(usuario,contrasena);
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List ListaProductosInventario() throws RemoteException {
        try {
            service = (RMIVentas) Naming.lookup(url);
            return service.ListaProductosInventario();
        }catch (MalformedURLException | RemoteException | NotBoundException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Cliente buscarCliente(String telefono) throws RemoteException {
        try {
            service = (RMIVentas) Naming.lookup(url);
            return service.buscarCliente(telefono);
        }catch (MalformedURLException | RemoteException | NotBoundException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Producto buscarProducto(String codigo) throws RemoteException {
        try {
            service = (RMIVentas) Naming.lookup(url);
            return service.buscarProducto(codigo);
        }catch (MalformedURLException | RemoteException | NotBoundException e){
            e.printStackTrace();
            return null;
        }
    }


}
