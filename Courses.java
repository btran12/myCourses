import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;
/**
 * 
 * @author Bao Tran
 *This class keep tracks of all the assignments created by the user
 */
public class Courses {
	
	private String courseName, profInfo, textInfo, profName, subject, email, phone, office, officeHours, title, author, datePublished, edition;
	
	private ArrayList<Assignments> arrAssignments = new ArrayList<Assignments>(); //This stores array of assignments
	
	public double examPercent, homeworkPercent, inClassPercent, labPercent, projectPercent;
	
	/**
	 * Constructor used to initialize the Courses object
	 * @param _name used to name the course
	 */
	public Courses(String _name) {
		courseName = _name;
	}
	
	/**
	 * 
	 * @return the name of the cousre
	 */
	public String getCourseName(){
		return courseName;
	}
	
	public void setCourseName(String _name) {
		courseName = _name;
	}

	/**
	 * Used to keep track of all the assignments by adding them to an array of Assignments
	 * @param assignment an Assignments object to be added to the array
	 */
	public void addAssignment(Assignments assignment){
		arrAssignments.add(assignment);
	}
	
	/**
	 * To get a specfic assignment within the array as specified by the index
	 * @param index an index used to locate an assignment within the array
	 * @return an Assignments object
	 */
	public Assignments getAssignment(int index){
		return arrAssignments.get(index);
	}
	
	/**
	 * Remove an assignment as specified by the index
	 * @param index an index used to locate an assignment within the array
	 */
	public void removeAssignment(int index) {
		arrAssignments.remove(index);
	}
	
	/**
	 * Find the index of an assignment within the array based on it's name
	 * @param assignment the assignment name to find
	 * @return the index or location of the assignment within the array
	 */
	public int findAssignment(String assignment) {
		for (int i = 0; i < arrAssignments.size(); i++){
			if (assignment.equals(arrAssignments.get(i).getName())){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Used to instantiate the professors information variables
	 * @param _name	name of the professor
	 * @param _subject subject that they teaches
	 * @param _email email of the professor
	 * @param _phone professor phone number
	 * @param _office professor office
	 * @param _officeHours professor office hours
	 */
	public void setProfInfo(String _name, String _subject, String _email, String _phone,String _office, String _officeHours){
		profName = _name;
		subject = _subject;
		email = _email;
		phone = _phone;
		office = _office;
		officeHours = _officeHours;
	}
	
	/**
	 * 
	 * @return the name of the professor
	 */
	public String getProfName(){
		return profName;
	}

	public String getSubject() {
		return subject;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getOffice() {
		return office;
	}

	public String getOfficeHours() {
		return officeHours;
	}

	/**
	 * A formatted display string of the professor information
	 * @return a formatted string of professor info
	 */
	public String getProfInfo(){
		profInfo = 	"Name: " + profName +
					"\nSubject: " + subject +
					"\nEmail: " + email +
					"\nPhone: " + phone +
					"\nRoom: " + office +
					"\nOffice Hours: " + officeHours;
		return profInfo;
	}
	
	/**
	 * Used to instantiate the textbook variables
	 * @param _title title of the book
	 * @param _author the author of the book
	 * @param _datePublished when the book was published
	 * @param _edition the edition of the book
	 */
	public void setTextInfo(String _title, String _author, String _datePublished, String _edition){
		title = _title;
		author = _author;
		datePublished = _datePublished;
		edition = _edition;
	}
	
	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getDatePublished() {
		return datePublished;
	}

	public String getEdition() {
		return edition;
	}

	public double getExamPercent() {
		return examPercent;
	}

	public double getHomeworkPercent() {
		return homeworkPercent;
	}

	public double getInClassPercent() {
		return inClassPercent;
	}

	public double getLabPercent() {
		return labPercent;
	}

	public double getProjectPercent() {
		return projectPercent;
	}

	/**
	 * A formatted display string of the textbook information
	 * @return a formatted string of textbook info
	 */
	public String getTextInfo(){
		textInfo = "Title: " + title +
					"\n Author: " + author +
					"\n Published On: " + datePublished +
					"\n Edition: " + edition;
					
		return textInfo;
	}
	
	/**
	 * A formatted string used to save the professor info
	 * @return a formatted string that can later be easily obtained again
	 */
	private String profSaveFormat(){
		return profName + "|" + subject + "|" + email + "|" + phone + "|" + office + "|" + officeHours;
	}

	/**
	 * A formatted string used to save the textbook info
	 * @return a formatted string that can later be easily obtained again
	 */
	private String bookSaveFormat(){
		return title + "|" + author + "|" + datePublished + "|" + edition;
	}
	
	/**
	 * Allow the user to specified the percentage of each assignments of the class
	 * @param exam Weighted percent of each exam
	 * @param homework Weighted percent of each homework assignments
	 * @param inClass Weighted percent of each in class assignments
	 * @param lab Weighted percent of each lab
	 * @param project Weighted percent of each project
	 */
	public void setPercentage(double exam, double homework, double inClass, double lab, double project){
		examPercent = exam;	//Convert from percentage to decimal;
		homeworkPercent = homework;
		inClassPercent = inClass;
		labPercent = lab;
		projectPercent = project;
	}
	
	/**
	 * A formatted string of percentages used for saving and retrieving
	 * @return a formatted string of the percentages 
	 */
	private String percentageSaveFormat(){
		return examPercent + "|" + homeworkPercent + "|" + inClassPercent + "|" + labPercent + "|" + projectPercent;
	}
	
	/**
	 * Calculate the average grade for each assignments, then weight them
	 * Will only calculate the types of grades that were specified by the user 
	 * @return a weighted average course grade
	 */
	public double getCourseGrade(){
		double courseGrade = 0.0;
		double hw = 0.0, exam = 0.0, project = 0.0, lab = 0.0, eElse = 0.0;
		double examPer = 0.0 , hwPer = 0.0, inClassPer = 0.0 , labPer = 0.0, projectPer = 0.0;
		int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0;
		
		for (int i = 0; i < arrAssignments.size(); i++){
			String type = arrAssignments.get(i).getType();	//Obtain the type for the assignments 
			
			//Check and set the percentages of each assignment according to its type
			if (type.equals("Homework")){
				hw += arrAssignments.get(i).getGrade();		//Get all the available homework grades 
				hwPer = homeworkPercent;
				
				count1++;
			}if (type.equals("Exam")){
				exam += arrAssignments.get(i).getGrade();	//Get all the available exam grades 
				examPer = examPercent;
				
				count2++;
			}if (type.equals("Project")){
				project += arrAssignments.get(i).getGrade();	//Get all the available project grades 
				projectPer = projectPercent;
				
				count3++;
			}if (type.equals("Lab")){
				lab += arrAssignments.get(i).getGrade();	//Get all the available lab grades
				labPer = labPercent;
				
				count4++;
			}if (type.equals("In Class")){
				eElse += arrAssignments.get(i).getGrade();	//Get all the available in class grades 
				inClassPer = inClassPercent;
				
				count5++;
			}			
		}
		
		//Get the average of each category
		//Will only do the calculations when the counts are not 0 (Cannot divide by 0)
		if (count1 != 0){
			hw /= count1; 
		}if (count2 != 0){
			exam /= count2;
		}if (count3 != 0){
			project /= count3;
		}if (count4 != 0){
			lab /= count4; 
		}if (count5 != 0){
			eElse /= count5;
		}

		//Weight it with the given percentage
		courseGrade = ((hw*hwPer)+(exam*examPer)+(project*projectPer)+(lab*labPer)+(eElse*inClassPer))/(hwPer+examPer+projectPer+labPer+inClassPer);
		return Math.round(courseGrade* 100.0)/100.0;
	}
	
	/**
	 * Take the course grade and use it to generate a letter grade
	 * @return a letter grade from A to F
	 */
	public String getLetterGrade(){
		double grade = getCourseGrade();
		if (grade >= 94){
			return "A";
		}else if (grade >= 90){
			return "A-";
		}else if (grade >= 87){
			return "B+";
		}else if (grade >= 83){
			return "B";
		}else if (grade >= 80){
			return "B-";
		}else if (grade >= 77){
			return "C+";
		}else if (grade >= 73){
			return "C";
		}else if (grade >= 70){
			return "C-";
		}else if (grade >= 67){
			return "D+";
		}else if (grade >= 60){
			return "D";
		}else{
			return "F";
		}
	}
	
	/**
	 * Get the number of assignments 
	 * @return the number of assignments created
	 */
	public int assignmentSize(){
		return arrAssignments.size();
	}
	
	/**
	 * Save in hierarchy style where the Courses class will bump to Assignments class to save
	 * @param fileName file location
	 */
	public void saveClass(String fileName) {
		try{
			FileOutputStream fos = new FileOutputStream (fileName + ".txt",true);
			OutputStreamWriter save = new OutputStreamWriter (fos);
				save.write("CLASS:" + courseName + "\n"+
							"PROF:" + profSaveFormat() + "\n" +
							"TEXT:" + bookSaveFormat() + "\n" +
							"PERC:" + percentageSaveFormat() + "\n");
				
				save.close();
				
		}catch(Exception e){
			JOptionPane.showMessageDialog (null, "Unable to save Class", "Saves Error", JOptionPane.WARNING_MESSAGE);
		}
		
		Iterator<Assignments> it = arrAssignments.iterator();
		while (it.hasNext()){ //While there is still a value within the assignments array, save it
			it.next().saveAssignment(fileName);
		}
		
	}
	

}
