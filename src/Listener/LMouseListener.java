package Listener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import ServerUI.GUI;
import ServerUI.Login;

/**
 * 
 * ��½���水ť����
 * ��¼ ����ʱ���˶��û��� ��������
 * ȡ�� �����룬�û������
 * @author MIKORU
 *
 */
public class LMouseListener extends RMouseListener{
	private Login frame2;
	public LMouseListener(Login s) {
		super();
		this.frame2 = s;
	}
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
}
