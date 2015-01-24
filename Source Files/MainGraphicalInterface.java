
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
	private Year year;
	private Semester thisSemester;

	//Keep track of which object it is
	private int profileIndex, assignIndex;

	//List the available courses in the semester
	private JComboBox<String> dropBox;

	@SuppressWarnings("rawtypes")
	private ArrayList<JComboBox> arrCombo;

	private JPasswordField passwordField;
	private JTextArea mainDisplay, assignmentsDisplay, profInfoDisplay, bookInfoDisplay;
	private JTextField userNameField, profNameField, courseNameField, profSubjectField, profPhoneField , profEmailField, profHoursField,
	profOfficeField, bookTitleField, publishedDateField, editionField, authorField, assignField, assignOnField, assignDueField;
	private JLabel semesterLabel, warningLbl, valueWarningLbl, welcomeLbl;
	private JRadioButton hwRadio, examRadio, inClassRadio, labRadio, projectRadio;
	private JSpinner hwSpin, examSpin, inClassSpin, projectSpin, labSpin, gradeSpin;
	private JPanel mainPanel, securityPanel, editClassPanel, editAssignmentPanel;
	informationPanel infoPanel;
	JProgressBar progressBar;
	int value = 0;

	//Group the type of assignment's radio buttons
	private final ButtonGroup buttonGroup = new ButtonGroup();

	//Location of where to save the file
	private final String fileName = "Accounts.txt";

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

//		|||||||||||||||||||||||||||||||||||||||||||||||||MAIN PANEL|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
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

		warningLbl = new JLabel();

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

		JLabel lblTextbookRequired = new JLabel("Textbook Information");
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

						//When ever a selection occurs details display the selected class
						for (int i = 0; i < thisSemester.courseSize(); i++){

							//Display the selected course
							if ((String.valueOf(dropBox.getSelectedItem()).equals(thisSemester.getCourse(i).getCourseName()))){
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
				if (year != null){
					if (year.getSemesterSize() > 0){

						infoPanel = new informationPanel(year, mainPanel);
						mainFrame.getContentPane().add(infoPanel.getPanel(), "name_27893318697400");
						infoPanel.informationActions();
						infoPanel.setVisible(true);
						mainPanel.setVisible(false);
					}
				}else{
					JOptionPane.showMessageDialog(null, "Nothing To Show!", "Message", JOptionPane.WARNING_MESSAGE);
				}
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

					//Prompt the user for the current password
					String pass = JOptionPane.showInputDialog("Enter Current Password");

					if (pass != null){	//If OK

						//Compare the entered password with the registered password
						if (pass.equals(account.getProfile(profileIndex).getPassword())){

							while (true){

								//Prompt the user for a new password
								newPass = JOptionPane.showInputDialog("Enter New Password");

								//If Cancelled
								if (newPass == null){
									break;
								}

								if (newPass.length() > 5){	//Implement a complex password restrictions Here<<<

									//Set the new password
									account.getProfile(profileIndex).setPassword(newPass);

									//Re-save
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

//		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++	END OF ACTIONS OF MAIN PANEL

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

//		|||||||||||||||||||||||||||||||||||||||||||||||||EDIT ASSIGNMENT PANEL|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
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

		assignField = new JTextField();
		assignField.setForeground(Color.BLACK);
		assignField.setColumns(10);
		assignField.setBackground(new Color(255, 255, 224));
		assignField.setBounds(198, 69, 126, 20);
		editAssignmentPanel.add(assignField);

		assignOnField = new JTextField();
		assignOnField.setColumns(10);
		assignOnField.setBackground(new Color(255, 255, 224));
		assignOnField.setBounds(198, 143, 126, 20);
		editAssignmentPanel.add(assignOnField);

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

		assignDueField = new JTextField();
		assignDueField.setColumns(10);
		assignDueField.setBackground(new Color(255, 255, 224));
		assignDueField.setBounds(198, 185, 126, 20);
		editAssignmentPanel.add(assignDueField);

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

	}//Initialize

	/**
	 * Login functions, find matching password and username
	 * Then flush old information if available
	 * Limit the incorrect attempt to login to 5, if exceed that limit, the program will terminate
	 */
	public void loginActions(){

		boolean correct = false;

		if (account.getSize() > 0){
			for (int i = 0; i < account.getSize(); i++){	//Loop to check all the logins info

				if ((userNameField.getText().equals(account.getProfile(i).getUsername())) && (String.valueOf(passwordField.getPassword()).equals(account.getProfile(i).getPassword()))){
					profileIndex = i;
					mainPanel.setVisible(true);
					securityPanel.setVisible(false);
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

					//Welcome the user on the Menu bar
					welcomeLbl.setText("Welcome: " + account.getProfile(profileIndex).getFullName());
					break;
				}
			}

			//If the username field and password field are not empty and correct is false then increment the progress bar and the number of tries available
			if (!userNameField.getText().isEmpty() && !String.valueOf(passwordField.getPassword()).isEmpty() && correct == false){
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

	/**
	 * Prompt to collect username, password, student id, and full name
	 * Password is implemented with a minimum length restriction in order to make the account a little more secure
	 */
	public void addProfileActions(){

		boolean alreadyExists = true;
		JTextField userNameTxtField = new JTextField(), passwordTxtField = new JTextField(),
				fullNameTxtField = new JTextField(), idTxtField = new JTextField();
		int promptMsg = 0;

		Object[] prompt = {
			"Enter Username:", 	userNameTxtField,
			"Enter Password:", passwordTxtField,
			"Your Full Name:", fullNameTxtField,
			"Your Student ID:", idTxtField,
		};

		while (promptMsg != JOptionPane.CANCEL_OPTION){

			promptMsg = JOptionPane.showConfirmDialog(null,	prompt, "Profile Information", JOptionPane.OK_CANCEL_OPTION);

			if (promptMsg == JOptionPane.OK_OPTION){

				//1. Check for matching userName
				for (int i = 0; i < account.getSize(); i ++){
					if (userNameTxtField.getText().equals(account.getProfile(i).getUsername())){
						alreadyExists = true;
					}else{
						alreadyExists = false;
					}
				}

				//2. If userName already exist and the username field is empty
				//Then loop again
				if (alreadyExists == true || userNameTxtField.getText().isEmpty()){

					//Prompt to let the user know and re-loop
					JOptionPane.showMessageDialog(null, "The username already exists, or the field is empty!");

				//3. If the password is less than 5 character in length
				//Then loop again
				}else if (passwordTxtField.getText().length() < 5){

					//Prompt to let the user know and loop to enter another password
					JOptionPane.showMessageDialog(null, "Please enter a password that is longer than 5 characters in length!");

				//4. Check if the name field is empty or contains invalid character
				}else if (fullNameTxtField.getText().isEmpty() || !fullNameTxtField.getText().contains("[a-zA-Z]")){

					JOptionPane.showMessageDialog(null, "The name field is empty, or contains invalid characters!");

				//5. Check if the ID field is empty
				}else if (idTxtField.getText().isEmpty()){

					JOptionPane.showMessageDialog(null, "The ID field is empty!");

				//6. If all of the above passes store those information
				//And get out of loop
				}else{

					//Create a new account
					account.addUser(new UserProfile(userNameTxtField.getText(), passwordTxtField.getText(), fullNameTxtField.getText(), idTxtField.getText()));
					break;
				}
			}
		}

	}
	/**
	 * Check for valid year title, it must match the format provided
	 * Once all the data checks are through, set the Year object to the object that had just been created
	 * Then change the frame title to that title
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
		    	for (int i = 0; i < account.getProfile(profileIndex).getYearSize(); i++){
		    		if (yearTitle.equals(account.getProfile(profileIndex).getYear(i).getYearTitle())){
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
			    	account.getProfile(profileIndex).addYear(new Year (yearTitle));

			    	//Set the year object to the object that just had been created
			   		year = account.getProfile(profileIndex).getLastYear();

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
	 * Semesters are limited to only 2 per year
	 * Prompt for input then set that semester object that just had been created to thisSemester
	 * Then for convenience, prompt the user to add classes if they'd like
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

		int forward;
		String semester = "";

		//Check for year creation
		if (year != null){

			//Limit the number of semesters to 2 only per Year
			if (year.getSemesterSize() < 2){

				//Keep looping if the field is empty
				while(semester.isEmpty()){

					semester = JOptionPane.showInputDialog("Enter Semester Season and Year");
					if (semester == null){
						break;
					}
				}

				if (semester != null){	//If OK

					//Initialize Semester object;
					year.addSemester(new Semester(semester));

					//Initialize thisSemester.
					thisSemester = year.getLastSemester();

					//Set the semester label to the semester name
					semesterLabel.setText(semester);

					while (true){
						//Go the the method to execute the adding of classes
						addClass();

						//Refresh the display
						display();

						//Prompt the user if they'd like to add more courses
						forward = JOptionPane.showConfirmDialog(null, "Add another course?", "+Course", JOptionPane.YES_NO_OPTION);

						if (forward == JOptionPane.NO_OPTION){	//If NO
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
	 * Go to the addClass() method to perform the necessary functions
	 * Then display the information that was just created
	 */
	public void addClassActions(){
		if (year != null){
			if (thisSemester != null){

				//Go the the method to execute the adding of classes
				addClass();

				//Refresh the display
				display();

			}else{
				JOptionPane.showMessageDialog(null, "Please Start a New Semester First!");
			}
		}else{
			JOptionPane.showMessageDialog(null, "Please Start a New Year First!");
		}
	}

	/**
	 * Go to addAssignment method to perform the necessary functions
	 * Then display updated information
	 */
	public void addAssignmentActions(){
		if (year != null){
			if (thisSemester != null){		//Data check to see if the semester have been created yet
				if (dropBox.getSelectedItem() != "N/A"){ //Data check to see if a class is selected

					//Keep track of the selected course's index
					int courseIndex = thisSemester.findCourseIndex(String.valueOf(dropBox.getSelectedItem()));

					//go to the method to perform the rest of the operations
					addAssignment(courseIndex);

					//Refresh the displays
					display();
					detailsDisplay(courseIndex);

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

	/**
	 * Set the previously stored information into the present fields
	 * Allow the user to alter the information present in those fields
	 */
	public void editClassActions(){
		//Setting the fields to previously set information of the class
		if (year != null){
			if (thisSemester != null){		//Data check to see if the semester have been created yet
				if (dropBox.getSelectedItem() != "N/A"){ //Data check to see if a class is selected

					//Switch the panels
					mainPanel.setVisible(false);
					editClassPanel.setVisible(true);

					//Keep track of the selected course's index
					int courseIndex = thisSemester.findCourseIndex(String.valueOf(dropBox.getSelectedItem()));

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
	 * Pull the previously saved information of the assignment
	 * Display them into the present text fields
	 */
	public void editAssignmentActions(){
		JComboBox<String> assignmentsBox = new JComboBox<String>();

		if (year != null){
			if (thisSemester != null){		//Data check to see if the semester have been created yet
				if (dropBox.getSelectedItem() != "N/A"){ //Data check to see if a class is selected

					//Locate the selected course's index
					int courseIndex = thisSemester.findCourseIndex(String.valueOf(dropBox.getSelectedItem()));

					for (int o = 0; o < thisSemester.getCourse(courseIndex).assignmentSize(); o++){
						//Add all available assignments name into the combo box
						assignmentsBox.addItem(thisSemester.getCourse(courseIndex).getAssignment(o).getName());
					}

					//Prompt the user to pick an assignment to edit
					int option = JOptionPane.showConfirmDialog(null, assignmentsBox, "Pick an Assignment to Edit", JOptionPane.OK_CANCEL_OPTION);

					if (option == JOptionPane.OK_OPTION){

						//Switch Panels
						editAssignmentPanel.setVisible(true);
						mainPanel.setVisible(false);

						//Locate the assignment index based on it's name
						assignIndex = thisSemester.getCourse(courseIndex).findAssignmentIndex(String.valueOf(assignmentsBox.getSelectedItem()));

						//Set the text fields with the previously saved assignment's information
						assignField.setText(thisSemester.getCourse(courseIndex).getAssignment(assignIndex).getName());
						assignOnField.setText(thisSemester.getCourse(courseIndex).getAssignment(assignIndex).getDate());
						assignDueField.setText(thisSemester.getCourse(courseIndex).getAssignment(assignIndex).getDue());

						//Select whatever radio button matches with the saved type
						if (thisSemester.getCourse(courseIndex).getAssignment(assignIndex).getType().equals("Homework")){
							hwRadio.setSelected(true);
						}else if (thisSemester.getCourse(courseIndex).getAssignment(assignIndex).getType().equals("Exam")){
							examRadio.setSelected(true);
						}else if (thisSemester.getCourse(courseIndex).getAssignment(assignIndex).getType().equals("In Class")){
							inClassRadio.setSelected(true);
						}else if (thisSemester.getCourse(courseIndex).getAssignment(assignIndex).getType().equals("Project")){
							projectRadio.setSelected(true);
						}else{
							labRadio.setSelected(true);
						}

						gradeSpin.setValue(thisSemester.getCourse(courseIndex).getAssignment(assignIndex).getGrade());

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
	 * Prompt the user to pick a semester from the list
	 * Then remove that selected semester based on it's index
	 * Then clear everything and re-save
	 */
	public void removeSemesterActions(){

		JComboBox<String> semesterBox = new JComboBox<String>();

		if (year!= null){
			if (year.getSemesterSize() > 0){ //If there are semesters available to delete

				for (int i = 0; i < year.getSemesterSize(); i++){
					//Add all the semesters title to the combo box
					semesterBox.addItem(year.getSemester(i).getName());
				}

				//Prompt the user to pick a semester to remove
				int confirm = JOptionPane.showConfirmDialog(null, semesterBox, "Remove a Semester", JOptionPane.OK_CANCEL_OPTION);

				if (confirm != JOptionPane.CANCEL_OPTION){	//If OK

					//Locate the semester's index by it's name
					int semesterIndex = year.findSemesterIndex(String.valueOf(semesterBox.getSelectedItem()));

					//remove that chosen semester
					year.removeSemester(semesterIndex);

					JOptionPane.showMessageDialog(null, "Semester " + semesterBox.getSelectedItem() + " Was Removed Successfully!");

					//Flush old information
					mainDisplay.setText("");
					profInfoDisplay.setText("");
					bookInfoDisplay.setText("");
					dropBox.removeAllItems();
					dropBox.addItem("N/A");

					//Re-save
					save();

				}
			}else{
				JOptionPane.showMessageDialog(null, "No Semester Found!", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(null, "No Semester Found!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Find the index of the selected course based on it's name
	 * Prompt the user to confirm the removal
	 * Then the course based on the index
	 * Refresh the display, and re-save
	 */
	public void removeCourseActions(){
		if (year != null){
			if (thisSemester != null){		//Data check to see if the semester have been created yet
				if (dropBox.getSelectedItem() != "N/A"){ //Data check to see if a class is selected

					//Locate the selected course Index
					int courseIndex = thisSemester.findCourseIndex(String.valueOf(dropBox.getSelectedItem()));

					//Prompt to confirm the removal
					int confirm = JOptionPane.showConfirmDialog(null, "Remove Course: " + thisSemester.getCourse(courseIndex).getCourseName(), "Confirm", JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION){

						//Remove the selected course
						thisSemester.removeCourse(courseIndex);

						//Remove the item that is selected from the dropbox
						dropBox.removeItem(dropBox.getSelectedItem());

						//Refresh the display
						display();

						//Clear details display
						assignmentsDisplay.setText("");
						profInfoDisplay.setText("");
						bookInfoDisplay.setText("");

						//Re-save
						save();
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
	 * Add all the available assignments' names into a drop down box
	 * Then prompt the user to select an assignment to remove
	 * Then remove the assignment based on it's index
	 * Then refresh the displays, and re-save
	 */
	public void removeAssignmentActions(){
		JComboBox<String> assignmentsBox = new JComboBox<String>();

		if (year != null){
			if (thisSemester != null){		//Data check to see if the semester have been created yet
				if (dropBox.getSelectedItem() != "N/A"){ //Data check to see if a class is selected

					//Keep track of the selected course's index
					int courseIndex = thisSemester.findCourseIndex(String.valueOf(dropBox.getSelectedItem()));

					for (int o = 0; o < thisSemester.getCourse(courseIndex).assignmentSize(); o++){

						//Add all the assignments name into the combo box
						assignmentsBox.addItem(thisSemester.getCourse(courseIndex).getAssignment(o).getName());
					}

					//Prompt to select an assignment to remove
					int option = JOptionPane.showConfirmDialog(null, assignmentsBox, "Remove an Assignment", JOptionPane.OK_CANCEL_OPTION);

					if (option == JOptionPane.OK_OPTION){

						int assignIndex = thisSemester.getCourse(courseIndex).findAssignmentIndex(String.valueOf(assignmentsBox.getSelectedItem()));

						//Remove that chosen assignment based on it's index
						thisSemester.getCourse(courseIndex).removeAssignment(assignIndex);

						//Refresh Displays
						display();
						detailsDisplay(courseIndex);

						//Re-save
						save();
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

	/**
	 * First add all the available years into a combo box to select from
	 * Then locate the selected year by the index and set the Year object to the found object
	 * Then prompt to load or create a new semester
	 */
	public void loadYearActions(){
		//Trash old information displays
		dropBox.removeAllItems();
		dropBox.addItem("N/A");
		semesterLabel.setText("");
		mainDisplay.setText("");
		assignmentsDisplay.setText("");
		profInfoDisplay.setText("");
		bookInfoDisplay.setText("");

		JComboBox<String> yearsBox = new JComboBox<String>();

		//Check if year exists
		if (account.getProfile(profileIndex) != null){
			//Check if there are any years available
			if (account.getProfile(profileIndex).getYearSize() > 0){

				for (int i = 0; i < account.getProfile(profileIndex).getYearSize(); i++){
					//Add all the available years to the combo box
					yearsBox.addItem(account.getProfile(profileIndex).getYear(i).getYearTitle());
				}

				//Prompt to pick a year to load
				int yearChoice = JOptionPane.showConfirmDialog(null, yearsBox, "Choose a Year to Load", JOptionPane.OK_CANCEL_OPTION);

				if (yearChoice == JOptionPane.OK_OPTION){

					//Locate the selected year's index by it's name
					int yearIndex = account.getProfile(profileIndex).findYearIndex(String.valueOf(yearsBox.getSelectedItem()));

					//Set the year object to the found year
					year = account.getProfile(profileIndex).getYear(yearIndex);

					//Prompt to Load, or Create a semester
					int choice = JOptionPane.showConfirmDialog(null, "Load a Semester?", "Load Semester Option", JOptionPane.YES_NO_OPTION);

					//If Yes, Load that semester, if no create a new semester
					if (choice == JOptionPane.YES_OPTION){

						loadSemesterActions();

					}else{

						//Prompt to create a new semester instead
						 choice = JOptionPane.showConfirmDialog(null, "Create a new Semester Instead?", "Create Semester Option", JOptionPane.YES_NO_OPTION);

						 //Go to the addSemesterActions() method
						 //to perform the rest of the operation to create a new semester
						 if (choice == JOptionPane.YES_OPTION)
							 addSemesterActions();
					}

					//Set the frame title to the year's title
					mainFrame.setTitle(year.getYearTitle());

				}
			}else{
				JOptionPane.showMessageDialog(null, "No Year Available To Load!");
			}
		}else{
			JOptionPane.showMessageDialog(null, "No Year Available To Load!");
		}
	}

	/**
	 * Load all the available semesters into a combo box
	 * Then Prompt the user to select one to load
	 * Then set the thisSemester object to the found semester object
	 * Refresh the displays
	 * Add all the courses available within that semester into the main combo box
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

		JComboBox<String> semestersBox = new JComboBox<String>();

		if (year != null){	//Check if year exists
			if (year.getSemesterSize() > 0){	//Check if there are any semesters available

				for (int i = 0; i < year.getSemesterSize(); i++){
					//Add all the available semesters' names to the combo box
					semestersBox.addItem(year.getSemester(i).getName());
				}

				//Prompt to pick a semester to load
				int semesterChoice = JOptionPane.showConfirmDialog(null, semestersBox, "Choose a Semester to Load", JOptionPane.OK_CANCEL_OPTION);

				if (semesterChoice == JOptionPane.OK_OPTION){

					//Locate the semester's index based on the selected item from the combo box
					int semesterIndex = year.findSemesterIndex(String.valueOf(semestersBox.getSelectedItem()));

					//Set the object to the found Semester object
					thisSemester = year.getSemester(semesterIndex);

					//Set the Semester label to the semester name
					semesterLabel.setText(thisSemester.getName());

					//Refresh the display
					display();

					for (int i = 0; i < thisSemester.courseSize(); i++){
						//Add all the courses available in this semester to the comboBox
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

	/**
	 * Access the method to save any information within each class that are available
	 *
	 */
	public void save(){
		if (account != null){

			//Access the saveAccounts method to save any information available within each class
			account.saveAccounts(fileName);
			JOptionPane.showMessageDialog(null, "Saved Successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

		}else{
			JOptionPane.showMessageDialog(null, "Nothing To be Saved!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Load whatever is in the Accounts.txt file
	 * The reader looks for any matching tags and scan in it's values
	 * A tag with more than 1 values are separated by a "|"
	 * Each sections are separated using the .split() function
	 * Then those sections are passed into methods by it's index within the section's array
	 */
	public void loadAccounts(){
		try {
			String currentLine;
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader (fileName));
			String[] section;

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
		int courseIndex = thisSemester.findCourseIndex(String.valueOf(dropBox.getSelectedItem()));

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
			save();

			//Re-add the edited item in the drop down to a new name
			dropBox.removeItem(dropBox.getSelectedItem());
			dropBox.addItem(courseNameField.getText());



			//Go Back to Previous screen
			mainPanel.setVisible(true);
			editClassPanel.setVisible(false);

			//Update the display
			display();
			detailsDisplay(courseIndex);

		}else{

			//Warns that the values does not adds up to 100%
			valueWarningLbl.setVisible(true);
		}
	}

	/**
	 * Update the assignment with the newly edited information
	 */
	public void updateAssignmentActions(){
		String type, name = "";

		//Keep track of the selected course's index
		int courseIndex = thisSemester.findCourseIndex(String.valueOf(dropBox.getSelectedItem()));

		//Limit the characters of the assignment's name to only 11 characters
		if (assignField.getText().length() > 11){
			name = assignField.getText();
			name = name.substring(0, 11);
		}

		//Check for a selection of the radio buttons
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

		//Set the updated information
		thisSemester.getCourse(courseIndex).getAssignment(assignIndex).setAssignmentInfo(name, assignOnField.getText(), assignDueField.getText(), type, (Double) gradeSpin.getValue());

		//Re-save
		save();

		//Switch Panels
		mainPanel.setVisible(true);
		editAssignmentPanel.setVisible(false);

		//Refresh displays
		display();
		detailsDisplay(courseIndex);
	}

	/**
	 * Push information onto the text areas
	 * to show assignments, professor info, and textbook info
	 * @param index to locate a specific course to display it's available information
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
	 *Show the available courses along with it's letter grades, and number grades
	 */
	public void display(){
		String show = "          " + "Course:" + "\t" + "Average:" + "\n";
		if (thisSemester != null){
			for (int i = 0; i < thisSemester.courseSize(); i++){
				show += "          " + thisSemester.getCourse(i).getCourseName() + "\t" + thisSemester.getCourse(i).getCourseGrade() + " " + thisSemester.getCourse(i).getLetterGrade() + "\n";
			}
			show += "\n-------------\n"
					+ "GPA: " + thisSemester.getSemesterGPA()
					+ "\n-------------";
			mainDisplay.setText(show);
		}
	}

	/**
	 * Prompt the user for inputs to set the professor's info, textbook's info, and assignment's percentages
	 */
	public void addClass(){
		//METHOD NEEDS REFACTORING

		String className = "", profName, subject, email, phone, office, hours;
		String title = "", author = "", published = "", edition = "";
		int option;
		double hw, exam, project, lab, eElse;
		ArrayList<JTextField> fields = new ArrayList<JTextField>();

		//Create 6 JTextFields and add them to the array
		for (int i = 0; i < 6; i ++){
			fields.add(new JTextField());
		}

		//Object Array with Textfields and Strings
		Object[] txtFields1 ={
				"Professor's Name: ", fields.get(0),
				"Subject: ", fields.get(1),
				"Email: ", fields.get(2),
				"Phone: ", fields.get(3),
				"Office Room: ", fields.get(4),
				"Office Hours: ", fields.get(5)
		};

		//Keep prompting for a name if the field is empty
		while (className.isEmpty()){

			//Prompt for a course name
			className = JOptionPane.showInputDialog("Course Name: ");

			//If Cancelled
			if (className == null){
				break;

			}else if (className.length() > 15){

				//Limit the number of characters to 15
				//Will automatically exits out of loop since className is not empty
				className = className.substring(0,15);
			}
		}

		//If OK
		if (className != null){

			//Check for invalid characters
			while (true){

				//Prompt for the professor's information
				option = JOptionPane.showConfirmDialog(null, txtFields1, "Professor's Information", JOptionPane.OK_CANCEL_OPTION);

				if (option != JOptionPane.CANCEL_OPTION){

					//If any of these fields contain invalid character
					if (fields.get(0).getText().contains("|") || fields.get(1).getText().contains("|") || fields.get(2).getText().contains("|")
							||fields.get(3).getText().contains("|") || fields.get(4).getText().contains("|") || fields.get(5).getText().contains("|")){

						//Show message to let the user know and re-loop
						JOptionPane.showMessageDialog(null, "Field Contains Invalid Character '|' ");

					}else{
						break;
					}
				}else{
					break;
				}
			}

			//Nested loop to ensure that if one pane is canceled, exit out of it entirely
			//If OK
			if (option == JOptionPane.OK_OPTION){

				//Set the inputed values into variables
				//The spaces is to ensure that if the user does not enter anything, something will be saved
				profName = " " + fields.get(0).getText();
				subject = " " + fields.get(1).getText();
				email = " " + fields.get(2).getText();
				phone = " " + fields.get(3).getText();
				office = " " + fields.get(4).getText();
				hours = " " + fields.get(5).getText();

				//Onto the next prompt
				//Reset these textfields to reuse
				fields.get(0).setText("");
				fields.get(1).setText("");
				fields.get(2).setText("");
				fields.get(3).setText("");

				//Object fields array for the textbook
				Object[] txtFields2 ={
					"Title: ", fields.get(0),
					"Author: ", fields.get(1),
					"Published on: ", fields.get(2),
					"Edtion: ", fields.get(3)
				};

				while (true){

					//Prompt the user to enter the book's information
					option = JOptionPane.showConfirmDialog(null, txtFields2, "Book's Information", JOptionPane.OK_CANCEL_OPTION);

					if (option == JOptionPane.OK_OPTION){

						//Check for invalid characters
						if (fields.get(0).getText().contains("|") || fields.get(1).getText().contains("|") || fields.get(2).getText().contains("|") || fields.get(3).getText().contains("|")){

							//Let the user know, and re-loop
							JOptionPane.showMessageDialog(null, "Fields Contains Invalid Character '|' ");

						}else{	//Will exit loop if there are no invalid characters
							break;
						}
					}else{
						break;
					}
				}

				//If OK
				if (option == JOptionPane.OK_OPTION) {

					//Obtain the inputed information and set them to these variables
					//Spaces before the concatenation to ensure that something will be saved if the inputed fields are empty
					title = " " + fields.get(0).getText();
					author = " " +fields.get(1).getText();
					published = " " +fields.get(2).getText();
					edition = " " + fields.get(3).getText();

					//Onto the next prompt
					//Check to see if the percentages adds up to 1
					while (true){

						//Prompt the user to enter the assignment's percentages
						option = JOptionPane.showConfirmDialog(null, percentPanel(), "Assignment Percentage (%)", JOptionPane.OK_CANCEL_OPTION);

						if (option == JOptionPane.OK_OPTION) {
							hw = (Integer.parseInt((String) arrCombo.get(0).getSelectedItem())) / 100.0;
							exam = (Integer.parseInt((String) arrCombo.get(1).getSelectedItem())) / 100.0;
							project = (Integer.parseInt((String) arrCombo.get(2).getSelectedItem())) / 100.0;
							lab = (Integer.parseInt((String) arrCombo.get(3).getSelectedItem())) / 100.0;
							eElse = (Integer.parseInt((String) arrCombo.get(4).getSelectedItem())) / 100.0;

							if ((hw + exam + project + lab + eElse) != 1.0){

								warningLbl.setForeground(Color.ORANGE);
								warningLbl.setText("			Values Must Adds Up to 100%");

							}else{	//Everything will be set in stone if the user gets to this point

								thisSemester.addClass(new Courses(className));
								dropBox.addItem(className);
								thisSemester.getLastCourse().setProfInfo(profName, subject, email, phone, office, hours);
								thisSemester.getLastCourse().setTextInfo(title, author, published, edition);
								thisSemester.getLastCourse().setPercentage(exam, hw, eElse, lab, project);

								//Save those new information
								save();

								//Get out of loop
								break;
							}
						}else{
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * Prompt the user to enter information on the assignment
	 * @param index to locate a specific course to get the assignments of that course
	 */
	public void addAssignment(int index){
		String name = "";
		int option = 0;

		JTextField date = new JTextField(), due = new JTextField(), grade = new JTextField();
		JComboBox<String> type = new JComboBox<String>();

		//To Ensure Only the percentages that the User have chosen
		//will show up in the combo box when adding an assignment
		if (thisSemester.getCourse(index).homeworkPercent > 0){
			type.addItem("Homework");
		}if (thisSemester.getCourse(index).projectPercent > 0){
			type.addItem("Project");
		}if (thisSemester.getCourse(index).labPercent > 0){
			type.addItem("Lab");
		}if (thisSemester.getCourse(index).examPercent > 0){
			type.addItem("Exam");
		}if (thisSemester.getCourse(index).inClassPercent> 0){
			type.addItem("In Class");
		}

		Object[] fields ={
				"*Type:", type,
				"Date Assigned:", date,
				"Due On:", due,
				"*Grade Received:", grade
		};

		//Keep looping if the field is empty
		while (name.isEmpty()){

			//Prompt the user for the name of the assignment
			name = JOptionPane.showInputDialog("*Assignment: ");

			//If canceled, exit out of loop
			if (name == null){
				break;
			}else if (name.length() > 11){

				//Limit the number of characters to 11 ("Homework 10")
				//Will automatically exit out of loop since name is not empty
				name = name.substring(0, 11);
			}
		}

		if (name != null){	//If OK

			while(true){

				//Prompt the user to enter the assignment's information
				option = JOptionPane.showConfirmDialog(null, fields, "Assignment Information", JOptionPane.OK_CANCEL_OPTION);

				//(1) Test this case first
				if (option == JOptionPane.OK_OPTION){

					//If fields contains invalid characters or Grade is not a valid number
					if (date.getText().contains("|") || due.getText().contains("|") || grade.getText().equals("") || Double.parseDouble(grade.getText()) < 0 || grade.getText().contains("[a-zA-Z]+") == true){

						//Let the user know, and re-loop
						JOptionPane.showMessageDialog(null, "Grade is Not a Valid Number or Contains Invalid Character '|'");

					}else{
						break;
					}
				}else{
					break;
				}

			}

			//(2) Then this case
			if (option == JOptionPane.OK_OPTION){

				//Create new assignment with those values
				thisSemester.getCourse(index).addAssignment(new Assignments(name, date.getText(), due.getText(), (String) type.getSelectedItem(), Double.parseDouble(grade.getText())));

				//Save that new assignment
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



		//Has 3 rows and 1 column with a 5 pixel vertical gap
		JPanel backPanel = new JPanel(new GridLayout(3,1, 0, 5));

		//Has 1 row and 5 columns, with a 5 pixel horizontal gap, and 10 pixel vertical gap
		JPanel topPanel = new JPanel(new GridLayout(1,5,5,10));

		//Has 1 row and 5 columns, with a 5 pixel horizontal gap
		JPanel lowerPanel = new JPanel(new GridLayout(1,5,10,0));

		String[] type = {"Homework(%)", "Exam(%)", "Project(%)", "Lab(%)", "In Class(%)"};

		//Create 5 type's labels
		for (int i = 0; i < type.length; i++){
			JLabel typeLbl = new JLabel(type[i]);
			typeLbl.setForeground(Color.ORANGE);
			topPanel.add(typeLbl);
		}

		arrCombo = new ArrayList<JComboBox>();

		//Numbers to be displayed in the combo boxes
		String[] percentage = {"0","5", "10","15", "20","25", "30","35", "40","45", "50", "55", "60", "65", "70", "75", "80","85", "90","95", "100"};

		//Create 5 combo boxes
		for (int i = 0; i < 5; i++){
			JComboBox percBox = new JComboBox(percentage);
			percBox.setBackground(Color.LIGHT_GRAY);
			arrCombo.add(percBox);
		}

		//Add the combo boxes to the panel
		for (int i = 0; i < arrCombo.size(); i ++){
			lowerPanel.add((JComboBox) arrCombo.get(i));
		}

		//Layout these other panels and components onto the backPanel
		backPanel.add(topPanel);
		backPanel.add(lowerPanel);
		backPanel.add(warningLbl);

		return backPanel;

	}
} // CLASS
