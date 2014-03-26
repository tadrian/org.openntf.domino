/* Generated By:JJTree: Do not edit this line. ASTValueInt.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
/*
 * � Copyright FOCONIS AG, 2014
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License.
 */
package org.openntf.domino.formula.ast;

import java.util.Set;

import org.openntf.domino.formula.AtFormulaParserImpl;
import org.openntf.domino.formula.FormulaContext;
import org.openntf.domino.formula.ValueHolder;

public class ASTValueInt extends SimpleNode {
	private ValueHolder value;

	public ASTValueInt(final int id) {
		super(id);
	}

	public ASTValueInt(final AtFormulaParserImpl p, final int id) {
		super(p, id);
	}

	public void parseInt(final String image) {
		try {
			int i = Integer.valueOf(image);
			value = ValueHolder.valueOf(i);
		} catch (NumberFormatException e) {
			double d = Integer.valueOf(image);
			value = ValueHolder.valueOf(d);
		}
	}

	@Override
	public String toString() {
		return super.toString() + ": " + value;
	}

	@Override
	public void toFormula(final StringBuilder sb) {
		sb.append(value);
	}

	@Override
	public ValueHolder evaluate(final FormulaContext ctx) {
		return value;
	}

	@Override
	protected void analyzeThis(final Set<String> readFields, final Set<String> modifiedFields, final Set<String> variables,
			final Set<String> functions) {
		// TODO Auto-generated method stub

	}
}
/* JavaCC - OriginalChecksum=19609d291368534191c28631651790db (do not edit this line) */
