package cn.com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import org.json.JSONObject;


public class CommonUtil {
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat sdfNoSplit = new SimpleDateFormat("yyyyMMdd");
	public static String prefix = "HOSPITAL-GZ";
	

	public static int random_num = 100000;

	public static void main(String[] args) {
		// System.out.println(sdf.format(new Date()));
		// getRamdom(1000000, 10000000);
//		System.out.println(getCode());
		System.out.println(isNumericZidai("123"));
		
	}


	private static boolean isNumericZidai(String str) {
		for (int i = 0; i < str.length(); i++) {
			System.out.println(str.charAt(i));
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * ��ȡ����
	 * 
	 * @return
	 */
	public static String getDate() {
		return sdf.format(new Date());
	}

	/**
	 * ��ȡ����
	 * 
	 * @return
	 */
	public static String getCode() {
		
		
		String code = prefix == null || "".equals(prefix)
				? (sdfNoSplit.format(new Date()) + "-" + getRamdom(random_num/10, random_num)+"-"+getRamdom(random_num/10, random_num))
				: (prefix + "-" + sdfNoSplit.format(new Date()) + "-" + getRamdom(random_num/10, random_num)+"-"+getRamdom(random_num/10, random_num));
		return code;
	}

	/**
	 * ��ȡ�����
	 * 
	 * @param start
	 *            ��ʼ
	 * @param end
	 *            ��ֹ
	 * @return
	 */
	public static int getRamdom(int start, int end) {
		ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
		int i3 = threadLocalRandom.nextInt(start, end);// 获取[-10,10)的随机整�?
		return i3;
	}

}
