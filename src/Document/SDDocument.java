package Document;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * ��ʦ�����ļ�   
 */
public class SDDocument extends Thread{

	private String ip = "127.0.0.1";// ���óɷ�����IP
	private int port = 8822;//���ö˿ں�
	public void start(){
		//�ӷ������������ļ�
		try { 
			Socket socket = new Socket(ip,port);
			while(true){  
				DataInputStream is = new  DataInputStream(socket.getInputStream());   
				OutputStream os = socket.getOutputStream();                  
				//1���õ��ļ���       
				String filename="E:\\";
				filename += is.readUTF(); 

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
				dos.flush();  
				dos.close();               
				is.close();  
				socket.close();  
			}  

		} catch (IOException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
		}   
	}
}
