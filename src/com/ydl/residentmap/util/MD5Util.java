package com.ydl.residentmap.util;

import java.security.MessageDigest;

/**
 * MD5加密解密算法
 **/
public class MD5Util {

	/***
	 * MD5加码 生成32位md5码
	 */
	public static String string2MD5(String inStr){
		MessageDigest md5;
		String outStr;
		try{
			md5 = MessageDigest.getInstance("MD5");
		}catch (Exception e){
			System.out.println(e.toString());
			e.printStackTrace();
			return null;
		}
		char[] inStrCharArr = inStr.toCharArray();
		byte[] inStrByteArr = new byte[inStrCharArr.length];

		for (int i = 0; i < inStrCharArr.length; i++)
			inStrByteArr[i] = (byte) inStrCharArr[i];
		byte[] md5Bytes = md5.digest(inStrByteArr);
		StringBuilder hexResult = new StringBuilder();
		for (byte md5Byte : md5Bytes) {
			int val = ((int) md5Byte) & 0xff;
			if (val < 16)
				hexResult.append("0");
			hexResult.append(Integer.toHexString(val));
		}
		outStr = hexResult.toString();
		return outStr;

	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */ 
	public static String convertMD5(String inStr){
		String outStr;
		char[] inStrArr = inStr.toCharArray();
		for (int i = 0; i < inStrArr.length; i++){
			inStrArr[i] = (char) (inStrArr[i] ^ 'p');
		}
		outStr = new String(inStrArr);
		return outStr;

	}

}
