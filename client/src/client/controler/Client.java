
package client.controler;


import client.view.ClientGui;
import client.model.ClientImplemention;
import java.rmi.RemoteException;
import client.model.ClientImplemention;
import client.view.Add_contact;
import client.view.ClientGuiGroup;
import client.view.GroupChatGui;
import client.view.MessengerLayout;
import client.view.NotifyFrame;
import client.view.SignUpGui;
import client.view.Sign_In;
import java.rmi.*;
import java.rmi.registry.*;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.model.UserInfo;
import server.model.srvInt;


public class Client {

    //ClientGui myGui;
    SignUpGui signUp;
    Sign_In signIn;
    MessengerLayout messngerLayout;
    ClientImplemention myImplement;
    Add_contact addContact;
    srvInt s;
    UserInfo clientName;
    HashMap<String,ClientGui> clientChat;
    HashMap<String,Vector> chatGroup;
    HashMap<String,ClientGuiGroup> clientGroupChat;
    ClientGui myGui;
    ClientGui friendGui;
   // ClientGuiGroup chat[];
    Vector<ClientGuiGroup> chat;
    ClientGuiGroup chatMe;
    GroupChatGui groupChat;
    ClientGuiGroup myGroupGui;
    

    public Client() {
        try {
            try {
               //myGui = new ClientGui(this);
               clientChat=new HashMap<>();
               chatGroup=new HashMap<>();
               clientGroupChat=new HashMap<>();
                signIn=new Sign_In(this);
                
                myImplement = new ClientImplemention(this);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            Registry reg = LocateRegistry.getRegistry("127.0.0.1",2000);
            s = (srvInt) reg.lookup("serverSend");
           // s.register(myImplement);
           
        } catch (Exception ex) {
            System.out.println("server not online");
        }
    }
   public void addClientToServer(){
        try {
            s.register(myImplement);
            System.out.println(clientName.getUser_Name()+ " has been added to server ");
        } catch (RemoteException ex) {
            System.out.println("Server is offline");
            
        }
    
    
    
    }
    
    public String callGetCurrentUserName()
    {
        String userName="";
   //     try {
            //userName= s.GetCurrentUserName();
            userName=clientName.getUser_Name();
   //     } catch (RemoteException ex) {
   //         Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
   //     }
        
        return userName;
    
    }

    public void appendMessage(String msg) {
        try {
            s.tellOthers(msg);
        } catch (Exception e) {
            System.out.println("Server is offline");
        }
    }
    public void appendMessageToFriend(String friendName, String msg) {
        try {
            s.tellFriend(clientName.getUser_Name(),friendName, msg);
        } catch (RemoteException ex) {
            System.out.println("Server is offline");
        }
        
    }
    public void appendMessageTogroup(Vector<String> sendMessageToGroup, String msg) {
        try {
            s.tellGroup(clientName.getUser_Name(),sendMessageToGroup, msg);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }

        
        
        
    }
    public void showClientGui(String friendName,ClientGui openChat){
      
         // myGui.setVisible(true);
         if(!clientChat.containsKey(friendName))
         {
             clientChat.put(friendName,openChat);
         
         
         
         }
         
         friendGui=clientChat.get(friendName);
         friendGui.openChatWindow(friendName);
         
             
           // myGui.openChatWindow(friendName);
      
      
      }
    public void showGroupGui(String currentuser,Vector<String> friendsName,ClientGuiGroup openGroupChat){
          // myGui.setVisible(true);
         if(!chatGroup.containsKey(currentuser))
         {
             chatGroup.put(currentuser,friendsName);
         
         
         
         }
         if(!clientGroupChat.containsKey(currentuser))
         {
             clientGroupChat.put(currentuser,openGroupChat);
         
         
         
         }
         myGroupGui=clientGroupChat.get(currentuser);
         myGroupGui.openGroupChatWindow(friendsName);
         
         
        
         
    
    }

    public void display(String msg) {
      //  myGui = new ClientGui(this);
     //   myGui.setVisible(true);
        friendGui.displayText(msg);
    }
    public void displayToGroup(String msg) {
        myGroupGui.displayTextToGroup(msg);
    }

    public void terminate() {
        try {
            s.unregister(myImplement);
            s.unregisterFromMap(clientName.getUser_Name());
        } catch (RemoteException ex) {
            System.out.println("Server is offline");
        }
    }

    public boolean checkSignUp(String firstPass,String confirmPass){
        return firstPass.equals(confirmPass);
        
           
    }   
    
    public void callAddUser(String userName,String userEmail,String firstPass){
        UserInfo userData=new UserInfo(userName,userEmail,firstPass);
        userName=userData.getUser_Name();
            try {
                s.addUser(userData);
            } catch (RemoteException ex) {
                System.out.println("Server is offline");
            }
    }
    
    public boolean callCheckLogin(String userName,String firstPass) {
        UserInfo userData=new UserInfo(userName,firstPass);
        clientName=userData;
        boolean checkFlag=false;
            try {
               checkFlag= s.checkLogin(userData);
            } catch (RemoteException ex) {
                System.out.println("Server is offline");
            }
          return checkFlag;
    }
    
    public Vector<String> callContactUser(){
       Vector<String> myContacts=new Vector<>();
        try{
        myContacts=s.getContactUser();
        }catch (Exception e){System.out.println("Server is offline");}
        
        return myContacts;
    }

   

    public boolean callcheckAmbguityName(String userName, String userEmail)  {
        boolean checkFlag=false;
        try {
           checkFlag= s.checkAmbguityName(userName,userEmail);
        } catch (RemoteException ex) {
            System.out.println("Server is offline");
        }
        return checkFlag;
    }
      public boolean callIsValidEmailAddress(String email){
            boolean isValidate=false;
            try {
                 isValidate=s.isValidEmailAddress(email);
            } catch (RemoteException ex) {
               System.out.println("Server is offline");
            }

                return isValidate;
       }
      

       public boolean callHasAcc(String friendName){
           boolean resultFlag=false;
           if(friendName.equals(clientName.getUser_Name()))
           {
               return resultFlag;
           
           }
           
           else{
           try{
          
               resultFlag= s.hasAcc(friendName);
           
           }catch(RemoteException ex)
            {System.out.println("Server is offline");}
           
           
           
           return resultFlag;
       }
       }
       public void callAddFriend(int id,String friendName){
           try{
             s.AddFriend(id,friendName);
           }catch(RemoteException ex){System.out.println("Server is offline");}
       }

        public int callCheckAmbigFriend(String friendName){
            int flagId=0;
            try {
                flagId=s.checkAmbigFriend(friendName,clientName.getUser_Name());
            } catch (RemoteException ex) {
                System.out.println("Server is offline");
            }
            return flagId;
        }
        
        public void sendNotifyToFriends(Vector<String> friends) {
            
        try {
             s.callMyFriends(clientName.getUser_Name(),friends);
        } catch (RemoteException ex) {
            System.out.println("Server is offline");
        }
            
            
        }
     public static void main(String[] args) {
        new Client();



    }

    public Vector<String> callCheckOnline(Vector<String> vec) {
        Vector<String> myOnlineFriends=new Vector<>();
        try {
            myOnlineFriends=s.checkOnline(vec);
        } catch (RemoteException ex) {
            System.out.println("Server is offline");
        }
        
        return myOnlineFriends;
    }

    public void setNotifyToLabel(String userName) {
        
        new NotifyFrame(userName + " is online now....");
        
    }

    public void callSendFileFromClient(byte []b){
        try {
            s.recFile(b);
        } catch (RemoteException ex) {
            System.out.println("Server is offline");
        }
    }

    public void giveFiletoClient(byte[] b) {
       System.out.println(clientName.getUser_Name() + " reciving a file......");

       friendGui.requestSaveFile(b);
        
    }

    public void sendRequestTo(String toFrind) {
        try {
            s.callFriendRequest(toFrind,clientName.getUser_Name());
        } catch (RemoteException ex) {
            System.out.println("Server is offline");
        }
    }

    public void acceptFriend(String fromCurrentUser) {
       // new ClientGui(this).doYoyAccept(fromCurrentUser);//you have an error here null pointer exception
      messngerLayout= signIn.getmyMessengerLayout();
      messngerLayout.doYoyAccept();
    }

    public Vector<String> callAddRequest() {
        Vector<String> vec=new Vector<>();
        try {
            vec=s.addRequest();
        } catch (RemoteException ex) {
            System.out.println("Server is offline");
        }
        return vec;
    }

    public void startGroupChat(Vector<String> vecName) {

        try {
            s.requestToStartChat(vecName,clientName.getUser_Name());
        } catch (RemoteException ex) {
            System.out.println("Server is offline");
        }
    }

    public void callSendImageFromClient(byte[] b) {
        try {
            s.recImage(b);
        } catch (RemoteException ex) {
            System.out.println("Server is offline");
           }
    }

    public void giveImagetoClient(byte[] b) {
        System.out.println(clientName.getUser_Name() + " reciving a Image......");

       friendGui.requestSaveImage(b);
    }

   

    

   

    

    
}


    
    