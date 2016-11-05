package Client;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import ClientUI.CBigScreen;
import Listener.PActionListener;
/**
 * 端口1153
 * 广播功能 学生端接收教师端截屏信息
 * 
 * @author MIKORU
 *
 */
public class ReceiveThread implements Runnable{
	private CBigScreen frame;
	private String ip;
	private Socket st;
	public ReceiveThread(CBigScreen frame,String ip){
		this.frame = frame;
		this.ip = ip;
	}
	@Override
	public void run() {
		while(frame.getFlag()){
				try {
					st = new Socket(ip,1153);
					frame.display();
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
                    
	                BufferedImage ai = resize(img,dis.width,dis.height);
	                frame.la.setIcon(new ImageIcon(ai));
	                Thread.sleep(100);
					
				} catch (IOException e) {
					frame.la.setIcon(null);
					frame.setVisible(false);
				} catch (InterruptedException e) {
				}finally{
	                try {
	                	if(st!=null)
	                		st.close();
	                } catch (IOException e) {}  
	            }
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
