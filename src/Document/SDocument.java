package Document;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 教师接收文件（端口号8822）
 * 开机时就要准备端口
 * @author Administrator
 *
 */
public class SDocument extends ServerSocket implements Runnable{
	private static int port=8822;
	public SDocument() throws IOException{
		//相当于ServerSocket server= new ServerSocket(port);
		super(port);
	}
//	public static void main(String[] args) {
//		try {
//			new SDocument();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	@Override
	public void run() {
		try{
			System.out.println("启动文件接收");
			while(true){
				Socket socket=this.accept();
				//当有学生发送了文件，调用
				new Document(socket);
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				this.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
	}
	
}
