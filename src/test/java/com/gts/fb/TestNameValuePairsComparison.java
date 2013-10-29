package com.gts.fb;

import junit.framework.Assert;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

/**
 * <p><b> 2013 </b></p>
 *
 * @author Vadim
 */
public class TestNameValuePairsComparison {

    @Test
    public void testNameValuePairSet() {

        BasicNameValuePair basicNameValuePair1 = new BasicNameValuePair("a", "b");
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("a", "c");
        Assert.assertEquals(true, basicNameValuePair1.equals(basicNameValuePair2));
    }
}
