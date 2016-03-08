package assignment02;

import java.util.GregorianCalendar;

/**
 * @author Atul Sherma, Carlos Guerra
 */
@SuppressWarnings("unused")
public class LibraryBookGeneric<T> extends Book
{
    private long book_isbn;
    private T bookHolder;
    private GregorianCalendar dueDate;

    // creates the libraryBook object
    public LibraryBookGeneric (long isbn, String author, String title)
    {
        super(isbn, author, title);
        T bookHolder = null;
        dueDate = null;
    }

    // Resets the book holder and dueDate once checked in
    public void checkin ()
    {
        bookHolder = null;
        dueDate = null;
    }

    // Sets the holder, isbn, and due date for the book
    public void checkout (T holder, long isbn, int month, int day, int year)
    {
        this.book_isbn = isbn;
        this.bookHolder = holder;
        this.dueDate = new GregorianCalendar(year, month, day);
    }

    // gets the holder of the book
    public T getHolder ()
    {
        return bookHolder;
    }

    // gets the due date for the respected book
    public GregorianCalendar getDueDate ()
    {
        return dueDate;
    }

    public void setHolder (T holder)
    {
        bookHolder = holder;
    }

    public void setDueDate (GregorianCalendar gc)
    {
        dueDate = gc;
    }
}
