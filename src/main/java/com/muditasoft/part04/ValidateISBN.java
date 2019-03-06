package com.muditasoft.part04;

public class ValidateISBN {

    private static final int LONG_ISBN_LENGTH = 13;
    private static final int SHORT_ISBN_LENGTH = 10;

    public boolean checkISBN(String isbn) {

        if (isbn.length() == LONG_ISBN_LENGTH) {
            return isThisAValid13DigitISBN(isbn);
        } else {
            if (isbn.length() != SHORT_ISBN_LENGTH)
                throw new NumberFormatException("ISBN numbers must be 10 digits long");

            return isThisAValid10DigitISBN(isbn);
        }

    }

    private boolean isThisAValid10DigitISBN(String isbn) {
        int total = 0;

        for (int i = 0; i < 10; i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                if (i == 9 && isbn.charAt(i) == 'X')
                    total += 10;    // X is 10
                else
                    throw new NumberFormatException("ISBN numbers can only contain numeric digits");
            } else
                total += Character.getNumericValue(isbn.charAt(i)) * (10 - i);
        }

        return total % 11 == 0;
    }

    private boolean isThisAValid13DigitISBN(String isbn) {
        int total = 0;

        for (int i = 0; i < 13; i++) {
            if (i % 2 == 0) {
                total += Character.getNumericValue(isbn.charAt(i));
            } else {
                total += Character.getNumericValue(isbn.charAt(i)) * 3;
            }
        }
        return total % 10 == 0;
    }
}
