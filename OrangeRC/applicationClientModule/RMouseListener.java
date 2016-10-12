import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class RMouseListener implements MouseListener {

	private GUI frame;
	private Login frame2;
	private AbstractButton jTextField;

	public RMouseListener() {
		// TODO Auto-generated constructor stub
	}

	public RMouseListener(GUI f) {
		this.frame = f;
	}
	
	public RMouseListener(Login s) {
		this.frame2 = s;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		String btnName =((JButton)e.getSource()).getText();
		String Keyname = frame2.jTextField.getText().trim();
		if ("ȷ��".equals(btnName.trim())) {
			if("14251104235".equals(Keyname)){
					frame2.dispose();
					JOptionPane.showMessageDialog(null,"��½�ɹ���","��ʾ",JOptionPane.DEFAULT_OPTION);
					new GUI();
				}
			else {
                JOptionPane.showMessageDialog(null,"�û������������","��ʾ",JOptionPane.ERROR_MESSAGE);	
			}
		}
		if ("ȡ��".equals(btnName.trim())){
			frame2.jTextField.setText(null);
			frame2.jPasswordField.setText(null);
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
		btn.setForeground(new Color(0x33, 0x66, 0xcc));// ����������ɫ
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
		btn.setBorderPainted(false);// ���ر߿�
	}


}