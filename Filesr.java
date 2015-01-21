import java.awt.*;
import java.io.*;
import java.util.StringTokenizer;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import com.knuth.*;

class Filesr extends JFrame implements ActionListener
{
	JTextField d,s;
	Knuth k;
	String dir,srch;
	JButton b;
	Font ft;
	int count=0;
	List l;
	String[] parent;
	int total;
	int i=0,j=0,m=0;
	String[] input;
	File f,temp;
	JScrollPane src;
	JButton open;
	
	Filesr()
	{
		d = new JTextField();
		d.setEditable(true);
		s = new JTextField();
		open = new JButton("Open");
		s.setEditable(true);
		b = new JButton("Search");
		l = new List(15,false);
		ft = new Font("Courier",Font.BOLD,13);
		l.setFont(ft);
		d.setFont(ft);
		s.setFont(ft);
		setLayout(null);
		src = new JScrollPane(l);
		setSize(550,600);
		add(d);
		add(s);
		add(b);
		add(open);
		add(l);
		d.addActionListener(this);
		s.addActionListener(this);
		open.addActionListener(this);
		b.addActionListener(this);
		l.addActionListener(this);
		d.setBounds(50,20,200,30);
		s.setBounds(50,60,200,30);
		b.setBounds(75,110,85,30);
		l.setBounds(50,150,450,400);
		open.setBounds(215,110,90,30);
		setVisible(true);
		f = new File(d.getText());
	}
	public void actionPerformed(ActionEvent ae) throws NullPointerException
	{
		if(ae.getSource() == b)
		{
			i =0;
			m = 0;
			j=0;
			count = 0;
			dir = d.getText();
			srch = s.getText().toLowerCase();
			//File f = new File(dir);
			File[] mlist = f.listFiles();
			String[] slist = f.list();
			
			l.removeAll();
			
			//for fuzzing the input and dividing
			StringTokenizer st = new StringTokenizer(srch,"|");
			total = st.countTokens();
			input = new String[total];
			while(st.hasMoreTokens())
			{
				input[j]=st.nextToken();
				j++;
			}
			for(j=0;j<total;j++)//optional for you need to reduce this
			{
				searchf(new File(dir),input[j]);
			}
			parent = new String[count];
			for (j=0;j<total;j++)
			{
				search(new File(dir),input[j]);
			}
			
		}
		
		//if(ae.getSource()==open && l.getSelectedItem()!=null)
		if(ae.getSource()==open  && l.getSelectedItem()!=null)
		{
			try {
				Fileviewer fvr = new Fileviewer((new File(parent[l.getSelectedIndex()]).getParent()),(new File(l.getSelectedItem()).getName()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(ae.getSource()== open && l.getSelectedIndex()==-1)
		{
			JOptionPane.showMessageDialog(this, "Select File to open");
		}
		
		
	}
	public void searchf(File fp,String p) throws NullPointerException
	{
		File[] files =null;
		try
		{
		
		if (fp.canRead()) {
			
			//if(fp.isFile())
				//files = fp.getParentFile().listFiles();
			if(fp.isDirectory())
				files = fp.listFiles();
			
			for (File temp : files) {
				
			    if (temp.isDirectory()) {
			    	//l.add(temp.getName(),i);
				    
			    	searchf(temp,p);
				
			    } else if(temp.isFile()){
				if ((new Knuth(p.toLowerCase(),(temp.getName().toLowerCase()))).find()) {	
					//
					count++;
				    l.add(temp.getName(),i);
				  //  parent[i]=temp.getPath();
				    i++;
				    
			    }
	 
			}
		    }
			
		
		}
		}catch(Exception e)
		{
			//e.printStackTrace();
		}
	}
	public void search(File fp , String p) throws NullPointerException
	{
		File[] files =null;
		try
		{
		
		if (fp.canRead()) {
			
			//if(fp.isFile())
				//files = fp.getParentFile().listFiles();
			if(fp.isDirectory())
				files = fp.listFiles();
			for (File temp : files) {
				
			    if (temp.isDirectory()) {
			    	//l.add(temp.getName(),i);
				    
			    	search(temp,p);
				
			    } else if(temp.isFile()){
				if ((new Knuth(p.toLowerCase(),(temp.getName().toLowerCase()))).find()) {	
					//
					parent[m]=temp.getPath();
				    m++;
				    
			    }
	 
			}
		    }
		 } else {
			JOptionPane.showMessageDialog(this, "Dont have access");
		 }
		}catch(Exception e)
		{
			//e.printStackTrace();
		}
		
	}
	public static void main(String args[])
	{
		Filesr f = new Filesr();
	}
}