package timer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomTimerTest {

    RandomTimer rtg;
    RandomTimer rtp;
    int n = 50000;
    double error = 0.01;
    int lolim = 0;
    int hilim = 100;

    double average = lolim + ((double)hilim - (double)lolim) / 2;
    int poissonLambda = 10;

    @BeforeEach
    void setUp() throws Exception {
        RandomTimer.randomDistribution gauss = RandomTimer.string2Distribution("gaussian");
        rtg = new RandomTimer(gauss, lolim, hilim);
        RandomTimer.randomDistribution poisson = RandomTimer.string2Distribution("poisson");
        rtp = new RandomTimer(poisson, poissonLambda);
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
    void nextGaussien() {
        int somme = 0;
        long sommeSqrt = 0;
        for (int i = 0; i < n; i++) {
            int e = rtg.next();
            somme += e;
            sommeSqrt += (long) e * e;
        }
        double experimentalAvg = (double) somme / n;
        assertTrue(average - average * error <= experimentalAvg && experimentalAvg <= average + average * error);

        double standardDev = Math.sqrt((n * sommeSqrt - Math.pow(somme, 2)) / (n * (n - 1)));
        for (int i = 0; i < 50; i++) {
            int e = rtg.next();
            assertTrue(average - 3*standardDev <= e && e <= average + 3*standardDev );
        }
    }

    @Test
    void nextPoisson() {
        int somme = 0;
        long sommeSqrt = 0;
        for (int i = 0; i < n; i++) {
            int e = rtp.next();
            somme += e;
            sommeSqrt += (long) e * e;
        }
        double experimentalAvg = (double)somme/n;
        double experimentalSquareAvg = (double)sommeSqrt/n;
        assertTrue(rtp.getMean() - rtp.getMean() * error <= experimentalAvg && experimentalAvg <= rtp.getMean() + rtp.getMean() * error);

        double experimentalVar = experimentalSquareAvg - Math.pow(experimentalAvg,2);
        assertTrue(rtp.getMean() - rtp.getMean() * error <= experimentalVar && experimentalVar <= rtp.getMean() + rtp.getMean() * error);
    }




    @Test
    void hasNext() {
        assertTrue(rtg.hasNext());
    }
}