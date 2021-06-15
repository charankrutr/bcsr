// "static void main" must be defined in a public class.
    
    class BCSRMat {
        
        int [] blockRowPtr;
        Value val; //?
        int [] cols;
        static int b; // block size
        int nb; // num of blocks
        int nnzb;
        
        static void print() {
            for (int i=0; i<temp.length; i++) {
                for (int j=0; j<temp[0].length; j++) {
                    System.out.print(temp[i][j] + "\t");
                }
                System.out.println();
            }
        }
        
        public BCSRMat(int b) {
            this.b = b;
        }
        
        void convertToBcsr(int [][] mat) {
        }
        
        static int [][] temp; 
        static void resetTemp() {
            temp = new int [b][b];
        }
        

        static boolean isNonZeroBlock(int [][] mat, int rs, int cs) {
            resetTemp();
            System.out.println(">> " + (rs+b));
            int nnz = 0;
            for (int i=rs, r=0; i<Math.min(rs+b, mat.length); i++, r++) {
                for (int j=cs, c=0; j<Math.min(cs+b, mat[0].length); j++, c++) {
                    //System.out.println("i =" +  i + " j=" + j);
                    //System.out.println("r =" +  r + " c=" + c);
                    temp[r][c] = mat[i][j];
                    if (mat[i][j] != 0)
                        nnz++;
                }
            }
            return nnz != 0;
        }
    }

    
    class Value {
        ArrayList<int [][]> val;
        // {  {1,2}  {2,3}  {1}
        //    {3,4}  {4,4}  {1}}
        List<int[]> size;
        // {{2,2} {2,2}  {2,1}}
    }

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        int [][] mat = {{11, 0, 0, 14}, 
                        {0, 22, 0, 0},
                        {0, 0, 33, 34},
                        {41, 0, 43, 44}};
        // enc(mat);
        // System.out.println();
        // dec();
        BCSRMat bcsrMat = new BCSRMat(2);
        System.out.println(bcsrMat.isNonZeroBlock(mat, 3, 3));
        bcsrMat.print();
        //convert(mat);
    }
    
//     static List <Integer> t = new ArrayList <> (); // rows
//     static List <Integer> b = new ArrayList <> (); // cols
//     static List <Integer> data = new ArrayList <> ();
//     static int m = 0, n =0;
//     static void enc(int [][] mat) {
//         for (int i=0; i<mat.length; i++) {
//             for (int j=0; j<mat[0].length; j++) {
//                 if (mat[i][j] != 0) {
//                     t.add(i);
//                     b.add(j);
//                     data.add(mat[i][j]);
//                 }
//             }
//         }
//         m = mat.length;
//         n = mat[0].length;
//         System.out.println(t);
//         System.out.println(b);
//         System.out.println(data);
        
//         // for (int i=0; i<data.length(); i++) {
//         //     y[t[i]] += data[i]*
//         // }
//     }
    
//     static void dec() {
//         int [][] mat = new int [m][n];
//         for (int i=0; i<data.size(); i++) {
//             mat[t.get(i)][b.get(i)] = data.get(i);
//         }
        
//         for (int i=0; i<m; i++) {
//             for (int j=0; j<n; j++) {
//                 System.out.print(mat[i][j] + "\t");
//             }
//             System.out.println();
//         }
//     }
    
    
}
