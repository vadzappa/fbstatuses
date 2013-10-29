package com.gts.fb;

import org.json.JSONObject;
import org.junit.Test;

/**
 * <p><b> 2013 </b></p>
 *
 * @author Vadim
 */
public class TestStatusesRetriever {

    @Test
    public void testStatusRetrieving() {
        FacebookStatusesRetriever facebookStatusesRetriever = new FacebookStatusesRetriever("Syria", FacebookSearchType.POST);
        JSONObject facebookEntities = facebookStatusesRetriever.findFacebookEntities();
        System.out.print(facebookEntities);
    }
}
