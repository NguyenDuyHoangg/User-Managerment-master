package jsoft.library;

import javax.servlet.*;

public class Utilities {
	public static byte getByteParam(ServletRequest request, String name) {
		byte value = -1;

		try {
			String str_value = request.getParameter(name);
			if (str_value != null && !str_value.equalsIgnoreCase("")) {
				value = Byte.parseByte(str_value);
			}
		} catch (NumberFormatException ex) {
			System.out.print("Co loi trong gia tri cua tham so");
			ex.printStackTrace();
		}

		return value;
	}
	
	public static short getShortParam(ServletRequest request, String name) {
		short value = -1;

		try {
			String str_value = request.getParameter(name);
			if (str_value != null && !str_value.equalsIgnoreCase("")) {
				value = Short.parseShort(str_value);
			}
		} catch (NumberFormatException ex) {
			System.out.print("Co loi trong gia tri cua tham so");
			ex.printStackTrace();
		}

		return value;
	}
	
	public static int getIntParam(ServletRequest request, String name) {
		int value = -1;

		try {
			String str_value = request.getParameter(name);
			if (str_value != null && !str_value.equalsIgnoreCase("")) {
				value = Integer.parseInt(str_value);
			}
		} catch (NumberFormatException ex) {
			System.out.print("Co loi trong gia tri cua tham so");
			ex.printStackTrace();
		}

		return value;
	}
	
	/**
	 * Lấy tham số phân trang
	 * @param request
	 * @return
	 */
	
	public static short getPageParam(ServletRequest request) {
		short page = 1;

		try {
			String str_value = request.getParameter("p");
			if (str_value != null && !str_value.equalsIgnoreCase("")) {
				page = Short.parseShort(str_value);
			}
		} catch (NumberFormatException ex) {
			System.out.print("Co loi khi lay tham so phan trang");
			ex.printStackTrace();
		}

		return page;
	}

}
