package cs4321.project1;

import cs4321.project1.list.DivisionListNode;
import cs4321.project1.list.SubtractionListNode;
import cs4321.project1.list.NumberListNode;
import cs4321.project1.list.AdditionListNode;
import cs4321.project1.list.MultiplicationListNode;
import cs4321.project1.list.UnaryMinusListNode;

import java.util.Stack;

/**
 * Evaluates the value of a list which is in the postfix form
 * 
 * @author Gaurav, Tanvi and Sahana (gk368,tmm259 and sv387)
 */
public class EvaluatePostfixListVisitor implements ListVisitor {

    private Stack<Double> result;

    /**
	 * Constructor for evaluation of a postfix list
	 */
	public EvaluatePostfixListVisitor() 
	{
        result = new Stack<Double>();
	}
	
	/**
	 * Getter method to get the final result of a postfix list evaluation
	 */
	public double getResult() 
	{
		return result.pop(); //The final value sitting in the stack is the result of the evaluation
	}

	/**
	 * Visits the number node and pushes it on to the stack
	 * Recursively calls the next node if it is present
	 * @param node
	 * 			The node that needs to be visited
	 */
	@Override
	public void visit(NumberListNode node) {
		
        result.push(node.getData()); //Pushing the operand onto the stack
        if (node.getNext() != null)
            node.getNext().accept(this);
	}

	/**
	 * Visits the addition node and pops two operands from the stack
	 * because addition is a binary operation. It then pushed the result
	 * back onto the stack
	 * @param node
	 * 			The node that needs to be visited
	 */
	@Override
	public void visit(AdditionListNode node) {
		
        double operand1 = result.pop();
        double operand2 = result.pop();
        result.push(operand1 + operand2);
        if (node.getNext() != null)
            node.getNext().accept(this);
    }
	
	/**
	 * Visits the subraction node and pops two operands from the stack
	 * because subraction is a binary operation. It then pushes the result
	 * back onto the stack
	 * @param node
	 * 			The node that needs to be visited
	 */
	@Override
	public void visit(SubtractionListNode node) {
		
        double operand1 = result.pop();
        double operand2 = result.pop();
        result.push(operand2 - operand1);
        if (node.getNext() != null)
            node.getNext().accept(this);

    }
	
	/**
	 * Visits the multiplication node and pops two operands from the stack
	 * because multiplication is a binary operation. It then pushes the result
	 * back onto the stack
	 * @param node
	 * 			The node that needs to be visited
	 */
	@Override
	public void visit(MultiplicationListNode node) {
		
        double operand1 = result.pop();
        double operand2 = result.pop();
        result.push(operand1 * operand2);
        if (node.getNext() != null)
            node.getNext().accept(this);
	}

	/**
	 * Visits the division node and pops two operands from the stack
	 * because division is a binary operation. It then pushes the result
	 * back onto the stack
	 * @param node
	 * 			The node that needs to be visited
	 */
	@Override
	public void visit(DivisionListNode node) {
		
        double operand1 = result.pop();
        double operand2 = result.pop();
        result.push(operand2 / operand1);
        if (node.getNext() != null)
            node.getNext().accept(this);
	}

	/**
	 * Visits the unary minus node and pops one operand from the stack
	 * because it is an unary operation. It then pushes the result
	 * back onto the stack
	 * @param node
	 * 			The node that needs to be visited
	 */
	@Override
	public void visit(UnaryMinusListNode node) {
		
        double operand1 = result.pop();
        result.push(-1 * operand1);
        if (node.getNext() != null)
            node.getNext().accept(this);
	}

}
