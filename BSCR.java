// "static void main" must be defined in a public class.
class Value {
    int [][] val;
    // {{1,2,3,4},{2,3,4,5}}
    int[][] size;
    // {{2,2} {2,2}  {2,1}}
    int valLen;

    public Value(int b, int nnzb) {
        // TODO: optimize b*b to appropirate size of the array during edge case.
        valLen = 0;
        //System.out.println("----" + nnzb);
        val = new int [nnzb][b*b];
        size = new int [nnzb][b*b];
    }
}

    
class BCSRMat {

    int [][] mat;
    int [] blockRowPtr;
    Value val;
    int [] cols;
    int b; // block size
    int nb; // num of blocks
    int nnzb;


    public BCSRMat(int [][] mat, int b) {
        this.mat = mat;
        this.b = b;
        nb = mat.length/b;
        populateNNZB(mat);
        val = new Value(b, nnzb);
        init(mat);
        populateBlockRowPtr(mat);
        int [] cols = new int [nnzb];
    }

    void init(int [][] mat) {
        for (int i=0; i<mat.length; i+=b) {
            for (int j=0; j<mat[0].length; j+=b) {
                if (isNonZeroBlock(i, j)) {
                    populateTempInVal(b, b);
                } 
            }
        }
    }
    
    void populateBlockRowPtr(int [][] mat) {
        blockRowPtr = new int [nb+1];
        int count = 0;
        int ind = 0;
        boolean blockChange = true;
        for (int i=0; i<mat.length; i+=b) {
            for (int j=0; j<mat[0].length; j+=b) {
                if (isNonZeroBlock(i, j)) {
                    if (blockChange) {
                        blockRowPtr[ind++] = count;
                    }     
                    count++;
                    blockChange = false;
                } 
            }
            blockChange = true;
        }
        blockRowPtr[ind] = count;
    }    
    
    void populateNNZB(int [][] mat) {
        int count = 0;
        for (int i=0; i<mat.length; i+=b) {
            for (int j=0; j<mat[0].length; j+=b) {
                if (isNonZeroBlock(i, j)) {
                    count++;
                } 
            }
        }
        nnzb = count;
        System.out.println("nnzb: " + nnzb);  
    }
    void populateTempInVal(int rb, int cb) {
        int ind = 0;
        for (int i=0; i<rb; i++) {
            for (int j=0; j<cb; j++) {
                val.val[val.valLen][ind++] = temp[i][j]; 
            }
        }
        val.valLen++;
    }

    void print() {
        // // temp
        // for (int i=0; i<temp.length; i++) {
        //     for (int j=0; j<temp[0].length; j++) {
        //         System.out.print(temp[i][j] + "\t");
        //     }
        //     System.out.println();
        // }
        // // values
        // for (int i=0; i<val.val.length; i++) {
        //     System.out.println(Arrays.toString(val.val[i]));
        // }
        // block row ptr
        System.out.println(Arrays.toString(blockRowPtr));
    }

    void convertToBcsr(int [][] mat) {
    }

    int [][] temp; 
    void resetTemp() {
        temp = new int [b][b];
    }


    boolean isNonZeroBlock(int rs, int cs) {
        resetTemp();
        int nnz = 0;
        for (int i=rs, r=0; i<Math.min(rs+b, mat.length); i++, r++) {
            for (int j=cs, c=0; j<Math.min(cs+b, mat[0].length); j++, c++) {
                temp[r][c] = mat[i][j];
                if (mat[i][j] != 0)
                    nnz++;
            }
        }
        return nnz != 0;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        int [][] mat = {{0, 0, 0, 0, 1}, 
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                        {41, 0, 0, 0, 0}};
        BCSRMat bcsrMat = new BCSRMat(mat, 2);
        //System.out.println(bcsrMat.isNonZeroBlock(3, 3));
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
