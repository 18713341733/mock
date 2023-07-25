package com.example.mockserver.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceRandomUtil {

    public static String replaceRandomFields(String input) {
        String pattern = "\\$\\{random:(id|str):(\\d+)}";
        Random random = new Random();

        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(input);

        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String fieldType = matcher.group(1);
            // 获取 (\\d+) 的具体值
            int length = Integer.parseInt(matcher.group(2));

            String replacement;
            if (fieldType.equals("id")) {
//                StringBuilder idBuilder = new StringBuilder();
//                for (int i = 0; i < length; i++) {
//                    idBuilder.append(random.nextInt(10));
//                }
//                replacement = idBuilder.toString();
                replacement =RandomUtil.randomNum(length);
            } else {
//                final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//                StringBuilder strBuilder = new StringBuilder();
//                for (int i = 0; i < length; i++) {
//                    int index = random.nextInt(chars.length());
//                    strBuilder.append(chars.charAt(index));
//                }
//                replacement = strBuilder.toString();
                replacement =RandomUtil.randomStr(length);
            }

            // 使用 Matcher 对象的 appendReplacement 方法进行替换
            matcher.appendReplacement(sb, replacement);
        }

        // 使用 Matcher 对象的 appendTail 方法获取剩余部分并拼接到结果字符串中
        matcher.appendTail(sb);

        return sb.toString();
    }
}
