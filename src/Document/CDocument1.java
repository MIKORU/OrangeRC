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

import ClientUI.ClientGUI;
import ServerUI.HelpUI;
import sun.applet.Main;

/**
 * ѧ�������ļ�
 * @author Administrator
 *
 */
public class CDocument1 implements Runnable{
	private Socket socket;
	String ip;
	int flag=0;
	int a=0;
	static String cpaths=new ClientGUI().cpaths;
	
//	File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
//	String desktopPath = desktopDir.getAbsolutePath();
	
	public CDocument1(String ip){
		this.ip=ip;
		
	}
	
	
	public void run() {
		while(flag==0){
			//�ӷ������������ļ�
			try { 
					System.out.println(cpaths);
					socket=new Socket(ip,8888);
					DataInputStream is = new  DataInputStream(socket.getInputStream());   
					OutputStream os = socket.getOutputStream();                  
					//1���õ��ļ���     
					
//					String filename="E:\\";
//					if(fpaths.equals("0")){
//					
//						fpaths="C:\\Users\\Administrator\\Desktop\\";
//					}
					String filename=cpaths+is.readUTF();

					
					File file=new File(filename);
					if(!file.exists()){
						System.out.println("�����ɵ��ļ���Ϊ:"+filename); 
						FileOutputStream fos=new FileOutputStream(filename);
						//ͨ���ֽ��������������ݣ��ֽڻ�������
						BufferedOutputStream bos=new BufferedOutputStream(fos);
						DataOutputStream dos = new DataOutputStream(bos);
						byte[] b = new byte[1024]; 
						int length = 0;
						while((length=is.read(b))!=-1){  
							//2����socket������д���ļ��������ȥ  
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
		}
		//�ļ�����ָ�����
		if(a==1){
			new Thread(new CDocument1(ip)).start();//8888
		}
        
	}
//		public static void main(String[] args){
//			String ip="127.0.0.1";
//			new CDocument1(ip,"0");
//		}
}	

