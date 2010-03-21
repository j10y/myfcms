 package com.zving.framework.ssi;
 
 import java.text.ParseException;
 import java.util.LinkedList;
 import java.util.List;
 
 public class ExpressionParseTree
 {
   private LinkedList nodeStack = new LinkedList();
 
   private LinkedList oppStack = new LinkedList();
   private Node root;
   private SSIMediator ssiMediator;
   private static final int PRECEDENCE_NOT = 5;
   private static final int PRECEDENCE_COMPARE = 4;
   private static final int PRECEDENCE_LOGICAL = 1;
 
   public ExpressionParseTree(String expr, SSIMediator ssiMediator)
     throws ParseException
   {
     this.ssiMediator = ssiMediator;
     parseExpression(expr);
   }
 
   public boolean evaluateTree()
   {
     return this.root.evaluate();
   }
 
   private void pushOpp(OppNode node)
   {
     if (node == null) {
       this.oppStack.add(0, node);
       return;
     }
 
     while (this.oppStack.size() != 0) {
       OppNode top = (OppNode)this.oppStack.get(0);
 
       if (top == null) {
         break;
       }
       if (top.getPrecedence() < node.getPrecedence())
         break;
       this.oppStack.remove(0);
 
       top.popValues(this.nodeStack);
 
       this.nodeStack.add(0, top);
     }
 
     this.oppStack.add(0, node);
   }
 
   private void resolveGroup()
   {
     OppNode top = null;
     while ((top = (OppNode)this.oppStack.remove(0)) != null)
     {
       top.popValues(this.nodeStack);
 
       this.nodeStack.add(0, top);
     }
   }
 
   private void parseExpression(String expr)
     throws ParseException
   {
     StringNode currStringNode = null;
 
     pushOpp(null);
     ExpressionTokenizer et = new ExpressionTokenizer(expr);
     while (et.hasMoreTokens()) {
       int token = et.nextToken();
       if (token != 0)
         currStringNode = null;
       switch (token)
       {
       case 0:
         if (currStringNode == null) {
           currStringNode = new StringNode(et.getTokenValue());
           this.nodeStack.add(0, currStringNode);
         }
         else {
           currStringNode.value.append(" ");
           currStringNode.value.append(et.getTokenValue());
         }
 
         break;
       case 1:
         pushOpp(new AndNode(null));
         break;
       case 2:
         pushOpp(new OrNode(null));
         break;
       case 3:
         pushOpp(new NotNode(null));
         break;
       case 4:
         pushOpp(new EqualNode(null));
         break;
       case 5:
         pushOpp(new NotNode(null));
 
         this.oppStack.add(0, new EqualNode(null));
         break;
       case 6:
         resolveGroup();
         break;
       case 7:
         pushOpp(null);
         break;
       case 8:
         pushOpp(new NotNode(null));
 
         this.oppStack.add(0, new LessThanNode(null));
         break;
       case 9:
         pushOpp(new NotNode(null));
 
         this.oppStack.add(0, new GreaterThanNode(null));
         break;
       case 10:
         pushOpp(new GreaterThanNode(null));
         break;
       case 11:
         pushOpp(new LessThanNode(null));
       case 12:
       }
 
     }
 
     resolveGroup();
     if (this.nodeStack.size() == 0) {
       throw new ParseException("No nodes created.", et.getIndex());
     }
     if (this.nodeStack.size() > 1) {
       throw new ParseException("Extra nodes created.", et.getIndex());
     }
     if (this.oppStack.size() != 0) {
       throw new ParseException("Unused opp nodes exist.", et.getIndex());
     }
     this.root = ((Node)this.nodeStack.get(0));
   }
 
   private final class AndNode extends ExpressionParseTree.OppNode
   {
     final ExpressionParseTree this$0;
 
     private AndNode()
     {
       super(???, null); this.this$0 = ???; }
 
     public boolean evaluate() { if (!(this.left.evaluate()))
         return false;
       return this.right.evaluate();
     }
 
     public int getPrecedence()
     {
       return 1;
     }
 
     public String toString()
     {
       return this.left + " " + this.right + " AND";
     }
 
     AndNode(AndNode paramAndNode)
     {
       this(this$1);
     }
   }
 
   private abstract class CompareNode extends ExpressionParseTree.OppNode
   {
     final ExpressionParseTree this$0;
 
     private CompareNode()
     {
       super(???, null); this.this$0 = ???; }
 
     protected int compareBranches() { String val1 = ((ExpressionParseTree.StringNode)this.left).getValue();
       String val2 = ((ExpressionParseTree.StringNode)this.right).getValue();
       return val1.compareTo(val2);
     }
 
     CompareNode(CompareNode paramCompareNode)
     {
       this(this$1); }
   }
 
   private final class EqualNode extends ExpressionParseTree.CompareNode {
     final ExpressionParseTree this$0;
 
     private EqualNode() {
       super(???, null); this.this$0 = ???; }
 
     public boolean evaluate() { return (compareBranches() == 0);
     }
 
     public int getPrecedence()
     {
       return 4;
     }
 
     public String toString()
     {
       return this.left + " " + this.right + " EQ";
     }
 
     EqualNode(EqualNode paramEqualNode)
     {
       this(this$1);
     }
   }
 
   private final class GreaterThanNode extends ExpressionParseTree.CompareNode
   {
     final ExpressionParseTree this$0;
 
     private GreaterThanNode()
     {
       super(???, null); this.this$0 = ???; }
 
     public boolean evaluate() { return (compareBranches() > 0);
     }
 
     public int getPrecedence()
     {
       return 4;
     }
 
     public String toString()
     {
       return this.left + " " + this.right + " GT";
     }
 
     GreaterThanNode(GreaterThanNode paramGreaterThanNode)
     {
       this(this$1);
     }
   }
 
   private final class LessThanNode extends ExpressionParseTree.CompareNode
   {
     final ExpressionParseTree this$0;
 
     private LessThanNode()
     {
       super(???, null); this.this$0 = ???; }
 
     public boolean evaluate() { return (compareBranches() < 0);
     }
 
     public int getPrecedence()
     {
       return 4;
     }
 
     public String toString()
     {
       return this.left + " " + this.right + " LT";
     }
 
     LessThanNode(LessThanNode paramLessThanNode)
     {
       this(this$1);
     }
   }
 
   private abstract class Node
   {
     final ExpressionParseTree this$0;
 
     private Node()
     {
       this.this$0 = ???; } 
     public abstract boolean evaluate();
 
     Node(Node paramNode) { this(this$1);
     }
   }
 
   private final class NotNode extends ExpressionParseTree.OppNode
   {
     final ExpressionParseTree this$0;
 
     private NotNode()
     {
       super(???, null); this.this$0 = ???; }
 
     public boolean evaluate() { return (!(this.left.evaluate()));
     }
 
     public int getPrecedence()
     {
       return 5;
     }
 
     public void popValues(List values)
     {
       this.left = ((ExpressionParseTree.Node)values.remove(0));
     }
 
     public String toString()
     {
       return this.left + " NOT";
     }
 
     NotNode(NotNode paramNotNode)
     {
       this(this$1);
     }
   }
 
   private abstract class OppNode extends ExpressionParseTree.Node
   {
     ExpressionParseTree.Node left;
     ExpressionParseTree.Node right;
     final ExpressionParseTree this$0;
 
     private OppNode()
     {
       super(???, null); this.this$0 = ???;
     }
 
     public abstract int getPrecedence();
 
     public void popValues(List values)
     {
       this.right = ((ExpressionParseTree.Node)values.remove(0));
       this.left = ((ExpressionParseTree.Node)values.remove(0));
     }
 
     OppNode(OppNode paramOppNode)
     {
       this(this$1);
     }
   }
 
   private final class OrNode extends ExpressionParseTree.OppNode
   {
     final ExpressionParseTree this$0;
 
     private OrNode()
     {
       super(???, null); this.this$0 = ???; }
 
     public boolean evaluate() { if (this.left.evaluate())
         return true;
       return this.right.evaluate();
     }
 
     public int getPrecedence()
     {
       return 1;
     }
 
     public String toString()
     {
       return this.left + " " + this.right + " OR";
     }
 
     OrNode(OrNode paramOrNode)
     {
       this(this$1);
     }
   }
 
   private class StringNode extends ExpressionParseTree.Node
   {
     StringBuffer value;
     String resolved = null;
 
     public StringNode(String paramString) {
       super(ExpressionParseTree.this, null);
       this.value = new StringBuffer(paramString);
     }
 
     public String getValue()
     {
       if (this.resolved == null)
         this.resolved = ExpressionParseTree.this.ssiMediator.substituteVariables(this.value.toString());
       return this.resolved;
     }
 
     public boolean evaluate()
     {
       return (getValue().length() != 0);
     }
 
     public String toString()
     {
       return this.value.toString();
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.ssi.ExpressionParseTree
 * JD-Core Version:    0.5.3
 */