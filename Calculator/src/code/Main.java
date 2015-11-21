package code;

import code.window.MainWindow;

public class Main {

	public static void main(String[] args) {

		/*
		 * String ex = ""; Scanner scan = new Scanner(System.in);
		 * 
		 * boolean run = true; while(run) { System.out.print(
		 * "Enter expression: "); ex = scan.nextLine();
		 * 
		 * if(ex.equals("q")) { scan.close(); return; }
		 * 
		 * System.out.println(ExpressionParser.eval(ex));
		 * 
		 * }
		 * 
		 * scan.close();
		 */

		MainWindow window = new MainWindow();

		while (window.open) {
			String currentText = window.textField.getText();

			if (currentText != null && window.calc) {
				String areaText = window.textArea.getText();
				double value = 0;
				try {
					value = ExpressionParser.eval(currentText);
					areaText += (currentText + "\n -> " + value + "\n\n");
				} catch (Exception e) {
					areaText += "invalid expression!\n";
				}
				window.textArea.setText(areaText);
				window.textField.setText("");
				window.calc = false;
			}
		}
	}

	public static boolean textContainsChar(String text, char c) {
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == c) {
				return true;
			}
		}

		return false;
	}
}
