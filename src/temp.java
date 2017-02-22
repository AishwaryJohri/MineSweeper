

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
import java.awt.event.MouseListener;
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

public class temp extends JFrame {

	private temp frm;
	private JPanel contentPane;
	private SpButton l[][] = new SpButton[30][100];
	private JPanel panel = new JPanel(); 
	JLabel timeTag = new JLabel("0");
	private String msgStr = "     Status                         Flags :";
	private JLabel detail = new JLabel(msgStr+Parameters.mines);
	private Appear app ;	
    Start start = new Start();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					
					temp frame = new temp();
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
	public temp() {
		frm=this;
		 app= new Appear(frm);
		setAutoRequestFocus(false);
		ImageIcon img =new ImageIcon("Icon.png");
		 this.setIconImage(img.getImage());
		 //setUndecorated(true);
		 
		 try{
		 File file = new File("D:\\JAVA CORE\\Programs\\Frames\\MineSweeper\\highscore.txt");
			if(!file.exists())
			{
				file.createNewFile();
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("MineSweeper     507084     (0 x 0)     0 mines\n");
				bw.close();
			}
		 }catch(Exception e){}
		 
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100,100,Parameters.colms*27,Parameters.rows*26+100);
		
		
		contentPane = new JPanel();
		
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setVisible(true);

		JLabel detail = new JLabel("     Status                         Flags :" +Parameters.mines);
		detail.setBackground(Color.LIGHT_GRAY);
		detail.setHorizontalAlignment(SwingConstants.CENTER);

		
		
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel mew = new JLabel("New");
		mew.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mew.setHorizontalAlignment(SwingConstants.CENTER);
		JMenuItem m1 = new JMenuItem("Retry");
		JMenuItem m2 = new JMenuItem("HighScore");
		JMenuItem m4 = new JMenuItem("Appearece");
		JMenuItem m3 = new JMenuItem("Exit");
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e)
			{
				m3.doClick();
			}
 			
		});
	
		m1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				    Timer.timeFlag=-1;
	   				timeTag.setText("0");
	   				detail.setText(msgStr+Parameters.mines);
	   				reset();
			}
		});
		
		m2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 File file = new File("D:\\JAVA CORE\\Programs\\Frames\\MineSweeper\\highscore.txt");
	   				String str="";
	   					FileReader fr;BufferedReader br;
	   					try {
	   						  fr = new FileReader(file);
	   					      br = new BufferedReader(fr);
	   					      JOptionPane.showMessageDialog(frm,br.readLine(),"HIGH SCORE...",JOptionPane.INFORMATION_MESSAGE);
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
					app.filler();
					   app.setLocationRelativeTo(frm);
		            	  app.setVisible(true);
				}
			});
				
        mew.addMouseListener(new MouseAdapter() 
        {									
			
			@Override
			public void mouseReleased(MouseEvent e) 
			{	
        			JPopupMenu p = new  JPopupMenu();
						//c = m1.getBackground();
        			m1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2,0));
					m2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,0));
					m3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10,0));
					m4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0));
						p.add(m1);p.add(m2);p.add(m4);p.addSeparator();p.add(m3);	
						p.show(panel,0,0);
			   
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
			    if(e.getSource()==mew)
			    	((JLabel)e.getSource()).setForeground(Color.black);
			}
			
			@Override
			public void mouseEntered(MouseEvent e)
			{
				if(e.getSource()==mew)
			    	((JLabel)e.getSource()).setForeground(Color.ORANGE);
			}
		}); 
        
		
		timeTag.setFont(new Font("Gautami", Font.BOLD, 12));
		timeTag.setForeground(Color.RED);
		timeTag.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(detail, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(mew, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 341, Short.MAX_VALUE)
					.addComponent(timeTag, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(mew, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(timeTag, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(detail, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(new GridLayout(Parameters.rows,Parameters.colms,1,1));
		contentPane.setLayout(gl_contentPane);

        startUp();

		for (int i = 1; i <=Parameters.rows; i++)
			for (int j = 1; j <= Parameters.colms; j++) {
				//l[i-1][j-1] = new SpButton(i, j, this, detail,timeTag);
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
		timeTag.setText("0");
	}
	
	public void allOpen(int a,int s)
	{
		for(int i = 1;i<=Parameters.rows;i++)
			{
				for(int j=1 ;j<=Parameters.colms;j++)
				{
					l[i-1][j-1].open=false;
					
						if(i!=a||j!=s)
						if(Start.tip[i][j]==-1)
						 l[i-1][j-1].setIcon((new ImageIcon("mine.jpg")));
					else
					{
						l[i-1][j-1].setIcon(null);
						l[i-1][j-1].set(Start.tip[i][j]);
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
						l[row-1+i][col-1+j].setText((Start.tip[row+i][col+j])+"");
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
	
		File file = new File("D:\\JAVA CORE\\Programs\\Frames\\MineSweeper\\highscore.txt");
		
		if(file.exists())
		{
		String str="";
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			try
			{
				while( ((Integer.parseInt(br.readLine().split("     ")[1])))>Integer.parseInt(timeTag.getText()))
				{
					String temp = JOptionPane.showInputDialog(this,"HIGH SCORE..please give name");
					if(temp!=null)
					{
						FileWriter fw = new FileWriter(file);
						BufferedWriter bw = new BufferedWriter(fw);
					        bw.write(temp+"     "+timeTag.getText()+"     ("+Parameters.rows+" x "+Parameters.colms+")     "+Parameters.mines+" mines"+"\n");
					        bw.close();
					}
				}
			}
			catch(Exception e){}
			
			finally {
				br.close();
			}
		}
	}
}
