package org.yates.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;


// TODO: gather the courage to document this mess
public class InputHandler {
    private final Predicate<String> validator;
    private final String errorMessage;
    private final BufferedReader reader;
    private final Logger log;
    private String prompt;

    private InputHandler(Predicate<String> validator, String prompt, String errorMessage, BufferedReader reader, String loggerName) {
        this.validator = validator;
        this.prompt = prompt;
        this.errorMessage = errorMessage;
        this.reader = reader;
        this.log = Logger.getLogger(loggerName);
    }

    @Contract(" -> new")
    public static @NotNull Builder builder() {
        return new Builder();
    }

    public boolean isValid(@NotNull String input) {
        return validator.test(input.toLowerCase());
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String query() {
        while (true) {
            System.out.print(prompt);
            try {
                String in = reader.readLine();
                if (in == null)
                    throw new NullPointerException("No input detected.");
                in = in.toLowerCase();
                if (isValid(in))
                    return in;
                throw new InputMismatchException(in);
            } catch (IOException e) {
                log.log(Level.SEVERE, "Unrecoverable error occurred.", e.getMessage());
                System.out.println(e.getLocalizedMessage());
                System.out.println(Arrays.toString(e.getStackTrace()));
            } catch (InputMismatchException e) {
                String response = e.getMessage();
                System.out.printf("'%s' is not a valid response.%n", response);
                System.out.println(errorMessage);
            } catch (NullPointerException e) {
                log.warning(e.getMessage());
                System.out.println("Let's try again...");
            }
        }
    }

    public static class Builder {
        private Predicate<String> validator = in -> true;
        private String prompt = "-> ";
        private String errorMessage = "Invalid input.";
        private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        private String loggerName = "YatesLogger";

        public Builder validator(Predicate<String> validator) {
            this.validator = validator;
            return this;
        }

        public Builder setReader(BufferedReader reader) {
            this.reader = reader;
            return this;
        }

        public Builder prompt(String prompt) {
            this.prompt = prompt;
            return this;
        }

        public Builder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public Builder loggerName(String loggerName) {
            this.loggerName = loggerName;
            return this;
        }

        public InputHandler build() {
            return new InputHandler(validator, prompt, errorMessage, reader, loggerName);
        }

    }
}
