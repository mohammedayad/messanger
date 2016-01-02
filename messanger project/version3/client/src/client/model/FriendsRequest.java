/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

import javax.swing.JButton;

/**
 *
 * @author dono
 */
public class FriendsRequest {
    private String FriendName;
    private JButton accept;
    private JButton refuse;
    
    public FriendsRequest(String FriendName,JButton accept,JButton refuse){
        this.FriendName=FriendName;
        this.accept=accept;
        this.refuse=refuse;
    
    
    }

    public String getFriendName() {
        return FriendName;
    }

    public void setFriendName(String FriendName) {
        this.FriendName = FriendName;
    }

    public JButton getAccept() {
        return accept;
    }

    public void setAccept(JButton accept) {
        this.accept = accept;
    }

    public JButton getRefuse() {
        return refuse;
    }

    public void setRefuse(JButton refuse) {
        this.refuse = refuse;
    }
    

    
}
