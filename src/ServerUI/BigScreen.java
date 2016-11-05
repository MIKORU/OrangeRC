package ServerUI;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Server.Receive;
import Server.RemoteServer;


/**
 * 
 * 监听端口1123，发送控制屏幕的消息
 * 放大屏幕，达成控制客户端屏幕的效果
 * 
 * @author MIKORU
 * @date 2016.10.26
 * 
 */

@SuppressWarnings("serial")
public class BigScreen extends JFrame{
	private String ip;
	private ObjectOutputStream objectOut ;
	private int order;
	
	public BigScreen(String btnName,int order,String ip){
		this.ip = ip;
		this.order = order;
		display(btnName);
	}
	/**
	 * 向socket1123通道中发送信息
	 * 
	 */
	public void sendEvent(InputEvent event) {
		try {
			Socket st = new Socket(ip,1123);
			
			objectOut = new ObjectOutputStream(st.getOutputStream());
	        objectOut.writeObject(event);
	        objectOut.flush();
	        
		}catch (IOException e) {
		}
    }
	/**
	 * 界面显示设置
	 * 鼠标键盘事件监听
	 */
	private void display(String title){
		JFrame frame = new JFrame(title);
        Toolkit tool = Toolkit.getDefaultToolkit();  
        Dimension dis = tool.getScreenSize();
		frame.setSize(dis);
		frame.add(GUI.la[order]);
		frame.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				sendEvent(e);
			}
			public void keyReleased(KeyEvent e) {
				sendEvent(e);
			}
			public void keyTyped(KeyEvent e) {
			
			}
		});
		frame.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				sendEvent(e);
			}
			public void mouseEntered(MouseEvent e) {
				sendEvent(e);
			}
			public void mouseExited(MouseEvent e) {
				sendEvent(e);
			}
			public void mousePressed(MouseEvent e) {
				sendEvent(e);
			}
			public void mouseReleased(MouseEvent e) {
				sendEvent(e);
			}
		});
		frame.addMouseWheelListener(new MouseWheelListener(){
			public void mouseWheelMoved(MouseWheelEvent e) {
				sendEvent(e); 
			}
		});
		frame.addMouseMotionListener(new MouseMotionListener(){
			public void mouseDragged(MouseEvent e) {
				sendEvent(e);
			}
			public void mouseMoved(MouseEvent e) {
				sendEvent(e);
			}
		});
		frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
	}
}