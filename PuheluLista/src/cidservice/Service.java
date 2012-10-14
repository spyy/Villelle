package cidservice;

import javax.xml.namespace.QName;

public interface Service extends java.rmi.Remote {

    /**
     *
     */
    public String listCalls(String data) throws java.rmi.RemoteException;
}
