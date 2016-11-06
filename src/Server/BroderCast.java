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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Listener.PActionListener;
import ServerUI.GUI;
/**
 * 教师端广播功能
 * 截屏发送给学生端
 * 鼠标无法截图 改为获取鼠标位置并绘制到图像上
 * 
 * 端口1153
 * @author MIKORU
 *
 */
public class BroderCast implements Runnable {
	private Socket st;
	private ServerSocket server;
	
	private Robot robot;
	private Rectangle rect;
	
	
	volatile static boolean flag = true;
	
	public static void StopThread() {
		flag = false;
	}
	public BroderCast(){
		try {
			server = new ServerSocket(1153);
			
			robot = new Robot();
			Point p = new Point(0, 0);
		 	Toolkit tool = Toolkit.getDefaultToolkit();  
		 	Dimension dis = tool.getScreenSize();  
		 	rect = new Rectangle(p, dis);
		} catch (IOException e) {
		} catch (AWTException e) {
		}
		
	}
	@Override
	public void run() {
		while(flag){
		 	try {
		 		st = server.accept();
		 		DataOutputStream ous = new DataOutputStream(st.getOutputStream()); 
	            
			 	BufferedImage img = robot.createScreenCapture(rect);
			 	BufferedImage cursor= ImageIO.read(new File(GUI.class.getResource("/image/mouse.png").getPath()));//把鼠标加载到缓存中
			 	Point p= MouseInfo.getPointerInfo().getLocation();//获取鼠标坐标
			 	img.createGraphics().drawImage(cursor, p.x, p.y, null); 
			 	
			 	ByteArrayOutputStream screen=new ByteArrayOutputStream();
				ImageIO.write(img,"jpeg",screen);
				byte[] data=screen.toByteArray();
			 	
			 	ous.writeInt(data.length);
			 	ous.write(data);
			 	ous.flush();
			 	Thread.sleep(100);
			 	
			} catch (IOException e) {
			} catch (InterruptedException e) {
			}finally {
                if (st != null) {
                    try {
                        st.close();
                    } catch (IOException e) {}
                }
			}
		}
		JOptionPane.showConfirmDialog(null,"屏幕广播已经关闭！","提示",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(PActionListener.class.getResource("/image/orange32.png")));
	}
}
