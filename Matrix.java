import java.util.*;
public class Matrix {
        List[]row;
        int NNZ = 0;
        Matrix(int n) {
                if(n<1) {
                        throw new  RuntimeException("Error: cannot find matrix for negative value ");
                }
                else {
                        row=new List[n+1];
                        for(int i = 1; i<n+1; i++) {
                                row[i]=new List();
                        }


                }
        }
        private class Entry {
                int col;
                double value;
                Entry(int col, double value) {
                        this.col=col;
                        this.value=value;
                }
                public boolean equals(Object x) {
                        boolean equal = false;
                        equal = (x instanceof Entry);
                        if (!equal) return equal;
                        Entry X = (Entry) x;
                        equal=(X.col==this.col && X.value==this.value);
                        return equal;
                }
                public String toString() {
                        return "("+col+", "+value+")";
                }
        }
        int getSize() {
                return row.length-1;

        }
        int getNNZ() {
            return NNZ;
    }
    @Override public boolean equals(Object x) {
            boolean equal = false;
            Matrix X;
            int index = 1;
            if(!(x instanceof Matrix)) { return false; }
            X = (Matrix) x;
            equal = (this.getSize() == X.getSize());
            while (equal && index<=getSize()) {
                    equal = this.row[index].equals(X.row[index]);
                    index++;
            }
            return equal;

    }
    void makeZero() {
            for(int i=1; i<=getSize();i++) {
                    row[i].clear();
            }
            NNZ=0;
    }
    Matrix copy() {
            Matrix m = new Matrix(getSize());
            for(int i = 1; i<=getSize(); i++) {
                    row[i].moveFront();
                    while(row[i].index()>=0) {
                            Entry A = (Entry)row[i].get();
                            m.changeEntry(i,A.col,A.value);
                            row[i].moveNext();
                    }
            }
            return m;
    }
    public void changeEntry(int i, int j, double x) {

        if (i < 1 || i > getSize() || j < 1 || j > getSize())
        {
                throw new RuntimeException(
                                "Matrix error: changeEntry() called with row/col arguments that are out of bounds.");
        }
        row[i].moveFront();
        while(row[i].index()>=0) {
                Entry A = (Entry)row[i].get();
                if(A.col==j) {
                        if(x==0) {
                                row[i].delete();
                                NNZ--;
                        }
                        else if(x>0)  {
                                A.value=x;
                        }
                        return;
                }
                if(A.col>j) {
                        if(x!=0) {
                                row[i].insertBefore(new Entry(j,x));
                                NNZ++;
                        }
                        return;
                }
                row[i].moveNext();
        }
        if(x!=0) {
                row[i].append(new Entry(j,x));
                NNZ++;
        }
}
    Matrix scalarMult(double x) {
        Matrix m = new Matrix(getSize());
        for(int i = 1; i<=getSize(); i++) {
                row[i].moveFront();
                while(row[i].index()>=0) {
                        m.changeEntry(i,((Entry)row[i].get()).col,((Entry)row[i].get()).value*x);
                        row[i].moveNext();
                }
        }
        return m;

}
    Matrix add(Matrix M) {
        if (this == M) return M.scalarMult(2);
        if(M.getSize()!=getSize()) {
                throw new RuntimeException("Error: cannot add() to matrices of different dimensions");
        }
        Matrix addition = new Matrix(getSize());
        for(int i = 1; i<=getSize(); i++) {
                row[i].moveFront();
                M.row[i].moveFront();
                while(row[i].index()>=0 || M.row[i].index()>=0) {
                        Entry A = row[i].index() >= 0 ? (Entry) row[i].get() : null;
                        Entry B = M.row[i].index() >= 0 ? (Entry) M.row[i].get() : null;
                        if(A == null && B != null) {
                                addition.changeEntry(i, B.col, B.value);
                                M.row[i].moveNext();
                        }
                        else if(A != null && B == null) {
                                addition.changeEntry(i, A.col, A.value);
                                row[i].moveNext();
                        }
                        else {
                                if(A.col<B.col) {
                                        addition.changeEntry(i,A.col,A.value);
                                        row[i].moveNext();
                                }

                                else if(A.col == B.col) {
                                        addition.changeEntry(i,A.col,A.value+B.value);
                                        row[i].moveNext();
                                        M.row[i].moveNext();
                                }
                                else {
                                        addition.changeEntry(i, B.col,B.value);
                                        M.row[i].moveNext();
                                }

                        }
                }
        }
        return addition;
}
    Matrix sub(Matrix M) {
        if (this == M) return new Matrix(this.getSize());
        if(M.getSize()!=getSize()) {
                throw new RuntimeException("Error: cannot add() to matrices of different dimensions");
        }
        Matrix subtraction = new Matrix(getSize());
        for(int i = 1; i<=getSize(); i++) {
                row[i].moveFront();
                M.row[i].moveFront();
                while(row[i].index()>=0 || M.row[i].index()>=0) {
                        Entry A = row[i].index() >= 0 ? (Entry) row[i].get() : null;
                        Entry B = M.row[i].index() >= 0 ? (Entry) M.row[i].get() : null;
                        if(A == null && B != null) {
                                subtraction.changeEntry(i, B.col, (-1)*B.value);
                                M.row[i].moveNext();
                        }
                        else if(A != null && B == null) {
                                subtraction.changeEntry(i, A.col, A.value);
                                row[i].moveNext();
                        }
                        else {
                                if(A.col<B.col) {
                                        subtraction.changeEntry(i,A.col,A.value);
                                        row[i].moveNext();
                                }

                                else if(A.col == B.col) {
                                        subtraction.changeEntry(i,A.col,A.value-B.value);
                                        row[i].moveNext();
                                        M.row[i].moveNext();
                                }
                                else {
                                        subtraction.changeEntry(i, B.col,(-1)*B.value);
                                        M.row[i].moveNext();
                                }

                        }
                }
        }
        return subtraction;
}
    Matrix transpose() {
        Matrix A = new Matrix(getSize());
        for(int i = 1; i<=getSize(); i++) {
                row[i].moveFront();
                while(row[i].index()>=0) {
                        A.changeEntry(((Entry)row[i].get()).col,i,((Entry)row[i].get()).value);
                        row[i].moveNext();
                }
        }
        return A;
}

Matrix mult(Matrix M) {
        if (getSize() != M.getSize()) {
                throw new RuntimeException(
                                "Matrix Error: Cannot multiply two square matrices of different dimensions.");
        }
        Matrix multiply = new Matrix(getSize());
        Matrix newM = M.transpose();
        int product=0;
        int k = 1;
        for(int i = 1; i<=getSize(); i++) {
                if(row[i].length()==0) {
                        continue;
                }
                for(int j = 1; j<=getSize();j++) {
                        if(newM.row[j].length() == 0) {
                                continue;
                        }
                        multiply.changeEntry(i,j,dot(row[i],newM.row[j]));
                }
        }

        return multiply;

}
private static double dot(List P, List Q) {
    double prod = 0.0;
    P.moveFront();
    Q.moveFront();
    while(P.index()>=0 && Q.index()>=0) {
            Entry A = (Entry)P.get();
            Entry B = (Entry)Q.get();
            if(A.col>B.col) {
                    Q.moveNext();
            }
            else if(A.col==B.col) {
                    prod+=(A.value*B.value);
                    P.moveNext();
                    Q.moveNext();
            }
            else {
                    P.moveNext();
            }

    }
    return prod;
}


public String toString() {
    StringBuffer sb = new StringBuffer();

    for(int i = 1; i<=getSize(); i++) {

            row[i].moveFront();

            if (row[i].length() != 0) {
                    sb.append(String.valueOf(i));
                    sb.append(": ");
                    sb.append(row[i].toString());
                    sb.append("\n");
            }
    }
    return new String(sb);
}

}
