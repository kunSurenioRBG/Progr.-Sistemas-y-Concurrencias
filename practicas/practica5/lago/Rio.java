
public class Rio extends Thread {
	private Lago lago;
	
	public Rio(Lago lago) {
		this.lago = lago;
	}
	
	public void run() {
		for(int i= 0; i < 2000; i++){
			lago.incRio();
		}
	}
}
