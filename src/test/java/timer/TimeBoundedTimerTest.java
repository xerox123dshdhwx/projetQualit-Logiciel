package timer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeBoundedTimerTest {

    PeriodicTimer pt1;
    TimeBoundedTimer bt1;
    TimeBoundedTimer bt2;

    @BeforeEach
    void setUp() {
        pt1 = new PeriodicTimer(10);
        bt1 = new TimeBoundedTimer(pt1, 25,55);
        bt2 = new TimeBoundedTimer(pt1, 30, 50);
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
    void hasNextLimit() { //test aux limites de hasNext
        assertTrue(bt2.hasNext());
        bt2.next(); // return first value 30
        assertTrue(bt2.hasNext());
        bt2.next(); // return first value 10
        assertTrue(bt2.hasNext());
        bt2.next(); // return first value 10
        assertFalse(bt2.hasNext());
    } // même bug que precedement

    @Test
    void nextPartitionnement() { //test de hasNext partitionnement
        assertEquals(30, bt1.next());
        assertEquals(10, bt1.next());
        assertEquals(10, bt1.next());
        assertNull(bt1.next());
    }

    @Test
    void nextLimit() { //test aux limites de hasNext
        assertEquals(30, bt2.next());
        assertEquals(10, bt2.next());
        assertEquals(10, bt2.next());
        assertNull(bt2.next());
    }
}