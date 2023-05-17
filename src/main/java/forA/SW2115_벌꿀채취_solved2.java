package forA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2 단계로 나우어서 생각
//   1단계 : 두 명의 시작 위치를 어떻게 선택할 것인가? => 모든 가능한 위치에서 2개의 위치를 선택 (순서 X) 한 후, 규칙에 위한되는 버릴 것은 버린다. 조합
//   2단계 : 각각의 위치에서 최대 벌꿀 채취는 어떻게 할 것인가? => 채취가 가능한 벌꿀통 중 어떤 것을 몇 개 선택하는 것이 최대값인 지 따진다. 부분집합
// memoization : price
public class SW2115_벌꿀채취_solved2 {

	static int T, N, M, C, max;
	static int[][] map;
	static int[] tgt, price; // src 로부터 2개의 시작점을 뽑는 조합을 생각할 때, 선택된 2개의 위치가 저장되는 공간
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			tgt = new int[2];
			price = new int[N*N];
			
			max = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 입력 완료
			
			comb(0, 0);
			System.out.println("#" + t + " " + max);
		}
	}

	static void calcMax(int idx) { // N*N 자리를 1열로 고려, idx 위치에서 연속적인 M 의 벌꿀 중 최대를 고려
		// 부분집합
		dfs( idx, idx + M, 0, idx, 0 );
	}
	// sum : 자리에서 채취하는 벌꿀
	// priceSum : 누적 채취하는 벌꿀
	static void dfs(int srcIdx, int tgtIdx, int sum, int originIdx, int priceSum) {
		// 기저조건
		if( srcIdx == tgtIdx ) return;
		
		// 벌꿀 채취
		int val = map[srcIdx/N][srcIdx%N];
		int currPriceSum = 0;
		if( sum + val <= C ) {
			currPriceSum = priceSum + (int) Math.pow(val, 2);
			price[originIdx] = Math.max(price[originIdx], currPriceSum);
		}
		
		dfs( srcIdx + 1, tgtIdx, sum, originIdx, priceSum ); // 비선택
		dfs( srcIdx + 1, tgtIdx, sum + val, originIdx, currPriceSum ); // 선택
	}
	
	static void comb(int srcIdx, int tgtIdx) {
		// 기저조건
		if( tgtIdx == 2 ) { // tgt 갯수만큼 선택완료
			// complete code
			// 두 위치가 겹치는 지 확인
			// 앞 자리 tgt[0] 부터 M 거리안에 tgt[1] 없으면 된다.
			if( tgt[0] < tgt[1] && tgt[1] <= tgt[0] + M - 1 ) return;
			
			// 같은 행에 있는지
			if( tgt[0] / N != (tgt[0] + M - 1) / N ) return;
			if( tgt[1] / N != (tgt[1] + M - 1) / N ) return;
			
			
			// 벌꿀채취를 위해 선택된 tgt[0], tgt[1] 두 위치는 1단계 통과
			// 2단계
			// 각자 위치에서 최대값을 가져온다. 그런 뒤 2개를 합하여 큰 값을 max 와 비교해서 선택
			if( price[ tgt[0] ] == 0 ) calcMax( tgt[0] );
			if( price[ tgt[1] ] == 0 ) calcMax( tgt[1] );
			max = Math.max( max,  price[ tgt[0] ] + price[ tgt[1] ] );
			return;
		}
		
		// 기저조건
		if( srcIdx == N*N) return; // src 로부터 선택하는데 src 를 모두 다 고려했다.
		
		tgt[tgtIdx] = srcIdx; // 좌표의 index로 생각
		
		// 선택과 비선택
		comb( srcIdx + 1, tgtIdx + 1); // 현재 선택을 만족하고 tgt의 다음 선택을 고려
		comb( srcIdx + 1, tgtIdx); // 현재 선택을 만족 하지 않고 tgt의 현재를 다시 고려
		
	}
}











