/*
 * ********************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2017 PAX Technology, Inc. All rights reserved.
 * ********************************************************************************
 */
package com.shawn.poandprotosv.demo.utils;

import java.io.*;
import java.util.*;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/7/16 15:11
 * @Version 1.0
 */
public class PropertiesToCsv {

    public static void main(String[] args) throws  IOException {
        Properties p = new Properties();
        p.load(new FileInputStream(new File("E:\\cpproject\\paxLanguageFile\\Pro\\common-messages.properties")));
        Iterator itr = p.entrySet().iterator();
        List<String> dataList=new ArrayList<String>();
        while (itr.hasNext()){
            Map.Entry e = (Map.Entry)itr.next();
            //对逗号和引号处理（csv文件会根据这个分割）
            String value = e.getValue().toString();
            if (e.getValue().toString().contains(",")){
                //如果还有双引号，先将双引号转义，避免两边加了双引号后转义错误
                if (e.getValue().toString().contains("\"")){
                    value = e.getValue().toString().replace("\"", "\"\"");
                }
                value ="\""+value+"\"";
            }
            String keyAndValue=  e.getKey()+","+value;
            dataList.add(keyAndValue);
            System.out.println(e.getKey()+":"+e.getValue());
        }
        boolean isSuccess= CsvUtils.exportCsv(new File("E:\\cpproject\\paxLanguageFile\\testCsv\\common-messages.csv"), dataList);

    }
}

