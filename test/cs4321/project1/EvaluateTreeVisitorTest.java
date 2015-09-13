package cs4321.project1;

import static org.junit.Assert.*;

import org.junit.Test;

import cs4321.project1.tree.*;

public class EvaluateTreeVisitorTest {

	private static final double DELTA = 1e-15;

	@Test
	public void testSingleLeafNode() {
		TreeNode n1 = new LeafTreeNode(1.0);
        EvaluateTreeVisitor v1 = new EvaluateTreeVisitor();
		n1.accept(v1);
		assertEquals(1.0, v1.getResult(), DELTA);
	}
	
	@Test
	public void testAdditionNode() {
		TreeNode n1 = new LeafTreeNode(1.0);
		TreeNode n2 = new LeafTreeNode(2.0);
		TreeNode n3 = new AdditionTreeNode(n1, n2);
		TreeNode n4 = new AdditionTreeNode(n2, n1);

        // 1 + 2
        EvaluateTreeVisitor v1 = new EvaluateTreeVisitor();
		n3.accept(v1);
		assertEquals(3.0, v1.getResult(), DELTA);

        // 2 + 1
        EvaluateTreeVisitor v2 = new EvaluateTreeVisitor();
		n4.accept(v2);
		assertEquals(3.0, v2.getResult(), DELTA);
	}
	
	@Test
	public void testMultiplicationNode() {
		TreeNode n1 = new LeafTreeNode(1.0);
		TreeNode n2 = new LeafTreeNode(2.0);
		TreeNode n3 = new MultiplicationTreeNode(n1, n2);
		TreeNode n4 = new MultiplicationTreeNode(n2, n1);
        EvaluateTreeVisitor v1 = new EvaluateTreeVisitor();
		n3.accept(v1);
		assertEquals(2.0, v1.getResult(), DELTA);
        EvaluateTreeVisitor v2 = new EvaluateTreeVisitor();
		n4.accept(v2);
		assertEquals(2.0, v2.getResult(), DELTA);
	}
	
	@Test
	public void testUnaryMinusBinaryTree() {
		TreeNode n1 = new LeafTreeNode(4.0);
		TreeNode n2 = new LeafTreeNode(5.0);
		TreeNode n3 = new UnaryMinusTreeNode(n1);
		TreeNode n4 = new UnaryMinusTreeNode(n2);
		TreeNode n5 = new MultiplicationTreeNode(n3,n4);		
        EvaluateTreeVisitor v1 = new EvaluateTreeVisitor();
		n5.accept(v1);
		assertEquals(20.0, v1.getResult(), DELTA);        		
	}
	
	@Test
	public void treeEvalBalancedTree() {
		TreeNode n1 = new LeafTreeNode(1.0);
		TreeNode n2 = new LeafTreeNode(2.0);
		TreeNode n3 = new LeafTreeNode(3.0);
		TreeNode n4 = new LeafTreeNode(4.0);
		TreeNode n5 = new AdditionTreeNode(n1,n2);
		TreeNode n6 = new MultiplicationTreeNode(n3,n4);
		TreeNode n7 = new DivisionTreeNode(n6,n5);		
        EvaluateTreeVisitor v1 = new EvaluateTreeVisitor();
		n7.accept(v1);
		assertEquals(4.0, v1.getResult(), DELTA);       		
	}
	
	@Test
	public void nonNumberNodeForUnaryMinus(){
		TreeNode n1 = new LeafTreeNode(1.0);
		TreeNode n2 = new LeafTreeNode(2.0);
		TreeNode n3 = new AdditionTreeNode(n1, n2);
		TreeNode n4 = new UnaryMinusTreeNode(n3);
		EvaluateTreeVisitor v1 = new EvaluateTreeVisitor();
		n4.accept(v1);
		assertEquals(-3.0, v1.getResult(),DELTA);		
	}
}
