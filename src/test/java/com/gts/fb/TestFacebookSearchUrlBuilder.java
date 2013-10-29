package com.gts.fb;

import java.util.Properties;

import com.gts.fb.util.PropertiesReader;
import junit.framework.Assert;
import org.junit.Test;

/**
 * <p><b> 2013 </b></p>
 *
 * @author Vadim
 */
public class TestFacebookSearchUrlBuilder {
    private static final Properties properties = PropertiesReader.readFromResource(FacebookStatusesRetriever.class, "at.properties");

    @Test
    public void testFacebookSearchUrlBuilder() {
        FacebookSearchUrlBuilder facebookSearchUrlBuilder = new FacebookSearchUrlBuilder();
        String build = facebookSearchUrlBuilder.query("Syria").
                searchType(FacebookSearchType.POST).
                searchParameter(new SearchParameter("name", "Vaeim")).
                accessToken(properties.getProperty("facebook.access_token")).
                build();
        Assert.assertEquals(true, build.contains("?type=post&q=Syria&access_token=CAAIws8AvwwYBACA9WVSqXreEmbkD37k4sDpNzkYENX1TZB7U7FjArgIA35iZAWvPnyErzZBbK6DxZBj9Bm1OYVLJMYgI69XaO3YZB11iyjZCpRWvgvgTTMSrXWSubr2ZCu9KfZA5Y9YQrHnLNJKyBfkfVbueGWCNT0owf63IDKxyo9Yko8Jgv0BMMIrNUdY8KkGxk5HYJA5OsAZDZD&name=Vaeim"));
    }
}
