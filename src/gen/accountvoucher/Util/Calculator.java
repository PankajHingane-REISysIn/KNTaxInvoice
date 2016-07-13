package gen.accountvoucher.Util;

import gen.dto.Constants;
import gen.dto.Util;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener, ItemListener, KeyListener {

    private final String VERSION = "4.0";
    JFrame window = new JFrame("Calculator " + VERSION);
    JMenuBar Menu = new JMenuBar();
    JMenu mnuProgram, mnuView, mnuHelp;
    JMenuItem mnuReset, mnuExit, mnuAbout;
    JCheckBoxMenuItem mnuShowCalc;
    JPanel pnlBottom, pnlBackspace, pnlClear, pnlButtons, pnlCalculation;
    JLabel lblFirst, lblOperator, lblSecond, lblEqual, lblResult;
    static JTextField txtDisplay;
    JButton buttons[] = new JButton[23];
    final short MAX_INPUT = 30;
    final float DIVIDE_ZERO_ERROR = (float) -.04060802;
    final float ERROR = (float) -.04060801;
    String tempNum;
    String sign;
    String Label;
    String R;
    String tempString;
    double number1, number2, result;
    boolean decimalUsed, secondNum, makeSecondTrue, lblSecondNum, secondEqual, error;
    Image img = new ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png")).getImage();
//constructor for Calculator class

    public Calculator() {

        Toolkit.getDefaultToolkit().getSystemEventQueue().push(new CopyPasteOperation(txtDisplay));

        //Window Properties
        window.setSize(306, 210);
        window.setLocation(470, 290);
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setResizable(false);
        window.setIconImage(img);
//Making the Menu and Menu Items and adding action listener/item listener
        mnuProgram = new JMenu("Program");
        mnuView = new JMenu("View");
        //mnuHelp = new JMenu("Help");
        Menu.add(mnuProgram);
        Menu.add(mnuView);
        //Menu.add(mnuHelp);
        mnuReset = new JMenuItem("Reset");
        mnuExit = new JMenuItem("Exit");
        mnuShowCalc = new JCheckBoxMenuItem("Show Calculations");
        //mnuAbout = new JMenuItem("About Calculator");
        mnuProgram.add(mnuReset);
        mnuProgram.add(mnuExit);
        mnuView.add(mnuShowCalc);
        //mnuHelp.add(mnuAbout);
        mnuReset.addActionListener(this);
        mnuExit.addActionListener(this);
        mnuShowCalc.addItemListener(this);
        //mnuAbout.addActionListener(this);

        pnlBottom = new JPanel();
        pnlBackspace = new JPanel();
        pnlClear = new JPanel();
        pnlButtons = new JPanel();
        pnlCalculation = new JPanel();

        //Setting Layouts for the Panels and other Properties
        pnlBottom.setLayout(new BorderLayout());
        pnlButtons.setLayout(new GridLayout(4, 5, 2, 2));
        pnlClear.setLayout(new GridLayout(1, 2, 2, 2));
        pnlBackspace.setLayout(new GridLayout(1, 2, 2, 2));
        pnlCalculation.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlBottom.setBackground(new Color(200, 200, 200));

        //Making Labels and adding them to pnlCalculation Panel.
        lblFirst = new JLabel();
        lblOperator = new JLabel();
        lblSecond = new JLabel();
        lblEqual = new JLabel();
        lblResult = new JLabel();
        pnlCalculation.add(lblFirst);
        pnlCalculation.add(lblOperator);
        pnlCalculation.add(lblSecond);
        pnlCalculation.add(lblEqual);
        pnlCalculation.add(lblResult);

        //Text Field Properties
        txtDisplay = new JTextField("0");
        txtDisplay.setEditable(false);
        txtDisplay.setHorizontalAlignment(JTextField.TRAILING);
        txtDisplay.setBackground(Color.WHITE);
        txtDisplay.setOpaque(true);
        txtDisplay.addKeyListener(this);

        //Making Buttons and adding to Panel Buttons
        buttons[0] = new JButton("0");
        buttons[10] = new JButton(".");
        buttons[11] = new JButton("=");
        buttons[12] = new JButton("/");
        buttons[13] = new JButton("*");
        buttons[14] = new JButton("-");
        buttons[15] = new JButton("+");
        buttons[16] = new JButton("sqrt");
        buttons[17] = new JButton("%");
        buttons[18] = new JButton("1/x");
        buttons[19] = new JButton("+/-");
        for (int i = 7; i < 10; i++) {
            buttons[i] = new JButton(String.valueOf(i));
            pnlButtons.add(buttons[i]);
        }
        pnlButtons.add(buttons[12]);
        pnlButtons.add(buttons[16]);
        for (int i = 4; i < 7; i++) {
            buttons[i] = new JButton(String.valueOf(i));
            pnlButtons.add(buttons[i]);
        }
        pnlButtons.add(buttons[13]);
        pnlButtons.add(buttons[17]);
        for (int i = 1; i < 4; i++) {
            buttons[i] = new JButton(String.valueOf(i));
            pnlButtons.add(buttons[i]);
        }
        pnlButtons.add(buttons[14]);
        pnlButtons.add(buttons[18]);
        pnlButtons.add(buttons[0]);
        pnlButtons.add(buttons[10]);
        pnlButtons.add(buttons[11]);
        pnlButtons.add(buttons[15]);
        pnlButtons.add(buttons[19]);

        //Creating Backspace Button and adding it to Backspace panel
        buttons[20] = new JButton("BackSpace");
        pnlBackspace.add(buttons[20]);

        //Creating Clear Buttons and adding it to Clear panel
        buttons[21] = new JButton("CE");
        buttons[22] = new JButton(" C ");
        pnlClear.add(buttons[21]);
        pnlClear.add(buttons[22]);

        /*Adds Action Listener to every button */
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(this);
            if (i < 11 || (i > 15 && i < 20)) {
                buttons[i].setForeground(Color.blue);
            } else {
                buttons[i].setForeground(Color.red);
            }
        }

        //Adds Panels to window and shows window
        pnlBottom.add(txtDisplay, BorderLayout.NORTH);
        pnlBottom.add(pnlBackspace, BorderLayout.WEST);
        pnlBottom.add(pnlClear, BorderLayout.EAST);
        pnlBottom.add(pnlButtons, BorderLayout.SOUTH);
        window.add(Menu, BorderLayout.NORTH);
        window.add(pnlCalculation);
        window.add(pnlBottom, BorderLayout.SOUTH);
        window.setVisible(true);
        pnlCalculation.setVisible(false);

        //Clears the screen. sets Variables to default.
        Clear();
    }

    public void actionPerformed(ActionEvent click) {
        //	Adding Digits to Screen
        for (int i = 0; i < 10; i++) {
            if (click.getSource() == buttons[i]) {
                addDigit(String.valueOf(i));
            }
        }
        //	Adding Decimal Point to Screen
        if (click.getSource() == buttons[10]) {
            if (!decimalUsed) {
                addDigit(".");
                decimalUsed = true;
            }
        }
        for (int i = 11; i < buttons.length; i++) {
            if (click.getSource() == buttons[i]) {
                switch (i) {
                    case 11:
                        Calculate("=");// EQUAL
                        break;
                    case 12:
                        Calculate("/");//	DIVDIE
                        break;
                    case 13:
                        Calculate("*");//	MULTIPLY
                        break;
                    case 14:
                        Calculate("-");//	SUBTRACT
                        break;
                    case 15:
                        Calculate("+");//	ADD
                        break;
                    case 16://	SQUARE ROOT
                        sqrt();
                        break;
                    case 17://	%
                        percent();
                        break;
                    case 18://	1/x
                        divideByX();
                        break;
                    case 19://	+/-
                        signChange();
                        break;
                    case 20://	BACKSPACE
                        Backspace();
                        break;
                    case 21://	CE
                        ClearExisting();
                        break;
                    case 22://	C
                        Clear();
                        break;
                }
            }
        }
        if (!(buttons[11] == click.getSource())) {
            secondEqual = false;
        }
        // Program -> Reset
        if (click.getSource() == mnuReset) {
            Clear();
            window.setSize(306, 210);
            pnlCalculation.setVisible(false);
            mnuShowCalc.setState(false);
            JOptionPane.showMessageDialog(null, "Reset!\nEverthing is back to default.", "Reset", JOptionPane.INFORMATION_MESSAGE);
        }
        // Program -> Exit
        if (click.getSource() == mnuExit) {
            //System.exit(0);
            window.dispose();
        }

        txtDisplay.requestFocus();
    }

    public void itemStateChanged(ItemEvent click) {
        if (click.getSource() == mnuShowCalc) {
            if (click.getStateChange() == ItemEvent.SELECTED) {
                window.setSize(306, 235);
                pnlCalculation.setVisible(true);
            } else {
                window.setSize(306, 210);
                pnlCalculation.setVisible(false);
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyChar();
        if (!(key == KeyEvent.VK_ENTER)) {
            secondEqual = false;
        }
        switch (key) {
            case KeyEvent.VK_0:
                addDigit("0");
                break;
            case KeyEvent.VK_1:
                addDigit("1");
                break;
            case KeyEvent.VK_2:
                addDigit("2");
                break;
            case KeyEvent.VK_3:
                addDigit("3");
                break;
            case KeyEvent.VK_4:
                addDigit("4");
                break;
            case KeyEvent.VK_5:
                addDigit("5");
                break;
            case KeyEvent.VK_6:
                addDigit("6");
                break;
            case KeyEvent.VK_7:
                addDigit("7");
                break;
            case KeyEvent.VK_8:
                addDigit("8");
                break;
            case KeyEvent.VK_9:
                addDigit("9");
                break;
            case KeyEvent.VK_PERIOD:
                if (!decimalUsed) {
                    addDigit(".");
                    decimalUsed = true;
                }
                break;
            case KeyEvent.VK_ENTER:
                Calculate("=");
                break;
            case KeyEvent.VK_SLASH:
                Calculate("/");
                break;
            case 42:
                Calculate("*");
                break;
            case KeyEvent.VK_MINUS:
                Calculate("-");
                break;
            case 43:
                Calculate("+");
                break;
            case KeyEvent.VK_BACK_SPACE:
                Backspace();
                break;
            case KeyEvent.VK_ESCAPE:
                Clear();
                break;
            case KeyEvent.VK_DELETE:
                ClearExisting();
                break;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void addDigit(String digit) {
        if (!error) {
            if (tempNum.equals("") && digit.equals("0")) {
                txtDisplay.setText("0");
                tempNum = "";
                if (lblSecondNum) {
                    lblSecond.setText("0");
                } else {
                    lblFirst.setText("0");
                    lblSecond.setText("_");
                    lblResult.setText("_");
                }
            } else if (tempNum.length() < MAX_INPUT) {
                tempNum += digit;
                if (tempNum.charAt(0) == '.') {
                    tempNum = "0" + tempNum;
                }
                txtDisplay.setText(tempNum);
                if (makeSecondTrue) {
                    secondNum = true;
                }
                checkLabels();
            }
            secondEqual = false;
        }
    }

    public void Calculate(String operator) {
        if (!error) {
            decimalUsed = false;
            if (operator.equals("=")) {
                if (secondEqual) {	///////////
                    number1 = result;				//
                    result = Process();			//Equal Sign Pressed Twice or more
                    Display(result);				//
                } else {				///////////			////////////////////
                    number2 = Double.parseDouble(txtDisplay.getText());	//
                    setSecondLabel();													//
                    result = Process();												//Equal Sign Pressed Once
                    Display(result);													//
                    number1 = result;													//
                    secondEqual = true;												//
                }													////////////////////
                secondNum = false;
                makeSecondTrue = false;
                lblSecondNum = false;
            } else {
                if (secondNum) {	/////////////////
                    number2 = Double.parseDouble(txtDisplay.getText());
                    setSecondLabel();					//
                    result = Process();				//
                    Display(result);					//Second Part
                    number1 = result;					//
                    lblSecond.setText("_");			//
                    lblResult.setText("_");			//
                    lblOperator.setText("?");		//
                    setFirstLabel(String.valueOf(result));
                    secondNum = false;				//
                } else {			/////////////////        ///////////////////
                    number1 = Double.parseDouble(txtDisplay.getText());		//
                    makeSecondTrue = true;												//
                    setFirstLabel();														//
                    lblSecond.setText("_");												//First Part
                    lblResult.setText("_");												//	
                    lblOperator.setText("?");											//
                    lblSecondNum = true;													//
                }														////////////////////
                sign = operator;
                secondEqual = false;
            }
            lblOperator.setText(sign);
            tempNum = "";
        }
    }

    public double Process() {
        if (sign.equals("*")) {
            return (number1 * number2);
        } else if (sign.equals("-")) {
            return (number1 - number2);
        } else if (sign.equals("+")) {
            return (number1 + number2);
        } else if (sign.equals("/")) {
            if (number2 == 0) {
                return DIVIDE_ZERO_ERROR;
            } else {
                return (number1 / number2);
            }
        } else {
            return ERROR;	//anything other than (/, *, -, +) should return ERROR so when displayed it gives error
        }
    }

    //Displays a double number unless if some errors are created 
    public void Display(double num) {
        if (num == DIVIDE_ZERO_ERROR) {
            R = "Cannot Divide by Zero!";
            error = true;
        } else if (num == ERROR) {
            R = "Invalid Input for Function!";
            error = true;
        } else {
            R = String.valueOf(Constants.DECIMAL_FORMAT.format(num));
            if ((R.charAt((R.length()) - 1) == '0') && (R.charAt((R.length()) - 2) == '.')) {
                R = R.substring(0, R.length() - 2);
            }
        }
        txtDisplay.setText(R);
        lblResult.setText(R);
        setFirstLabel();
    }

    //Square Root function. (sqrt)
    public void sqrt() {
        if (!error) {
            tempString = txtDisplay.getText();
            try {
                result = Math.sqrt(Double.parseDouble(tempString));
            } catch (Exception e) {
                result = ERROR;
            }
            if (tempString.indexOf("-") == 0) {
                result = ERROR;
            }
            lblSecond.setText("sqrt( " + tempString + " )");
            Display(result);
            lblFirst.setText("");
            lblOperator.setText("");
            secondNum = false;
            makeSecondTrue = false;
            lblSecondNum = false;
            tempNum = "";
        }
    }

    //converts number to percent (%)
    public void percent() {
        if (!error) {
            tempString = txtDisplay.getText();
            try {
                result = (Double.parseDouble(tempString) / 100);
            } catch (Exception e) {
                result = ERROR;
            }
            Display(result);
            setFirstLabel(tempString);
            lblOperator.setText("/");
            setSecondLabel("100");
            secondNum = false;
            makeSecondTrue = false;
            lblSecondNum = false;
            tempNum = "";
        }
    }

    //1 Divide by X function (1/x)
    public void divideByX() {
        if (!error) {
            tempString = txtDisplay.getText();
            if (tempString.equals("0")) {
                result = DIVIDE_ZERO_ERROR;
            } else {
                try {
                    result = 1 / Double.parseDouble(tempString);
                } catch (Exception e) {
                    result = ERROR;
                }
            }
            setSecondLabel(tempString);
            Display(result);
            setFirstLabel("1");
            lblOperator.setText("/");
            secondNum = false;
            makeSecondTrue = false;
            lblSecondNum = false;
            tempNum = "";
        }
    }

    //Makes it negative if positive and vice versa
    public void signChange() {
        if (!error) {
            tempString = txtDisplay.getText();
            if (tempString.equals("0")) {
                /* Do Nothing*/
            } else {
                if (tempString.charAt(0) == '-') {
                    tempNum = tempNum.substring(1, tempNum.length());
                } else {
                    tempNum = "-" + tempString;
                }
                txtDisplay.setText(tempNum);
                checkLabels();
            }
        }
    }

    /*	Checks the labels in pnlCalulation to the correct number corresponding with 
     whatever user types in.																					*/
    public void checkLabels() {
        if (tempNum.equals("")) {
            if (lblSecondNum) {
                setFirstLabel();
                lblSecond.setText("_");
            } else {
                lblFirst.setText("_");
                lblSecond.setText("_");
                lblResult.setText("_");
                lblOperator.setText("?");
            }
        } else {
            if (lblSecondNum) {
                lblSecond.setText(tempNum);
            } else {
                lblFirst.setText(tempNum);
                lblSecond.setText("_");
                lblResult.setText("_");
                lblOperator.setText("?");
            }
        }
    }

    /*	First Number Label, lblFirst, gets set the number1 without the .0 at the end.
     Sets lblFirst to whatever number1 is depending on where it is needed.				*/
    public void setFirstLabel() {
        Label = String.valueOf(number1);
        if (Label.length() > 2) {
            if (Label.charAt(Label.length() - 1) == '0' && Label.charAt(Label.length() - 2) == '.') {
                Label = Label.substring(0, Label.length() - 2);
            }
        }
        lblFirst.setText(Label);
    }

    public void setFirstLabel(String f) {
        if (f.length() > 2) {
            if (f.charAt(f.length() - 1) == '0' && f.charAt(f.length() - 2) == '.') {
                f = f.substring(0, f.length() - 2);
            }
        }
        lblFirst.setText(f);
    }

    public void setSecondLabel() {
        Label = String.valueOf(number2);
        lblResult.setText("_");
        if (Label.length() > 2) {
            if (Label.charAt(Label.length() - 1) == '0' && Label.charAt(Label.length() - 2) == '.') {
                Label = Label.substring(0, Label.length() - 2);
            }
        }
        lblSecond.setText(Label);
    }

    public void setSecondLabel(String s) {
        if (s.length() > 2) {
            if (s.charAt(s.length() - 1) == '0' && s.charAt(s.length() - 2) == '.') {
                s = s.substring(0, s.length() - 2);
            }
        }
        lblSecond.setText(s);
    }

    //Backspace
    public void Backspace() {
        if (!error) {
            if (txtDisplay.getText().length() < 2) {
                tempNum = "";
                txtDisplay.setText("0");
            } else {
                if ((txtDisplay.getText().charAt(txtDisplay.getText().length() - 1) == '.'
                        && txtDisplay.getText().charAt(txtDisplay.getText().length() - 2) == '0')
                        || (txtDisplay.getText().length() == 2 && txtDisplay.getText().charAt(0) == '-')) {
                    tempNum = "";
                    txtDisplay.setText("0");
                    decimalUsed = false;
                } else {
                    if (txtDisplay.getText().charAt(txtDisplay.getText().length() - 1) == '.') {
                        decimalUsed = false;
                    }
                    tempNum = txtDisplay.getText().substring(0, txtDisplay.getText().length() - 1);
                    txtDisplay.setText(tempNum);
                }
            }
            checkLabels();
        }
    }

    //Clears First Number or Second Number or everything if result has been shown.
    public void ClearExisting() {
        tempNum = "";
        txtDisplay.setText("0");
        decimalUsed = false;
        checkLabels();
        error = false;
    }

    //Resets everything to startup
    public void Clear() {
        tempNum = "";
        sign = "0";
        tempString = "";
        Label = "";
        R = "";
        txtDisplay.setText("0");
        lblFirst.setText("_");
        lblOperator.setText("?");
        lblSecond.setText("_");
        lblEqual.setText("=");
        lblResult.setText("_");
        number1 = 0.0;
        number2 = 0.0;
        result = 0.0;
        decimalUsed = false;
        secondNum = false;
        makeSecondTrue = false;
        lblSecondNum = false;
        secondEqual = false;
        error = false;
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
