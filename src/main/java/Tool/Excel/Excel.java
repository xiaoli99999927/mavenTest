package Tool.Excel;
import com.alibaba.excel.EasyExcel;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import Tool.Excel.Listener.*;
import Tool.Excel.Body.*;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Excel {

    @Test
    public void test1(){
        String FileName = "C:\\Users\\heyX\\Desktop\\明细表v2.0（已排序）.xls";
        EasyExcel.read(FileName,ExcelBody_orinal.class,new ExcelListener()).sheet().doRead();
    }

    @Test
    public void test2(){
        String FileName = "E:\\java\\IntelliJ IDEA\\JetBrains\\IntelliJ IDEA 2020.3.2\\projects\\mavenTest\\target\\明细表v2.0（已排序）write.xls";
        List<ExcelBody> WriteData = new ArrayList<ExcelBody>();
        for(int i=0;i<100;i++) {
            ExcelBody eb = new ExcelBody();
            eb.setLb("测试类别"+i);
            WriteData.add(eb);
        }

        EasyExcel.write(FileName,ExcelBody.class).sheet().doWrite(WriteData);
    }

    @Test
    public void httpTest() throws IOException {

        HttpClient client = new DefaultHttpClient();
        //发送get请求
        HttpGet request = new HttpGet("http://19.125.2.86:8080/gd/oa/receiverecordcrud?pageSize=30&new%24=false&readOnly=false&segmentType=OFC&pageNo=1&$$time$$=20210218165811");
        HttpResponse response = client.execute(request);

        /**请求发送成功，并得到响应**/
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            /**读取服务器返回过来的json字符串数据**/
            String strResult = EntityUtils.toString(response.getEntity());
            System.out.println(strResult);
        }
    }
}