package Tool.Json;
import Tool.Web.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Json {

    private List<JSONArray> CheckResults;

    public List<JSONArray> getResults() throws IOException {
        CheckResults = new ArrayList<JSONArray>();
        String htmlContent = new Receiverecordcrud().getHtmlContent();
        JSONObject jsonObject = JSON.parseObject(htmlContent);

        JSONArray jsonArray = (JSONArray)jsonObject.get("records");

        jsonArray.stream()
                .map(e-> ((JSONObject) e).get("cells"))
                .forEach(e->{
                    CheckResults.add(((JSONArray) e));
                });
        return CheckResults;
    }
}
