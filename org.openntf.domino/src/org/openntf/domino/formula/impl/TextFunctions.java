package org.openntf.domino.formula.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Pattern;

import org.openntf.domino.formula.FormulaContext;
import org.openntf.domino.formula.ValueHolder;
import org.openntf.domino.formula.ValueHolder.DataType;

import com.ibm.commons.util.NotImplementedException;

public enum TextFunctions {
	;
	/*----------------------------------------------------------------------------*/
	/*
	 * @Text
	 */
	/*----------------------------------------------------------------------------*/
	@ParamCount({ 1, 2 })
	public static String atText(final Object... args) {
		if (args.length != 1)
			throw new NotImplementedException();
		return args[0].toString();
	}

	/*----------------------------------------------------------------------------*/
	/*
	 * @Left, @LeftBack, @Right, @RightBack
	 */
	/*----------------------------------------------------------------------------*/
	private static Integer numOrStringTest(final Object what, final boolean mustBeNonNeg) {
		if (what instanceof Number) {
			int nWhat = ((Number) what).intValue();
			if (nWhat < 0 && mustBeNonNeg)
				throw new IllegalArgumentException("Got negative Number: " + what.toString());
			return (nWhat);
		}
		if (what instanceof String)
			return (null);
		throw new IllegalArgumentException("Expected Number or String, got " + what.getClass());
	}

	/*----------------------------------------------------------------------------*/
	private static String leftRight(final String whose, final Object what, final boolean left, final boolean back) {
		int lh = whose.length();
		int howMany;
		Integer i = numOrStringTest(what, true);
		if (i != null) {
			if ((howMany = i) > lh)
				howMany = lh;
			if (back)
				howMany = lh - howMany;
		} else {
			String sWhat = (String) what;
			if (back)
				howMany = whose.lastIndexOf(sWhat);
			else
				howMany = whose.indexOf(sWhat);
			if (howMany < 0)
				return "";
			if (!left)
				howMany = lh - howMany - sWhat.length();
		}
		return left ? whose.substring(0, howMany) : whose.substring(lh - howMany);
	}

	/*----------------------------------------------------------------------------*/
	@ParamCount(2)
	public static String atLeft(final String whose, final Object what) {
		return leftRight(whose, what, true, false);
	}

	/*----------------------------------------------------------------------------*/
	@ParamCount(2)
	public static String atLeftBack(final String whose, final Object what) {
		return leftRight(whose, what, true, true);
	}

	/*----------------------------------------------------------------------------*/
	@ParamCount(2)
	public static String atRight(final String whose, final Object what) {
		return leftRight(whose, what, false, false);
	}

	/*----------------------------------------------------------------------------*/
	@ParamCount(2)
	public static String atRightBack(final String whose, final Object what) {
		return leftRight(whose, what, false, true);
	}

	/*----------------------------------------------------------------------------*/
	/*
	 * @Middle, @MiddleBack
	 */
	/*----------------------------------------------------------------------------*/
	private static String middle(final String whose, final Object what1, final Object what2, final boolean back) {
		Integer num = numOrStringTest(what1, true);
		int offset = (num == null) ? -1 : num;
		int extraOffset1 = 0;
		int lh = whose.length();
		if (offset >= 0) {
			if (back) {
				offset = lh - offset;
				if (offset < 0)
					offset = 0;
				else if (offset < lh)
					offset++;
			} else {
				if (offset > lh)
					offset = lh;
			}
		} else {
			if (back)
				offset = whose.lastIndexOf((String) what1);
			else
				offset = whose.indexOf((String) what1);
			if (offset < 0)
				return "";
			extraOffset1 = ((String) what1).length();
		}
		int[] v = new int[2];
		v[0] = offset;
		v[1] = extraOffset1;
		num = numOrStringTest(what2, false);
		if (num != null)
			calcMiddle(whose, num, back, v);
		else
			calcMiddle(whose, (String) what2, back, v);
		offset = v[0];
		int howMany = v[1];
		if (offset < 0 || howMany <= 0 || offset >= lh)
			return "";
		if (howMany > lh - offset)
			howMany = lh - offset;

		return whose.substring(offset, offset + howMany);
	}

	/*----------------------------------------------------------------------------*/
	private static void calcMiddle(final String whose, int nWhat2, final boolean back, final int[] v) {
		int offset = v[0];
		int extraOffset1 = v[1];
		if (nWhat2 < 0) {
			int oldOffset = offset;
			nWhat2 = -nWhat2;
			offset -= nWhat2;
			if (offset < 0) {
				offset = 0;
				nWhat2 = oldOffset;
			}
		} else
			offset += extraOffset1;
		v[0] = offset;
		v[1] = nWhat2;
	}

	/*----------------------------------------------------------------------------*/
	private static void calcMiddle(final String whose, final String sWhat2, final boolean back, final int[] v) {
		int offset = v[0];
		int extraOffset1 = v[1];
		int i;
		int howMany;
		if (back)
			i = whose.substring(0, offset).lastIndexOf(sWhat2);
		else
			i = whose.substring(offset + extraOffset1).indexOf(sWhat2);
		int extra = 0;
		if (i < 0)
			i = back ? 0 : whose.length();
		else
			extra = sWhat2.length();
		if (back) {
			howMany = offset;
			offset = i + extra;
			howMany -= offset;
		} else {
			offset += extraOffset1;
			howMany = i;
		}
		v[0] = offset;
		v[1] = howMany;
	}

	/*----------------------------------------------------------------------------*/
	@ParamCount(3)
	public static String atMiddle(final String whose, final Object what1, final Object what2) {
		return middle(whose, what1, what2, false);
	}

	/*----------------------------------------------------------------------------*/
	@ParamCount(3)
	public static String atMiddleBack(final String whose, final Object what1, final Object what2) {
		return middle(whose, what1, what2, true);
	}

	/*----------------------------------------------------------------------------*/
	/*
	 * @Begins, @Ends, @Contains
	 */
	/*----------------------------------------------------------------------------*/
	private static ValueHolder begEndCont(final FormulaContext ctx, final ValueHolder[] params, final char fkt) {

		ValueHolder vh1 = params[0];
		ValueHolder vh2 = params[1];
		for (int i1 = 0; i1 < vh1.size; i1++) {
			String what = vh1.getString(i1);
			for (int i2 = 0; i2 < vh2.size; i2++) {
				String how = vh2.getString(i2);
				if ((fkt == 'b' && what.startsWith(how))		// @Begins
						|| (fkt == 'e' && what.endsWith(how))	// @Ends
						|| (fkt == 'c' && what.contains(how))) { // @Contains
					return ctx.TRUE;
				}
			}
		}
		return ctx.FALSE;
	}

	/*----------------------------------------------------------------------------*/
	@ParamCount(2)
	public static ValueHolder atBegins(final FormulaContext ctx, final ValueHolder[] params) {
		return begEndCont(ctx, params, 'b');
	}

	/*----------------------------------------------------------------------------*/
	@ParamCount(2)
	public static ValueHolder atEnds(final FormulaContext ctx, final ValueHolder[] params) {
		return begEndCont(ctx, params, 'e');
	}

	/*----------------------------------------------------------------------------*/
	@ParamCount(2)
	public static ValueHolder atContains(final FormulaContext ctx, final ValueHolder[] params) {
		return begEndCont(ctx, params, 'c');
	}

	/*----------------------------------------------------------------------------*/
	/*
	 * @LowerCase, @UpperCase, @ProperCase
	 */
	/*----------------------------------------------------------------------------*/
	@ParamCount(1)
	public static String atLowerCase(final String what) {
		return what.toLowerCase();
	}

	/*----------------------------------------------------------------------------*/
	@ParamCount(1)
	public static String atUpperCase(final String what) {
		return what.toUpperCase();
	}

	/*----------------------------------------------------------------------------*/
	@ParamCount(1)
	public static String atProperCase(final String what) {
		int lh = what.length();
		if (lh == 0)
			return what;
		char[] cArr = new char[lh];
		boolean lastWasLetter = false;
		for (int i = 0; i < lh; i++) {
			char c = what.charAt(i);
			if (Character.isLetter(c)) {
				c = lastWasLetter ? Character.toLowerCase(c) : Character.toUpperCase(c);
				lastWasLetter = true;
			} else
				lastWasLetter = false;
			cArr[i] = c;
		}
		return new String(cArr);
	}

	/*----------------------------------------------------------------------------*/
	/*
	 * @Ascii (?), @Char, @NewLine
	 */
	/*----------------------------------------------------------------------------*/
	@ParamCount(1)
	public static String atChar(final Number n) {
		int what = n.intValue();
		if (what < 0 || what > 255)
			throw new IllegalArgumentException("Expected Number between 0 and 255, got " + n.toString());
		char[] cArr = new char[1];
		cArr[0] = (char) what;
		return new String(cArr);
	}

	@ParamCount(0)
	public static String atNewLine() {
		return atChar(10);
	}

	/*----------------------------------------------------------------------------*/
	/*
	 * @Count, @Elements, @Length, @Trim
	 */
	/*----------------------------------------------------------------------------*/
	@ParamCount(1)
	public static ValueHolder atCount(final ValueHolder[] params) {
		return ValueHolder.valueOf(params[0].size);
	}

	/*----------------------------------------------------------------------------*/
	@ParamCount(1)
	public static ValueHolder atElements(final ValueHolder[] params) {
		int res = params[0].size;
		if (res == 1 && params[0].dataType == DataType.STRING && params[0].getString(0).isEmpty())
			res = 0;
		return ValueHolder.valueOf(res);
	}

	/*----------------------------------------------------------------------------*/
	@ParamCount(1)
	public static int atLength(final String whose) {
		return whose.length();
	}

	/*----------------------------------------------------------------------------*/
	/*
	 * Difference: Lotus doesn't trim tabs.
	 */
	@ParamCount(1)
	public static String atTrim(final String whom) {
		return whom.trim();
	}

	/*----------------------------------------------------------------------------*/
	/*
	 * @Explode, @Implode
	 */
	/*----------------------------------------------------------------------------*/
	@ParamCount({ 1, 2 })
	public static ValueHolder atImplode(final ValueHolder[] params) {
		String sep = (params.length == 1) ? " " : params[1].getString(0);
		ValueHolder vh = params[0];
		int bedarf = (vh.size - 1) * sep.length();
		for (int i = 0; i < vh.size; i++)
			bedarf += vh.getString(i).length();
		StringBuffer sb = new StringBuffer(bedarf);
		boolean first = true;
		for (int i = 0; i < vh.size; i++) {
			if (first)
				first = false;
			else
				sb.append(sep);
			sb.append(vh.getString(i));
		}
		return ValueHolder.valueOf(sb.toString());
	}

	/*----------------------------------------------------------------------------*/
	/*
	 * @IsNull, @IsNumber, @IsText, @IsTime
	 */
	/*----------------------------------------------------------------------------*/
	@ParamCount(1)
	public static ValueHolder atIsNull(final FormulaContext ctx, final ValueHolder[] params) {
		Integer i = atElements(params).getInt(0);
		return (i == 0) ? ctx.TRUE : ctx.FALSE;
	}

	/*----------------------------------------------------------------------------*/
	@ParamCount(1)
	public static ValueHolder atIsNumber(final FormulaContext ctx, final ValueHolder[] params) {
		return params[0].dataType.numeric ? ctx.TRUE : ctx.FALSE;
	}

	/*----------------------------------------------------------------------------*/
	@ParamCount(1)
	public static ValueHolder atIsText(final FormulaContext ctx, final ValueHolder[] params) {
		return (params[0].dataType == DataType.STRING) ? ctx.TRUE : ctx.FALSE;
	}

	/*----------------------------------------------------------------------------*/
	@ParamCount(1)
	public static ValueHolder atIsTime(final FormulaContext ctx, final ValueHolder[] params) {
		return (params[0].dataType == DataType.DATETIME) ? ctx.TRUE : ctx.FALSE;
	}

	/*----------------------------------------------------------------------------*/
	@ParamCount(1)
	public static ValueHolder atIsError(final FormulaContext ctx, final ValueHolder[] params) {
		return (params[0].dataType == DataType.ERROR) ? ctx.TRUE : ctx.FALSE;
	}

	/*----------------------------------------------------------------------------*/
	/*
	 * @Keywords
	 */
	/*----------------------------------------------------------------------------*/
	@ParamCount({ 2, 3 })
	public static ValueHolder atKeywords(final ValueHolder[] params) {
		boolean explicitSeps = (params.length == 3);
		String seps = explicitSeps ? params[2].getString(0) : "?. \t\r\n,!;:[](){}\"<>";
		Vector<String> res = new Vector<String>(params[1].size + 1);
		if (seps.isEmpty())
			keywordsNoSeps(res, params[0], params[1]);
		else
			keywordsWithSeps(res, seps, explicitSeps, params[0], params[1]);
		int sz = res.size();
		ValueHolder ret = ValueHolder.createValueHolder(String.class, sz);
		for (int i = 0; i < sz; i++)
			ret.add(res.get(i));
		return ret;
	}

	/*----------------------------------------------------------------------------*/
	private static void keywordsNoSeps(final Vector<String> res, final ValueHolder testStrs, final ValueHolder keywords) {
		for (int i = 0; i < keywords.size; i++) {
			String keyword = keywords.getString(i);
			for (int j = 0; j < testStrs.size; j++) {
				if (testStrs.getString(j).indexOf(keyword) >= 0) {
					res.add(keyword);
					break;
				}
			}
		}
	}

	/*----------------------------------------------------------------------------*/
	private static void keywordsWithSeps(final Vector<String> res, final String seps, final boolean explicitSeps,
			final ValueHolder testStrs, final ValueHolder keywords) {
		int bedarf = testStrs.size + 1;
		for (int i = 0; i < testStrs.size; i++)
			bedarf += testStrs.getString(i).length();
		char[] buffer = new char[bedarf + 10];
		int count = 0;
		for (int i = 0; i < testStrs.size; i++) {
			String ts = testStrs.getString(i);
			int lh = ts.length();
			boolean putIt = !explicitSeps;
			boolean lastWasSep = true;
			for (int j = 0; j < lh; j++) {
				char c = ts.charAt(j);
				if (seps.indexOf(c) >= 0) {
					if (!lastWasSep)
						buffer[count++] = (char) 0;
					lastWasSep = true;
					putIt = true;
					continue;
				}
				if (putIt) {
					buffer[count++] = c;
					lastWasSep = false;
				}
			}
			if (!lastWasSep)
				buffer[count++] = (char) 0;
		}
		String testStr = new String(buffer, 0, count);
		for (int i = 0; i < keywords.size; i++) {
			String keyword = keywords.getString(i);
			if (testStr.indexOf(keyword) >= 0)
				res.add(keyword);
		}
	}

	/*----------------------------------------------------------------------------*/
	/*
	 * @Like (?), @Matches (?), @RegExpMatches
	 */
	/*----------------------------------------------------------------------------*/
	@OpenNTF
	@ParamCount(2)
	public static ValueHolder atRegExpMatches(final FormulaContext ctx, final ValueHolder[] params) {
		ValueHolder vhTester = params[0];
		ValueHolder vhPatterns = params[1];
		for (int ip = 0; ip < vhPatterns.size; ip++) {
			Pattern regPatt = Pattern.compile(vhPatterns.getString(ip));
			for (int it = 0; it < vhTester.size; it++)
				if (regPatt.matcher(vhTester.getString(it)).matches())
					return ctx.TRUE;
		}
		return ctx.FALSE;
	}

	/*----------------------------------------------------------------------------*/
	/*
	 * @Replace, @ReplaceSubstring
	 * 
	 * These functions operate a bit differently: E.g.
	 *		@Replace("a":"b"; "a":"b"; "A") --> "A, " whereas
	 *		@ReplaceSubstring("a":"b"; "a":"b"; "A") --> "A, A"
	 */
	/*----------------------------------------------------------------------------*/
	@ParamCount(3)
	public static ValueHolder atReplace(final ValueHolder[] params) {
		Map<String, String> replacer = getReplaceMap(params[1], params[2]);
		ValueHolder vh = params[0];
		ValueHolder ret = ValueHolder.createValueHolder(String.class, vh.size);
		for (int i = 0; i < vh.size; i++) {
			String s = vh.getString(i);
			String t = replacer.get(s);
			ret.add(t == null ? s : t);
		}
		return ret;
	}

	/*----------------------------------------------------------------------------*/
	private static Map<String, String> getReplaceMap(final ValueHolder froms, final ValueHolder tos) {
		Map<String, String> ret = new HashMap<String, String>();
		for (int i = 0; i < froms.size; i++) {
			String from = froms.getString(i);
			String to = (i >= tos.size) ? "" : tos.getString(i);
			if (!ret.containsKey(from))
				ret.put(from, to);
		}
		return ret;
	}

	/*----------------------------------------------------------------------------*/
	@ParamCount(3)
	public static ValueHolder atReplaceSubstring(final ValueHolder[] params) {
		ValueHolder tests = params[0];
		ValueHolder ret = ValueHolder.createValueHolder(String.class, tests.size);
		for (int i = 0; i < tests.size; i++)
			ret.add(replaceString(tests.getString(i), params[1], params[2]));
		return ret;
	}

	/*----------------------------------------------------------------------------*/
	private static String replaceString(String what, final ValueHolder froms, final ValueHolder tos) {
		String to = tos.getString(0);
		for (int i = 0; i < froms.size; i++) {
			if (i != 0 && i < tos.size)
				to = tos.getString(i);
			what = what.replace(froms.getString(i), to);
		}
		return what;
	}

	/*----------------------------------------------------------------------------*/
	/*
	 * @Select, @Subset
	 * 
	 * Concerning @Select, again, Lotus behaves a bit funny:
	 * @Select(4) ---> 4  @Select(0) ---> ERROR  @Select(-1) ---> ERROR
	 * @Select(n;1;2;4) ---> 1 for every n<=1 e.g. @Select(-11;1;2;4) ---> 1
	 */
	/*----------------------------------------------------------------------------*/
	@ParamCount({ 2, 99 })
	public static ValueHolder atSelect(final ValueHolder[] params) {
		int which = params[0].getInt(0);
		if (which <= 1)
			which = 1;
		else if (which >= params.length)
			which = params.length - 1;
		return params[which];
	}

	/*----------------------------------------------------------------------------*/
	@SuppressWarnings("deprecation")
	@ParamCount(2)
	public static ValueHolder atSubset(final ValueHolder[] params) {
		int count = params[1].getInt(0);
		if (count == 0)
			throw new IllegalArgumentException("Second argument to @Subset is 0");
		int first;
		int last;
		ValueHolder vh = params[0];
		if (count > 0) {
			first = 0;
			last = (count > vh.size) ? vh.size : count;
		} else {
			count = -count;
			last = vh.size;
			first = (count > last) ? 0 : last - count;
		}
		ValueHolder ret = vh.newInstance(last - first);
		/*
		 * To avoid boxing/unboxing of primitives:
		 */
		switch (vh.dataType) {
		case BOOLEAN:
			for (int i = first; i < last; i++)
				ret.add(vh.getBoolean(i));
			break;
		case DOUBLE:
			for (int i = first; i < last; i++)
				ret.add(vh.getDouble(i));
			break;

		case INTEGER:
			for (int i = first; i < last; i++)
				ret.add(vh.getInt(i));
			break;
		default:
			for (int i = first; i < last; i++)
				ret.add(vh.getObject(i));
		}
		return ret;
	}

	/*----------------------------------------------------------------------------*/
	/*
	 * @Unique
	 */
	/*----------------------------------------------------------------------------*/
	@ParamCount(1)
	public static ValueHolder atUnique(final ValueHolder[] params) {
		ValueHolder vh = params[0];
		ValueHolder ret = ValueHolder.createValueHolder(String.class, vh.size);
		for (int i = 0; i < vh.size; i++) {
			String s = vh.getString(i);
			int j;
			for (j = 0; j < ret.size; j++)
				if (ret.getString(j).equals(s))
					break;
			if (j == ret.size)
				ret.add(s);
		}
		return ret;
	}

	/*----------------------------------------------------------------------------*/
	/*
	 * @Compare
	 */
	/*----------------------------------------------------------------------------*/
	@ParamCount({ 2, 3 })
	public static ValueHolder atCompare(final ValueHolder[] params) {

		if (params.length == 3) {
			for (int i = 0; i < params[2].size; i++)
				System.out.println("Option " + i + ": " + params[2].getString(i));
		}

		return (ValueHolder.valueOf(0));
	}
	/*----------------------------------------------------------------------------*/
}
