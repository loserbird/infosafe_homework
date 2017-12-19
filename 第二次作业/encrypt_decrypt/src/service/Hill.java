package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: bingqin
 * @Date: 2017/12/19
 * @Description:
 **/
public class Hill {

    public int[][] secret = {
            {17, 17, 5},
            {21, 18, 21},
            {2, 2, 19}
    };
    public int[][] reversemartrix;

    static int[] temp = {
            1, 0, 0,
    };

    //三个一组
    private char[][] groups(String str){
        StringBuilder sb = new StringBuilder();
        String strup = str.toUpperCase();
        //去掉空格
        for(int i=0;i<str.length();i++){
            char c = strup.charAt(i);
            if(c<=90 && c>=65){
                sb.append(c);
            }
        }
        str = sb.toString();
        int length = (str.length()+2)/3;
        char[][] result = new char[length][3];
        for(int i=0;i<length;i++){
            for(int j=0;j<3;j++){
                if((i*3+j) >= str.length()){
                    result[i][j] = 'X';
                }else{
                    result[i][j] = str.charAt(i*3+j);
                }

            }
        }

        return  result;
    }

    //将a表示为0，返回明文分组之后的数字表示
    public List<Integer[]> groups(char[][] result){
        List<Integer[]> list = new ArrayList<Integer[]>();
        for(int i=0;i<result.length;i++){
            Integer[] temparr = new Integer[3];
            for(int j=0;j<3;j++){
                temparr[j] = result[i][j]-65;
            }
            list.add(temparr);
        }
        return list;
    }

    //随机生成密钥，3*3矩阵
    public int[][] generateSec(){
        int[][] result = new int[3][3];
        Random random = new Random();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                result[i][j] = random.nextInt(30);
            }
        }
        this.secret = result;
        getReverseMartrix(result);
        return result;
    }

    //加密
    public List<Integer[]> encrypt(String str){
        char[][] groups = groups(str);
        List<Integer[]> textlist = groups(groups);
        List<Integer[]> result = new ArrayList<>();
        for(int i=0;i<textlist.size();i++){
            result.add(caculate(textlist.get(i),secret));
        }
        return result;
    }
    //解密
    public List<Integer[]> decrypt(List<Integer[]> encodelist){
        List<Integer[]> result = new ArrayList<>();
        for(int i=0;i<encodelist.size();i++){
            result.add(caculate(encodelist.get(i),reversemartrix));
        }
        return result;
    }
    //计算三元数组与3元矩阵的值，返回三元数组
    public Integer[] caculate(Integer[] item,int[][] secret){
        Integer[] result = new Integer[3];
        for(int i=0;i<3;i++){
            int sum = 0;
            for(int j=0;j<3;j++){
                sum += secret[i][j] * item[j];
            }
            result[i] = sum%26;
            sum = 0;
        }
        return result;
    }

    //得到矩阵的逆矩阵
    public int[][] getReverseMartrix(int[][] key2) {
        int[][] key = new int[key2.length][key2[0].length];
        for(int num=0; num<3; num++)
            for(int i=0; i<26; i++)
                for(int j=0; j<26; j++)
                    for(int k=0; k<26; k++) {
                        if((i*17+j*21+k*2)%26 == temp[num%3]
                                && ((i*17+j*18+k*2)%26 == temp[(num+2)%3])
                                && ((i*5+j*21+k*19)%26 == temp[(num+1)%3])) {
                            key[num][0] = i;
                            key[num][1] = j;
                            key[num][2] = k;
                        }
                    }
        this.reversemartrix = key;
        return key;
    }

    //将list<Integer[]>转换为List<String>
    public List<String> change(List<Integer[]> list){
        List<String> result = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            Integer[] temparr = list.get(i);
            char a = (char)(temparr[0]+65);
            char b = (char)(temparr[1]+65);
            char c = (char)(temparr[2]+65);
            result.add(a+""+b+c);
        }
        return result;
    }

    public static void main(String[] args) {
        Hill service = new Hill();
       /* System.out.println("生成密钥");
        int[][] secret = service.generateSec();
        for(int i=0;i<3;i++){
            for( int j=0;j<3;j++){
                System.out.print(secret[i][j]+" ");
            }
            System.out.println();
        }*/
        /*int[][] data = {
                {17,17,5 },
                {21,18,21},
                {2,2,19}
        };*/
        System.out.println("生成密钥的逆矩阵");
        int[][] reverseSec = service.getReverseMartrix(service.secret);
        for(int i=0;i<reverseSec.length;i++){
            for(int j=0;j<reverseSec[0].length;j++){
                System.out.print(reverseSec[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("为明文分组");
        char[][] result = service.groups("pay more mon");
        for(int i=0;i<result.length;i++){
            for( int j=0;j<3;j++){
                System.out.print(result[i][j]);
            }
            System.out.println();
        }
        System.out.println("明文分组后的矩阵表示");
        List<Integer[]> list = service.groups(result);
        for(int i=0;i<list.size();i++){
            Integer[] temparr = list.get(i);
            for(int j=0;j<3;j++){
                System.out.print(temparr[j]+" ");
            }
            System.out.println();
        }
        System.out.println("加密");
        List<Integer[]> encodeArr = service.encrypt("pay more mon");
        service.print(encodeArr);
        List<String> encodestr = service.change(encodeArr);
        for(String str : encodestr){
            System.out.println(str);
        }
        System.out.println("解密");
        List<Integer[]> decodeArr = service.decrypt(encodeArr);
        service.print(decodeArr);
        List<String> decodeStr = service.change(decodeArr);
        for(String str:decodeStr){
            System.out.println(str);
        }
    }

    public void print(List<Integer[]> list){
        for(int i=0;i<list.size();i++){
            Integer[] temparr = list.get(i);
            for(int j=0;j<temparr.length;j++){
                System.out.print(temparr[j]+" ");
            }
            System.out.println();
        }
    }
}
