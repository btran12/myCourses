import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

/**
 * 
 * @author Bao Tran
 *Store the year classes, and initiate its functions
 */
public class UserProfile {
	private String username, password, id, fullname;
	private ArrayList<Year> arrYear;
	
	/**
	 * Class constructor, used to instantiate the class's object and arrYear array
	 * @param _username provide a username
	 * @param _password provide a password
	 * @param _fullname the student's full name
	 * @param _id the student's id number
	 */
	public UserProfile(String _username, String _password, String _fullname, String _id ) {
		username = _username;
		password = _password;
		id = _id;
		fullname = _fullname;
		arrYear = new ArrayList<Year>();
	}
	
	/**
	 * Add year object into the arrYear array
	 * @param year the year object to add into the array
	 */
	public void addYear(Year year){
		arrYear.add(year);
	}
	
	/**
	 * Get a number of years that had been created
	 * @return how many elements or years are there within the array
	 */
	public int getYearSize(){
		return arrYear.size();
	}
	
	/**
	 * Get a cumulative GPA from all the years available
	 * @return a grade that is out of 4
	 */
	public double getCollegeGPA(){
		double collegeGPA = 0.0;
		for (int i = 0; i < arrYear.size(); i++){
			collegeGPA += arrYear.get(i).getYearGPA();
		}
		return Math.round(collegeGPA / arrYear.size() * 100.0) / 100.0 ;
	}
	
	/**
	 * Find a specific year based on the index
	 * @param index used to locate a Year object within the array
	 * @return that found Year object
	 */
	public Year getYear(int index){
		return arrYear.get(index);
	}
	
	/**
	 * Locate and return the index of the specified year
	 * @param year the name to locate the year from
	 * @return the index, or location of that year within the arrYear array
	 */
	public int findYear(String year) {
		for (int i = 0; i < arrYear.size(); i++){
			//Compare the strings to find the matching year
			if (year.equals(arrYear.get(i).getYearTitle())){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Get the last year that was added to the array
	 * @return the last element or year object within the array
	 */
	public Year getLastYear(){
		return arrYear.get(arrYear.size()-1);
	}
	
	/**
	 * 
	 * @return the username of the account
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * 
	 * @return the registered name of the account
	 */
	public String getFullName(){
		return fullname;
	}
	
	/**
	 * Set the username for the account
	 * @param username the username to be registered into the account
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 
	 * @return the password that was registered for the account
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Set the password for the account
	 * @param password the password to be registered into the account
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * A string format to be saved into the text file
	 * @return that formatted string
	 */
	public String profileSaveFormat(){
		String str = username + "|" + password + "|" + fullname + "|" + id;
		return str;
	}
	
	/**
	 * Save all the available accounts into the file
	 * @param fileName the location of where to save the information
	 */
	public void saveProfile(String fileName){
		
		try{
			FileOutputStream fos = new FileOutputStream (fileName + ".txt",true);
			OutputStreamWriter save = new OutputStreamWriter (fos);
				save.write("PROFILE:" +profileSaveFormat() +"\n");
				save.close();
				
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Unable to Save Profile!", "Saves Error", JOptionPane.WARNING_MESSAGE);
		}
		
		Iterator<Year> it = arrYear.iterator();
		while (it.hasNext()){ 
			it.next().saveYear(fileName);
		}
	}
	
}
