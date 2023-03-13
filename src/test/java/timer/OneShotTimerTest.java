package timer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OneShotTimerTest {

    OneShotTimer timer1,timer2;
    int number;

    @BeforeEach
    void setUp() {
        number = 5;
        timer1 = new OneShotTimer(number);
        timer2 = new OneShotTimer(12);
        timer2.next();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void hasNext() {
        assertTrue(timer1.hasNext());
        assertFalse(timer2.hasNext());
    }

    @Test
    void next() {
        assertEquals(number,timer1.next());
    }
}