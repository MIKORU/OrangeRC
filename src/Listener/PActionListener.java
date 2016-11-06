package Listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.rmi.server.RemoteServer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import Document.SDocument1;
import Server.Shutdown;
import ServerUI.BigScreen;
/**
 * 对每个用户的四个命令监听
 * 控制电脑
 * 关机重启
 * 发送文件
 * @author MIKORU
 *
 */
public class PActionListener extends RMouseListener implements ActionListener {
	int Iorder;
	
	public PActionListener(int Iorder){
		this.Iorder = Iorder;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String order = e.getActionCommand();
		//System.out.println(order);
		if(order.equals("控制电脑")){
			int n=JOptionPane.showConfirmDialog(null,"现在连接到"+Iorder+"号机！","提示",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(PActionListener.class.getResource("/image/orange32.png")));
			if(n==0)
				new BigScreen(Iorder+"号机",Iorder,Server.RemoteServer.ips[Iorder]);
		}else if(order.equals("文件传输")){
			try {
				int n=JOptionPane.showConfirmDialog(null,"确定要发送文件到"+Iorder +"号机？","提示",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(PActionListener.class.getResource("/image/orange32.png")));
				if(n==0)
					new Thread(new SDocument1(Iorder)).start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if(order.equals("关机")){
			try {
				int n=JOptionPane.showConfirmDialog(null,"确定使"+Iorder+"号机关机？","提示",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(PActionListener.class.getResource("/image/orange32.png")));
				if(n==0)
					new Thread(new Shutdown("关机",Iorder)).start();
				//System.out.println("关机ing");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if(order.equals("重启")){
			try {
				int n=JOptionPane.showConfirmDialog(null,"确定重启"+Iorder+"号机？","提示",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(PActionListener.class.getResource("/image/orange32.png")));
				if(n==0)
					new Thread(new Shutdown("重启",Iorder)).start();
				//System.out.println("重启ing");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}


}
