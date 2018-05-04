package cn.kgc.util;

import java.io.IOException;
import java.util.*;

/**
 * <p></p>
 *
 * @author zzshang
 * @version v1.0
 * @since 2015/5/25
 */
public class PropCache {
    //要初始化的配置文件
    static List<String> propsList=new ArrayList<String>();
    //缓冲配置文件 项目启动的时候加载
    static Map<String,Map<String,String>> props=new HashMap<String,Map<String,String>>();
    //配置文件统一后缀
    static String extName=".properties";

    public static Map<String, Map<String, String>> getProps() {
        return props;
    }

    public static void setProps(Map<String, Map<String, String>> props) {
        PropCache.props = props;
    }

    public void initPropCache() throws IOException {
        for(String propName:propsList){
            if(EmptyUtils.isEmpty(PropCache.getProps())){
                PropCache.setProps(new HashMap<String, Map<String, String>>());
            }
            Map temp=new HashMap();
            ResourceBundle resb = ResourceBundle.getBundle(propName);
            Enumeration<String> enums = resb.getKeys();
            while (enums.hasMoreElements()) {
                String key = enums.nextElement();
                if (EmptyUtils.isNotEmpty(key)) {
                    String value =resb.getString(key);
                    temp.put(key,value);
                }
            }
            props.put(propName,temp);
        }
    }
    /***
     * 根据文件获取配置文件所有的属性
     * @param fileName
     * @return
     */
    public static Map<String,String> getProperties(String fileName){
        return props.get(fileName);
    }
    /***
     * 根据配置文件和key 获取value
     * @param fileName
     * @param key
     * @return
     */
    public static String getValue(String fileName, String key){
        if(!EmptyUtils.isEmpty(props.get(fileName))){
            return props.get(fileName).get(key);
        }
        return null;
    }

    public static List<String> getPropsList() {
        return propsList;
    }

    public static void setPropsList(List<String> propsList) {
        PropCache.propsList = propsList;
    }

    public static String getExtName() {
        return extName;
    }

    public static void setExtName(String extName) {
        PropCache.extName = extName;
    }
}
