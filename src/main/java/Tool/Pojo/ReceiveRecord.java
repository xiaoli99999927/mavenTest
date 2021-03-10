package Tool.Pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ReceiveRecord {
    @ExcelProperty(value="报文ID")
    private String gwid;
    @ExcelProperty(value="发文部门")
    private String faqi;
    @ExcelProperty(value="收文部门")
    private String jieshou;
    @ExcelProperty(value="公文名称")
    private String docname;
    @ExcelProperty(value="报文类型")
    private String leixing;
    @ExcelProperty(value="接收情况")
    private String shifouyijieshou;
    @ExcelProperty(value="公文状态")
    private String jieshouzhunagtai;
    @ExcelProperty(value="接收报文时间")
    private String jieshoushijian;
}
