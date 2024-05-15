import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.saic.cloud.fota.model.EcuExcel;
import com.saic.cloud.fota.service.TaskDescService;
import com.saic.cloud.fota.service.impl.TaskDescServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: chenchunrong
 * @Date: 2024/5/11 3:24 PM
 * @description: Test
 */
public class Test {
    public static void main(String[] args) {
        //TaskDescService taskDescService = new TaskDescServiceImpl();
        //System.out.println(taskDescService.buildEcuInfoListFromExcle("/Users/chenchunrong/Downloads/导入ecu模版.xlsx"));
        List<String> list = new ArrayList<>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        System.out.println(list.subList(1, list.size()));
    }
}
