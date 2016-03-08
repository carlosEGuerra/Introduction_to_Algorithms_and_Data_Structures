package assignment02;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Atul Sherma, Carlos Guerra
 */
public class LibraryGenericTests
{
    private LibraryGeneric<String> library_Test = new LibraryGeneric<String>();;

    @Test(expected = NullPointerException.class)
    public void NullPointerExceptionTest ()
    {
        library_Test = null;

        library_Test.lookup(5);
    }

    @Test
    public void testSearchForTim ()
    {
        library_Test.add(00001, "Charles", "Welcome to the Jungle");
        library_Test.add(00002, "Google", "The Algorithm");
        library_Test.add(00003, "Bing", "At least we tried");
        library_Test.add(00005, "Myspace", "The Network that Tried");
        library_Test.add(00006, "Facebook", "We Did It Better");

        library_Test.checkout(00002, "Tim", 01, 01, 0001);

        assertEquals("Tim", library_Test.lookup(00002));
    }

    /**
     * Tests checkin(T Holder)
     */
    @Test
    public void NoBooksWithSpecifiedHolderCheckin ()
    {
        library_Test.add(00001, "Charles", "Welcome to the Jungle");
        library_Test.add(00002, "Google", "The Algorithm");
        library_Test.add(00003, "Bing", "At least we tried");
        library_Test.add(00005, "Myspace", "The Network that Tried");
        library_Test.add(00006, "Facebook", "We Did It Better");

        assertEquals(false, library_Test.checkin("Apple"));
    }

    @Test
    public void TestsHolderInCheckin ()
    {
        library_Test.add(00001, "Charles", "Welcome to the Jungle");
        library_Test.add(00002, "Google", "The Algorithm");
        library_Test.add(00003, "Bing", "At least we tried");
        library_Test.add(00005, "Myspace", "The Network that Tried");
        library_Test.add(00006, "Facebook", "We Did It Better");

        library_Test.checkout(00002, "Tim", 01, 01, 0001);

        assertEquals(true, library_Test.checkin("Tim"));
    }

    /**
     * Test checkin(long isbn)
     */
    @Test
    public void NoBookWithISBNCheckin ()
    {
        assertEquals(false, library_Test.checkin(9));
    }

    @Test
    public void BookWithSepcifiedISBNAlreadyIsCheckedIn ()
    {
        library_Test.add(00001, "Charles", "Welcome to the Jungle");
        library_Test.add(00002, "Google", "The Algorithm");
        library_Test.add(00003, "Bing", "At least we tried");
        library_Test.add(00005, "Myspace", "The Network that Tried");
        library_Test.add(00006, "Facebook", "We Did It Better");

        assertEquals(false, library_Test.checkin(1));
    }

    @Test
    public void BookWithISBNIsCheckedOut ()
    {
        library_Test.add(00001, "Charles", "Welcome to the Jungle");
        library_Test.add(00002, "Google", "The Algorithm");
        library_Test.add(00003, "Bing", "At least we tried");
        library_Test.add(00005, "Myspace", "The Network that Tried");
        library_Test.add(00006, "Facebook", "We Did It Better");

        library_Test.checkout(1, "Me", 1, 1, 1);

        assertEquals(true, library_Test.checkin(1));
    }

    @Test
    public void BookNotExistsWithSpecifiedISBN ()
    {
        assertEquals(null, library_Test.lookup(1));
    }

    @Test
    public void BookWithISBNExists ()
    {
        library_Test.add(00001, "Charles", "Welcome to the Jungle");
        library_Test.add(00002, "Google", "The Algorithm");
        library_Test.add(00003, "Bing", "At least we tried");
        library_Test.add(00005, "Myspace", "The Network that Tried");
        library_Test.add(00006, "Facebook", "We Did It Better");

        library_Test.checkout(1, "Adolf", 1, 1, 1);

        assertEquals("Adolf", library_Test.lookup(1));
    }

    /**
     * Tests getOverDueList(int month, int day, int year)
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void TestsForNullPointerException ()
    {
        library_Test.getOverdueList(-1, -1, -1).get(0);
    }

    @Test
    public void OverDueListWithAnEntryLibraryTest ()
    {
        library_Test.add(00001, "Charles", "Welcome to the Jungle");
        library_Test.add(00002, "Google", "The Algorithm");
        library_Test.add(00003, "Bing", "At least we tried");
        library_Test.add(00005, "Myspace", "The Network that Tried");
        library_Test.add(00006, "Facebook", "We Did It Better");

        library_Test.checkout(1, "Tim", 2, 2, 2);

        ArrayList<LibraryBookGeneric<String>> library = library_Test.getOverdueList(0, 0, 0);

        assertEquals(1, library.size());
    }

    @Test
    public void LibraryWithNoOverdueBooksTest ()
    {
        library_Test.add(00001, "Charles", "Welcome to the Jungle");
        library_Test.add(00002, "Google", "The Algorithm");
        library_Test.add(00003, "Bing", "At least we tried");
        library_Test.add(00005, "Myspace", "The Network that Tried");
        library_Test.add(00006, "Facebook", "We Did It Better");

        assertEquals(0, library_Test.getOverdueList(1, 1, 1).size());
    }

    @Test
    public void LibraryOverdueIsSortedTest ()
    {
        library_Test.add(00001, "Charles", "Welcome to the Jungle");
        library_Test.add(00002, "Google", "The Algorithm");
        library_Test.add(00003, "Bing", "At least we tried");
        library_Test.add(00004, "Adolf Hitler", "Mein Kamf");
        library_Test.add(00005, "Myspace", "The Network that Tried");
        library_Test.add(00006, "Facebook", "We Did It Better");
        
        library_Test.checkout(00001, "Joe", 0, 0, 0);
        library_Test.checkout(00002, "Joe", 11, 5, 1001);
        library_Test.checkout(00003, "Joe", 11, 4, 1001);
        library_Test.checkout(00004, "Joe", 11, 3, 1001);
        library_Test.checkout(00005, "Joe", 11, 2, 1001);
        library_Test.checkout(00006, "Joe", 11, 1, 1001);

        ArrayList<LibraryBookGeneric<String>> result_array = new ArrayList<>();
        for(int i  = library_Test.getInventoryList().size()-1; i >= 1; i--)
        {
            result_array.add(library_Test.getInventoryList().get(i));
        }
        
        ArrayList<LibraryBookGeneric<String>> sorted_list = library_Test.getOverdueList(0, 0, 0);
        
        assertEquals(5, sorted_list.size());
        
        if (sorted_list.contains(library_Test.getInventoryList().get(0)))
            fail();
    }

    @Test
    public void OrderByAuthorTest ()
    {
        library_Test.add(00001, "Charles", "Welcome to the Jungle");
        library_Test.add(00002, "Google", "The Algorithm");
        library_Test.add(00003, "Bing", "At least we tried");
        library_Test.add(00004, "Adolf Hitler", "Mein Kamf");
        library_Test.add(00005, "Myspace", "The Network that Tried");
        library_Test.add(00006, "Facebook", "We Did It Better");
        
        ArrayList<LibraryBookGeneric<String>> resulting_array = library_Test.getOrderedByAuthor();
        
        assertEquals("Adolf Hitler", resulting_array.get(0).getAuthor());
        assertEquals("Myspace", resulting_array.get(5).getAuthor());
    }

    @Test
    public void LookupByISBN ()
    {
        library_Test.add(00001, "Charles", "Welcome to the Jungle");
        library_Test.add(00002, "Google", "The Algorithm");
        library_Test.add(00003, "Bing", "At least we tried");
        library_Test.add(00005, "Myspace", "The Network that Tried");
        library_Test.add(00006, "Facebook", "We Did It Better");

        library_Test.checkout(00002, "Tim", 01, 01, 0001);

        assertEquals("Tim", library_Test.lookup(2));
    }

    @Test
    public void LookupByHolder ()
    {
        library_Test.add(00001, "Charles", "Welcome to the Jungle");
        library_Test.add(00002, "Google", "The Algorithm");
        library_Test.add(00003, "Bing", "At least we tried");
        library_Test.add(00005, "Myspace", "The Network that Tried");
        library_Test.add(00006, "Facebook", "We Did It Better");

        library_Test.checkout(00002, "Tim", 01, 01, 0001);

        ArrayList<LibraryBookGeneric<String>> temp = library_Test.lookup("Tim");

        assertEquals(true, temp.get(0).equals(new Book(00002, "Google", "The Algorithm")));
    }
}
