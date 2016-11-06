package ServerUI;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HelpUI extends JFrame{
	public static String fpaths = "C:\\Users\\Administrator\\Desktop\\";//最终默认文件接收地址
	private  String pan;
	private  String fname;
	LinkLabel ll = new LinkLabel("访问Githb_Reset113", "https://github.com/Reset113");
	public HelpUI(){
		display();
	}
	private JPanel addJPanel(){
		
		JPanel jp1 = new JPanel();
        jp1.setLayout(null);
        jp1.setBackground(null);
        
        
        JLabel la = new JLabel(new ImageIcon(GUI.class.getResource("/image/orange32.png")));
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
		        	fpaths = pan+fname;
		        else fpaths = pan;
		        //System.out.println(fpaths);
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
		        fpaths = "C:\\Users\\Administrator\\Desktop\\";
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
        
        return jp1;
	}
	private JPanel addPanel(String name,String path) {
		JPanel jp2 = new JPanel();
		JLabel la = new JLabel(new ImageIcon(GUI.class.getResource("/image/orange32.png")));
        la.setText(name);
        la.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		la.setBounds(20,20,150,30);
		JTextArea area=new JTextArea();
		File file= new File(GUI.class.getResource(path).getPath());
		try {
			FileReader fread=new FileReader(file);
			BufferedReader breader=new BufferedReader(fread);
			String s;
			
			while((s=breader.readLine())!=null)
			{
				area.append(s+"\n");
			}
			breader.close();
			fread.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
////////////////			
//			e.printStackTrace();
		}
		
		area.setBounds(60,65,300,300);
		area.setEditable(false);
		area.setBackground(null);
		
		LinkLabel ll = new LinkLabel("访问Githb_Reset113", "https://github.com/Reset113");
		ll.setBounds(230,300,400,20);
		
		jp2.setLayout(null);
		jp2.setBackground(null);
		jp2.add(la);
		jp2.add(ll);
		jp2.add(area);
		return jp2;
	}
	private void display(){
		this.setTitle("帮助设置");
		this.setBounds(400,100,500,500);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
        JLabel jl= new JLabel(new ImageIcon(GUI.class.getResource("/image/Help.jpg")));
        jl.setBounds(0,0,500,130);
        this.add(jl);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        this.getContentPane().add(tabbedPane);

        ImageIcon imageIcon = new ImageIcon(HelpUI.class.getResource("/image/orange32.png"));
        
        tabbedPane.addTab("  路径设置 ", imageIcon,addJPanel(),"点击设置默认传输文件路径");
        
        tabbedPane.addTab("  帮助说明 ", imageIcon,addPanel("  功能介绍 ","/read_me.txt"),"点击查看帮助说明");
        
        
        tabbedPane.addTab("  开发人员 ", imageIcon,addPanel("Reset113 成员","/ous.txt"),"点击查看开发人员");

        
        tabbedPane.addTab("  高级选项 ", imageIcon,new JLabel(),"点击获取更多功能权限");
        tabbedPane.setSelectedIndex(0);  //设置默认选中的
        tabbedPane.setEnabledAt(3,false);   //设置索引0的面板不可用
        tabbedPane.setBounds(0,130,500,500-130);
        this.setVisible(true);
        this.setResizable(false);
        
	}
	public static void main(String[] args) {
		HelpUI ui= new HelpUI(); 
	}
}
/**
 * 外链地址实现
 * @author MIKORU
 *
 */
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
