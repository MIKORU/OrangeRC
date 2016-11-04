package Server;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
import ServerUI.GUI;

public class BroderCast implements Runnable {
	private Socket st;
	private Robot robot;
	private Rectangle rect;
	ServerSocket server;
	public BroderCast(){
		try {
			server = new ServerSocket(1153);
			System.out.println("开放端口！");
			robot = new Robot();
			Point p = new Point(0, 0);
		 	Toolkit tool = Toolkit.getDefaultToolkit();  
		 	Dimension dis = tool.getScreenSize();  
		 	rect = new Rectangle(p, dis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
		 	try {
		 		st = server.accept();
		 		System.out.println("连接上了！");
		 		DataOutputStream ous = new DataOutputStream(st.getOutputStream()); 
	            
			 	BufferedImage img = robot.createScreenCapture(rect);
			 	BufferedImage cursor= ImageIO.read(new File(GUI.class.getResource("/image/mouse.png").getPath()));  //把鼠标加载到缓存中
			 	Point p= MouseInfo.getPointerInfo().getLocation();               //获取鼠标坐标
			 	img.createGraphics().drawImage(cursor, p.x, p.y, null); 
			 	
			 	ByteArrayOutputStream temB=new ByteArrayOutputStream();
				ImageIO.write(img,"jpeg",temB);
			 	byte[] data=temB.toByteArray();
			 	ous.writeInt(data.length);
			 	ous.write(data);
			 	ous.flush();
			 	Thread.sleep(100);
			 	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
                if (st != null) {
                    try {
                        st.close();
                    } catch (IOException e) {e.printStackTrace();}
                }
			}
		}
	}
}
