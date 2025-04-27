package user;

import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.Color;
import base.*;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
public class FrontWindow extends JFrame {
    Pool_info current;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextPane InfoPane;
	private JTextPane ResultPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrontWindow frame = new FrontWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrontWindow() {
		setTitle("Pool_simulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Banner Settings");
		menuBar.add(mnNewMenu);
		
		JMenuItem sel = new JMenuItem("Select");
		mnNewMenu.add(sel);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Create");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_3 = new JMenu("Pool log search");
		mnNewMenu.add(mnNewMenu_3);
		
		JMenuItem filter = new JMenuItem("filter only...");
		mnNewMenu_3.add(filter);
		filter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Filter frame=new Filter(current,ResultPane.getText());
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				
			}
			
		});
		JMenu mnNewMenu_1 = new JMenu("Customise");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem Pull = new JMenuItem("Set Pull count");
		mnNewMenu_1.add(Pull);
		Pull.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Pullcount frame = new Pullcount(current);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}

		});
		
		JMenu mnNewMenu_2 = new JMenu("About");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem help = new JMenuItem("Help");
		mnNewMenu_2.add(help);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Help frame = new Help();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
			
		});
		sel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					JFrame j= new JFrame();
					FileDialog f=new FileDialog(j,"Load Pool",FileDialog.LOAD );
					f.setVisible(true);
					String path_name=f.getFile();
					System.out.println(f.getDirectory()+path_name);
					FileInputStream in=new FileInputStream(f.getDirectory()+path_name);
					Scanner s=new Scanner(in);
					StringBuilder temp= new StringBuilder();
					while(s.hasNextLine()) {
						temp.append(s.nextLine()+"\n");
					}
					Pool_info poo=Pool_info.convert_pool(temp.toString());
				    current=poo;
					InfoPane.setText(poo.toString());				
				} 
				catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					e1.printStackTrace();
				}
			}
			
		});

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnDraw = new JButton("1 Draw(D)");
		btnDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			item s= current.pool();
			ResultPane.setText(ResultPane.getText()+"\nPull "+Integer.toString(current.gettotalcount())+": "+s.getName()+"  rarity:"+current.getAllbrackets()[s.getRarity()-1].getName());
			
			}
		});
		panel.add(btnDraw);
		
		JButton btnNewButton_1 = new JButton("10 Draws(T)");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
for(int i=0;i<10; i++) {
	item s= current.pool();
	ResultPane.setText(ResultPane.getText()+"\nPull "+Integer.toString(current.gettotalcount())+": "+s.getName()+"  rarity:"+current.getAllbrackets()[s.getRarity()-1].getName());
}
			}
		});
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_5 = new JButton("Reset All(R)");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(current!=null) {
					ResultPane.setText("");
					int[] temp=new int[current.get_maxrarity()];
					current.setDrawcount(temp);
					current.settotalcount(0);
				}
			}
		});
		panel.add(btnNewButton_5);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		   ScrollPane sp_info = new ScrollPane();
		    panel_1.add(sp_info);
		    
		InfoPane = new JTextPane();
		InfoPane.setEditable(false);
		InfoPane.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
		InfoPane.setBackground(new Color(128, 255, 255));
		sp_info.add(InfoPane);
	    
	 
		ScrollPane sp_result = new ScrollPane();
		panel_1.add(sp_result);
		
	    ResultPane = new JTextPane();
	    ResultPane.setForeground(new Color(0, 0, 0));
	    ResultPane.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
	    ResultPane.setEditable(false);
		sp_result.add(ResultPane);
		
	
	}

}
