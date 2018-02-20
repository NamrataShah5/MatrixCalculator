# MatrixCalculator
The purpose of this project was to create a calculator for performing matrix operations, where there can be “sparseness” of matrix operands. A matrix is “Sparse” when the number of non-zero entries is small compared to the total number of entries, n2. I wanted to learn how to write manipulation operations when there are more restrictions in an ADT (e.g. empty cells in a matrix).

What each file does:

List: Altered my pre-existing List from other projects to take Objects instead of ints

Sparse: Main class that reads in size of matrix, operations to be performed, and elements to be stored from an input file. 

MatrixClient.java: Testing client for Matrix ADT.

Matrix.java: Constructs Matrix and Entry. The Matrix implements an Array of Lists. Operations allows us to get the size of the matrix, the number of non-zero entries, clear the entire matrix, change the entries of the matrix, perform scalar multiplication, addition, subtraction, multiplication, transpose, and dot product on the matrix. These operations have to performed while keeping in considerations that the cells that are empty will be skipped over, but must still be used (considered as 0) in order to perform arithmetic operations.
