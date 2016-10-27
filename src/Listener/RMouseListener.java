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
 * 对主界面四个功能按钮的监听
 * （暂时）只实现了控制屏幕
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
		if("控制屏幕".equals(btnName.trim())){
			cl=(cl==true?false:true);
			if(cl==true){
				JOptionPane.showMessageDialog(null,"开启控制屏幕功能\n请双击选择一位学生！\n再次点击控制屏幕按钮可取消","提示",JOptionPane.DEFAULT_OPTION);
			}else{
				JOptionPane.showMessageDialog(null,"关闭控制屏幕功能\n","提示",JOptionPane.DEFAULT_OPTION);
			}
		}
		if("文件传输".equals(btnName.trim())){
			SUDocument d=new SUDocument();
			d.start();
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