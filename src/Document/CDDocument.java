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
 * ѧ�������ļ����Լ����У�
 * @author Administrator
 *
 */
public class CDDocument extends Thread{
	//��������
	int port= 8822;//���õĶ˿ں�
	String ip="127.0.0.1";
	public void run(){//���ܿͻ����ϴ����ļ���������
		try {  
			Socket socket = new Socket(ip,port); 
			DataInputStream is = null;
			OutputStream os = null;
//			while(true){  
				is = new  DataInputStream(socket.getInputStream());   
				os = socket.getOutputStream(); 
				

				//1���õ��ļ���       
				String filename="E:\\";
//				JFileChooser jf=new JFileChooser();
//				jf.setFileSelectionMode(jf.FILES_AND_DIRECTORIES);
//				jf.showSaveDialog(null);
//				
//				File file=jf.getSelectedFile();
//				String filename=jf.getSelectedFile().getPath();
				//String filename=jf.getAbsolutePath();
				
				//readUTF:���������ж�ȡutf-8��������ݣ�����String�ַ�������ʽ����
				filename += is.readUTF(); 
				//////////////////////////
//				//�����ļ�������ʾ����
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
				System.out.println("�����ɵ��ļ���Ϊ:"+filename);
				
				//////////////////////////////////////////////
				//д������ͼ������֮���ԭʼ�ֽڵ�������byte������Ϊ���ݶ���Ļ��������ֽ�����
				//Ҫд�ַ�����FileWriter
				FileOutputStream fos=new FileOutputStream(filename);
				//ͨ���ֽ��������������ݣ��ֽڻ�������
				BufferedOutputStream bos=new BufferedOutputStream(fos);
				DataOutputStream dos = new DataOutputStream(bos);
				////////////////////////////////////////////////
				
//				DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new BufferedOutputStream(new FileOutputStream(filename))));
				//�������ݶ���Ļ����� 
				byte[] b = new byte[1024]; 
				int length = 0;  
				while((length=is.read(b))!=-1){  
					//��socket������д���ļ��������ȥ  
					//��ָ��byte�����ƫ����0��ʼ��length���ֽ�д����ļ������
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
			System.out.println("socket�ر���");
		}  
	}

	public static void main(String arg[]) {
		new CDDocument().start();
	}
}