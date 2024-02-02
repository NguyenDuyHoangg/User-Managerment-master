package jsoft.ads.user;

import jsoft.*;
import jsoft.library.ORDER;
import jsoft.objects.*;

import java.util.*;
import org.javatuples.*;

public class UserControl {
	private UserModel um;

	public UserControl(ConnectionPool cp) {
		this.um = new UserModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.um.getCP();
	}

	public void releaseConnection() {
		this.um.releaseConnection();
	}

	// ------------------------------------------
	public boolean addUser(UserObject item) {
		return this.um.addUser(item);
	}

	// ------------------------------------------
	public boolean editUser(UserObject item, EDIT_TYPE et) {
		return this.um.editUser(item, et);
	}

	// ------------------------------------------
	public boolean delUser(UserObject item) {
		return this.um.delUser(item);
	}
	
	public UserObject getUserObject(int id) {
		return this.um.getUserObject(id);
	}
	
	public UserObject getUserObject(String username, String userpass) {
		return this.um.getUserObject(username, userpass);
	}
	
	//---------------------------------------------
	public Pair<String, String> viewUsers(Quartet<UserObject, Integer, Byte, Boolean> infos,Pair<USER_SORT, ORDER> so){
		Pair<ArrayList<UserObject>, Short> datas = this.um.getUserObject(infos, so);// dữ liệu lấy trong model lấy ra 
		
		if(infos.getValue3()) {
			return UserLibrary.viewTrashUser(datas.getValue0());
		}else {
			return UserLibrary.viewUser(datas, infos);
		}
		
	}
	
}
