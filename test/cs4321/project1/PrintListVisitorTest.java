package cs4321.project1;

import static org.junit.Assert.*;
import cs4321.project1.list.*;

import org.junit.Test;

public class PrintListVisitorTest {

	@Test
	public void testSingleNumberNode() {
		ListNode n1 = new NumberListNode(1.0);
		PrintListVisitor pv1 = new PrintListVisitor();
		n1.accept(pv1);
		assertEquals("1.0", pv1.getResult());
	}
	
	@Test
	public void testAdditionSimplePrefix() {
		ListNode n1 = new NumberListNode(1.0);
		ListNode n2 = new NumberListNode(2.0);
		ListNode n3 = new AdditionListNode();
		n3.setNext(n2);
		n2.setNext(n1);
		PrintListVisitor pv1 = new PrintListVisitor();
		n3.accept(pv1);
		assertEquals("+ 2.0 1.0", pv1.getResult());
	}
	
	@Test
	public void testAdditionSimplePostfix() {
		ListNode n1 = new NumberListNode(1.0);
		ListNode n2 = new NumberListNode(2.0);
		ListNode n3 = new AdditionListNode();
		n1.setNext(n2);
		n2.setNext(n3);
		PrintListVisitor pv1 = new PrintListVisitor();
		n1.accept(pv1);
		assertEquals("1.0 2.0 +", pv1.getResult());
	}
	
	@Test
	public void nestedUnbalancedTreePrefix() {
		ListNode n1 = new NumberListNode(4.0);
		ListNode n2 = new NumberListNode(5.0);
		ListNode n3 = new NumberListNode(15.0);
		ListNode n4 = new NumberListNode(3.0);
		ListNode n5 = new DivisionListNode();
		ListNode n6 = new MultiplicationListNode();
		ListNode n7 = new AdditionListNode();
		n6.setNext(n1);
		n1.setNext(n7);
		n7.setNext(n2);
		n2.setNext(n5);
		n5.setNext(n3);
		n3.setNext(n4);
		PrintListVisitor pv1 = new PrintListVisitor();
		n6.accept(pv1);
		assertEquals("* 4.0 + 5.0 / 15.0 3.0", pv1.getResult());
	}
	
	@Test
	public void nestedUnbalancedTreePostfix() {
		ListNode n1 = new NumberListNode(4.0);
		ListNode n2 = new NumberListNode(5.0);
		ListNode n3 = new NumberListNode(15.0);
		ListNode n4 = new NumberListNode(3.0);
		ListNode n5 = new DivisionListNode();
		ListNode n6 = new MultiplicationListNode();
		ListNode n7 = new AdditionListNode();
		n1.setNext(n2);
		n2.setNext(n3);
		n3.setNext(n4);
		n4.setNext(n5);
		n5.setNext(n7);
		n7.setNext(n6);
		PrintListVisitor pv1 = new PrintListVisitor();
		n1.accept(pv1);
		assertEquals("4.0 5.0 15.0 3.0 / + *", pv1.getResult());
	}
	
	@Test
	public void unaryMinusinPrefix() {
		ListNode n1 = new NumberListNode(8.0);
		ListNode n2 = new NumberListNode(2.0);
		ListNode n3 = new UnaryMinusListNode();
		ListNode n4 = new UnaryMinusListNode();
		ListNode n5 = new DivisionListNode();		
		n5.setNext(n3);
		n3.setNext(n1);
		n1.setNext(n4);
		n4.setNext(n2);		
		PrintListVisitor pv1 = new PrintListVisitor();
		n5.accept(pv1);
		assertEquals("/ ~ 8.0 ~ 2.0", pv1.getResult());
	}
	
	@Test
	public void unaryMinusinPostfix() {
		ListNode n1 = new NumberListNode(8.0);
		ListNode n2 = new NumberListNode(2.0);
		ListNode n3 = new UnaryMinusListNode();
		ListNode n4 = new UnaryMinusListNode();
		ListNode n5 = new DivisionListNode();		
		n1.setNext(n3);
		n3.setNext(n2);
		n2.setNext(n4);
		n4.setNext(n5);		
		PrintListVisitor pv1 = new PrintListVisitor();
		n1.accept(pv1);
		assertEquals("8.0 ~ 2.0 ~ /", pv1.getResult());
	}
}
