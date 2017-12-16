package com.minxing.client.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AesHelper {
	
	static String secret_type = "AES/CBC/PKCS5Padding";
	

	// 解密
	public static String decrypt(byte[] sSrc, String key, String iv) {
		try {
			// 判断Key是否正确
			if (key == null) {
				key = "c351bfd05dab20d7";
			}
			if (iv == null) {
				iv = "a0fe7c7c98e09e8c";
			}
			byte[] raw = key.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance(secret_type);
			IvParameterSpec ivp = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivp);

			try {
				byte[] original = cipher.doFinal(sSrc);
				String originalString = new String(original);
				return originalString;
			} catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}

	public static String byte2HexStr(byte buf[]) {
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

	public static byte[] hexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	public static String base64encode(String origin) {
		byte[] b = null;
		try {
			b = origin.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		b = Base64.encodeBase64(b);
		String s = new String(b);
		return s;
	}

	public static String base64decode(String origin) {
		byte[] b = null;
		try {
			b = replaceBlank(origin).getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		b = Base64.decodeBase64(b);
		String s = new String(b);
		return s;
	}
	
	
	
	
	public static String licenseDecode(String secret){
		String s = base64decode(secret);
		byte[] b = hexStr2Byte(s);
		return decrypt(b, null, null);
	}
	
	public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
	
	public static String SHA1(String decript) {
		try {
			MessageDigest digest = MessageDigest
					.getInstance("SHA-1");
			digest.update(decript.getBytes("utf-8"));
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

//	public static void main(String[] ss){
//		User ocu = new User();
//		  String json = new Gson().toJson(ocu);
//		  System.out.println(json);
//		  //System.out.println(UrlEncoder.encode("于歆然"));
//		  System.out.println(SHA1("{\"user_ids\":\"21154\",\"ocuId\":\"21317\",\"ocuSecret\":\"e2f90a682a68167e5930afd07ac64c27\",\"userId\":21317,\"timestamp\":1484562818263,\"template\":{\"id\":\"147\",\"data\":{\"name\":\"吕杰\"}}}:1484562818263:e2f90a682a68167e5930afd07ac64c27"));
//	}

}
