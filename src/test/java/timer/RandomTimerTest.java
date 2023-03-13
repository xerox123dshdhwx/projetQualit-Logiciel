package timer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomTimerTest {

    RandomTimer rt;
    int n = 100000;
    int lolim = 0;
    int hilim = 100;
    double average = lolim + ((double)hilim - (double)lolim) / 2;


    @BeforeEach
    void setUp() throws Exception {
        RandomTimer.randomDistribution gauss = RandomTimer.string2Distribution("gaussian");
        rt = new RandomTimer(gauss, lolim, hilim);
    }

    @Test
    void string2Distribution() {
        assertEquals(RandomTimer.randomDistribution.GAUSSIAN, RandomTimer.string2Distribution("gaussian"));
    }

    @Test
    void distribution2String() {
        assertEquals("GAUSSIAN", RandomTimer.distribution2String(RandomTimer.randomDistribution.GAUSSIAN));
    }

    @Test
    void next() {
        int somme = 0;
        long sommeSqrt = 0;
        for (int i = 0; i < n; i++) {
            int e = rt.next();
            somme += e;
            sommeSqrt += (long) e * e;
        }
        double experimentalAvg = (double) somme / n;
        assertTrue(average - average * 0.01 <= experimentalAvg && experimentalAvg <= average + average * 0.01);

        double standardDev = Math.sqrt((n * sommeSqrt - Math.pow(somme, 2)) / (n * (n - 1)));
        for (int i = 0; i < 50; i++) {
            int e = rt.next();
            assertTrue(experimentalAvg - 3*standardDev <= e && e <= experimentalAvg + 3*standardDev );
        }
    }

    @Test
    void hasNext() {
        assertTrue(rt.hasNext());
    }
}