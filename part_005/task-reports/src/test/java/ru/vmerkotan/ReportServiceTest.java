package ru.vmerkotan;

import org.junit.Test;
import ru.vmerkotan.output.Output;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * Tests for ReportService class.
 *
 * Created by vmerkotan on 1/19/2017.
 */
public class ReportServiceTest {
    /**
     * Test printRangeReport method.
     */
    @Test
    public void whenTaskIsOutOfRangeThenDoNotPrintIt() {
        TestOutput output = new TestOutput();
        ReportService service = new ReportService(output);
        List<Task> tasks = new ArrayList<>();

        Task t1 = new Task(State.OPEN, new GregorianCalendar(2017, 1, 2));
        Operation o11 = new Operation(State.OPEN, State.IN_PROGRESS, new GregorianCalendar(2017, 1, 3));
        Operation o12 = new Operation(State.OPEN, State.IN_PROGRESS, new GregorianCalendar(2017, 1, 4));
        Operation o13 = new Operation(State.OPEN, State.IN_PROGRESS, new GregorianCalendar(2017, 1, 5));
        t1.addOperation(o11);
        t1.addOperation(o12);
        t1.addOperation(o13);

        Task t2 = new Task(State.OPEN, new GregorianCalendar(2017, 2, 14));
        Operation o21 = new Operation(State.OPEN, State.IN_PROGRESS, new GregorianCalendar(2017, 2, 15));
        Operation o22 = new Operation(State.OPEN, State.IN_PROGRESS, new GregorianCalendar(2017, 2, 16));
        Operation o23 = new Operation(State.OPEN, State.IN_PROGRESS, new GregorianCalendar(2017, 2, 17));
        t2.addOperation(o21);
        t2.addOperation(o22);
        t2.addOperation(o23);

        Task t3 = new Task(State.OPEN, new GregorianCalendar(2017, 3, 25));
        Operation o31 = new Operation(State.OPEN, State.IN_PROGRESS, new GregorianCalendar(2017, 3, 25));
        Operation o32 = new Operation(State.OPEN, State.IN_PROGRESS, new GregorianCalendar(2017, 3, 26));
        Operation o33 = new Operation(State.OPEN, State.IN_PROGRESS, new GregorianCalendar(2017, 3, 27));
        t3.addOperation(o31);
        t3.addOperation(o32);
        t3.addOperation(o33);

        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);

        service.printRangeReport(tasks, new GregorianCalendar(2017, 1, 4), new GregorianCalendar(2017, 2, 16));

        StringBuilder expected = new StringBuilder();

        expected.append(t1.toString());
        expected.append(o12.toString());
        expected.append(o13.toString());
        expected.append(t2.toString());
        expected.append(o21.toString());
        expected.append(o22.toString());
        assertThat(output.getResult(), containsString(expected.toString()));

    }

    /**
     * Simple Output test class.
     */
    private class TestOutput implements Output {
        /**
         * holds all output result.
         */
        private StringBuilder sb = new StringBuilder();
        @Override
        public void write(String message) {
            sb.append(message);
        }

        /**
         * Returns all inputs representation.
         *
         * @return  String representation of all inputs.
         */
        String getResult() {
            return this.sb.toString();
        }
    }

}