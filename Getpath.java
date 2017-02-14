
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;


public  class Getpath  implements ActionListener {
	public int a,b;
	JFrame f =new JFrame();
	JPanel panel1=new JPanel();
	JPanel panel2=new JPanel();
	JButton src=new JButton("Source");
	JButton dest=new JButton("Destination");
	JButton run=new JButton("Execute");
	JButton build=new JButton("BuildPath");
	JButton random=new JButton("Random");
	JButton reset=new JButton("Reset");
	JButton obstacle=new JButton ("Obstacles");
	 public  JButton [][]button=new JButton[100][100];
	 public int[][] Arr = new int[100][100];
	Color color = UIManager.getColor ( "Panel.background" );
	public Queue<Integer> q1=new LinkedList<Integer>();
	public Queue<Integer> q2=new LinkedList<Integer>();
	public int k,xs,ys,xd,yd;
	  Queue<Integer> queue=new LinkedList<Integer>();
	
	Getpath(int a,int b)
	{
		this.a=a;this.b=b;
		f.setTitle("Shortest Path Finder");
		f.setVisible(true);
		f.setBounds(500, 200, 125, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel1.setBackground(Color.LIGHT_GRAY);
		panel2.setBackground(Color.CYAN);
		
		panel1.setLayout(new GridLayout(a,b));
		f.add(panel1);
		f.add(panel2, BorderLayout.SOUTH);
		panel2.add(src);panel2.add(dest);panel2.add(obstacle);panel2.add(build);
		panel2.add(run);panel2.add(random);
		panel2.add(reset);
		src.addActionListener(this);
	    dest.addActionListener(this);
	    obstacle.addActionListener(this);
	    build.addActionListener(this);
	    run.addActionListener(this);
	    reset.addActionListener(this);
	    random.addActionListener(this);
	    
	    for(int i=0;i<a;i++)
	    {
	    	for(int j=0;j<b;j++)
	    	{
	    		 button[i][j]= new JButton(""+i+","+j+"");
	    		//button [i][j].setActionCommand(""+i+","+j+"");
	    		button [i][j].addActionListener(this);
	    		
	    		button [i][j].setBackground(Color.black);
	    		panel1.add(button [i][j]);
	    	}
	    }
	    f.pack();
	    f.add(panel1);
	    f.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		//System.out.println(e.getActionCommand());
		if(e.getActionCommand().equalsIgnoreCase("Source"))
			k=1;
		if(e.getActionCommand().equalsIgnoreCase("Destination"))
			k=2;
		if(e.getActionCommand().equalsIgnoreCase("Obstacles"))
			k=3;
		if(e.getActionCommand().equalsIgnoreCase("execute"))
			k=4;
		if(e.getActionCommand().equalsIgnoreCase("buildpath"))
			k=5;
		if(e.getActionCommand().equalsIgnoreCase("random"))
			k=6;
		if(e.getActionCommand().equalsIgnoreCase("reset"))
			k=7;
		if(k==1)
		{
			String s=e.getActionCommand();
			if(button[xs][ys].getActionCommand().equals("start"))
			{
				button[xs][ys].setText(""+xs+","+ys+"");
				//Color color = UIManager.getColor ("Panel.background");
       		 button[xs][ys].setBackground(Color.BLACK);
			}
			
			String s1="";
			int i=0;
			for(i=0;i<s.length();i++)
			{
				if(s.charAt(i)==',')
				{
					xs=Integer.parseInt(s1);
					s1="";
				}
				else
					s1=s1+s.charAt(i);
			}
			ys=Integer.parseInt(s1);
			
			if(button[xs][ys].isFocusOwner())
			{
			button[xs][ys].setBackground(Color.GREEN);
			button[xs][ys].setText("start");
			//System.out.println(xs+" "+ys);
			}
		}
		
		if(k==2)
		{
			String s=e.getActionCommand();
			if(button[xd][yd].getActionCommand().equals("end"))
			{
				button[xd][yd].setText(""+xd+","+yd+"");
				//Color color = UIManager.getColor ("Panel.background");
       		 button[xd][yd].setBackground(Color.BLACK);
			}
			
			String s1="";
			int i=0;
			for(i=0;i<s.length();i++)
			{
				if(s.charAt(i)==',')
				{
					xd=Integer.parseInt(s1);
					s1="";
				}
				else
					s1=s1+s.charAt(i);
			}
			yd=Integer.parseInt(s1);
			
			if(button[xd][yd].isFocusOwner())
			{
			button[xd][yd].setBackground(Color.RED);
			button[xd][yd].setText("end");
			//System.out.println(xs+" "+ys);
			}
		}
		// putting the obstacles in between source and destination
		
		if(k==3 && (!e.getActionCommand().equalsIgnoreCase("x"))){
       	 for(int i=0;i<a;++i){
       		 for(int j=0;j<b;++j){
       			 if(button[i][j].isFocusOwner()){
       				 button[i][j].setText("x");
       				 button[i][j].setBackground(Color.WHITE);
       				 Arr[i][j]=-1;
       				 break;
       			 }
       		 }
       	 }
        }else if(k==3 && e.getActionCommand().equalsIgnoreCase("x")){
       	 for(int i=0;i<a;++i){
       		 for(int j=0;j<b;++j){
       			 if(button[i][j].isFocusOwner()){
       				 button[i][j].setText(""+i+","+j+"");
       				 button[i][j].setBackground(Color.BLACK);
       				 Arr[i][j]=0;
       				 break;
       			 }
       		 }
       	 }
        }
		// Executing the path if possible from source to destination
		if(k==4)
		{
			if(Arr[xd][yd]==0)
					{
				JOptionPane.showMessageDialog(null, "Oops! Path does not exist");
					}
			else
			{
				int count=Integer.parseInt(button[xd][yd].getText());
				System.out.println(count);
				int x=xd,y=yd,valid;
				while(count>=1)
				{
					count--;
					if(((x+1)<a  && button[x+1][y].getText()!="x")){
	        			valid=Integer.parseInt(button[x+1][y].getText());
                   	 if(count==valid ){
                   		 button[x+1][y].setBackground(Color.YELLOW);
                   		 x++;
                   		 continue;
                   	 }}
				
					if(((x-1)>=0  && button[x-1][y].getText()!="x")){
	        			valid=Integer.parseInt(button[x-1][y].getText());
                   	 if(count==valid ){
                   		 button[x-1][y].setBackground(Color.YELLOW);
                   		 x--;
                   		 continue;
                   	 }}
					if(((y+1)<b  && button[x][y+1].getText()!="x")){
	        			valid=Integer.parseInt(button[x][y+1].getText());
                   	 if(count==valid ){
                   		 button[x][y+1].setBackground(Color.YELLOW);
                   		 y++;
                   		 continue;
                   	 }}
					if(((y-1)>=0  && button[x][y-1].getText()!="x")){
	        			valid=Integer.parseInt(button[x][y-1].getText());
                   	 if(count==valid ){
                   		 button[x][y-1].setBackground(Color.YELLOW);
                   		 y--;
                   		 continue;
                   	 }}
					
				}
				button[xs][ys].setBackground(Color.GREEN);
				
				
				
		}
		}
		// Building path if possible from source to destination
		if(k==5)
		{
			
			q1.add(xs);q1.add(ys);
			
			while(!q1.isEmpty())
			{
				int x=q1.poll();
				int y=q1.poll();
				if(x+1<a&&button[x+1][y].getText()!="x"&& Arr[x+1][y]==0)
				{
					Arr[x+1][y]=Arr[x][y]+1;
					q1.add(x+1);q1.add(y);
				}
				 if(x-1>=0&&button[x-1][y].getText()!="x"&&Arr[x-1][y]==0)
				{
					 Arr[x-1][y]=Arr[x][y]+1;
					q1.add(x-1);q1.add(y);
				}
				 if(y+1<b&&button[x][y+1].getText()!="x"&&Arr[x][y+1]==0)
				{
					 Arr[x][y+1]=Arr[x][y]+1;
					q1.add(x);q1.add(y+1);
				}
				 if(y-1>=0&&button[x][y-1].getText()!="x"&&Arr[x][y-1]==0)
				{
					 Arr[x][y-1]=Arr[x][y]+1;
					q1.add(x);q1.add(y-1);
				}
			}
			for(int i=0;i<a;i++)
			{
				for(int j=0;j<b;j++)
				{
					if(button[i][j].getText()!="start"&&button[i][j].getText()!="x")
					button[i][j].setText(Integer.toString(Arr[i][j]));
					
				}
			}
		}
		// Randomly putting obstacles in between source and destination
		if(k==6)
		{
			int count=((int)(Math.random()*1000))%((a*b)/5);
			while(count>0)
			{
				count--;
				int i=((int)(Math.random()*1000))%(a);
				int j=((int)(Math.random()*1000))%(b);
				if(!(((i==xs)&&j==ys)||(i==xd&&j==yd)))
				{
					button[i][j].setText("x");
					button[i][j].setBackground(Color.WHITE);
					Arr[i][j]=-1;
				}

			}
			
		}
		// Resetting the entire application
		if(k==7)
		{
			for(int i=0;i<a;i++)
			{
				for(int j=0;j<b;j++)
				{
					button[i][j].setText(""+i+","+j+"");
					button[i][j].setBackground(Color.BLACK);
					Arr[i][j]=0;
				}
			}
			k=0;
		}
	}
		
	public static void main(int a,int b) {
		
         new Getpath(a,b);
	}
	
}
