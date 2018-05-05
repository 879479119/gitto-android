package com.stormphoenix.ogit.utils;

import com.stormphoenix.ogit.entity.http.Base64;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;

/**
 * Created by wanlei on 18-3-25.
 */

public class EncodingUtils {
    public EncodingUtils() {
    }

    public static final byte[] fromBase64(String content) {
        return Base64.decode(content);
    }

    public static final String toBase64(byte[] content) {
        return Base64.encodeBytes(content);
    }

    public static final String toBase64(String content) {
        byte[] bytes;
        try {
            bytes = content.getBytes("UTF-8");
        } catch (UnsupportedEncodingException var2) {
            bytes = content.getBytes();
        }

        return toBase64(bytes);
    }

    public static final String uuid(String string) {
        HashSet<Integer> integerHashSet = new HashSet<>();
        Random random = new Random();
        int randoms = random.nextInt(1000);
        if (!integerHashSet.contains(randoms)) {
            integerHashSet.add(randoms);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssss", Locale.CHINA);
            return sdf.format(new Date()) + String.valueOf(randoms) + string;//唯一哈希码
        }
        return null;
    }
}
