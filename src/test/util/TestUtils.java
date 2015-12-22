/**
 *@��Ŀ���ƣ�EncryptUtils  
 *@������ test.util
 *@������TestUtils.java
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
package test.util;

import java.io.IOException;

import org.bouncycastle.jce.provider.JDKKeyFactory.RSA;

import rsa.utils.RSAUtils;
import md5.util.MD5Utils;
import des.util.DESUtils;
import base64.util.Base64Utils;
import aes.util.AESUtils;

/**
 * @author tom
 *
 */
public class TestUtils {
	static String content="1234567890";
	static   String key = "0987654321";

	public static void main(String[] args) throws IOException, Exception {
		//-----------------AES����------------------------------------
		System.out.println("---------------AES����-----------------------");
		String aesEncrypt=AESUtils.encrypt(content, key);
		String aesDncrypt=AESUtils.decrypt(aesEncrypt, key);
		System.out.println("����ǰ��content--------->"+content);
		System.out.println("���ܺ�aesEncrypt--------->"+aesEncrypt);
		System.out.println("���ܺ�aesDncrypt--------->"+aesDncrypt);
		//-----------------BASE64����------------------------------------
		System.out.println("---------------BASE64����-----------------------");		
		String getBase64=Base64Utils.getBase64(content);
		String fromBase64=Base64Utils.getFromBase64(getBase64);
		System.out.println("����ǰ��content--------->"+content);
		System.out.println("���ܺ�getBase64--------->"+getBase64);
		System.out.println("���ܺ�fromBase64--------->"+fromBase64);
		//-----------------DES����------------------------------------
		System.out.println("---------------DES����-----------------------");
		String desEncrypt=DESUtils.encrypt(content, key);
		String desDncrypt=DESUtils.decrypt(desEncrypt, key);
		System.out.println("����ǰ��content--------->"+content);
		System.out.println("���ܺ�desEncrypt--------->"+desEncrypt);
		System.out.println("���ܺ�desDncrypt--------->"+desDncrypt);
		//-----------------MD5����------------------------------------
		System.out.println("---------------MD5����-----------------------");
		String md51=MD5Utils.getMD5(content);
		String md52=MD5Utils.getMD5String(content);
		System.out.println("����ǰ��content--------->"+content);
		System.out.println("md51��md52--------->"+md52);
		System.out.println("md52��md52--------->"+md52);
		//-----------------RSA����------------------------------------
		System.out.println("---------------RSA����-----------------------");
		RSAUtils rsaUtils=new RSAUtils();
		System.out.println("��Կ��"+rsaUtils.getRSAPublicKey().getFormat());
		System.out.println("˽Կ��"+rsaUtils.getRSAPrivateKey().getFormat());
		String rsaEncrypt=rsaUtils.encryptByPublicKey(content, rsaUtils.getRSAPublicKey());
		String rsaDncrypt=rsaUtils.decryptByPrivateKey(rsaEncrypt, rsaUtils.getRSAPrivateKey());
		System.out.println("����ǰ��content--------->"+content);
		System.out.println("rsaEncrypt��rsaEncrypt--------->"+rsaEncrypt);
		System.out.println("rsaDncrypt��rsaDncrypt--------->"+rsaDncrypt);


	}
}
