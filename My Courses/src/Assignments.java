import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import javax.swing.JOptionPane;

/**
 * This class collect information on each assignment
 * @author Bao Tran
 */
public class Assignments {
	private String name, date, dueDate, type;
	private double grade;
	
	/**
	 * class constructor used to instantiate the class object
	 * @param _name the name of each assignment
	 */
	public Assignments(String _name) {
		name = _name;	
	}
	
	/**
	 * An alternative constructor with more arguments
	 * @param _name name of the assignment
	 * @param _date date of when the assignment was assigned
	 * @param _due date of when the assignment is due
	 * @param _type the type of assignment: Homework, Exam, ..
	 * @param _grade the grade received for that assignment
	 */
	public Assignments( String _name, String _date, String _due, String _type, double _grade){
		name = _name;
		date = _date;
		dueDate = _due;
		type = _type;
		grade = _grade;
		
	}
	
	/**
	 * 
	 * @return the name of the assignment
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 
	 * @return the type of the assignment
 	 */
	public String getType(){
		return type;
	}
	
	/**
	 * 
	 * @return the grade received for that assignment
	 */
	public double getGrade(){
		return grade;
	}
	
	/**
	 * @return a formatted display string of the assignment
	 */
	public String toString(){
		String toString;
		toString = name + "\t" + "[" + type +"]" +"\t" + date + "\t" + dueDate + "\t" + grade;
					
		return toString;
	}
	
	/**
	 * Update the previous assignment's information with new information
	 * @param _name name of the assignment
	 * @param _date	the date on which the assignment was assigned
	 * @param _dueDate the date on which the assignment is due
	 * @param _type the type of assignment it is
	 * @param _grade the grade you received for the assignment
	 */
	public void setAssignmentInfo(String _name, String _date, String _dueDate, String _type, double _grade){
		name = _name;
		date = _date;
		dueDate = _dueDate;
		type = _type;
		grade = _grade;
	}
	
	/**
	 * Get the date on which the assignment was assigned
	 * @return that date on which the assignment was assigned on
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Get the date on which it is or was due
	 * @return date on which the assignment is due
	 */
	public String getDue() {
		return dueDate;
	}

	/**
	 * 
	 * @return a formatted string for saving
	 */
	private String saveAssignmentFormat(){
		return name + "|" + date + "|" + dueDate + "|" + type + "|" + grade; 
	}
	
	/**
	 * Last of the hierarchy style order of saving
	 * @param fileName location of the file to be saved
	 */
	public void saveAssignment(String fileName) {
		try{
			FileOutputStream fos = new FileOutputStream (fileName , true);
			OutputStreamWriter save = new OutputStreamWriter (fos);
				save.write("HW:" + saveAssignmentFormat() + "\n");
				save.close();
				
		}catch(Exception e){
			JOptionPane.showMessageDialog (null, "Unable to save Assignment", "Saves Error", JOptionPane.WARNING_MESSAGE);
		}
		
	}

}
