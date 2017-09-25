import com.minxing.client.ocu.ArticleMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by wenliu on 2017/7/17.
 */
public class TestArticleMessage {

    public static void main(String[] args) {
        SimpleDateFormat s = new SimpleDateFormat();
        try {
            new ArticleMessage(false, true, s.parse(""));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
