    package solving;

    import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.io.OutputStreamWriter;
    import java.sql.Wrapper;
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.StringTokenizer;

    public class JUN1270_전쟁땅따먹기 {
        static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        static StringTokenizer st;

        static int n, Ti;
        static double[] Nij;

        public static void main(String[] args) throws IOException {
//            System.out.println(Integer.parseInt(String.valueOf(Math.pow(2, 31))));
            n = Integer.parseInt(bf.readLine());

            external: for (int i = 0; i < n; i++) {
                st = new StringTokenizer(bf.readLine());
                Ti = Integer.parseInt(st.nextToken());
                if(Ti==0){
                    bw.write("SYJKGW\n");
                    continue;
                }

                Nij = new double[Ti];

                for (int j = 0; j < Ti; j++) {
                    Nij[j] = Double.parseDouble(st.nextToken());
                }

                Arrays.sort(Nij);

                int cnt = 1;
                double flag = Nij[0];
                ArrayList<Double> tempList = new ArrayList<>();
                for (int j = 1; j < Ti; j++) {
                    if(tempList.contains(Nij[j])) continue;
                    if(Nij[j] != flag) {
                        flag = Nij[j];
                        cnt = 1;
                    }
                    else {
                        cnt++;
                        if(cnt >= (double) Ti/2) {
                            tempList.add(Nij[j]);
                            cnt = 0;
//                            bw.write((int)Nij[j]+"\n");
//                            continue external;
                        }
                    }
                }

                if(tempList.size()==1) {
                    double result = tempList.get(0);
                    int result1 = (int) result;
                    bw.write(result1+"\n");
                }
                else bw.write("SYJKGW\n");

            }
            bw.flush();
            bw.close();
        }

    }
