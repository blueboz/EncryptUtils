
package md5.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import base64.util.Base64Utils;
/**
 *@��Ŀ���ƣ�EncryptUtils  
 *@������ md5.util
 *@������SHAUtils.java
 *@��������TODO
 *@�����ˣ�tom
 *@�������ڣ�2015��12��21��
 *@�޸��ˣ�
 *@�޸�ʱ�䣺
 *@�޸ı�ע��
 *@versions  
 *
 * �๦��˵���� 
 *SHA1 �� MD5 ��ɢ���㷨���������С������ӳ�䵽һ����С�ġ��̶����ȵ�Ψһֵ��
 *������ǿ��ɢ��һ���ǲ�����ģ������ζ��ͨ��ɢ�н�����޷��Ƴ��κβ��ֵ�ԭʼ��Ϣ��
 *�κ�������Ϣ�ı仯�����½�һλ����������ɢ�н�������Ա仯�����֮Ϊѩ��ЧӦ��
 *ɢ�л�Ӧ���Ƿ���ͻ�ģ����Ҳ���������ͬɢ�н����������Ϣ��
 *������Щ���Ե�ɢ�н���Ϳ���������֤��Ϣ�Ƿ��޸ġ�MD5 �� SHA1 ��Լ�� 33%
 */

public class SHAUtils {


	public final static String getSHA(String pwd) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("SHA");//SHA ���� MD5
		return Base64Utils.getBase64(md.digest(pwd.getBytes()).toString());
	}

	public final static String getMD5String(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4',
				'5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			//���MD5ժҪ�㷨�� MessageDigest ����
			MessageDigest mdInst = MessageDigest.getInstance("SHA");
			//ʹ��ָ�����ֽڸ���ժҪ
			mdInst.update(btInput);
			//�������
			byte[] md = mdInst.digest();
			//������ת����ʮ�����Ƶ��ַ�����ʽ
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
