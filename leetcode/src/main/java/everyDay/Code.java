package everyDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code {
    public static void main(String[] args) {
        int[][] nums = new int[][]{{1,3},{3,7},{8,9}};
        intersectionSizeTwo(nums);
    }
    public static int minOperations(int[] nums) {
        List<Integer> s = new ArrayList<>();
        int res = 0;
        for (int a : nums) {
            while (!s.isEmpty() && s.get(s.size() - 1) > a) {
                s.remove(s.size() - 1);
            }
            if (a == 0) continue;
            if (s.isEmpty() || s.get(s.size() - 1) < a) {
                res++;
                s.add(a);
            }
        }
        return res;
    }
    public static int maxOperations(String s) {
        int n = s.length();
        int res = 0;
        int index = 0;
        //之前节点一共的1
        int before = 0;
        //遍历到每一个1进行本身的判断，移动后再加上之前节点所有的1的数量。
        while(index<n && s.charAt(index) != '1'){
            index++;
        }
        while(index<n){
            //尾部处理
            if(index == n-1 && s.charAt(index) == '1'){
                return res;
            }else if(index == n-1 && s.charAt(index) == '0'){
                return res+before;
            }
            //连1跳过
            if(index<n-1 && s.charAt(index+1) == '1'){
                before++;
                index++;
                continue;
            }
            //找到0位置
            int right = index;
            while(right<n-1 && s.charAt(right+1) == '0'){
                right++;
            }
            //更新
            res++;
            res+=before;
            before++;
            index = right+1;
        }
        return res;
    }
    //717-思路：遇到1跳两步，遇到0跳一步，看是否跳过了最后的0，如果跳过了则为false，否则为true。
    public static boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        int i = 0;
        while(true){
            if(i == n){
                return false;
            }
            if(i == n-1){
                return true;
            }
            if(bits[i] == 1){
                i+=2;
            }else{
                i++;
            }
        }
    }
    //leetcode757，先按照左侧排序，然后贪心取就可以了
    public static int intersectionSizeTwo(int[][] intervals) {
        int n = intervals.length;
        int res = 0;
        int m = 2;
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        List<Integer>[] temp = new List[n];
        for (int i = 0; i < n; i++) {
            temp[i] = new ArrayList<Integer>();
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = intervals[i][0], k = temp[i].size(); k < m; j++, k++) {
                res++;
                help(intervals, temp, i - 1, j);
            }
        }
        return res;
    }

    public static void help(int[][] intervals, List<Integer>[] temp, int pos, int num) {
        for (int i = pos; i >= 0; i--) {
            if (intervals[i][1] < num) {
                break;
            }
            temp[i].add(num);
        }
    }
}
