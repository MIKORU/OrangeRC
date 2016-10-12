import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.io.ObjectInputStream;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.text.html.ImageView;


public class GUI extends JFrame{
	private JButton controlBtn = null;
	private JButton docuBtn = null;
	private JButton screenBtn = null;
	private JButton playBtn = null;
	JButton la_image= new JButton();
	ObjectInputStream ins;
	
	public GUI(){
		display();
		ins =  RemoteClient.getIns();
		new Receive(la_image,ins).start();
	}
	
	private JPanel addJPanel() {
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
		
		controlBtn = createBtn("������Ļ", "./image/control72x.png");
		menuPanel.add(controlBtn);


		docuBtn = createBtn("�ļ�����","./image/document96x.png");
		menuPanel.add(docuBtn);


		screenBtn = createBtn("��Ļ��ʾ", "./image/screen96x.png");
		menuPanel.add(screenBtn);

		
		playBtn = createBtn("�ػ�", "./image/shutdown64x.png");
		menuPanel.add(playBtn);

		return menuPanel;
	}

	/**
	 * ������������ť
	 * 
	 * @param text
	 *            ��ť����
	 * @param icon
	 *            ��ťͼ������·��
	 * @return ���������ʽ�ͼ�������İ�ť
	 */
	private JButton createBtn(String text, String icon) {
		JButton btn = new JButton(text, new ImageIcon(icon));
		btn.setUI(new BasicButtonUI());// �ָ������Ӿ�Ч��
		btn.setPreferredSize(new Dimension(250, 100));// ���ð�ť��С
		btn.setContentAreaFilled(false);// ���ð�ť͸��
		btn.setFont(new Font("����", Font.PLAIN, 20));// ��ť�ı���ʽ
		btn.setMargin(new Insets(0, 0, 0, 0));// ��ť������߿����
		btn.addMouseListener(new RMouseListener(this));
		return btn;
	}
	private void display(){
		
		this.setSize(1320, 750);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setTitle("OrangeRC");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JLabel jlp = new JLabel();
	    
	    
	    la_image.setBounds(20, 20, 300,200);
	    la_image.setOpaque(true);


	    jlp.add(la_image);
	    JPanel menuPanel = addJPanel();
	  	menuPanel.setBorder(BorderFactory.createTitledBorder("������"));
	  	
	  	menuPanel.setBounds(20,500,1300, 180);
	  	
	  	this.getContentPane().add(menuPanel);
	    this.getContentPane().add(jlp);
	    
	}

}
