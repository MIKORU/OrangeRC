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
 * 客户端接收服务器端信息
 * 控制鼠标，键盘
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
		MouseEvent mevent = null ; //鼠标事件
		MouseWheelEvent mwevent = null ;//鼠标滚动事件
		KeyEvent kevent = null ; //键盘事件
		int mousebuttonmask = -100; //鼠标按键
		switch (event.getID()){
			case MouseEvent.MOUSE_MOVED :   //鼠标移动
				 mevent = ( MouseEvent )event ;
				 robot.mouseMove( mevent.getX() , mevent.getY() );
				 break ;
			case MouseEvent.MOUSE_PRESSED :   //鼠标键按下
				 mevent = ( MouseEvent ) event;
				 robot.mouseMove( mevent.getX() , mevent.getY() );
				 mousebuttonmask = getMouseClick(mevent.getButton() );
				 if(mousebuttonmask != -100)
				 robot.mousePress(mousebuttonmask);
				 break;
			case MouseEvent.MOUSE_RELEASED :  //鼠标键松开
				 mevent = ( MouseEvent ) event;
				 robot.mouseMove( mevent.getX() , mevent.getY() );
				 mousebuttonmask = getMouseClick( mevent.getButton() );//取得鼠标按键
				 if(mousebuttonmask != -100)
				 robot.mouseRelease( mousebuttonmask );
				 break ;
			case MouseEvent.MOUSE_WHEEL :   //鼠标滚动
				 mwevent = ( MouseWheelEvent ) event ;
				 robot.mouseWheel(mwevent.getWheelRotation());
				 break ;
			case MouseEvent.MOUSE_DRAGGED :   //鼠标拖拽
				 mevent = ( MouseEvent ) event ;
				 robot.mouseMove( mevent.getX(), mevent.getY() );
				 break ;
			case KeyEvent.KEY_PRESSED :   //按键
				 kevent = ( KeyEvent ) event;
				 robot.keyPress( kevent.getKeyCode() );
				 break ;
			case KeyEvent.KEY_RELEASED :   //松键
				 kevent= ( KeyEvent ) event ;
				 robot.keyRelease( kevent.getKeyCode() );
				 break ;
			default: break ;
		}
	}
	private static int getMouseClick(int button) { //取得鼠标按键
		if (button == MouseEvent.BUTTON1) //左键 ,中间键为BUTTON2
		 return InputEvent.BUTTON1_MASK;
		if (button == MouseEvent.BUTTON3) //右键
		 return InputEvent.BUTTON3_MASK;
		return -100;
	} 
}