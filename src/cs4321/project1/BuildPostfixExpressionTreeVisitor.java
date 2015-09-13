package cs4321.project1;

import cs4321.project1.list.*;
import cs4321.project1.tree.*;

/**
 * This class constructs Postfix list representation of the intermediate infix tree node
 * by traversing recursively the left child node, right child node and appending the current node
 * @author Gaurav, Tanvi and Sahana (gk368,tmm259 and sv387)
 */
public class BuildPostfixExpressionTreeVisitor implements TreeVisitor {

    private ListNode tempResult;
    private ListNode result;
    
    /* 
     * Constructor for the BuildPostfixExpressionTreeVisitor 
     */

	public BuildPostfixExpressionTreeVisitor() {
		// TODO fill me in
        tempResult = null;
        result = null;
	}
	
	/*
	 * Method to return the resultant Postfix expression List 
	 */

	public ListNode getResult() {
		// TODO fill me in
		return result;
	}
	
	/*
	 * Method to read the value of leaf (number) node
	 * and updates the temporary list that has its head node 
	 * referenced by result list forming the postfix representation of the given expression
	 * 
	 * @see cs4321.project1.TreeVisitor#visit(cs4321.project1.tree.LeafTreeNode)
	 */

	@Override
	public void visit(LeafTreeNode node) {
		// TODO fill me in
		if(tempResult != null){
			tempResult.setNext(new NumberListNode(node.getData()));
			tempResult = tempResult.getNext();
		}		
		else{
			tempResult = new NumberListNode(node.getData());
			result = tempResult;
		}			
			
	}
	/*
	 * On encountering a UnaryMinus tree node reads just its single child node recursively
	 * and calls the accept function on its child node for recursively traversing deep before 
	 * appending the unary minus to the expression list
	 * @see cs4321.project1.TreeVisitor#visit(cs4321.project1.tree.UnaryMinusTreeNode)
	 */
	@Override
	public void visit(UnaryMinusTreeNode node) {
		// TODO fill me in
		node.getChild().accept(this);
		tempResult.setNext(new UnaryMinusListNode());  
		tempResult = tempResult.getNext();
	}

	/*
	 * On encountering an Addition operator node traverses recursively in postorder ie
	 * left child node to construct a subset postfix expression with the leftchild node as the root
	 * right child node to construct a subset postfix expression with the rightchild node as the root
	 * Appends the temp list with the addition node
	 * @see cs4321.project1.TreeVisitor#visit(cs4321.project1.tree.AdditionTreeNode)
	 */
			
	@Override
	public void visit(AdditionTreeNode node) {
		// TODO fill me in
        node.getLeftChild().accept(this);
        node.getRightChild().accept(this);        
        tempResult.setNext(new AdditionListNode());
        tempResult = tempResult.getNext();        
    }
	
	/*
	 * On encountering an Multiplication operator node traverses recursively in postorder i.e.
	 * left child node to construct a subset postfix expression with the leftchild node as the root
	 * right child node to construct a subset postfix expression with the rightchild node as the root
	 * Appends the temp list with the multiplication node 
	 * @see cs4321.project1.TreeVisitor#visit(cs4321.project1.tree.MultiplicationTreeNode)
	 */

	@Override
	public void visit(MultiplicationTreeNode node) {
		// TODO fill me in
		node.getLeftChild().accept(this);
        node.getRightChild().accept(this);
        tempResult.setNext(new MultiplicationListNode());
        tempResult = tempResult.getNext();
	}
	
	/*
	 * On encountering an Subtraction operator node traverses recursively in postorder i.e.
	 * left child node to construct a subset postfix expression with the leftchild node as the root
	 * right child node to construct a subset postfix expression with the rightchild node as the root
	 * Appends the temp list with the subtraction node 
	 * @see cs4321.project1.TreeVisitor#visit(cs4321.project1.tree.SubtractionTreeNode)
	 */

	@Override
	public void visit(SubtractionTreeNode node) {
		// TODO fill me in
		node.getLeftChild().accept(this);
        node.getRightChild().accept(this);
        tempResult.setNext(new SubtractionListNode()); 
        tempResult = tempResult.getNext();
    }
	
	/*
	 * On encountering an Division operator node traverses recursively in postorder i.e.
	 * left child node to construct a subset postfix expression with the leftchild node as the root
	 * right child node to construct a subset postfix expression with the rightchild node as the root
	 * Appends the temp list with the division node 
	 * @see cs4321.project1.TreeVisitor#visit(cs4321.project1.tree.DivisionTreeNode)
	 */

	@Override
	public void visit(DivisionTreeNode node) {
		// TODO fill me in
		node.getLeftChild().accept(this);
        node.getRightChild().accept(this);
        tempResult.setNext(new DivisionListNode()); 
        tempResult = tempResult.getNext();
    }

}
