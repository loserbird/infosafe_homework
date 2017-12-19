package service;

/**
 * @Author: bingqin
 * @Date: 2017/12/19
 * @Description:
 **/
public class Caesar {
    //凯撒密码加密
    public  String encrypt(String str,int k){
        char[] strchararr = str.toUpperCase().toCharArray();
        for(int i=0;i<strchararr.length;i++){
            if(strchararr[i] >= 65 && strchararr[i] <= 90 ){
                if((strchararr[i]+k) <= 90){
                    strchararr[i] = (char)(strchararr[i]+k);
                }else {
                    strchararr[i] = (char) (64+((strchararr[i] + k) % 90));
                }
            }
        }
        return String.valueOf(strchararr);
    }
    //凯撒密码解密
    public  String decrypt(String str,int k){
        char[] strchararr = str.toCharArray();
        for(int i=0;i<strchararr.length;i++){
            if(strchararr[i] >= 65 && strchararr[i] <= 90 ){
                if((strchararr[i]-k) >= 65){
                    strchararr[i] = (char)(strchararr[i]-k);
                }else {
                    strchararr[i] = (char) (90 - (64-strchararr[i]+k));
                }
            }
        }
        return String.valueOf(strchararr).toLowerCase();
    }



    public static void main(String[] args) {
        Caesar service = new Caesar();
        String caesarStr = service.encrypt("abc",3);
        System.out.println(caesarStr);
        System.out.println(service.decrypt(caesarStr,3));
    }
}
