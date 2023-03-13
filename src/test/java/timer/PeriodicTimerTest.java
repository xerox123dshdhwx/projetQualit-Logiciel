package timer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeriodicTimerTest {

    PeriodicTimer pt1;
    PeriodicTimer pt2;

    @BeforeEach
    void setUp() {
        pt1 = new PeriodicTimer(10,20);
        pt2 = new PeriodicTimer(10);
    }

    @Test
    void next() {
        assertEquals(pt1.next(),20);
        assertEquals(pt1.next(),10);
        assertEquals(pt1.next(),10);

        assertEquals(pt2.next(),10);
        assertEquals(pt2.next(),10);
    }

    @Test
    void hasNext() {
        assertTrue(pt1.hasNext());
        assertTrue(pt2.hasNext());
    }
}