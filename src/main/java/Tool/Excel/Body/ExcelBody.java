package Tool.Excel.Body;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExcelBody {
  /*
  * 序号	公共场所ID	所在市区	所在镇街	公共场所	类别	公共场所管理员	当天检测人次	近7天检测人次	检测总人次	当前统计时间

   * */

      @ExcelProperty(value={"检测明细表","序号"})
      private String xh;
      @ExcelProperty(value={"检测明细表","公共场所ID"})
      private String ggcsid;
      @ExcelProperty(value={"检测明细表","所在市区"})
      private String szsq;
      @ExcelProperty(value={"检测明细表","所在镇街"})
      private String szzj;
      @ExcelProperty(value={"检测明细表","公共场所"})
      private String ggcs;
      @ExcelProperty(value={"检测明细表","类别"})
      private String lb;
      @ExcelProperty(value={"检测明细表","公共场所管理员"})
      private String ggcsgly;
      @ExcelProperty(value={"检测明细表","检测数据","当天检测人次"})
      private int dtjcrc;
      @ExcelProperty(value={"检测明细表","检测数据","近7天检测人次"})
      private int j7tjcrc;
      @ExcelProperty(value={"检测明细表","检测数据","检测总人次"})
      private int jczrs;
      @ExcelProperty(value={"检测明细表","当前统计时间"})
      private String dqtjsj;
}