import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;


public class GUI extends JFrame{
	public void display(JLabel la_image){
		javax.swing.JFrame frame=new javax.swing.JFrame("OrangeRC");
	    frame.setSize(1200,700);
	    frame.setResizable(false);
	    //JLabel Stu = new JLabel("ѧ��1");
	    JMenuBar jmb = new JMenuBar();
	    JButton fbutton1 = new JButton("ȫ��㲥");
	    JButton fbutton2 = new JButton("�ػ�");
	    JButton fbutton3 = new JButton("����");
	    
	    
	    JMenu fileMenu = new JMenu("����");
	    JMenu editMenu = new JMenu("����");
	    JMenu helpMenu = new JMenu("����");
	    jmb.add(fileMenu);
	    jmb.add(editMenu);
	    jmb.add(helpMenu);
	    fileMenu.add(new JMenuItem("�ػ�"));
	    fileMenu.add(new JMenuItem("����"));
	    fileMenu.add(new JMenuItem("�㲥"));
	    fileMenu.addSeparator();
	    fileMenu.add(new JMenuItem("ȫѡ"));
	    fileMenu.addSeparator();
	    fileMenu.add(new JMenuItem("�˳�"));
	    editMenu.add(new JMenuItem("Reset113"));
	    
	    la_image.setSize(600, 400);
	
	    JLayeredPane jlp = new JLayeredPane();
	    //JLayeredPane njlp = new JLayeredPane();
	    jmb.setBounds(0,0,1200,30);
	    jmb.setOpaque(true);
	    //jmb.setBackground(Color.YELLOW);
	    jlp.add(jmb);
	
	    jlp.add(la_image, (Integer) (JLayeredPane.PALETTE_LAYER + 50));
	    jlp.add(fbutton1, (Integer) (JLayeredPane.PALETTE_LAYER + 50));
	    jlp.add(fbutton2, (Integer) (JLayeredPane.PALETTE_LAYER + 50));
	    jlp.add(fbutton3, (Integer) (JLayeredPane.PALETTE_LAYER + 50));
	    //jlp.add(jmb, (Integer) (JLayeredPane.POPUP_LAYER + 50));
	    //jlp.add(Stu, (Integer) (JLayeredPane.PALETTE_LAYER + 50));
	    
	    
	    la_image.setBounds(30, 50, la_image.getWidth(),la_image.getHeight());
	    la_image.setOpaque(true);
	    
	    fbutton1.setBounds(1000, 80, 100, 40);
	    fbutton1.setOpaque(true);
	    jlp.add(fbutton1);
	    fbutton2.setBounds(1000, 220, 100, 40);
	    fbutton2.setOpaque(true);
	    jlp.add(fbutton2);
	    fbutton3.setBounds(1000, 360, 100, 40);
	    fbutton3.setOpaque(true);
	    jlp.add(fbutton3);
	    //jmb.setBounds(0,0,1200,30);
	    //Stu.setBounds(150,320,100,100);
//	    JScrollPane jsp = new JScrollPane(jlp);
//	    jsp.setHorizontalScrollBarPolicy(                
//                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        jsp.setVerticalScrollBarPolicy(                
//                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
	    
	    //frame.setLayeredPane(njlp);
	    frame.setLayeredPane(jlp);
	    //frame.setBackground(Color.orange);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(3);
	    frame.setAlwaysOnTop(true);
	}
}
