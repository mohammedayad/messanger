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
import java.util.Vector;
public interface clientInt extends Remote{
    void receive(String msg)throws RemoteException;
    void sendNotify(String userName)throws RemoteException;
    void openMyFriendChat(String me)throws RemoteException;

    void reciveFile(byte[] b)throws RemoteException;

    void reciveFriendRequest(String fromCurrentUser)throws RemoteException;
    void reciveImage(byte[] b)throws RemoteException;

    void receiveToGroup(String msg)throws RemoteException;

    void openMyFriendsChat(String user_Name)throws RemoteException;

    void openMyFriendChatGroup(Vector<String> sendMessageToGroup,String user_Name)throws RemoteException;

    


    
    
    
    
}
