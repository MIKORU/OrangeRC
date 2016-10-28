package Client;
import java.awt.Event;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * �ͻ��˽��շ���������Ϣ
 * ������꣬����
 * 
 * @author MIKORU
 * @date 2016.10.26
 */
class EventThread implements Runnable{ 
    private ObjectInput objectIn;
    public EventThread(){ 
    } 
    public void run() { 
        try {
        	ServerSocket server =  new ServerSocket(1123);
            Robot robot = new Robot(); 
            while(true){
            	Socket st = server.accept();
            	objectIn = new ObjectInputStream(st.getInputStream());
                Object event = objectIn.readObject(); 
                InputEvent inEvent = (InputEvent)event; 
                if(inEvent!=null)
                	actionEvent(robot,inEvent); 
            }
        } catch (Exception e) { 
            e.printStackTrace();
            
        } 
    }
	public void actionEvent(Robot robot,InputEvent event){ 
		MouseEvent mevent = null ; //����¼�
		MouseWheelEvent mwevent = null ;//�������¼�
		KeyEvent kevent = null ; //�����¼�
		int mousebuttonmask = -100; //��갴��
		switch (event.getID()){
			case MouseEvent.MOUSE_MOVED :   //����ƶ�
				 mevent = ( MouseEvent )event ;
				 robot.mouseMove( mevent.getX() , mevent.getY() );
				 break ;
			case MouseEvent.MOUSE_PRESSED :   //��������
				 mevent = ( MouseEvent ) event;
				 robot.mouseMove( mevent.getX() , mevent.getY() );
				 mousebuttonmask = getMouseClick(mevent.getButton() );
				 if(mousebuttonmask != -100)
				 robot.mousePress(mousebuttonmask);
				 break;
			case MouseEvent.MOUSE_RELEASED :  //�����ɿ�
				 mevent = ( MouseEvent ) event;
				 robot.mouseMove( mevent.getX() , mevent.getY() );
				 mousebuttonmask = getMouseClick( mevent.getButton() );//ȡ����갴��
				 if(mousebuttonmask != -100)
				 robot.mouseRelease( mousebuttonmask );
				 break ;
			case MouseEvent.MOUSE_WHEEL :   //������
				 mwevent = ( MouseWheelEvent ) event ;
				 robot.mouseWheel(mwevent.getWheelRotation());
				 break ;
			case MouseEvent.MOUSE_DRAGGED :   //�����ק
				 mevent = ( MouseEvent ) event ;
				 robot.mouseMove( mevent.getX(), mevent.getY() );
				 break ;
			case KeyEvent.KEY_PRESSED :   //����
				 kevent = ( KeyEvent ) event;
				 robot.keyPress( kevent.getKeyCode() );
				 break ;
			case KeyEvent.KEY_RELEASED :   //�ɼ�
				 kevent= ( KeyEvent ) event ;
				 robot.keyRelease( kevent.getKeyCode() );
				 break ;
			default: break ;
		}
	}
	private static int getMouseClick(int button) { //ȡ����갴��
		if (button == MouseEvent.BUTTON1) //��� ,�м��ΪBUTTON2
		 return InputEvent.BUTTON1_MASK;
		if (button == MouseEvent.BUTTON3) //�Ҽ�
		 return InputEvent.BUTTON3_MASK;
		return -100;
	} 
}