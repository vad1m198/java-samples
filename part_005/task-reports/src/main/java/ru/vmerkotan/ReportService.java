package ru.vmerkotan;

import ru.vmerkotan.output.Output;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Reporst service.
 *
 * Created by vmerkotan on 1/19/2017.
 */
public class ReportService {
    /**
     * Private Output to write result to.
     */
    private Output output;

    /**
     * Creates new ReportService instance.
     *
     * @param output    Output to write result to.
     */
    ReportService(Output output) {
        this.output = output;
    }

    /**
     * Prints tasks operation for passed range
     * to the output.
     *
     * @param tasks     List Task to print report for.
     * @param start     Calendar start range.
     * @param finish    Calendar finish range.
     */
    void printRangeReport(List<Task> tasks, Calendar start, Calendar finish) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");

        tasks.stream()
            .filter(task -> task.getOperations()
                .stream()
                .filter(operation -> (operation.getExecutedDate().after(start) || operation.getExecutedDate().equals(start))
                        && (operation.getExecutedDate().before(finish) || operation.getExecutedDate().equals(finish)))
                .collect(Collectors.toList()).size() > 0)
            .forEach(i -> {
                output.write(i.toString());
                i.getOperations()
                    .stream()
                    .filter(operation -> (operation.getExecutedDate().after(start) || operation.getExecutedDate().equals(start))
                            && (operation.getExecutedDate().before(finish) || operation.getExecutedDate().equals(finish)))
                    .forEach(o -> output.write(o.toString()));
            });
    }

}
