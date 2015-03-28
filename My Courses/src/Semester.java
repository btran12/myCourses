import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;
/**
 * This class retains information for each semester
 * Be able to create, remove, and find courses
 * @author Bao Tran
 */
public class Semester {
	private String semesterTitle;
	private ArrayList<Courses> arrClasses = new ArrayList<Courses>();
	
	/**
	 * Class contructor
	 * @param _name this is the title of the semester
	 */
	public Semester(String _name) {
		semesterTitle = _name;
	}
	
	/**
	 * Remove a course specified by an index
	 * @param index used to locate a specific course to be removed
	 */
	public void removeCourse(int index){
		arrClasses.remove(index);
	}
	
	/**
	 * Used to keep track of classes by adding them to an array
	 * @param _class a Classes object to be added to the array
	 */
	public void addClass(Courses _class){ //Add class object
		arrClasses.add(_class);		
	}

	
	/**
	 * Used to obtain a specific course as specified by an index 
	 * @param i an index use to locate an element within the arrClasses array
	 * @return the Classes that is specified by the index
	 */
	public Courses getCourse(int i){
		return arrClasses.get(i);
	}
	
	/**
	 * Find a course index based on it's name
	 * @param course the name of the course
	 * @return the course location within the array
	 */
	public int findCourseIndex(String course) {
		for (int i = 0; i < arrClasses.size(); i++){
			if (course.equals(arrClasses.get(i).getCourseName())){
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * 
	 * @return the number of courses within the array
	 */
	public int courseSize(){
		return arrClasses.size();
	}
	
	/**
	 * Used mainly to load the last course that was saved in the array
	 * @return the last element within the array
	 */
	public Courses getLastCourse(){
		
		return arrClasses.get(arrClasses.size() -1); //Get the last saved object
	}
	
	/**
	 * 
	 * @return the semester title
	 */
	public String getName() {
		return semesterTitle;
	}
	
	/**
	 * Calculate the average of all the courses within the array
	 * @return the GPA of the semester in a 4.0 format
	 */
	public double getSemesterGPA() {
		double gpa = 0.0;
		
		for (int i = 0; i < arrClasses.size(); i++){
			gpa += arrClasses.get(i).getCourseGrade();
		}
		
		return Math.round(gpa / arrClasses.size() * 0.04 *100.0) / 100.0;
	}
	
	/**
	 * The display format of this Semester 
	 * Used in the Information Menu
	 * Including the semester title, and all the course plus the GPA
	 * @return a formatted display string of this semester
	 */
	public String semesterInfo(){
		String info = "";
		
		for (int x = 0; x < arrClasses.size(); x ++){
			info += "\t" + arrClasses.get(x).getCourseName() + "\t" + arrClasses.get(x).getProfName()+ "\t\t" + arrClasses.get(x).getCourseGrade() + " | " + arrClasses.get(x).getLetterGrade()+ "\n" ;
		}
		
		return info;
			
	}
	
	/**
	 * Save in a hierarchy style
	 * Where this Semester class will bump the Courses class to save
	 * @param fileName location of the file to be saved
	 */
	public void saveSemester(String fileName){
		
		try{
			FileOutputStream fos = new FileOutputStream (fileName , true);	//Change to True once get the Year class working 
			OutputStreamWriter save = new OutputStreamWriter (fos);
				save.write("SEMESTER:" +semesterTitle +"\n");
				save.close();
				
		}catch(Exception e){
			JOptionPane.showMessageDialog (null, "Unable to save Semester", "Saves Error", JOptionPane.WARNING_MESSAGE);
		}
		
		Iterator<Courses> it = arrClasses.iterator();
		while (it.hasNext()){ //While there is still a value within the classes array, save it
			it.next().saveClass(fileName);
		}
	}

	
}
