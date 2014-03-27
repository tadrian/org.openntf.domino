/* Generated By:JJTree&JavaCC: Do not edit this line. AtFormulaParserImplConstants.java */
/* *  © Copyright FOCONIS AG, 2014 *  * Licensed under the Apache License, Version 2.0 (the "License");  * you may not use this file except in compliance with the License.  * You may obtain a copy of the License at: *  * http://www.apache.org/licenses/LICENSE-2.0 *  * Unless required by applicable law or agreed to in writing, software  * distributed under the License is distributed on an "AS IS" BASIS,  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or  * implied. See the License for the specific language governing  * permissions and limitations under the License. */
package org.openntf.domino.formula;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface AtFormulaParserImplConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int SINGLE_LINE_COMMENT = 6;
  /** RegularExpression Id. */
  int FORMAL_COMMENT = 7;
  /** RegularExpression Id. */
  int MULTI_LINE_COMMENT = 8;
  /** RegularExpression Id. */
  int NORMAL_TEXT = 9;
  /** RegularExpression Id. */
  int BEGIN_FORMULA = 10;
  /** RegularExpression Id. */
  int BEGIN_PSEUDONYM = 11;
  /** RegularExpression Id. */
  int Date_or_KW = 12;
  /** RegularExpression Id. */
  int OSubscript = 13;
  /** RegularExpression Id. */
  int CSubscript = 14;
  /** RegularExpression Id. */
  int EOS = 15;
  /** RegularExpression Id. */
  int OParen = 16;
  /** RegularExpression Id. */
  int CParen = 17;
  /** RegularExpression Id. */
  int Assign = 18;
  /** RegularExpression Id. */
  int BoolNot = 19;
  /** RegularExpression Id. */
  int KW_DEFAULT = 20;
  /** RegularExpression Id. */
  int KW_ENVIRONMENT = 21;
  /** RegularExpression Id. */
  int KW_FIELD = 22;
  /** RegularExpression Id. */
  int KW_REM = 23;
  /** RegularExpression Id. */
  int KW_SELECT = 24;
  /** RegularExpression Id. */
  int At_Include = 25;
  /** RegularExpression Id. */
  int At_Function = 26;
  /** RegularExpression Id. */
  int At_do = 27;
  /** RegularExpression Id. */
  int At_doWhile = 28;
  /** RegularExpression Id. */
  int At_for = 29;
  /** RegularExpression Id. */
  int At_if = 30;
  /** RegularExpression Id. */
  int At_v2if = 31;
  /** RegularExpression Id. */
  int At_iferror = 32;
  /** RegularExpression Id. */
  int At_transform = 33;
  /** RegularExpression Id. */
  int At_sort = 34;
  /** RegularExpression Id. */
  int At_text = 35;
  /** RegularExpression Id. */
  int At_eval = 36;
  /** RegularExpression Id. */
  int At_foceval = 37;
  /** RegularExpression Id. */
  int At_return = 38;
  /** RegularExpression Id. */
  int At_iserror = 39;
  /** RegularExpression Id. */
  int At_other = 40;
  /** RegularExpression Id. */
  int ListConcat = 41;
  /** RegularExpression Id. */
  int OpMul = 42;
  /** RegularExpression Id. */
  int OpMulP = 43;
  /** RegularExpression Id. */
  int OpDiv = 44;
  /** RegularExpression Id. */
  int OpDivP = 45;
  /** RegularExpression Id. */
  int OpPlus = 46;
  /** RegularExpression Id. */
  int OpPlusP = 47;
  /** RegularExpression Id. */
  int OpMinus = 48;
  /** RegularExpression Id. */
  int OpMinusP = 49;
  /** RegularExpression Id. */
  int CmpEqual = 50;
  /** RegularExpression Id. */
  int CmpEqualP = 51;
  /** RegularExpression Id. */
  int CmpNE = 52;
  /** RegularExpression Id. */
  int CmpNEP = 53;
  /** RegularExpression Id. */
  int CmpLT = 54;
  /** RegularExpression Id. */
  int CmpLTP = 55;
  /** RegularExpression Id. */
  int CmpGT = 56;
  /** RegularExpression Id. */
  int CmpGTP = 57;
  /** RegularExpression Id. */
  int CmpLTE = 58;
  /** RegularExpression Id. */
  int CmpLTEP = 59;
  /** RegularExpression Id. */
  int CmpGTE = 60;
  /** RegularExpression Id. */
  int CmpGTEP = 61;
  /** RegularExpression Id. */
  int BoolAnd = 62;
  /** RegularExpression Id. */
  int BoolOr = 63;
  /** RegularExpression Id. */
  int END_FORMULA = 64;
  /** RegularExpression Id. */
  int NumDoubleUS = 65;
  /** RegularExpression Id. */
  int NumDoubleDE = 66;
  /** RegularExpression Id. */
  int NumInteger = 67;
  /** RegularExpression Id. */
  int Exponent = 68;
  /** RegularExpression Id. */
  int String1 = 69;
  /** RegularExpression Id. */
  int String2 = 70;
  /** RegularExpression Id. */
  int Identifier = 71;
  /** RegularExpression Id. */
  int Letter = 72;
  /** RegularExpression Id. */
  int Digit = 73;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int SUBSCRIPT = 1;
  /** Lexical state. */
  int INLINE = 2;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\r\\n\"",
    "<SINGLE_LINE_COMMENT>",
    "<FORMAL_COMMENT>",
    "<MULTI_LINE_COMMENT>",
    "<NORMAL_TEXT>",
    "\"<!\"",
    "\"<#\"",
    "<Date_or_KW>",
    "\"[\"",
    "\"]\"",
    "\";\"",
    "\"(\"",
    "\")\"",
    "\":=\"",
    "\"!\"",
    "\"DEFAULT\"",
    "\"ENVIRONMENT\"",
    "\"FIELD\"",
    "\"REM\"",
    "\"SELECT\"",
    "\"@Include\"",
    "\"@Function\"",
    "\"@do\"",
    "\"@doWhile\"",
    "\"@for\"",
    "\"@if\"",
    "\"@V2if\"",
    "\"@iferror\"",
    "\"@transform\"",
    "\"@sort\"",
    "\"@text\"",
    "\"@eval\"",
    "\"@foceval\"",
    "\"@return\"",
    "\"@iserror\"",
    "<At_other>",
    "\":\"",
    "\"*\"",
    "\"**\"",
    "\"/\"",
    "\"*/\"",
    "\"+\"",
    "\"*+\"",
    "\"-\"",
    "\"*-\"",
    "\"=\"",
    "\"*=\"",
    "<CmpNE>",
    "\"*<>\"",
    "\"<\"",
    "\"*<\"",
    "\">\"",
    "\"*>\"",
    "\"<=\"",
    "\"*<=\"",
    "\">=\"",
    "\"*>=\"",
    "\"&\"",
    "\"|\"",
    "\"!>\"",
    "<NumDoubleUS>",
    "<NumDoubleDE>",
    "<NumInteger>",
    "<Exponent>",
    "<String1>",
    "<String2>",
    "<Identifier>",
    "<Letter>",
    "<Digit>",
  };

}
