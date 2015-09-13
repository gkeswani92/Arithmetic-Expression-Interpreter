package cs4321.project1;

import cs4321.project1.list.*;

/**
 * Visitor to pretty-print the expression in either prefix or postfix form
 * 
 * @author Gaurav, Tanvi and Sahana (gk368,tmm259 and sv387)
 */

public class PrintListVisitor implements ListVisitor {

    private String result; // running string representation

	public PrintListVisitor() {
        result = "";
	}

	/**
     * Getter method to get the final pretty printed list
     */
	public String getResult() {
		return result.trim();
	}
	
	/**
	 * Method to check if a node has a next node and recursively calls 
	 * the same if true
	 * 
	 * @param node
	 * 				the node who's child we need to test for
	 */				
	public void checkAndCallNextNode(ListNode node){
		if (node.getNext() != null) {
        	node.getNext().accept(this);
        }
	}
	
    /**
     * Visit method for leaf node; just concatenates the numeric value to the
     * running string
     *
     * @param node
     *            the node to be visited
     */
	@Override
	public void visit(NumberListNode node) {
        result += node.getData(); //Concatenates the data from the number node to the result
        if (node.getNext() != null) {
        	result += " ";
        	node.getNext().accept(this);
        }
	}

	/**
	 * Visit method for the addition list node; concatenates a + sign
	 * to the running string and recursively calls the next node
	 * using visitor pattern
	 * 
	 * @param node
	 * 				the node to be visited
	 */
	@Override
	public void visit(AdditionListNode node) {
		result += "+ ";
		checkAndCallNextNode(node);
		result += " ";
	}

	/**
	 * Visit method for the subraction list node; concatenates a - sign
	 * to the running string and recursively calls the next node
	 * using visitor pattern
	 * 
	 * @param node
	 * 				the node to be visited
	 */
	@Override
	public void visit(SubtractionListNode node) {
		result += "- ";
		checkAndCallNextNode(node);
		result += " ";
	}

	/**
	 * Visit method for the multiplication list node; concatenates a * sign
	 * to the running string and recursively calls the next node
	 * using visitor pattern
	 * 
	 * @param node
	 * 				the node to be visited
	 */
	@Override
	public void visit(MultiplicationListNode node) {
		result += "* ";
		checkAndCallNextNode(node);
		result += " ";
	}

	/**
	 * Visit method for the division list node; concatenates a + sign
	 * to the running string and recursively calls the next node
	 * using visitor pattern
	 * 
	 * @param node
	 * 				the node to be visited
	 */
	@Override
	public void visit(DivisionListNode node) {
		result += "/ ";
		checkAndCallNextNode(node);
		result += " ";
	}

	/**
	 * Visit method for the unary minus list node; concatenates a ~ sign
	 * to the running string and recursively calls the next node
	 * using visitor patter
	 * 
	 * @param node
	 * 				the node to be visited
	 */
	@Override
	public void visit(UnaryMinusListNode node) {
		result += "~ ";
		checkAndCallNextNode(node);
	}
}
