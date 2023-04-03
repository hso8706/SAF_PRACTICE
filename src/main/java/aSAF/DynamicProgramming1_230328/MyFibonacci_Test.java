package aSAF.DynamicProgramming1_230328;

public class MyFibonacci_Test {
	static int[] memo = new int[100];
	
	//topDown: recursion
	public static int fibo1(int n) {		
		if (n <= 1) //n==0, n == 1
			return n;
		//memorization 추가 작업
		else {
			if(memo[n] > 0){//값이 있는 경우
				return memo[n];
			}
		}
		
//		return fibo1(n - 1) + fibo1(n - 2);
		memo[n] = fibo1(n - 1) + fibo1(n - 2);
		return memo[n];
	}
	
	//bottomUp : loop
	public static int fibo2(int n) {
		memo[0] = 0;
		memo[1] = 1;
		
		for(int i =2; i <= n;i++) {
			memo[i] = memo[i-1] + memo[i-2];
		}		
		return memo[n];
	}
	
	public static void main(String[] args) {
		System.out.println(fibo1(5));
		System.out.println(fibo2(5));
	}
	
}
