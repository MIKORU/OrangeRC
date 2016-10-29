package ServerUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

import Listener.LMouseListener;

import java.awt.*;

/**
 * 
 * 登陆界面UI设计
 * @author MIKORU
 *
 */
public class Login extends JFrame{
    public JTextField jTextField ;
    public JPasswordField jPasswordField;
    JLabel jLabel1,jLabel2;
    JPanel jp1,jp2,jp3;
    JButton jb1,jb2;
    JLabel jl ;
    
    private JButton createBtn(String text, String icon) {
		JButton btn = new JButton(text, new ImageIcon(Login.class.getResource(icon)));
		btn.setUI(new BasicButtonUI());// 恢复基本视觉效果
		btn.setPreferredSize(new Dimension(100, 40));// 设置按钮大小
		btn.setContentAreaFilled(false);// 设置按钮透明
		btn.setFont(new Font("黑体", Font.PLAIN, 20));// 按钮文本样式
		btn.setMargin(new Insets(0, 0, 0, 0));// 按钮内容与边框距离
		btn.addMouseListener(new LMouseListener(this));
		return btn;
	}
	public Login(){
        jTextField = new JTextField(12);
        jPasswordField = new JPasswordField(12);
        jLabel1 = new JLabel("用户名 ");
        jLabel2 = new JLabel(" 密码  ");
        jb1 = createBtn("确认","image/yes.png");
        jb2 = createBtn("取消","image/cancel.png");
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        
        //设置布局
        this.setLayout(null);
        
        jp1.add(jLabel1); 
        jp1.add(jTextField);//第一块面板添加用户名和文本框 
        jp1.setBounds(30, 230, 400, 40);
        
        jp2.add(jLabel2);
        jp2.add(jPasswordField);//第二块面板添加密码和密码输入框
        jp2.setBounds(30,280,400,40);
        
        jp3.setLayout(new FlowLayout(FlowLayout.CENTER,40,5));
        jp3.add(jb1);
        jp3.add(jb2); //第三块面板添加确认和取消
        jp3.setBounds(0,350,500,140);
        
        jl= new JLabel(new ImageIcon(GUI.class.getResource("image/Login.jpg")));
        jl.setBounds(0,0,500,190);
        
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jl);
        //设置显示
        this.setSize(500, 450);
        this.setLocation(450, 170);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("登陆");
         
    }
}