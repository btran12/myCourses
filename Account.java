

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class Account {

	ArrayList<UserProfile> arrProfile = new ArrayList<UserProfile>();
	
	
	public Account() {
		//Do NOthing
	}
	
	public void addUser(UserProfile profile){
		arrProfile.add(profile);
	}
	
	public UserProfile getLastProfile(){
		return arrProfile.get(arrProfile.size()-1);
	}
	
	public UserProfile getProfile(int i){
		return arrProfile.get(i);
	}
	
	public int getSize(){
		return arrProfile.size();
	}
	
	public void saveAccounts(String fileName){
		
		try{
			FileOutputStream fos = new FileOutputStream (fileName + ".txt",false);
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
