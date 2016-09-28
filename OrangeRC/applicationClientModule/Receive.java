import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ObjectInputStream;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/** 
 * ���շ�������ͼƬ 
 *  
 * @author MIKORU
 * @date 2016.09.27
 */  
public class Receive extends Thread {  
		boolean isAlive = true;  
		ImageIcon icon;
		
	

		public void run(JLabel la_image,ObjectInputStream ins) {
            try {  
                while (isAlive) {  

                	icon = (ImageIcon) ins.readObject();
 
                    // ����ͼƬ��С����contentPane��С�� 
                	 Image img = icon.getImage();
                     Toolkit tk = Toolkit.getDefaultToolkit() ;
                     Dimension d =tk.getScreenSize();

                         int w = d.width;
                         int h =d.height;
                            BufferedImage bi = resize(img,la_image.getWidth(),la_image.getHeight());
 
                            la_image.setIcon(new ImageIcon(bi));
                            la_image.repaint();//������ǰ���ı���
                    Thread.sleep(1000);  
                }  
            } catch (Exception e1) {  
                e1.printStackTrace();  
            }
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