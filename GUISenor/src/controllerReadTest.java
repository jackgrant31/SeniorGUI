import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class controllerReadTest extends JFrame{
	private static final long serialVersionUID = -7129976919159465311L;

	private abstract static class AxisPanel extends JPanel{
		private static final long serialVersionUID = -2117191506803328790L;
		Component axis;
		float data;

		public AxisPanel(Component ax){
			axis = ax;
			setLayout(new BorderLayout());
			add(new JLabel(ax.getName()+"("+ax.getIdentifier()+")"),
					BorderLayout.NORTH);
		}

		public void poll(){
			data = axis.getPollData();
			renderData();
		}

		public Component getAxis() {
			return axis;
		}

		protected abstract void renderData();
	}

	private static class DigitalAxisPanel extends AxisPanel {
		private static final long serialVersionUID = -4006900519933869168L;
		JLabel digitalState = new JLabel("<unread>");

		public DigitalAxisPanel(Component ax) {
			super(ax);
			add(digitalState,BorderLayout.CENTER);
		}

		protected void renderData(){
			if (data == 0.0f){
				digitalState.setBackground(getBackground());
				digitalState.setText("OFF");
			} else if ( data == 1.0f) {
				digitalState.setBackground(Color.green);
				digitalState.setText("ON");
			}else { // shoudl never happen
				digitalState.setBackground(Color.red);
				digitalState.setText("ERR:"+data);
			}
			digitalState.repaint();
		}
	}

	private static class DigitalHatPanel extends AxisPanel {
		private static final long serialVersionUID = -3293100130201231029L;
		JLabel digitalState = new JLabel("<unread>");

		public DigitalHatPanel(Component ax) {
			super(ax);
			add(digitalState,BorderLayout.CENTER);
		}

		protected void renderData(){
			if (data == Component.POV.OFF){
				digitalState.setBackground(getBackground());
				digitalState.setText("OFF");
			} else if ( data == Component.POV.UP) {
				digitalState.setBackground(Color.green);
				digitalState.setText("UP");
			} else if ( data == Component.POV.UP_RIGHT) {
				digitalState.setBackground(Color.green);
				digitalState.setText("UP+RIGHT");
			} else if ( data == Component.POV.RIGHT) {
				digitalState.setBackground(Color.green);
				digitalState.setText("RIGHT");
			} else if ( data == Component.POV.DOWN_RIGHT) {
				digitalState.setBackground(Color.green);
				digitalState.setText("DOWN+RIGHT");
			} else if ( data == Component.POV.DOWN) {
				digitalState.setBackground(Color.green);
				digitalState.setText("DOWN");
			} else if ( data == Component.POV.DOWN_LEFT) {
				digitalState.setBackground(Color.green);
				digitalState.setText("DOWN+LEFT");
			} else if ( data == Component.POV.LEFT) {
				digitalState.setBackground(Color.green);
				digitalState.setText("LEFT");    
			} else if ( data == Component.POV.UP_LEFT) {
				digitalState.setBackground(Color.green);
				digitalState.setText("UP+LEFT");
			}else { // shoudl never happen
				digitalState.setBackground(Color.red);
				digitalState.setText("ERR:"+data);
			}
			digitalState.repaint();
		}
	}
	private static class AnalogAxisPanel extends AxisPanel {
		private static final long serialVersionUID = -3220244985697453835L;
		JLabel analogState = new JLabel("<unread>");

		public AnalogAxisPanel(Component ax) {
			super(ax);
			add(analogState,BorderLayout.CENTER);
		}

		protected void renderData(){
			String extra = "";
			if (getAxis().getDeadZone() >= Math.abs(data))
				extra = " (DEADZONE)";
			analogState.setText(""+data+extra);
			analogState.repaint();
		}
	}



	private static class ControllerWindow extends JFrame {
		private static final long serialVersionUID = 5812903945250431578L;
		Controller ca;
		List<AxisPanel> axisList = new ArrayList<>();
		boolean disabled = false;

		public ControllerWindow(JFrame frame,Controller ca){
			super(ca.getName());
			this.setName(ca.getName());
			this.ca = ca;
			Container c = this.getContentPane();
			c.setLayout(new BorderLayout());
			Component[] components = ca.getComponents();
			System.out.println("Component count = "+components.length);
			if (components.length>0) {
				int width = (int)Math.ceil(Math.sqrt(components.length));
				JPanel p = new JPanel();
				p.setLayout(new GridLayout(width,0));
				for(int j=0;j<components.length;j++){
					addAxis(p,components[j]);
				}  
				c.add(new JScrollPane(p),BorderLayout.CENTER);
			}
			setSize(400,400);
			setLocation(50,50);
			setVisible(true);
		}

		public boolean disabled() {
			return disabled;
		}

		private void setDisabled(boolean b){
			disabled = b;
			if (!disabled){
				this.setTitle(ca.getName()); 
				System.out.println(ca.getName()+" enabled");
			} else {
				this.setTitle(ca.getName()+" DISABLED!");
				System.out.println(ca.getName()+" disabled");
			}
			repaint();
		}

		private void addAxis(JPanel p, Component ax){
			AxisPanel p2;
			if (ax.isAnalog()) {
				p2 = new AnalogAxisPanel(ax);
			} else {
				if (ax.getIdentifier() == Component.Identifier.Axis.POV) {
					p2 = new DigitalHatPanel(ax);
				} else {     
					p2 = new DigitalAxisPanel(ax);
				}
			}
			p.add(p2);
			axisList.add(p2);
			//ax.setPolling(true);
		}

		public void poll(){
			if (!ca.poll()) {
				if (!disabled()){
					setDisabled(true);
				}
				return;
			} 
			if (disabled()){
				setDisabled(false);
			}
			//System.out.println("Polled "+ca.getName());
			for(Iterator<AxisPanel> i =axisList.iterator();i.hasNext();){
				try {
					i.next().poll();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	static final long HEARTBEATMS =100; // 10th of a second
	List<ControllerWindow> controllers = new ArrayList<>();

	public controllerReadTest() {
		super("Controller Read Test. Version: ");
		ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment();
		Controller[] ca = ce.getControllers();
		for(int i =0;i<ca.length;i++){
			makeController(ca[i]);
		}

		new Thread(() ->{
				try {
					while(true){
						for(Iterator<ControllerWindow> i=controllers.iterator();i.hasNext();){
							try {
								ControllerWindow cw = i.next();
								cw.poll();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						Thread.sleep(HEARTBEATMS);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		}).start();
		pack();
		setSize(400,400);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void makeController(Controller c) {
		Controller[] subControllers = c.getControllers();
		if (subControllers.length == 0 ) {
			createControllerWindow(c);
		} else {
			for(int i=0;i<subControllers.length;i++){
				makeController(subControllers[i]);
			}
		}
	}

	private void createControllerWindow(Controller c){
		controllers.add(new ControllerWindow(this,c));
	}    

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		new controllerReadTest().setVisible(true);
	}

}
