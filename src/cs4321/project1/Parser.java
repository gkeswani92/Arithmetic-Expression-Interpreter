package cs4321.project1;

import cs4321.project1.tree.*;

/**
 * Class for a parser that can parse a string and produce an expression tree. To
 * keep the code simple, this does no input checking whatsoever so it only works
 * on correct input.
 * 
 * An expression is one or more terms separated by + or - signs. A term is one
 * or more factors separated by * or / signs. A factor is an expression in
 * parentheses (), a factor with a unary - before it, or a number.
 * 
 * @author Lucja Kot
 * @author Gaurav, Tanvi and Sahana (gk368,tmm259 and sv387)
 */
public class Parser {

	private String[] tokens;
	private int currentToken; // pointer to next input token to be processed
    private TreeNode result = null;
    private TreeNode result2 = null;

	/**
	 * @precondition input represents a valid expression with all tokens
	 *               separated by spaces, e.g. "3.0 - ( 1.0 + 2.0 ) / - 5.0. All
	 *               tokens must be either numbers that parse to Double, or one
	 *               of the symbols +, -, /, *, ( or ), and all parentheses must
	 *               be matched and properly nested.
	 */
	public Parser(String input) {
		this.tokens = input.split("\\s+");
		currentToken = 0;
	}

	/**
	 * Parse the input and build the expression tree
	 * 
	 * @return the (root node of) the resulting tree
	 */
	public TreeNode parse() {
		return expression();
	}

	/**
	 * Parse the remaining input as far as needed to get the next factor
	 * 
	 * @return the (root node of) the resulting subtree
	 */
	private TreeNode factor() {
        String operand = tokens[currentToken];

        // F := number
        if (isNumber(operand)) {
            result = new LeafTreeNode(Double.parseDouble(operand));
        // F := -F
        } else if (operand.equalsIgnoreCase("-")) {
            currentToken++;
            result = factor();
            return new UnaryMinusTreeNode(result);
        // F := (E)
        } else {
            currentToken++;
            result = expression();
        }

        currentToken++;
        return result;
	}

	/**
	 * Parse the remaining input as far as needed to get the next term
	 * 
	 * @return the (root node of) the resulting subtree
	 */
	private TreeNode term() {
        TreeNode result1;
        String operator = "";
        result = factor();
        result1 = result;

        while (currentToken < tokens.length && tokens[currentToken] != null &&
                (tokens[currentToken].equalsIgnoreCase("*") || tokens[currentToken].equalsIgnoreCase("/"))) {
            operator = tokens[currentToken];
            currentToken++;
            result2 = factor();

            if (operator.equalsIgnoreCase("*")) {
                result = new MultiplicationTreeNode(result1, result2);
                result1 = result;
            } else if (operator.equalsIgnoreCase("/")) {
                result = new DivisionTreeNode(result1, result2);
                result1 = result;
            }

            //currentToken++;

            // factor() does not increment the currentToken like term does so have to handle this case separately
//            if (currentToken < tokens.length && tokens[currentToken] != null && tokens[currentToken].equalsIgnoreCase(")")) {
//                currentToken++;
//            }
        }
		return result;

	}

	/**
	 * Parse the remaining input as far as needed to get the next expression
	 * 
	 * @return the (root node of) the resulting subtree
	 */
	private TreeNode expression() {
        String operator = "";
        result = term();
        TreeNode result1 = result;
        while (currentToken < tokens.length && tokens[currentToken] != null &&
                (tokens[currentToken].equalsIgnoreCase("+") || tokens[currentToken].equalsIgnoreCase("-"))) {
            operator = tokens[currentToken];
            currentToken++;
            result2 = term();

            if (operator.equalsIgnoreCase("+")) {
                result = new AdditionTreeNode(result1, result2);
                result1 = result;
            } else if(operator.equalsIgnoreCase("-")) {
                result = new SubtractionTreeNode(result1, result2);
                result1 = result;
            }

//            if (currentToken < tokens.length && tokens[currentToken] != null && tokens[currentToken].equalsIgnoreCase(")")) {
//                currentToken++;
//            }
        }
		return result;

	}

    /**
     * Checks if argument is a number(double)
     * @param operand
     * @return true if argument is a number, false otherwise
     */
    boolean isNumber(String operand) {
        try {
            Double.parseDouble(operand);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
