/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author 3yad
 */
import client.model.clientInt;
import java.rmi.*;
import java.rmi.registry.*;
import java.sql.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Server  {
  
ChatServerImpl obj;
Registry reg;
HashMap<String,clientInt> clientsMapFriend;
ChatServerImpl s;
     public Server(){
		try{
			 obj=new ChatServerImpl();
			//Registry reg =LocateRegistry.getRegistry();
                         reg=LocateRegistry.createRegistry(2000);
			reg.rebind("serverSend",obj);
                        clientsMapFriend=new HashMap<>();
                       
		}catch( Exception e){e.printStackTrace();} 

	}

//end constractor
  /*  public static void main(String[] args) {
        // TODO code application logic here
     //   new Server();
    }*/
    public void stopServer() {
            try {
                reg.unbind("serverSend");
            } catch (Exception ex) {

            }
     }
    
    
    public void startServer() {
            try {
               reg.rebind("serverSend",obj);
            } catch (Exception ex) {

            }
     }
    
    
   // public void callgetFriend(){
    //    clientsMapFriend=s.getFriends();
   // }
    
    public int numberOfOnlineUser(){
      //  return  clientsMapFriend.size();
      return obj.getFriendsNum();
    }

    
    
    public int numberOfAllUser(){
        return obj.getNumberOfAllUser();
    }
    
    
   public int numberOfOfflineUser(){
       return obj.getNumberOfAllUser()-obj.getFriendsNum();
   }
    

   
}
