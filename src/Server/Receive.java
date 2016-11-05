package Server;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Listener.PActionListener;
import ServerUI.BigScreen;
import ServerUI.GUI;

/** 
 * 接收客户端的图片 
 *  
 * @author MIKORU
 * @date 2016.09.27
 */  
public class Receive implements Runnable {
		private Socket st;
		private int port;
		private String ip;
		private int s = 0;
		
	    public Receive(Socket st,int port,int order,String ip) {
			this.st = st;
			this.port = port;
			this.s = order;
			this.ip = ip;
		}
		public void run() {
                try {
                	DataInputStream ins = new DataInputStream(st.getInputStream());
                	
                	int len = ins.readInt();
                	byte[] data=new byte[len];
                    ins.readFully(data);
                    ByteArrayInputStream bins=new ByteArrayInputStream(data);
                    
                    BufferedImage image= ImageIO.read(bins);
                    ImageIcon ic=new ImageIcon(image);
                    Image img = ic.getImage();
                    
            		Toolkit tool = Toolkit.getDefaultToolkit();  
            		Dimension dis = tool.getScreenSize();
                    
                    BufferedImage screen = resize(img,380,220);
	                BufferedImage bigscreen = resize(img,dis.width,dis.height);
	                
	                GUI.la[s].setIcon(new ImageIcon(bigscreen));//大屏幕影像
	                
	                GUI.la_image[s].setIcon(new ImageIcon(screen));//小屏幕影像
	                GUI.la_image[s].setText(ip);
	                GUI.la_image[s].setIconTextGap(4);
	                Thread.sleep(100);
	                
                }catch (InterruptedException e) {
				}catch (IOException e1) {
						GUI.la_image[s].setIcon(new ImageIcon(GUI.class.getResource("/image/orange.png")));
                		GUI.la[s].setIcon(new ImageIcon(GUI.class.getResource("/image/CE.jpg")));
                		GUI.la_image[s].setText("主机"+s);
                		GUI.la_image[s].setIconTextGap(15);
                		JOptionPane.showConfirmDialog(null,s+"号机断开连接！","提示",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(PActionListener.class.getResource("/image/orange32.png")));
				}
                finally {
					try {
						if(st!=null){
							st.close();
						}
					} catch (IOException e) {}
				}
         }
		/**
		 * 对Image进行压缩图像处理
		 * 
		 * @param img
		 * @param newW
		 * @param newH
		 * @return
		 */
        private BufferedImage resize(Image img, int newW, int newH) {
            int w = img.getWidth(null);
            int h = img.getHeight(null);
            BufferedImage dimg = new BufferedImage(newW, newH,BufferedImage.TYPE_INT_BGR);
            Graphics2D g = dimg.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                          RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
            g.dispose();
            return dimg;
        }
			
}
