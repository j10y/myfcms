/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.zving.framework.ssi;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

// Referenced classes of package com.zving.framework.ssi:
//            ExpressionTokenizer, SSIMediator

public class ExpressionParseTree {
	private final class AndNode extends OppNode {

		public boolean evaluate() {
			if (!left.evaluate())
				return false;
			else
				return right.evaluate();
		}

		public int getPrecedence() {
			return 1;
		}

		public String toString() {
			return left + " " + right + " AND";
		}

		private AndNode() {
			super(null);
		}

		AndNode(AndNode andnode) {
			this();
		}
	}

	private abstract class CompareNode extends OppNode {

		protected int compareBranches() {
			String val1 = ((StringNode) left).getValue();
			String val2 = ((StringNode) right).getValue();
			return val1.compareTo(val2);
		}

		private CompareNode() {
			super(null);
		}

		CompareNode(CompareNode comparenode) {
			this();
		}
	}

	private final class EqualNode extends CompareNode {

		public boolean evaluate() {
			return compareBranches() == 0;
		}

		public int getPrecedence() {
			return 4;
		}

		public String toString() {
			return left + " " + right + " EQ";
		}

		private EqualNode() {
			super(null);
		}

		EqualNode(EqualNode equalnode) {
			this();
		}
	}

	private final class GreaterThanNode extends CompareNode {

		public boolean evaluate() {
			return compareBranches() > 0;
		}

		public int getPrecedence() {
			return 4;
		}

		public String toString() {
			return left + " " + right + " GT";
		}

		private GreaterThanNode() {
			super(null);
		}

		GreaterThanNode(GreaterThanNode greaterthannode) {
			this();
		}
	}

	private final class LessThanNode extends CompareNode {

		public boolean evaluate() {
			return compareBranches() < 0;
		}

		public int getPrecedence() {
			return 4;
		}

		public String toString() {
			return left + " " + right + " LT";
		}

		private LessThanNode() {
			super(null);
		}

		LessThanNode(LessThanNode lessthannode) {
			this();
		}
	}

	private abstract class Node {

		public abstract boolean evaluate();

		private Node() {
		}

		Node(Node node) {
			this();
		}
	}

	private final class NotNode extends OppNode {

		public boolean evaluate() {
			return !left.evaluate();
		}

		public int getPrecedence() {
			return 5;
		}

		public void popValues(List values) {
			left = (Node) values.remove(0);
		}

		public String toString() {
			return left + " NOT";
		}

		private NotNode() {
			super(null);
		}

		NotNode(NotNode notnode) {
			this();
		}
	}

	private abstract class OppNode extends Node {

		public abstract int getPrecedence();

		public void popValues(List values) {
			right = (Node) values.remove(0);
			left = (Node) values.remove(0);
		}

		Node left;
		Node right;

		private OppNode() {
			super(null);
		}

		OppNode(OppNode oppnode) {
			this();
		}
	}

	private final class OrNode extends OppNode {

		public boolean evaluate() {
			if (left.evaluate())
				return true;
			else
				return right.evaluate();
		}

		public int getPrecedence() {
			return 1;
		}

		public String toString() {
			return left + " " + right + " OR";
		}

		private OrNode() {
			super(null);
		}

		OrNode(OrNode ornode) {
			this();
		}
	}

	private class StringNode extends Node {

		public String getValue() {
			if (resolved == null)
				resolved = ssiMediator.substituteVariables(value.toString());
			return resolved;
		}

		public boolean evaluate() {
			return getValue().length() != 0;
		}

		public String toString() {
			return value.toString();
		}

		StringBuffer value;
		String resolved;

		public StringNode(String value) {
			super(null);
			resolved = null;
			this.value = new StringBuffer(value);
		}
	}

	public ExpressionParseTree(String expr, SSIMediator ssiMediator) throws ParseException {
		nodeStack = new LinkedList();
		oppStack = new LinkedList();
		this.ssiMediator = ssiMediator;
		parseExpression(expr);
	}

	public boolean evaluateTree() {
		return root.evaluate();
	}

	private void pushOpp(OppNode node) {
		if (node == null) {
			oppStack.add(0, node);
			return;
		}
		do {
			if (oppStack.size() == 0)
				break;
			OppNode top = (OppNode) oppStack.get(0);
			if (top == null || top.getPrecedence() < node.getPrecedence())
				break;
			oppStack.remove(0);
			top.popValues(nodeStack);
			nodeStack.add(0, top);
		} while (true);
		oppStack.add(0, node);
	}

	private void resolveGroup() {
		for (OppNode top = null; (top = (OppNode) oppStack.remove(0)) != null;) {
			top.popValues(nodeStack);
			nodeStack.add(0, top);
		}

	}

	private void parseExpression(String expr) throws ParseException {
		StringNode currStringNode = null;
		pushOpp(null);
		ExpressionTokenizer et;
		for (et = new ExpressionTokenizer(expr); et.hasMoreTokens();) {
			int token = et.nextToken();
			if (token != 0)
				currStringNode = null;
			switch (token) {
			case 12: // '\f'
			default:
				break;

			case 0: // '\0'
				if (currStringNode == null) {
					currStringNode = new StringNode(et.getTokenValue());
					nodeStack.add(0, currStringNode);
				} else {
					currStringNode.value.append(" ");
					currStringNode.value.append(et.getTokenValue());
				}
				break;

			case 1: // '\001'
				pushOpp(new AndNode(null));
				break;

			case 2: // '\002'
				pushOpp(new OrNode(null));
				break;

			case 3: // '\003'
				pushOpp(new NotNode(null));
				break;

			case 4: // '\004'
				pushOpp(new EqualNode(null));
				break;

			case 5: // '\005'
				pushOpp(new NotNode(null));
				oppStack.add(0, new EqualNode(null));
				break;

			case 6: // '\006'
				resolveGroup();
				break;

			case 7: // '\007'
				pushOpp(null);
				break;

			case 8: // '\b'
				pushOpp(new NotNode(null));
				oppStack.add(0, new LessThanNode(null));
				break;

			case 9: // '\t'
				pushOpp(new NotNode(null));
				oppStack.add(0, new GreaterThanNode(null));
				break;

			case 10: // '\n'
				pushOpp(new GreaterThanNode(null));
				break;

			case 11: // '\013'
				pushOpp(new LessThanNode(null));
				break;
			}
		}

		resolveGroup();
		if (nodeStack.size() == 0)
			throw new ParseException("No nodes created.", et.getIndex());
		if (nodeStack.size() > 1)
			throw new ParseException("Extra nodes created.", et.getIndex());
		if (oppStack.size() != 0) {
			throw new ParseException("Unused opp nodes exist.", et.getIndex());
		} else {
			root = (Node) nodeStack.get(0);
			return;
		}
	}

	private LinkedList nodeStack;
	private LinkedList oppStack;
	private Node root;
	private SSIMediator ssiMediator;
	private static final int PRECEDENCE_NOT = 5;
	private static final int PRECEDENCE_COMPARE = 4;
	private static final int PRECEDENCE_LOGICAL = 1;

}


/*
	DECOMPILATION REPORT

	Decompiled from: E:\xiacc\workspace\zcms\WebRoot\WEB-INF\lib\com.jar
	Total time: 47 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/