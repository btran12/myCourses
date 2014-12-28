import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author Bao Tran
 *This class is at the top of the hierarchy
 *It's purpose is to store multiple semesters and retrieve information from multiple semesters
 */
public class Year {
	private String schoolYear;
	private ArrayList<Semester> arrSemester = new ArrayList<Semester>();
	/**
	 * Class constructor
	 * @param _schoolYear used as the year title
	 */
	public Year(String _schoolYear) {
		schoolYear = _schoolYear;
	}

	/**
	 * Add many semesters together into an array
	 * @param semester a semester object, to be added to an array of semesters
	 */
	public void addSemester(Semester semester){
		arrSemester.add(semester);
	}

	/**
	 * To remove a particular semester within the array as specified by the index
	 * @param i the index used to find the semester within the array
	 */
	public void removeSemester(int i){
		arrSemester.remove(i);
	}

	/**
	 * Calculate average gpa from both years
	 * @return a gpa from both semesters, in a 4.0 format
	 */
	public double getYearGPA(){
		double yearGPA = 0.0;
		for (int i = 0; i < arrSemester.size(); i++){
			yearGPA += arrSemester.get(i).getSemesterGPA();
		}

		return Math.round(yearGPA/arrSemester.size() * 100.0)/100.0;
	}

	/**
	 *
	 * @return the number of semesters within the array
	 */
	public int getSemesterSize(){
		return arrSemester.size();
	}

	/**
	 *
	 * @return the last semester within the array
	 */
	public Semester getLastSemester(){
		return arrSemester.get(arrSemester.size() - 1);
	}

	/**
	 *
	 * @param i an index used to locate a semester within the array
	 * @return a specific semester as specified by an index i
	 */
	public Semester getSemester(int i){
		return arrSemester.get(i);
	}

	/**
	 *
	 * @return the year title
	 */
	public String getYearTitle(){
		return schoolYear;
	}

	/**
	 * Used to save a line of text into a .txt file
	 * This method saves in a hierarchy style
	 * Where this class will bump to save the next class of strings
	 * @param fileName a location where the file can be saved
	 */
	public void saveYear(String fileName){

		try{
			FileOutputStream fos = new FileOutputStream (fileName + ".txt",true);
			OutputStreamWriter save = new OutputStreamWriter (fos);
				save.write("YEAR:" +schoolYear +"\n");
				save.close();

		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Unable to save Year", "Saves Error", JOptionPane.WARNING_MESSAGE);
		}

		Iterator<Semester> it = arrSemester.iterator();
		while (it.hasNext()){ //While there is still a value within the classes array, save it
			it.next().saveSemester(fileName);
		}
	}

}
