/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model;

/**
 *
 * @author 3yad
 */
import client.model.clientInt;
import java.rmi.*;
import java.util.Vector;
public interface srvInt extends Remote{

   void tellOthers (String msg) throws RemoteException;
   void tellFriend (String currentUserName,String friendName,String msg) throws RemoteException;
   void register (clientInt clientRef) throws RemoteException;
   void unregister (clientInt clientRef) throws RemoteException;
   void unregisterFromMap(String key) throws RemoteException;
   void addUser(UserInfo ui)throws RemoteException;
   boolean checkLogin(UserInfo ui)throws RemoteException;
   Vector<String> getContactUser()throws RemoteException;
   boolean checkAmbguityName(String useName,String userEmail)throws RemoteException;
   boolean isValidEmailAddress(String email)throws RemoteException;
   String GetCurrentUserName()throws RemoteException;
   boolean hasAcc(String friendName)throws RemoteException;
   void AddFriend(int id,String friendName)throws RemoteException;
   int checkAmbigFriend(String friendName,String clientName) throws RemoteException;
   Vector<String> checkOnline(Vector<String> vec)throws RemoteException;
   void callMyFriends(String user_Name, Vector<String> friends)throws RemoteException;

    void recFile(byte [] b)throws RemoteException;
    void callFriendRequest(String frindName,String fromCurrentUser)throws RemoteException;
    Vector<String> addRequest()throws RemoteException;
    void requestToStartChat(Vector<String> vecName,String currentUser) throws RemoteException;
    void recImage(byte[] b)throws RemoteException;
    void tellGroup(String user_Name, Vector<String> sendMessageToGroup, String msg)throws RemoteException;




}
