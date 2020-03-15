package model;
import java.util.Random;

public class Captcha {
	public Captcha() {
				
	}
	
	public StringBuffer StringCaptcha() {
	Random ra = new Random();
	// 设置字符
	char[] chars = "1234567890QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm".toCharArray();
     // 获取4位随机数
	StringBuffer buffer = new StringBuffer();
	int index; // 获取随机chars下标
	for (int i = 0; i < 4; i++) {
		index = ra.nextInt(chars.length); // 获取随机chars下标
		buffer.append(chars[index]);
	}
	System.out.println("本次验证码为："+buffer);
	return buffer;
	}		


}