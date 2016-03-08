/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment01;

import java.lang.reflect.Array;

public class MatrixTester
{
    public static void main (String[] args)
    {
        Matrix M1 = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });

        Matrix M2 = new Matrix(new int[][] { { 4, 5 }, { 3, 2 }, { 1, 1 } });

        Array testCase = null;

        Matrix additionSolution = new Matrix(new int[][] { { 2, 4, 6 }, { 4, 10, 12 } });

        // this is the known correct result of multiplying M1 by M2
        Matrix referenceMultiply = new Matrix(new int[][] { { 13, 12 }, { 29, 26 } });

        /*
         * Note that none of the tests below will be correct until you have implemented all methods. This is just one
         * example of a test, you must write more tests and cover all cases.
         */
        // tests toString method
        System.out.println(M1.toString());

        // tests matrix addition
        if ((M1.plus(M1)).equals(additionSolution))
            System.out.println("Plus WORKS! \n");

        // test for equals
        if (M1.equals(M1))
            System.out.println("Equals WORKS!");

        if (!(M1.equals(testCase)))
            System.out.println("The types don't match and it was caught!\n");

        // get the matrix computed by your times method
        Matrix computedMultiply = M1.times(M2);

        // exercises your toString method
        System.out.println("Computed result for M1 * M2:\n" + computedMultiply);

        // exercises your .equals method, and makes sure that your computed result is the same as the known correct
        // result
        if (!referenceMultiply.equals(computedMultiply))
        {
            System.out.println("Should be:\n" + referenceMultiply);
        }

    }
}
