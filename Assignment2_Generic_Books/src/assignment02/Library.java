package assignment02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class representation of a library (a collection of library books).
 * 
 * @author Atul Sherma, Carlos Guerra
 */
public class Library
{

    private ArrayList<LibraryBook> library;

    public Library ()
    {
        library = new ArrayList<LibraryBook>();
    }

    /**
     * Add the specified book to the library, assume no duplicates.
     * 
     * @param isbn -- ISBN of the book to be added
     * @param author -- author of the book to be added
     * @param title -- title of the book to be added
     */
    public void add (long isbn, String author, String title)
    {
        library.add(new LibraryBook(isbn, author, title));
    }

    /**
     * Add the list of library books to the library, assume no duplicates.
     * 
     * @param list -- list of library books to be added
     */
    public void addAll (ArrayList<LibraryBook> list)
    {
        library.addAll(list);
    }

    /**
     * Add books specified by the input file. One book per line with ISBN, author, and title separated by tabs.
     * 
     * If file does not exist or format is violated, do nothing.
     * 
     * @param filename
     */
    public void addAll (String filename)
    {
        ArrayList<LibraryBook> toBeAdded = new ArrayList<LibraryBook>();

        try (Scanner fileIn = new Scanner(new File(filename)))
        {

            int lineNum = 1;

            while (fileIn.hasNextLine())
            {
                String line = fileIn.nextLine();

                try (Scanner lineIn = new Scanner(line))
                {
                    lineIn.useDelimiter("\\t");

                    if (!lineIn.hasNextLong())
                    {
                        throw new ParseException("ISBN", lineNum);
                    }
                    long isbn = lineIn.nextLong();

                    if (!lineIn.hasNext())
                    {
                        throw new ParseException("Author", lineNum);
                    }
                    String author = lineIn.next();

                    if (!lineIn.hasNext())
                    {
                        throw new ParseException("Title", lineNum);
                    }
                    String title = lineIn.next();
                    toBeAdded.add(new LibraryBook(isbn, author, title));
                }
                lineNum++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.err.println(e.getMessage() + " Nothing added to the library.");
            return;
        }
        catch (ParseException e)
        {
            System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
                    + ". Nothing added to the library.");
            return;
        }

        library.addAll(toBeAdded);
    }

    /**
     * Returns the holder of the library book with the specified ISBN.
     * 
     * If no book with the specified ISBN is in the library, returns null.
     * 
     * @param isbn -- ISBN of the book to be looked up
     */
    public String lookup (long isbn)
    {

        // cycles through the arraylist of library
        for (int i = 0; i < library.size(); i++)
        {
            // compares the isbn's
            if (library.get(i).getIsbn() == isbn)
            {
                return library.get(i).getHolder();
            }
        }
        return null;
    }

    /**
     * Returns the list of library books checked out to the specified holder.
     * 
     * If the specified holder has no books checked out, returns an empty list.
     * 
     * @param holder -- holder whose checked out books are returned
     */
    public ArrayList<LibraryBook> lookup (String holder)
    {
        // Creates an array that will output the library books checked out by the holder
        ArrayList<LibraryBook> temp_array = new ArrayList<LibraryBook>();

        // Cycles through the library
        for (int i = 0; i < library.size(); i++)
        {
            // compares the holder to see if they are null and if they have the holder searched for
            if (library.get(i).getHolder() != null && library.get(i).getHolder().equals(holder))
            {
                temp_array.add(library.get(i));
            }
        }
        return temp_array;
    }

    /**
     * Sets the holder and due date of the library book with the specified ISBN.
     * 
     * If no book with the specified ISBN is in the library, returns false.
     * 
     * If the book with the specified ISBN is already checked out, returns false.
     * 
     * Otherwise, returns true.
     * 
     * @param isbn -- ISBN of the library book to be checked out
     * @param holder -- new holder of the library book
     * @param month -- month of the new due date of the library book
     * @param day -- day of the new due date of the library book
     * @param year -- year of the new due date of the library book
     * 
     */
    public boolean checkout (long isbn, String holder, int month, int day, int year)
    {
        // checks to see if the library size is 0
        if (library.size() == 0)
            return false;

        // checks the isbn and sees if it is null
        if (this.lookup(isbn) == null)
        {
            for (int i = 0; i < library.size(); i++)
            {
                if (library.get(i).getIsbn() == isbn)
                {
                    library.get(i).checkout(holder, isbn, year, month, day);
                }
            }
            return true;
        }

        return false;
    }

    /**
     * Unsets the holder and due date of the library book.
     * 
     * If no book with the specified ISBN is in the library, returns false.
     * 
     * If the book with the specified ISBN is already checked in, returns false.
     * 
     * Otherwise, returns true.
     * 
     * @param isbn -- ISBN of the library book to be checked in
     */
    public boolean checkin (long isbn)
    {
        // checks to see if the isbn exists
        if (this.lookup(isbn) == null)
        {
            return false;
        }
        // cycles through the library
        for (int i = 0; i < library.size(); i++)
        {
            // if the desired isbn is in the library then it will check in the book
            if (library.get(i).getIsbn() == isbn)
            {
                library.get(i).checkin();
            }
        }
        return true;
    }

    /**
     * Unsets the holder and due date for all library books checked out be the specified holder.
     * 
     * If no books with the specified holder are in the library, returns false;
     * 
     * Otherwise, returns true.
     * 
     * @param holder -- holder of the library books to be checked in
     */
    public boolean checkin (String holder)
    {
        // creates a temp array and initializes it to the lookup array
        ArrayList<LibraryBook> temp_arr = this.lookup(holder);

        // if the size of the array is 0 then it returns true
        if (temp_arr.size() == 0)
            return false;

        // sets all the items in the temp_arr to checked in values and return true
        for (int i = 0; i < temp_arr.size(); i++)
        {
            temp_arr.get(i).checkin();
        }
        return true;
    }
}
