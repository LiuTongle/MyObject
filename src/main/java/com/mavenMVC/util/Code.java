package com.mavenMVC.util;

/**
 * Created by lizai on 15/6/11.
 */
public class Code {

	/**
	 * 存储当前登录用户id的字段名
	 */
	public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

	// public static final String SERVER_ADDRESS = "http://192.168.50.247:8080";
	public static final String SERVER_ADDRESS = "http://211.149.215.209:8088";

	/**
	 * 存放Authorization的header字段
	 */
	public static final String TOKEN = "token";

	/**
	 * 存放sig的header字段
	 */
	public static final String SIG = "sig";

	/**
	 * 存放tokenType的header字段
	 */
	public static final String TOKEN_TYPE = "tokenType";

	/**
	 * 存放sig的header字段
	 */
	public static final String REQUEST_TIME = "requestTime";

	public static final int NOT_LOGIN_ERROR = 2;

	public static final int ARTICLE_NEW = 1;

	public static final int ARTICLE_VALID = 0;

	public static final int NO_SEX = 0;

	public static final int MALE = 1;

	public static final int FEMALE = 2;

	public static final String ERROR_PAGE = "/error.jsp";

	public static final String MES_PWD = "78$d3e36";

	public static final String MES_SN = "SDK-WSS-010-08624";

	public static final String PING_CODE = "sk_live_qLOazTCS04a9PGCSyDWTu5aT";

	// public static final String PING_PRIVATE_KEY =
	// "MIICXAIBAAKBgQDKt70b2h8d3PgEgYpsY1N94nkHmYTRS20IEnp5GUkQNbyXxFvN\n" +
	// "iYCLZyAra5WVFW9xEgBa34ovQrkPzfAE7Ol2YMS7ZLYQgMeO3dj9AoFjE+Du2yDP\n" +
	// "xpg1ohQ3x1JAJAlnI3UC5shCTVi9CfXOiBmRUANKOkoJDO1Rc0M9PWdVjwIDAQAB\n" +
	// "AoGAPXlOrb2Nph62T5eBBLFyRkCBd16EuntnCwWfgk26rGG/WT7AoCnMg3m4dbJg\n" +
	// "YB9p9h4BYY1ceEyBlltbKAM4IJUIy1BLqFNFSTHUNQ3ipzx5kC2QV+Ac6cgxyiPg\n" +
	// "LaCMaSdetirbq/Q85oJXBlkuM/LSru/An8NlQiIlYX27vSECQQD7u2M3/6qwoUi+\n" +
	// "1MBHKDRG2w9jCjnlVWfdEa0xi7PzmCEKJvI4IZu6AmnlL8cvJZ/7MNBx7Bi6cZzW\n" +
	// "N72CDQIZAkEAziecU+Jq4FVPVGdb9F02inFe3/satFjbkFt37/22Cyn3tPz/Rb+7\n" +
	// "kq6g2j/MWWI+CctRH8i60EsL7fh3HOYZ5wJAWZ2tRctD1dulDSKqTOq4KZ1kzepf\n" +
	// "EBCmiCH52VCVwJug739L7cWxLbgcQNYQf+1SFdeb7WKSrxUxM2XmljzRSQJAXvw5\n" +
	// "Nx03iS1FY+pLoAfivV8HC8Qyxa27XIQIevc3DWXE0AtRwt1Ym3kCfAyxJu3xD6oy\n" +
	// "MqbWDGrHkFnO5+3NOQJBAI5lyfbjXUVoo00zl9P4WfagwfsYf+sIFr6K+djimjtH\n" +
	// "vrdP5DmKJmfvihrUJRM9+XGb5RpCraKCEWc0uth/0NE=";

	public static final String PING_PRIVATE_KEY = 
			"MIICXAIBAAKBgQDCiDVWhCcnwHRgKe6STMycQHWg76JmrDkqgVu1GQxK5E8wePQ4\n" +
	                "X0HF44nFahN1O8RhGh5J13momMNk4AFtdMlmVbuWMnEZMIgsRncvItdfYPNA2m/J\n" +
	                "Hm0fSt12e34qxqY887VGxRTx/Pmne9Sw246r0VvzYcJnuQFE439sTEnG4QIDAQAB\n" +
	                "AoGAGsGNRIcMs6T+3LMnwyuZAOzEwQv0tFZ/zl7YdEIrtKlOUwZv4MkJTOSM8IA0\n" +
	                "iO1dh1WGUPW4H/5S8a71w05K3PDFOP6r6mXe9a4Kz44H5q+Z+vpAv5D3cEyfF0UV\n" +
	                "NWMloJHoL/4Ndk53CLqrpjXzwMzzMxuYxta/uhVeyrFRKDkCQQD1xWuGi+scY14G\n" +
	                "6+ieKWr4K567eDHuU60AcEt2bIZBdYyiuTCSIN37DArhY2qLllk56rOvPEy70B/Y\n" +
	                "wNzadApbAkEAyqDb5GLK2q4MapW8+vQjy4l6IHOl6EZlgkdXi+ZY6J5+ARoGAQqP\n" +
	                "m4kZAT2eMbJEJO64NJmwcdgdV72034VgcwJAOmlpXNj0UBLtzgdTigOeAM2dNEEk\n" +
	                "3NfWTjxiMwsn/XbKrgIFoTVejMQX8emG8RYSrskxW3yhYCHe780msGTT8QJBAIcp\n" +
	                "bgSWccIVP2sJnxm3KPloJvLmsdCSxLt2Y0uKx9vDfUG23jJrnQefF/Lf61ozE4mf\n" +
	                "Q3iy3CPUiR4yCSjgi0kCQF7jWtaKxzD2WpzJVpFQ6jz73c4vTwaDAcc9xxzkrXCl\n" +
	                "8dTzGokvsHDK7QQ18sWL0OcpU6se4LnggflPMZebO2E=";

	public static final String PING_APP_CODE = "app_OSaLa1jrT8uPv1WH";

	public static final String PING_APP_CODE_E = "app_500qTGGSmzDO58mz";
}
