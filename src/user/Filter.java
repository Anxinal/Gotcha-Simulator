package user;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import base.Pool_info;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JCheckBox;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


import javax.swing.JTextArea;
import java.awt.Panel;
import javax.swing.JButton;

public class Filter extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private ArrayList<JCheckBox> checks=new ArrayList<JCheckBox>();
	public Filter(Pool_info poo,String log) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 613, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("Only Show Rarity:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
		panel.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new FlowLayout());
		for(int i=0;i<poo.get_maxrarity();i++) {
			JCheckBox check=new JCheckBox(poo.getAllbrackets()[i].getName());
			check.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
			panel_2.add(check);	
			checks.add(check);
		}
		ScrollPane scrollPane = new ScrollPane();
		contentPane.add(scrollPane);
		JTextArea result=new JTextArea();
		result.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		scrollPane.add(result);
		
		Panel panel_1 = new Panel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(2);
		contentPane.add(panel_1);
		
		JButton confirm = new JButton("Confirm");
		panel_1.add(confirm);
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				result.setText("");
			 String[] logs=log.split("\n");
			 ArrayList<String> creteria=new ArrayList<String>();
			 Iterator<JCheckBox> ch=checks.iterator();
			 while(ch.hasNext()) {
			JCheckBox temp=	ch.next();
				 if(temp.isSelected()) {creteria.add(temp.getText());}}
			 
			for(int i=0;i<logs.length;i++) {
				Iterator<String> ir=creteria.iterator();
				while(ir.hasNext()) {
					if(logs[i].contains(ir.next())) {
						result.setText(result.getText()+logs[i]+"\n");
					}
					}
					}
				}
			
			
		});
	}

}
