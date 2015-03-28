import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


@SuppressWarnings("serial")
public class informationPanel extends JPanel {

	private JLabel yearLbl;
	private JLabel gpaLbl;
	private JTextArea fallTextArea;
	private JTextArea springTextArea;
	private JLabel lblFall;
	private JLabel lblSpring;
	private JLabel fallGPALbl;
	private JLabel springGPALbl;
	private JPanel informationPanel;
	private JPanel mainPanel;
	private Year year;
	
	/**
	 * Create the panel.
	 */
	public informationPanel(Year yr, JPanel panel) {
		year = yr;
		mainPanel = panel;
		
	}
	
	public JPanel getPanel(){
		informationPanel = new JPanel();
		informationPanel.setBackground(new Color(205, 92, 92));
		informationPanel.setLayout(null);
		
		yearLbl = new JLabel("Year",JLabel.LEFT);
		yearLbl.setForeground(Color.ORANGE);
		yearLbl.setFont(new Font("Arial", Font.BOLD, 16));
		yearLbl.setBounds(10, 11, 155, 29);
		informationPanel.add(yearLbl);
		
		gpaLbl = new JLabel("",JLabel.RIGHT);
		gpaLbl.setForeground(Color.ORANGE);
		gpaLbl.setFont(new Font("Arial", Font.BOLD, 14));
		gpaLbl.setBounds(221, 474, 235, 29);
		informationPanel.add(gpaLbl);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 92, 446, 159);
		informationPanel.add(scrollPane_2);
		
		fallTextArea = new JTextArea();
		fallTextArea.setEditable(false);
		fallTextArea.setTabSize(5);
		scrollPane_2.setViewportView(fallTextArea);
		fallTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
		fallTextArea.setBackground(new Color(255, 255, 224));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 302, 446, 159);
		informationPanel.add(scrollPane_3);
		
		springTextArea = new JTextArea();
		springTextArea.setEditable(false);
		springTextArea.setTabSize(5);
		scrollPane_3.setViewportView(springTextArea);
		springTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
		springTextArea.setBackground(new Color(255, 255, 224));
		
		lblFall = new JLabel("1st Semester",JLabel.CENTER);
		lblFall.setForeground(Color.ORANGE);
		lblFall.setFont(new Font("Arial", Font.BOLD, 14));
		lblFall.setBounds(159, 41, 135, 23);
		informationPanel.add(lblFall);
		
		lblSpring = new JLabel("2nd Semester",JLabel.CENTER);
		lblSpring.setForeground(Color.ORANGE);
		lblSpring.setFont(new Font("Arial", Font.BOLD, 14));
		lblSpring.setBounds(159, 255, 135, 23);
		informationPanel.add(lblSpring);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.setVisible(true);
				informationPanel.setVisible(false);
			}
		});
		btnGoBack.setFont(new Font("Arial", Font.PLAIN, 11));
		btnGoBack.setBackground(Color.LIGHT_GRAY);
		btnGoBack.setBounds(10, 480, 89, 23);
		informationPanel.add(btnGoBack);
		
		JLabel lblCourse = new JLabel("Course");
		lblCourse.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourse.setBounds(66, 74, 57, 14);
		informationPanel.add(lblCourse);
		
		JLabel lblTeacher = new JLabel("Instructor");
		lblTeacher.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTeacher.setBounds(183, 74, 73, 14);
		informationPanel.add(lblTeacher);
		
		JLabel lblGrade = new JLabel("Grade");
		lblGrade.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGrade.setBounds(341, 74, 57, 14);
		informationPanel.add(lblGrade);
		
		JLabel label = new JLabel("Course");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(66, 282, 57, 14);
		informationPanel.add(label);
		
		JLabel lblInstructor = new JLabel("Instructor");
		lblInstructor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInstructor.setBounds(183, 282, 73, 14);
		informationPanel.add(lblInstructor);
		
		JLabel label_2 = new JLabel("Grade");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_2.setBounds(341, 282, 57, 14);
		informationPanel.add(label_2);
		
		fallGPALbl = new JLabel("Semester GPA", SwingConstants.RIGHT);
		fallGPALbl.setForeground(Color.ORANGE);
		fallGPALbl.setFont(new Font("Arial", Font.PLAIN, 14));
		fallGPALbl.setBounds(332, 41, 124, 23);
		informationPanel.add(fallGPALbl);
		
		springGPALbl = new JLabel("Semester GPA", SwingConstants.RIGHT);
		springGPALbl.setForeground(Color.ORANGE);
		springGPALbl.setFont(new Font("Arial", Font.PLAIN, 14));
		springGPALbl.setBounds(332, 255, 124, 23);
		informationPanel.add(springGPALbl);
		
		return informationPanel;
	}
	
	public void informationActions(){
		
		yearLbl.setText("Year " + year.getYearTitle());
		gpaLbl.setText("Cumulative GPA: " + year.getYearGPA());
		
		//First semester
		if (year.getSemesterSize() > 0){	
			lblFall.setText(year.getSemester(0).getName());
			fallTextArea.setText(year.getSemester(0).semesterInfo());
			fallGPALbl.setText("GPA: " + year.getSemester(0).getSemesterGPA());
		}else{
			fallTextArea.setText("No Information Available");
		}
		
		//Second semester
		if (year.getSemesterSize() > 1){	
			lblSpring.setText(year.getSemester(1).getName());
			springTextArea.setText(year.getSemester(1).semesterInfo());
			springGPALbl.setText("GPA: " + year.getSemester(1).getSemesterGPA());
		}else{
			springTextArea.setText("No Information Available");
		}
		
		
	}
	
	public void setVisible(boolean visible){
		informationPanel.setVisible(visible);
	}

}
