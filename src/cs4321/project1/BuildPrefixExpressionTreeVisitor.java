package cs4321.project1;

import cs4321.project1.list.*;
import cs4321.project1.tree.*;

/**
 * This class constructs Prefix list representation of the intermediate infix tree
 * by appending the current node to the list and 
 * traversing  recursively the left child node and the right child node
 * @author Gaurav, Tanvi and Sahana (gk368,tmm259 and sv387)
 */
public class BuildPrefixExpressionTreeVisitor implements TreeVisitor {

    private ListNode tempResult;
    private ListNode result;
    
    /* 
     * Constructor for the BuildPrefixExpressionTreeVisitor 
     */

    public BuildPrefixExpressionTreeVisitor() {
		tempResult = null;
        result = null;
	}
    
    /*
	 * Method to return the resultant Prefix expression List 
	 */

	public ListNode getResult() {
		// TODO fill me in
		return result;
	}
	
	/*
	 * Method to read the value of leaf (number) node
	 * and updates the temporary list that has its head node 
	 * referenced by result list forming the prefix representation of the given expression
	 * 
	 * @see cs4321.project1.TreeVisitor#visit(cs4321.project1.tree.LeafTreeNode)
	 */

	@Override
	public void visit(LeafTreeNode node) {
		// TODO fill me in
        if (tempResult != null) {
            tempResult.setNext(new NumberListNode(node.getData()));
        } else {
            tempResult = new NumberListNode(node.getData());
            result = tempResult;
        }
        tempResult = tempResult.getNext();
	}
	
	/*
	 * On encountering a UnaryMinus tree node appends the unary minus node 
	 * to the expression list and recursively calls the accept method on its single child
	 * for traversing further deep 
	 * @see cs4321.project1.TreeVisitor#visit(cs4321.project1.tree.UnaryMinusTreeNode)
	 */

	@Override
	public void visit(UnaryMinusTreeNode node) {
		// TODO fill me in
        if (tempResult !=null) {
            tempResult.setNext(new UnaryMinusListNode());
            tempResult = tempResult.getNext();
        } else {
            tempResult = new UnaryMinusListNode();
            result = tempResult;
        }

        node.getChild().accept(this);
	}
	
	/*
	 * On encountering an Addition operator node traverses recursively in preorder ie
	 * Appends the temp list with the addition node
	 * left child node to construct a subset prefix expression with the leftchild node as the root
	 * right child node to construct a subset prefix expression with the rightchild node as the root	 
	 * @see cs4321.project1.TreeVisitor#visit(cs4321.project1.tree.AdditionTreeNode)
	 */

	@Override
	public void visit(AdditionTreeNode node) {
		// TODO fill me in
        if (tempResult != null) {
            tempResult.setNext(new AdditionListNode());
            tempResult = tempResult.getNext();
        } else {
            tempResult = new AdditionListNode();
            result = tempResult;
        }

        node.getLeftChild().accept(this);
        node.getRightChild().accept(this);
	}
	
	/*
	 * On encountering an Multiplication operator node traverses recursively in preorder ie
	 * Appends the temp list with the multiplication node
	 * left child node to construct a subset prefix expression with the leftchild node as the root
	 * right child node to construct a subset prefix expression with the rightchild node as the root	
	 * @see cs4321.project1.TreeVisitor#visit(cs4321.project1.tree.MultiplicationTreeNode)
	 */

	@Override
	public void visit(MultiplicationTreeNode node) {
		// TODO fill me in
        if (tempResult != null) {
            tempResult.setNext(new MultiplicationListNode());
            tempResult = tempResult.getNext();
        } else {
            tempResult = new MultiplicationListNode();
            result = tempResult;
        }

        node.getLeftChild().accept(this);
        node.getRightChild().accept(this);
	}
	/*
	 * On encountering an Subtraction operator node traverses recursively in preorder ie
	 * Appends the temp list with the subtraction node
	 * left child node to construct a subset prefix expression with the leftchild node as the root
	 * right child node to construct a subset prefix expression with the rightchild node as the root	
	 * @see cs4321.project1.TreeVisitor#visit(cs4321.project1.tree.SubtractionTreeNode)
	 */

	@Override
	public void visit(SubtractionTreeNode node) {
		// TODO fill me in
        if (tempResult != null) {
            tempResult.setNext(new SubtractionListNode());
            tempResult = tempResult.getNext();
        } else {
            tempResult = new SubtractionListNode();
            result = tempResult;
        }
        node.getLeftChild().accept(this);
        node.getRightChild().accept(this);
	}
	
	/*
	 *On encountering an Division operator node traverses recursively in preorder ie
	 * Appends the temp list with the division node
	 * left child node to construct a subset prefix expression with the leftchild node as the root
	 * right child node to construct a subset prefix expression with the rightchild node as the root	
	 * @see cs4321.project1.TreeVisitor#visit(cs4321.project1.tree.DivisionTreeNode)
	 */

	@Override
	public void visit(DivisionTreeNode node) {
		// TODO fill me in
        if (tempResult != null) {
            tempResult.setNext(new DivisionListNode());
            tempResult = tempResult.getNext();
        } else {
            tempResult = new DivisionListNode();
            result = tempResult;
        }
        node.getLeftChild().accept(this);
        node.getRightChild().accept(this);
	}

}
