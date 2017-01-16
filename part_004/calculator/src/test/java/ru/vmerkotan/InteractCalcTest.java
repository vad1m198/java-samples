package ru.vmerkotan;

import org.junit.Test;
import ru.vmerkotan.actions.CosAction;
import ru.vmerkotan.actions.DivideAction;
import ru.vmerkotan.actions.MultiplyAction;
import ru.vmerkotan.actions.SinAction;
import ru.vmerkotan.actions.SubtractAction;
import ru.vmerkotan.actions.SumAction;
import ru.vmerkotan.input.Input;
import ru.vmerkotan.output.Output;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * {@code InteractCalcTest} represents tests for InteractCalc class.
 * Created by vmerkotan on 1/6/2017.
 */
public class InteractCalcTest {
    /**
     * inner InteractCalc instance.
     */
    private InteractCalc runner;
    /**
     * inner Calculator instance.
     */
    private static Calculator calc = new Calculator();
    static {
        calc.addAction(new SumAction("sum", "This operation sums all passed params"));
        calc.addAction(new SubtractAction("sub", "This operation subtracts second argument from the first"));
        calc.addAction(new MultiplyAction("mul", "This operation multiplies all passed arguments"));
        calc.addAction(new DivideAction("div", "This operation divides first argument by second"));
        calc.addAction(new CosAction("cos", "This operation calculates cosine of given argument"));
        calc.addAction(new SinAction("sin", "This operation calculates sinus of given argument"));
    }

    /**
     * Verify sumAction.
     * When sum 1 and 2. Result should be 3.0
     */
    @Test
    public void whenPassOneAndTwoThenThreeTheResult() {
        String[] answers = new String[]{"sum", "1 2", "no", "exit"};
        Input input = new TestInput(answers);
        Output output = new TestOutput();
        runner = new InteractCalc(calc, input, output);
        runner.init();
        assertThat(3.0, is(closeTo(calc.getResult(), 0.1)));
    }

    /**
     * Verify SubtractAction.
     * When sub 5 and 1. Result should be 4.0
     */
    @Test
    public void whenSubFiveAndOneThenFourTheResult() {
        String[] answers = new String[]{"sub", "5 1", "no", "exit"};
        Input input = new TestInput(answers);
        Output output = new TestOutput();
        runner = new InteractCalc(calc, input, output);
        runner.init();
        assertThat(4.0, is(closeTo(calc.getResult(), 0.1)));
    }

    /**
     * Verify MultiplyAction.
     * When mul 5 and 2. Result should be 10.0
     */
    @Test
    public void whenMulFiveAndTwoThenTenTheResult() {
        String[] answers = new String[]{"mul", "5 2", "no", "exit"};
        Input input = new TestInput(answers);
        Output output = new TestOutput();
        runner = new InteractCalc(calc, input, output);
        runner.init();
        assertThat(10.0, is(closeTo(calc.getResult(), 0.1)));
    }

    /**
     * Verify DivideAction.
     * When div 10 and 2. Result should be 5.0
     */
    @Test
    public void whenDivTenAndTwoThenFiveTheResult() {
        String[] answers = new String[]{"div", "10 2", "no", "exit"};
        Input input = new TestInput(answers);
        Output output = new TestOutput();
        runner = new InteractCalc(calc, input, output);
        runner.init();
        assertThat(5.0, is(closeTo(calc.getResult(), 0.1)));
    }

    /**
     * Verify Invalid Key.
     * When pass invalid Key the you can fix it.
     */
    @Test
    public void whenPassInvalidKeyThenNoError() {
        String[] answers = new String[]{"non valid", "10 2", "sum", "10 2", "no", "exit"};
        Input input = new TestInput(answers);
        Output output = new TestOutput();
        runner = new InteractCalc(calc, input, output);
        runner.init();
        assertThat(12.0, is(closeTo(calc.getResult(), 0.1)));
    }

    /**
     * Verify Invalid arguments.
     * When pass invalid arguments the you can fix it.
     */
    @Test
    public void whenPassInvalidArgumentsThenNoError() {
        String[] answers = new String[]{"sub", "non valid", "sub", "10 2", "no", "exit"};
        Input input = new TestInput(answers);
        Output output = new TestOutput();
        runner = new InteractCalc(calc, input, output);
        runner.init();
        assertThat(8.0, is(closeTo(calc.getResult(), 0.1)));
    }

    /**
     * Verify SubtractAction Invalid arguments number.
     * When pass invalid arguments number then no error.
     */
    @Test
    public void whenSubtractPassWrongArgsNumberThenNoError() {
        String[] answers = new String[]{"sub", "1 2 3", "sub", "10 2", "no", "exit"};
        Input input = new TestInput(answers);
        Output output = new TestOutput();
        runner = new InteractCalc(calc, input, output);
        runner.init();
        assertThat(8.0, is(closeTo(calc.getResult(), 0.1)));
    }

    /**
     * Verify DivideAction Invalid arguments number.
     * When pass invalid arguments number then no error.
     */
    @Test
    public void whenDividePassWrongArgsNumberThenNoError() {
        String[] answers = new String[]{"div", "1 2 3", "div", "10 2", "no", "exit"};
        Input input = new TestInput(answers);
        Output output = new TestOutput();
        runner = new InteractCalc(calc, input, output);
        runner.init();
        assertThat(5.0, is(closeTo(calc.getResult(), 0.1)));
    }

    /**
     * Verify CosAction invalid arguments number.
     * When pass invalid arguments number then no error.
     */
    @Test
    public void whenCosPassWrongArgsNumberThenNoError() {
        String[] answers = new String[]{"cos", "0 1", "cos", "0", "no", "exit"};
        Input input = new TestInput(answers);
        Output output = new TestOutput();
        runner = new InteractCalc(calc, input, output);
        runner.init();
        assertThat(1.0, is(closeTo(calc.getResult(), 0.1)));
    }

    /**
     * Verify CosAction.
     * When pass 0 the result 1;
     */
    @Test
    public void whenCosPassZeroThenResultOne() {
        String[] answers = new String[]{"cos", "0", "no", "exit"};
        Input input = new TestInput(answers);
        Output output = new TestOutput();
        runner = new InteractCalc(calc, input, output);
        runner.init();
        assertThat(1.0, is(closeTo(calc.getResult(), 0.1)));
    }

    /**
     * Verify SinAction invalid arguments number.
     * When pass invalid arguments number then no error.
     */
    @Test
    public void whenSinPassWrongArgsNumberThenNoError() {
        String[] answers = new String[]{"sin", "0 1", "sin", "0", "no", "exit"};
        Input input = new TestInput(answers);
        Output output = new TestOutput();
        runner = new InteractCalc(calc, input, output);
        runner.init();
        assertThat(0.0, is(closeTo(calc.getResult(), 0.1)));
    }

    /**
     * Verify SinAction.
     * When pass 0 the result 1;
     */
    @Test
    public void whenSinPassZeroThenResultOne() {
        String[] answers = new String[]{"sin", "0", "no", "exit"};
        Input input = new TestInput(answers);
        Output output = new TestOutput();
        runner = new InteractCalc(calc, input, output);
        runner.init();
        assertThat(0.0, is(closeTo(calc.getResult(), 0.1)));
    }

    /**
     * {@code TestInput} class represents Input instance for testing.
     */
    private class TestInput implements Input {
        /**
         * Holds string to read.
         */
        private String[] answers;
        /**
         * Current answers array position.
         */
        private int position = 0;

        /**
         * Creates new TestInput instance.
         * @param answers   String[] to read.
         */
        TestInput(String[] answers) {
            this.answers = answers;
        }

        /**
         * Returns answer form answers inner array.
         * @return answer
         */
        @Override
        public String read() {
            return answers[position++];
        }
    }

    /**
     * {@code TestOutput} class presents Output instance for testing.
     */
    private class TestOutput implements Output {
        /**
         * Do nothing.
         * @param output    String to write.
         */
        @Override
        public void write(String output) { }
    }

}