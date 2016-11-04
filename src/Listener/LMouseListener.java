package Listener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import ServerUI.GUI;
import ServerUI.Login;

/**
 * 
 * 登陆界面按钮监听
 * 登录 （暂时）核对用户名 无需密码
 * 取消 将密码，用户名清空
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
		if ("确认".equals(btnName.trim())) {
			if("123".equals(Keyname)){
					frame2.dispose();
					JOptionPane.showMessageDialog(null,"登陆成功！","提示",JOptionPane.DEFAULT_OPTION);
					new GUI();
				}
			else {
                JOptionPane.showMessageDialog(null,"用户名或密码错误！","提示",JOptionPane.ERROR_MESSAGE);	
			}
		}
		if ("取消".equals(btnName.trim())){
			frame2.jTextField.setText(null);
			frame2.jPasswordField.setText(null);
		}
	}
}
