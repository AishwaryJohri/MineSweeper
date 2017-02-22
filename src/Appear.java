

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;

public class Appear extends JFrame implements ItemListener,MouseListener,KeyListener {
	
	private JTextField row_no;
	private JTextField col_no;
	private JTextField mine_no;
	private JRadioButton Radio_B;private JRadioButton Radio_I;private JRadioButton Radio_A;private JRadioButton Radio_C;
	private JLabel row,col,mine;
	private JLabel right,left,img,img_L,img_R;
	private JButton ok;
	private int Rows_c=Parameters.rows,Cols_c=Parameters.colms,Mines_c=Parameters.mines;
	private Boolean flag=false;
    private JFrame currF;
    private String str_p[] = {"set1_p.jpg","set2_p.jpg","set3_p.jpg","set4_p.jpg","set5_p.jpg","set6_p.jpg","set7_p.jpg"};
    private String str_s[] = {"set1_s.jpg","set2_s.jpg","set3_s.jpg","set4_s.jpg","set5_s.jpg","set6_s.jpg","set7_s.jpg"};
    private String str_d[] = {"set1_d.jpg","set2_d.jpg","set3_d.jpg","set4_d.jpg","set5_d.jpg","set6_d.jpg","set7_d.jpg"};
    private String str_ds[] = {"set1_ds.jpg","set2_ds.jpg","set3_ds.jpg","set4_ds.jpg","set5_ds.jpg","set6_ds.jpg","set7_ds.jpg"};
    private String str_gr[] = {"set1_gr.jpg","set2_gr.jpg","set3_gr.jpg","set4_gr.jpg","set5_gr.jpg","set6_gr.jpg","set7_gr.jpg"};
	private int id=0;
	
	public void filler()
	{
		row_no.setText(Parameters.rows+"");
		col_no.setText(Parameters.colms+"");
		mine_no.setText(Parameters.mines+"");
	}
	
	public Appear(JFrame frm)
	{
    	
		super("Appearece");
		setResizable(false);
		setAlwaysOnTop(true);
		currF=this;
	   this.setSize(380,347);
	   setLocationRelativeTo(frm);
	   this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	   
	   this.addWindowListener(new WindowAdapter() {
		   
		   public void windowClosing(WindowEvent e)
		   {
			   frm.setEnabled(true);
			   currF.dispose();
		   }
		   
		   
	});
	   
	   
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		row_no = new JTextField();
		row_no.setEnabled(false);
		row_no.setColumns(10);
		
		col_no = new JTextField();
		col_no.setEnabled(false);
		col_no.setColumns(10);
		
		mine_no = new JTextField();
		mine_no.setEnabled(false);
		mine_no.setColumns(10);
		
		ButtonGroup group = new  ButtonGroup();
		
	    Radio_B = new JRadioButton("Beginner(10 x 10)"); 
	    group.add(Radio_B);Radio_B.addItemListener(this);
		
		Radio_I = new JRadioButton("Intermediate(16 x 16)");
		group.add(Radio_I);Radio_I.addItemListener(this);
		Radio_I.setSelected(true);
		
		Radio_A = new JRadioButton("Advanced(16 x 30)");
		group.add(Radio_A);Radio_A.addItemListener(this);
		
		Radio_C = new JRadioButton("Custom");
		group.add(Radio_C);Radio_C.addItemListener(this);
		
		 row = new JLabel("Rows   ");
		
		 col = new JLabel("Colm   ");
		
		 mine = new JLabel("Mines   ");
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(Radio_B)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(Radio_I)
							.addGap(23)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(row, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
								.addComponent(mine, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
								.addComponent(col, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)))
						.addComponent(Radio_A))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(31)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(mine_no, 0, 0, Short.MAX_VALUE)
								.addComponent(col_no, 0, 0, Short.MAX_VALUE)
								.addComponent(row_no, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Radio_C)))
					.addGap(31))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(Radio_B)
						.addComponent(Radio_C))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(row_no, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(row))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(col_no, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(col)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(33)
							.addComponent(Radio_I)))
					.addGap(12)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(Radio_A)
						.addComponent(mine)
						.addComponent(mine_no, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frm.setEnabled(true);
				currF.dispose();
			}
		});
		
	    ok = new JButton("OK");
	    ok.addKeyListener(this);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(flag)
				{
					int x,y;
					id=0;
						if(row_no.getText().length()<4 && Pattern.compile("[0-9]*").matcher(row_no.getText()).matches() && !row_no.getText().equals("") && Integer.parseInt(row_no.getText())<=24 && Integer.parseInt(row_no.getText())>9) 
						{
							Parameters.rows=Integer.parseInt(row_no.getText());
							Rows_c = Integer.parseInt(row_no.getText());
							row.setForeground(Color.BLACK);;
							row.setText("Rows   ");
							row.setToolTipText(null);
							row_no.setToolTipText(null);
							x=Integer.parseInt(row_no.getText());
						}
						else
						{
							    x=Rows_c;
								id=-1;
								row_no.setText("");
								row.setForeground(Color.RED);;
								row.setText("Rows * ");
								row.setToolTipText("Enter value in between 10-24");
								row_no.setToolTipText("Enter value in between 10-24");
						}
						
						if(col_no.getText().length()<4 && Pattern.compile("[0-9]*").matcher(col_no.getText()).matches() && !col_no.getText().equals("") && Integer.parseInt(col_no.getText())<=50 && Integer.parseInt(col_no.getText())>9)
						{
						   Parameters.colms=Integer.parseInt(col_no.getText());
						   Cols_c=Integer.parseInt(col_no.getText());
						   col.setForeground(Color.BLACK);
						   col.setText("Colm   ");
						   col.setToolTipText(null);
						   col_no.setToolTipText(null);
						   y = Integer.parseInt(col_no.getText());
						}
						
						else
						{
							y=Cols_c;
							id=-1;
							col_no.setText("");
							col.setForeground(Color.RED);
							col.setText("Colm * ");
							col.setToolTipText("Enter value in between 10-50");
							col_no.setToolTipText("Enter value in between 10-50");
						}
	
						if(mine_no.getText().length()<4 && Pattern.compile("[0-9]*").matcher(mine_no.getText()).matches() && !mine_no.getText().equals("") && Integer.parseInt(mine_no.getText()) <= x*y-15 && Integer.parseInt(mine_no.getText())>9 )
						{
							Parameters.mines=Integer.parseInt(mine_no.getText());
						    Mines_c = Integer.parseInt(mine_no.getText());
						    mine.setForeground(Color.BLACK);
						    mine.setText("Mines   ");
						    mine.setToolTipText(null);
						    mine_no.setToolTipText(null);
						}
						else
						{
							id=-1;
							mine_no.setText("");
							mine.setForeground(Color.RED);
							mine.setText("Mines * ");
							mine.setToolTipText("Enter value in between 10-"+(x*y-15));
							mine_no.setToolTipText("Enter value in between 10-"+(x*y-15));
						}
						
						if(id!=-1)
							Parameters.level=3;
				}
				else
				{
					if(id==1)
					{
						Rows_c=10;Cols_c=10;Mines_c=10;Parameters.level=0;
					}
					if(id==2)
					{
						Rows_c=16;Cols_c=16;Mines_c=40;Parameters.level=1;
					}
					if(id==3)
					{
						Rows_c=16;Cols_c=30;Mines_c=99;Parameters.level=2;
					}
				  Parameters.rows=Rows_c;Parameters.colms=Cols_c;Parameters.mines=Mines_c;
				}
				
				if(id!=-1)
				{
				
					Timer.timeFlag=0;
					
					Parameters.tile_p= str_p[Parameters.id];
					Parameters.tile_s= str_s[Parameters.id];
					
					frm.setEnabled(true);
					
						File f = new File("D:\\Parameters.conf");
						
						if(!f.exists())
							try {
								f.createNewFile();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						
						FileWriter fw = null;
						
						try {
							fw = new FileWriter(f);
							BufferedWriter bw = new BufferedWriter(fw);
							String code=Rows_c+"\n"+Cols_c+"\n"+Mines_c+"\n"+Parameters.tile_p+"\n"+Parameters.tile_s+"\n"+Parameters.id+"\n"+Parameters.level+"\n";
						    bw.write(code); 
						    bw.close();
						}
						catch(Exception ev){};
						
						currF.dispose();
						frm.dispose();
		       	         new Arena();		
				}
			}
		});
		
		JLabel lblTiles = new JLabel("Tiles");
		lblTiles.setFont(new Font("SimSun-ExtB", Font.BOLD, 12));
		lblTiles.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addComponent(ok)
					.addPreferredGap(ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
					.addComponent(cancel)
					.addGap(50))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(159)
					.addComponent(lblTiles, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(166, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTiles, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(ok)
						.addComponent(cancel))
					.addContainerGap())
		);
		
		img = new JLabel("");
		img.setIcon(new ImageIcon( (str_d[Parameters.id])));
		img.setHorizontalAlignment(SwingConstants.CENTER);
		
		left = new JLabel("<");
		left.setOpaque(true);
		left.addMouseListener(this);
		left.setFont(new Font("Segoe Print", Font.BOLD, 26));
		left.setForeground(new Color(0, 0, 0));
		left.setHorizontalAlignment(SwingConstants.CENTER);
		
		right = new JLabel(">");
		right.addMouseListener(this);
		right.setOpaque(true);
		right.setFont(new Font("Segoe Print", Font.BOLD, 22));
		right.setHorizontalAlignment(SwingConstants.CENTER);
		
	     img_L = new JLabel();
	     img_L.addMouseListener(new MouseAdapter() {
	     	public void mouseReleased(MouseEvent arg0) {
	     		if(Parameters.id>0){
	     		img.setIcon(new ImageIcon( (str_d[--Parameters.id])));
	 		   if(Parameters.id >0)
	 		     img_L.setIcon(new ImageIcon(  ( str_ds[Parameters.id-1])));
	 		   else
	 			   img_L.setIcon(null);
	 		   img_R.setIcon(new ImageIcon(  ( str_ds[Parameters.id+1])));
	     		}
	     	}
	     });
	     if(Parameters.id>0)
	      img_L.setIcon(new ImageIcon((str_ds[Parameters.id-1])));
		
		img_R = new JLabel();
		img_R.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(Parameters.id<str_p.length-1){
				img.setIcon(new ImageIcon( ( str_d[++Parameters.id])));
				   img_L.setIcon(new ImageIcon(  ( str_ds[Parameters.id-1])));
				   if(Parameters.id < str_p.length-1)
				     img_R.setIcon(new ImageIcon( ( str_ds[Parameters.id+1])));
				   else
					   img_R.setIcon(null);
				}
			}
		});
		if(Parameters.id< str_p.length-1)
		      img_R.setIcon(new ImageIcon((str_ds[Parameters.id+1])));
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(left, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(img_L, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(img, GroupLayout.PREFERRED_SIZE, 106, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(img_R, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(right, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(img, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 96, Short.MAX_VALUE)
						.addComponent(left, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(img_L, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
						.addComponent(right, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(img_R, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		getContentPane().setLayout(groupLayout);
		
}

	
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		flag=false;
		
		row_no.setEnabled(false);
		col_no.setEnabled(false);
		mine_no.setEnabled(false);
		
		if(e.getItemSelectable()==Radio_B)
			id=1;
		if(e.getItemSelectable()==Radio_I)
			id=2;
		if(e.getItemSelectable()==Radio_A)
			id=3;
		if(e.getItemSelectable()==Radio_C)
		{
			row_no.setEnabled(true);
			col_no.setEnabled(true);
			mine_no.setEnabled(true);
			flag=true;
			id=0;
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent ev) {
		
	   if(ev.getComponent()==left && Parameters.id >0)
	   {
		   img.setIcon(new ImageIcon( (str_d[--Parameters.id])));
		   if(Parameters.id >0)
		     img_L.setIcon(new ImageIcon(  ( str_ds[Parameters.id-1])));
		   else
			   img_L.setIcon(null);
		   img_R.setIcon(new ImageIcon(  ( str_ds[Parameters.id+1])));
	   }
	   
	   if(ev.getComponent()==right && Parameters.id < str_p.length-1 )
	   {
		   img.setIcon(new ImageIcon( ( str_d[++Parameters.id])));
		   img_L.setIcon(new ImageIcon(  ( str_ds[Parameters.id-1])));
		   if(Parameters.id < str_p.length-1)
		     img_R.setIcon(new ImageIcon( ( str_ds[Parameters.id+1])));
		   else
			   img_R.setIcon(null);
	   }
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
		if(e.getComponent()==right)
			right.setBackground(new Color(216, 216, 216));
		if(e.getComponent()==left)
			left.setBackground(new Color(216, 216, 216));
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getComponent()==right)
			right.setBackground(new Color(240, 240, 240));
		if(e.getComponent()==left)
			left.setBackground(new Color(240, 240, 240));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getExtendedKeyCode()==KeyEvent.VK_ENTER)
		{
			ok.doClick();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
