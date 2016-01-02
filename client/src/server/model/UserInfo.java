
package server.model;
import java.io.*;
import java.util.*;


public class UserInfo implements Serializable {
    
    private String user_Name;
    private String email;
    private String password;
    public UserInfo(){
    
    
    
    }
    public UserInfo(String name,String userEmail,String userPass){
        user_Name=name;
        email=userEmail;
        password=userPass;
        
    
    } 
    
     public UserInfo(String name,String userPass){
        user_Name=name;
       
        password=userPass;
        
    
    } 

    
    public String getUser_Name() {
        return user_Name;
    }

    
    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    
    public String getEmail() {
        return email;
    }

    
    public void setEmail(String email) {
        this.email = email;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }

    
    public String getPassword() {
        return password;
    }
    
    
    
    
}
