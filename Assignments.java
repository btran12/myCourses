import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import javax.swing.JOptionPane;

/**
 * 
 * @author Bao Tran
 *This class outline the details of each assignment
 */
public class Assignments {
	private String name, date, due, type;
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
		due = _due;
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
		toString = name + "\t" + "[" + type +"]" +"\t" + date + "\t" + due + "\t" + grade;
					
		return toString;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setDue(String due) {
		this.due = due;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public String getDate() {
		return date;
	}

	public String getDue() {
		return due;
	}

	/**
	 * 
	 * @return a formatted string for saving
	 */
	private String saveAssignmentFormat(){
		return name + "|" + date + "|" + due + "|" + type + "|" + grade; 
	}
	
	/**
	 * Last of the hierarchy style order of saving
	 * @param fileName location of the file to be saved
	 */
	public void saveAssignment(String fileName) {
		try{
			FileOutputStream fos = new FileOutputStream (fileName +".txt",true);
			OutputStreamWriter save = new OutputStreamWriter (fos);
				save.write("HW:" + saveAssignmentFormat() + "\n");
				save.close();
				
		}catch(Exception e){
			JOptionPane.showMessageDialog (null, "Unable to save Assignment", "Saves Error", JOptionPane.WARNING_MESSAGE);
		}
		
	}

}
