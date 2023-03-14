package timer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomTimerTest {

    RandomTimer rtGauss;
    RandomTimer rtPoisson;
    RandomTimer rtExp;
    RandomTimer rtPosibilist;
    int n = 100000;
    double error = 0.02;
    int lolim = 0;
    int hilim = 1000;
    int lambdaPoisson = 10;
    double lambdaExp = 0.01;


    @BeforeEach
    void setUp() throws Exception {
        RandomTimer.randomDistribution gauss = RandomTimer.string2Distribution("gaussian");
        rtGauss = new RandomTimer(gauss, lolim, hilim);
        RandomTimer.randomDistribution poisson = RandomTimer.string2Distribution("poisson");
        rtPoisson = new RandomTimer(poisson, lambdaPoisson);
        RandomTimer.randomDistribution exp = RandomTimer.string2Distribution("exp");
        rtExp = new RandomTimer(exp, lambdaExp);
        RandomTimer.randomDistribution posibilist = RandomTimer.string2Distribution("posibilist");
        rtPosibilist = new RandomTimer(posibilist, lolim, hilim);
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
            int e = rtGauss.next();
            somme += e;
            sommeSqrt += (long) e * e;
        }
        double experimentalAvg = (double) somme / n;
        double theoricalAvg = lolim + (double) (hilim - lolim) / 2;

        assertTrue(theoricalAvg - theoricalAvg * error <= experimentalAvg && experimentalAvg <= theoricalAvg + theoricalAvg * error);

        double standardDev = Math.sqrt((n * sommeSqrt - Math.pow(somme, 2)) / (n * (n - 1)));
        for (int i = 0; i < 50; i++) {
            int e = rtGauss.next();
            assertTrue(theoricalAvg - 3 * standardDev <= e && e <= theoricalAvg + 3 * standardDev);
        }
    }

    @Test
    void nextPoisson() {
        int somme = 0;
        long sommeSqrt = 0;
        for (int i = 0; i < n; i++) {
            int e = rtPoisson.next();
            somme += e;
            sommeSqrt += (long) e * e;
        }
        double experimentalAvg = (double) somme / n;
        double experimentalSquareAvg = (double) sommeSqrt / n;
        double experimentalVar = experimentalSquareAvg - Math.pow(experimentalAvg, 2);

        assertTrue(lambdaPoisson - lambdaPoisson * error <= experimentalAvg && experimentalAvg <= lambdaPoisson + lambdaPoisson * error);
        assertTrue(lambdaPoisson - lambdaPoisson * error <= experimentalVar && experimentalVar <= lambdaPoisson + lambdaPoisson * error);
    }

    @Test
    void nextExp() {
        double somme = 0;
        long sommeSqrt = 0;
        for (int i = 0; i < n; i++) {
            int e = rtExp.next();
            somme += e;
            sommeSqrt += (long) e * e;
        }
        double theoricalAvg = 1 / lambdaExp;
        double experimentalAvg = somme / n;
        double experimentalSquareAvg = (double) sommeSqrt / n;
        double theoricalVar = Math.pow(theoricalAvg, 2);
        double experimentalVar = experimentalSquareAvg - Math.pow(experimentalAvg, 2);

        assertTrue(theoricalAvg - theoricalAvg * error <= experimentalAvg && experimentalAvg <= theoricalAvg + theoricalAvg * error);
        assertTrue(theoricalVar - theoricalVar * error <= experimentalVar && experimentalVar <= theoricalVar + theoricalVar * error);
    }

    @Test
    void nextPosibilist() {
        int somme = 0;
        long sommeSqrt = 0;
        for (int i = 0; i < n; i++) {
            int e = rtPosibilist.next();
            somme += e;
            sommeSqrt += (long) e * e;
        }
        double theoricalAvg = (double) (lolim + hilim) / 2;
        double experimentalAvg = (double) somme / n;
        double experimentalSquareAvg = (double) sommeSqrt / n;
        double theoricalVar = Math.pow(hilim - lolim, 2) / 12;
        double experimentalVar = experimentalSquareAvg - Math.pow(experimentalAvg, 2);

        assertTrue(theoricalAvg - theoricalAvg * error <= experimentalAvg && experimentalAvg <= theoricalAvg + theoricalAvg * error);
        assertTrue(theoricalVar - theoricalVar * error <= experimentalVar && experimentalVar <= theoricalVar + theoricalVar * error);
    }

    @Test
    void badTimerException(){
        assertThrows(BadTimerException.class, () -> {
            RandomTimer.randomDistribution gauss = RandomTimer.string2Distribution("gaussian");
            rtGauss = new RandomTimer(gauss, lambdaExp);
        });
    }

    @Test
    void hasNext() {
        assertTrue(rtGauss.hasNext());
    }
}