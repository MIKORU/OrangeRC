package Document;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 * 学生接收文件（自己运行）
 * @author Administrator
 *
 */
public class CDDocument extends Thread{
	//服务器端
	int port= 8822;//设置的端口号
	String ip="127.0.0.1";
	public void run(){//接受客户端上传的文件，并保存
		try {  
			Socket socket = new Socket(ip,port); 
			DataInputStream is = null;
			OutputStream os = null;
//			while(true){  
				is = new  DataInputStream(socket.getInputStream());   
				os = socket.getOutputStream(); 
				

				//1、得到文件名       
				String filename="E:\\";
//				JFileChooser jf=new JFileChooser();
//				jf.setFileSelectionMode(jf.FILES_AND_DIRECTORIES);
//				jf.showSaveDialog(null);
//				
//				File file=jf.getSelectedFile();
//				String filename=jf.getSelectedFile().getPath();
				//String filename=jf.getAbsolutePath();
				
				//readUTF:从输入流中读取utf-8编码的数据，并以String字符串的形式返回
				filename += is.readUTF(); 
				//////////////////////////
//				//已有文件存在提示覆盖
//				for(int i=1;i<10;i++){
//					if(file.exists()){
//						filename=filename+"("+i+")";
//					}else  break;
//				}
//				long data=System.currentTimeMillis();
//				if(file.exists()){
//					filename+=File.separator+data;
//				}
				/////////////////////////
				System.out.println("新生成的文件名为:"+filename);
				
				//////////////////////////////////////////////
				//写入诸如图像数据之类的原始字节的流，以byte数组作为数据读入的缓冲区（字节流）
				//要写字符流用FileWriter
				FileOutputStream fos=new FileOutputStream(filename);
				//通过字节数组来缓冲数据（字节缓冲流）
				BufferedOutputStream bos=new BufferedOutputStream(fos);
				DataOutputStream dos = new DataOutputStream(bos);
				////////////////////////////////////////////////
				
//				DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new BufferedOutputStream(new FileOutputStream(filename))));
				//设置数据读入的缓冲区 
				byte[] b = new byte[1024]; 
				int length = 0;  
				while((length=is.read(b))!=-1){  
					//把socket输入流写到文件输出流中去  
					//将指定byte数组从偏移量0开始的length个字节写入此文件输出流
					dos.write(b, 0, length);  
				}  
				dos.flush();  
				dos.close();               
				is.close();  
				socket.close();  
//			}               
		} catch (IOException e) {  
			e.printStackTrace();  
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("socket关闭啦");
		}  
	}

	public static void main(String arg[]) {
		new CDDocument().start();
	}
}