/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

/**
 *
 * @author 3yad
 */
import java.rmi.*;
import java.rmi.server.*;
import client.controler.Client;
import client.view.ClientGui;
import client.view.ClientGuiGroup;
import java.util.Vector;
public class ClientImplemention extends UnicastRemoteObject implements clientInt{
   // ClientImplemention ci;
    Client c;
    public ClientImplemention (Client c)throws RemoteException
    {
        this.c=c;
    }

    @Override
    public void receive(String msg) throws RemoteException {
       // Client c=new Client();
        c.display(msg);
        
    }
    @Override
    public void receiveToGroup(String msg) throws RemoteException {
        c.displayToGroup(msg);
    }

    @Override
    public void sendNotify(String userName) throws RemoteException {
        
        c.setNotifyToLabel(userName);
    }
    @Override
    public void openMyFriendsChat(String user_Name) throws RemoteException {
        
    }

    @Override
    public void openMyFriendChat(String me) throws RemoteException {
        
        c.showClientGui(me,new ClientGui(c));
    }
    @Override
    public void openMyFriendChatGroup(Vector<String> sendMessageToGroup,String user_Name) throws RemoteException {
        sendMessageToGroup.remove(c.callGetCurrentUserName());
        sendMessageToGroup.add(user_Name);
        c.showGroupGui(c.callGetCurrentUserName(), sendMessageToGroup, new ClientGuiGroup(c));
    }

    @Override
    public void reciveFile(byte[] b) throws RemoteException {
        
        c.giveFiletoClient(b);
    }

    @Override
    public void reciveFriendRequest(String fromCurrentUser) throws RemoteException {
        c.acceptFriend(fromCurrentUser);
        
        
        
    }

    @Override
    public void reciveImage(byte[] b) throws RemoteException {
       c.giveImagetoClient(b);
    }

    

    

    
    
}
