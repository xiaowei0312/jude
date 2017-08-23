package com.sxsram.ssm.util;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class SmsUtil {
	private final static String NAMESPACE = "http://tempuri.org/";

	private final static String endpoint = "http://115.29.52.221:24663/Service/?wsdl";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.print(SubmitSms("xbgtest001", "123456", "13546712419",
				"您正在注册成为聚德会员，校验码：123456，如果以上非您本人操作，请忽略本短信【智网科技】", "1001"));

		// System.out.print(GetOrderState("", "", ""));

		// System.out.print(GetBalance("", "", "1001"));

		// System.out.print(GetUplinkMessage("", "", "", ""));
	}

	/**
	 * 发送短信
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param mobileNum
	 *            接收者手机号
	 * @param content
	 *            短信内容
	 * @param productNum
	 *            产品编号
	 * @return
	 */

	public static String SubmitSms(String username, String password, String mobileNum, String content,
			String productNum) {

		String result = "";

		try {

			Service service = new Service();

			Call call = (Call) service.createCall();

			String soapActionURI = "http://tempuri.org/I_Order/SubmitSms";

			call.setUseSOAPAction(true);

			call.setSOAPActionURI(soapActionURI);

			call.setTargetEndpointAddress(endpoint);

			call.setOperationName(new QName(NAMESPACE, "SubmitSms"));// wsdl里面描述的接口名称

			call.addParameter(new QName(NAMESPACE, "username"), org.apache.axis.encoding.XMLType.XSD_DATE,

					javax.xml.rpc.ParameterMode.IN);// 接口的参数

			call.addParameter(new QName(NAMESPACE, "password"), org.apache.axis.encoding.XMLType.XSD_DATE,

					javax.xml.rpc.ParameterMode.IN);// 接口的参数

			call.addParameter(new QName(NAMESPACE, "mobileNum"), org.apache.axis.encoding.XMLType.XSD_DATE,

					javax.xml.rpc.ParameterMode.IN);// 接口的参数

			call.addParameter(new QName(NAMESPACE, "content"), org.apache.axis.encoding.XMLType.XSD_DATE,

					javax.xml.rpc.ParameterMode.IN);// 接口的参数

			call.addParameter(new QName(NAMESPACE, "productNum"), org.apache.axis.encoding.XMLType.XSD_DATE,

					javax.xml.rpc.ParameterMode.IN);// 接口的参数

			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型

			result = (String) call.invoke(new Object[] { username, password, mobileNum, content, productNum });

			// 给方法传递参数，并且调用方法

		}

		catch (Exception e) {

			System.err.println(e.toString());

		}

		return result;

	}

	/**
	 * 获取订单状态
	 * 
	 * @param orderNum
	 *            订单号
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */

	public static String GetOrderState(String orderNum, String username, String password) {

		String result = "";

		try {

			Service service = new Service();

			Call call = (Call) service.createCall();

			String soapActionURI = "http://tempuri.org/I_Order/GetOrderState";

			call.setUseSOAPAction(true);

			call.setSOAPActionURI(soapActionURI);

			call.setTargetEndpointAddress(endpoint);

			call.setOperationName(new QName("http://tempuri.org/", "GetOrderState"));// wsdl里面描述的接口名称

			call.addParameter(new QName("http://tempuri.org/", "orderNum"), org.apache.axis.encoding.XMLType.XSD_DATE,

					javax.xml.rpc.ParameterMode.IN);// 接口的参数

			call.addParameter(new QName("http://tempuri.org/", "username"), org.apache.axis.encoding.XMLType.XSD_DATE,

					javax.xml.rpc.ParameterMode.IN);// 接口的参数

			call.addParameter(new QName("http://tempuri.org/", "password"), org.apache.axis.encoding.XMLType.XSD_DATE,

					javax.xml.rpc.ParameterMode.IN);// 接口的参数

			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型

			result = (String) call.invoke(new Object[] { orderNum, username, password });

			// 给方法传递参数，并且调用方法

		}

		catch (Exception e) {

			System.err.println(e.toString());

		}

		return result;

	}

	/**
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param productNum
	 *            产品编号
	 * @return
	 */
	public static String GetBalance(String username, String password, String productNum) {

		String result = "";

		try {

			Service service = new Service();

			Call call = (Call) service.createCall();

			String soapActionURI = "http://tempuri.org/I_PointAccount/GetBalance";

			call.setUseSOAPAction(true);

			call.setSOAPActionURI(soapActionURI);

			call.setTargetEndpointAddress(endpoint);

			call.setOperationName(new QName("http://tempuri.org/", "GetBalance"));// wsdl里面描述的接口名称

			call.addParameter(new QName("http://tempuri.org/", "username"), org.apache.axis.encoding.XMLType.XSD_DATE,

					javax.xml.rpc.ParameterMode.IN);// 接口的参数

			call.addParameter(new QName("http://tempuri.org/", "password"), org.apache.axis.encoding.XMLType.XSD_DATE,

					javax.xml.rpc.ParameterMode.IN);// 接口的参数

			call.addParameter(new QName("http://tempuri.org/", "productNum"), org.apache.axis.encoding.XMLType.XSD_DATE,

					javax.xml.rpc.ParameterMode.IN);// 接口的参数

			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型

			result = (String) call.invoke(new Object[] { username, password, productNum });

			// 给方法传递参数，并且调用方法

		}

		catch (Exception e) {

			System.err.println(e.toString());

		}

		return result;

	}

	/**
	 * 
	 * @param username
	 *            用户名
	 * @param phoneNum
	 *            发送手机号
	 * @param startTime
	 *            开始时间(留空默认为当天时间)
	 * @param endTime
	 *            结束时间(留空默认为当天时间)
	 * @return
	 */
	public static String GetUplinkMessage(String username, String phoneNum, String startTime, String endTime) {

		String result = "";

		try {

			Service service = new Service();

			Call call = (Call) service.createCall();

			String soapActionURI = "http://tempuri.org/I_Order/GetUplinkMessage";

			call.setUseSOAPAction(true);

			call.setSOAPActionURI(soapActionURI);

			call.setTargetEndpointAddress(endpoint);

			call.setOperationName(new QName("http://tempuri.org/", "GetUplinkMessage"));// wsdl里面描述的接口名称

			call.addParameter(new QName("http://tempuri.org/", "username"), org.apache.axis.encoding.XMLType.XSD_DATE,

					javax.xml.rpc.ParameterMode.IN);// 接口的参数

			call.addParameter(new QName("http://tempuri.org/", "phoneNum"), org.apache.axis.encoding.XMLType.XSD_DATE,

					javax.xml.rpc.ParameterMode.IN);// 接口的参数

			call.addParameter(new QName("http://tempuri.org/", "startTime"), org.apache.axis.encoding.XMLType.XSD_DATE,

					javax.xml.rpc.ParameterMode.IN);// 接口的参数

			call.addParameter(new QName("http://tempuri.org/", "endTime"), org.apache.axis.encoding.XMLType.XSD_DATE,

					javax.xml.rpc.ParameterMode.IN);// 接口的参数

			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型

			result = (String) call.invoke(new Object[] { username, phoneNum, startTime, endTime });

			// 给方法传递参数，并且调用方法

		}

		catch (Exception e) {

			System.err.println(e.toString());

		}

		return result;

	}

}
