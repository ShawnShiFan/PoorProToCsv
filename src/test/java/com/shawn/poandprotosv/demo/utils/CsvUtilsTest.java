package com.shawn.poandprotosv.demo.utils;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/7/17 9:11
 * @Version 1.0
 */
public class CsvUtilsTest {

    @Test
    public void exportCsv() {
        List<String> dataList=new ArrayList<String>();
        dataList.add("1,张三,男");
        dataList.add("2,李四,男");
        dataList.add("3,小红,1213,女");
        boolean isSuccess=CsvUtils.exportCsv(new File("E:\\cpproject\\paxLanguageFile\\testCsv\\test3.xls"), dataList);
        System.out.println(isSuccess);
    }
}