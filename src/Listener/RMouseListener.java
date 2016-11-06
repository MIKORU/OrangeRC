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
 * 对主界面四个功能按钮的监听
 * @author MIKORU
 *
 */
public class RMouseListener implements MouseListener {
	private boolean count = true;//点击按钮单双次数 目前只用于屏幕广播开启关闭
	
	public RMouseListener() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		String btnName =((JButton)e.getSource()).getText();
		if("帮助设置".equals(btnName.trim())){
			new HelpUI();
		}
		if("屏幕演示".equals(btnName.trim())){
			if(count == true){
				int n = JOptionPane.showConfirmDialog(null,"确定开启屏幕广播？","提示",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(PActionListener.class.getResource("/image/orange32.png")));
				if(n==0)
					new Thread(new BroderCast()).start();
			}else{
				int n = JOptionPane.showConfirmDialog(null,"确定关闭屏幕广播？","提示",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(PActionListener.class.getResource("/image/orange32.png")));
				if(n==0){
					BroderCast.StopThread();
				}
			}
			count = !count;
		}
		if("文件传输".equals(btnName.trim())){
			try {
				new Thread(new SDocument1()).start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		if("关机重启".equals(btnName.trim())){
			Object[] opt ={ "关机", "重启", "取消" };  
			int n = JOptionPane.showOptionDialog(null,"请选择操作:\n", "操作", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, new ImageIcon(GUI.class.getResource("/image/orange32.png")), opt, "关机");  
			if(n==0){
				new Thread(new Shutdown("关机")).start();
			}else if(n==1){
				new Thread(new Shutdown("重启")).start();
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) e.getComponent();
		Border etchedBorder = new EtchedBorder(EtchedBorder.RAISED);// 设置边框凸显
		btn.setBorder(etchedBorder);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) e.getComponent();
		Border etchedBorder = new EtchedBorder(EtchedBorder.LOWERED);// 设置边框凹显
		btn.setBorder(etchedBorder);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) e.getComponent();
		btn.setForeground(/*new Color(0x33, 0x66, 0xcc)*/Color.WHITE);// 设置字体颜色
		btn.setBorderPainted(true);// 显示边框
		Border etchedBorder = new EtchedBorder(EtchedBorder.LOWERED);// 设置边框凹显
		btn.setBorder(etchedBorder);
		btn.setRolloverEnabled(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) e.getComponent();
		btn.setForeground(Color.black);// 设置字体颜色
		//btn.setBorderPainted(false);// 隐藏边框
	}


}