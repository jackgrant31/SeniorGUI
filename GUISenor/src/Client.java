import java.net.*;
import java.io.*;
import java.util.concurrent.*;
class Client extends Thread{
	private String host;
	private int port;
	private Socket sock;
	private OutputStream outstream;
	private SynchronousQueue<String> queue;
	Client(String host,int port,SynchronousQueue<String> queue){
		super();
		this.host=host;
		this.port=port;
		this.queue=queue;
	}

	public void run(){
		connect(this.host);
		while(true){
			try{
				while(this.sock != null && this.sock.isConnected()){
					String cmd = this.queue.take();
					if(cmd=="q"){
						this.close();
						break;
					}
					this.outstream.write(cmd.getBytes());
				}
				Thread.sleep(1000);
			}catch(IOException e){
				//e.printStackTrace();
			}catch(InterruptedException e){
				//e.printStackTrace();
			}
		}
	}
	public void connect(String host)
	{
		try{
			this.sock = new Socket(this.host,this.port);
			this.outstream = this.sock.getOutputStream();
		}catch(IOException e){
				//e.printStackTrace();
		}
	}
	
	public void close(){
		try{
			this.outstream.close();
			this.sock.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
}

