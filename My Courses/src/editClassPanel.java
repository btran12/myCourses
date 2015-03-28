

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

//CLASS DOES NOT WORK JUST YET
public class editClassPanel extends JPanel{
	
	MainGraphicalInterface mgi;
	
	private JPanel editClassPanel;
	
	JTextField profNameField, courseNameField, profSubjectField, profPhoneField , profEmailField, profHoursField,
	profOfficeField, bookTitleField, publishedDateField, editionField, authorField;
	
	JSpinner hwSpin, examSpin, inClassSpin, projectSpin, labSpin, gradeSpin;
	
	private JLabel valueWarningLbl;

	private String course;

	private JPanel mainPanel;

	private Semester thisSemester;

	/**
	 * Create the panel.
	 */
	public editClassPanel(JPanel panel, String selectedCourse, Semester selectedSemester) {
		mainPanel = panel;
		course = selectedCourse;
		thisSemester = selectedSemester;
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel getPanel(){
		editClassPanel = new JPanel();
		editClassPanel.setBackground(new Color(205, 92, 92));
		editClassPanel.setLayout(null);
		
		courseNameField = new JTextField();
		courseNameField.setForeground(Color.BLACK);
		courseNameField.setBackground(new Color(255, 255, 224));
		courseNameField.setBounds(193, 69, 126, 20);
		editClassPanel.add(courseNameField);
		courseNameField.setColumns(10);
		
		profNameField = new JTextField();
		profNameField.setBackground(new Color(255, 255, 224));
		profNameField.setBounds(78, 143, 126, 20);
		editClassPanel.add(profNameField);
		
		profSubjectField = new JTextField();
		profSubjectField.setBackground(new Color(255, 255, 224));
		profSubjectField.setColumns(10);
		profSubjectField.setBounds(303, 143, 128, 20);
		editClassPanel.add(profSubjectField);
		
		profPhoneField = new JTextField();
		profPhoneField.setBackground(new Color(255, 255, 224));
		profPhoneField.setColumns(10);
		profPhoneField.setBounds(303, 185, 128, 20);
		editClassPanel.add(profPhoneField);
		
		profEmailField = new JTextField();
		profEmailField.setBackground(new Color(255, 255, 224));
		profEmailField.setColumns(10);
		profEmailField.setBounds(78, 185, 126, 20);
		editClassPanel.add(profEmailField);
		
		profHoursField = new JTextField();
		profHoursField.setBackground(new Color(255, 255, 224));
		profHoursField.setColumns(10);
		profHoursField.setBounds(303, 228, 128, 20);
		editClassPanel.add(profHoursField);
		
		profOfficeField = new JTextField();
		profOfficeField.setBackground(new Color(255, 255, 224));
		profOfficeField.setColumns(10);
		profOfficeField.setBounds(78, 228, 126, 20);
		editClassPanel.add(profOfficeField);
		
		JLabel lblNewLabel_5 = new JLabel("Name:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5.setForeground(Color.ORANGE);
		lblNewLabel_5.setBounds(30, 144, 38, 16);
		editClassPanel.add(lblNewLabel_5);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setForeground(Color.ORANGE);
		lblEmail.setBounds(30, 186, 36, 16);
		editClassPanel.add(lblEmail);
		
		JLabel lblOffice = new JLabel("Office");
		lblOffice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOffice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOffice.setForeground(Color.ORANGE);
		lblOffice.setBounds(35, 229, 33, 16);
		editClassPanel.add(lblOffice);
		
		JLabel lblOfficeHours = new JLabel("Office Hours:");
		lblOfficeHours.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOfficeHours.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOfficeHours.setForeground(Color.ORANGE);
		lblOfficeHours.setBounds(217, 229, 75, 16);
		editClassPanel.add(lblOfficeHours);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPhone.setForeground(Color.ORANGE);
		lblPhone.setBounds(252, 186, 40, 16);
		editClassPanel.add(lblPhone);
		
		JLabel lblSubject = new JLabel("Subject:");
		lblSubject.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSubject.setForeground(Color.ORANGE);
		lblSubject.setBounds(244, 146, 48, 16);
		editClassPanel.add(lblSubject);
		
		JLabel lblCourse_1 = new JLabel("Course:");
		lblCourse_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCourse_1.setForeground(Color.ORANGE);
		lblCourse_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCourse_1.setBounds(138, 70, 45, 16);
		editClassPanel.add(lblCourse_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.ORANGE);
		separator_2.setBackground(Color.GRAY);
		separator_2.setBounds(463, 265, -464, 122);
		editClassPanel.add(separator_2);
		
		JButton btnGoBack_1 = new JButton("Go Back");
		btnGoBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.setVisible(true);
				editClassPanel.setVisible(false);
			}
		});
		btnGoBack_1.setBounds(30, 476, 89, 23);
		editClassPanel.add(btnGoBack_1);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	//Must check for invalid characters.
				updateClassActions();
			}
		});
		btnUpdate.setBounds(342, 476, 89, 23);
		editClassPanel.add(btnUpdate);
		
		JLabel lblEditCourse = new JLabel("Edit Course");
		lblEditCourse.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditCourse.setForeground(Color.ORANGE);
		lblEditCourse.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEditCourse.setBounds(103, 11, 259, 47);
		editClassPanel.add(lblEditCourse);
		
		JLabel lblProfessorInformation = new JLabel("Professor Information");
		lblProfessorInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfessorInformation.setForeground(Color.ORANGE);
		lblProfessorInformation.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProfessorInformation.setBounds(103, 100, 259, 32);
		editClassPanel.add(lblProfessorInformation);
		
		JLabel lblTextbookInformation = new JLabel("Textbook Information");
		lblTextbookInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextbookInformation.setForeground(Color.ORANGE);
		lblTextbookInformation.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTextbookInformation.setBounds(103, 260, 259, 32);
		editClassPanel.add(lblTextbookInformation);
		
		bookTitleField = new JTextField();
		bookTitleField.setColumns(10);
		bookTitleField.setBackground(new Color(255, 255, 224));
		bookTitleField.setBounds(99, 303, 126, 20);
		editClassPanel.add(bookTitleField);
		
		publishedDateField = new JTextField();
		publishedDateField.setColumns(10);
		publishedDateField.setBackground(new Color(255, 255, 224));
		publishedDateField.setBounds(127, 348, 98, 20);
		editClassPanel.add(publishedDateField);
		
		editionField = new JTextField();
		editionField.setColumns(10);
		editionField.setBackground(new Color(255, 255, 224));
		editionField.setBounds(324, 348, 98, 20);
		editClassPanel.add(editionField);
		
		authorField = new JTextField();
		authorField.setColumns(10);
		authorField.setBackground(new Color(255, 255, 224));
		authorField.setBounds(324, 306, 98, 20);
		editClassPanel.add(authorField);
		
		JLabel lblBookTitle = new JLabel("Book Title:");
		lblBookTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBookTitle.setForeground(Color.ORANGE);
		lblBookTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBookTitle.setBounds(30, 307, 61, 16);
		editClassPanel.add(lblBookTitle);
		
		JLabel lblDatePublished = new JLabel("Date Published:");
		lblDatePublished.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDatePublished.setForeground(Color.ORANGE);
		lblDatePublished.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDatePublished.setBounds(30, 349, 89, 16);
		editClassPanel.add(lblDatePublished);
		
		JLabel lblEdition = new JLabel("Edition:");
		lblEdition.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEdition.setForeground(Color.ORANGE);
		lblEdition.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEdition.setBounds(270, 349, 43, 16);
		editClassPanel.add(lblEdition);
		
		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuthor.setForeground(Color.ORANGE);
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAuthor.setBounds(270, 307, 43, 16);
		editClassPanel.add(lblAuthor);
		
		JLabel lblGradingCriteria = new JLabel("Grading Criteria");
		lblGradingCriteria.setHorizontalAlignment(SwingConstants.CENTER);
		lblGradingCriteria.setForeground(Color.ORANGE);
		lblGradingCriteria.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGradingCriteria.setBounds(103, 379, 259, 32);
		editClassPanel.add(lblGradingCriteria);
		
		hwSpin = new JSpinner();
		hwSpin.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 5.0));
		hwSpin.setBounds(35, 431, 38, 20);
		editClassPanel.add(hwSpin);

		examSpin = new JSpinner();
		examSpin.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 5.0));
		examSpin.setBounds(127, 431, 38, 20);
		editClassPanel.add(examSpin);

		projectSpin = new JSpinner();
		projectSpin.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 5.0));
		projectSpin.setBounds(217, 431, 38, 20);
		editClassPanel.add(projectSpin);

		labSpin = new JSpinner();
		labSpin.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 5.0));
		labSpin.setBounds(305, 431, 38, 20);
		editClassPanel.add(labSpin);

		inClassSpin = new JSpinner();
		inClassSpin.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 5.0));
		inClassSpin.setBounds(393, 431, 38, 20);
		editClassPanel.add(inClassSpin);
		
		JLabel label_1 = new JLabel("%");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setForeground(Color.ORANGE);
		label_1.setBounds(78, 434, 24, 14);
		editClassPanel.add(label_1);
		
		JLabel label_3 = new JLabel("%");
		label_3.setForeground(Color.ORANGE);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(169, 434, 24, 14);
		editClassPanel.add(label_3);
		
		JLabel label_4 = new JLabel("%");
		label_4.setForeground(Color.ORANGE);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBounds(260, 434, 24, 14);
		editClassPanel.add(label_4);
		
		JLabel label_5 = new JLabel("%");
		label_5.setForeground(Color.ORANGE);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_5.setBounds(347, 434, 24, 14);
		editClassPanel.add(label_5);
		
		JLabel label_6 = new JLabel("%");
		label_6.setForeground(Color.ORANGE);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_6.setBounds(437, 434, 24, 14);
		editClassPanel.add(label_6);
		
		JLabel lblHomework = new JLabel("Homework");
		lblHomework.setHorizontalAlignment(SwingConstants.CENTER);
		lblHomework.setForeground(Color.ORANGE);
		lblHomework.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblHomework.setBounds(30, 407, 61, 16);
		editClassPanel.add(lblHomework);
		
		JLabel lblExam = new JLabel("Exam");
		lblExam.setHorizontalAlignment(SwingConstants.CENTER);
		lblExam.setForeground(Color.ORANGE);
		lblExam.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblExam.setBounds(113, 408, 61, 16);
		editClassPanel.add(lblExam);
		
		JLabel lblProject = new JLabel("Project");
		lblProject.setHorizontalAlignment(SwingConstants.CENTER);
		lblProject.setForeground(Color.ORANGE);
		lblProject.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblProject.setBounds(205, 408, 61, 16);
		editClassPanel.add(lblProject);
		
		JLabel lblLab = new JLabel("Lab");
		lblLab.setHorizontalAlignment(SwingConstants.CENTER);
		lblLab.setForeground(Color.ORANGE);
		lblLab.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLab.setBounds(289, 407, 61, 16);
		editClassPanel.add(lblLab);
		
		JLabel lblInClass = new JLabel("In Class");
		lblInClass.setHorizontalAlignment(SwingConstants.CENTER);
		lblInClass.setForeground(Color.ORANGE);
		lblInClass.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblInClass.setBounds(383, 407, 61, 16);
		editClassPanel.add(lblInClass);
		
		valueWarningLbl = new JLabel("Values Must Adds Up to 100%");
		valueWarningLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		valueWarningLbl.setForeground(Color.BLUE);
		valueWarningLbl.setBackground(Color.BLUE);
		valueWarningLbl.setHorizontalAlignment(SwingConstants.CENTER);
		valueWarningLbl.setBounds(138, 480, 189, 15);
		valueWarningLbl.setVisible(false);
		editClassPanel.add(valueWarningLbl);
		
		return editClassPanel;
	}

	public void setFields(){
		
		//Keep track of the selected course's index
		int courseIndex = thisSemester.findCourseIndex(course);
		
		//Set the fields with the available information
		courseNameField.setText(thisSemester.getCourse(courseIndex).getCourseName());
		profNameField.setText(thisSemester.getCourse((courseIndex)).getProfName());
		profSubjectField.setText(thisSemester.getCourse((courseIndex)).getSubject());
		profEmailField.setText(thisSemester.getCourse((courseIndex)).getEmail());
		profPhoneField.setText(thisSemester.getCourse((courseIndex)).getPhone());
		profOfficeField.setText(thisSemester.getCourse((courseIndex)).getOffice());
		profHoursField.setText(thisSemester.getCourse((courseIndex)).getOfficeHours());
		
		bookTitleField.setText(thisSemester.getCourse((courseIndex)).getTitle());
		publishedDateField.setText(thisSemester.getCourse((courseIndex)).getDatePublished());
		authorField.setText(thisSemester.getCourse((courseIndex)).getAuthor());
		editionField.setText(thisSemester.getCourse((courseIndex)).getEdition());
		
		hwSpin.setValue(thisSemester.getCourse((courseIndex)).getHomeworkPercent() *100);
		examSpin.setValue(thisSemester.getCourse((courseIndex)).getExamPercent() *100);
		projectSpin.setValue(thisSemester.getCourse((courseIndex)).getProjectPercent()*100);
		labSpin.setValue(thisSemester.getCourse((courseIndex)).getLabPercent()*100);
		inClassSpin.setValue(thisSemester.getCourse((courseIndex)).getInClassPercent()*100);
	}
	
	/**
	 * Check if every edits are done correctly
	 * Check that the percentages adds up to 100
	 * If it does, update the class with new information
	 */
	public void updateClassActions(){
		
		//Calculate the values
		double exam = (Double) examSpin.getValue()/100.0;
		double hw = (Double) hwSpin.getValue()/100.0;
		double inClass = (Double) inClassSpin.getValue()/100.0;
		double lab = (Double) labSpin.getValue()/100.0;
		double project = (Double) projectSpin.getValue()/100.0;
		
		double total =  exam + hw + inClass + lab + project;

		//Keep track of the selected course's index
		int courseIndex = thisSemester.findCourseIndex(course);
		
		//Make sure that the percentages adds up to 100
		//Or 1 in this case since divided by 100 up top
		if (total == 1.0){
			
			//Update class with new information
			thisSemester.getCourse(courseIndex).setCourseName(courseNameField.getText());
			
			thisSemester.getCourse(courseIndex).setProfInfo(profNameField.getText(), profSubjectField.getText(),
						profEmailField.getText(), profPhoneField.getText(), profOfficeField.getText(), profHoursField.getText());
			
			thisSemester.getCourse(courseIndex).setTextInfo(bookTitleField.getText(), authorField.getText(), 
						publishedDateField.getText(), editionField.getText());
			
			thisSemester.getCourse(courseIndex).setPercentage(exam, hw, inClass, lab, project);
			
			//Re-save
			//save();
			
			//updateDropBox(course, courseNameField.getText());
			
			//Go Back to Previous screen
			mainPanel.setVisible(true);
			editClassPanel.setVisible(false);
			
			//Update the display
			//display();
			
		}else{
			
			//Warns that the values does not adds up to 100%
			valueWarningLbl.setVisible(true);
		}
	}

	public void setVisible(boolean b) {
		editClassPanel.setVisible(b);
		
	}

}
