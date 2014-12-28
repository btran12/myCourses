import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;


public class UserProfile {
	private String username, password, id, fullname;
	private ArrayList<Year> arrYear = new ArrayList<Year>();
	
	public UserProfile(String uname, String pword, String _fullname, String _id ) {
		username = uname;
		password = pword;
		id = _id;
		fullname = _fullname;
	}
	
	public UserProfile(){
		
	}
	
	public int findYear(String year){
		return arrYear.indexOf(year);
	}
	public void addYear(Year year){
		arrYear.add(year);
	}
	
	public int getYearSize(){
		return arrYear.size();
	}
	
	public double getCollegeGPA(){
		double collegeGPA = 0.0;
		for (int i = 0; i < arrYear.size(); i++){
			collegeGPA += arrYear.get(i).getYearGPA();
		}
		return Math.round(collegeGPA / arrYear.size() * 100.0) / 100.0 ;
	}
	public Year getYear(int index){
		return arrYear.get(index);
	}
	public Year getLastYear(){
		return arrYear.get(arrYear.size()-1);
	}
	public String getUsername() {
		return username;
	}
	
	public String getFullName(){
		return fullname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String profileSaveFormat(){
		String str = username + "|" + password + "|" + fullname + "|" + id;
		return str;
	}
	
	public void saveProfile(String fileName){
		
		try{
			FileOutputStream fos = new FileOutputStream (fileName + ".txt",true);
			OutputStreamWriter save = new OutputStreamWriter (fos);
				save.write("PROFILE:" +profileSaveFormat() +"\n");
				save.close();
				
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Unable to save College!", "Saves Error", JOptionPane.WARNING_MESSAGE);
		}
		
		Iterator<Year> it = arrYear.iterator();
		while (it.hasNext()){ 
			it.next().saveYear(fileName);
		}
	}

}
