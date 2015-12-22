package base64.util;

import java.io.UnsupportedEncodingException;
/**
 *@��Ŀ���ƣ�EncryptUtils  
 *@������ base64.util
 *@������Base64Utils.java
 *@��������TODO
 *@�����ˣ�tom
 *@�������ڣ�2015��12��21��
 *@�޸��ˣ�
 *@�޸�ʱ�䣺
 *@�޸ı�ע��
 *@versions  
 *
 *		�๦��˵���� 
 */
public class Base64Utils {
	/**
	 * base64����
	 * @param str
	 * @return
	 */
	public static String getBase64(String str) {  
		byte[] b = null;  
		String s = null;  
		try {  
			b = str.getBytes("utf-8");  
		} catch (UnsupportedEncodingException e) {  
			e.printStackTrace();  
		}  
		if (b != null) {  
			s = new BASE64Encoder().encode(b);  
		}  
		return s;  
	}  

	/**
	 * ��ȡbase64���������
	 * @param s
	 * @return
	 */
	public static String getFromBase64(String s) {  
		byte[] b = null;  
		String result = null;  
		if (s != null) {  
			BASE64Decoder decoder = new BASE64Decoder();  
			try {  
				b = decoder.decodeBuffer(s);  
				result = new String(b, "utf-8");  
			} catch (Exception e) {  
				e.printStackTrace();  
			}  
		}  
		return result;  
	}  
}
