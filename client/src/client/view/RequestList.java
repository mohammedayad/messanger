/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import client.model.FriendsRequest;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author dono
 */
public class RequestList extends JButton implements ListCellRenderer<Object> {
     @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        
        FriendsRequest user=(FriendsRequest) value;
        setText(user.getFriendName());
        //setIcon(user.getAccept());
       // setEnabled(list.isEnabled());
         if (isSelected){
                setBackground(list.getSelectionBackground());
               setForeground(list.getSelectionForeground());
        }
         else{
               setBackground(list.getBackground());
               setForeground(list.getForeground());
        }
         setEnabled(true);
         setFont(list.getFont());
         return this;
        
    }
}
