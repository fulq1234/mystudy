package cn.kgc.util;
import java.util.Map;

public class MapUtil {

    public static void paramExecute(Map<String,Object> param){
        if(EmptyUtils.isEmpty(param)){
            return;
        }
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            if(EmptyUtils.isEmpty(entry.getValue())){
                param.put(entry.getKey(),-1);
            }
       }
    }
}
