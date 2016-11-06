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
 * ��ÿ���û����ĸ��������
 * ���Ƶ���
 * �ػ�����
 * �����ļ�
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
		if(order.equals("���Ƶ���")){
			int n=JOptionPane.showConfirmDialog(null,"�������ӵ�"+Iorder+"�Ż���","��ʾ",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(PActionListener.class.getResource("/image/orange32.png")));
			if(n==0)
				new BigScreen(Iorder+"�Ż�",Iorder,Server.RemoteServer.ips[Iorder]);
		}else if(order.equals("�ļ�����")){
			try {
				int n=JOptionPane.showConfirmDialog(null,"ȷ��Ҫ�����ļ���"+Iorder +"�Ż���","��ʾ",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(PActionListener.class.getResource("/image/orange32.png")));
				if(n==0)
					new Thread(new SDocument1(Iorder)).start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if(order.equals("�ػ�")){
			try {
				int n=JOptionPane.showConfirmDialog(null,"ȷ��ʹ"+Iorder+"�Ż��ػ���","��ʾ",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(PActionListener.class.getResource("/image/orange32.png")));
				if(n==0)
					new Thread(new Shutdown("�ػ�",Iorder)).start();
				//System.out.println("�ػ�ing");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if(order.equals("����")){
			try {
				int n=JOptionPane.showConfirmDialog(null,"ȷ������"+Iorder+"�Ż���","��ʾ",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(PActionListener.class.getResource("/image/orange32.png")));
				if(n==0)
					new Thread(new Shutdown("����",Iorder)).start();
				//System.out.println("����ing");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}


}
