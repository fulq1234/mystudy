package cn.kgc.util;

import cn.kgc.beans.Recruit;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zezhong.shang on 17-9-18.
 */
public class DealDataUtil {

    public static PropCache propCache;

    //分词
    public static List<String> getKeyword(Recruit recruit,String extWords,String stopWords) {
        List<String> list = new ArrayList<>();
        StringReader input = new StringReader(recruit.getJobDescription().trim());
        IKSegmenter ikSeg = new IKSegmenter(input, true);   // true 用智能分词 ，false细粒度
        try {
            for (Lexeme lexeme = ikSeg.next(); lexeme != null; lexeme = ikSeg.next()) {
                String temp = lexeme.getLexemeText().replaceAll(" ", "");
                boolean flag = CharUtil.strIsEnglish(temp);
                //如果是英文
                if (flag) {
                    //判断是否是无效词
                    if (filterInanition(temp, stopWords)) {
                        //判断是否是复合词
                        List<String> results = splitWord(temp);
                        //如果是复合词
                        if (!EmptyUtils.isEmpty(results)) {
                            for (String tt : results) {
                                if (filterInanition(tt,stopWords)) {
                                    list.add(replaceTyc(tt));
                                }
                            }
                        } else {
                            list.add(replaceTyc(temp));
                        }
                    }
                } else { //如果是中文
                    if (filterZwWord(temp, extWords)) {
                        list.add(replaceTyc(temp));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /***
     * 同义词过滤
     *
     * @param word
     * @return
     */
    public static String replaceTyc(String word) {
        String temp = propCache.getProperties("word/tyc").get(word);
        return EmptyUtils.isEmpty(temp) ? word : temp;
    }

    /***
     * 无效词过滤
     * @param word
     * @return
     */
    public static boolean filterInanition(String word, String stopWord) {
        if (stopWord.contains("," + word + ",")) {
            return false;
        }
        return true;
    }

    /**
     * 中文技术筛选
     *
     * @param word
     * @return
     */
    public static boolean filterZwWord(String word,String extWords) {
        if (extWords.contains("," + word + ",")) {
            return true;
        }
        return false;
    }

    /***
     * 拆分复合词
     *
     * @param word
     * @return
     */
    public static List<String> splitWord(String word) {
        List<String> result = new ArrayList<>();
        String temp = propCache.getProperties("word/splitword").get(word);
        if (!EmptyUtils.isEmpty(temp)) {
            for (String tt : temp.split(",")) {
                result.add(tt);
            }
            return result;
        }
        return null;
    }

    public static List<String> ikSplitWord(String description) throws IOException {
        List<String> list=new ArrayList<>();
        Set<String> set=new HashSet<String>();
        StringReader input = new StringReader(description.trim());
        IKSegmenter ikSeg = new IKSegmenter(input, true); // true 用智能分词 ，false细粒度
        for (Lexeme lexeme = ikSeg.next(); lexeme != null; lexeme = ikSeg.next()) {
            String temp = lexeme.getLexemeText().replaceAll(" ", "");
            set.add(temp);
        }
        list.addAll(set);
        return list;
    }

    public static void main(String[] args) throws IOException {
        List<String> list=new ArrayList<>();
        String words="我正在利用网络爬虫技术爬取招聘数据";
        StringReader input = new StringReader(words);
        IKSegmenter ikSeg = new IKSegmenter(input, true);   // true 用智能分词 ，false细粒度
        for (Lexeme lexeme = ikSeg.next(); lexeme != null; lexeme = ikSeg.next()) {
            String temp = lexeme.getLexemeText().replaceAll(" ", "");
            System.out.println(temp);
        }

        Set<String> set=new HashSet<String>();

        set.add("111");
        set.add("112");
        set.add("113");
        set.add("114");
        set.add("115");
        list.addAll(set);
        for (String str:list){
            System.out.println(str);
        }
    }
}
