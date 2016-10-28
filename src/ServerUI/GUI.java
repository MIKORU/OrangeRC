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
 * 主界面UI设计
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
		JPanel menuPanel = new BackgroundPane(new ImageIcon(GUI.class.getResource("/image/menubb.jpg")));
		//JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		docuBtn = createBtn("文件传输","/image/document64.png",150,140);
		menuPanel.add(docuBtn);
		//docuBtn.addMouseListener(new RMouseListener());
		
		screenBtn = createBtn("屏幕演示", "/image/computer64.png",150,140);
		menuPanel.add(screenBtn);
		//screenBtn.addMouseListener(new RMouseListener());
		
		playBtn = createBtn("关机重启", "/image/shutdown64.png",150,140);
		menuPanel.add(playBtn);
		//playBtn.addMouseListener(new RMouseListener());
		
		setBtn = createBtn("帮助设置", "/image/setting64.png",150,140);
		menuPanel.add(setBtn);
		//controlBtn.addMouseListener(new RMouseListener());
		
		return menuPanel;
	}

	private JButton createBtn(String text, String icon,int w,int h) {
		JButton btn = new JButton(text, new ImageIcon(GUI.class.getResource(icon)));
		btn.setUI(new BasicButtonUI());// 恢复基本视觉效果
		btn.setPreferredSize(new Dimension(w, h));// 设置按钮大小
		btn.setContentAreaFilled(false);// 设置按钮透明
		btn.setFont(new Font("黑体", Font.PLAIN, 25));// 按钮文本样式
		btn.setMargin(new Insets(0, 0, 0, 0));// 按钮内容与边框距离
        btn.setVerticalTextPosition(JButton.BOTTOM);
        btn.setHorizontalTextPosition(JButton.CENTER);
        btn.addMouseListener(new RMouseListener());
		btn.setIconTextGap(15);
		return btn;
	}
	private JPopupMenu createJPop(int i){
		JPopupMenu popupMenu = new JPopupMenu();  
        JMenuItem menuItem1 = new JMenuItem("关机");
        JMenuItem menuItem2 = new JMenuItem("重启");
        JMenuItem menuItem3 = new JMenuItem("发送文件");
        JMenuItem menuItem4 = new JMenuItem("控制电脑");
        
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
		
		JLabel jls = new JLabel(new ImageIcon(GUI.class.getResource("/image/menub.jpg")));
	  	jls.setBounds(0, 0, 1300,120);
		
		JPanel jlp = new BackgroundPane(new ImageIcon(GUI.class.getResource("/image/background.jpg")));
		jlp.setLayout(new GridLayout(USERNUM/3+1,3,5,5));
      
		
		for(int i=0;i<USERNUM;i++){
		    la_image[i] = createBtn("主机 "+i,"/image/orange.png",300,250);
		    jlp.add(la_image[i]);
		    la_image[i].setComponentPopupMenu(createJPop(i));
		    //la_image[i].setOpaque(true);
		}
		
	    JPanel menuPanel = addJPanel();
	  	menuPanel.setBounds(0,120,150, 580);
	  	menuPanel.setBorder(BorderFactory.createMatteBorder(15, 1, 1, 1, Color.white));

	  	JScrollPane scroll = new JScrollPane(jlp);
	  	scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	  	scroll.setBorder(BorderFactory.createTitledBorder("屏幕分享"));
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
 * 用于实现背景面板使用自定义图像
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