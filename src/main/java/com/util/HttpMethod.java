package com.util;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import org.apache.http.Header;
import org.springframework.stereotype.Component;

/**
 * @Auther: Chang
 * @Date: 2018/10/3
 */
@Component
public class HttpMethod {

	public String get(String url, String cookies) {
		HttpConfig config = getConfig(cookies);
		String content = null;
		try {
			content = HttpClientUtil.get(config.url(url));
		} catch (HttpProcessException e) {
			e.printStackTrace();
		}
		return content;
	}

	private HttpConfig getConfig(String cookies) {
//		String cookies = "TIEBA_USERTYPE=85ff978c6dadb77a891409ee; BDUSS=HcwU0V3dUQ4Vkd2VzQ1VmdjZTluanpBVXBQQ3lFSjJ6RWs3QmowcUd3UX5TcXhiQVFBQUFBJCQAAAAAAAAAAAEAAAASBTkOY2hhbmd3ZW5ibzEyAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD-9hFs~vYRbe; TIEBAUID=898e9f1e8967b23b4701e2e5; bdshare_firstime=1535425866642; PSTM=1537354969; BAIDUID=6565E40E80DB731D240253D11B58F0F0:FG=1; BIDUPSID=702BE7AFD0CC73CB22ED9D7D35DBC0D9; MCITY=-%3A; STOKEN=fb1a4181ae270c67c2265a812685719f1f4befa41329980c5106f2d0256633f9; Hm_lvt_98b9d8c2fd6608d564bf2ac2ae642948=1536678145,1536842371,1537263827,1538151069; H_PS_PSSID=1465_21116_18560_26350_27244_22160; delPer=0; PSINO=7; IS_NEW_USER=4a22c8fc8f221877280bc548; BAIDU_WISE_UID=wapp_1538480831269_864; USER_JUMP=-1; SEENKW=%E5%B9%BF%E4%B8%9C%E5%95%86%E5%AD%A6%E9%99%A2; BDRCVFR[feWj1Vr5u3D]=I67x6TjHwwYf0; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598";
		String referer = "https://www.baidu.com";
		Header[] headers= HttpHeader.custom().cookie(cookies).referer(referer).build();
		HttpConfig config = HttpConfig.custom().headers(headers);
		return config;
	}
}



