package Document;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import ServerUI.HelpUI;
/**
 * ��ʦ�˵���
 * @author Administrator
 *
 */
public class Document extends Thread{

	private Socket socket;
	static String fpaths=new HelpUI().fpaths;
	public Document(Socket socket) {
		super();
		this.socket=socket;
		this.start();
	}
	@Override
	public void run(){
		//�ӷ������������ļ�
		try { 
				System.out.println(fpaths);
				DataInputStream is = new  DataInputStream(socket.getInputStream());   
				OutputStream os = socket.getOutputStream();                  
				//1���õ��ļ���  
//				if(fpaths.equals("0")){
//					fpaths="C:\\Users\\Administrator\\Desktop\\";
//				}
				String filename=fpaths+is.readUTF();

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

		} catch (IOException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
		}  
	}
	
}
