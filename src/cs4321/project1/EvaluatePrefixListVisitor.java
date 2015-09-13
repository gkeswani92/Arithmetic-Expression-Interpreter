package cs4321.project1;

import cs4321.project1.list.*;
import javafx.util.Pair;

import java.util.Stack;

/**
 * Evaluates the value of a list which is in the postfix form
 * 
 * @author Gaurav, Tanvi and Sahana (gk368,tmm259 and sv387)
 */

public class EvaluatePrefixListVisitor implements ListVisitor {

    private Stack<Pair<Character,Integer>> operators;
    private Stack<Double> operands;

    /**
     * Constructor for the evaluation of a list in prefix form.
     * It initialises a stack for the operators and operands each
     */
	public EvaluatePrefixListVisitor() {
        operators = new Stack<Pair<Character, Integer>>();
        operands = new Stack<Double>();
	}
	
	/**
	 * Getter method to get the final result of a prefix list evaluation
	 */
	public double getResult() {
		return operands.pop(); // so that skeleton code compiles
	}
	
	/**
	 * Visits the number node and pushes it on to the stack and reduces 
	 * the current operators required count by 1. If the required count
	 * goes down to 0, it evaluates the value of that expression and pushed
	 * the new value onto the stack.
	 * 
	 * It also recursively calls the next node if it is present
	 * 
	 * @param node
	 * 			The node that needs to be visited
	 */
	@Override
	public void visit(NumberListNode node) {
		
		//Push the operand on to the stack
        operands.push(node.getData());

        if (!operators.empty()) 
        {
        	//Pop the current operator i.e. head from the stack and decrement its
        	//count by 1 since we have added an operand on the stack
            Pair<Character, Integer> currentOperator = operators.pop();
            Integer count = currentOperator.getValue() - 1;
            
            //When the expected operand count goes down to zero for an operator
            //Perform the required operation
            if (count == 0) 
            {	
                //Evaluate the value of the current expression depending on what
            	//the operator is and push the new value on to the stack
                evaluateExpression(currentOperator.getKey());
                
                //If there is an operator below the current one on the stack, 
                //reduce its count by 1 since we are pushing the result of the 
                //current operation on to the stack
                if (!operators.empty()) 
                {
                    Pair<Character, Integer> newHead = operators.pop();
                    
                    //Repeat this process until the time we fulfill the conditions
                    //of an operator i.e. its count becomes zero or we run out of 
                    //elements
                    while (newHead != null && newHead.getValue() - 1 == 0)
                    {
                    	evaluateExpression(newHead.getKey());
                    	
                    	//New value of new head if there is something on the stack
                    	if (!operators.empty())
                    		newHead = operators.pop();
                    	//If stack is empty, it is time to exit :)
                    	else
                    		newHead = null;
                    }
                    if (newHead != null)
                    	operators.push(new Pair<Character, Integer>(newHead.getKey(), newHead.getValue() - 1));
                }
            }
            
            //Since required operand count is not yet zero, we just pushed 
            //the operator with a reduced count back onto the stack
            else
                operators.push(new Pair<Character, Integer>(currentOperator.getKey(), count));
       
            //Recursively call the next node if it exists
            if (node.getNext() != null)
                node.getNext().accept(this);
            
        }
	}
	
	/**
	 * Visits the addition node and adds it to the operator stack 
	 * along with the number of required operands i.e. 2 since it 
	 * is a binary operation. 
	 * 
	 * It then recursively calls the next node if there is one that
	 * exists.
	 * @param node
	 * 			The node that needs to be visited
	 */
	@Override
	public void visit(AdditionListNode node) {
		
        operators.push(new Pair<Character, Integer>('+', 2));
        
        if( node.getNext() != null)
            node.getNext().accept(this);
	}
	
	/**
	 * Visits the subraction node and adds it to the operator stack 
	 * along with the number of required operands i.e. 2 since it 
	 * is a binary operation. 
	 * 
	 * It then recursively calls the next node if there is one that
	 * exists.
	 * @param node
	 * 			The node that needs to be visited
	 */
	@Override
	public void visit(SubtractionListNode node) {
		// TODO fill me in
        operators.push(new Pair<Character, Integer>('-', 2));

        if( node.getNext() != null)
            node.getNext().accept(this);
	}

	/**
	 * Visits the multiplication node and adds it to the operator stack 
	 * along with the number of required operands i.e. 2 since it 
	 * is a binary operation. 
	 * 
	 * It then recursively calls the next node if there is one that
	 * exists.
	 * @param node
	 * 			The node that needs to be visited
	 */
	@Override
	public void visit(MultiplicationListNode node) {
		// TODO fill me in
        operators.push(new Pair<Character, Integer>('*', 2));

        if( node.getNext() != null)
            node.getNext().accept(this);
	}
	
	/**
	 * Visits the division node and adds it to the operator stack 
	 * along with the number of required operands i.e. 2 since it 
	 * is a binary operation. 
	 * 
	 * It then recursively calls the next node if there is one that
	 * exists.
	 * @param node
	 * 			The node that needs to be visited
	 */
	@Override
	public void visit(DivisionListNode node) {
		// TODO fill me in
        operators.push(new Pair<Character, Integer>('/', 2));

        if( node.getNext() != null)
            node.getNext().accept(this);
	}
	
	/**
	 * Visits the unary minus node and adds it to the operator stack 
	 * along with the number of required operands i.e. 1 since it 
	 * is a unary operation. 
	 * 
	 * It then recursively calls the next node if there is one that
	 * exists.
	 * @param node
	 * 			The node that needs to be visited
	 */
	@Override
	public void visit(UnaryMinusListNode node) {
		// TODO fill me in
        operators.push(new Pair<Character, Integer>('~', 1));

        if( node.getNext() != null)
            node.getNext().accept(this);
	}
	
	/**
	 * The method receives the operations that needs to be performed
	 * and evaluates the expression by popping the required number
	 * of operands from the operand stack
	 * @param operator
	 * 			The operation that needs to be performed
	 */
    private void evaluateExpression(Character operator) {

        double operand1, operand2;
        switch (operator) {
            case '+': 
            	operands.push(operands.pop() + operands.pop());
                break;

            case '-': 
            	operand1 = operands.pop();
                operand2 = operands.pop();
                operands.push(operand2 - operand1);
                break;

            case '*': 
            	operands.push(operands.pop() * operands.pop());
                break;

            case '/': 
            	operand1 = operands.pop();
                operand2 = operands.pop();
                operands.push(operand2 / operand1);
                break;

            case '~': 
            	operands.push(-1 * operands.pop());
            	break;
        }
    }
}
