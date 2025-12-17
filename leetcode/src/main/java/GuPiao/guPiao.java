package GuPiao;

public class guPiao {
    public static void main(String[] args) {

    }
    //leetcode-123 买卖股票的最佳时间
    //通用方法，参数签名：能够进行交易的次数，价格数组
    public int commend(int k,int[] prices){
        int n = 2*k;
        //这个dp的长度是2倍的交易次数，单数为买入的日子，双数为卖出的日子
        int[] dp = new int[n];
        for(int i = 0;i<n;i++){
            if(i%2 == 0){
                dp[i] = Integer.MIN_VALUE;
            }
        }
        for(int p:prices){
            //初始化0和1，也就是第一天的数据
            dp[0] = Math.max(dp[0],0-p);
            dp[1] = Math.max(dp[1],dp[0]+p);
            for(int i = 2;i<2*k;i+=2){
                //今日买入=今天最大收益、昨天的收益+今天卖的钱
                dp[i] = Math.max(dp[i],dp[i-1]-p);
                //今日卖出=今日最大收益、昨天的收益-今天买的钱
                dp[i+1] = Math.max(dp[i+1],dp[i]+p);
            }
        }
        return dp[2*k-1];
    }
}
