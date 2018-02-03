
package com.almundo.callcenter.controller.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class Util {
    
    public static final int MAX = 10000;
    public static final int MIN = 5000;
    
    /**
     * Static method that generates times of 5 to 10 seconds
     * @return 
     */
    public static long generateRandom(){
        Random random = new Random();
        return random.longs(MAX, MIN, MAX).findFirst().getAsLong();
    }
    
    public static String dateFormater(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        return format.format(date);
    }
}
