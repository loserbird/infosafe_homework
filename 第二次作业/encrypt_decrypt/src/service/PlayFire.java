package service;

import java.util.*;

/**
 * @Author: bingqin
 * @Date: 2017/12/19
 * @Description:
 **/
public class PlayFire {

        public char[][] matrix;
        public String matrixStr;

        //playfire算法：输入关键字，构造5*5矩阵
        public char[][] makeMatrix(String str){
            char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',  'K', 'L', 'M', 'N', 'O',
                    'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
            char[][] matrix = new char[5][5];
            //去除重复的字符串
            Set<Character> set = new LinkedHashSet<>();
            for(Character c : str.toUpperCase().toCharArray()){
                set.add(c);
            }
            StringBuilder sb = new StringBuilder();
            for(Iterator<Character> iterator = set.iterator(); iterator.hasNext();){
                sb.append(iterator.next());
            }
            String tempstr = sb.toString();
            for(int i=0;i<25;i++){
                if(!tempstr.contains(chars[i]+"")){
                    sb.append(chars[i]);
                }
            }
            System.out.println(sb.toString());
            tempstr = sb.toString();
            this.matrixStr = tempstr;
            for(int i=0;i<5;i++){
                for (int j = 0;j<5;j++){
                    matrix[i][j] = tempstr.charAt(i*5+j);
                }
            }
            this.matrix = matrix;
            return matrix;
        }
        //打印矩阵
        private void printMatrix(char[][] martrix){
            for(int i=0;i<5;i++){
                for (int j = 0;j<5;j++){
                    System.out.print(martrix[i][j]+" ");
                }
                System.out.println();
            }
        }
        //将明文分组
        public List<String> group(String str){
            char[] strArr = str.toUpperCase().toCharArray();
            //先去掉所有非字母字符
            StringBuilder sb = new StringBuilder();
            for (char c : strArr){
                if(c<=90 && c>=65){
                    sb.append(c);
                }
            }
            String tempstr = sb.toString();
            List<String> groups = new ArrayList<>();
            for(int i=0;i<tempstr.length();i++){
                char a = tempstr.charAt(i);
                //到了最后一个字符
                if((i+1) == tempstr.length()){
                    groups.add(a+""+'K');
                    break;
                }
                char b =tempstr.charAt(i+1);
                if( a != b ){
                    groups.add(a+""+b);
                    i++;
                }else{
                    groups.add(a+""+'K');
                }
            }
            return groups;
        }
        //加密
        public List<String> encode(String str){
            List<String> groups = group(str);
            List<String> result = new ArrayList<>();
            for(int i=0;i<groups.size();i++){
                char a = groups.get(i).charAt(0);
                char b = groups.get(i).charAt(1);
                int arow = rows(a);
                int acol = cols(a);
                int brow = rows(b);
                int bcol = cols(b);
                char newa;
                char newb;
                if(arow == brow){            //如果在同一行
                    newa = matrix[arow-1][acol%5];
                    newb = matrix[brow-1][bcol%5];
                }else if(acol == bcol){            //如果在同一列
                    newa = matrix[arow%5][acol-1];
                    newb = matrix[brow%5][bcol-1];
                }else{
                    newa = matrix[arow-1][bcol-1];
                    newb = matrix[brow-1][acol-1];
                }
                result.add(newa+""+newb);
            }
            return result;
        }

        //加密
        public List<String> decode(List<String> groups){
            List<String> result = new ArrayList<>();
            for(int i=0;i<groups.size();i++){
                char a = groups.get(i).charAt(0);
                char b = groups.get(i).charAt(1);
                int arow = rows(a);
                int acol = cols(a);
                int brow = rows(b);
                int bcol = cols(b);
                char newa;
                char newb;
                if(arow == brow){            //如果在同一行
                    newa = matrix[arow-1][(acol-1==0?4:acol-2)%5];
                    newb = matrix[brow-1][(bcol-1==0?4:bcol-2)%5];
                }else if(acol == bcol){            //如果在同一列
                    newa = matrix[(arow-1==0?4:arow-2)%5][acol-1];
                    newb = matrix[(brow-1==0?4:brow-2)%5][bcol-1];
                }else{
                    newa = matrix[arow-1][bcol-1];
                    newb = matrix[brow-1][acol-1];
                }
                result.add(newa+""+newb);
            }
            return result;
        }
        //返回该字符在矩阵的行号
        public int rows(char c){
            int index = -1;
            //j需要特殊处理
            if(c == 'J'){
                index = matrixStr.indexOf('I');
            }else{
                index = matrixStr.indexOf(c);
            }
            return index/5+1;

        }
        //返回该字符在矩阵的列号
        public int cols(char c){
            int index = -1;
            //j需要特殊处理
            if(c == 'J'){
                index = matrixStr.indexOf('I');
            }else{
                index = matrixStr.indexOf(c);
            }
            return index%5+1;

        }
        public static void main(String[] args) {
            PlayFire service = new PlayFire();
            char[][] matrix = service.makeMatrix("monarchy");
            service.printMatrix(matrix);
            //System.out.println(service.rows('C'));
            //System.out.println(service.cols('H'));
            //List<String> list = service.group("balloon");
            List<String> list = service.group("we are discovered save yourself");
            System.out.println(list);
            List<String> result = service.encode("we are discovered save yourself");
            System.out.println(result);
            List<String> result2 = service.decode(result);
            System.out.println(result2);
        }
}
