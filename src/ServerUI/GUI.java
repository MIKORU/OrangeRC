package ServerUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
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
public class GUI extends JFrame{
	
	private final static int USERNUM = RemoteServer.USERNUM; 
	public static JButton[] la_image  = new JButton[USERNUM];
	public static JLabel[] la  = new JLabel[USERNUM];
	
	public GUI(){
		display();
	}
	
	private JPanel addJPanel() {
		JPanel menuPanel = new BackgroundPane(new ImageIcon(GUI.class.getResource("/image/menubb.jpg")));
		menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		JButton docuBtn = createBtn("文件传输","/image/document64.png",150,140);
		menuPanel.add(docuBtn);
		
		JButton screenBtn = createBtn("屏幕演示", "/image/computer64.png",150,140);
		menuPanel.add(screenBtn);
		
		JButton playBtn = createBtn("关机重启", "/image/shutdown64.png",150,140);
		menuPanel.add(playBtn);
		
		JButton setBtn = createBtn("帮助设置", "/image/setting64.png",150,140);
		menuPanel.add(setBtn);
		
		return menuPanel;
	}
	/**
	 * 
	 * 一般按钮个性化设置
	 * 
	 * @param text
	 * @param icon
	 * @param w
	 * @param h
	 * @return
	 */
	private JButton createBtn(String text, String icon,int w,int h) {
		JButton btn = new JButton(text, new ImageIcon(GUI.class.getResource(icon)));
		btn.setUI(new BasicButtonUI());
		btn.setPreferredSize(new Dimension(w, h));
		btn.setContentAreaFilled(false);//设置按钮透明
		btn.setFont(new Font(Font.DIALOG, Font.BOLD, 25));//按钮文本样式
		btn.setMargin(new Insets(0, 0, 0, 0));//按钮内容与边框距离
        btn.setVerticalTextPosition(JButton.BOTTOM);
        btn.setHorizontalTextPosition(JButton.CENTER);
        btn.addMouseListener(new RMouseListener());
		btn.setIconTextGap(15);
		return btn;
	}
	/**
	 * 个人弹出窗口按钮个性化设置
	 * 
	 * @param text
	 * @param i
	 * @return
	 */
	private JButton createPBtn(String text,int i) {
		JButton btn = new JButton(text, new ImageIcon(GUI.class.getResource("/image/bgbtn.png")));
		btn.setUI(new BasicButtonUI());
		btn.setPreferredSize(new Dimension(120, 50));
		btn.setContentAreaFilled(false);
		btn.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		btn.setMargin(new Insets(0, 0, 0, 0));
        btn.setHorizontalTextPosition(JButton.CENTER);
        btn.addActionListener(new PActionListener(i));
		return btn;
	}
	private JPopupMenu createJPop(int i){
		JPopupMenu popupMenu = new JPopupMenu();  
        JButton menubtn1 = createPBtn("关机",i);
        JButton menubtn2 = createPBtn("重启",i);
        JButton menubtn3 = createPBtn("文件传输",i);
        JButton menubtn4 = createPBtn("控制电脑",i);
        
        popupMenu.add(menubtn1);
        popupMenu.add(menubtn2);
        popupMenu.add(menubtn3);
        popupMenu.add(menubtn4);
        
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
		    la[i] = new JLabel();
		    jlp.add(la_image[i]);
		    la_image[i].setComponentPopupMenu(createJPop(i));
		}
		//侧边功能栏
	    JPanel menuPanel = addJPanel();
	  	menuPanel.setBounds(0,120,150, 580);
	  	menuPanel.setBorder(BorderFactory.createMatteBorder(15, 1, 1, 1, Color.white));
	  	
	  	//主要屏幕显示
	  	JScrollPane scroll = new JScrollPane(jlp);
	  	scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	  	scroll.setBorder(BorderFactory.createTitledBorder("屏幕分享"));
	  	scroll.setBounds(150,120,1150, 580);
	  	scroll.setBackground(Color.white);
	  	
	  	this.getContentPane().add(menuPanel);
	  	this.getContentPane().add(jls);
	    this.getContentPane().add(scroll);
	    
	    this.getContentPane().setBackground(Color.white);
	    this.setResizable(false);//不可更改屏幕大小
	}
}
/***
 * 用于实现背景面板使用自定义图像
 * @author MIKORU
 *
 */
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