package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.server.RemoteServer;

import javax.swing.JButton;
import javax.swing.JOptionPane;

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
public class PActionListener implements ActionListener {
	int Iorder;
	public PActionListener(int Iorder){
		this.Iorder = Iorder;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String order = e.getActionCommand();
		if(order.equals("控制电脑")){
			JOptionPane.showMessageDialog(null,"现在连接到"+Iorder+"号机","提示",JOptionPane.DEFAULT_OPTION);
			new BigScreen(Iorder+"号机",Server.RemoteServer.ips[Iorder]);
		}else if(order.equals("发送文件")){
			
		}else if(order.equals("关机")){
			try {
				new Thread(new Shutdown("关机",Iorder)).start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if(order.equals("重启")){
			try {
				new Thread(new Shutdown("重启",Iorder)).start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}


}
