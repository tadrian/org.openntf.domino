/* Generated By:JJTree: Do not edit this line. SimpleNode.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.openntf.domino.tests.rpr.formula.parse;

import org.openntf.domino.tests.rpr.formula.eval.FormulaContext;
import org.openntf.domino.tests.rpr.formula.eval.ValueHolder;

public abstract class SimpleNode implements Node {

	protected Node parent;
	protected Node[] children;
	protected int id;
	protected AtFormulaParser parser;
	protected int codeLine;
	protected int codeColumn;

	public SimpleNode(final int i) {
		id = i;
	}

	public SimpleNode(final AtFormulaParser p, final int i) {
		this(i);
		parser = p;
		Token t = p.token;
		codeLine = t.beginLine;
		codeColumn = t.beginColumn;
	}

	public EvaluateException createEvaluateException(final Throwable cause) {
		return new EvaluateException(codeLine, codeColumn, cause);
	}

	public void jjtOpen() {
	}

	public void jjtClose() {
	}

	public void jjtSetParent(final Node n) {
		parent = n;
	}

	public Node jjtGetParent() {
		return parent;
	}

	public void jjtAddChild(final Node n, final int i) {
		if (children == null) {
			children = new Node[i + 1];
		} else if (i >= children.length) {
			Node c[] = new Node[i + 1];
			System.arraycopy(children, 0, c, 0, children.length);
			children = c;
		}
		children[i] = n;
	}

	public Node jjtGetChild(final int i) {
		return children[i];
	}

	public int jjtGetNumChildren() {
		return (children == null) ? 0 : children.length;
	}

	/* You can override these two methods in subclasses of SimpleNode to
	   customize the way the node appears when the tree is dumped.  If
	   your output uses more than one line you should override
	   toString(String), otherwise overriding toString() is probably all
	   you need to do. */

	@Override
	public String toString() {
		return AtFormulaParserTreeConstants.jjtNodeName[id];
	}

	public String toString(final String prefix) {
		return prefix + toString();
	}

	/* Override this method if you want to customize how the node dumps
	   out its children. */

	public void dump(final String prefix) {
		System.out.println(toString(prefix));
		if (children != null) {
			for (int i = 0; i < children.length; ++i) {
				SimpleNode n = (SimpleNode) children[i];
				if (n != null) {
					n.dump(prefix + " ");
				}
			}
		}
	}

	public abstract ValueHolder evaluate(FormulaContext ctx) throws EvaluateException;

	protected void appendParams(final StringBuilder sb) {
		// TODO Auto-generated method stub

		if (children != null) {
			sb.append('(');
			for (int i = 0; i < children.length; ++i) {
				if (i > 0) {
					sb.append(';');
				}
				SimpleNode n = (SimpleNode) children[i];
				n.toFormula(sb);
			}
			sb.append(')');
		}
	}
}

/* JavaCC - OriginalChecksum=71a47937b2d85c71b1e18aa5cd3417bc (do not edit this line) */
