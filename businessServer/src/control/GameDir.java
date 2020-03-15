package control;

import model.ServerRunable;
import view.BusinessView;

public class GameDir {
	private MyActionListener myac;
	private ServerRunable sr;
	public GameDir() {
		
		myac=new MyActionListener();
		BusinessView frame = new BusinessView(myac);
		myac.setBusinessframe(frame);
		frame.setVisible(true);
	}
}
