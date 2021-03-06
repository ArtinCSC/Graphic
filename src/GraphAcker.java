
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JTextField;

public class GraphAcker extends JApplet implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static int temp;
	private static int count = 50;
	private static int countTotal = 0;
	private static int counter = 0;
	private static int h = 600;

	int forColor = 1;
	int m, n;
	boolean paint = false;
	Font titleFont;
	Font regularFont;
	// *** two buttons are declared
	JButton DrawLinesButton;
	JButton clearLinesButton;

	// *** two input values declared
	JTextField inputLineNumber1;
	JTextField inputLineNumber2;

	Container contentPane;

	// *** all JApplet have a method paint to draw the graphics
	// *** Graphics object is brought in
	// *** paint() is automatically called
	public void paint(Graphics page) {
		if (!paint)
			graphInitialize(page);
		super.paint(page);
		setBackground(Color.WHITE);
		graph(page);

	}

	// *** The Applet class defines a method that is essential for programming
	// applets,
	// *** the init() method. This method is called just after the applet object
	// has
	// *** been created and before it appears on the screen. Its purpose is to
	// give
	// *** the applet a chance to do any necessary initialization. This method
	// is
	// *** called by the system, not by your program. Your job as a programmer
	// *** is just to provide a definition of the init() method.
	public void init() {
		System.out.println("init has been called");
		contentPane = getContentPane();
		contentPane.setLayout(null);
		// *** 32 and 18 are pixel size
		titleFont = new Font("Arial", Font.BOLD, 32);
		regularFont = new Font("Arial", Font.PLAIN, 18);

		// *** memory is allocated for Button
		DrawLinesButton = new JButton("Draw Lines");
		// *** this button is active and will be read
		// *** by method actionPerformed(ActionEvent e)
		DrawLinesButton.addActionListener(this);
		// *** Location of button is created
		DrawLinesButton.setBounds(350, 650, 150, 40);

		// *** memory is allocated for Button
		clearLinesButton = new JButton("Clear Lines");
		// *** this button is active and will be read
		// *** by method actionPerformed(ActionEvent e)
		clearLinesButton.addActionListener(this);
		// *** Location of button is created
		clearLinesButton.setBounds(350, 700, 150, 40);

		// *** setBounds() creates the input box
		// *** memory is allocated for TextField
		inputLineNumber1 = new JTextField(15);
		// *** Location of TextField is created
		inputLineNumber1.setBounds(210, 660, 50, 30);

		// *** memory is allocated for TextField
		inputLineNumber2 = new JTextField(15);
		// *** Location of TextField is created
		inputLineNumber2.setBounds(210, 690, 50, 30);

		// *** add them to contentPane
		contentPane.add(DrawLinesButton);
		contentPane.add(clearLinesButton);
		contentPane.add(inputLineNumber1);
		contentPane.add(inputLineNumber2);
	}

	public void graphInitialize(Graphics page) {
		Graphics g = getGraphics();
		Color c = getBackground();
		g.setColor(c);
		for (int x = 0; x < 1250; x++)
			g.drawLine(x, 900, x, 0);
		System.out.println("graphInitialize has been called");
		page.setColor(Color.BLACK);
		page.setFont(titleFont);
		page.setFont(regularFont);
		// *** Strings line up with setBounds for each JTextField
		 //page.drawString("Enter line number 1: ",40, 580);
		//page.drawString("Enter line number 2: ",40, 610);
		// *** initialize input to 0
		inputLineNumber1.setText("0");
		inputLineNumber2.setText("0");
	}

	public void graph(Graphics page) {
		System.out.println("graph has been called");
		if (!inputLineNumber1.getText().equals("") && !inputLineNumber2.getText().equals("")) {
			// *** text must be changed to int using the wrapper class Integer
			int lineNumber1 = Integer.parseInt(inputLineNumber1.getText());
			int lineNumber2 = Integer.parseInt(inputLineNumber2.getText());
			int result = 0;
			// *** method called here
			counter = 0;
			count = 50;
			 result = ackermannValue(lineNumber1, lineNumber2, page);
			// *** method called here

			// *** 3 strings are placed in the window
			page.setColor(Color.BLACK);
			page.setFont(titleFont);
			page.drawString("Ackermann Trace Graph", 540, 50);
			page.setFont(regularFont);
			page.drawString("Number of lines:  " + countTotal, 600, 670);
			page.drawString("Ackermann function is called:  " + counter, 600, 700);
			page.drawString("Ackermann value:  " + result, 600, 730);
			page.drawString("Enter line number 1: ", 40, 680);
			page.drawString("Enter line number 2: ", 40, 710);
		}
	}

	private int ackermannValue(int m, int n, Graphics page) { 
		counter++;
		countTotal = 0;
		for (int i = 0; i < m; i++) {
			page.drawLine(count, 650, count, h);
			h--;
			if(h == 65){
				h = 600;
				setNewColor(page, (int) (Math.random() * 12) + 1);
				//if(count < 1189)
				//count = count + 10;
			}
			count++;
			if (count == 1200)
				count = 50;
			countTotal++;
		}
	
		for (int i = 0; i < n; i++) {
			page.drawLine(count, 650, count, h);
			h--;
			if(h == 65){
				h = 600;
				setNewColor(page, (int) (Math.random() * 12) + 1);
				//if(count < 1189)
				//count = count + 10;
			}
			count++;
			if (count == 1200)
				count = 50;
			countTotal++;
		}
		
		if (m == 0)
			n = n + 1;
		else if (n == 0)
			n = ackermannValue(m - 1, 1, page);
		else
			n = ackermannValue(m - 1, ackermannValue(m, n - 1, page), page);

		return n;
	}

	private void setNewColor(Graphics page, int forColor) {
		switch (forColor) {
		case 1:
			page.setColor(Color.BLACK);
			break;
		case 2:
			page.setColor(Color.BLUE);
			break;
		case 3:
			page.setColor(Color.CYAN);
			break;
		case 4:
			page.setColor(Color.DARK_GRAY);
			break;
		case 5:
			page.setColor(Color.GRAY);
			break;
		case 6:
			page.setColor(Color.GREEN);
			break;
		case 7:
			page.setColor(Color.LIGHT_GRAY);
			break;
		case 8:
			page.setColor(Color.DARK_GRAY);
			break;
		case 9:
			page.setColor(Color.MAGENTA);
			break;
		case 10:
			page.setColor(Color.ORANGE);
			break;
		case 11:
			page.setColor(Color.PINK);
			break;
		case 12:
			page.setColor(Color.YELLOW);
			break;
		}
	}

	public void actionPerformed(ActionEvent e) {
		// *** buttons are active
		String actionCommand = e.getActionCommand();
		// *** one of two possible values of actionCommand
		if (actionCommand.equalsIgnoreCase("Draw Lines")) {
			System.out.println("Draw Lines has been called");
			paint = true;
			countTotal = 0;
			count = 0;
			repaint();// *** method paint() is called again
		} else if (actionCommand.equalsIgnoreCase("Clear Lines")) {
			System.out.println("Clear Lines has been called");
			this.clear();
			inputLineNumber1.setText("");
			inputLineNumber2.setText("");
		}
	}

	public void clear() {
		// *** Graphics object is gotten
		Graphics g = getGraphics();
		Color c = getBackground();
		g.setColor(c);
		// *** lines of the background color cover up the image
		for (int i = 100; i < 1250; i++)
			g.drawLine(i, 500, i, 150);
		for (int i = 600; i < 1000; i++)
			g.drawLine(i, 700, i, 75);

	}
}