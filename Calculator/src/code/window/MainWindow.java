package code.window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 2996202720425593795L;
	
	private JPanel contentPane;
	public boolean open = true;
	public JTextArea textField;
	public boolean calc = false;
	public JTextArea textArea;
	private JMenuBar menuBar;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setBackground(Color.DARK_GRAY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        dispose();
		        open = false;
		        System.exit(0);
		    }
		});
		
		
		
		setBounds(100, 100, 450, 517);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSettings = new JMenu("Options");
		menuBar.add(mnSettings);
		
		JMenuItem mntmCalculator = new JMenuItem("Calculator");
		mntmCalculator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnSettings.add(mntmCalculator);
		
		JMenuItem mntmGraphingCalculator = new JMenuItem("Graphing Calculator");
		mntmGraphingCalculator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnSettings.add(mntmGraphingCalculator);
		
		JMenuItem mntmSettings = new JMenuItem("Settings");
		mntmSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnSettings.add(mntmSettings);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				open = false;
				System.exit(0);
			}
		});
		mnSettings.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Font font = new Font("Arial", Font.BOLD, 24);
		
		textField = new JTextArea();
		textField.setBackground(new Color(176, 224, 230));
		textField.setFont(font);
		textField.setBounds(9, 407, 326, 45);
		contentPane.add(textField);
		
		textArea = new JTextArea();
		textArea.setBackground(new Color(176, 224, 230));
		textArea.setFont(font);
		textArea.setBounds(10, 11, 424, 374);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("Calculate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calc = true;
			}
		});
		btnNewButton.setBounds(345, 397, 89, 63);
		contentPane.add(btnNewButton);
		setVisible(true);
	}
}
