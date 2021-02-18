package ada;

import org.junit.Assert;
import org.junit.Test;

class TextUtilsTest {

    @Test
    public void Should_Format_Text_According_To_Specified_Column_Width() {
        int columnWidth = 30;
        String inputString =
                "Aenean volutpat aliquam lectus ac laoreet. Sed nibh purus, eleifend sit amet arcu molestie, molestie maximus enim.";
        String actualString = TextUtils.justified(inputString, columnWidth);
        String expectedString =
                "Aenean volutpat aliquam lectus\nac  laoreet.  Sed  nibh purus,\neleifend    sit    amet   arcu\nmolestie,   molestie   maximus\nenim.";
        Assert.assertEquals(expectedString, actualString);
    }
}
