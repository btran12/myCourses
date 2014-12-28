
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;




/**
 * 
 * @author Bao Tran
 *This is the user graphical interface with functions and references to the other classes
 *This class allow the user to add information and see information
 */
public class MainGraphicalInterface {

	private JFrame mainFrame;
	private Account account;
	private UserProfile profile;
	private Year year;
	private Semester thisSemester;
	private JComboBox<String> dropBox;
	private JTextArea mainDisplay,assignmentsDisplay,profInfoDisplay,bookInfoDisplay,fallTextArea,springTextArea;
	@SuppressWarnings("rawtypes")
	private ArrayList<JComboBox> arrCombo;
	private JLabel semesterLabel, warningLbl = new JLabel(), yearLbl, gpaLbl, lblFall, lblSpring, fallGPALbl, springGPALbl, valueWarningLbl, welcomeLbl;
	private JPanel informationPanel, mainPanel, securityPanel, editClassPanel, editAssignmentPanel;
	private JTextField userNameField;
	private JPasswordField passwordField;
	JProgressBar progressBar;
	int value = 0;
	private JTextField profNameField, courseNameField, profSubjectField,profPhoneField ,profEmailField ,profHoursField ,profOfficeField ,
	bookTitleField,publishedDateField ,editionField ,authorField ;
	private JSpinner hwSpin, examSpin, inClassSpin, projectSpin, labSpin;
	private JTextField assField, assOnField,assDueField;
	private JSpinner gradeSpin;
	private JRadioButton hwRadio, examRadio, inClassRadio, labRadio, projectRadio;
	private int assignmentIndex;
	private int semesterCount = 0;	//To make sure that there will be a maximum of only 2 semesters
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	/**
	 * The program starts from here
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGraphicalInterface window = new MainGraphicalInterface();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor of this class
	 */
	public MainGraphicalInterface() {
		
		initialize();
	}

	/**
	 * All the variables, functions and actions are created here
	 */
	private void initialize() {
		account = new Account();//First run only will this be needed
		loadAccounts(); //Load up all the available accounts
		//GUI COMPONENTS
		mainFrame = new JFrame();
		mainFrame.setResizable(false);
		mainFrame.setTitle("Courses Tracker");
		mainFrame.setBounds(100, 100, 472, 557);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new CardLayout(0, 0));
		
//		|||||||||||||||||||||||||||||||||||||||||||||||||SECURITY PANEL|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
		securityPanel = new JPanel();
		securityPanel.setBackground(new Color(205, 92, 92));
		mainFrame.getContentPane().add(securityPanel, "name_26606478684680");
		securityPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setForeground(Color.ORANGE);
		lblNewLabel_1.setBounds(149, 98, 64, 14);
		securityPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setForeground(Color.ORANGE);
		lblNewLabel_2.setBounds(149, 139, 64, 14);
		securityPanel.add(lblNewLabel_2);
		
		userNameField = new JTextField();
		userNameField.setBackground(new Color(255, 255, 224));
		userNameField.setBounds(223, 95, 112, 20);
		securityPanel.add(userNameField);
		userNameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(255, 255, 224));
		passwordField.setBounds(223, 136, 112, 20);
		securityPanel.add(passwordField);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBackground(new Color(205, 92, 92));
		lblNewLabel_3.setIcon(new ImageIcon(MainGraphicalInterface.class.getResource("/org/eclipse/wb/swing/Logo.jpg")));
		lblNewLabel_3.setBounds(95, 229, 300, 300);
		securityPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Welcome to myCourses");
		lblNewLabel_4.setForeground(Color.ORANGE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(88, 22, 307, 34);
		securityPanel.add(lblNewLabel_4);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBounds(0, 0, 466, 21);
		securityPanel.add(menuBar_1);
		
		JMenu mnStart = new JMenu("Start");
		menuBar_1.add(mnStart);
		
		JMenuItem mntmEnd = new JMenuItem("End Session");
		mntmEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenuItem addProfileMenu = new JMenuItem("Create a Profile");
		addProfileMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addProfileActions();
			}
		});
		mnStart.add(addProfileMenu);
		mnStart.add(mntmEnd);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(95, 204, 300, 14);
		progressBar.setForeground(Color.ORANGE);
		securityPanel.add(progressBar);
		
//|||||||||||||||||||||||||||||||||||||||||||||||||MAIN PANEL|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(205, 92, 92));
		mainPanel.setForeground(Color.ORANGE);
		mainFrame.getContentPane().add(mainPanel, "name_27846828030000");
		mainPanel.setLayout(null);
		
		welcomeLbl = new JLabel("Welcome:");
		welcomeLbl.setForeground(new Color(0, 128, 128));
		welcomeLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		welcomeLbl.setFont(new Font("Arial", Font.PLAIN, 12));
		welcomeLbl.setBounds(297, 0, 159, 21);
		mainPanel.add(welcomeLbl);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setForeground(Color.BLACK);
		menuBar.setBackground(Color.ORANGE);
		menuBar.setBounds(0, 0, 468, 21);
		mainPanel.add(menuBar);
		
		JMenu startMenu = new JMenu("Start");
		startMenu.setFont(new Font("Segoe UI", Font.BOLD, 12));
		startMenu.setForeground(Color.BLACK);
		menuBar.add(startMenu);
		
		semesterLabel = new JLabel("SEASON YEAR");
		semesterLabel.setFont(new Font("Calibri", Font.BOLD, 21));
		semesterLabel.setBounds(180, 32, 131, 20);
		mainPanel.add(semesterLabel);
		semesterLabel.setForeground(Color.ORANGE);
		semesterLabel.setBackground(Color.YELLOW);
		
		JMenu addMenu = new JMenu("+");
		addMenu.setIcon(null);
		addMenu.setFont(new Font("SansSerif", Font.BOLD, 20));
		addMenu.setForeground(Color.BLACK);
		menuBar.add(addMenu);
		
		JMenu editMenu = new JMenu("Edit");
		editMenu.setFont(new Font("Segoe UI", Font.BOLD, 12));
		editMenu.setForeground(Color.BLACK);
		menuBar.add(editMenu);
		
		JMenu removeMenu = new JMenu("Remove");
		editMenu.add(removeMenu);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(140, 60, 316, 177);
		mainPanel.add(scrollPane_1);
		
		mainDisplay = new JTextArea();
		scrollPane_1.setViewportView(mainDisplay);
		mainDisplay.setTabSize(15);
		mainDisplay.setWrapStyleWord(true);
		mainDisplay.setFont(new Font("Arial", Font.PLAIN, 14));
		mainDisplay.setLineWrap(true);
		mainDisplay.setEditable(false);
		mainDisplay.setBackground(new Color(255, 255, 224));
		
		JLabel lblAssignment = new JLabel("Assignment:");
		lblAssignment.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAssignment.setBounds(18, 260, 73, 14);
		mainPanel.add(lblAssignment);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblType.setBounds(116, 260, 32, 14);
		mainPanel.add(lblType);
		
		JLabel lblAssignedOn = new JLabel("Assigned On:");
		lblAssignedOn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAssignedOn.setBounds(187, 260, 85, 14);
		mainPanel.add(lblAssignedOn);
		
		JLabel lblDueOn = new JLabel("Due On:");
		lblDueOn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDueOn.setBounds(282, 260, 47, 14);
		mainPanel.add(lblDueOn);
		
		JLabel lblGradeReceived = new JLabel("Grade Received: ");
		lblGradeReceived.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblGradeReceived.setBounds(343, 260, 100, 14);
		mainPanel.add(lblGradeReceived);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 278, 446, 107);
		mainPanel.add(scrollPane);
		
		assignmentsDisplay = new JTextArea();
		scrollPane.setViewportView(assignmentsDisplay);
		assignmentsDisplay.setWrapStyleWord(true);
		assignmentsDisplay.setBackground(new Color(255, 255, 224));
		assignmentsDisplay.setFont(new Font("Arial", Font.PLAIN, 14));
		assignmentsDisplay.setLineWrap(true);
		assignmentsDisplay.setEditable(false);
		
		JLabel lblNewLabel = new JLabel("Course Info:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setForeground(new Color(255, 215, 0));
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setBounds(188, 242, 100, 14);
		mainPanel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 396, 466, 2);
		mainPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(232, 396, 2, 131);
		mainPanel.add(separator_1);
		
		profInfoDisplay = new JTextArea();
		profInfoDisplay.setEditable(false);
		profInfoDisplay.setBackground(new Color(255, 255, 224));
		profInfoDisplay.setFont(new Font("Miriam", Font.PLAIN, 15));
		profInfoDisplay.setBounds(10, 422, 212, 94);
		mainPanel.add(profInfoDisplay);
		
	    bookInfoDisplay = new JTextArea();
	    bookInfoDisplay.setEditable(false);
	    bookInfoDisplay.setBackground(new Color(255, 255, 224));
	    bookInfoDisplay.setFont(new Font("Miriam", Font.PLAIN, 15));
		bookInfoDisplay.setBounds(244, 422, 212, 94);
		mainPanel.add(bookInfoDisplay);
		
		dropBox = new JComboBox<String>();		
		dropBox.addItem("N/A");
		dropBox.setForeground(new Color(0, 0, 0));
		dropBox.setBackground(Color.LIGHT_GRAY);
		dropBox.setBounds(10, 62, 120, 20);
		mainPanel.add(dropBox);
		
		JLabel lblProfessorsInformation = new JLabel("Professor Information");
		lblProfessorsInformation.setForeground(new Color(255, 215, 0));
		lblProfessorsInformation.setLabelFor(profInfoDisplay);
		lblProfessorsInformation.setFont(new Font("Arial", Font.BOLD, 13));
		lblProfessorsInformation.setBounds(40, 404, 155, 14);
		mainPanel.add(lblProfessorsInformation);
		
		JLabel lblTextbookRequired = new JLabel("Textbook Required");
		lblTextbookRequired.setForeground(new Color(255, 215, 0));
		lblTextbookRequired.setFont(new Font("Arial", Font.BOLD, 13));
		lblTextbookRequired.setBounds(284, 404, 142, 14);
		mainPanel.add(lblTextbookRequired);
		
		JLabel lblPickClass = new JLabel("Select a Course:");
		lblPickClass.setForeground(Color.ORANGE);
		lblPickClass.setFont(new Font("Arial", Font.BOLD, 13));
		lblPickClass.setBackground(Color.BLACK);
		lblPickClass.setBounds(18, 43, 115, 14);
		mainPanel.add(lblPickClass);
		
		//Change the look of JOptionPane (stackoverflow)
		UIManager.put("OptionPane.background",new ColorUIResource(205, 92, 92));
		UIManager.put("Panel.background",new ColorUIResource(205, 92, 92));
		UIManager.put("OptionPane.messageForeground", Color.ORANGE);
		UIManager.put("Button.background", Color.LIGHT_GRAY);
		
//		+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++	START OF ACTIONS
// 		>------------------LOGIN BUTTON--------------------<	
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginActions();
			}
		});
		loginButton.setBounds(262, 167, 73, 23);
		securityPanel.add(loginButton);
// 		>------------------ADD PROFILE BUTTON--------------------<
		JButton addProfileButton = new JButton("Add Profile");
		addProfileButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				addProfileActions();
			}
		});
		addProfileButton.setBounds(358, 168, 98, 23);
		securityPanel.add(addProfileButton);
// 		>------------------NEW YEAR MENU--------------------<		
		JMenuItem newYearMenu = new JMenuItem("New School Year");
		newYearMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addYearActions();
			}
		});
		startMenu.add(newYearMenu);
		
// 		>------------------ADD SEMESTER MENU--------------------<
		JMenuItem addSemesterMenu = new JMenuItem("+Semester");
		addMenu.add(addSemesterMenu);
		addSemesterMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addSemesterActions();
			}
		});
	    
//		 >----------------ADD CLASS MENU-------------------<
		JMenuItem classMenu = new JMenuItem("+Class");
		addMenu.add(classMenu);
		classMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addClassActions();
			}
		});
//		 >----------------ADD ASSIGNMENT MENU-------------------<					
		JMenuItem assignmentMenu = new JMenuItem("+Assignment");
		addMenu.add(assignmentMenu);
		assignmentMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addAssignmentActions();
			}
		});
		
		// >----------------ADD ASSIGNMENT BUTTON-------------------<			
		JButton btnAddAssignment = new JButton("+Assignment");
		btnAddAssignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addAssignmentActions();
			}
		});
		btnAddAssignment.setFont(new Font("Arial", Font.PLAIN, 11));
		btnAddAssignment.setBackground(Color.LIGHT_GRAY);
		btnAddAssignment.setBounds(22, 197, 100, 23);
		mainPanel.add(btnAddAssignment);
		
// 		>------------------EDIT CLASS MENU--------------------<
		JMenuItem editClassMenu = new JMenuItem("Edit Class");
		editClassMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editClassActions();
			}
		});
		editMenu.add(editClassMenu);
// 		>------------------EDIT ASSIGNMENT MENU--------------------<
		JMenuItem editAssignmentMenu = new JMenuItem("Edit Assignment");
		editAssignmentMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editAssignmentActions();
			}
		});
		editMenu.add(editAssignmentMenu);
		
// 		>------------------REMOVE SEMESTER MENU--------------------<
		JMenuItem removeSemesterMenu = new JMenuItem("-Semester");
		removeSemesterMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeSemesterActions();
			}
		});
		removeMenu.add(removeSemesterMenu);
		
// 		>------------------REMOVE COURSE MENU--------------------<	
		JMenuItem removeCourseMenu = new JMenuItem("-Course");
		removeCourseMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeCourseActions();
			}
		});
		removeMenu.add(removeCourseMenu);
// 		>------------------REMOVE ASSIGNMENT MENU--------------------<			
		JMenuItem removeAssignmentMenu = new JMenuItem("-Assignment");
		removeAssignmentMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeAssignmentActions();
			}
		});
		removeMenu.add(removeAssignmentMenu);
		
// 		>------------------------COMBO BOX----------------------<		
		dropBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (year != null){
					if (thisSemester != null){
						for (int i = 0; i < thisSemester.coursesSize(); i++){	//When ever a selection occurs details display the selected class
							if ((dropBox.getSelectedItem() == thisSemester.getCourse(i).getCourseName())){	//If any of the courses are selected display that course info
								detailsDisplay(i);
								break;
							}else if (dropBox.getSelectedItem() == "N/A"){	//If "N/A" is selected, clear everything
								assignmentsDisplay.setText("");
								profInfoDisplay.setText("");
								bookInfoDisplay.setText("");
							}
						}
					}
				}
				
			}
		});
		
//		>------------------LOAD YEAR MENU--------------------<	
		JMenuItem loadYearMenu = new JMenuItem("Load Year");
		startMenu.add(loadYearMenu);
		loadYearMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				loadYearActions();
			}
		});
//		>------------------LOAD SEMESTER MENU--------------------<				
	    JMenuItem loadSemesterMenu = new JMenuItem("Load Semester");
		startMenu.add(loadSemesterMenu);
		loadSemesterMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				loadSemesterActions();
			}
		});
// 		>------------------SAVE MENU--------------------<
		JMenuItem saveMenu = new JMenuItem("Save");
		saveMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});
		startMenu.add(saveMenu);
// 		>------------------------INFORMATION MENU-----------------------<
		JMenuItem informationMenu = new JMenuItem("Information");
		startMenu.add(informationMenu);
		informationMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				informationActions();
			}
		});
		
// 		>------------------------LOG OUT MENU-----------------------<
		JMenuItem mntmLogOut = new JMenuItem("Log Out");
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				securityPanel.setVisible(true);
				mainPanel.setVisible(false);
				userNameField.setText("");
				passwordField.setText("");
				progressBar.setValue(0);
			}
		});
		startMenu.add(mntmLogOut);
		
// 		>------------------------EXIT MENU-----------------------<
		JMenuItem quitMenu = new JMenuItem("Exit");
		quitMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		startMenu.add(quitMenu);
		
		JMenu accountMenu = new JMenu("Account");
		accountMenu.setForeground(Color.BLACK);
		accountMenu.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(accountMenu);
		
// 		>------------------------CHANGE PASSWORD MENU-----------------------<
		JMenuItem editPassMenu = new JMenuItem("Change Password");
		editPassMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newPass = "";
				while (true){
					String pass = JOptionPane.showInputDialog("Enter Current Password");	
					if (pass != null){	//If OK
						if (pass.equals(profile.getPassword())){	//Entered pass matches
							while (true){
								newPass = JOptionPane.showInputDialog("Enter New Password");
								if (newPass == null){
									break;
								}
								if (newPass.length() > 5){	//Implement a complex password restrictions Here<<<
									profile.setPassword(newPass);
									save();
									break;
								}else{
									JOptionPane.showMessageDialog(null, "Password Must Be Longer than 5 Characters");
								}
							}
							break;
						}else{
							JOptionPane.showMessageDialog(null, "The Password You Have Entered is Incorrect", "Wrong Password Message", JOptionPane.INFORMATION_MESSAGE);
						}
					}else{	//If Cancelled
						break;
					}
				}
			}
		});
		accountMenu.add(editPassMenu);
//		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++	END OF ACTIONS
		
//		|||||||||||||||||||||||||||||||||||||||||||||||||EDIT CLASS PANEL|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
		editClassPanel = new JPanel();
		editClassPanel.setBackground(new Color(205, 92, 92));
		mainFrame.getContentPane().add(editClassPanel, "name_6553863413760");
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
		profNameField.setColumns(10);
		
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
		
//|||||||||||||||||||||||||||||||||||||||||||||||||EDIT ASSIGNMENT PANEL|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||		
		editAssignmentPanel = new JPanel();
		editAssignmentPanel.setBackground(new Color(205, 92, 92));
		mainFrame.getContentPane().add(editAssignmentPanel, "name_35311240234040");
		editAssignmentPanel.setLayout(null);
		
		JLabel lblEditAsssignment = new JLabel("Edit Asssignment");
		lblEditAsssignment.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditAsssignment.setForeground(Color.ORANGE);
		lblEditAsssignment.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEditAsssignment.setBounds(101, 11, 259, 47);
		editAssignmentPanel.add(lblEditAsssignment);
		
		assField = new JTextField();
		assField.setForeground(Color.BLACK);
		assField.setColumns(10);
		assField.setBackground(new Color(255, 255, 224));
		assField.setBounds(198, 69, 126, 20);
		editAssignmentPanel.add(assField);
		
		assOnField = new JTextField();
		assOnField.setColumns(10);
		assOnField.setBackground(new Color(255, 255, 224));
		assOnField.setBounds(198, 143, 126, 20);
		editAssignmentPanel.add(assOnField);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate.setForeground(Color.ORANGE);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDate.setBounds(150, 144, 38, 16);
		editAssignmentPanel.add(lblDate);
		
		JLabel lblGrade_1 = new JLabel("Grade:");
		lblGrade_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGrade_1.setForeground(Color.ORANGE);
		lblGrade_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGrade_1.setBounds(148, 283, 40, 16);
		editAssignmentPanel.add(lblGrade_1);
		
		JLabel lblType_1 = new JLabel("Type:");
		lblType_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblType_1.setForeground(Color.ORANGE);
		lblType_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblType_1.setBounds(198, 216, 48, 16);
		editAssignmentPanel.add(lblType_1);
		
		JLabel lblAssignmentl = new JLabel("Assignment:");
		lblAssignmentl.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAssignmentl.setForeground(Color.ORANGE);
		lblAssignmentl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAssignmentl.setBounds(117, 70, 71, 16);
		editAssignmentPanel.add(lblAssignmentl);
		
		JLabel lblAssignmentInformation = new JLabel("Assignment Information");
		lblAssignmentInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblAssignmentInformation.setForeground(Color.ORANGE);
		lblAssignmentInformation.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAssignmentInformation.setBounds(108, 100, 259, 32);
		editAssignmentPanel.add(lblAssignmentInformation);
		
		JLabel lblDueDate = new JLabel("Due Date:");
		lblDueDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDueDate.setForeground(Color.ORANGE);
		lblDueDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDueDate.setBounds(131, 186, 57, 16);
		editAssignmentPanel.add(lblDueDate);
		
		gradeSpin = new JSpinner();
		gradeSpin.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		gradeSpin.setBounds(198, 282, 48, 20);
		editAssignmentPanel.add(gradeSpin);
		
		assDueField = new JTextField();
		assDueField.setColumns(10);
		assDueField.setBackground(new Color(255, 255, 224));
		assDueField.setBounds(198, 185, 126, 20);
		editAssignmentPanel.add(assDueField);
		
		hwRadio = new JRadioButton("Homework");
		buttonGroup.add(hwRadio);
		hwRadio.setBounds(52, 239, 89, 23);
		editAssignmentPanel.add(hwRadio);

		examRadio = new JRadioButton("Exam");
		buttonGroup.add(examRadio);
		examRadio.setBounds(144, 239, 57, 23);
		editAssignmentPanel.add(examRadio);

		inClassRadio = new JRadioButton("In Class");
		buttonGroup.add(inClassRadio);
		inClassRadio.setBounds(203, 239, 71, 23);
		editAssignmentPanel.add(inClassRadio);

		labRadio = new JRadioButton("Lab");
		buttonGroup.add(labRadio);
		labRadio.setBounds(276, 239, 48, 23);
		editAssignmentPanel.add(labRadio);

		projectRadio = new JRadioButton("Project");
		buttonGroup.add(projectRadio);
		projectRadio.setBounds(326, 239, 71, 23);
		editAssignmentPanel.add(projectRadio);
		
		JButton button = new JButton("Go Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.setVisible(true);
				editAssignmentPanel.setVisible(false);
			}
		});
		button.setBounds(34, 326, 89, 23);
		editAssignmentPanel.add(button);
		
		JButton button_1 = new JButton("Update");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateAssignmentActions();
			}
		});
		button_1.setBounds(346, 326, 89, 23);
		editAssignmentPanel.add(button_1);

//		|||||||||||||||||||||||||||||||||||||||||||||||||INFORMATION PANEL|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
		informationPanel = new JPanel();
		informationPanel.setBackground(new Color(205, 92, 92));
		mainFrame.getContentPane().add(informationPanel, "name_27893318697400");
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
		
	}//Initialize
	
	public void loginActions(){
		boolean correct = false;
		if (account.getSize() > 0){
			for (int i = 0; i < account.getSize(); i++){	//Loop to check all the logins info
				if ((userNameField.getText().equals(account.getProfile(i).getUsername())) && (String.valueOf(passwordField.getPassword()).equals(account.getProfile(i).getPassword()))){
					
					mainPanel.setVisible(true);
					securityPanel.setVisible(false);
					profile = account.getProfile(i);	//Initialize that profile
					correct = true;	//To avoid the conditional below, since the info is correct. 
					
					//Flush old informations
					dropBox.removeAllItems();
					dropBox.addItem("N/A");
					//Clear display
					mainDisplay.setText("");
					semesterLabel.setText("");
					assignmentsDisplay.setText("");
					profInfoDisplay.setText("");
					bookInfoDisplay.setText("");
					
					welcomeLbl.setText("Welcome: " + profile.getFullName());
					break;
				}
			}
				if (correct == false){	//If the entered information is wrong
					JOptionPane.showMessageDialog(null, "Incorrect Username or Password! Try Again. ");
					progressBar.setValue(value = value+20);	//Allow the user 5 tries
					if (value == 100){
						JOptionPane.showMessageDialog(null, "You Have Exceeded the Amount of Tries");
						JOptionPane.showMessageDialog(null, "Ending Session");
						System.exit(0);
					}
				}
			
		}else{
			JOptionPane.showMessageDialog(null, "Please Create a New Account First!");
		}
	}
	
	public void addProfileActions(){
		String userName, password, id, fullName;
		
		userName = JOptionPane.showInputDialog("Enter a Username");
		if (userName != null){
			password = JOptionPane.showInputDialog("Enter a Password");
			if (password != null){
				id = JOptionPane.showInputDialog("Enter your student ID");
				if (id != null){
					fullName = JOptionPane.showInputDialog("Enter your full name");
					if (fullName != null){
						account.addUser(new UserProfile(userName, password, id, fullName));
						save();
					}
				}
			}
		}
	}
	/**
	 * Add year actions check for valid inputs of the year title
	 * Also checks for same file names
	 */
	public void addYearActions(){
		//Flush old informations
		dropBox.removeAllItems();
		dropBox.addItem("N/A");
		//Clear display
		mainDisplay.setText("");
		assignmentsDisplay.setText("");
		profInfoDisplay.setText("");
		bookInfoDisplay.setText("");
		
		String yearTitle = "";
		
		while (true){
			boolean exists = false;
		    yearTitle = JOptionPane.showInputDialog("Enter School Year (####-####)");
		    
		    if (yearTitle != null){	//If OK 
		    	//Check if the same text name already exists 
		    	for (int i = 0; i < profile.getYearSize(); i++){
		    		if (yearTitle.equals(profile.getYear(i).getYearTitle())){
		    			exists = true;
		    			break;
		    		}
		    	}
		    	//Check if the field is empty
		    	if (yearTitle.isEmpty()){
		    		JOptionPane.showMessageDialog(null, "The Field is Empty!", "Check Inputs Warning", JOptionPane.WARNING_MESSAGE);
		    	
		    	//Check for invalid characters; since a .txt file cannot be named with these chars
		    	}else if (!yearTitle.matches("20[0-9][0-9]-20[0-9][0-9]")){	// Check for format of string	(TutorialsPoint)
			    	JOptionPane.showMessageDialog(null, "Inputs Does Not Matches with the Format (####-####)", "Check Inputs Warning", JOptionPane.WARNING_MESSAGE);
			    
			    }else if (exists){ //Check for same file name
			    	JOptionPane.showMessageDialog(null, "A Year with that Title Already Exists!", "Name Exists Warning", JOptionPane.WARNING_MESSAGE);
			    	
			    }else{	//If input passes all the data checks
			    	profile.addYear(new Year (yearTitle));	
			   		year = profile.getLastYear();
					mainFrame.setTitle(yearTitle);
					JOptionPane.showMessageDialog(null, "Choose From the (+) Menu to Create a New Semester");
				   	break;	
			   	}
			    
		   	}else{	//If Canceled 
		   		break;
		    }
		}
	}
	
	/**
	 * These are the actions to be performed when a new semester is added
	 */
	public void addSemesterActions(){
		//Flush old informations
		dropBox.removeAllItems();
		dropBox.addItem("N/A");
		//Clear display
		mainDisplay.setText("");
		semesterLabel.setText("");
		assignmentsDisplay.setText("");
		profInfoDisplay.setText("");
		bookInfoDisplay.setText("");
		
		String semester = "";
		if (year != null){	//Check for year creation
			if (semesterCount < 2){	//Limit the number of semesters to 2 only
				while(semester.equals("")){ //Keep looping if the field is empty
					semester = JOptionPane.showInputDialog("Enter Semester Season and Year");
					if (semester == null){
						break;
					}
				}
				if (semester != null){	//If OK
					int forward = 0;
					//INITIAL SETUP
					year.addSemester(new Semester(semester)); //Initialize Semester object;	
					semesterCount++;
					thisSemester = year.getLastSemester();		//Initialize thisSemester. 
					semesterLabel.setText(semester);
					while (true){ //Enter Several Courses if preferred
						addClass();
						display();
						forward = JOptionPane.showConfirmDialog(null, "Add another course?", "+Course", JOptionPane.YES_NO_OPTION);
						if (forward == 1){	//If NO
							break;
						}	
					}
				}
			}else{
				JOptionPane.showMessageDialog(null, "The Maximum Number of Semesters Has Been Created", "Warning!!", JOptionPane.WARNING_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(null, "Please Create a New Year First", "Warning!!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * These are the actions to be performed when a new class is added
	 */
	public void addClassActions(){
		if (year != null){
			if (thisSemester != null){		//Check for semester creation			
				addClass();
				display();
			}else{
				JOptionPane.showMessageDialog(null, "Please Start a New Semester First!");
			}
		}else{
			JOptionPane.showMessageDialog(null, "Please Start a New Year First!");
		}
	}
	
	/**
	 * These are the actions to be performed when a new assignment is added
	 */
	public void addAssignmentActions(){
		if (year != null){
			if (thisSemester != null){		//Data check to see if the semester have been created yet
				if (dropBox.getSelectedItem() != "N/A"){ //Data check to see if a class is selected
					for (int i = 0; i < thisSemester.coursesSize(); i++){
						if (dropBox.getSelectedItem() == thisSemester.getCourse(i).getCourseName()){
							addAssignment(i);
							display();
							detailsDisplay(i);	
							break;	//If the class is found break out of it.
						}
					}
				}else{
					JOptionPane.showMessageDialog(null, "Please Select a Class from the Drop Down!");
				}
				
			}else{
				JOptionPane.showMessageDialog(null, "Please Start a New Semester First!");
			}
		}else{
			JOptionPane.showMessageDialog(null, "Create a New Year First!");
		}
	}
	
	public void editClassActions(){
		//Setting the fields to previously set information of the class
		if (year != null){
			if (thisSemester != null){		//Data check to see if the semester have been created yet
				if (dropBox.getSelectedItem() != "N/A"){ //Data check to see if a class is selected
					mainPanel.setVisible(false);
					editClassPanel.setVisible(true);
					courseNameField.setText(thisSemester.getCourse(dropBox.getSelectedIndex()-1).getCourseName());
					profNameField.setText(thisSemester.getCourse((dropBox.getSelectedIndex()-1)).getProfName());
					profSubjectField.setText(thisSemester.getCourse((dropBox.getSelectedIndex()-1)).getSubject());
					profEmailField.setText(thisSemester.getCourse((dropBox.getSelectedIndex()-1)).getEmail());
					profPhoneField.setText(thisSemester.getCourse((dropBox.getSelectedIndex()-1)).getPhone());
					profOfficeField.setText(thisSemester.getCourse((dropBox.getSelectedIndex()-1)).getOffice());
					profHoursField.setText(thisSemester.getCourse((dropBox.getSelectedIndex()-1)).getOfficeHours());
					
					bookTitleField.setText(thisSemester.getCourse((dropBox.getSelectedIndex()-1)).getTitle());
					publishedDateField.setText(thisSemester.getCourse((dropBox.getSelectedIndex()-1)).getDatePublished());
					authorField.setText(thisSemester.getCourse((dropBox.getSelectedIndex()-1)).getAuthor());
					editionField.setText(thisSemester.getCourse((dropBox.getSelectedIndex()-1)).getEdition());
					
					hwSpin.setValue(thisSemester.getCourse((dropBox.getSelectedIndex()-1)).getHomeworkPercent() *100);
					examSpin.setValue(thisSemester.getCourse((dropBox.getSelectedIndex()-1)).getExamPercent() *100);
					projectSpin.setValue(thisSemester.getCourse((dropBox.getSelectedIndex()-1)).getProjectPercent()*100);
					labSpin.setValue(thisSemester.getCourse((dropBox.getSelectedIndex()-1)).getLabPercent()*100);
					inClassSpin.setValue(thisSemester.getCourse((dropBox.getSelectedIndex()-1)).getInClassPercent()*100);
				}else{
					JOptionPane.showMessageDialog(null, "Please Select a Class from the Drop Down!");
				}
				
			}else{
				JOptionPane.showMessageDialog(null, "No Courses Found!");
			}
		}else{
			JOptionPane.showMessageDialog(null, "No Courses Found!");
		}
	}
	
	public void editAssignmentActions(){
		if (year != null){
			if (thisSemester != null){		//Data check to see if the semester have been created yet
				if (dropBox.getSelectedItem() != "N/A"){ //Data check to see if a class is selected
					JComboBox<String> assignmentsBox = new JComboBox<String>();
					for (int o = 0; o < thisSemester.getCourse(dropBox.getSelectedIndex()-1).assignmentSize(); o++){
						assignmentsBox.addItem(thisSemester.getCourse(dropBox.getSelectedIndex()-1).getAssignment(o).getName());	//Add all the assignments name into the combo box
					}
					int option = JOptionPane.showConfirmDialog(null, assignmentsBox, "Pick an Assignment to Edit", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION){
						//Switch Panels
						editAssignmentPanel.setVisible(true);
						mainPanel.setVisible(false);
						//Find the assignment
						for (int x = 0; x < thisSemester.getCourse(dropBox.getSelectedIndex()-1).assignmentSize(); x ++){
							if (assignmentsBox.getSelectedItem() == thisSemester.getCourse(dropBox.getSelectedIndex()-1).getAssignment(x).getName()){	//Loop to find matching semester name
								assignmentIndex = x;
								//Get that assignment details
								assField.setText(thisSemester.getCourse(dropBox.getSelectedIndex()-1).getAssignment(x).getName());
								assOnField.setText(thisSemester.getCourse(dropBox.getSelectedIndex()-1).getAssignment(x).getDate());
								assDueField.setText(thisSemester.getCourse(dropBox.getSelectedIndex()-1).getAssignment(x).getDue());
								if (thisSemester.getCourse(dropBox.getSelectedIndex()-1).getAssignment(x).getType().equals("Homework")){
									hwRadio.setSelected(true);
								}else if (thisSemester.getCourse(dropBox.getSelectedIndex()-1).getAssignment(x).getType().equals("Exam")){
									examRadio.setSelected(true);
								}else if (thisSemester.getCourse(dropBox.getSelectedIndex()-1).getAssignment(x).getType().equals("In Class")){
									inClassRadio.setSelected(true);
								}else if (thisSemester.getCourse(dropBox.getSelectedIndex()-1).getAssignment(x).getType().equals("Project")){
									projectRadio.setSelected(true);
								}else{
									labRadio.setSelected(true);
								}
								gradeSpin.setValue(thisSemester.getCourse(dropBox.getSelectedIndex()-1).getAssignment(x).getGrade());
								break;	//If the assignment is found, break out of the loop
							}
						}
					}	
				}else{
					JOptionPane.showMessageDialog(null, "Choose A Class To Update");
				}
				
			}else{
				JOptionPane.showMessageDialog(null, "No Assignments Found!");
			}
		}else{
			JOptionPane.showMessageDialog(null, "No Assignments Found!");
		}
	}
	
	
	/**
	 * Actions performed when a semester is removed
	 */
	public void removeSemesterActions(){
		if (year!= null){
			if (year.getSemesterSize() > 0){ //If there are semesters available to delete
				JComboBox<String> semesterBox = new JComboBox<String>();
				for (int i = 0; i < year.getSemesterSize(); i++){
					semesterBox.addItem(year.getSemester(i).getName());		//Add all the semesters title to the combo box
				}
				int confirm = JOptionPane.showConfirmDialog(null, semesterBox, "Remove a Semester", JOptionPane.OK_CANCEL_OPTION);
				if (confirm != JOptionPane.CANCEL_OPTION){	//If OK is pressed
					for (int i = 0; i <year.getSemesterSize(); i++){
						if (semesterBox.getSelectedItem() == year.getSemester(i).getName()){	//Find the chosen name
							year.removeSemester(i); //remove that chosen semester
							JOptionPane.showMessageDialog(null, "Semester " + semesterBox.getSelectedItem() + " Was Removed Successfully!");
							//Flush old information
							mainDisplay.setText("");
							profInfoDisplay.setText("");
							bookInfoDisplay.setText("");
							dropBox.removeAllItems();
							dropBox.addItem("N/A");
							save();	//Re-save
							break; //Get out of the loop once the removing is done
						}
					}
				}
			}else{
				JOptionPane.showMessageDialog(null, "No Semester Found!", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(null, "No Semester Found!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * Actions performed when a course is removed
	 */
	public void removeCourseActions(){
		if (year != null){
			if (thisSemester != null){		//Data check to see if the semester have been created yet
				if (dropBox.getSelectedItem() != "N/A"){ //Data check to see if a class is selected
					for (int i = 0; i < thisSemester.coursesSize(); i++){
						if (dropBox.getSelectedItem() == thisSemester.getCourse(i).getCourseName()){
							int confirm = JOptionPane.showConfirmDialog(null, "Remove Course: " + thisSemester.getCourse(i).getCourseName(), "Confirm", JOptionPane.YES_NO_OPTION);
							if (confirm == JOptionPane.YES_OPTION){
								thisSemester.removeCourse(i);	//Remove the selected course
								dropBox.removeItem(dropBox.getSelectedItem());	//Remove the item that is selected
								display();
								//Clear details display
								assignmentsDisplay.setText("");
								profInfoDisplay.setText("");
								bookInfoDisplay.setText("");
								save();	//Re-save
								break;	//If the class is found break out of it.
							}
						}
					}
				}else{
					JOptionPane.showMessageDialog(null, "Please Select a Class from the Drop Down!");
				}
				
			}else{
				JOptionPane.showMessageDialog(null, "No Courses Found!");
			}
		}else{
			JOptionPane.showMessageDialog(null, "No Courses Found!");
		}
	}
	
	/**
	 * Actions performed when an assignment is removed
	 */
	public void removeAssignmentActions(){
		if (year != null){
			if (thisSemester != null){		//Data check to see if the semester have been created yet
				if (dropBox.getSelectedItem() != "N/A"){ //Data check to see if a class is selected
					for (int i = 0; i < thisSemester.coursesSize(); i++){
						if (dropBox.getSelectedItem() == thisSemester.getCourse(i).getCourseName()){
							JComboBox<String> assignmentsBox = new JComboBox<String>();
							for (int o = 0; o < thisSemester.getCourse(i).assignmentSize(); o++){
								assignmentsBox.addItem(thisSemester.getCourse(i).getAssignment(o).getName());	//Add all the assignments name into the combo box
							}
							int option = JOptionPane.showConfirmDialog(null, assignmentsBox, "Remove an Assignment", JOptionPane.OK_CANCEL_OPTION);
							if (option == JOptionPane.OK_OPTION){
								for (int x = 0; x < thisSemester.getCourse(i).assignmentSize(); x ++){
									if (assignmentsBox.getSelectedItem() == thisSemester.getCourse(i).getAssignment(x).getName()){	//Loop to find matching semester name
										thisSemester.getCourse(i).removeAssignment(x);	//remove that chosen assignment
										break;	//If the assignment is found, break out of the loop
									}
								}
								display();
								detailsDisplay(i);
								save();	//Re-save
								break;	//If the class is found break out of it.
							}	
						}
					}
				}else{
					JOptionPane.showMessageDialog(null, "Choose A Class To Remove Assignments From!");
				}
				
			}else{
				JOptionPane.showMessageDialog(null, "No Assignments Found!");
			}
		}else{
			JOptionPane.showMessageDialog(null, "No Assignments Found!");
		}
	}
	public void loadYearActions(){
		//Trash old information displays
		dropBox.removeAllItems();
		dropBox.addItem("N/A");
		semesterLabel.setText("");
		mainDisplay.setText("");
		assignmentsDisplay.setText("");
		profInfoDisplay.setText("");
		bookInfoDisplay.setText("");
		
		if (profile != null){	//Check if year exists
			if (profile.getYearSize() > 0){	//Check if there are any years available
				JComboBox<String> yearsBox = new JComboBox<String>();
				for (int i = 0; i < profile.getYearSize(); i++){
					yearsBox.addItem(profile.getYear(i).getYearTitle());	//Add all the available years to the combo box
				}
				int yearChoice = JOptionPane.showConfirmDialog(null, yearsBox, "Choose a Year to Load", JOptionPane.OK_CANCEL_OPTION);
				if (yearChoice == JOptionPane.OK_OPTION){	
					for (int i = 0; i < profile.getYearSize(); i++){	//Loop to find the matching semester by its name
						if (yearsBox.getSelectedItem() == profile.getYear(i).getYearTitle()){
							year = profile.getYear(i);
							//Prompt to Load, or Create a semester
							int choice = JOptionPane.showConfirmDialog(null, "Load a Semester?", "Load Semester Option", JOptionPane.YES_NO_OPTION);
							
							if (choice == JOptionPane.YES_OPTION){
								loadSemesterActions();
							}else{	//if no
								//Give option to create a new semester instead
								 choice = JOptionPane.showConfirmDialog(null, "Create a new Semester Instead?", "Create Semester Option", JOptionPane.YES_NO_OPTION);
								 if (choice == JOptionPane.YES_OPTION)
									 addSemesterActions();
							}
							break;	//If the semester is found, break out of the loop
						}
					}
					mainFrame.setTitle(year.getYearTitle());
					
				}
			}else{
				JOptionPane.showMessageDialog(null, "No Semesters Available To Load!");
			}
		}else{
			JOptionPane.showMessageDialog(null, "No Semesters Available To Load!");
		}	
	}
	
	/**
	 * Load a semester through the menu
	 * Then set it to the thisSemester object
	 * To allow for further changes to the courses and assignments of that particular semester
	 */
	public void loadSemesterActions(){
		//Trash old information displays
		dropBox.removeAllItems();
		dropBox.addItem("N/A");
		semesterLabel.setText("");
		mainDisplay.setText("");
		assignmentsDisplay.setText("");
		profInfoDisplay.setText("");
		bookInfoDisplay.setText("");
		
		if (year != null){	//Check if year exists
			if (year.getSemesterSize() > 0){	//Check if there are any semesters available
				JComboBox<String> semestersBox = new JComboBox<String>();
				for (int i = 0; i < year.getSemesterSize(); i++){
					semestersBox.addItem(year.getSemester(i).getName());	//Add all the available semesters names to the combo box
				}
				int semesterChoice = JOptionPane.showConfirmDialog(null, semestersBox, "Choose a Semester to Load", JOptionPane.OK_CANCEL_OPTION);
				if (semesterChoice == JOptionPane.OK_OPTION){	
					for (int i = 0; i < year.getSemesterSize(); i++){	//Loop to find the matching semester by its name
						if (semestersBox.getSelectedItem() == year.getSemester(i).getName()){
							thisSemester = year.getSemester(i);
							break;	//If the semester is found, break out of the loop
						}
					}
					//Set Components with the chosen semester's information
					semesterLabel.setText(thisSemester.getName());
					display();
					for (int i = 0; i < thisSemester.coursesSize(); i++){	//Add all the courses available in this semester to the comboBox
						dropBox.addItem(thisSemester.getCourse(i).getCourseName());
					}
				}
			}else{
				JOptionPane.showMessageDialog(null, "No Semesters Available To Load!");
			}
		}else{
			JOptionPane.showMessageDialog(null, "No Semesters Available To Load!");
		}
	}
	
	public void informationActions(){
		if (year != null){
			if (year.getSemesterSize() > 0){
				yearLbl.setText("Year " + year.getYearTitle());
				gpaLbl.setText("Cumulative GPA: " + year.getYearGPA());
				
				if (year.getSemesterSize() > 0){	//First semester
					lblFall.setText(year.getSemester(0).getName());
					fallTextArea.setText(year.getSemester(0).semesterInfo());
					fallGPALbl.setText("GPA: " + year.getSemester(0).getSemesterGPA());
				}else{
					fallTextArea.setText("No Information Available");
				}
				if (year.getSemesterSize() > 1){	//Second semester
					lblSpring.setText(year.getSemester(1).getName());
					springTextArea.setText(year.getSemester(1).semesterInfo());
					springGPALbl.setText("GPA: " + year.getSemester(1).getSemesterGPA());
				}else{
					springTextArea.setText("No Information Available");
				}
				
				informationPanel.setVisible(true);
				mainPanel.setVisible(false);
				//JOptionPane.showMessageDialog(null,informationPanel(), "Year Information", JOptionPane.PLAIN_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(null, "Nothing To Show!", "Message", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * Save created information to a .txt file
	 * 
	 */
	public void save(){ 			
		if (account != null){
			account.saveAccounts("Accounts");
			JOptionPane.showMessageDialog(null, "Saved Successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(null, "Nothing To be Saved!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * Load whatever is in the .txt file
	 * The reader looks for any matching tags and scan in it's values
	 * A tag with more than 1 values are separated by a | 
	 * Therefore they are read in by the .split function
	 * @param fileName the path of where the file is to be saved
	 */
	public void loadAccounts(){
		try {
			String currentLine;
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader ("Accounts.txt"));
			String[] section;
			semesterCount = 0;
			
			while ((currentLine = br.readLine()) != null){ //While the text file is not empty
				StringTokenizer st = new StringTokenizer(currentLine, ":");
				String tag = st.nextToken();
				String value = st.nextToken();
			
				switch(tag){
				case "ACCOUNT":
					account = new Account();
					break;
				case "PROFILE":
					section = value.split("\\|");
					account.addUser(new UserProfile(section[0], section[1],section[2],section[3]));
					break;
				case "YEAR":
					account.getLastProfile().addYear(new Year(value));
					break;
				case "SEMESTER":
					account.getLastProfile().getLastYear().addSemester(new Semester(value)); // Just add semester name
					semesterCount++;
					break;
				case "CLASS":
					account.getLastProfile().getLastYear().getLastSemester().addClass(new Courses(value)); //Just add course name
					break;
				case "PROF":
					section = value.split("\\|"); //Use splitter to split professor's to-string
					account.getLastProfile().getLastYear().getLastSemester().getLastCourse().setProfInfo(section[0], section[1], section[2], section[3], section[4], section[5]);
					break;
				case "TEXT":
					section = value.split("\\|"); //Use splitter to split text's to-string
					account.getLastProfile().getLastYear().getLastSemester().getLastCourse().setTextInfo(section[0], section[1], section[2], section[3]);
					break;
				case "PERC":
					section = value.split("\\|");
					account.getLastProfile().getLastYear().getLastSemester().getLastCourse().setPercentage(Double.parseDouble(section[0]), Double.parseDouble(section[1]), Double.parseDouble(section[2]), Double.parseDouble(section[3]), Double.parseDouble(section[4]));
					break;
				case "HW":
					section = value.split("\\|"); //Use splitter to split
					account.getLastProfile().getLastYear().getLastSemester().getLastCourse().addAssignment(new Assignments(section[0], section[1], section[2], section[3], Double.parseDouble(section[4])));
					break;
				}
			}
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No File Found", "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void updateClassActions(){
		double exam = (Double) examSpin.getValue()/100.0;
		double hw = (Double) hwSpin.getValue()/100.0;
		double inClass = (Double) inClassSpin.getValue()/100.0;
		double lab = (Double) labSpin.getValue()/100.0;
		double project = (Double) projectSpin.getValue()/100.0;
		
		double total =  exam + hw + inClass + lab + project;
		
		if (total == 1.0){
			//Update the new information
			thisSemester.getCourse(dropBox.getSelectedIndex()-1).setCourseName(courseNameField.getText());
			thisSemester.getCourse(dropBox.getSelectedIndex()-1).setProfInfo(profNameField.getText(), profSubjectField.getText(),
						profEmailField.getText(), profPhoneField.getText(), profOfficeField.getText(), profHoursField.getText());
			thisSemester.getCourse(dropBox.getSelectedIndex()-1).setTextInfo(bookTitleField.getText(), authorField.getText(), 
						publishedDateField.getText(), editionField.getText());
			thisSemester.getCourse(dropBox.getSelectedIndex()-1).setPercentage(exam, hw, inClass, lab, project);
			//Save
			save();
			//Go Back to Previous screen
			mainPanel.setVisible(true);
			editClassPanel.setVisible(false);
			//Update the display
			display();
			detailsDisplay(dropBox.getSelectedIndex()-1);
		}else{
			valueWarningLbl.setVisible(true);
		}
	}
	
	public void updateAssignmentActions(){
		String type;
		//Get the updated information
		thisSemester.getCourse(dropBox.getSelectedIndex()-1).getAssignment(assignmentIndex).setName(assField.getText());
		thisSemester.getCourse(dropBox.getSelectedIndex()-1).getAssignment(assignmentIndex).setDate(assOnField.getText());
		thisSemester.getCourse(dropBox.getSelectedIndex()-1).getAssignment(assignmentIndex).setDue(assDueField.getText());
		if (hwRadio.isSelected()){
			type = hwRadio.getText();
		}else if (examRadio.isSelected()){
			type = examRadio.getText();
		}else if (inClassRadio.isSelected()){
			type = inClassRadio.getText();
		}else if (projectRadio.isSelected()){
			type = projectRadio.getText();
		}else{
			type = labRadio.getText();
		}
		thisSemester.getCourse(dropBox.getSelectedIndex()-1).getAssignment(assignmentIndex).setType(type);
		thisSemester.getCourse(dropBox.getSelectedIndex()-1).getAssignment(assignmentIndex).setGrade((Double) gradeSpin.getValue());
		
		//Save
		save();
		//Switch Panels
		mainPanel.setVisible(true);
		editAssignmentPanel.setVisible(false);
		//Update Info Displays
		display();
		detailsDisplay(dropBox.getSelectedIndex()-1);
	}
	
	/**
	 * This display the assignments, professor, and textbook information
	 * @param index Used to locate a specific course's assignments, professor, textbook information
	 */
	public void detailsDisplay(int index){		
		String assignment = "";
		String prof = "";
		String text = "";

		for (int i = 0; i < thisSemester.getCourse(index).assignmentSize(); i ++){	//Go to that specified class with the index, and print out all assignments
			assignment += thisSemester.getCourse(index).getAssignment(i).toString() + "\n"; 
		}
		
		prof =  thisSemester.getCourse(index).getProfInfo();
		text =	thisSemester.getCourse(index).getTextInfo();
		assignmentsDisplay.setText(assignment);
		profInfoDisplay.setText(prof);
		bookInfoDisplay.setText(text);
	}
	
	/**
	 * This is the main display
	 * Strings are formatted to display all the courses along with the GPA, and grades for the semester
	 */
	public void display(){			
		String show = "          " + "Course:" + "\t" + "Average:" + "\n";
		if (thisSemester != null){
			for (int i = 0; i < thisSemester.coursesSize(); i++){
				show += "          " + thisSemester.getCourse(i).getCourseName() + "\t" + thisSemester.getCourse(i).getCourseGrade() + " " + thisSemester.getCourse(i).getLetterGrade() + "\n";
			}
			show += "\n-------------\n" 
					+ "GPA: " + thisSemester.getSemesterGPA()
					+ "\n-------------";
			mainDisplay.setText(show);
		}
	}
	
	/**
	 * Allow the user to fill out all the informations of that class
	 * Like the class name, professor info, textbook info, and percentages on assignments
	 */
	public void addClass(){
		String className = "", profName, subject, email, phone, office, hours;
		int option1;
		JTextField profName1 = new JTextField();
		JTextField subject1 = new JTextField();
		JTextField email1 = new JTextField();
		JTextField phone1 = new JTextField();
		JTextField office1 = new JTextField();
		JTextField hours1 = new JTextField();

		Object[] txtFields1 ={
				"Professor's Name: ", profName1,
				"Subject: ", subject1,
				"Email: ", email1,
				"Phone: ", phone1,
				"Office Room: ", office1,
				"Office Hours: ", hours1
		};
		
		//Initializes the class
		while (className.equals("")){	//keep looping if the field is empty
			className = JOptionPane.showInputDialog("Course Name: ");
			if (className == null){
				break;
			}else if (className.length() > 15){	//Limit the number of characters allowable
				className = className.substring(0,15);
			}
		}
		if (className != null){
			
			//Test for correct inputs
			while (true){
				option1 = JOptionPane.showConfirmDialog(null, txtFields1, "Professor's Information", JOptionPane.OK_CANCEL_OPTION);
				//If any of these fields contain invalid character 
				if (option1 != JOptionPane.CANCEL_OPTION){
					if (profName1.getText().contains("|") || subject1.getText().contains("|") || email1.getText().contains("|") 
							|| phone1.getText().contains("|") || office1.getText().contains("|") || hours1.getText().contains("|")){
						JOptionPane.showMessageDialog(null, "Invalid Character '|' ");
					}else{
						break;
					}
				}else{
					break;
				}
			}
				//Nested loop to ensure that if one pane is canceled, exit out of it entirely
				//Enter Professor's Information
			if (option1 == JOptionPane.OK_OPTION){
				//The spaces will ensure that if the user does not enter anything, something will be saved
				profName = " " +profName1.getText();
				subject = " " +subject1.getText();
				email = " " +email1.getText();
				phone = " " +phone1.getText();
				office = " " +office1.getText();
				hours = " " +hours1.getText();

//				~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~			
				String title = "", author = "", published = "", edition = "";				
				
				JTextField title1 = new JTextField();
				JTextField author1 = new JTextField();
				JTextField published1 = new JTextField();
				JTextField edition1 = new JTextField();
					
				Object[] txtFields2 ={
					"Title: ", title1,
					"Author: ", author1,
					"Published on: ", published1,
					"Edtion: ", edition1
				};
				
				while (true){
					option1 = JOptionPane.showConfirmDialog(null, txtFields2, "Book's Information", JOptionPane.OK_CANCEL_OPTION);
					if (option1 != JOptionPane.CANCEL_OPTION){
						if (title1.getText().contains("|") || author1.getText().contains("|") || published1.getText().contains("|") || edition1.getText().contains("|")){
								JOptionPane.showMessageDialog(null, "Invalid Character '|' ");
						}else{	//Will exit loop if there are no invalid characters
							break;
						}
					}else{
						break;
					}
				}
					//Enter Book's Information
				if (option1 == JOptionPane.OK_OPTION) {
					title = " " +title1.getText();
					author = " " +author1.getText();
					published = " " +published1.getText();
					edition = " " + edition1.getText();
					
//				~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			
					double hw, exam, project, lab, eElse;
							
					while (true){	//Check to see if the percentages adds up to 1
						option1 = JOptionPane.showConfirmDialog(null, percentPanel(), "Assignment Percentage (%)", JOptionPane.OK_CANCEL_OPTION);
							
						if (option1 == JOptionPane.OK_OPTION) {
							hw = (Integer.parseInt((String) arrCombo.get(0).getSelectedItem())) / 100.0;
							exam = (Integer.parseInt((String) arrCombo.get(1).getSelectedItem())) / 100.0;
							project = (Integer.parseInt((String) arrCombo.get(2).getSelectedItem())) / 100.0;
							lab = (Integer.parseInt((String) arrCombo.get(3).getSelectedItem())) / 100.0;
							eElse = (Integer.parseInt((String) arrCombo.get(4).getSelectedItem())) / 100.0;
									
							if ((hw + exam + project + lab + eElse) != 1.0){
								warningLbl.setForeground(Color.ORANGE);
								warningLbl.setText("			Values Must Adds Up to 100%");
							}else{	//Everything will be implemented if the user gets to this point
								thisSemester.addClass(new Courses(className));
								dropBox.addItem(className);
								thisSemester.getLastCourse().setProfInfo(profName, subject, email, phone, office, hours);
								thisSemester.getLastCourse().setTextInfo(title, author, published, edition);
								thisSemester.getLastCourse().setPercentage(exam, hw, eElse, lab, project);		
								save();	//Everything must be filled out before the information can be saved
								break;
							}
						}else{
								break;
						}
					}
				}
			}
		}		
	}//addClass
	
	/**
	 * Allow the user to input the information of each assignment
	 * Like assignment name, grade, etc..
	 * @param index to locate a specific course to get the assignments of that course
	 */
	public void addAssignment(int index){
		String name = "", date, due, type;
		double grade;
		JTextField date1, due1, grade1;
		JComboBox<String> type1;
		int option = 0;
		
		date1 = new JTextField();
		due1 = new JTextField();
		grade1 = new JTextField();
		type1 = new JComboBox<String>();
		
		//To Ensure Only the percentages that the User have chosen will show up when adding an assignment
		if (thisSemester.getCourse(index).homeworkPercent > 0){
			type1.addItem("Homework");
		}if (thisSemester.getCourse(index).projectPercent > 0){
			type1.addItem("Project");
		}if (thisSemester.getCourse(index).labPercent > 0){
			type1.addItem("Lab");
		}if (thisSemester.getCourse(index).examPercent > 0){
			type1.addItem("Exam");
		}if (thisSemester.getCourse(index).inClassPercent> 0){
			type1.addItem("In Class");
		}
	
		Object[] fields ={
				"*Type:", type1,
				"Date Assigned:", date1,
				"Due On:", due1,
				"*Grade Received:", grade1
		};
		
		while (name.equals("")){ //Keep looping if the field is empty
			name = JOptionPane.showInputDialog("*Assignment: ");
			if (name == null){	//If canceled, exit out of loop
				break;
			}else if (name.length() > 11){	//Limit the number of chars allowable
				name = name.substring(0, 11);
			}
		}
		if (name != null){	//If OK
			//Keep looping if the grade field is blank, or is less than zero, or is not a number (stackoverflow)
			while(true){
				option = JOptionPane.showConfirmDialog(null, fields, "Assignment Information", JOptionPane.OK_CANCEL_OPTION);
				if (option != JOptionPane.CANCEL_OPTION){
					if (date1.getText().contains("|") || due1.getText().contains("|")){
						JOptionPane.showMessageDialog(null, "Invalid Character '|'");
					}else{	//If doesnt contain invalid inputs
						if (grade1.getText().equals("") || Double.parseDouble(grade1.getText()) < 0 || grade1.getText().contains("[a-zA-Z]+") == true){
							JOptionPane.showMessageDialog(null, "Grade Must Be a Real Positive Number");
						}else{
							break;
						}
					}
				}else{
					break;
				}
				
			}
			if (option == JOptionPane.OK_OPTION){
				date = date1.getText();
				due = due1.getText();
				type = (String) type1.getSelectedItem();
				grade = Double.parseDouble(grade1.getText());
					
				thisSemester.getCourse(index).addAssignment(new Assignments(name, date, due, type, grade));
				save();
			}
		}
	}
	
	/**
	 * A panel that would allow the user to select a certain percentage for each course
	 * There are 5 drop downs each with numbers from 0 to 100 in multiples of 5 
	 * @return a panel where the user can select the percentages from 5 different types of assignments
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JPanel percentPanel(){
		JPanel backPanel = new JPanel(new GridLayout(3,1, 0, 5));
		JPanel topPanel = new JPanel(new GridLayout(1,5,5,10));
		JPanel lowerPanel = new JPanel(new GridLayout(1,5,10,0));
		
		String[] type = {"Homework(%)", "Exam(%)", "Project(%)", "Lab(%)", "In Class(%)"};
		
		for (int i = 0; i < type.length; i++){
			JLabel typeLbl = new JLabel(type[i]);
			typeLbl.setForeground(Color.ORANGE);
			topPanel.add(typeLbl);
		}

		arrCombo = new ArrayList<JComboBox>();
		
		String[] percentage = {"0","5", "10","15", "20","25", "30","35", "40","45", "50", "55", "60", "65", "70", "75", "80","85", "90","95", "100"};
		for (int i = 0; i < 5; i++){
			JComboBox percBox = new JComboBox(percentage);
			percBox.setBackground(Color.LIGHT_GRAY);
			arrCombo.add(percBox);	
		}
		
		for (int i = 0; i < arrCombo.size(); i ++){
			lowerPanel.add((JComboBox) arrCombo.get(i));
		}
		
		backPanel.add(topPanel);
		backPanel.add(lowerPanel);
		backPanel.add(warningLbl);
		return backPanel;

	}
} // CLASS
