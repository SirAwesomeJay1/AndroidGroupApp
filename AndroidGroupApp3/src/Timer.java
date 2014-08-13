import java.util.Scanner;
import java.util.TimerTask;
public class Timer {
	public class Stopwatch {
		int interval;
		Timer timer;

	public void main(String[] args) {
		    Scanner sc = new Scanner(System.in);
		    System.out.print("Input seconds => : ");
		    String secs = sc.nextLine();
		    int delay = 1000;
		    int period = 1000;
		    timer = new Timer();
		    interval = Integer.parseInt(secs);
		    System.out.println(secs);
		    timer.scheduleAtFixedRate(new TimerTask() {

		        public void run() {
		            System.out.println(setInterval());

		        }
		    }, delay, period);
		}

		private final int setInterval() {
		    if (interval == 1)
		        timer.cancel();
		    return --interval;
		}
		}

	public void scheduleAtFixedRate(TimerTask timerTask, int delay, int period) {
		
		
	}

	public void cancel() {
		
		
	}
}