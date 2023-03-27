package com.foxminded;

public class Division {

   private static final char NEW_LINE_SYMBOL = '\n';
   private static final char SPACE_SYMBOL = ' ';
   private static final char DASH_SYMBOL = '-';
   private static final char MINUS_SYMBOL = '_';
   private static final String PIPE_SYMBOL = "|";
   private static final String PERCENT_SYMBOL = "%";
   private static final String S_LETTER = "s";
   private static final String D_LETTER = "d";
   private static final int LINE_LENGTH_INCREMENT = 2;
   private static final int NUMBER_OF_LINES_TO_MODIFY = 3;
   private static final int INDEX_OF_FIRST_LINE = 0;
   private static final int INDEX_OF_SECOND_LINE = 1;
   private static final int INDEX_OF_THIRD_LINE = 2;
   private static final int MAX_NUMBER_OF_SKIPPED_DIGIT = 2;

    public String divide(Integer dividend, Integer divisor) throws IllegalArgumentException {

        checkException(dividend, divisor);

        StringBuilder quotient = new StringBuilder();

        if (divisor < 0 ^ dividend < 0) {
            quotient.append('-');
        }

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        StringBuilder dividendSB = new StringBuilder().append(dividend);
        StringBuilder reminder = new StringBuilder();
        StringBuilder result = new StringBuilder();
        Integer reminderDividend;
        int subtrahend;
        int remainder;
        int numberOfSkipDigit = 0;

        for (int i = 0; i < dividendSB.length(); i++) {

            String formula = PERCENT_SYMBOL + (i + LINE_LENGTH_INCREMENT) + S_LETTER;
            String digitFormula = PERCENT_SYMBOL + (i + LINE_LENGTH_INCREMENT) + D_LETTER;
            numberOfSkipDigit++;
            reminder.append(dividendSB.charAt(i));
            reminderDividend = Integer.parseInt(reminder.toString());
            if (reminderDividend >= divisor || dividend < divisor) {
                remainder = reminderDividend % divisor;
                subtrahend = reminderDividend / divisor * divisor;
                quotient.append(reminderDividend / divisor);

                String formatRemainderInteger = String.format(formula, MINUS_SYMBOL + reminderDividend.toString());
                result.append(formatRemainderInteger).append(NEW_LINE_SYMBOL);

                String formatSubtrahend = String.format(digitFormula, subtrahend);
                result.append(formatSubtrahend).append(NEW_LINE_SYMBOL);

                int subtrahendLength = Integer.toString(subtrahend).length();
                result.append(makeTab(formatRemainderInteger.length() - subtrahendLength, subtrahendLength ));

                reminder.replace(0, reminder.length(), Integer.toString(remainder));

                numberOfSkipDigit = 0;
            } else {
                if (reminderDividend == 0) {
                    quotient.append(0);
                }
                if (numberOfSkipDigit == MAX_NUMBER_OF_SKIPPED_DIGIT) {
                    quotient.append(0);
                }
            }
            if (i == dividendSB.length() - 1) {
                result.append(String.format(formula, reminder)).append(NEW_LINE_SYMBOL);
            }

        }
        viewMaker(dividend, divisor, result, quotient);
        return result.toString();
    }

    private void viewMaker(Integer dividend, Integer divisor, StringBuilder result, StringBuilder quotient) {
        int[] firstThreeLines = new int[NUMBER_OF_LINES_TO_MODIFY];
        int j = 0;
        for (int i = 0; i < result.length() && j < NUMBER_OF_LINES_TO_MODIFY; i++) {
            if (result.charAt(i) == NEW_LINE_SYMBOL) {
                firstThreeLines[j] = i;
                j++;
            }
        }
        int tabLength = Math.max(quotient.length(), divisor.toString().length());
        int tab = dividend.toString().length() + 1 - firstThreeLines[0];
        result.insert(firstThreeLines[INDEX_OF_THIRD_LINE], rowMaker(tab, SPACE_SYMBOL) + PIPE_SYMBOL + quotient.toString());
        result.insert(firstThreeLines[INDEX_OF_SECOND_LINE], rowMaker(tab, SPACE_SYMBOL) + PIPE_SYMBOL + rowMaker(tabLength, DASH_SYMBOL));
        result.insert(firstThreeLines[INDEX_OF_FIRST_LINE], PIPE_SYMBOL + divisor);
        result.replace(1, firstThreeLines[0], dividend.toString());
    }

    private String rowMaker(int numberOfSymbols, char symbol) {
        return String.valueOf(symbol).repeat(Math.max(0, numberOfSymbols));
    }


    private String makeTab(Integer indentLength, Integer lengthOfSymbol) {
        return rowMaker(indentLength, SPACE_SYMBOL) + rowMaker(lengthOfSymbol, DASH_SYMBOL) + NEW_LINE_SYMBOL;

    }

    private void checkException(Integer dividend, Integer divisor) {
        if (dividend == null || divisor == null) {
            throw new IllegalArgumentException("null input");
        } else if (divisor.equals(0)) {
            throw new ArithmeticException("you cant divide by zero");
        }
    }

}
