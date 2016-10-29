package ServerUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.basic.BasicButtonUI;

import Listener.PActionListener;
import Listener.RMouseListener;
import Server.RemoteServer;

/***
 * 
 * ������UI���
 * @author MIKORU
 *
 */
@SuppressWarnings("serial")
public class GUI extends JFrame{
	private JButton setBtn = null;
	private JButton docuBtn = null;
	private JButton screenBtn = null;
	private JButton playBtn = null;
	private final static int USERNUM = RemoteServer.USERNUM; 
	public static JButton[] la_image  = new JButton[USERNUM];
	
	public GUI(){
		display();
	}
	
	private JPanel addJPanel() {
		JPanel menuPanel = new BackgroundPane(new ImageIcon(GUI.class.getResource("image/menubb.jpg")));
		//JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		docuBtn = createBtn("�ļ�����","./image/document64.png",150,140);
		menuPanel.add(docuBtn);
		//docuBtn.addMouseListener(new RMouseListener());
		
		screenBtn = createBtn("��Ļ��ʾ", "./image/computer64.png",150,140);
		menuPanel.add(screenBtn);
		//screenBtn.addMouseListener(new RMouseListener());
		
		playBtn = createBtn("�ػ�����", "./image/shutdown64.png",150,140);
		menuPanel.add(playBtn);
		//playBtn.addMouseListener(new RMouseListener());
		
		setBtn = createBtn("��������", "./image/setting64.png",150,140);
		menuPanel.add(setBtn);
		//controlBtn.addMouseListener(new RMouseListener());
		
		return menuPanel;
	}

	private JButton createBtn(String text, String icon,int w,int h) {
		JButton btn = new JButton(text, new ImageIcon(GUI.class.getResource(icon)));
		btn.setUI(new BasicButtonUI());// �ָ������Ӿ�Ч��
		btn.setPreferredSize(new Dimension(w, h));// ���ð�ť��С
		btn.setContentAreaFilled(false);// ���ð�ť͸��
		btn.setFont(new Font(Font.DIALOG, Font.BOLD, 25));// ��ť�ı���ʽ
		btn.setMargin(new Insets(0, 0, 0, 0));// ��ť������߿����
        btn.setVerticalTextPosition(JButton.BOTTOM);
        btn.setHorizontalTextPosition(JButton.CENTER);
        btn.addMouseListener(new RMouseListener());
		btn.setIconTextGap(15);
		return btn;
	}
	private JPopupMenu createJPop(int i){
		JPopupMenu popupMenu = new JPopupMenu();  
        JMenuItem menuItem1 = new JMenuItem("�ػ�");
        JMenuItem menuItem2 = new JMenuItem("����");
        JMenuItem menuItem3 = new JMenuItem("�����ļ�");
        JMenuItem menuItem4 = new JMenuItem("���Ƶ���");
        
        popupMenu.add(menuItem1);
        popupMenu.add(menuItem2);
        popupMenu.add(new JSeparator());
        popupMenu.add(menuItem3);
        popupMenu.add(new JSeparator());
        popupMenu.add(menuItem4);
        
	    menuItem1.addActionListener (new PActionListener(i));
        menuItem2.addActionListener (new PActionListener(i));
        menuItem3.addActionListener (new PActionListener(i));
        menuItem4.addActionListener (new PActionListener(i));
        
        return popupMenu;
		
	}
	private void display(){
		
		this.setSize(1310, 730);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setTitle("OrangeRC");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		JLabel jls = new JLabel(new ImageIcon(GUI.class.getResource("image/menub.jpg")));
	  	jls.setBounds(0, 0, 1300,120);
		
		JPanel jlp = new BackgroundPane(new ImageIcon(GUI.class.getResource("image/background.jpg")));
		jlp.setLayout(new GridLayout(USERNUM/3+1,3,5,5));
      
		
		for(int i=0;i<USERNUM;i++){
		    la_image[i] = createBtn("���� "+i,"./image/orange.png",300,250);
		    jlp.add(la_image[i]);
		    la_image[i].setComponentPopupMenu(createJPop(i));
		    //la_image[i].setOpaque(true);
		}
		
	    JPanel menuPanel = addJPanel();
	  	menuPanel.setBounds(0,120,150, 580);
	  	menuPanel.setBorder(BorderFactory.createMatteBorder(15, 1, 1, 1, Color.white));

	  	JScrollPane scroll = new JScrollPane(jlp);
	  	scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	  	scroll.setBorder(BorderFactory.createTitledBorder("��Ļ����"));
	  	scroll.setBounds(150,120,1150, 580);
	  	scroll.setBackground(Color.white);
	  	this.getContentPane().add(menuPanel);
	  	this.getContentPane().add(jls);
	    this.getContentPane().add(scroll);
	    this.getContentPane().setBackground(Color.white);
	    this.setResizable(false);
	}
}
/***
 * ����ʵ�ֱ������ʹ���Զ���ͼ��
 * @author MIKORU
 *
 */
@SuppressWarnings("serial")
class BackgroundPane extends JPanel {
    private Image image = null;
    public BackgroundPane(ImageIcon image) {  
    	super();
        this.image = image.getImage();  
    }
	protected void paintComponent(Graphics g) {  
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
    }  
}