/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author dono
 */
public class RenderList extends JLabel implements ListCellRenderer<Object> {
    
    public RenderList(){
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        ImageIcon imgIcon=new ImageIcon(getClass().getResource("images\\USER.gif"));
        setIcon(imgIcon);
        //setText(info.get);
        
        if (isSelected) {
                setBackground(list.getSelectionBackground());
               setForeground(list.getSelectionForeground());
        } else {
               setBackground(list.getBackground());
               setForeground(list.getForeground());
        }
        return this;
    }
    
}
