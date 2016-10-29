package Server;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.ImageIcon;

import ServerUI.BigScreen;
import ServerUI.GUI;

/** 
 * 接收客户端的图片 
 *  
 * @author MIKORU
 * @date 2016.09.27
 */  
public class Receive implements Runnable {  
		boolean isAlive = true;  
		ImageIcon icon;
		private Socket st;
		private ObjectInputStream ins;
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
	            	//while (isAlive) {
                	for (int i = 0; i < 50; i++){
	            		ins = new ObjectInputStream(st.getInputStream());  
	            		icon = (ImageIcon) ins.readObject();
						
	            		Toolkit tool = Toolkit.getDefaultToolkit();  
	                    Dimension dis = tool.getScreenSize();
		                Image img = icon.getImage();
		                BufferedImage bi = resize(img,380,220);
		                BufferedImage ai = resize(img,dis.width,dis.height);
		                BigScreen.la.setIcon(new ImageIcon(ai));
		                GUI.la_image[s].setIcon(new ImageIcon(bi));
		                GUI.la_image[s].setText(ip);
		                GUI.la_image[s].setIconTextGap(4);
                	}
		                //Thread.sleep(1000/20);
	            	//}
                }
//                catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						//e.printStackTrace();
//	            		System.out.println("错误1");
//				}
                catch (ClassNotFoundException e1) {
						//e1.printStackTrace();
						System.out.println("错误2");
				}catch (IOException e1) {
						//e1.printStackTrace();
						System.out.println("错误3");
                		GUI.la_image[s].setIcon(new ImageIcon(GUI.class.getResource("image/orange.png")));
                		BigScreen.la.setIcon(new ImageIcon(GUI.class.getResource("image/CE.jpg")));
                		//BigScreen.la.setBounds(0, 0, 1000, 700);
                		GUI.la_image[s].setText("主机"+s);
                		GUI.la_image[s].setIconTextGap(15);
				}
                finally {
					try {
						if(st!=null){
							ins.close();
							st.close();
							System.out.println("Server端口关闭！");
						}
					} catch (IOException e) {}
				}  
//	                catch (IOException e) { 
//	                		isAlive = false;    //如果抛出了异常，那么就是断开连接了  跳出无限循环
//	                		GUI.la_image[s].setIcon(new ImageIcon(GUI.class.getResource("/image/huaji.png")));
//	                		//BigScreen.la.setIcon(new ImageIcon("/image/CE.jpg"));
//	                		//BigScreen.la.setBounds(0, 0, 1000, 700);
//	                		GUI.la_image[s].setText("主机"+s);
//	                		GUI.la_image[s].setIconTextGap(15);
//	                }
         }
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
