package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIAdmin extends Remote {
    public boolean iniciarSesion(String usuario, String contrasena) throws RemoteException;

}