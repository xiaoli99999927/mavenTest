import Tool.Json.Json;
import com.alibaba.fastjson.JSONArray;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ReceiveRecordCrudTest {

    public static void main(String[] args) throws IOException {
        List<JSONArray> results = new Json().getResults();
        results.stream()
                .forEach(System.out::println);


        results.stream()
                .collect(Collectors.groupingBy(e-> ((JSONArray) e).get(5),Collectors.counting()))
                .entrySet()
                .stream()
                .forEach(System.out::println);
    }
}
