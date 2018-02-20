import java.io.*;
import java.util.Scanner;

class Sparse {
        public static void main(String[]args) throws IOException {
                Scanner in = null;
                PrintWriter out = null;
                String line = null;
                String[] token = null;
                int i, n=0;
                int lineNumber=1;

                if(args.length < 2) {
                        System.err.println("Usage: FileIO infile outfile");
                        System.exit(1);
                }

                in = new Scanner(new File(args[0]));
                out = new PrintWriter(new FileWriter(args[1]));
                int lineX, lineY;
                line = in.nextLine()+" ";
                token = line.split("\\s+");
                Matrix X = new Matrix(Integer.parseInt(token[0]));
                Matrix Y= new Matrix(Integer.parseInt(token[0]));

                lineX = Integer.parseInt(token[1]) + 2;
                lineY = Integer.parseInt(token[2]) + lineX + 1;
                lineNumber++;
                while(in.hasNextLine()){
                    line = in.nextLine();
                    token = line.split("\\s+");
                    System.out.println(lineNumber);
                    if(lineNumber > 2 && lineNumber <= lineX){
                            System.out.println("token[0]+ "+token[0]+"token 1: "+token[1]+"token 2: "+token[2]);
                            X.changeEntry(Integer.parseInt(token[0]), Integer.parseInt(token[1]), Double.parseDouble(token[2]));
                    }
                    if(lineNumber > (lineX+1) && lineNumber <= lineY){
                            System.out.println("token[0]+ "+token[0]+"token 1: "+token[1]+"token 2: "+token[2]);
                            Y.changeEntry(Integer.parseInt(token[0]), Integer.parseInt(token[1]), Double.parseDouble(token[2]));
                    }
                    lineNumber++;
            }
                out.println("A has " + X.getNNZ() + " non-zero entries:");
                out.println(X.toString());

                out.println("B has " + Y.getNNZ() + " non-zero entries:");
                out.println(Y.toString());

                out.println("(1.5)*A = ");
                out.println(X.scalarMult(1.5).toString());

                out.println("A+B =");
                out.println(X.add(Y).toString());

                out.println("A+A =");
                out.println(X.add(X).toString());

                out.println("B-A =");
                out.println(Y.sub(X).toString());

                out.println("A-A =");
                out.println(X.sub(X).toString());

                out.println("Transpose(A) = ");
                out.println(X.transpose().toString());

                out.println("A*B = ");
                out.println(X.mult(Y).toString());

                out.println("B*B = ");
                out.println(Y.mult(Y).toString());

                in.close();
                out.close();
        }
}
