import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.JTextComponent;


public class Mycode extends JFrame implements ActionListener
{
	String parent;
	String[] sfiles;
	JButton search;
	JTextField tf;
	JLabel l;
	JButton jb,open;
	
	JList jl;
	List mylist;
	Font f;
	JScrollPane scroll;
	File path;
	File[] lfiles;
	BorderLayout b;
	Mycode()
	{
		tf = new JTextField(15);
		search = new JButton("Search");
		jb = new JButton("Submit");
		open = new JButton("Open");
		l = new JLabel("Message");
		jl = new JList(lfiles);
		mylist = new List(12,false);
		f = new Font("Courier",Font.BOLD,14);
		scroll = new JScrollPane(mylist);
		setSize(500,600);
		
		setLayout(null);
		setVisible(true);
		add(tf);
		add(jb);
		add(search);
		add(open);
		add(mylist,b.CENTER);
		//add(l,b.CENTER);
		tf.setFont(f);
		mylist.setFont(f);
		/*tf.setBounds(50,35,230,27);
		jb.setBounds(100,75,90,30);
		//l.setBounds(50,20,100,20);
		mylist.setBounds(50,20,100,20);*/
		tf.setBounds(50,20,200,30);
		jb.setBounds(70,70,90,35);
		open.setBounds(180,70,90,35);
		search.setBounds(300,70,90,35);
		mylist.setBounds(20,140,400,400);
		//add(l);
		//jl.setBounds(50,100,300,300);
		
		
		tf.addActionListener(this);
		jb.addActionListener(this);
		open.addActionListener(this);
		mylist.addActionListener(this);
		search.addActionListener(this);
		
		
		
	}
	
	public static void main(String args[])
	{
		Mycode mg = new Mycode();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int i;
		if(e.getSource()== open && (new File(tf.getText()+File.separator+mylist.getSelectedItem()).isDirectory()))
		{
			//parent = tf.getText();
			path = new File(tf.getText()+mylist.getSelectedItem());
			if(mylist.getSelectedItem()== "..")
			{
				tf.setText(new File(tf.getText()).getParent());
				path = new File(tf.getText());
				mylist.removeAll();
				mylist.add("..",0);
				for(i=0;i<sfiles.length;i++)
					mylist.add(sfiles[i],i+1);
				
			}
			else
			{
				parent = tf.getText();
			tf.setText(tf.getText()+mylist.getSelectedItem()+File.separator);
			
			}
			sfiles = path.list();
			
			
			mylist.removeAll();
			mylist.add("..",0);
			for(i=0;i<sfiles.length;i++)
				mylist.add(sfiles[i],i+1);
		}
		else if(e.getSource()== open && (new File(tf.getText()+File.separator+mylist.getSelectedItem()).isFile()))
		{
			try {
				Frame fv = new Fileviewer(tf.getText(),mylist.getSelectedItem());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource()== open && !(new File(tf.getText()).isDirectory()))
				{
				JOptionPane.showMessageDialog(this,(new File(tf.getText())).getAbsolutePath()+" is not a Directory");
				}
		else if(e.getSource()== jb)
		{
		parent = tf.getText();
		mylist.removeAll();
		String p = null;
		if(!tf.getText().isEmpty())
		{
			p = tf.getText();
		}
		p = tf.getText();
		File path = new File(p);
		lfiles = path.listFiles();
		sfiles = path.list();
		
		for(i=0;i<lfiles.length;i++)
			mylist.add(sfiles[i],i);
		
		}
		
		// for search object
		if(e.getSource()==search)
		{
			Filesr fs = new Filesr();
		}
	}
	
}	
	
	class Fileviewer extends JFrame implements ActionListener
	{
		public JButton save,delete;
		public JLabel lab;
		public JTextField jtf;
		public JButton sear;
		public String dirt,fname,s;
		public TextArea text;
		public Font ft;
		public JScrollBar scr;
		public JMenu file, edit,help;
		public JMenuBar mb;
		
		
		Fileviewer(String Dir,String filename) throws IOException
		{
			super();
			// adding menubar
			mb = new JMenuBar();
			this.setJMenuBar(mb);
			file = new JMenu("File");
			file.add(new JMenuItem("New"));
			file.addSeparator();
			file.add(new JMenuItem("Save"));
			file.addSeparator();
			edit = new JMenu("Edit");
			edit.add(new JMenuItem("Copy"));
			edit.add(new JMenuItem("Paste"));
			help = new JMenu("Help");
			help.add(new JMenuItem("About"));
			mb.add(file);
			mb.add(edit);
			mb.add(help);
			
			ft = new Font("Courier",Font.PLAIN,15);
			FileReader f = null;
			char[] buff = new char[4096];
			text = new TextArea("");
			dirt = Dir;
			fname = filename;
			text.setEditable(true);
			text.setFont(ft);
			setLayout(null);
			setSize(600,600);
			this.add(text);
			lab = new JLabel("Test");
			scr = new JScrollBar();
			//text.setText("Hello");
			jtf = new JTextField();
			jtf.setEditable(true);
			save = new JButton("Save");
			sear = new JButton("Search");
			delete = new JButton("Delete");
			this.add(save);
			this.add(jtf);
			this.add(sear);
			this.add(lab);
			this.add(delete);
			jtf.setBounds(20,30,150,30);
			sear.setBounds(210,30,100,35);
			save.setBounds(370,30,80,35);
			delete.setBounds(470,30,80,35);
			text.setBounds(0,100,580,410);
			lab.setBounds(10,10,100,10);
			setVisible(true);
			jtf.setFont(ft);
			jtf.addActionListener(this);
			sear.addActionListener(this);
			save.addActionListener(this);
			delete.addActionListener(this);
			
			
			
			File myfile = new File(dirt,fname);
			f = new FileReader(myfile);
			
			
			int len;
			while((len=f.read(buff))!= -1)
			{
				s = new String(buff,0,len);
				text.append(s);
				
			}
			text.setCaretPosition(0);//set to 1st positions
			f.close();
		}
		public void actionPerformed(ActionEvent ae)
		{
			
			String src;
			
			if(ae.getSource()==sear && (jtf.getText()!=null))
			{
				//search will go here
				int count=0;
				src = jtf.getText().toLowerCase();
				int i,j;
				for(i=0;i<(text.getText().toLowerCase().length() - src.length());i++)
				{
					for(j=0;j<src.length();j++)
					{
						if(text.getText().toLowerCase().charAt(i+j)!=src.toLowerCase().charAt(j))
							break;
						
					}
					if(j == src.length())
						count=1;
				}
				if(count==1)
				{
					lab.setText(src+" Found !!!");
				}
				else
					JOptionPane.showMessageDialog(this, src+" Keyword not found");
				jtf.setText("");
			}
			File fp = new File(dirt,fname);
			FileWriter fw = null;
			if(ae.getSource()==save && fp.canWrite() )
			{
				String buffer = text.getText();
				try {
					fw = new FileWriter(fp);
					
			        FileOutputStream is = new FileOutputStream(fp);
			        OutputStreamWriter osw = new OutputStreamWriter(is);    
			        Writer w = new BufferedWriter(osw);
			        w.write(text.getText());
			        w.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				lab.setText("Saved");
			}
			fp = new File(dirt,fname);
			if(ae.getSource()==delete)
			{
				
				if(fp.delete())
				{
					text.setText("");
					JOptionPane.showMessageDialog(this, "File successfully Deleted");
					dispose();
				}
				
			}
		}
	}
	
	


