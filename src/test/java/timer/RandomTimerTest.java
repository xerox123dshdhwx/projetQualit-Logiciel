package timer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RandomTimerTest {

    RandomTimer rt;
    ArrayList<Integer> gaussianDistrib = new ArrayList<>();


    @BeforeEach
    void setUp() throws Exception {
        RandomTimer.randomDistribution gauss = RandomTimer.string2Distribution("gaussian");
        rt = new RandomTimer(gauss,0,1000);
        for (int i = 0; i < 1000; i++) {
            gaussianDistrib.add(rt.next());
        }
    }

    @Test
    void string2Distribution() {
        assertEquals(RandomTimer.randomDistribution.GAUSSIAN,RandomTimer.string2Distribution("gaussian"));
    }

    @Test
    void distribution2String() {
        assertEquals("GAUSSIAN",RandomTimer.distribution2String(RandomTimer.randomDistribution.GAUSSIAN));
    }

    @Test
    void next() {
        System.out.println(gaussianDistrib);
    }

    @Test
    void hasNext() {
    }
}