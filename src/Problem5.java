public class Problem5 {
    public static String multBrute(String A, String B) {
        SignedNumber first = parse(A);
        SignedNumber second = parse(B);
        String product = multiplySchool(first.digits, second.digits);
        return format(product, first.negative ^ second.negative);
    }

    public static String multSmart(String A, String B) {
        SignedNumber first = parse(A);
        SignedNumber second = parse(B);
        String product = karatsuba(first.digits, second.digits);
        return format(product, first.negative ^ second.negative);
    }

    private static String karatsuba(String first, String second) {
        if (first.equals("0") || second.equals("0")) {
            return "0";
        }
        if (Math.min(first.length(), second.length()) <= 32) {
            return multiplySchool(first, second);
        }

        int split = Math.max(first.length(), second.length()) / 2;
        String firstLow = lowPart(first, split);
        String firstHigh = highPart(first, split);
        String secondLow = lowPart(second, split);
        String secondHigh = highPart(second, split);

        String lowProduct = karatsuba(firstLow, secondLow);
        String highProduct = karatsuba(firstHigh, secondHigh);
        String middleProduct = karatsuba(add(firstLow, firstHigh), add(secondLow, secondHigh));
        String crossProduct = subtract(subtract(middleProduct, lowProduct), highProduct);
        return add(add(appendZeros(highProduct, split * 2), appendZeros(crossProduct, split)), lowProduct);
    }

    private static String multiplySchool(String first, String second) {
        if (first.equals("0") || second.equals("0")) {
            return "0";
        }
        int[] digits = new int[first.length() + second.length()];
        for (int firstIndex = first.length() - 1; firstIndex >= 0; firstIndex--) {
            int digitA = first.charAt(firstIndex) - '0';
            for (int secondIndex = second.length() - 1; secondIndex >= 0; secondIndex--) {
                int digitB = second.charAt(secondIndex) - '0';
                int position = firstIndex + secondIndex + 1;
                int value = digitA * digitB + digits[position];
                digits[position] = value % 10;
                digits[position - 1] += value / 10;
            }
        }
        StringBuilder result = new StringBuilder(digits.length);
        int index = digits[0] == 0 ? 1 : 0;
        for (; index < digits.length; index++) {
            result.append((char) ('0' + digits[index]));
        }
        return normalize(result.toString());
    }

    private static String add(String first, String second) {
        StringBuilder result = new StringBuilder(Math.max(first.length(), second.length()) + 1);
        int firstIndex = first.length() - 1;
        int secondIndex = second.length() - 1;
        int carry = 0;
        while (firstIndex >= 0 || secondIndex >= 0 || carry != 0) {
            int value = carry;
            if (firstIndex >= 0) {
                value += first.charAt(firstIndex--) - '0';
            }
            if (secondIndex >= 0) {
                value += second.charAt(secondIndex--) - '0';
            }
            result.append((char) ('0' + value % 10));
            carry = value / 10;
        }
        return result.reverse().toString();
    }

    private static String subtract(String first, String second) {
        StringBuilder result = new StringBuilder(first.length());
        int firstIndex = first.length() - 1;
        int secondIndex = second.length() - 1;
        int borrow = 0;
        while (firstIndex >= 0) {
            int value = first.charAt(firstIndex--) - '0' - borrow;
            int digitB = secondIndex >= 0 ? second.charAt(secondIndex--) - '0' : 0;
            if (value < digitB) {
                value += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result.append((char) ('0' + value - digitB));
        }
        return normalize(result.reverse().toString());
    }

    private static String lowPart(String value, int split) {
        return normalize(value.substring(Math.max(0, value.length() - split)));
    }

    private static String highPart(String value, int split) {
        int end = Math.max(0, value.length() - split);
        return end == 0 ? "0" : normalize(value.substring(0, end));
    }

    private static String appendZeros(String value, int count) {
        if (value.equals("0")) {
            return value;
        }
        return value + "0".repeat(count);
    }

    private static String normalize(String value) {
        int firstNonzero = 0;
        while (firstNonzero < value.length() - 1 && value.charAt(firstNonzero) == '0') {
            firstNonzero++;
        }
        return value.substring(firstNonzero);
    }

    private static SignedNumber parse(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Input must be a non-empty integer string");
        }
        boolean negative = value.charAt(0) == '-';
        int start = negative || value.charAt(0) == '+' ? 1 : 0;
        if (start == value.length()) {
            throw new IllegalArgumentException("Input must contain digits");
        }
        for (int index = start; index < value.length(); index++) {
            if (value.charAt(index) < '0' || value.charAt(index) > '9') {
                throw new IllegalArgumentException("Input contains a non-digit character");
            }
        }
        String digits = normalize(value.substring(start));
        return new SignedNumber(digits, negative && !digits.equals("0"));
    }

    private static String format(String digits, boolean negative) {
        return negative && !digits.equals("0") ? "-" + digits : digits;
    }

    private static final class SignedNumber {
        private final String digits;
        private final boolean negative;

        private SignedNumber(String digits, boolean negative) {
            this.digits = digits;
            this.negative = negative;
        }
    }
}