package Client;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

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
    private int port;
  
    public SendThread(String ip,int port) {
    	this.ip = ip;
    	this.port = port;
    }  
  
    public void run() {  
        while (isAlive) {
        	try {
        		st = new Socket(ip, port);
        		for (int i = 0; i < 9000; i++) {
	        		os = new ObjectOutputStream(st.getOutputStream());
	        		
	        		robot = new Robot();  
	                Point p = new Point(0, 0);
	                Toolkit tool = Toolkit.getDefaultToolkit();  
	                Dimension dis = tool.getScreenSize();  
	                rect = new Rectangle(p, dis);
	        		
	        		BufferedImage img = robot.createScreenCapture(rect);
	                ImageIcon icon = new ImageIcon(img);  
	                
	                os.writeObject(icon);  
	                os.flush();
        		}
                //Thread.sleep(1000/20);
            }catch (Exception e) {  
            	//e.printStackTrace();
            	System.out.println("错误4");
            	break;
            	//System.exit(0);
        	}finally {
				try {
					if(st!=null){
						os.close();
						st.close();
						System.out.println("Client端口关闭！");
					}
				} catch (IOException e) {}
			}   
        }  
    }  
}