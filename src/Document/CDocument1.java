package Document;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.filechooser.FileSystemView;

import sun.applet.Main;

/**
 * 学生接收文件
 * @author Administrator
 *
 */
public class CDocument1 implements Runnable{
	private Socket socket;
	String ip;
	int flag=0;
	int a=0;
	String fpaths=null;
//	File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
//	String desktopPath = desktopDir.getAbsolutePath();
	
	public CDocument1(String ip,String fpaths){
		this.ip=ip;
		this.fpaths=fpaths;
		
	}
	
	
	public void run() {
		while(flag==0){
			//从服务器端下载文件
			try { 
					System.out.println(a);	
					socket=new Socket(ip,8888);
					DataInputStream is = new  DataInputStream(socket.getInputStream());   
					OutputStream os = socket.getOutputStream();                  
					//1、得到文件名     
					
//					String filename="E:\\";
					if(fpaths.equals("0")){
					
						fpaths="C:\\Users\\Administrator\\Desktop\\";
					}
					String filename=fpaths+is.readUTF();

					
					File file=new File(filename);
					if(!file.exists()){
						System.out.println("新生成的文件名为:"+filename); 
						FileOutputStream fos=new FileOutputStream(filename);
						//通过字节数组来缓冲数据（字节缓冲流）
						BufferedOutputStream bos=new BufferedOutputStream(fos);
						DataOutputStream dos = new DataOutputStream(bos);
						byte[] b = new byte[1024]; 
						int length = 0;
						while((length=is.read(b))!=-1){  
							//2、把socket输入流写到文件输出流中去  
							fos.write(b, 0, length);  
						}
						flag=1;
						a=1;
						dos.flush();  
						dos.close();               
						is.close();  
						socket.close();
					}
					  

			} catch (IOException e) {  
				// TODO Auto-generated catch block  
//				e.printStackTrace();  
			} 
			System.out.println(a);
		}
		//文件传输指令接收
		if(a==1){
			System.out.println("开8888");
			new Thread(new CDocument1(ip,fpaths)).start();//8888
		}
        
	}
		public static void main(String[] args){
			String ip="127.0.0.1";
			new CDocument1(ip,"0");
		}
}	


