package klarna;

/**
 * 111
 **/
class Challenge {
    private static final String ST = "st";
    private static final String ND = "nd";
    private static final String RD = "rd";
    private static final String TH = "th";

    public static String numberToOrdinal(Integer number) {
        if (number == 0) {
            return "";
        }
        int numLen = getDigitLength(number);
        String ordinal = "";

        int lastTwoDigit = getLastTwoDigits(number);
        boolean isElevenSeries = getElevenSeries(lastTwoDigit);
        if (isElevenSeries) {
            ordinal = TH;
        } else {
            ordinal = number > 9 ? getOrdinal(mod10(lastTwoDigit)) : getOrdinal(lastTwoDigit);
        }
        return number.toString() + ordinal;
    }

    private static int getLastTwoDigits(int number) {
        if(number>99){
            return number % 100;
        }
        return number;
    }

    private static boolean getElevenSeries(int number) {
        return number == 11 || number == 12 | number == 13;
    }

    private static String getOrdinal(Integer number) {

        if (number == 1) {
            return ST;
        } else if (number == 2) {
            return ND;
        } else if (number == 3) {
            return RD;
        }
        return TH;
    }

    private static int mod10(int num) {
        return num % 10;
    }

    private static int getDigitLength(int num) {
        int length = 1;
        while (num > 9) {
            num /= 10;
            length += 1;
        }
        return length;
    }

    public static void main(String[] args) {
        System.out.println(numberToOrdinal(101));
    }
}