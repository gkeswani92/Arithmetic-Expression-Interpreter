package cs4321.project1;

import cs4321.project1.tree.*;

import java.util.Stack;

/**
 * Traverse expression tree and evaluate it to a single number
 * 
 * @author Gaurav, Tanvi and Sahana (gk368,tmm259 and sv387)
 */

public class EvaluateTreeVisitor implements TreeVisitor {

    private Stack<Double> operands = new Stack<Double>();
    private double result;
    private double operand1;
    private double operand2;

	public EvaluateTreeVisitor() {
        result = 0;
	}

	public double getResult() {
        result = operands.pop();
		return result; // so that skeleton code compiles
	}

	@Override
	public void visit(LeafTreeNode node) {
        operands.push(node.getData());
	}

    /**
     * Evaluates the unary tree node, adds the result to the stack and recursively calls the child node.
     * @param node unary minus node
     */
	@Override
	public void visit(UnaryMinusTreeNode node) {
        node.getChild().accept(this);

        operand1 = operands.pop();
        operands.push(-1 * operand1);
	}

    /**
     * Evaluates the addition tree node, adds the result to the stack and recursively calls the child nodes.
     * @param node addition tree node
     */
	@Override
	public void visit(AdditionTreeNode node) {
        node.getLeftChild().accept(this);
        node.getRightChild().accept(this);

        operand1 = operands.pop();
        operand2 = operands.pop();
        operands.push(operand1 + operand2);
	}

    /**
     * Evaluates the multiplication tree node, adds the result to the stack and recursively calls the child nodes.
     * @param node multiplication tree node
     */
	@Override
	public void visit(MultiplicationTreeNode node) {
        node.getLeftChild().accept(this);
        node.getRightChild().accept(this);

        operand1 = operands.pop();
        operand2 = operands.pop();
        operands.push(operand1 * operand2);
	}

    /**
     * Evaluates the subtraction tree node, adds the result to the stack and recursively calls the child nodes.
     * @param node subtraction tree node
     */
	@Override
	public void visit(SubtractionTreeNode node) {
        node.getLeftChild().accept(this);
        node.getRightChild().accept(this);
        operand1 = operands.pop();
        operand2 = operands.pop();
        operands.push(operand2 - operand1);
	}

    /**
     * Evaluates the division tree node, adds the result to the stack and recursively calls the child nodes.
     * @param node division tree node
     */
	@Override
	public void visit(DivisionTreeNode node) {
        node.getLeftChild().accept(this);
        node.getRightChild().accept(this);

        operand1 = operands.pop();
        operand2 = operands.pop();
        operands.push(operand2 / operand1);
	}
}