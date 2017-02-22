import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class SpButton extends JLabel implements MouseListener {
	
	int row,col;
	boolean open=false;
	Arena frm ;
	JLabel detail,timeTag;
	private String msgStr = "     Status                         Flags :";
	JLabel currL;
	
	public void set(int i)
	{
		this.setText(i+"");
	}
	
    public SpButton(int x,int y,Arena frm,JLabel detail,JLabel timeTag) {
    	this.frm=frm;
    	currL=this;
    	this.detail=detail;this.timeTag=timeTag;
		row=x;col=y;
		this.setIcon(new ImageIcon( (Parameters.tile_p)));
		this.setBackground(new Color(230,230,250));
		this.setForeground(Color.black);
		this.setOpaque(true);
		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(CENTER);
	}
    
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setForeground(Color.BLACK);
		this.setBackground(new Color(230,230,250));
		if(!open)
		{
		this.setIcon(null);
		this.setIcon(new ImageIcon( (Parameters.tile_s)));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		this.setForeground(Color.black);
		this.setBackground(new Color(230,230,250));
		if(!open)
		{
		    this.setIcon(null);
			this.setIcon(new ImageIcon((Parameters.tile_p)));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
		if(Timer.timeFlag==0||Timer.timeFlag==-1)
			new Timer(timeTag);
		
		if(open)
		{
			if(e.getButton()== MouseEvent.BUTTON3 && this.getIcon()!=null)
			{
				open =false;
				this.setIcon(null);
				this.setIcon(new ImageIcon( (Parameters.tile_p)));
				Start.mine[row][col]=9;
				 Start.detect--;
				 detail.setText("   HhhhhMMMMM                       Flags :"+(Parameters.mines-Start.detect));
				if(Start.tip[row][col]==-1)
				   {
					Start.mine[row][col]=-1;
					Start.validDetect--;
				   }
			}
			else
			 detail.setText("Already Disclose                    Flags :"+(Parameters.mines-Start.detect));
		}
		else
		{
			if(e.getButton()==MouseEvent.BUTTON1)
			{
				 
					open = true;
					if(Start.mine[row][col]!=-1)
					{
						frm.field(row, col);
						detail.setText("      SAFE                          Flags :"+(Parameters.mines-Start.detect));
						
					}
					else
					{
						this.setIcon(new ImageIcon( ("mine2.jpg")));
						detail.setText("  OUT OF LUCK!                      Flags :"+(Parameters.mines-Start.detect));
						Start.detect=-1;
					}
				
			}
			
			if(e.getButton()==MouseEvent.BUTTON3)
			{
					open =true;
					this.setIcon(new ImageIcon(("flag.jpg")));
					 Start.detect++;
					 detail.setText("   HhhhhMMMMM                       Flags :"+(Parameters.mines-Start.detect));
					if(Start.mine[row][col]==-1)
					   {
						Start.validDetect++;
					   }	
					Start.mine[row][col]=-3;
						
			}
			
			int res = Start.result();
			
			if(res==1)
			{
				open=false;
				Timer.timeFlag=-1;
				 detail.setText("*******YoU WoN*******");
				 try {
					frm.highscore();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			   	if(JOptionPane.showConfirmDialog(frm, "Congrates u WON .....Play Again....")==0)
			   	{
			   		timeTag.setText("0");
			   		detail.setText(msgStr+Parameters.mines);
					 frm.reset();
				}
			   	
			   	else
			   		System.exit(2210);
			}
			if(res==0||res==3)
			{
				//frm.setEnabled(false);
				Timer.timeFlag=-1;
				frm.allOpen(row,col);
				detail.setText("*******YoU LoSe*******");
				if(res==0)
				{ 
					 if(JOptionPane.showConfirmDialog(frm, "MineBlast..OUT OF LUCK and !! Play Again ...")==0)
					  {
						 timeTag.setText("0  ");
						 detail.setText(msgStr+Parameters.mines);
						 frm.reset();
					  }
					 else
						  System.exit(2210);		  
				}
				
				else
					{
						   if(res==3)
							  {
							   
								 if(JOptionPane.showConfirmDialog(frm, "Marks Displaced..OUT OF LUCK and !! Play Again ...")==0)
								  {
									 this.setIcon(new ImageIcon(("flagWrg.jpg")));
									 timeTag.setText("0");
									 detail.setText(msgStr+Parameters.mines);
									 frm.reset();
								  }
								 else 
										System.exit(2210);
							  }
					}
			  
			}
		}
	}
	
}
