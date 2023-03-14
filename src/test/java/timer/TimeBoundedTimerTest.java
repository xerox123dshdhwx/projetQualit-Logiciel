package timer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeBoundedTimerTest {

    PeriodicTimer pt1;
    TimeBoundedTimer bt1;

    @BeforeEach
    void setUp() {
        pt1 = new PeriodicTimer(10);
        bt1 = new TimeBoundedTimer(pt1, 25,55);
    }

    @Test
    void hasNextPartitionnement() { //test de hasNext partitionnement
        assertTrue(bt1.hasNext());
        bt1.next(); // return first value 30
        assertTrue(bt1.hasNext());
        bt1.next(); // return first value 10
        assertTrue(bt1.hasNext());
        bt1.next(); // return first value 10
        assertFalse(bt1.hasNext());

    } // Bug trouvé, on a un décalage d'une unité entre next() et hasNext(), au moment ou on dépasse la borne, next() et null alors que hasNext() est True.

    @Test
    void nextPartitionnement() { //test de hasNext partitionnement
        assertEquals(30, bt1.next());
        assertEquals(10, bt1.next());
        assertEquals(10, bt1.next());
        assertNull(bt1.next());
    }
}