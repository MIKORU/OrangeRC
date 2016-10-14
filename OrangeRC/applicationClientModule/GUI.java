import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.io.ObjectInputStream;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.text.html.ImageView;


public class GUI extends JFrame{
	private JButton controlBtn = null;
	private JButton docuBtn = null;
	private JButton screenBtn = null;
	private JButton playBtn = null;
	public final static int USERNUM = RemoteServer.USERNUM; 
	public static JButton[] la_image  = new JButton[USERNUM];
	
	public GUI(){
		display();
	}
	
	private JPanel addJPanel() {
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
		
		controlBtn = createBtn("������Ļ", "./image/control72x.png",250,100);
		menuPanel.add(controlBtn);

		docuBtn = createBtn("�ļ�����","./image/document96x.png",250,100);
		menuPanel.add(docuBtn);

		screenBtn = createBtn("��Ļ��ʾ", "./image/screen96x.png",250,100);
		menuPanel.add(screenBtn);
		
		playBtn = createBtn("�ػ�", "./image/shutdown64x.png",250,100);
		menuPanel.add(playBtn);

		return menuPanel;
	}

	private JButton createBtn(String text, String icon,int w,int h) {
		JButton btn = new JButton(text, new ImageIcon(icon));
		btn.setUI(new BasicButtonUI());// �ָ������Ӿ�Ч��
		btn.setPreferredSize(new Dimension(w, h));// ���ð�ť��С
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
		this.setLayout(null);
		JLayeredPane jlp = new JLayeredPane();
	    
		jlp.setLayout(new GridLayout(USERNUM/4+1,4,5,10));
		
		for(int i=0;i<USERNUM;i++){
		    la_image[i] = createBtn(null,"./image/huaji.png",300,250);
		    //la_image[i].setHorizontalTextPosition(JButton.CENTER);
		    jlp.add(la_image[i]);
		    la_image[i].setOpaque(true);
		}
		
	    JPanel menuPanel = addJPanel();
	  	menuPanel.setBorder(BorderFactory.createMatteBorder(2, 20, 2, 20, Color.gray));
	  	
	  	menuPanel.setBounds(0,550,1300, 150);
	  	

	  	jlp.setBorder(BorderFactory.createTitledBorder("��Ļ����"));
	  	jlp.setBounds(0,0,1350,2000);
	  	JScrollPane scroll = new JScrollPane(jlp);
	  	scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


	  	scroll.setBounds(0,0,1300, 540);
	  	this.getContentPane().add(menuPanel);
	    this.getContentPane().add(scroll);
	}
}
