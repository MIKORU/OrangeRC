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
 * ��ÿ���û����ĸ��������
 * ���Ƶ���
 * �ػ�����
 * �����ļ�
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
		if(order.equals("���Ƶ���")){
			JOptionPane.showMessageDialog(null,"�������ӵ�"+Iorder+"�Ż�","��ʾ",JOptionPane.DEFAULT_OPTION);
			new BigScreen(Iorder+"�Ż�",Server.RemoteServer.ips[Iorder]);
		}else if(order.equals("�����ļ�")){
			
		}else if(order.equals("�ػ�")){
			try {
				new Thread(new Shutdown("�ػ�",Iorder)).start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if(order.equals("����")){
			try {
				new Thread(new Shutdown("����",Iorder)).start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}


}
