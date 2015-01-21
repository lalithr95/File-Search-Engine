import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

class rsad extends Frame implements ActionListener
{
static int port;
static String portstr;
static String ipadd;
Label l1,l2,l3;
TextArea ta1;
TextField t1,t2;
Button b1,b2,b3,b4;
String abcd;
char[] ch3;
BufferedReader br;
Socket s;
int p=17,q=13,e=7,n;
int d=0;
char[] ck;
int c[];
int[] k;
int Q,j,i,m,m1,cnt;
rsad() throws Exception
{
super("CLIENT KEY");
getConnection();
Font font=new Font("Serif", Font.ITALIC, 20);
l1=new Label("encoded key");
l2=new Label("Port");
l3=new Label("IP Address");
t1=new TextField(20);
t2=new TextField(20);

l1.setFont(font);
l2.setFont(font);
l3.setFont(font);

ta1=new TextArea(5,20);
b1=new Button("Receive");
b1.setFont(font);
b2=new Button("Decode key");
b2.setFont(font);
b3=new Button("clear");
b3.setFont(font);
b4=new Button("exit");
b4.setFont(font);
ck=new char[16];
k=new int[16];
ch3=new char[16];
c=new int[16];
setSize(600,300);
    setVisible(true);
    setLayout(new FlowLayout());

add(l1);
add(ta1);
add(l2);
add(t1);
add(l3);
add(t2);
add(b1); add(b2); add(b3); add(b4);
 b1.addActionListener(this);
    b2.addActionListener(this);
    b3.addActionListener(this);
    b4.addActionListener(this);
t2.setText(ipadd);
t1.setText(portstr);
t1.setEnabled(false);
t2.setEnabled(false);
//    t1.setEchoChar('*');
receive();

addWindowListener(new WindowAdapter()
      {
       public void windowClosing(WindowEvent we)
          {
           System.exit(0);
          }
       });
}
public void paint(Graphics g)
{

}
 public void getConnection() throws Exception
   {
 // InetAddress a= InetAddress.getLocalHost();
   //String str2=a.getHostAddress();
//System.out.println("IP address"+Integer.parseInt(str2));
	s=new Socket(ipadd,port);
    br=new BufferedReader(new InputStreamReader (s.getInputStream()));
    //System.out.println(str2);
   }

public void receive()throws Exception
{
while(true)
{
cnt=Integer.parseInt(br.readLine());

        System.out.println("cnt is"+cnt);

       for(i=0;i<cnt;i++)
        {
         c[i]=Integer.parseInt(br.readLine());
          System.out.print("  "+c[i]);
        }
}
}

public void actionPerformed(ActionEvent ae)
   {
String str=ae.getActionCommand();
    if(ae.getSource()==b3)
     {
      ta1.setText("");
      }
	if(ae.getSource()==b1&&str.equals("Receive"))

	{

	  for(i=0;i<cnt;i++)
       ch3[i]=(char)c[i];

      abcd=String.valueOf(ch3,0,cnt);
      ta1.append(abcd);
JOptionPane.showMessageDialog(this,
                                    "Encrypted key received sucessfully",
                                    "client",
                                    JOptionPane.INFORMATION_MESSAGE);

	
	}


	if(ae.getSource()==b2&&str.equals("Decode key"))
	{
	decode();
JOptionPane.showMessageDialog(this,
                                    "The key is decoded and saved in KEY.txt file",
                                    "client",
                                    JOptionPane.INFORMATION_MESSAGE);
	}

 if(ae.getSource()==b4)
     {
       System.exit(0);
     }

}



void decode()
{

Q=(p-1)*(q-1);
n=p*q;


for(i=1;i<Q;i++)
{
if((i*e)%Q==1)
	{
	 d=i;
//System.out.println("value of d is:"+d);
	break;
	}
//System.out.println(d);
}


//Decryption

System.out.println("\n Decryption :");

try
{
FileWriter fw1=new FileWriter("KEY.txt");
for(i=0;i<cnt;i++)
{
m=c[i];
m1=m;
for(j=1;j<d;j++)
{
m=(m1*m)%n;
}
k[i]=m;

ck[i]=(char)k[i];
fw1.write(ck[i]);

System.out.print(ck[i]);
}
fw1.close();
}
catch(Exception e)
{
}

}

public static void main(String args[]) throws Exception
{
portstr=args[0];
port= Integer.parseInt(args[0]);
System.out.println("IPaddress in argument" + args[1]);

ipadd=args[1];
System.out.println("IPadd" + ipadd);
rsad rs=new rsad();

}
}



