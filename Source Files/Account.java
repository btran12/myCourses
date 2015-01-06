

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 * Track registered UserProfiles
 * @author Bao Tran
 */
public class Account {

	private ArrayList<UserProfile> arrProfile;
	
	/**
	 * Class constructor, used to instantiate the class object
	 */
	public Account() {
		arrProfile = new ArrayList<UserProfile>();
	}
	
	/**
	 * Add UserProfile object into the arrProfile array
	 * @param profile the UserProfile object
	 */
	public void addUser(UserProfile profile){
		arrProfile.add(profile);
	}
	
	/**
	 * Get the last account in the array
	 * @return that last element or account
	 */
	public UserProfile getLastProfile(){
		return arrProfile.get(arrProfile.size()-1);
	}
	
	/**
	 * Get a specific element or account within the array based on the provided index
	 * @param i the index used to find a specific account or element
	 * @return that account/element at that index
	 */
	public UserProfile getProfile(int i){
		return arrProfile.get(i);
	}

	/**
	 * Get the size of the array or how many accounts are there
	 * @return the size of the array
	 */
	public int getSize(){
		return arrProfile.size();
	}
	
	/**
	 * Save a simple string to instantiate the object later when load
	 * @param fileName the location of where to save the file
	 */
	public void saveAccounts(String fileName){
		
		try{
			FileOutputStream fos = new FileOutputStream (fileName ,false);
			OutputStreamWriter save = new OutputStreamWriter (fos);
				save.write("ACCOUNT:" + "myCourses Accounts" +"\n");
				save.close();
				
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Unable to save Accounts!", "Saves Error", JOptionPane.WARNING_MESSAGE);
		}
		
		Iterator<UserProfile> it = arrProfile.iterator();
		while (it.hasNext()){ 	//While there's still a profile in the array, save it. 
			it.next().saveProfile(fileName);
		}
	}
}
