package timer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DateTimerTest {

    ArrayList<Integer> lapsTimes;
    DateTimer dt;

    @BeforeEach
    void setUp() {
        lapsTimes = new ArrayList<>();
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
        assertEquals(5,dt.next());
        assertEquals(1,dt.next());
        assertThrows(NoSuchElementException.class, () -> dt.next());
    }
}