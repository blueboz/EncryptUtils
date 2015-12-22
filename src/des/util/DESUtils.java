
package des.util;
import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import base64.util.BASE64Decoder;
import base64.util.BASE64Encoder;

/**
 *@��Ŀ���ƣ�EncryptUtils  
 *@������ des.util
 *@������DESUtils.java
 *@��������TODO
 *@�����ˣ�tom
 *@�������ڣ�2015��12��21��
 *@�޸��ˣ�
 *@�޸�ʱ�䣺
 *@�޸ı�ע��
 *@versions  
 *
 *�๦��˵���� 
 * �������ݼ��ܱ�׼��DES���ǶԳ������㷨�����Ǽ�����Կ�ܹ��ӽ�����Կ�����������������Ҳ������
 * ��Կ�϶̣����ܴ���򵥣��ӽ����ٶȿ죬�����ڼ��ܴ������ݵĳ��ϡ�
 */
public class DESUtils {
	private final static String DES = "DES";
	/**
	 * Description ���ݼ�ֵ���м���
	 * @param data 
	 * @param key  ���ܼ�byte����
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data, String key) throws Exception {
		byte[] bt = encrypt(data.getBytes(), key.getBytes());
		String strs = new BASE64Encoder().encode(bt);
		return strs;
	}

	/**
	 * Description ���ݼ�ֵ���н���
	 * @param data
	 * @param key  ���ܼ�byte����
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static String decrypt(String data, String key) throws IOException,
	Exception {
		if (data == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] buf = decoder.decodeBuffer(data);
		byte[] bt = decrypt(buf,key.getBytes());
		return new String(bt);
	}
	/**
	 * Description ���ݼ�ֵ���м���
	 * @param data
	 * @param key  ���ܼ�byte����
	 * @return
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// ����һ�������ε������Դ
		SecureRandom sr = new SecureRandom();

		// ��ԭʼ��Կ���ݴ���DESKeySpec����
		DESKeySpec dks = new DESKeySpec(key);

		// ����һ����Կ������Ȼ��������DESKeySpecת����SecretKey����
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher����ʵ����ɼ��ܲ���
		Cipher cipher = Cipher.getInstance(DES);

		// ����Կ��ʼ��Cipher����
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

		return cipher.doFinal(data);
	}


	/**
	 * Description ���ݼ�ֵ���н���
	 * @param data
	 * @param key  ���ܼ�byte����
	 * @return
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		// ����һ�������ε������Դ
		SecureRandom sr = new SecureRandom();

		// ��ԭʼ��Կ���ݴ���DESKeySpec����
		DESKeySpec dks = new DESKeySpec(key);

		// ����һ����Կ������Ȼ��������DESKeySpecת����SecretKey����
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher����ʵ����ɽ��ܲ���
		Cipher cipher = Cipher.getInstance(DES);

		// ����Կ��ʼ��Cipher����
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

		return cipher.doFinal(data);
	}
}
