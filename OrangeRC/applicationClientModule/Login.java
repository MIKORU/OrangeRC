
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

import java.awt.*;

public class Login extends JFrame{
    JTextField jTextField ;
    JPasswordField jPasswordField;
    JLabel jLabel1,jLabel2;
    JPanel jp1,jp2,jp3;
    JButton jb1,jb2;
    JLabel jl ;
    
    private JButton createBtn(String text, String icon) {
		JButton btn = new JButton(text, new ImageIcon(icon));
		btn.setUI(new BasicButtonUI());// �ָ������Ӿ�Ч��
		btn.setPreferredSize(new Dimension(100, 40));// ���ð�ť��С
		btn.setContentAreaFilled(false);// ���ð�ť͸��
		btn.setFont(new Font("����", Font.PLAIN, 20));// ��ť�ı���ʽ
		btn.setMargin(new Insets(0, 0, 0, 0));// ��ť������߿����
		btn.addMouseListener(new RMouseListener(this));
		return btn;
	}
    @SuppressWarnings("deprecation")
	public Login(){
        jTextField = new JTextField(12);
        jPasswordField = new JPasswordField(12);
        jLabel1 = new JLabel("�û��� ");
        jLabel2 = new JLabel(" ����  ");
        jb1 = createBtn("ȷ��","./image/yes.png");
        jb2 = createBtn("ȡ��","./image/cancel.png");
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        
        //���ò���
        this.setLayout(null);
        
        jp1.add(jLabel1); 
        jp1.add(jTextField);//��һ���������û������ı��� 
        jp1.setBounds(30, 230, 400, 40);
        
        jp2.add(jLabel2);
        jp2.add(jPasswordField);//�ڶ�����������������������
        jp2.setBounds(30,280,400,40);
        
        jp3.setLayout(new FlowLayout(FlowLayout.CENTER,40,5));
        jp3.add(jb1);
        jp3.add(jb2); //������������ȷ�Ϻ�ȡ��
        jp3.setBounds(0,350,500,140);
        
        jl= new JLabel(new ImageIcon("./image/Login.jpg"));//setIcon(new ImageIcon("./image/Login.jpg"));
        jl.setBounds(0,0,500,190);
        
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jl);
        //������ʾ
        this.setSize(500, 450);
        this.setLocation(450, 170);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("��½");
         
    }
}