package jsoft.ads.user;

import java.sql.*;

import org.javatuples.*;

import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.ads.basic.*;
import jsoft.library.ORDER;
import jsoft.objects.*;

import java.util.*;

public class UserImpl extends BasicImpl implements User {

	public UserImpl(ConnectionPool cp) {
		super(cp, "User");// tuân theo lớp cha(BasicImpl)
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addUser(UserObject item) {
		// TODO Auto-generated method stub

		if (this.isExitsting(item)) {
			return false;
		}

		StringBuilder sql = new StringBuilder();// dữ nguyên tham chiếu(không cần phải tham chiếu nhiều lần)
		sql.append("INSERT INTO tbluser(");
		sql.append("user_name, user_pass, user_fullname, user_birthday, user_mobilephone, ");
		sql.append("user_homephone, user_officephone, user_email, user_address, user_jobarea, ");
		sql.append("user_job, user_position, user_applyyear, user_permission, user_notes, ");
		sql.append("user_roles, user_logined, user_created_date, user_last_modified, user_last_logined, ");
		sql.append("user_parent_id, user_actions");
		sql.append(") ");
		sql.append("VALUES(?,md5(?),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		// biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getUser_name());
			pre.setString(2, item.getUser_pass());
			pre.setString(3, item.getUser_fullname());
			pre.setString(4, item.getUser_birthday());
			pre.setString(5, item.getUser_mobilephone());
			pre.setString(6, item.getUser_homephone());
			pre.setString(7, item.getUser_officephone());
			pre.setString(8, item.getUser_email());
			pre.setString(9, item.getUser_address());
			pre.setString(10, item.getUser_jobarea());
			pre.setString(11, item.getUser_job());
			pre.setString(12, item.getUser_position());
			pre.setShort(13, item.getUser_applyyear());
			pre.setByte(14, item.getUser_permission());
			pre.setString(15, item.getUser_notes());
			pre.setString(16, item.getUser_roles());
			pre.setShort(17, item.getUser_logined());
			pre.setString(18, item.getUser_created_date());
			pre.setString(19, item.getUser_last_modified());
			pre.setString(20, item.getUser_last_logined());
			pre.setInt(21, item.getUser_id());
			pre.setByte(22, item.getUser_actions());

			return this.add(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				// trả về trạng thái an toàn
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return false;
	}

	private boolean isExitsting(UserObject item) {
		boolean flag = false;
		String sql = "SELECT user_id FROM tbluser WHERE user_name = '" + item.getUser_name() + "' ";
		ResultSet rs = this.get(sql, 0);
		if (rs != null) {
			try {
				if (rs.next()) {
					flag = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;
	}

	@Override
	public boolean editUser(UserObject item, EDIT_TYPE et) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();// dữ nguyên tham chiếu(không cần phải tham chiếu nhiều lần)
		switch (et) {
		case TRASH:
			sql.append("UPDATE tbluser SET user_trash_id=?, user_last_modified=? ");
			sql.append("WHERE user_id=? ");
			break;
		default:
			sql.append("UPDATE tbluser SET ");
			sql.append("user_fullname=?, user_birthday=?, user_mobilephone=?, ");
			sql.append("user_homephone=?, user_officephone=?, user_email=?, user_address=?, user_jobarea=?,");
			sql.append("user_job=?, user_position=?, user_applyyear=?, user_notes=?, ");
			sql.append("user_last_modified=? ");
			sql.append("WHERE user_id=? ");
		}

		// biên dịch
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());

			switch (et) {
			case TRASH:
				pre.setInt(1, item.getUser_trash_id());
				pre.setString(2, item.getUser_last_modified());
				pre.setInt(3, item.getUser_id());
				break;
			default:
				pre.setString(1, item.getUser_fullname());
				pre.setString(2, item.getUser_birthday());
				pre.setString(3, item.getUser_mobilephone());
				pre.setString(4, item.getUser_homephone());
				pre.setString(5, item.getUser_officephone());
				pre.setString(6, item.getUser_email());
				pre.setString(7, item.getUser_address());
				pre.setString(8, item.getUser_jobarea());
				pre.setString(9, item.getUser_job());
				pre.setString(10, item.getUser_position());
				pre.setShort(11, item.getUser_applyyear());
				pre.setString(12, item.getUser_notes());
				pre.setString(13, item.getUser_last_modified());
				pre.setInt(14, item.getUser_id());
			}

			return this.edit(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				// trả về trạng thái an toàn
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public boolean delUser(UserObject item) {
		// TODO Auto-generated method stub

		if (!this.isEmpty(item)) {
			return false;
		}
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tbluser WHERE user_id=?");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setInt(1, item.getUser_id());
			return this.del(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}

//	private boolean isEmpty(UserObject item) {
//		boolean flag = true;
//		StringBuilder sql = new StringBuilder();
//		sql.append("SELECT article_id FROM tblarticle WHERE article_author_name='").append(item.getUser_name())
//				.append("' ;");
//		sql.append("SELECT product_id FROM tblproduct WHERE product_manager_id=" + item.getUser_id() + "; ");
//		sql.append("SELECT user_id FROM tbluser WHERE user_parent_id=" + item.getUser_id() + "; ");
//
//		ArrayList<ResultSet> res = this.getsMR(sql.toString());
//		for (ResultSet rs : res) {
//			if (rs != null) {
//				try {
//					if (rs.next()) {
//						flag = false;
//						break;
//					}
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		return flag;
//	}

	// thử khái quát hóa
	private boolean isEmpty(Object item) {
		boolean flag = true;
		StringBuilder sql = new StringBuilder();
		if (item instanceof UserObject) {
			sql.append("SELECT article_id FROM tblarticle WHERE article_author_name='")
					.append(((UserObject) item).getUser_name()).append("' ;");
			sql.append("SELECT product_id FROM tblproduct WHERE product_manager_id=" + ((UserObject) item).getUser_id()
					+ "; ");
			sql.append("SELECT user_id FROM tbluser WHERE user_parent_id=" + ((UserObject) item).getUser_id() + "; ");
		} else if (item instanceof SectionObject) {
			sql.append("SELECT category_id FROM tblcategory WHERE category_section_id=?")
					.append(((SectionObject) item).getSection_id());
		}
		ArrayList<ResultSet> res = this.getsMR(sql.toString());
		for (ResultSet rs : res) {
			if (rs != null) {
				try {
					if (rs.next()) {
						flag = false;
						break;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	@Override
	public ResultSet getUsers(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbluser WHERE user_id=?";
		return this.get(sql, id);
	}

	// phương thức đăng nhập
	@Override
	public ResultSet getUsers(String username, String userpass) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbluser WHERE(user_name=?) AND (user_pass=md5(?))";
		return this.get(sql, username, userpass);
	}

	// lấy ra các giá trị đối tượng
	@Override
	public ArrayList<ResultSet> getUsers(Quartet<UserObject, Integer, Byte, Boolean> infos, Pair<USER_SORT, ORDER> so) {
		// TODO Auto-generated method stub

		// đối tượng lưu trữ thông tin lọc kết quả
		UserObject similar = infos.getValue0();
		
		// số bản ghi được lấy trong một lần
		byte total = infos.getValue2();

		// vị trí bắt đầu lấy bản ghi
		int at = (infos.getValue1()-1)*total;
		
		// Thùng rác
		boolean isTrash = infos.getValue3();
		
		StringBuilder sql = new StringBuilder();
		// tổng số bản ghi
		sql.append("SELECT COUNT(user_id) AS total FROM tbluser "+this.createConditions(similar, isTrash)+"; "); // lấy trước

		sql.append("SELECT * FROM tbluser ");
		sql.append(this.createConditions(similar, isTrash));
		switch (so.getValue0()) {
		case NAME:
			sql.append(" ORDER BY user_name ").append(so.getValue1().name());
			break;
		case FULLNAME:
			sql.append(" ORDER BY user_fullname ").append(so.getValue1().name());
			break;
		default:
			sql.append(" ORDER BY user_id DESC ");
		}
		sql.append(" LIMIT " + at + ", " + total + "; ");
		
//		System.out.println(sql.toString());

		return this.getsMR(sql.toString());
	}

	private StringBuilder createConditions(UserObject similar, boolean isTrash) {
		StringBuilder tmp = new StringBuilder();

		if (similar != null) {
			tmp.append("(user_permission<=").append(similar.getUser_permission()).append(") ");

			if (isTrash) {
				tmp.append(" AND (user_trash_id>0)");
			} else {
				tmp.append(" AND (user_trash_id=0)");
			}
			
			//Lấy từ khóa tìm kiếm nếu có
			String key = similar.getUser_name();
			if(!key.equalsIgnoreCase("")) {
				
				tmp.append(" AND (");
				tmp.append("(user_name LIKE '%"+key+"%') OR ");
				tmp.append("(user_fullname LIKE '%"+key+"%') OR ");
				tmp.append("(user_address LIKE '%"+key+"%') OR ");
				tmp.append("(user_email LIKE '%"+key+"%') OR ");
				tmp.append("(user_notes LIKE '%"+key+"%') ");
				tmp.append(")");
				
			}
		}

		if (!tmp.toString().equalsIgnoreCase("")) {
			tmp.insert(0, " WHERE ");
		}

		return tmp;
	}

//	public static void main(String[] args) {
//		// tạo bộ quản lý kết nối
//		ConnectionPool cp = new ConnectionPoolImpl();
//
//		// tạo đối tượng thực thi chức năng mức interface
//		User u = new UserImpl(cp);
//
//		// THEM
//		// tạo đối tượng lữu trữ thông tin thêm mới
//		UserObject nUser = new UserObject();
//		nUser.setUser_name("y100");
//		nUser.setUser_created_date("06/06/2003");
//		nUser.setUser_email("khanh@gamil.com");
//		nUser.setUser_parent_id(20);
//		nUser.setUser_pass("222");
//
//		nUser.setUser_fullname("Nguyen Danh Khanh");
//		nUser.setUser_address("Ha Tinh");
//
//		nUser.setUser_id(68);
//		boolean result = u.delUser(nUser);
//		if (!result) {
//			System.out.println("\n---------------------------------KHONG THANH CONG --------------------------------");
//		}
//
//		// lấy tập kết quả (danh sách người sử dụng)
//		Triplet<UserObject, Integer, Byte> infos = new Triplet<>(null, 0, (byte) 15);
//		ArrayList<ResultSet> res = u.getUsers(infos, new Pair<>(USER_SORT.NAME, ORDER.DESC));
//		ResultSet rs = res.get(1);
//
//		String row;
//		if (rs != null) {
//			try {
//				while (rs.next()) {
//					row = "ID: " + rs.getInt("user_id");
//					row += "\tNAME: " + rs.getString("user_name");
//					row += "\tPARENT: " + rs.getString("user_parent_id");
//					row += "\tPASSWORD: " + rs.getString("user_pass");
//
//					System.out.println(row);
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		rs = res.get(0);
//		if (rs != null) {
//			try {
//				if (rs.next()) {
//					System.out.println("Tong so tai khoan trong CSDL la:" + rs.getShort("total"));
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//		}
//	}

}
