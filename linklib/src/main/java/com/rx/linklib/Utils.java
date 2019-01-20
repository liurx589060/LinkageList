package com.rx.linklib;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2019/1/19.
 */

public class Utils {
    public enum ELetterType {
        /**
         * 只输出字符
         */
        LETTER_ONLY_CHARACTER,
        /**
         * 输出所有，不转化
         */
        LETTER_ALL, //当不为字母的用#代替（数字也用#代替）
        /**
         * //输出为字母或数字
         */
        LETTER_NUM,
    }

    /**
     * 获取汉字字符串的首字母，英文字符不变
     * 例如：阿飞→af
     */
    public static String getPinYinHeadChar(String chines) {
        StringBuffer sb = new StringBuffer();
        sb.setLength(0);
        char[] chars = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > 128) {
                try {
                    sb.append(PinyinHelper.toHanyuPinyinStringArray(chars[i], defaultFormat)[0].charAt(0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 获取汉字字符串的第一个字母
     */
    public static String getPinYinFirstLetter(String str) {
       return getPinYinFirstLetter(str,ELetterType.LETTER_ALL);
    }

    /**
     * 获取汉字字符串的第一个字母
     * isNotLetterByOther  true:当不是
     */
    public static String getPinYinFirstLetter(String str,ELetterType eLetterType) {
        StringBuffer sb = new StringBuffer();
        sb.setLength(0);
        char c = str.charAt(0);
        String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
        if (pinyinArray != null) {
            sb.append(pinyinArray[0].charAt(0));
        } else {
            sb.append(c);
        }

        String result = sb.toString().toUpperCase();
        if(eLetterType == ELetterType.LETTER_ALL) {

        }else if(eLetterType == ELetterType.LETTER_NUM) {
            Pattern pattern = Pattern.compile("[a-z0-9A-Z]*");
            if(!pattern.matcher(result).matches()) {
                result = "#";
            }
        }else if(eLetterType == ELetterType.LETTER_ONLY_CHARACTER) {
            Pattern pattern = Pattern.compile("[a-zA-Z]*");
            if(!pattern.matcher(result).matches()) {
                result = "#";
            }
        }
        return result;
    }

    /**
     * 获取汉字字符串的汉语拼音，英文字符不变
     */
    public static String getPinYin(String chines) {
        StringBuffer sb = new StringBuffer();
        sb.setLength(0);
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    sb.append(PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                sb.append(nameChar[i]);
            }
        }
        return sb.toString();
    }
}
