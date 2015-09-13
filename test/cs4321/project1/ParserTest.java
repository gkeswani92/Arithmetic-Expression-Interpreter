package cs4321.project1;

import static org.junit.Assert.*;

import org.junit.Test;

import cs4321.project1.tree.TreeNode;

public class ParserTest {

	/*
	 * This class depends on the correct functioning of PrintTreeVisitor(), which is provided for you.
	 */
			
	@Test
	public void testSingleNumber() {
		Parser p1 = new Parser("1.0");
		TreeNode parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("1.0", v1.getResult());

	}
	
	@Test
	public void testUnaryMinusSimple() {
		Parser p1 = new Parser("- 1.0");
		TreeNode parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("(-1.0)", v1.getResult());

	}
	
	@Test
	public void testUnaryMinusComplex() {
		Parser p1 = new Parser("- - 1.0");
		TreeNode parseResult1 =  p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("(-(-1.0))", v1.getResult());

	}

    @Test
    public void testAdditionSimple() {
        Parser p1 = new Parser("( - 1.0 + 2.0 )");
        TreeNode parseResult1 =  p1.parse();
        PrintTreeVisitor v1 = new PrintTreeVisitor();
        parseResult1.accept(v1);
        assertEquals("((-1.0)+2.0)", v1.getResult());
    }

    @Test
    public void testSubtractionSimple() {
        Parser p1 = new Parser("- 1.0 - - 2.0");
        TreeNode parseResult1 =  p1.parse();
        PrintTreeVisitor v1 = new PrintTreeVisitor();
        parseResult1.accept(v1);
        assertEquals("((-1.0)-(-2.0))", v1.getResult());
    }

    @Test
    public void testMultiplicationSimple() {
        Parser p1 = new Parser("- 1.0 * - 2.0");
        TreeNode parseResult1 =  p1.parse();
        PrintTreeVisitor v1 = new PrintTreeVisitor();
        parseResult1.accept(v1);
        assertEquals("((-1.0)*(-2.0))", v1.getResult());
    }

    @Test
    public void testDivisionSimple() {
        Parser p1 = new Parser("- 1.0 / - 2.0");
        TreeNode parseResult1 =  p1.parse();
        PrintTreeVisitor v1 = new PrintTreeVisitor();
        parseResult1.accept(v1);
        assertEquals("((-1.0)/(-2.0))", v1.getResult());
    }

    @Test
    public void testBodmasSimple() {
        Parser p1 = new Parser("- 2.0 * ( 3.0 + 1.0 )");
        TreeNode parseResult1 =  p1.parse();
        PrintTreeVisitor v1 = new PrintTreeVisitor();
        parseResult1.accept(v1);
        assertEquals("((-2.0)*(3.0+1.0))", v1.getResult());
    }

    @Test
    public void testBodmasComplex() {
        Parser p1 = new Parser("( 5 * 2.0 ) / 6.5");
        TreeNode parseResult1 =  p1.parse();
        PrintTreeVisitor v1 = new PrintTreeVisitor();
        parseResult1.accept(v1);
        assertEquals("((5.0*2.0)/6.5)", v1.getResult());
    }

    @Test
    public void testBodmasComplex2() {
        Parser p1 = new Parser("( 5 + 2.0 ) - 6.5");
        TreeNode parseResult1 =  p1.parse();
        PrintTreeVisitor v1 = new PrintTreeVisitor();
        parseResult1.accept(v1);
        assertEquals("((5.0+2.0)-6.5)", v1.getResult());
    }
    
    @Test
    public void testLeftToRightAssociaction() {
        Parser p1 = new Parser("- 2 * ( - 9 / 3 ) + 6");
        TreeNode parseResult1 =  p1.parse();
        PrintTreeVisitor v1 = new PrintTreeVisitor();
        parseResult1.accept(v1);
        assertEquals("(((-2.0)*((-9.0)/3.0))+6.0)", v1.getResult());
    }

    @Test
    public void testAdditionSimple2() {
        Parser p1 = new Parser("5 + 2 + 7");
        TreeNode parseResult1 =  p1.parse();
        PrintTreeVisitor v1 = new PrintTreeVisitor();
        parseResult1.accept(v1);
        assertEquals("((5.0+2.0)+7.0)", v1.getResult());
    }

    @Test
    public void testComplexExpression() {
        Parser p1 = new Parser("( ( ( 8 + 4 ) * 7 ) / 12 )");
        TreeNode parseResult1 =  p1.parse();
        PrintTreeVisitor v1 = new PrintTreeVisitor();
        parseResult1.accept(v1);
        assertEquals("(((8.0+4.0)*7.0)/12.0)", v1.getResult());
    }

    @Test
    public void testComplexExpression2(){
        Parser p1 = new Parser("4 + ( 1 / 12 )");
        TreeNode parseResult1 =  p1.parse();
        PrintTreeVisitor v1 = new PrintTreeVisitor();
        parseResult1.accept(v1);
        assertEquals("(4.0+(1.0/12.0))", v1.getResult());
    }

    @Test
    public void testBodmasComplex1() {
        Parser p1 = new Parser("( - 2 * ( - 9 / 3 ) + ( ( ( 8 + 4 ) * 7 ) / 12 ) - ( 6 - ( - 4 ) )");
        TreeNode parseResult1 =  p1.parse();
        PrintTreeVisitor v1 = new PrintTreeVisitor();
        parseResult1.accept(v1);
        assertEquals("((((-2.0)*((-9.0)/3.0))+(((8.0+4.0)*7.0)/12.0))-(6.0-(-4.0)))", v1.getResult());
    }


    @Test
    public void testLeftToRightAssociation() {
        Parser p1 = new Parser("- 2 * ( - 9 / 3 ) + 6");
        TreeNode parseResult1 =  p1.parse();
        PrintTreeVisitor v1 = new PrintTreeVisitor();
        parseResult1.accept(v1);
        assertEquals("(((-2.0)*((-9.0)/3.0))+6.0)", v1.getResult());
    }
    
    @Test
    public void simpleParserTestFromPiazza(){    	
    	Parser p1 = new Parser("( 1.0 + 65.0 )");
        TreeNode parseResult1 =  p1.parse();
        PrintTreeVisitor v1 = new PrintTreeVisitor();
        parseResult1.accept(v1);
        assertEquals("(1.0+65.0)", v1.getResult());
    }
    
    @Test
    public void simpleAssociationTest(){    	
    	Parser p1 = new Parser("8 / 4 / 2 + 5 + 3");
        TreeNode parseResult1 =  p1.parse();
        PrintTreeVisitor v1 = new PrintTreeVisitor();
        parseResult1.accept(v1);
        assertEquals("((((8.0/4.0)/2.0)+5.0)+3.0)", v1.getResult());
    }
}
