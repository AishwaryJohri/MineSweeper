
import java.util.Random;

public class Start {

	  int i = Parameters.rows+2,j=Parameters.colms+2;
	  static int mine[][]= new int[32][102];
	  static int tip[][]=new int[32][102];
	  static int detect=0;
	  static int validDetect =0;
	  
	  public void paraReset()
	  {
		  detect=0;
		  validDetect =0 ;
	  }
	  
	  public void initMine()
	  {
		  for(int i=0;i<this.i;i++)
			  for(int j=0;j<this.j;j++)
				  mine[i][j]=9;
	  }
	  
	public void setMine()
	{
		int row,col;
		boolean flag;
		Random ra = new Random();
		for(int i=0;i<Parameters.mines;i++)
		{
			do
			{
			   row = (int)ra.nextInt(this.i-2)+1;
			   col = (int)ra.nextInt(this.j-2)+1;
			   
			   if(mine[row][col]!=-1)
			   {
				   flag =false;
				   mine[row][col]=-1;
			   }
			   else
				   flag=true;
			}while(flag);
		}
	}
	
	 public void initTip()
	  {
		  for(int i=0;i<this.i;i++)
			  for(int j=0;j<this.j;j++)
				  tip[i][j]=9;
	  }
	
	public void setTip()
	{
		int mark;
		for(int row=1;row<=this.i-2;row++)
			for(int col=1;col<=this.j-2;col++)
			{ 
				mark=0;
				for(int i=-1;i<=1;i++)
					for(int j=-1;j<=1;j++)
					{
						if(mine[row+i][col+j]==-1)
							mark++;
					}
				if(mine[row][col]==-1)
					tip[row][col]=-1;
				else
				 tip[row][col]=mark;
			}
	}

	public static int result()
	{
		if(validDetect==Parameters.mines)
			return 1;
		if(detect==Parameters.mines)
			return 3;
		if(detect==-1)
			return 0;
		else
		{
		    for(int i=1;i<=Parameters.rows;i++)
			 {
				  for(int j=1;j<=Parameters.colms;j++)
					  {
					       if(mine[i][j]!=-2 && mine[i][j]!=-1 && mine[i][j]!=-3)
					       {
					    	   return 2;
					       }
					  }
			  }
		}
		return 1;
	}
}


