    package solving;

    import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.io.OutputStreamWriter;
    import java.util.Arrays;
    import java.util.StringTokenizer;

    public class JUN1270_전쟁땅따먹기 {
        static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        static StringTokenizer st;

        static int n, Ti;
        static int[] Nij;

        public static void main(String[] args) throws IOException {
    //        System.out.println(Math.pow(2, 31)*2 <= Integer.MAX_VALUE);
            n = Integer.parseInt(bf.readLine());

            external: for (int i = 0; i < n; i++) {
                st = new StringTokenizer(bf.readLine());
                Ti = Integer.parseInt(st.nextToken());
                Nij = new int[Ti];

                for (int j = 0; j < Ti; j++) {
                    Nij[j] = Integer.parseInt(st.nextToken());
                }

                Arrays.sort(Nij);

                int cnt = 0;
                int flag = Nij[0];
                for (int j = 1; j < Ti; j++) {
                    if(Nij[j] != flag) {
                        flag = Nij[j];
                        cnt = 0;
                    }
                    else {
                        cnt++;
                        if(cnt >= Ti/2) {
                            System.out.println(Nij[j]);
                            continue external;
                        }
                    }
                }
                System.out.println("SYJKGW");
            }
        }

    }
