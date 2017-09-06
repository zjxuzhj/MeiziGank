package zhj.meizigank.util;

import android.text.TextUtils;

/**
 * Created by panyi on 2017/8/10.
 */

public class UrlUtil {
    public static String findUrlWithOutSufix(final String url) {
        if (TextUtils.isEmpty(url))
            return url;

        int lastIndex = url.lastIndexOf("/");
        String sub = url.substring(0, lastIndex);
        return sub;
    }

    public static String findUrlSufix(final String url) {
        if (TextUtils.isEmpty(url))
            return url;

        int lastIndex = url.lastIndexOf("/");
        String sub = url.substring(lastIndex, url.length());
        return sub;
    }

    public static String getImageFormatStr(final String suffix, String base) {
        if (TextUtils.isEmpty(suffix))
            return "%s";
        String div = null;
        int index = -1;
        for (int i = 0; i < suffix.length(); i++) {
            char c = suffix.charAt(i);
            if (c == '/')
                continue;

            if (!TextUtils.isDigitsOnly(c + "")) {
                //System.out.println("div = "+c);
                div = c + "";
                index = i;
                break;
            }
        }

        String alphaAfterSub = suffix.substring(index, suffix.lastIndexOf("."));
        //System.out.println("alphaAfterSub = " + alphaAfterSub);

        int aIndex = suffix.indexOf(div);
        String head = suffix.substring(0, aIndex);
        StringBuilder sb = new StringBuilder();
        if (base != null) {
            sb.append(base);
        }
        sb.append(head).append(div);
        if (alphaAfterSub.length() > 3) {//a01   w1101
            sb.append(alphaAfterSub.substring(1, alphaAfterSub.length() - 2));
        }
        sb.append("%s");
        int dotIndex = suffix.lastIndexOf(".");
        String tail = suffix.substring(dotIndex, suffix.length());
        sb.append(tail);

        return sb.toString();
    }

}
