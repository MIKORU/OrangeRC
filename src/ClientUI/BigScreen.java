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

public class BigScreen extends JFrame{
	public static JLabel la = new JLabel();
	private boolean flag = true;
	public BigScreen(){
		this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                flag=false;
                dispose();
                JOptionPane.showConfirmDialog(null,"��Ļ�㲥������","��ʾ",JOptionPane.OK_OPTION,JOptionPane.INFORMATION_MESSAGE);
                System.gc(); //��������
            }
        });
	}
	public boolean getFlag(){
		return flag;
	}
	public void display(){
		flag = true;
		this.setTitle("��ʦ�㲥");
        Toolkit tool = Toolkit.getDefaultToolkit();  
        Dimension dis = tool.getScreenSize();
		this.setSize(dis);
		this.setAlwaysOnTop(true);
		this.add(la);
		la.setSize(dis);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
//		this.setAlwaysOnTop(true);
	}
	
}
