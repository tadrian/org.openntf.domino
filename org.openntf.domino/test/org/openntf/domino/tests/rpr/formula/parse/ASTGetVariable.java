/* Generated By:JJTree: Do not edit this line. ASTGetVariable.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.openntf.domino.tests.rpr.formula.parse;

import org.openntf.domino.tests.rpr.formula.eval.FormulaContext;
import org.openntf.domino.tests.rpr.formula.eval.Value;

public class ASTGetVariable extends SimpleNode {
	private String variableName = "";

	public ASTGetVariable(final int id) {
		super(id);
	}

	public ASTGetVariable(final AtFormulaParser p, final int id) {
		super(p, id);
	}

	public void init(final String image) {
		variableName = image;
	}

	@Override
	public String toString() {
		return super.toString() + ": " + variableName;
	}

	@Override
	public Value evaluate(final FormulaContext ctx) {
		return ctx.getVar(variableName);
	}
}
/* JavaCC - OriginalChecksum=6fc7ffc46fcd95aa653ad69d15d081cc (do not edit this line) */
