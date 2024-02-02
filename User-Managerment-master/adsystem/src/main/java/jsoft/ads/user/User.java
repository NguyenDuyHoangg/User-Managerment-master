package jsoft.ads.user;

import java.util.*;
import org.javatuples.*;
import java.sql.*;
import jsoft.library.*;
import jsoft.objects.*;
import jsoft.ShareControl;
import jsoft.ads.basic.*;

public interface User extends ShareControl {
	// Các chức năng cập nhật
	public boolean addUser(UserObject item);

	public boolean editUser(UserObject item, EDIT_TYPE et);

	public boolean delUser(UserObject item);

	// Các chức năng lấy dữ liệu
	public ResultSet getUsers(int id);
	public ResultSet getUsers(String username, String userpass);
	// public ResultSet getUsers(UserObject similar, int at, byte total);

	// public ResultSet getUsers(UserObject similar, Integer at , Byte total);

	public ArrayList<ResultSet> getUsers(Quartet<UserObject, Integer, Byte, Boolean> infos, Pair<USER_SORT, ORDER> so);

//	public ArrayList<ResultSet> getUsers(Quartet<UserObject, Integer, Byte, USER_SORT> infos);
}
