package cn.kgc.util;

import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

/**
 * 分词工具类
 * @author Administrator
 *
 */
public class IKUtils {

	public static Set<String> getKeyWord(String jobDesc) throws Exception{  
        //estword.etc:扩展词词典  
        //stopword.etc:停用词词典，这个是不用关心的词  
        Set<String> set = new HashSet<String>();  
        //set中不允许重复  
        String jobstr = jobDesc.trim();  
        StringReader reader = new StringReader(jobstr);  
        IKSegmenter ikSementer = new IKSegmenter(reader,true);  
        Lexeme lexene = null;//词，分析出来的词  
        while((lexene = ikSementer.next()) != null){  
            String job = lexene.getLexemeText().trim();  
            set.add(job);  
        }  
        return set;  
    }  
}
