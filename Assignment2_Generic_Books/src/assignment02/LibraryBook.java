package assignment02;

import java.util.GregorianCalendar;

/**
 * @author Atul Sherma, Carlos Guerra
 */
public class LibraryBook extends Book
{
    @SuppressWarnings("unused")
    private long book_isbn;
    private String bookHolder;
    private GregorianCalendar dueDate;

    // creates the libraryBook object
    public LibraryBook (long isbn, String author, String title)
    {
        super(isbn, author, title);
        @SuppressWarnings("unused")
        String bookHolder = null;
        dueDate = null;
    }

    // Resets the book holder and dueDate once checked in
    public void checkin ()
    {
        bookHolder = null;
        dueDate = null;
    }

    // Sets the holder, isbn, and due date for the book
    public void checkout (String holder, long isbn, int year, int month, int day)
    {
        this.book_isbn = isbn;
        this.bookHolder = holder;
        this.dueDate = new GregorianCalendar(year, month, day);
    }

    // gets the holder of the book
    public String getHolder ()
    {
        return bookHolder;
    }

    // gets the due date for the respected book
    public GregorianCalendar getDueDate ()
    {
        return dueDate;
    }

    public void setHolder (String holder)
    {
        bookHolder = holder;
    }

    public void setDueDate (GregorianCalendar gc)
    {
        dueDate = gc;
    }
}
