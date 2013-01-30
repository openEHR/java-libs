grammar ArchPath;

options {
	language=Java;
	k=2;
}

@header {
	package br.com.zilics.archetypes.models.rm.utils.path;
	import br.com.zilics.archetypes.models.rm.utils.path.parsed.*;
	import java.util.List;
	import java.util.ArrayList;
	import java.util.Map;
	import java.util.LinkedHashMap;
	import java.util.Stack;
}

@lexer::header {
	package br.com.zilics.archetypes.models.rm.utils.path;
}

@rulecatch {
	catch (RecognitionException e) {
	    throw e;
	}
}


archPath returns [TreeNode result]
	:	expr  { $archPath.result = $expr.result; }
	;

expr returns [TreeNode result]
@init {
	List<TreeNode> expressions = new ArrayList<TreeNode>();
}
	:	first=exprSingle { expressions.add($first.result); } (',' other=exprSingle  { expressions.add($other.result); } ) * { $expr.result = new Merge(expressions); }
	;

exprSingle returns [TreeNode result]
	:	forExpr             { $exprSingle.result = $forExpr.result;        }
	|	quantifiedExpr      { $exprSingle.result = $quantifiedExpr.result; }
	|	ifExpr              { $exprSingle.result = $ifExpr.result;         }
	|	orExpr              { $exprSingle.result = $orExpr.result;         }
	;


quantifiedExpr returns [TreeNode result]
@init {
	Map<String, TreeNode> vars = new LinkedHashMap<String, TreeNode>();
}
	:	op=keySomeOrEvery '$' firstVar=varName keyIn first=exprSingle { vars.put($firstVar.text, $first.result); } (',' '$' otherVar=varName keyIn other=exprSingle { vars.put($otherVar.text, $other.result); }) * keySatisfies node=exprSingle {
		if ("some".equals(op)) {
		  	$quantifiedExpr.result = new Some(vars, $node.result);
		} else {
		  	$quantifiedExpr.result = new Every(vars, $node.result);
		}	
	}
	;
forExpr returns [TreeNode result]
@init {
	Map<String, TreeNode> vars = new LinkedHashMap<String, TreeNode>();
}
	:	keyFor '$' firstVar=varName keyIn first=exprSingle { vars.put($firstVar.text, $first.result); } (',' '$' otherVar=varName keyIn other=exprSingle { vars.put($otherVar.text, $other.result); } ) * keyReturn node=exprSingle { $forExpr.result = new For(vars, $node.result); } 
	;
	
	
ifExpr returns [TreeNode result]
	:	keyIf '(' expr ')' keyThen ifTrue=exprSingle keyElse ifFalse=exprSingle {
		$ifExpr.result = new If($expr.result, $ifTrue.result, $ifFalse.result);
	}
	;


orExpr returns [TreeNode result]
	:	left=andExpr { $orExpr.result = $left.result; } ( keyOr right=andExpr { $orExpr.result = new Or($orExpr.result, $right.result); }) *
	;

andExpr returns [TreeNode result]
	:	left=comparisonExpr { $andExpr.result = $left.result; } ( keyAnd right=comparisonExpr { $andExpr.result = new And($andExpr.result, $right.result); }) *
	;

comparisonExpr returns [TreeNode result]
	:	left=rangeExpr { $comparisonExpr.result = $left.result; } ( op=CompareOperator right=rangeExpr {
		if ("=".equals($op.text)) {
		  	$comparisonExpr.result = new Equal($comparisonExpr.result, $right.result);
		} else if ("!=".equals($op.text)) {
		  	$comparisonExpr.result = new NotEqual($comparisonExpr.result, $right.result);
		} else if ("<".equals($op.text)) {
		  	$comparisonExpr.result = new LessThan($comparisonExpr.result, $right.result);
		} else if (">".equals($op.text)) {
		  	$comparisonExpr.result = new GreaterThan($comparisonExpr.result, $right.result);
		} else if ("<=".equals($op.text)) {
		  	$comparisonExpr.result = new LessThanOrEqual($comparisonExpr.result, $right.result);
		} else {
		  	$comparisonExpr.result = new GreaterThanOrEqual($comparisonExpr.result, $right.result);
		}
	}) ?
	;

rangeExpr returns [TreeNode result]
	:	left=additiveExpr { $rangeExpr.result = $left.result; } ( keyTo right=additiveExpr {
		$rangeExpr.result = new Range($rangeExpr.result, $right.result);
	}) ?
	;


additiveExpr returns [TreeNode result]
	:	left=multiplicativeExpr { $additiveExpr.result = $left.result; } ( op=('+' | '-') right=multiplicativeExpr {
		if ("+".equals(op.getText())) {
		  	$additiveExpr.result = new Add($additiveExpr.result, $right.result);
		} else {
		  	$additiveExpr.result = new Subtract($additiveExpr.result, $right.result);
		}
	}) *
	;

multiplicativeExpr returns [TreeNode result]
	:	left=unionExpr { $multiplicativeExpr.result = $left.result; } (op=keyMultiplicativeOperator right=unionExpr {
		if ("*".equals(op)) {
		  	$multiplicativeExpr.result = new Multiply($multiplicativeExpr.result, $right.result);
		} else if ("mod".equals(op)) {
		  	$multiplicativeExpr.result = new Remainder($multiplicativeExpr.result, $right.result);
		} else {
		  	$multiplicativeExpr.result = new Divide($multiplicativeExpr.result, $right.result);
		}
	}) *
	;

unionExpr returns [TreeNode result]
	:	left=intersectExceptExpr { $unionExpr.result = $left.result; } ( (keyUnion | '|') right=intersectExceptExpr {
		$unionExpr.result = new Union($unionExpr.result, $right.result);
	}) * 
	;

intersectExceptExpr returns [TreeNode result]
	:	left=instanceofExpr { $intersectExceptExpr.result = $left.result; } ( op=keyIntersectOrExcept right=instanceofExpr {
		if ("intersect".equals(op)) {
		  	$intersectExceptExpr.result = new Intersect($intersectExceptExpr.result, $right.result);
		} else {
		 	$intersectExceptExpr.result = new Except($intersectExceptExpr.result, $right.result);
		}
	}) *
	;

instanceofExpr returns [TreeNode result]
	:	unaryExpr { $instanceofExpr.result = $unaryExpr.result; } ( keyInstance keyOf id=Identifier {
		$instanceofExpr.result = new Instanceof($instanceofExpr.result, $id.text);
	}) ?
	;
	
unaryExpr returns [TreeNode result]
	:	op=('-' | '+') valueExpr {
		if ("+".equals(op.getText())) {
		    $unaryExpr.result = new UnaryPlus($valueExpr.result);
		} else {
		    $unaryExpr.result = new UnaryMinus($valueExpr.result);
		}
	}
	|	valueExpr  { $unaryExpr.result = $valueExpr.result; }
	;

valueExpr returns [TreeNode result]
	:	pathExpr  { $valueExpr.result = $pathExpr.result; }
	;
	

pathExpr returns [TreeNode result]
	:	('/' relativePathExpr)=> '/' relativePathExpr              { $pathExpr.result = new PathFollow(new Root(), $relativePathExpr.result); }
	|	'//' relativePathExpr                                      { $pathExpr.result = new PathFollow(new Root(), new PathFollow(new DescendantOrSelf("*"), $relativePathExpr.result)); }
	|	relativePathExpr                                           { $pathExpr.result = $relativePathExpr.result; }
	|       '/'                                                        { $pathExpr.result = new Root(); }
	;

relativePathExpr returns [TreeNode result]
@init {
	Stack<TreeNode> steps = new Stack<TreeNode>();
}
	:	left=stepExpr { steps.push($left.result); } (op=('/' | '//') right=stepExpr {
		if ("//".equals(op.getText())) {
			steps.push(new DescendantOrSelf("*"));
		}
		steps.push($right.result);
	}) * {
		$relativePathExpr.result = steps.pop();
		while(steps.size() > 0) {
			$relativePathExpr.result = new PathFollow(steps.pop(), $relativePathExpr.result);
		}
	}
	;


stepExpr returns [TreeNode result]
	:	filterExpr   { $stepExpr.result = $filterExpr.result; }
	|	axisStep     { $stepExpr.result = $axisStep.result;   }
	;

axisStep returns [TreeNode result]
	:	(reverseStep { $axisStep.result = $reverseStep.result; } | forwardStep { $axisStep.result = $forwardStep.result; }) predicateList[$axisStep.result] { $axisStep.result = $predicateList.result; }
	;

forwardStep returns [TreeNode result]
	:	forwardAxis '::' valueTest {
		if ("child".equals($forwardAxis.text)) {
			$forwardStep.result = new Child($valueTest.text);
		} else if ("descendant".equals($forwardAxis.text)) {
			$forwardStep.result = new Descendant($valueTest.text);
		} else if ("self".equals($forwardAxis.text)) {
			$forwardStep.result = new Self($valueTest.text);
		} else if ("descendant-or-self".equals($forwardAxis.text)) {
			$forwardStep.result = new DescendantOrSelf($valueTest.text);
		} else {
			$forwardStep.result = new Metadata($valueTest.text);
		}
	}
	|	abbrevForwardStep  { $forwardStep.result = $abbrevForwardStep.result; }
	;


abbrevForwardStep returns [TreeNode result]
@init {
	boolean isMetadata = false;
}
	:	('@' { isMetadata = true; } )? valueTest { 
		if (isMetadata)
			$abbrevForwardStep.result = new Metadata($valueTest.text);
		else
			$abbrevForwardStep.result = new Child($valueTest.text);
	}
	;
forwardAxis
	:	{input.LT(1).getText().equals("child")}? Identifier
	|	{input.LT(1).getText().equals("descendant")}? Identifier
	|	{input.LT(1).getText().equals("self")}? Identifier
	|       {input.LT(1).getText().equals("metadata")}? Identifier
	|	'descendant-or-self'
	;


reverseStep returns [TreeNode result]
	:	reverseAxis '::' valueTest {
		if ("parent".equals($reverseAxis.text)) {
			$reverseStep.result = new Parent($valueTest.text);
		} else if ("ancestor".equals($reverseAxis.text)) {
			$reverseStep.result = new Ancestor($valueTest.text);
		} else {
			$reverseStep.result = new AncestorOrSelf($valueTest.text);
		}
	}
	|	abbrevReverseStep  { $reverseStep.result = $abbrevReverseStep.result; }
	;


reverseAxis
	:	{input.LT(1).getText().equals("parent")}? Identifier
	|	{input.LT(1).getText().equals("ancestor")}? Identifier
	|	'ancestor-or-self'
	;

abbrevReverseStep returns [TreeNode result]
	:	'..' { $abbrevReverseStep.result = new Parent("*"); }
	;

valueTest
	:	Identifier
	|	'*'
	;

filterExpr returns [TreeNode result]
	:	primaryExpr predicateList[$primaryExpr.result] { $filterExpr.result = $predicateList.result; }
	;

predicateList[TreeNode node] returns [TreeNode result]
@init {
	$predicateList.result = $node;
}
	:	( predicate[$predicateList.result] { $predicateList.result = $predicate.result; } ) *
	;

predicate[TreeNode node] returns [TreeNode result]
	:	('[' ATCode ']')=> '[' ATCode ']' { $predicate.result = new ATCodePredicate($node, $ATCode.text); }
	|	'[' expr ']'                      { $predicate.result = new ExpressionPredicate($node, $expr.result); }
	;

primaryExpr returns [TreeNode result]
	:	literal             { $primaryExpr.result = $literal.result; }
	|	varRef              { $primaryExpr.result = $varRef.result; }
	|	parenthesizedExpr   { $primaryExpr.result = $parenthesizedExpr.result; }
	|	contextItemExpr     { $primaryExpr.result = $contextItemExpr.result; }
	|	functionCall        { $primaryExpr.result = $functionCall.result; }
	;

literal returns [TreeNode result]
	:	IntegerLiteral   { $literal.result = new Literal(Integer.parseInt($IntegerLiteral.text)); }
	|	DoubleLiteral    { $literal.result = new Literal(Double.parseDouble($DoubleLiteral.text)); }
	|	StringLiteral    { $literal.result = new Literal($StringLiteral.text.substring(1, $StringLiteral.text.length() - 1)); }
	;

varRef returns [TreeNode result]
	:	'$' varName      { $varRef.result = new VarRef($varName.text); }
	;

varName
	:	Identifier
	;

contextItemExpr returns [TreeNode result]
	:	'.' { $contextItemExpr.result = new Self("*"); }
	;
	
parenthesizedExpr returns [TreeNode result]
@init {
	$parenthesizedExpr.result = new EmptyList();
}
	:	'(' (expr { $parenthesizedExpr.result = $expr.result; })? ')' 
	;


functionCall returns [TreeNode result]
@init {
	List<TreeNode> operands = new ArrayList<TreeNode>();
}
	:	Identifier '(' (first=exprSingle { operands.add($first.result); } (',' other=exprSingle { operands.add($other.result); } )* )? ')' {
		$functionCall.result = new FunctionCall($Identifier.text, operands);
	}
	;
	

keySomeOrEvery returns [String result]
	:	{"some".equals(input.LT(1).getText()) || "every".equals(input.LT(1).getText())}? Identifier { $keySomeOrEvery.result = $Identifier.text; }
	;
		
keyIntersectOrExcept returns [String result]
	:	{"intersect".equals(input.LT(1).getText()) || "except".equals(input.LT(1).getText())}? Identifier { $keyIntersectOrExcept.result = $Identifier.text; }
	;

keyFor
	:	{input.LT(1).getText().equals("for")}? Identifier
	;

keyIf
	:	{input.LT(1).getText().equals("if")}? Identifier
	;

keyThen
	:	{input.LT(1).getText().equals("then")}? Identifier
	;

keyElse
	:	{input.LT(1).getText().equals("else")}? Identifier
	;

keyInstance
	:	{input.LT(1).getText().equals("instance")}? Identifier
	;

keyOf
	:	{input.LT(1).getText().equals("of")}? Identifier
	;

keyIn
	:	{input.LT(1).getText().equals("in")}? Identifier
	;

keyOr
	:	{input.LT(1).getText().equals("or")}? Identifier
	;

keyAnd
	:	{input.LT(1).getText().equals("and")}? Identifier
	;

keyTo
	:	{input.LT(1).getText().equals("to")}? Identifier
	;

keyUnion
	:	{input.LT(1).getText().equals("union")}? Identifier
	;

keySatisfies
	:	{input.LT(1).getText().equals("satisfies")}? Identifier
	;

keyReturn
	:	{input.LT(1).getText().equals("return")}? Identifier
	;

keyMultiplicativeOperator returns [String result]
	:	'*'  { $keyMultiplicativeOperator.result = "*"; }
	|	{"mod".equals(input.LT(1).getText()) || "div".equals(input.LT(1).getText())}? Identifier { $keyMultiplicativeOperator.result = $Identifier.text; }
	;

ATCode
	:	'at' Digits ('.' Digits) *
	;
		
Identifier
	:	(Letter|'_') (Letter|Digit|'_')*
	;

fragment
Letter
	:	('a'..'z')|('A'..'Z')
	;

fragment
Digit
	:	('0'..'9')
	;

IntegerLiteral
	:	Digits
	;

DoubleLiteral
	:	(('.' Digits ) | (Digits '.' Digits )) (('e'|'E') ('+'|'-')? Digits ) ?
	;

StringLiteral
	:	'"' ('""' | ~('"'))*  '"'
	;

fragment
Digits
	:	(Digit) +
	;


CompareOperator
	:	'=' | '!=' | '<' | '<=' | '>' | '>='
	;

WS
	 :	(' ' | '\r' | '\t' | '\u000C' | '\n') { $channel=HIDDEN; }
	 ;

