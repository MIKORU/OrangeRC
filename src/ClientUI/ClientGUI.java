package ClientUI;

import java.awt.AWTException;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;



public class ClientGUI extends JFrame{

	public static String cpaths = "桌面";
	private  String pan;
	private  String fname;
	LinkLabel ll = new LinkLabel("访问Githb_Reset113", "https://github.com/Reset113");
    public ClientGUI(){
        systemTray();//系统托盘
    }
	private JFrame addJFrame(){
		JFrame frame = new JFrame();
		
		frame.setBounds(400,100,400,300);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JPanel jp1 = new JPanel();
        jp1.setLayout(null);
        jp1.setBackground(null);
        
        
        JLabel la = new JLabel(new ImageIcon(ClientGUI.class.getResource("/image/orange32.png")));
        la.setText("默认文件存放路径：");
        la.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        la.setBounds(10,15+20,250,30);
        la.setOpaque(false);
        jp1.add(la);
        
        String[] item = {"C:","D:","E:","F:"};
        final JComboBox jcb = new JComboBox(item);
        jcb.setSelectedItem(item[0]);
        jcb.setEditable(true);
        jcb.setBounds(75,130+40,50,20);
        pan = "C:\\";
        jcb.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		if (ItemEvent.SELECTED == e.getStateChange()) {   //这个判断是选择只会得到一个结果，如果没有判断，会得到两个相同的值，从而获取不到所要选中的值。
        			pan = jcb.getSelectedItem().toString();
        			pan = pan+"\\";
        			//System.out.println(pan);
        		}
        	}
        });
        final JTextField jtf = new JTextField();
        jtf.setColumns(100);
        jtf.setBounds(128,130+40,180,20);
        final JButton btn = new JButton("确定");
        btn.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				jcb.setEnabled(false);
		        jtf.setEnabled(false);
		        fname = jtf.getText();
		        if(fname!=null)
		        	cpaths = pan+fname;
		        else cpaths = pan;
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
        	
        });
        final JButton btn2 = new JButton("取消");
        btn2.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				jcb.setEnabled(true);
		        jtf.setEnabled(true);
		        jtf.setText(null);
		        fname = null;
			}

			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
        	
        });
        btn.setBounds(200,160+40,60,20);
        btn2.setBounds(270,160+40,60,20);
        jcb.setEnabled(false);
        jtf.setEnabled(false);
        btn.setEnabled(false);
        btn2.setEnabled(false);
        
        jp1.add(jtf);
        jp1.add(jcb);
        jp1.add(btn);
        jp1.add(btn2);
        
        ButtonGroup pan = new ButtonGroup();
        JRadioButton jrb1 = new JRadioButton("默认路径（桌面）");
        jrb1.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
        jrb1.setBounds(40,50+40,300,20);
        jrb1.setOpaque(false);  
        jrb1.setSelected(true);
        pan.add(jrb1);
        jrb1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcb.setEnabled(false);
		        jtf.setEnabled(false);
		        btn.setEnabled(false);
		        btn2.setEnabled(false);
		        cpaths = "桌面";
			}
			
        });
        
        JRadioButton jrb2 = new JRadioButton("自定义路径");
        jrb2.setBounds(40,90+40,200,20);
        jrb2.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
        jrb2.setOpaque(false);
        pan.add(jrb2);
        jrb2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jcb.setEnabled(true);
		        jtf.setEnabled(true);
		        btn.setEnabled(true);
		        btn2.setEnabled(true);
			}
			
        }); 
        jp1.add(jrb1);
        jp1.add(jrb2);
        
		
		ll.setBounds(230,300,400,20);
		jp1.add(ll);
		
		frame.setContentPane(jp1);
		
        return frame;
	}
    private void  systemTray(){
        if(SystemTray.isSupported()){    //判断系统是否支持托盘功能.
            ImageIcon icon = new ImageIcon(ClientGUI.class.getResource("/image/orange16.png"));
            PopupMenu popupMenu = new PopupMenu();
            MenuItem itemExit = new MenuItem("退出系统");
            MenuItem itemPush = new MenuItem("传输文件");
            MenuItem itemRecive = new MenuItem("默认文件路径");
            itemExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }            
            });
            itemPush.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //弹出窗口选择文件
                }
            });
            itemRecive.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addJFrame().setVisible(true);;
                }
            });
            popupMenu.add(itemPush);
            popupMenu.add(itemRecive);
            popupMenu.addSeparator();
            popupMenu.add(itemExit);
            TrayIcon trayIcon = new TrayIcon(icon.getImage(),"远程监控客户端",popupMenu);
            SystemTray sysTray = SystemTray.getSystemTray();
            try {
                sysTray.add(trayIcon);
            } catch (AWTException e1) {    }
        }
    }
}
class LinkLabel extends JLabel {
    private String text, url;
    private boolean isSupported;

    public LinkLabel(String text, String url) {
        this.text = text;
        this.url = url;
        try {
            this.isSupported = Desktop.isDesktopSupported()
                    && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
        } catch (Exception e) {
            this.isSupported = false;
        }
        setText(false);
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setText(isSupported);
                if (isSupported)
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent e) {
                setText(false);
            }

            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(
                            new java.net.URI(LinkLabel.this.url));
                } catch (Exception ex) {
                }
            }
        });
    }

    private void setText(boolean b) {
        if (!b)
            setText("<html><font color=blue><u>" + text);
        else
            setText("<html><font color=red><u>" + text);
    }
}
