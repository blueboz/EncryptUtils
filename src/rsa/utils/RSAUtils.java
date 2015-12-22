
package rsa.utils;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;

import javax.crypto.Cipher;
/**
 *@��Ŀ���ƣ�EncryptUtils  
 *@������ rsa.utils
 *@������RSAUtils.java
 *@��������TODO
 *@�����ˣ�tom
 *@�������ڣ�2015��12��21��
 *@�޸��ˣ�
 *@�޸�ʱ�䣺
 *@�޸ı�ע��
 *@versions  
 *@˵����
 * RSA �����ࡣ�ṩ���ܣ����ܣ�������Կ�Եȷ�����
 * ��Ҫ��http://www.bouncycastle.org����bcprov-jdk14-123.jar��
 * RSA����ԭ�����  
 * RSA�İ�ȫ�������ڴ����ķֽ⣬��Կ��˽Կ��������������������100��ʮ����λ���ĺ�����  
 * �ݲ²⣬��һ����Կ�������ƶϳ����ĵ��Ѷȵ�ͬ�ڷֽ������������Ļ�  
 * ===================================================================  
 * �����㷨�İ�ȫ��δ�õ����۵�֤����  
 * ===================================================================  
 * ��Կ�Ĳ�����  
 * 1.ѡ������������ p,q,���� n=p*q;  
 * 2.���ѡ�������Կ e,Ҫ�� e �� (p-1)*(q-1)����  
 * 3.���� Euclid �㷨���������Կ d , ʹ������ e*d = 1(mod(p-1)*(q-1)) (���� n,d ҲҪ����)  
 * 4:���˵ó���ԿΪ (n,e) ˽ԿΪ (n,d)  
 * ===================================================================  
 * �ӽ��ܷ�����  
 * 1.���Ƚ�Ҫ���ܵ���Ϣ m(�����Ʊ�ʾ) �ֳɵȳ������ݿ� m1,m2,...,mi �鳤 s(�����ܴ�) ,���� 2^s<n  
 * 2:��Ӧ�������ǣ� ci = mi^e(mod n)  
 * 3:����ʱ�����¼��㣺 mi = ci^d(mod n)  
 * ===================================================================  
 * RSA�ٶ�  
 * ���ڽ��еĶ��Ǵ������㣬ʹ��RSA�������Ҳ��DES����100�������� ���������Ӳ��ʵ�֡�  
 * �ٶ�һֱ��RSA��ȱ�ݡ�һ����˵ֻ������������ ���ܡ� 
 * RSA�ǷǶԳ��㷨��������Կ�ͽ�����Կ�ǲ�һ���ģ�����˵����������һ����Կ�Ƶ�����һ����Կ��
 * ��Կ�ߴ�󣬼ӽ����ٶ�����һ������������������ ��
 */

public class RSAUtils {

	//��Կ��
	private KeyPair keyPair = null;
	/**
	 * ��ʼ����Կ��
	 */
	public RSAUtils(){
		try {
			this.keyPair = this.generateKeyPair();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ������Կ��
	 * @return KeyPair
	 * @throws Exception
	 */
	private KeyPair generateKeyPair() throws Exception {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA",new org.bouncycastle.jce.provider.BouncyCastleProvider());
			//���ֵ��ϵ������ܵĴ�С�����Ը��ģ����ǲ�Ҫ̫�󣬷���Ч�ʻ��
			final int KEY_SIZE = 1024;
			keyPairGen.initialize(KEY_SIZE, new SecureRandom());
			KeyPair keyPair = keyPairGen.genKeyPair();
			return keyPair;
		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}



	/**
	 * ���ɹ�Կ
	 * @param modulus
	 * @param publicExponent
	 * @return RSAPublicKey
	 * @throws Exception
	 */
	private RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) throws Exception {

		KeyFactory keyFac = null;
		try {
			keyFac = KeyFactory.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
		} catch (NoSuchAlgorithmException ex) {
			throw new Exception(ex.getMessage());
		}
		RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
		try {
			return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
		} catch (InvalidKeySpecException ex) {
			throw new Exception(ex.getMessage());
		}

	}

	/**
	 * ����˽Կ
	 * @param  modulus
	 * @param  privateExponent
	 * @return RSAPrivateKey
	 * @throws Exception
	 */
	private RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) throws Exception {
		KeyFactory keyFac = null;
		try {
			keyFac = KeyFactory.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
		} catch (NoSuchAlgorithmException ex) {
			throw new Exception(ex.getMessage());
		}
		RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus), new BigInteger(privateExponent));
		try {
			return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
		} catch (InvalidKeySpecException ex) {
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * ���ع�Կ
	 * @return
	 * @throws Exception 
	 */
	public RSAPublicKey getRSAPublicKey() throws Exception{

		//��ȡ��Կ
		RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();
		//��ȡ��Կϵ��(�ֽ�������ʽ)
		byte[] pubModBytes = pubKey.getModulus().toByteArray();
		//���ع�Կ����ָ��(�ֽ�������ʽ)
		byte[] pubPubExpBytes = pubKey.getPublicExponent().toByteArray();
		//���ɹ�Կ
		RSAPublicKey recoveryPubKey = this.generateRSAPublicKey(pubModBytes,pubPubExpBytes);
		return recoveryPubKey;
	}

	/**
	 * ��ȡ˽Կ
	 * @return
	 * @throws Exception 
	 */
	public RSAPrivateKey getRSAPrivateKey() throws Exception{

		//��ȡ˽Կ
		RSAPrivateKey priKey = (RSAPrivateKey) keyPair.getPrivate();
		//����˽Կϵ��(�ֽ�������ʽ)
		byte[] priModBytes = priKey.getModulus().toByteArray();
		//����˽Կר��ָ��(�ֽ�������ʽ)
		byte[] priPriExpBytes = priKey.getPrivateExponent().toByteArray();
		//����˽Կ
		RSAPrivateKey recoveryPriKey = this.generateRSAPrivateKey(priModBytes,priPriExpBytes);
		return recoveryPriKey;
	}

	/** 
	 * ��Կ���� 
	 *  
	 * @param data 
	 * @param publicKey 
	 * @return 
	 * @throws Exception 
	 */  
	public static String encryptByPublicKey(String data, RSAPublicKey publicKey)  
			throws Exception {  
		Cipher cipher = Cipher.getInstance("RSA");  
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
		// ģ��  
		int key_len = publicKey.getModulus().bitLength() / 8;  
		// �������ݳ��� <= ģ��-11  
		String[] datas = splitString(data, key_len - 11);  
		String mi = "";  
		//������ĳ��ȴ���ģ��-11��Ҫ�������  
		for (String s : datas) {  
			mi += bcd2Str(cipher.doFinal(s.getBytes()));  
		}  
		return mi;  
	}  

	/** 
	 * ˽Կ���� 
	 *  
	 * @param data 
	 * @param privateKey 
	 * @return 
	 * @throws Exception 
	 */  
	public static String decryptByPrivateKey(String data, RSAPrivateKey privateKey)  
			throws Exception {  
		Cipher cipher = Cipher.getInstance("RSA");  
		cipher.init(Cipher.DECRYPT_MODE, privateKey);  
		//ģ��  
		int key_len = privateKey.getModulus().bitLength() / 8;  
		byte[] bytes = data.getBytes();  
		byte[] bcd = ASCII_To_BCD(bytes, bytes.length);  
		System.err.println(bcd.length);  
		//������ĳ��ȴ���ģ����Ҫ�������  
		String ming = "";  
		byte[][] arrays = splitArray(bcd, key_len);  
		for(byte[] arr : arrays){  
			ming += new String(cipher.doFinal(arr));  
		}  
		return ming;  
	}  
	/** 
	 * ASCII��תBCD�� 
	 *  
	 */  
	public static byte[] ASCII_To_BCD(byte[] ascii, int asc_len) {  
		byte[] bcd = new byte[asc_len / 2];  
		int j = 0;  
		for (int i = 0; i < (asc_len + 1) / 2; i++) {  
			bcd[i] = asc_to_bcd(ascii[j++]);  
			bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));  
		}  
		return bcd;  
	}  
	public static byte asc_to_bcd(byte asc) {  
		byte bcd;  

		if ((asc >= '0') && (asc <= '9'))  
			bcd = (byte) (asc - '0');  
		else if ((asc >= 'A') && (asc <= 'F'))  
			bcd = (byte) (asc - 'A' + 10);  
		else if ((asc >= 'a') && (asc <= 'f'))  
			bcd = (byte) (asc - 'a' + 10);  
		else  
			bcd = (byte) (asc - 48);  
		return bcd;  
	}  
	/** 
	 * BCDת�ַ��� 
	 */  
	public static String bcd2Str(byte[] bytes) {  
		char temp[] = new char[bytes.length * 2], val;  

		for (int i = 0; i < bytes.length; i++) {  
			val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);  
			temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');  

			val = (char) (bytes[i] & 0x0f);  
			temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');  
		}  
		return new String(temp);  
	}  
	/** 
	 * ����ַ��� 
	 */  
	public static String[] splitString(String string, int len) {  
		int x = string.length() / len;  
		int y = string.length() % len;  
		int z = 0;  
		if (y != 0) {  
			z = 1;  
		}  
		String[] strings = new String[x + z];  
		String str = "";  
		for (int i=0; i<x+z; i++) {  
			if (i==x+z-1 && y!=0) {  
				str = string.substring(i*len, i*len+y);  
			}else{  
				str = string.substring(i*len, i*len+len);  
			}  
			strings[i] = str;  
		}  
		return strings;  
	}  
	/** 
	 *�������  
	 */  
	public static byte[][] splitArray(byte[] data,int len){  
		int x = data.length / len;  
		int y = data.length % len;  
		int z = 0;  
		if(y!=0){  
			z = 1;  
		}  
		byte[][] arrays = new byte[x+z][];  
		byte[] arr;  
		for(int i=0; i<x+z; i++){  
			arr = new byte[len];  
			if(i==x+z-1 && y!=0){  
				System.arraycopy(data, i*len, arr, 0, y);  
			}else{  
				System.arraycopy(data, i*len, arr, 0, len);  
			}  
			arrays[i] = arr;  
		}  
		return arrays;  
	}  
}
