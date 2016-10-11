
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

import java.awt.*;

public class Login extends JFrame{
    JTextField jTextField ;
    JPasswordField jPasswordField;
    JLabel jLabel1,jLabel2;
    JPanel jp1,jp2,jp3;
    JButton jb1,jb2;
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
        jLabel1 = new JLabel("�û���");
        jLabel2 = new JLabel(" ����  ");
        jb1 = createBtn("ȷ��","./image/yes.png");
        jb2 = createBtn("ȡ��","./image/cancel.png");
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        
        //���ò���
        this.setLayout(new GridLayout(3, 1,0,3));
        
        jp1.add(jLabel1); 
        jp1.add(jTextField);//��һ���������û������ı��� 
        jp1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        
        jp2.add(jLabel2);
        jp2.add(jPasswordField);//�ڶ�����������������������
        jp2.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 12));
        
        jp3.add(jb1);
        jp3.add(jb2); //������������ȷ�Ϻ�ȡ��
        jp3.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        //������ʾ
        this.setSize(300, 250);
        this.setLocation(500, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("��½");
         
    }
}