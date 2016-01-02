/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author 3yad
 */
import server.model.srvInt;
import client.model.clientInt;
import java.rmi.*;
import java.sql.*;
import java.rmi.server.*;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.model.UserInfo;
public class ChatServerImpl extends UnicastRemoteObject implements srvInt{
    
    Vector<clientInt> clientsvectors=new Vector<>();
    HashMap<String,clientInt> clientsMap=new HashMap<>();

    String url="jdbc:mysql://localhost:3306/";
    String Driver="com.mysql.jdbc.Driver";
    String dbname="project";
    String username="root";
    String password="";
    Connection con;
    UserInfo currentUser;
    String UserName;
    String myFriendName;
    
    public ChatServerImpl()throws RemoteException
    {
        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		
            con = DriverManager.getConnection(url+dbname,username,password);
        }catch(Exception e){e.printStackTrace();}    
    }
    
    @Override
    public void tellOthers(String msg) throws RemoteException {
        System.out.println("message recived : " + msg);
        for(clientInt clientRef:clientsvectors)
        {
            try {
                clientRef.receive(msg);
            } catch (RemoteException e) {
                System.out.println("cannot send message to client!!!");
                e.printStackTrace();
            }
        
        
        }
    }
    @Override
    public void tellGroup(String user_Name, Vector<String> sendMessageToGroup, String msg) throws RemoteException {
        System.out.println("message recived : " + msg);
        Vector<clientInt> friends=new Vector<>();
        clientInt me=clientsMap.get(user_Name);
        friends.add(me);
        for(int i=0;i<sendMessageToGroup.size();i++)
        {   if(clientsMap.containsKey(sendMessageToGroup.get(i)))
            {
                friends.add(clientsMap.get(sendMessageToGroup.get(i)));
              //  sendMessageToGroup.remove(i);
              //  sendMessageToGroup.add(user_Name);
                clientsMap.get(sendMessageToGroup.get(i)).openMyFriendChatGroup(sendMessageToGroup,user_Name);

            }
            else
                System.out.println(sendMessageToGroup.get(i) + " is offline now");
        } 
        
        for(clientInt clientRef:friends)
        {
            try {
               // clientRef.openMyFriendsChat(user_Name);
                clientRef.receiveToGroup(msg);
            } catch (RemoteException e) {
                System.out.println("cannot send message to client!!!");
                e.printStackTrace();
            }
        
        
        }
    
    
    }
    
      @Override
    public void requestToStartChat(Vector<String> vecName,String currentName)  {
       
        
       
    }

     @Override
    public void tellFriend(String currentUserName,String friendName, String msg) {
        System.out.println("message recived : " + msg);
        myFriendName=friendName;
        clientInt me=clientsMap.get(currentUserName);
        if(clientsMap.containsKey(friendName))//he is online
        {
        clientInt myFriend=clientsMap.get(friendName);
        
        Vector<clientInt> twoClientChat=new Vector<>();
        twoClientChat.add(me);
        twoClientChat.add(myFriend);
        try {
            myFriend.openMyFriendChat(currentUserName);
        } catch (RemoteException ex) {
            ex.printStackTrace();
           // System.out.println(friendName + " is not online now");
        }
        for(clientInt clientRef:twoClientChat)
        {
            try {
                clientRef.receive(msg);
            } catch (RemoteException e) {
                System.out.println("cannot send message to client!!!");
                e.printStackTrace();
            }
        
        
        }
        
        
        }
        else
             System.out.println(friendName + " is not online now");

    }
    @Override
    public void callMyFriends(String user_Name, Vector<String> friends) throws RemoteException {
         for(String friendName:friends)
        {
           if(clientsMap.containsKey(friendName)){
                clientInt friendsRef=clientsMap.get(friendName);
                 //friendsRef.sendNotify(user_Name + " is online now");
                 friendsRef.sendNotify(user_Name);
                
           }
            
        
        
        }
        
        
    }

    @Override
    public void register(clientInt clientRef) throws RemoteException {
        
       clientsvectors.add(clientRef);
       clientsMap.put(currentUser.getUser_Name(),clientRef);
       System.out.println("Client added : "+currentUser.getUser_Name());
        
    }

    @Override
    public void unregister(clientInt clientRef) throws RemoteException {
         clientsvectors.remove(clientRef);
          System.out.println("Client deleted from vector : "+currentUser.getUser_Name());
      //   System.out.println("Client deleted : "+currentUserName);

        
    }
    @Override
    public void unregisterFromMap(String key) throws RemoteException {
         clientsMap.remove(key);
          System.out.println("Client deleted from map : "+key);
      //   System.out.println("Client deleted : "+currentUserName);

        
    }
    
    

    @Override
    public void addUser(UserInfo ui) throws RemoteException {
        try {
                Statement ps = con.createStatement();
			
		String sql = "INSERT INTO messenger (User_name,Email,password) VALUES ('"+ui.getUser_Name()+"','"+ui.getEmail()+"','"+ui.getPassword()+"')";
			
		ps.executeUpdate(sql);
                        
                        
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
    }

    
    public boolean checkLogin(UserInfo ui) throws RemoteException {
        String sql = "select password from messenger where User_name = '"+ui.getUser_Name()+"'";
        //currentUser=ui;
        try{
            Statement st=con.createStatement();
            ResultSet res = st.executeQuery(sql);
        while(res.next()){
            String dbPassword = res.getString("password");
            if(dbPassword.equals(ui.getPassword())){
                
                currentUser=ui;
             //   currentUserName=new String(currentUser.getUser_Name());
                return true;
            }
        }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return false;
    
    
    }

    @Override
    public Vector<String> getContactUser() {
        String sql = "select friend_name  from friends where User_id = (select id from messenger where User_name='"+currentUser.getUser_Name()+"')";
        Vector<String> contacts=new Vector();
        try{
            Statement st=con.createStatement();
            ResultSet res = st.executeQuery(sql);
            
        while(res.next()){
            String friendName = res.getString("friend_name");
            contacts.add(friendName);
            }
        
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return contacts;
    }

    @Override
    public boolean checkAmbguityName(String userName, String userEmail) throws RemoteException {
          String sql = "select User_name from messenger where User_name='"+userName+"'";
  
          String sql1 = "select Email from messenger where Email ='"+userEmail+"'";

          boolean checkUserName=false;
          boolean checkEmail=false;
          boolean checkResult=false;
        try{
            Statement st=con.createStatement();
            Statement st1=con.createStatement();
            ResultSet res = st.executeQuery(sql);
            ResultSet res1 = st1.executeQuery(sql1);
             if(!res.next()){
               
               checkUserName=true;
               
            }
             if(!res1.next()){
               
               checkEmail=true;
               
            }
             if(checkUserName&&checkEmail)
             {
                 checkResult=true;
             
             
             
             }
             
             
        
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
       
       return checkResult;
    }

    @Override
    public boolean isValidEmailAddress(String email){
        
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }

    @Override
    public String GetCurrentUserName(){
        return currentUser.getUser_Name();
        
    }

    @Override
    public boolean hasAcc(String friendName) throws RemoteException {
         String sql = "select User_name from messenger where User_name = '"+friendName+"'";
        // String sql1 = "select id from messenger where User_name = '"+currrentUserName+"'";
         boolean flag=false;
        try{
           // Statement st1=con.createStatement();
          //  ResultSet res1=st1.executeQuery(sql1);//id current user
            Statement st=con.createStatement();
            ResultSet res = st.executeQuery(sql);
            if( res.next()){
              
                flag=true;
                System.out.println(friendName + " is a member of chat system");
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return flag;
    }

    @Override
    public void AddFriend(int id,String friendName) throws RemoteException {
             
        int num=0;
         String name="";
        try {
            
                Statement ps = con.createStatement();	
		String sql = "INSERT INTO friends VALUES ("+id+",'"+friendName+"')";	
		ps.executeUpdate(sql);
                System.out.println(friendName + " added to Client id num : " +id);
                
                System.out.println("i am added IN FRIEND TABLE id= "+id +"name  "+friendName);
                
                Statement ps2=con.createStatement();
                String sql2="SELECT user_name FROM messenger where id="+id;
                ResultSet res =ps2.executeQuery(sql2);
                 if(res.next()){
                      name=res.getString(1);
                 } 
                 Statement ps3=con.createStatement();
                String sql3="SELECT id FROM messenger where user_name='"+friendName+"'";
                ResultSet res2 =ps3.executeQuery(sql3);
                 if(res2.next()){
                      num=res2.getInt(1);
                 } 
                 
                 Statement ps4 = con.createStatement();	
		String sql4 = "INSERT INTO friends VALUES ("+num+",'"+name+"')";	
		ps4.executeUpdate(sql4);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
                 System.out.println("i am added IN FRIEND TABLE id= "+num +"name  "+name);
    }

    @Override
    public int checkAmbigFriend(String friendName,String clientName) throws RemoteException {
        
       String sql ="select * from friends where friend_name='"+friendName+"' and User_id=(select id from messenger WHERE User_name='"+clientName+"')";
       int clientNameId=0;
       String sql1="select id from messenger WHERE User_name='"+clientName+"'";
      // int resId=0;
          boolean checkResult=false;
        try{
            Statement st=con.createStatement();
            ResultSet res = st.executeQuery(sql);
            Statement st1=con.createStatement();
            ResultSet res1 = st1.executeQuery(sql1);
             if(!res.next()){
               
               checkResult=true;
               System.out.println("they are not be friend yet.....");
                if(res1.next())
               {
                    clientNameId=res1.getInt("id");
                    System.out.println(clientName + " has id number : " +clientNameId);
               
               
               
               }
               
              
              
            }
            /* else{
               
                  String sql1 = "select * from messenger where User_name='"+currentUser.getUser_Name()+"'";
                  Statement st1=con.createStatement();
                    ResultSet res1 = st.executeQuery(sql1);
                    if(res1.next()){
                        int resId2=res1.getInt("id");
                        if(resId2==resId){
                            
                            checkResult=false;
                        }
                    }
                 
                 
             }*/
             
        }catch(Exception e){
           e.printStackTrace();
        }
        
       
       return clientNameId;
    }

    @Override
    public Vector<String> checkOnline(Vector<String> vec) {
        Vector<String> onlineFriends=new Vector<>();
        for(int i=0;i<vec.size();i++)
        {
            if(clientsMap.containsKey(vec.get(i))){
                onlineFriends.add(vec.get(i));
                System.out.println(vec.get(i) + " is online now");
        
            }
        }
        
        return onlineFriends;
        
    }
    
    public HashMap<String,clientInt> getFriends()
    {
        return clientsMap;
    }
  
    public int getFriendsNum()
    {
        return clientsvectors.size();
    }

    public int getNumberOfAllUser(){
        int num=0;
        try {
            
                Statement ps = con.createStatement();
                
			
		String sql = "SELECT count(*) from messenger";
			
		 ResultSet res =ps.executeQuery(sql);
                 if(res.next()){
                     num=res.getInt(1);
                 }

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
                return num;
    }

    @Override
    public void recFile(byte[] b)  {
        try{
            if(clientsMap.containsKey(myFriendName))//he is online
            {
            clientInt sendFileTo=clientsMap.get(myFriendName);
            System.out.println("sending a file to " + myFriendName);
            sendFileTo.reciveFile(b);
            }   
        }catch(Exception ex){ex.printStackTrace();}
    }

    @Override
    public void callFriendRequest(String frindName,String fromCurrentUser) throws RemoteException {
        try{
            
            clientInt friend=clientsMap.get(frindName);
            
            friend.reciveFriendRequest(fromCurrentUser);
               
        }catch(Exception ex){ex.printStackTrace();}
        
        addFriendRequestInDatabase(frindName,fromCurrentUser);
        
    }

   public void addFriendRequestInDatabase(String friendName,String fromCurrentUser){
       
        int num=0;
        try {
                 Statement ps3=con.createStatement();
                String sql3="SELECT id FROM messenger where user_name='"+fromCurrentUser+"'";
                ResultSet res2 =ps3.executeQuery(sql3);
                 if(res2.next()){
                      num=res2.getInt(1);
                 } 
                 
                 Statement ps4 = con.createStatement();	
		String sql4 = "INSERT INTO request VALUES ("+num+",'"+friendName+"')";	
		ps4.executeUpdate(sql4);
	} catch (SQLException ex) {ex.printStackTrace();}
                
    }

    @Override
    public Vector<String> addRequest() throws RemoteException {
       int num=0;
       Vector<String> vecRequest=new Vector<>();
        try {
            String clientName=currentUser.getUser_Name();/////id from current client
            Statement ps3=con.createStatement();
            String sql3="SELECT id FROM messenger where user_name='"+clientName+"'";
            ResultSet res2 =ps3.executeQuery(sql3);
            if(res2.next()){
            num=res2.getInt(1);
            }   
            Statement ps=con.createStatement();
            String sql="SELECT friend_name FROM request where id_num="+num;
            ResultSet res =ps.executeQuery(sql);
            while(res.next()){
                System.out.println(res.getString(1));
                vecRequest.add(res.getString(1));
                  
            }
        }
         catch (Exception ex) {
            ex.printStackTrace();
        }
       return vecRequest; 
    }

    @Override
    public void recImage(byte[] b) throws RemoteException {
         try{
            if(clientsMap.containsKey(myFriendName))//he is online
            {
            clientInt sendFileTo=clientsMap.get(myFriendName);
            System.out.println("sending a file to " + myFriendName);
            sendFileTo.reciveImage(b);
            }   
        }catch(Exception ex){ex.printStackTrace();}
    }

    

  
   }
    
