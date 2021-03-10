package Tool.Web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.regex.*;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Http {
	
	public Map<String, String> headerMaps = Arrays.stream(new Object[][] {
		{"Host","www.bilibili.com"},
		{"User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0"},
		{"Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"},
		{"Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2"},
		{"Accept-Encoding","gzip, deflate, br"},
		{"Connection","keep-alive"},
		{"Cookie","LIVE_BUVID=AUTO3015682501774181; buvid3=40B0C9C1-A7D5-4C74-B315-0EC7B6BD46A6190949infoc"},
		{"Upgrade-Insecure-Requests","1"}		
	}).collect(Collectors.toMap(kv -> (String) kv[0], kv -> (String) kv[1]));
	public void addHeaders(HttpGet httpget,Map<String, String> headerMaps) {
		headerMaps.forEach((k,v)->{
			httpget.addHeader(k, v);
		});
	}
	
	private String htmlContent;
	private List<String> matcher = new ArrayList<String>();

	public void show(){
		System.out.println(htmlContent);
	}

	public void analysis() {

		Matcher matcher = Pattern.compile("(?<=img.{0,100}src=\")[^><]*?(\\.jpg|png)(?=\")").matcher(htmlContent);

		while(matcher.find()) {
			this.matcher.add(matcher.group());
		}
		System.out.println("end analysis");
	}



	public void init() {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet("http://www.bilibili.com");
		addHeaders(get,headerMaps);

		CloseableHttpResponse response = null;
		
		try {
			response = client.execute(get);
			HttpEntity en = response.getEntity();
			htmlContent=EntityUtils.toString(en,"utf-8");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		analysis();
		HttpClientUtils.closeQuietly(response);
		HttpClientUtils.closeQuietly(client);
	}

	public static void main(String[] args) {

		Http http = new Http();
		http.init();
		http.show();

	}
	
}
