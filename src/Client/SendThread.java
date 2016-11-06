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
import javax.swing.JOptionPane;

import Listener.PActionListener;

/**
 * 
 * 客户端发送截屏信息
 * 
 * @author MIKORU
 * @date 2016.09.27
 */
class SendThread implements Runnable {  
    private Robot robot;
    private Rectangle rect;
    
    private boolean isAlive = true;
    
    private Socket st;
    private String ip;
  
    public SendThread(String ip) {
    	this.ip = ip;
		try {
			robot = new Robot();
		} catch (AWTException e) {
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
        		DataOutputStream ous = new DataOutputStream(st.getOutputStream()); 
        			
        		//截图图像转byte 传输速度一般
        		BufferedImage img = robot.createScreenCapture(rect);
        		ByteArrayOutputStream imgStream=new ByteArrayOutputStream();
        		ImageIO.write(img,"jpeg",imgStream);
        		byte[] data=imgStream.toByteArray();
        		 	
        		ous.writeInt(data.length);
        		ous.write(data);
        		ous.flush();
        		Thread.sleep(100);
            }catch (Exception e) {
            	JOptionPane.showConfirmDialog(null,"与服务器端断开连接！","提示",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(PActionListener.class.getResource("/image/orange32.png")));
            	isAlive = false;
        	}finally {
				try {
					if(st!=null){
						st.close();
					}
				}catch (IOException e) {}
			}   
        }  
    }  
}