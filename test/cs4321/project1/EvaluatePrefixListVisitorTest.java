package cs4321.project1;

import static org.junit.Assert.*;

import org.junit.Test;

import cs4321.project1.list.*;

public class EvaluatePrefixListVisitorTest {
	
	private static final double DELTA = 1e-15;

	@Test
	public void testSingleNumberNode() {
		ListNode n1 = new NumberListNode(1.0);
		EvaluatePrefixListVisitor v1 = new EvaluatePrefixListVisitor();
		n1.accept(v1);
		assertEquals(1.0, v1.getResult(), DELTA);
	}

	@Test
	public void testAdditionSimple() {
		ListNode n1 = new NumberListNode(1.0);
		ListNode n2 = new NumberListNode(2.0);
		ListNode n3 = new AdditionListNode();
		n3.setNext(n2);
		n2.setNext(n1);
		EvaluatePrefixListVisitor v1 = new EvaluatePrefixListVisitor();
		n3.accept(v1);
		assertEquals(3.0, v1.getResult(), DELTA);
		
		ListNode n4 = new NumberListNode(1.0);
		ListNode n5 = new NumberListNode(2.0);
		ListNode n6 = new AdditionListNode();
		n6.setNext(n5);
		n5.setNext(n4);
		EvaluatePrefixListVisitor v2 = new EvaluatePrefixListVisitor();
		n6.accept(v2);
		assertEquals(3.0, v2.getResult(), DELTA);
	}
	
	@Test
	public void testAdditionMultipleInstances() {
		ListNode n1 = new NumberListNode(1.0);
		ListNode n2 = new NumberListNode(2.0);
		ListNode n3 = new AdditionListNode();
		ListNode n4 = new NumberListNode(3.0);
		ListNode n5 = new AdditionListNode();
		n5.setNext(n4);
		n4.setNext(n3);
		n3.setNext(n2);
		n2.setNext(n1);
		EvaluatePrefixListVisitor v1 = new EvaluatePrefixListVisitor();
		n5.accept(v1);
		assertEquals(6.0, v1.getResult(), DELTA);
	}

	@Test
	public void testComplexExpression() {
		ListNode n1 = new NumberListNode(1.0);
		ListNode n2 = new NumberListNode(1.0);
		ListNode n3 = new AdditionListNode();
		ListNode n4 = new NumberListNode(2.0);
		ListNode n5 = new AdditionListNode();
		ListNode n6 = new NumberListNode(3.0);
		ListNode n7 = new NumberListNode(1.0);
		ListNode n8 = new NumberListNode(1.0);
		ListNode n9 = new AdditionListNode();
		ListNode n10 = new NumberListNode(7.0);
		ListNode n11 = new SubtractionListNode();
		ListNode n12 = new NumberListNode(15.0);
		ListNode n13 = new DivisionListNode();
		ListNode n14 = new MultiplicationListNode();
		ListNode n15 = new SubtractionListNode();
		n15.setNext(n14);
		n14.setNext(n13);
		n13.setNext(n12);
		n12.setNext(n11);
		n11.setNext(n10);
		n10.setNext(n9);
		n9.setNext(n8);
		n8.setNext(n7);
		n7.setNext(n6);
		n6.setNext(n5);
		n5.setNext(n4);
		n4.setNext(n3);
		n3.setNext(n2);
		n2.setNext(n1); //Expression is - * / 15 - 7 + 1 1 3 + 2 + 1 1
		
		EvaluatePrefixListVisitor v1 = new EvaluatePrefixListVisitor();
		n15.accept(v1);
		assertEquals(5.0, v1.getResult(), DELTA);
	}
	
	@Test
	public void testSimpleExpression() {
		ListNode n1 = new NumberListNode(3.0);
		ListNode n2 = new NumberListNode(2.0);
		ListNode n3 = new NumberListNode(1.0);
		ListNode n4 = new AdditionListNode();
		ListNode n5 = new SubtractionListNode();
		ListNode n6 = new NumberListNode(6.0);
		ListNode n7 = new NumberListNode(8.0);
		ListNode n8 = new AdditionListNode();
		ListNode n9 = new MultiplicationListNode();
		
		n9.setNext(n8);
		n8.setNext(n7);
		n7.setNext(n6);
		n6.setNext(n5);
		n5.setNext(n4);
		n4.setNext(n3);
		n3.setNext(n2);
		n2.setNext(n1); //Expression is * + 8 6 - + 1 2 3
		
		EvaluatePrefixListVisitor v1 = new EvaluatePrefixListVisitor();
		n9.accept(v1);
		assertEquals(0.0, v1.getResult(), DELTA);
	}
}
