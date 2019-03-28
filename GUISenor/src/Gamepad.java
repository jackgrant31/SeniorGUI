import net.java.games.input.*;
import java.util.*;
import java.util.concurrent.*;


class Gamepad extends Thread{
	private boolean useController = false;
	private ArrayList<Controller> foundGamepads;
	private SynchronousQueue<String> queue;
	private HashMap<Integer,Float> status;
	Gamepad(SynchronousQueue<String> queue){
		super();
		this.queue=queue;
		this.foundGamepads = new ArrayList<Controller>();
		this.status = new HashMap<Integer,Float>();
		Controller[] controllersList = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for (int i = 0; i < controllersList.length; i++) {
			Controller controller = controllersList[i];
			if ((controller.getType() == Controller.Type.STICK) || (controller.getType() == Controller.Type.GAMEPAD)){
				this.foundGamepads.add(controller);
				System.out.println(controller.getName() + " - " + controller.getType().toString());
			}
		}
		if(!this.foundGamepads.isEmpty()){
			this.useController = true;
		}
		
		for(int i=0;i<this.foundGamepads.size();i++){
			Controller con = this.foundGamepads.get(i);
			con.poll();
			Component[] components = con.getComponents();
			for (int j = 0; j < components.length; j++)
			{
				float val = components[j].getPollData();//Log inital values in status map
				val = ((float)((int)(val*10)))/10;//Round to 
				this.status.put(j,val);
				System.out.println(components[j].toString());
			}
		}
	}

	public void run(){
		
		while(this.useController){
			for(int i=0;i<this.foundGamepads.size();i++){
				Controller con = this.foundGamepads.get(i);
				con.poll();
				Component[] components = con.getComponents();
				StringBuffer buffer = new StringBuffer();
				for (int j = 0; j < components.length; j++)
				{
					float val = components[j].getPollData();
					val = ((float)((int)(val*10)))/10;
					if(Math.abs(this.status.get(j)-val)>0.1){
						this.status.put(j,val);
						buffer.append(components[j].toString()+"\t"+j+"\t");
						buffer.append(val);
						buffer.append("\n");
						parse(j,(int)(val*10));
					}
				}
				if(buffer.length()>0){
					buffer.append("********************************************\n");
					System.out.println(buffer.toString());
				}
			}
			try
			{
				Thread.sleep(50);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	void parse(int component,int value){
		try{
			switch(component){//Mapped to xbox controller
				case 0: this.queue.put("16"+map(value,-10,10,-255,255));//Forward control
						break;
				case 1: this.queue.put("18"+map(value,-10,10,-255,255));
						break;
				case 2: this.queue.put("15"+map(value*-1,-10,10,-255,255));//Pitch control
						break;
				case 3: this.queue.put("17"+map(value,-10,10,-255,255));
						break;
				default: this.queue.put("10");//Stop all motors
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	int map(int in, int inMin, int inMax,int outMin,int outMax){
		return (in-inMin)*(outMax-outMin)/(inMax-inMin)+outMin;
	}
	
}

