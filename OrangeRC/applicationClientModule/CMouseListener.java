import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class CMouseListener extends RMouseListener {
	@SuppressWarnings("unused")
	private int order;
	public CMouseListener(int order) {
		// TODO Auto-generated constructor stub
		super();
		this.order = order;
	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		String btnName =((JButton)e.getSource()).getText();
		if(e.getClickCount()==2){
			if(RMouseListener.cl==true){
				new BigScreen(RemoteServer.ip);
			}
		}
	}
}