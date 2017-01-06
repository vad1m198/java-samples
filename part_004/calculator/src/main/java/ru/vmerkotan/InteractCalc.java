package ru.vmerkotan;

import ru.vmerkotan.actions.CosAction;
import ru.vmerkotan.actions.DivideAction;
import ru.vmerkotan.actions.MultiplyAction;
import ru.vmerkotan.actions.SinAction;
import ru.vmerkotan.actions.SubtractAction;
import ru.vmerkotan.actions.SumAction;
import ru.vmerkotan.exceptions.InvalidArgumentsException;
import ru.vmerkotan.exceptions.InvalidKeyException;
import ru.vmerkotan.input.ConsoleInput;
import ru.vmerkotan.input.Input;
import ru.vmerkotan.output.ConsoleOutput;
import ru.vmerkotan.output.Output;

/**
 * The {@code InteractCalc} class represents runner to
 * start calculator.
 *
 * Created by vmerkotan on 1/6/2017.
 */
public class InteractCalc {
    /**
     * Inner Calculator instance.
     */
    private Calculator calc;
    /**
     * Inner Input instance.
     */
    private Input input;
    /**
     * Inner Output instance.
     */
    private Output output;

    /**
     * Constructs new InteractCalc object.
     * @param calc  Calculator instance.
     * @param input Input instance.
     * @param output Output instance
     */
    InteractCalc(Calculator calc, Input input, Output output) {
        this.calc = calc;
        this.input = input;
        this.output = output;
    }

    /**
     * Main method.
     * @param args  String[] passed arguments.
     */
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.addAction(new SumAction("sum", "This operation sums all passed params"));
        calc.addAction(new SubtractAction("sub", "This operation subtracts second argument from the first"));
        calc.addAction(new MultiplyAction("mul", "This operation multiplies all passed arguments"));
        calc.addAction(new DivideAction("div", "This operation divides first argument by second"));
        calc.addAction(new CosAction("cos", "This operation calculates cosine of given argument"));
        calc.addAction(new SinAction("sin", "This operation calculates sinus of given argument"));
        Input input = new ConsoleInput();
        Output output = new ConsoleOutput();
        InteractCalc runner = new InteractCalc(calc, input, output);
        runner.init();
    }

    /**
     * Runs application.
     */
    void init() {
        boolean exit = false;
        while (!exit) {
            this.output.write(this.calc.getDescriptions());
            this.output.write("Please type the operation key, press enter then type parameters separated by space and press enter to get the result."
                                + System.getProperty("line.separator") + "You can reuse last result just type '-r' instead of any argument."
                                + " Please be aware by default -r will be equal to 0");
            validateInput();
            this.output.write("Type 'exit' to stop the program");
            if ("exit".equals(this.input.read())) {
                exit = true;
            }
        }
    }

    /**
     * Catches exceptions and asks to input actions one more time.
     */
    private void validateInput() {
        boolean isValid = false;
        while (!isValid) {
            try {
                String key = this.input.read();
                this.calc.executeByKey(key, this.input.read().split(" "));
                this.output.write("result: " + this.calc.getResult());
                rerunOperation(key);
                isValid = true;
            } catch (InvalidArgumentsException | InvalidKeyException rte) {
                this.output.write(rte.getMessage());
            }
        }
    }

    /**
     * Executes operation by key if user want to rerun.
     * @param key   String Action key representation.
     */
    private void rerunOperation(String key) {
        boolean rerun = true;
        while (rerun) {
            this.output.write("Do you want to rerun last operation?");
            String answer = this.input.read().trim();
            if ("yes".equals(answer)) {
                try {
                    this.output.write("Please type arguments");
                    this.calc.executeByKey(key, this.input.read().split(" "));
                    this.output.write("result: " + this.calc.getResult());
                } catch (InvalidArgumentsException iae) {
                    this.output.write(iae.getMessage());
                }
            } else {
                rerun = false;
            }
        }


    }

}
