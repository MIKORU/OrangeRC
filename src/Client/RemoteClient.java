package Client;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;


/**
 * Ô¶³Ì¼à¿Ø¿Í»§¶Ë
 * 
 * @author MIKORU
 *
 */
public class RemoteClient {
	private static String ip = "127.0.0.1";
	public static void main(String[] args) throws UnknownHostException, IOException {
        //screen listener
		
        new Thread(new SendThread(ip,1113)).start();
        new Thread(new ReceiveOrder()).start();
        new Thread(new EventThread()).start();
        
        //mouse listener
//        try {
//			socket2 = new Socket("127.0.0.1",1234);
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        new EventThread(socket2).start();
    }
}