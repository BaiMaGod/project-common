package com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 检查类
 */
public class CheckUtil {


    /**
     * 检查账号合法性
     *        1. 账号长度：5-16
     *        2. 账号不能包含非法字符*，&，#，@
     * @return
     */
    public static boolean checkNumber(String number){
        if(number.length() < 5 || number.length() > 16){
            return false;
        }
        if(number.indexOf('*')>=0 || number.indexOf('&')>=0 || number.indexOf('#')>=0 || number.indexOf('@')>=0){
            return false;
        }

        return true;
    }

    /**
     * 检查密码合法性
     *       1. 密码长度：6-16
     *       2. 密码必须包含数字和英文
     * @return
     */
    public static boolean checkPassword(String password){
        if(password.length() < 6 || password.length() > 16){
            return false;
        }
        boolean num = false,word = false;
        for (int i = 0; i < password.length(); i++) {
            if(password.charAt(i)>=48 && password.charAt(i)<=57){   // 判断是否有数字
                num = true;
            }else if( (password.charAt(i)>=65 && password.charAt(i)<=90) || (password.charAt(i)>=97 && password.charAt(i)<=122)) {    // 判断是否有字母（大小写）
                word = true;
            }
        }
        if( !(num && word)){   // 如果有一个没有包含，返回不合法
            return false;
        }

        return true;
    }

    /**
     * 检查 字符串 是否全是数字
     * @return
     */
    public static boolean checkIsNumber(String str){
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i)<48 && str.charAt(i)>57){   // 如果不是数字
                return false;
            }
        }

        return true;
    }

    /**
     * 检查 字符串 是否是 int
     * @return
     */
    public static boolean checkIsInt(String str){
        if (str.length() < 11 && checkIsNumber(str)) {
            return true;
        }

        return false;
    }

    /**
     * 验证邮箱格式
     * @param string
     * @return
     */
    public static boolean isEmail(String string) {
        if (string == null)
            return false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches())
            return true;
        else
            return false;
    }
}
