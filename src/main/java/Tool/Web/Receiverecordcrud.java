package Tool.Web;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Receiverecordcrud {

    public Map<String, String> headerMaps = Arrays.stream(new Object[][] {
            {"Accept","application/json"},
            {"Accept-Encoding","gzip, deflate"},
            {"Accept-Language","zh-CN"},
            {"ajax","true"},
            {"Connection","Keep-Alive"},
            {"Cookie","loginName=PUa6NaPOcJweskhkBngozflo5H8v9qmmYNbVD%2FL149gao4n%2BSUwf68xmmGakYBINAxtxHv6FrxctstyQR0KfMg%3D%3D; JSESSIONID_HSJYHJKAPP2=4A8C4F301BF6C2D869B85A09BDC74A8E"},
            {"Host","19.125.2.86:8080"},
            {"pre-action","showList"},
            {"Referer","http://19.125.2.86:8080/gd/oa/receiverecordcrud"},
            {"User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko"}
    }).collect(Collectors.toMap(kv -> (String) kv[0], kv -> (String) kv[1]));
    public void addHeaders(HttpGet httpget, Map<String, String> headerMaps) {
        headerMaps.forEach((k,v)->{
            httpget.addHeader(k, v);
        });
    }

    private String htmlContent;
    private List<String> matcher = new ArrayList<String>();

    public void show() throws IOException {
        init();
        System.out.println(htmlContent);
    }



    public String getHtmlContent() throws IOException {
        init();
        return htmlContent;
    }

    public void init() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://19.125.2.86:8080/gd/oa/receiverecordcrud?pageSize=30&new%24=false&readOnly=false&segmentType=OFC&pageNo=1&$$time$$=20210218165811");
        addHeaders(get,headerMaps);

        CloseableHttpResponse response = null;


        response = client.execute(get);
        HttpEntity en = response.getEntity();
        htmlContent= EntityUtils.toString(en,"utf-8");

        HttpClientUtils.closeQuietly(response);
        HttpClientUtils.closeQuietly(client);
    }

    public static void main(String[] args) throws IOException {
        new Receiverecordcrud().show();
    }
}
