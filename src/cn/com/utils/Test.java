package cn.com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
	public static void main(String[] args) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d1 = sdf.parse("2019-11-24");
			Date d2 = sdf.parse("2019-11-23");
			if(d1.after(d2)) {
				System.out.println("true");
			}
			
			if(d2.before(d1)) {
				System.out.println("true");
			}
			
			if(d2.before(d2)) {
				System.out.println("true");
			}
			
			if(d2.after(d2)) {
				System.out.println("true");
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
