package org.siit;


import java.util.List;

import static java.util.Collections.replaceAll;
import static org.siit.BinaryOperator.*;
import static org.siit.BinaryOperator.MODULUS;

public class ExpressionEvaluator  {

	public static int evaluate(Expression expression) {


		List<Object> elements = expression.getElements();
		convertElements(elements);
		int result = (int) elements.get(0);

		for (int i=0; i<(elements.size()-1)/2; ++i) {

			result = evalBinary((BinaryOperator) elements.get(i*2+1), result, 	(int) elements.get(i*2+2));
		}
		return result;
	}
	
	public static Integer evalBinary(
			String operator, Integer left, Integer right) {
		switch (operator) {
		case "+":
			return left + right;
		case "-":
			return left - right;
		case "*":
			return left * right;
		case "/":
			return left / right;
		case "%":
			return left % right;
		default:
			throw new ValidationException(
					"Operator '" + operator + "' is not known");
		}
	}
	
	public static Integer evalBinary(
			BinaryOperator op, Integer left, Integer right) {
		switch (op) {
		case ADD:
			return left + right;
		case SUBTRACT:
			return left - right;
		case MULTIPLY:
			return left * right;
		case DIVIDE:
			return left / right;
		case MODULUS:
			return left % right;
		default:
			throw new ValidationException(
					"Operator '" + op + "' is not known");
		}
	}

	public static void convertElements(List<Object> elements){

		replaceAll(elements,"+",ADD);
		replaceAll(elements,"-",SUBTRACT);
		replaceAll(elements,"*",MULTIPLY);
		replaceAll(elements,"/",DIVIDE);
		replaceAll(elements,"%",MODULUS);
	}
	
}
