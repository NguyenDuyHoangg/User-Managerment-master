package jsoft.objects;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserObject {
	private int user_id;
	private String user_name;
	private String user_pass;
	private String user_fullname;
	private String user_birthday;
	private String user_mobilephone;
	private String user_homephone;
	private String user_officephone;
	private String user_email;
	private String user_address;
	private String user_jobarea;
	private String user_job;
	private String user_position;
	private short user_applyyear;
	private byte user_permission;
	private String user_notes;
	private String user_roles;
	private short user_logined;
	private String user_created_date;
	private String user_last_modified;
	private String user_last_logined;
	private int  user_parent_id;
	private byte user_actions;
	private int user_trash_id;
	
	
}
