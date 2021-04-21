package calculator;

import java.util.Stack;

public class Evaluation
{
	public static double evaluate(String expression)
	{
		char[] tokens = expression.toCharArray();

		Stack<Double> values = new
							Stack<Double>();

		Stack<Character> ops = new
							Stack<Character>();

		for (int i = 0; i < tokens.length; i++)
		{

			if (tokens[i] == ' ')
				continue;

			if (tokens[i] >= '0' &&
				tokens[i] <= '9')
			{
				StringBuffer sbuf = new
							StringBuffer();
		
				while (i < tokens.length &&
						tokens[i] >= '0' &&
						tokens[i] <= '9')
					sbuf.append(tokens[i++]);
				values.push(Double.parseDouble(sbuf.
									toString()));
	
				i--;
			}

			else if (tokens[i] == '(')
				ops.push(tokens[i]);

		
			else if (tokens[i] == ')')
			{
				while (ops.peek() != '(')
				values.push(applyOp(ops.pop(),
								values.pop(),
								values.pop()));
				ops.pop();
			}

			else if (tokens[i] == '+' ||
					tokens[i] == '-' ||
					tokens[i] == 'X' ||
						tokens[i] == '÷'
						|| tokens[i] == '%')
			{
	
				while (!ops.empty() &&
					hasPrecedence(tokens[i],
									ops.peek()))
				values.push(applyOp(ops.pop(),
								values.pop(),
								values.pop()));

				ops.push(tokens[i]);
			}
		}

		while (!ops.empty())
			values.push(applyOp(ops.pop(),
							values.pop(),
						values.pop()));

		return values.pop();
	}

	public static boolean hasPrecedence(
						char op1, char op2)
	{
		if (op2 == '(' || op2 == ')')
			return false;
		if ((op1 == 'X' || op1 == '÷' ) &&
			(op2 == '+' || op2 == '-') && op1 == '%')
			return false;
		else
			return true;
	}

	public static double applyOp(char op,
						double b, double a)
	{
		switch (op)
		{
		case '+':
			return a + b;
		case '-':
			return a - b;
		case 'X':
			return a * b;
		case '÷':
			if (b == 0)
				throw new
				UnsupportedOperationException(
					"Cannot divide by zero");
			return a / b;
		case '%':
		if (b == 0)
				throw new
				UnsupportedOperationException(
					"Cannot divide by zero");
			return a % b;
		}
		return 0;
	}
	public static void main(String[] args)
	{
		System.out.println(Evaluation.
						evaluate("10 + 2 * 6"));
		System.out.println(Evaluation.
					evaluate("100 * 2 + 12"));
		System.out.println(Evaluation.
				evaluate("100 * ( 2 + 12 )"));
		System.out.println(Evaluation.
			evaluate("100 * ( 2 + 12 ) / 14"));
	}
}
