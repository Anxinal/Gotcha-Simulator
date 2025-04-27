package user;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.ScrollPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.BorderLayout;
import javax.swing.JTextArea;

public class Help extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
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

	/**
	 * Create the frame.
	 * @throws FileNotFoundException 
	 */
	public Help() throws FileNotFoundException {
		setTitle("Gotcha Simulator Help");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		ScrollPane scrollPane = new ScrollPane();
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.add(textArea, BorderLayout.NORTH);
	    FileInputStream f=new FileInputStream("src/Pool_library/Help_content.txt");
	    Scanner s=new Scanner(f);
	    StringBuilder temp=new StringBuilder();
	    while(s.hasNextLine()) {
	    	temp.append(s.nextLine()+"\n");
	    }
	    textArea.setText("Please read the following instructions before creating a new pool for simulation:\r\n"
	    		+ "You shall follow the following format for crating a pool file if u wish a manual entry:\r\n"
	    		+ "Part 1: [Pool_name]##[number of type of rarity]##\r\n"
	    		+ "Part 2: ####[Rarity number]#[Probability]#([GuaranteePull Ceiling]#[the number of pull where probability starts climbing])#[Name of rarity]\r\n"
	    		+ "List of item(one for each line)\r\n"
	    		+ "###[Rarity number]#[Probability]#([GuaranteePull Ceiling]#[the number of pull where probability starts climbing])#[Name of rarity]\r\n"
	    		+ "List of item(one for each line)\r\n"
	    		+ "###... until all rarity info is entered\r\n"
	    		+ "\r\n"
	    		+ "\r\n"
	    		+ "\r\n"
	    		+ "For example:\r\n"
	    		+ "--------------------\r\n"
	    		+ "Star of wishes##3##\r\n"
	    		+ "####3 0.05 90 72   -- start climbing prob from 72 draws up until 100% in the 90th draw\r\n"
	    		+ "Helia#event  --There should be only one character with maximum rareness and event followed\r\n"
	    		+ "Tricia\r\n"
	    		+ "Selena\r\n"
	    		+ "###2 10.00 10 9       \r\n"
	    		+ "Lucy\r\n"
	    		+ "Allemond\r\n"
	    		+ "Very long sword\r\n"
	    		+ "###1 89.95\r\n"
	    		+ "long sword\r\n"
	    		+ "----------------------\r\n"
	    		+ "This is a valid pool info input\r\n"
	    		+ "\r\n"
	    		+ "1.The probability of each item in each pool is evenly spread out for the same rarity except for the highest where there is any #event item\r\n"
	    		+ "2. Guarantee pulls prioritise items with lower rarity levels\r\n"
	    		+ "3.#event mark suggests that this item will be selected by a 50% chance for every pull selecting items from this rarity level. If this item is not selected once it will definitely be selected the next time\r\n"
	    		+ "#event mark could only appear in the maximum rarity level");
	}

}
