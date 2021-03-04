package gui;

import java.awt.Color;  
 import java.awt.Font;  
 import java.awt.event.FocusAdapter;  
 import java.awt.event.FocusEvent;  
 import javax.swing.JTextField;  
   
 public class HintTextField extends JTextField {  
   
   Font gainFont = new Font("Tahoma", Font.PLAIN, 11);  
   Font lostFont = new Font("Tahoma", Font.ITALIC, 11);  
   
   public HintTextField(final String hint) {  
   
     setText(hint);  
     setFont(new Font("Tahoma", Font.PLAIN, 15));  
     setForeground(Color.GRAY);  
   
     this.addFocusListener(new FocusAdapter() {  
   
       @Override  
       public void focusGained(FocusEvent e) {  
         if (getText().equals(hint)) {  
           setText("");  
           setFont(new Font("Tahoma", Font.PLAIN, 15));  
         } else {  
           setText(getText());  
           setFont(new Font("Tahoma", Font.PLAIN, 15));  
         }  
       }  
   
       @Override  
       public void focusLost(FocusEvent e) {  
         if (getText().equals(hint)|| getText().length()==0) {  
           setText(hint);  
           setFont(new Font("Tahoma", Font.PLAIN, 15));  
           setForeground(Color.GRAY);  
         } else {  
           setText(getText());  
           setFont(new Font("Tahoma", Font.PLAIN, 15));  
           setForeground(Color.BLACK);  
         }  
       }  
     });  
   
   }  
 }  