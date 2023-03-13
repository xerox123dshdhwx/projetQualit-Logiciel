package timer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MergedTimerTest {

    MergedTimer mergedTimer1,mergedTimer2,OmergedTimer3;
    OneShotTimer Otimer1,Otimer2,Otimer3;
    RandomTimer.randomDistribution gauss;
    RandomTimer Gtimer1;


    int lolim = 0;
    int hilim = 100;


    @BeforeEach
    void setUp() throws Exception {
        Otimer1 = new OneShotTimer(12);
        Otimer2 = new OneShotTimer(2);

        gauss = RandomTimer.string2Distribution("gaussian");
        Gtimer1 = new RandomTimer(gauss, lolim, hilim);

        Otimer3 = new OneShotTimer(15);
        Otimer3.next();

        mergedTimer1= new MergedTimer(Otimer1,Gtimer1);
        mergedTimer2= new MergedTimer(Otimer1,Otimer3);
        OmergedTimer3= new MergedTimer(Otimer1,Otimer2);


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void hasNext() {
        assertEquals(mergedTimer1.hasNext() ,Gtimer1.hasNext() && Otimer1.hasNext() );
        assertEquals(mergedTimer2.hasNext() ,Otimer3.hasNext() && Otimer1.hasNext());
    }

    @Test
    void next() {
        assertEquals(OmergedTimer3.next(),12+2);
    }
}