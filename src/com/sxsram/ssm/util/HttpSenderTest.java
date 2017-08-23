package com.sxsram.ssm.util;

import javax.print.attribute.standard.PrinterMessageFromOperator;

public class HttpSenderTest {
	public static void main(String[] args) {
		String url = "http://sapi.253.com/msg/";// 应用地址
		String account = "cs-013";// 账号
		String pswd = "Zx123456";// 密码
		String mobile = "18135100170,13546712419";// 手机号码，多个号码使用","分割
		String msg = "您好，您的验证码是222256";// 短信内容
		boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
		String extno = null;// 扩展码

		try {
			//String returnString = HttpSender.batchSend(url, account, pswd, mobile, msg, needstatus, extno);
			String returnString = "20161028,111\n111111111111";
			System.out.println(returnString);
			// TODO 处理返回值,参见HTTP协议文档
			System.out.println(Integer.valueOf(returnString.substring(returnString.indexOf(',')+1,returnString.indexOf('\n')==-1?returnString.length():returnString.indexOf('\n'))));
		} catch (Exception e) {
			// TODO 处理异常
			e.printStackTrace();
		}
	}
}
