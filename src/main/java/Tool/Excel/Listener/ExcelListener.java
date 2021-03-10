package Tool.Excel.Listener;

import Tool.Excel.Body.*;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class ExcelListener extends AnalysisEventListener<ExcelBody_orinal> {
    private List<ExcelBody_orinal> ExcelData = new ArrayList<ExcelBody_orinal>();
    @Override
    public void invoke(ExcelBody_orinal excelBody, AnalysisContext analysisContext) {
        ExcelData.add(excelBody);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        ExcelData.stream()
                .collect(Collectors.groupingBy(e -> e.getSzzj(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparingLong(e->e.getValue()))
                .forEach(e->System.out.println(e));
    }
}