package klarna;

public class PostFixCalculator {

    private static final String ADD = "+"; 
    private static final String SUB = "-";
    private static final String MUL = "*";
    private static final String DIV = "/";

    public double evaluate(String expr) {

        SinglyLinkedListStack<Double> stack = new SinglyLinkedListStack<>();
        String[] el = expr.split(" ");
        double operand1, operand2;

        for(int i = 0; i < el.length; i++) {
            if( el[i].equals(ADD) || el[i].equals(SUB) || el[i].equals(MUL) || el[i].equals(DIV) ) {
                operand2 = stack.pop();
                operand1 = stack.pop();
                switch(el[i]) {
                    case ADD: {
                        double local = operand1 + operand2;
                        stack.push(local);
                        break;
                    }

                    case SUB: {
                        double local = operand1 - operand2;
                        stack.push(local);
                        break;
                    }

                    case MUL: {
                        double local = operand1 * operand2;
                        stack.push(local);
                        break;
                    }

                    case DIV: {
                        double local = operand1 / operand2;
                        stack.push(local);
                        break;
                    }
                }
            } else {
                stack.push(Double.parseDouble(el[i]));
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        System.out.println(new PostFixCalculator().evaluate("5 1 2 + 4 * + 3 -"));
    }
}