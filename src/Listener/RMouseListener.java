package Listener;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import Document.SDocument;
import Document.SDocument1;
import Server.BroderCast;
import ServerUI.GUI;
import ServerUI.HelpUI;
import Server.Shutdown;

/**
 * 
 * ���������ĸ����ܰ�ť�ļ���
 * @author MIKORU
 *
 */
public class RMouseListener implements MouseListener {
	private boolean count = true;//�����ť��˫���� Ŀǰֻ������Ļ�㲥�����ر�
	
	public RMouseListener() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		String btnName =((JButton)e.getSource()).getText();
		if("��������".equals(btnName.trim())){
			new HelpUI();
		}
		if("��Ļ��ʾ".equals(btnName.trim())){
			if(count == true){
				int n = JOptionPane.showConfirmDialog(null,"ȷ��������Ļ�㲥��","��ʾ",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(PActionListener.class.getResource("/image/orange32.png")));
				if(n==0)
					new Thread(new BroderCast()).start();
			}else{
				int n = JOptionPane.showConfirmDialog(null,"ȷ���ر���Ļ�㲥��","��ʾ",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(PActionListener.class.getResource("/image/orange32.png")));
				if(n==0){
					BroderCast.StopThread();
				}
			}
			count = !count;
		}
		if("�ļ�����".equals(btnName.trim())){
			try {
				new Thread(new SDocument1()).start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		if("�ػ�����".equals(btnName.trim())){
			Object[] opt ={ "�ػ�", "����", "ȡ��" };  
			int n = JOptionPane.showOptionDialog(null,"��ѡ�����:\n", "����", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, new ImageIcon(GUI.class.getResource("/image/orange32.png")), opt, "�ػ�");  
			if(n==0){
				new Thread(new Shutdown("�ػ�")).start();
			}else if(n==1){
				new Thread(new Shutdown("����")).start();
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) e.getComponent();
		Border etchedBorder = new EtchedBorder(EtchedBorder.RAISED);// ���ñ߿�͹��
		btn.setBorder(etchedBorder);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) e.getComponent();
		Border etchedBorder = new EtchedBorder(EtchedBorder.LOWERED);// ���ñ߿���
		btn.setBorder(etchedBorder);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) e.getComponent();
		btn.setForeground(/*new Color(0x33, 0x66, 0xcc)*/Color.WHITE);// ����������ɫ
		btn.setBorderPainted(true);// ��ʾ�߿�
		Border etchedBorder = new EtchedBorder(EtchedBorder.LOWERED);// ���ñ߿���
		btn.setBorder(etchedBorder);
		btn.setRolloverEnabled(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) e.getComponent();
		btn.setForeground(Color.black);// ����������ɫ
		//btn.setBorderPainted(false);// ���ر߿�
	}


}