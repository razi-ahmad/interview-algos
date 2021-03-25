package twillio;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;


class Result {

    private static final String GSM_7_REGEX = "[A-Za-z0-9 .,]+";
    /*
     * Complete the 'calculateTotalMessageCost' function below.
     *
     * The function is expected to return a DOUBLE.
     * The function accepts STRING_ARRAY messages as parameter.
     */

    public static double calculateTotalMessageCost(List<String> messages) {
        messages.forEach(System.out::println);
        if (Objects.isNull(messages) || messages.isEmpty())
            return 0.0;

        double cost = 0.0;
        TiwilioOperators operator = null;
        for (String message : messages) {
            if (matchGSM7(message)) {
                operator = TiwilioOperators.GSM7;
            } else {
                operator = TiwilioOperators.UCS2;
            }

            for (int x = 0; x < message.length(); ) {
                cost += operator.getCharges();
                x += operator.getLimit();
            }
        }
        DecimalFormat df = new DecimalFormat("#.###");
        return Double.parseDouble(df.format(cost));
        //return ((double) Math.round(cost * 1000d) / 1000d);
    }

    private static boolean matchGSM7(String message) {
        return Pattern.matches(GSM_7_REGEX, message);
    }

}

enum TiwilioOperators {
    GSM7(0.01, 160), UCS2(0.015, 70);
    private double charges;
    private int limi;

    private TiwilioOperators(double charges, int limit) {
        this.charges = charges;
        this.limi = limit;
    }

    public double getCharges() {
        return charges;
    }

    public int getLimit() {
        return limi;
    }
}
