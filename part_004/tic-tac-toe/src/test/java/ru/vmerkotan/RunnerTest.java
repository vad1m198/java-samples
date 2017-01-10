package ru.vmerkotan;

import org.junit.Test;
import ru.vmerkotan.input.Input;
import ru.vmerkotan.output.Output;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * Tests for Runner class.
 *
 * Created by vmerkotan on 1/10/2017.
 */
public class RunnerTest {

    /**
     * getGameField method test.
     * If input is empty string the
     * return GameField with field size equal to 3.
     */
    @Test
    public void whenEmptyInputThenReturnDefaultGameField() {
        String[] inputs = new String[]{""};
        TestInput input = new TestInput(inputs);
        GameField gf = Runner.getGameField(input, new TestOutput());
        assertThat(gf.getSize(), is(3));
    }

    /**
     * getGameField method test.
     * If input is valid number
     * then return GameField with size equal to passed number.
     */
    @Test
    public void whenValidInputThenReturnNewGameField() {
        String[] inputs = new String[]{"5"};
        TestInput input = new TestInput(inputs);
        GameField gf = Runner.getGameField(input, new TestOutput());
        assertThat(gf.getSize(), is(5));
    }

    /**
     * getUserStartTheGame method test.
     * If input is empty then return true.
     */
    @Test
    public void whenInputIsEmptyThenReturnTrue() {
        String[] inputs = new String[]{""};
        TestInput input = new TestInput(inputs);
        assertTrue(Runner.getUserStartTheGame(input, new TestOutput()));
    }

    /**
     * getUserStartTheGame method test.
     * If input is not empty then return false.
     */
    @Test
    public void whenInputIsNotEmptyThenReturnFalse() {
        String[] inputs = new String[]{"s"};
        TestInput input = new TestInput(inputs);
        assertFalse(Runner.getUserStartTheGame(input, new TestOutput()));
    }

    /**
     * verify if User gets line first
     * then user wins.
     */
    @Test
    public void whenUserGetLineFirstThenUserWins() {
        String[] inputs = new String[]{"0", "0", "1", "1", "2", "2"};
        TestInput input = new TestInput(inputs);
        TestOutput output = new TestOutput();
        GameField gf = new GameField(3);
        Contender contender = new Contender(gf);
        Runner r = new Runner(gf, contender, true, input, output);
        r.init();
        assertThat(output.getResult(), containsString("User wins"));
    }

    /**
     * verify if Contender gets line first
     * then Contender is winner.
     */
    @Test
    public void whenContenderGetLineFirstThenContenderWins() {
        String[] inputs = new String[]{"0", "1", "1", "1"};
        TestInput input = new TestInput(inputs);
        TestOutput output = new TestOutput();
        GameField gf = new GameField(3);
        Contender contender = new Contender(gf);
        Runner r = new Runner(gf, contender, false, input, output);
        r.init();
        assertThat(output.getResult(), containsString("Contender is winner"));
    }


    /**
     * class for test input.
     */
    private class TestInput implements Input {
        /**
         * String[] to read inputs from.
         */
        private String[] inputs;
        /**
         * Current inputs position.
         */
        private int position;

        /**
         * Initiates new TestInput object.
         *
         * @param inputs String[]
         */
        private TestInput(String[] inputs) {
            this.inputs = inputs;
        }

        @Override
        public String read() {
            return this.inputs[position++];
        }
    }

    /**
     *  class to be used in tests.
     *  To write result to.
     */
    private class TestOutput implements Output {
        /**
         * Private StringBuilder to aggregate outputs.
         */
        private StringBuilder sb = new StringBuilder();

        @Override
        public void write(String output) {
            sb.append(output);
        }

        /**
         * Return String representation of internal StringBuilder.
         *
         * @return sb.toString().
         */
        public String getResult() {
            return this.sb.toString();
        }


    }

}