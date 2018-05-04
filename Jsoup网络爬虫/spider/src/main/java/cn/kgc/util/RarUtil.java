package cn.kgc.util;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩文件工具类
 * @author sun.kai
 * 2016年8月14日
 */
public class RarUtil {
    static final int BUFFER = 8192;
    private static File zipFile;
    //日誌
    static Logger logger= Logger.getLogger(RarUtil.class);
    /**
     * 压缩单个或多文件方法
     * @param zipPath 压缩后的文件路径
     * @param srcPathName 要压缩的文件路径
     * 参数srcPathName也可以定义成数组形式，需调用方把参数封装到数组中传过来即可
     */
    public static void compress(String zipPath,String... srcPathName) {
        //压缩后的文件对象
        zipFile = new File(zipPath);
        try {
            //创建写出流操作
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,new CRC32());
            ZipOutputStream out = new ZipOutputStream(cos);
            for(String srcPath:srcPathName){
                //创建需要压缩的文件对象
                File file = new File(srcPath);
                if (!file.exists()){
                    throw new RuntimeException(srcPath + "不存在！");
                }
                String basedir = "";
                compress(file, out, basedir);
            }
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static void compress(File file, ZipOutputStream out, String basedir) {
        /*
         * 判断是目录还是文件
         */
        if (file.isDirectory()) {
            basedir += file.getName() + File.separator;
            compressDirectory(file, out, basedir);
        } else {
            System.out.println("压缩：" + basedir + file.getName());
            compressFile(file, out, basedir);
        }
    }

    /**
     * 压缩一个目录
     */
    private static void compressDirectory(File dir, ZipOutputStream out, String basedir) {
        if (!dir.exists()){
            return;
        }
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            /* 递归 */
            compress(files[i], out, basedir);
        }
    }

    /**
     * 压缩一个文件
     */
    private static void compressFile(File file, ZipOutputStream out, String basedir) {
        if (!file.exists()) {
            return;
        }
        try {
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(file));
            //创建Zip实体，并添加进压缩包
            ZipEntry entry = new ZipEntry(basedir + file.getName());
            out.putNextEntry(entry);
            //读取待压缩的文件并写进压缩包里
            int count;
            byte data[] = new byte[BUFFER];
            while ((count = bis.read(data, 0, BUFFER)) != -1) {
                out.write(data, 0, count);
            }
            bis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 解压缩
     * @param sourceFile 要解压缩的文件的路径
     * @param destDir 解压缩后的目录路径
     * @throws Exception
     */
    public static void deCompress(String sourceFile,String destDir) throws Exception{
        //创建需要解压缩的文件对象
        File file = new File(sourceFile);
        if (!file.exists()){
            throw new RuntimeException(sourceFile + "不存在！");
        }
        //创建解压缩的文件目录对象
        File destDiretory  = new File(destDir);
        if(!destDiretory.exists()){
            destDiretory.mkdirs();
        }
        /*
         * 保证文件夹路径最后是"/"或者"\"
         * charAt()返回指定索引位置的char值
         */
        char lastChar = destDir.charAt(destDir.length()-1);
        if(lastChar!='/'&&lastChar!='\\'){
            //在最后加上分隔符
            destDir += File.separator;
        }
        unzip(sourceFile, destDir);
    }

    /**
     * 解压方法
     * 需要ant.jar
     */
    private static void unzip(String sourceZip,String destDir) throws Exception{
        try{
            Project p = new Project();
            Expand e = new Expand();
            e.setProject(p);
            e.setSrc(new File(sourceZip));
            e.setOverwrite(false);
            e.setDest(new File(destDir));
            e.execute();
        }catch(Exception e){
            throw e;
        }
    }
    /***
     * 按照文件夾壓縮
     * @param src
     */
    public static void compressByDir(String src){
        File file=new File(src);
        File[] tempList = file.listFiles();
        logger.info("该目录下对象个数："+tempList.length);
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isDirectory()) {
                File directory=tempList[i];
                logger.info("正在压缩'"+directory.getPath()+"'文件夹..........");
                compress(src+"\\"+directory.getName()+".rar",directory.getPath());
            }
        }
    }

    public static void main(String[] args) {
        while (true){
            Scanner sc=new Scanner(System.in);
            logger.info("请输入要压缩的文件夹....");
            String dir=sc.nextLine();
            if(dir.equals("quit")){
                return;
            }else {
                compressByDir(dir);
            }
        }
    }
}
