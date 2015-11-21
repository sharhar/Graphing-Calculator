package code;

public class ExpressionParser {

	public static char[] suppoertedOps = new char[5];
	public static char[] numberChars = new char[11];

	static {
		suppoertedOps[0] = '+';
		suppoertedOps[1] = '-';
		suppoertedOps[2] = '*';
		suppoertedOps[3] = '/';
		suppoertedOps[4] = '^';

		numberChars[0] = '0';
		numberChars[1] = '1';
		numberChars[2] = '2';
		numberChars[3] = '3';
		numberChars[4] = '4';
		numberChars[5] = '5';
		numberChars[6] = '6';
		numberChars[7] = '7';
		numberChars[8] = '8';
		numberChars[9] = '9';
		numberChars[10] = '.';
	}

	public static double eval(String exp) {
		String ex = Utils.stripSpaces(exp);

		int highest = 0;

		int temp = 0;
		for (int i = 0; i < ex.length(); i++) {
			if (ex.charAt(i) == '(') {
				temp++;
			} else if (ex.charAt(i) == ')') {
				temp--;
			}

			if (temp > highest) {
				highest = temp;
			}
		}

		for (int i = highest; i > 0; i--) {
			ex = evalLevel(ex, i);
		}

		return evalEx(ex);
	}

	private static String evalLevel(String totalText, int level) {
		String result = totalText;
		boolean run = true;
		while (run) {
			int[] pos = ffe(result, level);

			if (pos[1] == -1) {
				if (pos[0] == -1) {
					return result;
				}
				pos[1] = totalText.length();
			}

			double value = evalEx(result.substring(pos[0], pos[1]));

			result = result.substring(0, pos[0] - 1) + value + result.substring(pos[1] + 1, result.length());
		}

		return result;
	}

	private static int[] ffe(String text, int target) {
		int[] result = new int[2];

		result[0] = -1;
		result[1] = -1;

		int level = 0;
		boolean atPlace = false;

		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == '(') {
				level++;
				if (level == target) {
					result[0] = i + 1;
					atPlace = true;
				}
			} else if (text.charAt(i) == ')') {
				level--;
				if (atPlace) {
					result[1] = i;
					return result;
				}
			}
		}

		return result;
	}

	public static double evalEx(String exp) {
		String result = new String(exp);

		char[] ops = new char[1];
		ops[0] = '^';
		result = evalOps(result, ops);
		ops = new char[2];
		ops[0] = '*';
		ops[1] = '/';
		result = evalOps(result, ops);
		ops[0] = '+';
		ops[1] = '-';
		result = evalOps(result, ops);

		return Double.parseDouble(result);
	}

	private static String evalOps(String exp, char[] ops) {
		String result = new String(exp);

		boolean run = true;
		while (run) {
			int index = -1;
			for (int i = 0; i < ops.length; i++) {
				int pos = result.indexOf(ops[i]);
				if (index < pos) {
					index = pos;
				}
			}

			if (index == -1) {
				run = false;
			} else {
				String[] numbers = getNumbers(result, index).split(" ");

				double value = evalSimpleExp(numbers, result.charAt(index));

				int start = Integer.parseInt(numbers[2]);
				int end = Integer.parseInt(numbers[3]);

				String a = result.substring(0, start);
				String b = result.substring(end + 1, result.length());

				result = a + value + b;
			}
		}

		return result;
	}

	public static double evalSimpleExp(String[] numbers, char op) {
		if (numbers.length == 1) {
			return Double.parseDouble(numbers[0]);
		}

		double num1 = Double.parseDouble(numbers[0]);
		double num2 = Double.parseDouble(numbers[1]);
		try {
			if (!Utils.inCharArray(op, suppoertedOps)) {

				throw new RuntimeException(op + "");

			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		double total = 0;

		if (op == '+') {
			total = num1 + num2;
		} else if (op == '-') {
			total = num1 - num2;
		} else if (op == '*') {
			total = num1 * num2;
		} else if (op == '/') {
			total = num1 / num2;
		} else if (op == '^') {
			total = Math.pow(num1, num2);
		}

		return total;
	}

	private static String getNumbers(String text, int index) {

		int start = -1;
		int end = -1;

		int start2 = -1;
		int end2 = -1;

		String num1 = "";
		String num2 = "";

		boolean found = false;
		for (int i = index - 1; !found; i--) {
			if (i == -1) {
				start = i + 1;
				found = true;
			} else {

				char cur = text.charAt(i);

				if (i == index - 1) {
					if (cur == ' ') {
						end2 = i - 1;
					} else {
						end2 = i;
					}

				} else {
					if (!Utils.inCharArray(cur, numberChars)) {
						start = i + 1;
						found = true;
					}
				}
			}
		}

		found = false;

		for (int i = index + 1; !found; i++) {
			if (i == text.length()) {
				end = i - 1;
				found = true;
			} else {

				char cur = text.charAt(i);

				if (i == index + 1) {
					if (cur == ' ') {
						start2 = i + 1;
					} else {
						start2 = i;
					}

				} else {
					if (!Utils.inCharArray(cur, numberChars)) {
						end = i - 1;
						found = true;
					}
				}
			}
		}

		num1 = text.substring(start, end2 + 1);
		num2 = text.substring(start2, end + 1);

		return (num1 + " " + num2 + " " + start + " " + end);
	}
}
