package com.almundo.callcenter.controller.Util;

import com.almundo.callcenter.controller.util.Util;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;


public class UtilTest {
    
    @Test
    public void testGenerateRandomReturnNotNull(){
      Long time = Util.generateRandom();
        Assert.assertNotNull(time);
    }
    
    @Test
    public void testDateFormaterWhenDateIsNotNullReturnDateFormatteer(){
        String result = "2018-03-02 05:15:30.0";
        String formater = Util.dateFormater(new Date("03/02/2018 5:15:30"));
        Assert.assertEquals(result, formater);
    }
    
    
}
