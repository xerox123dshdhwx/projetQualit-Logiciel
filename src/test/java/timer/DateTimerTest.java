package timer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class DateTimerTest {

    Vector<Integer> lapsTimes;
    DateTimer dt;

    @BeforeEach
    void setUp() {
        lapsTimes = new Vector<>();
        lapsTimes.add(5);
        lapsTimes.add(1);
        dt = new DateTimer(lapsTimes);
    }


    @Test
    void hasNext() {
        assertTrue(dt.hasNext());
        dt.next();
        dt.next();
        assertFalse(dt.hasNext());
    }

    @Test
    void next() {
        assertEquals(dt.next(),5);
        assertEquals(dt.next(),1);
        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> {
            dt.next();
        });
    }
}