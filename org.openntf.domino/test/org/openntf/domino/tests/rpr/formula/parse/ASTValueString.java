/* Generated By:JJTree: Do not edit this line. ASTValueString.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.openntf.domino.tests.rpr.formula.parse;

import org.openntf.domino.tests.rpr.formula.eval.FormulaContext;
import org.openntf.domino.tests.rpr.formula.eval.ValueHolder;

public class ASTValueString extends SimpleNode {
	String value;

	public ASTValueString(final int id) {
		super(id);
	}

	public ASTValueString(final AtFormulaParser p, final int id) {
		super(p, id);
	}

	public void parseString(final String image, final char c) {
		if (c == '{') {
			value = image.substring(1, image.length() - 1);
		} else if (c == '"') {
			value = image.substring(1, image.length() - 1);
			int pos = 0;
			int start = 0;
			// YES: This looks complicated. But we want to be as much compatible as possible
			if ((pos = value.indexOf('\\')) != -1) {
				StringBuffer sb = new StringBuffer(value.substring(start, pos));
				while (true) {
					start = pos + 1;
					pos = value.indexOf('\\', start);
					if (pos == -1) {
						sb.append(value.substring(start));
						break;
					}
					sb.append(value.substring(start, pos));
				}
				value = sb.toString();
			}
		}
	}

	@Override
	public String toString() {
		return super.toString() + ": " + value;
	}

	@Override
	public void toFormula(final StringBuilder sb) {
		// TODO Quote Stings properly!
		String s = value.replace("\\", "\\\\");
		s = s.replace("\"", "\\\"");
		sb.append("\"" + s + "\"");
	}

	@Override
	public ValueHolder evaluate(final FormulaContext ctx) {
		return new ValueHolder(value);
	}

}
/* JavaCC - OriginalChecksum=b093634abe91991ac4fe1c236b97b93b (do not edit this line) */
