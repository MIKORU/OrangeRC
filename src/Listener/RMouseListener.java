package Listener;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import Document.SUDocument;

/**
 * 
 * ���������ĸ����ܰ�ť�ļ���
 * ����ʱ��ֻʵ���˿�����Ļ
 * @author MIKORU
 *
 */
public class RMouseListener implements MouseListener {
	public static boolean cl = false;
	public RMouseListener() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		String btnName =((JButton)e.getSource()).getText();
		if("������Ļ".equals(btnName.trim())){
			cl=(cl==true?false:true);
			if(cl==true){
				JOptionPane.showMessageDialog(null,"����������Ļ����\n��˫��ѡ��һλѧ����\n�ٴε��������Ļ��ť��ȡ��","��ʾ",JOptionPane.DEFAULT_OPTION);
			}else{
				JOptionPane.showMessageDialog(null,"�رտ�����Ļ����\n","��ʾ",JOptionPane.DEFAULT_OPTION);
			}
		}
		if("�ļ�����".equals(btnName.trim())){
			SUDocument d=new SUDocument();
			d.start();
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