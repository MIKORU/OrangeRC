package Document;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sun.rmi.runtime.Log;
import Server.RemoteServer;
/**
 * ��ʦ�����ļ����˿ں�8821��
 * ����ʱ��Ҫ׼���˿�
 * 
 * @author Administrator
 *
 */
public class SDocument1 extends ServerSocket implements Runnable{
	private static int port=8888;
	String order;
	String ip;
	int count=0;
	
	public SDocument1() throws IOException {
		super(port);
		ip=null;
		
	}
	public SDocument1(int Iorder) throws IOException{
		super(port);
		ip=RemoteServer.ips[Iorder];
		
	}
	public void run(){
		try{
			System.out.println("�����ļ�����");
			File fi=new selectFile().select();
			int flag=0;
			SimpleDateFormat df = new SimpleDateFormat("ssSSS");//�������ڸ�ʽ
			String s=df.format(new Date());
			int b=Integer.parseInt(s);
			int a=0;
			if(ip==null){
				//��ʱ���жϹرս���
				while(flag==0){ 
					String s1=df.format(new Date());
					a=Integer.parseInt(s1);
					if(a-b>=5000){
						flag=1;
					}
					Socket socket=this.accept();
					new Document1(socket,fi);
					
				}
			}
			else{
				Socket socket=this.accept();
				new Document1(socket,fi);
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				this.close();
				System.out.println("�ر��ļ�����");
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		try {
			new SDocument1();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
