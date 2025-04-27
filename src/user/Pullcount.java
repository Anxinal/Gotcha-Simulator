package user;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import base.*;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.ScrollPane;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pullcount extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	
	public Pullcount(Pool_info poo) {
		setTitle("Gotcha Simulator-set pull count");
		Queue<JSlider> response=new LinkedList<JSlider>();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 777, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "name_33380439084300");
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Set Poll count(after previous guarantee) ");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 28));
		panel_1.add(lblNewLabel);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Save and exit");
		btnNewButton.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] temp_count=new int[response.size()];
				int counter=0;
			while(response.peek()!=null) {
				JSlider temp=response.poll();
				temp_count[counter]=temp.getValue();
				counter++;
				
			}
			poo.setDrawcount(temp_count);
			JOptionPane.showMessageDialog(Pullcount.this,"Poll Count Reset successful");
			Pullcount.this.setVisible(false);
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_3.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		itembracket[] temp=poo.getAllbrackets();
		
		for(int i=0;i<temp.length;i++) {
			
			if(temp[i].getGuarantee()) {
			JLabel info=new JLabel("Rarity level "+Integer.toString(i+1)+":");
			info.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
			
			JSlider slide=new JSlider(0,temp[i].getceiling(),poo.getDrawcount()[i]);
			JLabel slide_info=new JLabel(Integer.toString(slide.getValue()));
			slide_info.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
			slide.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					slide_info.setText(Integer.toString(slide.getValue()));			
				}
				
			});
			JPanel pan=new JPanel();
			pan.setLayout(new FlowLayout(FlowLayout.LEADING));
			pan.add(info);
			pan.add(slide);
			pan.add(slide_info);
			response.add(slide);
			panel_2.add(pan);
			continue;
	
			}
		   response.add(new JSlider(0,0,0));
		}
	
		
		
	
	}

}
