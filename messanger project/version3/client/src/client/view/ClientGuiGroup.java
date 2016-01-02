/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import client.controler.Client;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author 3yad
 */
public class ClientGuiGroup extends javax.swing.JFrame {

    /**
     * Creates new form ClientGui
     */
    Client c;
    String sendMessageTo;
    Vector<String> sendMessageToGroup;
    int number=0;
    public ClientGuiGroup(Client c) {
        
        initComponents();
        
        this.c = c;
        setTitle(c.callGetCurrentUserName());
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
      /* this.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                ClientGui.this.c.terminate();
              // ClientGui.this.setVisible(false);
            }
        });*/
        //this.setVisible(true);
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.out.println(" chat Closed");
              //  ClientGui.this.c.terminate();
               // e.getWindow().dispose();
            }
        });
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        sendFileButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        picAddButton = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(500, 150, 0, 0));

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        sendFileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_file.png"))); // NOI18N
        sendFileButton.setToolTipText("send file");
        sendFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendFileButtonActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        jButton2.setToolTipText("save chat");

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        picAddButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/picture_add.png"))); // NOI18N
        picAddButton.setToolTipText("send image");
        picAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                picAddButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(picAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sendFileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(picAddButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
     //   c.appendMessage(c.callGetCurrentUserName() + ":  " +jTextArea3.getText());
       
        c.appendMessageTogroup(sendMessageToGroup,c.callGetCurrentUserName() + ":  " +jTextArea3.getText());
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void sendFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendFileButtonActionPerformed
        JFileChooser fc=new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("TEXT FILES", new String[]{"txt"});
	fc.setFileFilter(filter);
        int size=0;
        FileInputStream fis=null;
        byte[] b;
        if(fc.showOpenDialog(ClientGuiGroup.this)==JFileChooser.APPROVE_OPTION){
 
            try {
                String path=fc.getSelectedFile().getPath();
                fis = new FileInputStream(path);
                size=fis.available();
                b=new byte[size];
                fis.read(b);
                sendFileFromClient(b);
                fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            
        }
        
    }//GEN-LAST:event_sendFileButtonActionPerformed

    private void picAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_picAddButtonActionPerformed
         JFileChooser fc=new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter( "JPG & png Images", "jpg", "png");
        fc.setFileFilter(filter);
        int size=0;
        FileInputStream fis=null;
        byte[] b;
        if(fc.showOpenDialog(ClientGuiGroup.this)==JFileChooser.APPROVE_OPTION){
 
            try {
                String path=fc.getSelectedFile().getPath();
                fis = new FileInputStream(path);
                size=fis.available();
                b=new byte[size];
                fis.read(b);
                sendImageFromClient(b);
                fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            
        }
    }//GEN-LAST:event_picAddButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public void sendFileFromClient(byte [] file){
       c.callSendFileFromClient(file);
    }
    public void sendImageFromClient(byte [] file){
       c.callSendImageFromClient(file);
    }
    
   /* public void displayText(String msg) {
        jTextArea2.append(msg + "\n");
        jTextArea3.setText("");

    }*/
    public void displayTextToGroup(String msg) {
        jTextArea2.append(msg + "\n");
        jTextArea3.setText("");
        
    }
    public void openGroupChatWindow(Vector<String> friendsName) {
        
        setVisible(true);
        System.out.println("group chat opend");
        sendMessageToGroup=friendsName;
    
    }
    
   /* public void openChatGroupWindow(Vector<String> friendsName,Vector<ClientGuiGroup> gui) {
        for(int i=0;i<gui.size();i++)
        {
           gui.get(i).setVisible(true);
        
        }
        
            
        
        System.out.println("chat opend");
        sendMessageToGroup=friendsName;
        
    }*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JButton picAddButton;
    private javax.swing.JButton sendFileButton;
    // End of variables declaration//GEN-END:variables

    public void requestSaveFile(byte[] b) {
      int choice=  JOptionPane.showConfirmDialog(this,"you recived a file do you want to save it ??!","request save file",JOptionPane.YES_NO_OPTION);
        if(choice==0){
            System.out.println("i am a proving this file");
            JFileChooser fc=new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("TEXT FILES", new String[]{"txt"});
            fc.setFileFilter(filter);
		if (fc.showSaveDialog(ClientGuiGroup.this)==JFileChooser.APPROVE_OPTION)
		{
		String path=fc.getSelectedFile().getPath();
             try{
		FileOutputStream fos=new FileOutputStream(path);
		fos.write(b);
                System.out.println(new String(b));
		fos.close();
		}catch(IOException ex){ex.printStackTrace();} 
        }
        
    }

    
}

    public void doYoyAccept(String fromCurrentUser) {
        int num=  JOptionPane.showConfirmDialog(this,"you recived a Friend Request From '"+fromCurrentUser+"'  do you want to accept it ??!","Friend Request",JOptionPane.YES_NO_OPTION);
        
            if(num==0){
                    boolean res=c.callHasAcc(fromCurrentUser);

                if(!res){
                     JOptionPane.showMessageDialog(this,"cannot add this user as friend","warning",JOptionPane.WARNING_MESSAGE,null);
                    // friendUsername.setText("");
                }
                else{
                    int currentUserId=c.callCheckAmbigFriend(fromCurrentUser);
                        if(currentUserId!=0){
                            c.callAddFriend(currentUserId,fromCurrentUser);
                            this.setVisible(false);

                        }
                        else{
                            JOptionPane.showMessageDialog(this,"friend name already exist","warning",JOptionPane.WARNING_MESSAGE,null);
                        }

                }
    }
        
        
        
        }

    public void requestSaveImage(byte[] b) {
         int choice=  JOptionPane.showConfirmDialog(this,"you recived a image do you want to save it ??!","request save image",JOptionPane.YES_NO_OPTION);
        if(choice==0){
            System.out.println("i am a proving this image");
            JFileChooser fc=new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter( "JPG & png Images", "jpg", "png");
            fc.setFileFilter(filter);
		if (fc.showSaveDialog(ClientGuiGroup.this)==JFileChooser.APPROVE_OPTION)
		{
		String path=fc.getSelectedFile().getPath();
             try{
		FileOutputStream fos=new FileOutputStream(path);
		fos.write(b);
                System.out.println(new String(b));
		fos.close();
		}catch(IOException ex){ex.printStackTrace();} 
        }
        
    }
        
    }

    

    

    
        
        
    
}