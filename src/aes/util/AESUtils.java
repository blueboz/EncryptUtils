
package aes.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import base64.util.BASE64Decoder;

/**
 *@��Ŀ���ƣ�EncryptUtils  
 *@������ aes.util
 *@������AESUtils.java
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
public class AESUtils {
	/**
	 * ����
	 * @param input ����
	 * @param key   ����
	 * @return
	 */
	public static String encrypt(String content, String password) {  
		try {             
			KeyGenerator kgen = KeyGenerator.getInstance("AES");  
			kgen.init(128, new SecureRandom(password.getBytes()));  
			SecretKey secretKey = kgen.generateKey();  
			byte[] enCodeFormat = secretKey.getEncoded();  
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");  
			Cipher cipher = Cipher.getInstance("AES");// ����������  
			byte[] byteContent = content.getBytes("utf-8");  
			cipher.init(Cipher.ENCRYPT_MODE, key);// ��ʼ��  
			byte[] result = cipher.doFinal(byteContent);  
			return parseByte2HexStr(result); // ����  
		} catch (NoSuchAlgorithmException e) {  
			e.printStackTrace();  
		} catch (NoSuchPaddingException e) {  
			e.printStackTrace();  
		} catch (InvalidKeyException e) {  
			e.printStackTrace();  
		} catch (UnsupportedEncodingException e) {  
			e.printStackTrace();  
		} catch (IllegalBlockSizeException e) {  
			e.printStackTrace();  
		} catch (BadPaddingException e) {  
			e.printStackTrace();  
		}  
		return null;  
	}  

	/**
	 * ����
	 * @param input   ����������
	 * @param key     ����
	 * @return
	 */
	public static String decrypt(String content, String password) {  
		try {  
			KeyGenerator kgen = KeyGenerator.getInstance("AES");  
			kgen.init(128, new SecureRandom(password.getBytes()));  
			SecretKey secretKey = kgen.generateKey();  
			byte[] enCodeFormat = secretKey.getEncoded();  
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");              
			Cipher cipher = Cipher.getInstance("AES");// ����������  
			cipher.init(Cipher.DECRYPT_MODE, key);// ��ʼ��  
			byte[] result = cipher.doFinal(parseHexStr2Byte(content));  
			return new String(result); // ����  
		} catch (NoSuchAlgorithmException e) {  
			e.printStackTrace();  
		} catch (NoSuchPaddingException e) {  
			e.printStackTrace();  
		} catch (InvalidKeyException e) {  
			e.printStackTrace();  
		} catch (IllegalBlockSizeException e) {  
			e.printStackTrace();  
		} catch (BadPaddingException e) {  
			e.printStackTrace();  
		}  
		return null;  
	}
	public static String parseByte2HexStr(byte buf[]) {  
		StringBuffer sb = new StringBuffer();  
		for (int i = 0; i < buf.length; i++) {  
			String hex = Integer.toHexString(buf[i] & 0xFF);  
			if (hex.length() == 1) {  
				hex = '0' + hex;  
			}  
			sb.append(hex.toUpperCase());  
		}  
		return sb.toString();  
	}  
	/**��16����ת��Ϊ������ 
	 * @param hexStr 
	 * @return 
	 */  
	public static byte[] parseHexStr2Byte(String hexStr) {  
		if (hexStr.length() < 1)  
			return null;  
		byte[] result = new byte[hexStr.length()/2];  
		for (int i = 0;i< hexStr.length()/2; i++) {  
			int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
			int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
			result[i] = (byte) (high * 16 + low);  
		}  
		return result;  
	}
}  
