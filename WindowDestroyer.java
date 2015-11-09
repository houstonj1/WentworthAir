import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class WindowDestroyer extends WindowAdapter {

	public void WindowCling(WindowEvent e){
		System.exit(0);
	}
}
