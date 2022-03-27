package com.deng.crm.commons.utils;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {

    /**
     * 对其进行格式化
     * @param date
     * @return
     */
    public static String formateDateTime(Date date){
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataStr=dateFormat.format(date);
        return dataStr;
    }
}
