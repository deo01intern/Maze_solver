
import java.util.*;
import java.util.regex.Pattern;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class Getstarted  {
	JFrame f;
	JButton b;
	JLabel l1,l2;
	JTextField t1,t2;
	
	Getstarted()
	{
		
		 f=new JFrame("Maze Solver");
	     b=new JButton("click me");
	     l1=new JLabel ("Enter Row: ");
	     l2=new JLabel ("Enter Column:");
	     f.getContentPane().setBackground( Color.lightGray );
	    JTextField t1,t2;  
	    t1=new JTextField();  
	    l1.setBounds(50,50,100,100);
	    t1.setBounds(140,85,100,25);  
	    t2=new JTextField();
	    l2.setBounds(50,100,100,100);
	    t2.setBounds(140,140,100,25); 
	    b.setBounds(130,200,85,30);
	    f.add(t1); f.add(t2);
	    f.add(l1);f.add(l2);f.add(b);
	    
	    

	    f.setSize(400,400); 
	    
	    f.setLayout(null);  
	    f.setVisible(true);  
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    b.addActionListener(new ActionListener()
	    		{
	public void actionPerformed(ActionEvent e)
	{
		boolean flag=false;
		try{
			
			if(t1.getText().equals("")||t2.getText().equals(""))
			{
				flag=true;
				
				throw new NumberFormatException("Text Field cannot be Empty");
			}
			if(Pattern.matches("[a-zA-Z]", t1.getText())|Pattern.matches("[a-zA-Z]", t2.getText()))
			{
				flag=true;
				throw new NumberFormatException("That's a string! Enter valid Integer");
			}
			
			 int a=Integer.parseInt(t1.getText());
			 int b=Integer.parseInt(t2.getText());
			 if(a<0|b<0)
			{
				flag=true;
				throw new  NumberFormatException("Enter positive Number");
			}
			else if(a==0|b==0)
			{
				flag=true;
				throw new NumberFormatException("Grid of size 0*0 not possible");
			}
			 Getpath.main(a,b);
		}
		 
		catch(NumberFormatException E)
		{
			if(flag==true)
				JOptionPane.showMessageDialog(null, E.getMessage());
			else
				JOptionPane.showMessageDialog(null, "Enter a valid Integer");
		}
		
	}
	
	    	});
	}
	
	public static void main(String args[])  
    { 
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Getstarted window = new Getstarted();
						window.f.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		
	
}
}
