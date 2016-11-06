package ClientUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Listener.PActionListener;


public class CBigScreen extends JFrame{
	public static JLabel la = new JLabel();
	private boolean flag = true;
	public CBigScreen(){
		this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                flag=false;
                dispose();
                JOptionPane.showConfirmDialog(null,"自动关闭屏幕广播！","提示",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(CBigScreen.class.getResource("/image/orange32.png")));
            }
        });
	}
	public boolean getFlag(){
		return flag;
	}
	public void display(){
		flag = true;
		this.setTitle("教师广播");
        Toolkit tool = Toolkit.getDefaultToolkit();  
        Dimension dis = tool.getScreenSize();
        
		this.setSize(dis);
		
		this.add(la);
		la.setSize(dis);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		//this.setAlwaysOnTop(true);
	}
	
}
