package test.java.workflowEval;

import main.java.workflowEval.Mutation;
import main.java.workflowEval.Mutator;
import main.java.workflowEval.Selector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppTest {
    static Selector selector;

    @BeforeAll
    public static void initSelector(){
        selector = new Selector(4);
    }

    @Test
    public void testSelectorSetNegativeValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> selector.setSuccessfulMutations(-3));

        assertEquals("Only positive Integers are accepted!", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
    void testSelectorGetSetValue(int input) {
        selector.setSuccessfulMutations(input);
        assertEquals(input, selector.getSuccessfulMutations());
    }

    @ParameterizedTest
    @MethodSource("integerSource")
    void testSelectorGetSetMethod(int argument) {
        selector.setSuccessfulMutations(argument);
        assertEquals(argument, selector.getSuccessfulMutations());
    }

    private static IntStream integerSource() {
        int[] a = new int[10];
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            a[i] = random.nextInt(20) + 1;
        }

        return IntStream.of(a);
    }

    @Disabled("Disabled until Mutator is properly implemented")
    @Test
    @Timeout(200)
    void mutatorTest() {

    }

    @RepeatedTest(10)
    void testMethodSource() {
        ArrayList<Mutation> mutations = createMutations();
        ArrayList<Mutation> m2 = selector.select(mutations);

        assertTrue(m2.get(0).getValue() >= m2.get(1).getValue());
        assertEquals(4, m2.size());
    }

    static ArrayList<Mutation> createMutations() {
        ArrayList<Mutation> res = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            res.add(new Mutation("state", i));
        }

        return res;
    }

    @Mock
    Mutator mutator;

    @Mock
    main.java.workflowEval.Eval evaluator;

    @Test
    void MockTest() {
        ArrayList<Mutation> mutations = createMutations();
        assertNotNull(mutator);
        assertNotNull(evaluator);
        Random random = new Random();

        ArrayList<Mutation> m2 = new ArrayList<>();
        m2.addAll(mutations);
        m2.addAll(mutations);

        when(mutator.mutateGeneration(mutations)).thenReturn(m2);
        when(evaluator.evaluate(any(Mutation.class))).thenReturn(random.nextInt(10) + 1);

        ArrayList<Mutation> m3 = mutator.mutateGeneration(mutations);

        assertEquals(mutations.size()*2, m3.size());

        for (Mutation m: m3) {
            m.setValue(evaluator.evaluate(m));
        }

        ArrayList<Mutation> m4 = selector.select(m3);

        assertTrue(m4.get(0).getValue() >= m4.get(1).getValue());
    }
}