
package com.shawn.poandprotosv.demo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/7/16 15:38
 * @Version 1.0
 */
public class PoToCsv {

    /**
       * 将po属性文件转换成list类型数据
       * @param fileName
       * @return List集合
       */

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static void readFileByLines(String fileName,List<String> listKey,List<String> listValue) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                //  System.out.println("line " + line + ":" + tempString);
                line++;
                String key =".*msgid.*";
                String value =".*msgstr.*";
                boolean isMatchKey = Pattern.matches(key,tempString);
                boolean isMatchValue = Pattern.matches(value,tempString);
                Pattern pattern = Pattern.compile("\"(.*?)\"");

                if (isMatchKey){
                    //捕获到msgid对应行，获取引号中内容，加进list
                    Matcher matcherKey = pattern.matcher(tempString);
                    while (matcherKey.find()){
                        //   System.out.println(matcherKey.group());
                        listKey.add(matcherKey.group());
                    }
                }
                if (isMatchValue){
                    //捕获到msgstr对应行，获取引号中内容，加进list
                    Matcher matcherValue = pattern.matcher(tempString);
                    while (matcherValue.find()){
                        //  System.out.println(matcherValue.group());
                        listValue.add(matcherValue.group());
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }



    public static void main(String[] args) {
        String fileName ="E:\\cpproject\\paxLanguageFile\\testpo\\en.po";
        List<String> listKey = new ArrayList<>();
        List<String> listValue = new ArrayList<>();
        readFileByLines(fileName,listKey,listValue);
        List<String> dataList = new ArrayList<>();
        int i = 0;
        while (i < listValue.size()){
            //对逗号和引号处理（csv文件会根据这个分割）
            String value = listValue.get(i);
            if (listValue.get(i).contains(",")){
                //如果还有双引号，先将双引号转义，避免两边加了双引号后转义错误
                if ( listValue.get(i).contains("\"")){
                    value =  listValue.get(i).replace("\"", "\"\"");
                }
                value ="\""+value+"\"";
            }
            String keyAndValue= listKey.get(i)+","+value;
            dataList.add(keyAndValue);
            i++;
        }

        boolean isSuccess= CsvUtils.exportCsv(new File("E:\\cpproject\\paxLanguageFile\\testCsv\\MessageEnPo.csv"), dataList);
    }

}
