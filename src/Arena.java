
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.border.TitledBorder;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.BorderLayout;
import java.awt.SystemColor;

public class Arena extends JFrame {

	private Arena frm;
	private JPanel contentPane;
	private SpButton l[][] = new SpButton[25][51];
	private JPanel panel = new JPanel(); 
	JLabel timeTag = new JLabel("0  ");
	private String msgStr = "     Status                         Flags :";
	//private JLabel detail = new JLabel(msgStr+Parameters.mines);
	private Appear app ;	
    Start start = new Start();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					
					Arena frame = new Arena();
					 frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	/**
	 * Create the frame.
	 * 
	 */
	public Arena() {
		setResizable(false);
		frm=this;
		setAutoRequestFocus(false);
		ImageIcon img =new ImageIcon( ("Icon.png"));
		 this.setIconImage(img.getImage());
		 
		  try{
				 
				 File file = new File("D:\\Parameters.conf");
					if(!file.exists())
					{
						file.createNewFile();
						FileWriter fw = new FileWriter(file);
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write("16\n16\n40\nset7_p.jpg\nset7_s.jpg\n6\n1\n");
						bw.flush();
						bw.close();
					}
			 }catch(Exception e){}
			 
			 try{
				
			 File file = new File("D:\\highscore.conf");
				if(!file.exists())
				{
					file.createNewFile();
					FileWriter fw = new FileWriter(file);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("Easy:     MineSweeper     507084     0 mines\nMedium:     MineSweeper     507084     0 mines\nHard:     MineSweeper     507084     0 mines\n");
					bw.flush();
					bw.close();
				}
			 }catch(Exception e){}
			 
			 String Para[]=new String[7];
			 
			 File file = new File("D:\\Parameters.conf");
				FileReader fr;BufferedReader br;
					try {
						  fr = new FileReader(file);
					      br = new BufferedReader(fr);
					      int i=0;
					      while(i<7)
					      {
					    	  Para[i]=br.readLine().trim();
					    	  i++;
					      }
					      br.close();
					      fr.close();
					} catch (Exception e1) {}
					finally
			        {
						Parameters.rows=Integer.parseInt(Para[0]);
						Parameters.colms=Integer.parseInt(Para[1]);
						Parameters.mines=Integer.parseInt(Para[2]);
						Parameters.tile_p=Para[3];
						Parameters.tile_s =Para[4];
						Parameters.id=Integer.parseInt(Para[5]);
						Parameters.level=Integer.parseInt(Para[6]); 
						app= new Appear(frm);
						start = new Start();
			        }
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(300,100,Parameters.colms*27,Parameters.rows*26+90);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu New = new JMenu(" New ");
		New.setHorizontalAlignment(SwingConstants.CENTER);
		New.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				New.setForeground(Color.ORANGE);
			}
			
			public void mouseExited(MouseEvent e)
			{
				New.setForeground(Color.BLACK);
			}
		});
		
		New.setBackground(SystemColor.activeCaption);
		menuBar.add(New);
		
		JMenuItem m1 = new JMenuItem("Retry");
		New.add(m1);
		m1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,0));
		
		JMenuItem m2 = new JMenuItem("HighScore");
		New.add(m2);
		m2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2,0));
		
		JMenuItem m4 = new JMenuItem("Appearence");
		New.add(m4);
		m4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0));

		New.addSeparator();
		
		JMenuItem m3 = new JMenuItem("Exit");
		New.add(m3);
		m3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11,0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.controlHighlight);
		menuBar.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
	    timeTag = new JLabel("0  ");
		timeTag.setForeground(new Color(255, 0, 0));
		timeTag.setFont(new Font("Gautami", Font.BOLD, 13));
		panel_1.add(timeTag, BorderLayout.EAST);
		
		contentPane = new JPanel();
		
		setContentPane(contentPane);
		this.setVisible(true);

		JLabel detail = new JLabel("     Status                         Flags :" +Parameters.mines);
		detail.setBackground(Color.LIGHT_GRAY);
		detail.setHorizontalAlignment(SwingConstants.CENTER);
		panel.setBackground(Color.LIGHT_GRAY);

		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e)
			{
				m3.doClick();
			}
			
			public void windowDeiconified(WindowEvent e)
			{
				Timer.startTime=Timer.lastTime;
				if(Timer.timeFlag==2)
			    Timer.timeFlag=1;
			}
			
			public void windowIconified(WindowEvent e)
			{
				Timer.cutTime=Integer.parseInt(timeTag.getText().trim());
				if(Timer.cutTime!=0)
				  Timer.timeFlag=2;
			}
		});
	
		m1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(frm, "R U Sure..... [:D)")==0)
				{
				    Timer.timeFlag=-1;
	   				timeTag.setText("0  ");
	   				detail.setText(msgStr+Parameters.mines);
	   				reset();
				}
			}
		});
		
		m2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 File file = new File("D:\\highscore.conf");
	   				FileReader fr;BufferedReader br;
	   					try {
	   						  fr = new FileReader(file);
	   					      br = new BufferedReader(fr);
	   					      String str ="";
	   					      int i=0;
							while(i++<3)
	   					      {
	   					    	str+=br.readLine()+"\n";
	   					      }
	   					      JOptionPane.showMessageDialog(frm,str,"HIGH SCORE...",JOptionPane.INFORMATION_MESSAGE);
	   					      br.close();
	   					} catch (Exception e1) {}
			}
		});
					
		
			m3.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(JOptionPane.showConfirmDialog(frm,"R U Sure..... [:D)")==0)
					{
						System.exit(2210);
					}
				}
			});
						
			m4.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					frm.setEnabled(false);
					app.filler();
					   app.setLocationRelativeTo(frm);
		            	  app.setVisible(true);
				}
			});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(detail, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(detail, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(new GridLayout(Parameters.rows,Parameters.colms,1,1));
		contentPane.setLayout(gl_contentPane);

        startUp();

		for (int i = 1; i <=Parameters.rows; i++)
			for (int j = 1; j <= Parameters.colms; j++) {
				l[i-1][j-1] = new SpButton(i, j, this, detail,timeTag);
				panel.add(l[i-1][j-1]);
				l[i-1][j-1].addMouseListener(l[i-1][j-1]);
			}
		
	}
	
	public void startUp()
	{
		start.paraReset();
		start.initMine();
		start.initTip();
		start.setMine();
		start.setTip();
		start.paraReset();
	}
	
	public void reset()
	{
		
		startUp();
		for (int i = 1; i <=Parameters.rows; i++)
			for (int j = 1; j <= Parameters.colms; j++) {
				{
					l[i-1][j-1].open=false;
					l[i-1][j-1].setText(null);
				    l[i-1][j-1].setIcon(new ImageIcon(Parameters.tile_p));
				}
			}
		panel.repaint();
		timeTag.setText("0  ");
	}
	
	public void allOpen(int a,int s)
	{
		for(int i = 1;i<=Parameters.rows;i++)
			{
				for(int j=1 ;j<=Parameters.colms;j++)
				{
					l[i-1][j-1].open=false;
					
						if(i!=a||j!=s){
						if(Start.tip[i][j]==-1)
						 {
							if(Start.mine[i][j]==-3)
							 l[i-1][j-1].setIcon(new ImageIcon(("flagCorr.jpg")));
							else
							 l[i-1][j-1].setIcon(new ImageIcon(("mine.jpg")));
						 }
					else
					{
						if(Start.mine[i][j]==-3)
							l[i-1][j-1].setIcon(new ImageIcon(("flagWrg.jpg")));
						else
						{
							l[i-1][j-1].setIcon(null);
							if(Start.tip[i][j]!=0)
							 l[i-1][j-1].set(Start.tip[i][j]);
						}
					}
				 }
						else{
							if(Start.mine[a][s]==-3 ){
								if(Start.tip[a][s]==-1)
								  l[i-1][j-1].setIcon(new ImageIcon(("flagCorr.jpg")));	
								 else
									 l[i-1][j-1].setIcon(new ImageIcon(("flagWrg.jpg")));
							}     
								
						}
			   } 
			}
	}
	
	public void field(int row,int col)
	{
		if(Start.tip[row][col]==0 && Start.tip[row][col]!=9)
		{
			for(int i=-1;i<=1;i++)
			{
				for(int j=-1;j<=1;j++)
				{
					if(Start.tip[row+i][col+j]==0 && (i!=0 || j!=0) && !(l[row-1+i][col-1+j].open))
						{
						   field(row+i,col+j);
						}
					if(row-1+i>=0 && col-1+j>=0 && row-1+i<Parameters.rows && col-1+j<Parameters.colms )
					{
						l[row-1+i][col-1+j].open=true;
						l[row-1+i][col-1+j].setIcon(null);
						 if(Start.tip[row+i][col+j]!=0)
						l[row-1+i][col-1+j].set(Start.tip[row+i][col+j]);
						Start.mine[row+i][col+j]=-2;
					}
				}
			}
			return;
		}
		l[row-1][col-1].open=true;
		l[row-1][col-1].setIcon(null);
		l[row-1][col-1].setText((Start.tip[row][col])+"");
		Start.mine[row][col]=-2;
	}
	
	
	public void highscore() throws IOException {
	
		File file = new File("D:\\highscore.conf");
		if(file.exists())
		{
			if(Parameters.level!=3)
			{
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			FileWriter fw=null;
			BufferedWriter bw =null;
			String str[] =new String[3];
			String level[]={"Easy","Medium","Hard"};
			try
			{
				int i=0;
				while(i<3)
				{
					str[i]=br.readLine();
					i++;
				}
				fr.close();
				br.close();
				fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
				if( ((Integer.parseInt(str[Parameters.level].split("     ")[2])))>Integer.parseInt(timeTag.getText().trim()))
				{
					String temp = JOptionPane.showInputDialog(this,"HIGH SCORE..please give name");
					if(temp!=null)
					{
					        str[Parameters.level]=level[Parameters.level]+":     "+temp+"     "+timeTag.getText()+"     "+Parameters.mines+" mines";	       
					}
					else
					{
						str[Parameters.level]=str[Parameters.level];
					}
				}
				i=0;
				while(i<3)
				{
					bw.write(str[i]+"\n");
					i++;
				}
			}
			catch(Exception e){}
			
			finally {
				bw.close();fw.close();
			}
		}
	  }
	}
}
