
import javax.swing.JLabel;

public class Timer implements Runnable{
	
	public static int timeFlag=0,cutTime=0;
	public static int startTime,lastTime;
    private JLabel l;
	
     public Timer(JLabel l){
    	 l.setText("0  ");
    	 startTime=(int) (System.currentTimeMillis()/1000);
    	 lastTime=startTime;
    	 this.l=l;
    	 timeFlag=1;	 
    	 new Thread(this).start();
     }
	
	@Override
	public void run() {
	do
	{
		  lastTime=(int) (System.currentTimeMillis()/1000);
		if(timeFlag==1)
		  l.setText(((int)(lastTime-startTime)+cutTime)+"  ");
	}while(timeFlag!=-1);
	
	timeFlag=0;
	cutTime=0;
	}
}
