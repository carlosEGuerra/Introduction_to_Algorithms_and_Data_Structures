package assignment01;

public class Matrix
{
    int numRows;
    int numColumns;
    int data[][];

    // Constructor with data for new matrix (automatically determines
    // dimensions)
    public Matrix (int data[][])
    {
        numRows = data.length; // d.length is the number of 1D arrays in the 2D
                               // array
        if (numRows == 0)
        {
            numColumns = 0;
        }
        else
        {
            numColumns = data[0].length; // d[0] is the first 1D array
        }
        this.data = new int[numRows][numColumns]; // create a new matrix to hold
                                                  // the data
        // copy the data over
        for (int i = 0; i < numRows; i++)
        {
            for (int j = 0; j < numColumns; j++)
            {
                this.data[i][j] = data[i][j];
            }
        }
    }

    @Override // instruct the compiler that we do indeed intend for this method
              // to override the superclass' (Object) version
    public boolean equals (Object other)
    {
        if (!(other instanceof Matrix))
        {
            // make sure the Object we're comparing to is a Matrix
            return false;
        }
        Matrix matrix = (Matrix) other; // if the above was not true, we know it's safe to treat 'o' as a Matrix

        if (matrix.numRows != this.numRows || matrix.numColumns != this.numColumns)
            return false;

        // does the equality
        for (int i = 0; i < matrix.numRows; i++)
        {
            for (int j = 0; j < matrix.numColumns; j++)
            {
                if (this.data[i][j] != matrix.data[i][j])
                    return false;
            }
        }
        return true; // placeholder
    }

    @Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object)
              // version
    public String toString ()
    {
        String returnString = new String("");

        // does the calculations and placing of calculations
        for (int i = 0; i < this.numRows; i++)
        {
            for (int j = 0; j < this.numColumns; j++)
            {
                returnString += this.data[i][j] + " ";
            }
            returnString += "\n";
        }
        return returnString; // placeholder
    }

    public Matrix times (Matrix matrix)
    {
        if (this.numColumns != matrix.numRows)
            return null;
        // initiallizes a matrix of appropriate size
        Matrix returnMatrix = new Matrix(new int[this.numRows][matrix.numColumns]);

        // does the calculations and placing of calculations
        for (int i = 0; i < this.numRows; i++)
        {
            for (int j = 0; j < matrix.numColumns; j++)
            {
                int tempVar = 0;
                for (int p = 0; p < matrix.numRows; p++)
                {
                    tempVar += this.data[j][p] * matrix.data[p][i];
                }
                returnMatrix.data[j][i] = tempVar;
            }
        }

        return returnMatrix; // return statements
    }

    public Matrix plus (Matrix matrix)
    {
        if (matrix.numColumns != this.numColumns || matrix.numRows != this.numRows)
            return null;
        Matrix returnMatrix = new Matrix(new int[matrix.numRows][matrix.numColumns]);

        // does the calculations and placing of calculations
        for (int i = 0; i < this.numRows; i++)
        {
            for (int j = 0; j < this.numColumns; j++)
            {
                returnMatrix.data[i][j] = this.data[i][j] + matrix.data[i][j];
            }
        }
        return returnMatrix; // placeholder
    }
}
