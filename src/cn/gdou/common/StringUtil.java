package cn.gdou.common;

public class StringUtil {
    public static boolean strIsNull(String str){
        if(str == null||str.length()<=0||str.isEmpty()||"".equals(str)){
            return true;
        }else {
            return false;
        }
    }
}
