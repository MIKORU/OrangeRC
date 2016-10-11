import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.IIOException;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;

public class RemoteServer {  
	  
    private static Socket st;  
  
    public static void main(String[] args) {  
        ServerSocket server;  
        try {  
            server = new ServerSocket(1123);  
            st = server.accept();   
            // �����̣߳�һ�����ͽ�����һ�����������̲�����ģ�����  
            new SendThread(st).start();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}  
  
  
class SendThread extends Thread {  
    Robot robot;  
    ObjectOutputStream os;  
    BufferedOutputStream bos;  
    ImageOutputStream ios;  
    Rectangle rect;  
    private boolean isAlive = true;  
    Socket st;  
  
    public SendThread(Socket st) {  
        this.st = st;  
        try {
            robot = new Robot();  
            Point p = new Point(0, 0);  
            // �����Ļ��С  
            Toolkit tool = Toolkit.getDefaultToolkit();  
            Dimension dis = tool.getScreenSize();  
            rect = new Rectangle(p, dis);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void run() {  
        try {  
            os = new ObjectOutputStream(st.getOutputStream());  
            BufferedImage img = null;  
            while (isAlive) {  
                // ���ݾ���rect��С���н������õ�BUfferedImage����  
                img = robot.createScreenCapture(rect);  
                // BUfferedImage���л����Ȱ�װ��ImageIcon��д��ȥ  
                ImageIcon icon = new ImageIcon(img);  
                os.writeObject(icon);  
                os.flush();  
                Thread.sleep(1000);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}  