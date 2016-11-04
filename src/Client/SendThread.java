package Client;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;

/**
 * 
 * 客户端发送截屏信息
 * 
 * @author MIKORU
 * @date 2016.09.27
 */
class SendThread implements Runnable {  
    Robot robot;  
    ObjectOutputStream os; 
    Rectangle rect;  
    private boolean isAlive = true;  
    Socket st;
    private String ip;
  
    public SendThread(String ip) {
    	this.ip = ip;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	Point p = new Point(0, 0);
	 	Toolkit tool = Toolkit.getDefaultToolkit();  
	 	Dimension dis = tool.getScreenSize();  
	 	rect = new Rectangle(p, dis);
    }  
  
    public void run() {  
        while (isAlive) {
        	try {
        		st = new Socket(ip, 1113);
//        		ZipOutputStream zip = new ZipOutputStream(new DataOutputStream(st.getOutputStream()));
//                zip.setLevel(9);     //压缩级别
//                
//        		robot = new Robot();  
//                Point p = new Point(0, 0);
//                Toolkit tool = Toolkit.getDefaultToolkit();  
//                Dimension dis = tool.getScreenSize();  
//                rect = new Rectangle(p, dis);
//                
//                BufferedImage img = robot.createScreenCapture(rect);
//                zip.putNextEntry(new ZipEntry("screen.jpg"));
//                ImageIO.write(img, "jpg", zip);
//                if(zip!=null)
//                	zip.close();
        			DataOutputStream ous = new DataOutputStream(st.getOutputStream()); 
               
        		 	BufferedImage img = robot.createScreenCapture(rect);
        		 	ByteArrayOutputStream temB=new ByteArrayOutputStream();
        		 	ImageIO.write(img,"jpeg",temB);
               
        		 	byte[] data=temB.toByteArray();
        		 	ous.writeInt(data.length);
        		 	ous.write(data);
        		 	ous.flush();
        		 	Thread.sleep(100);
            }catch (Exception e) {  
            	//e.printStackTrace();
            	System.out.println("连接断开");
            	break;
            	//System.exit(0);
        	}finally {
				try {
					if(st!=null){
						st.close();
						//System.out.println("Client端口关闭！");
					}
				} catch (IOException e) {}
			}   
        }  
    }  
}