import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.script.*;

class Calculator extends JFrame implements ActionListener{
	
	JButton b[] = new JButton[20];
	JTextField tf;
	Font f = new Font("",Font.BOLD,20);
	
	Calculator(){
		setTitle("Calculator By Lucky");
		setVisible(true);
		setSize(415,640);
		setLocation(200,60);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		tf = new JTextField();
		tf.setSize(400,100);
		tf.setLocation(0,0);
		tf.setHorizontalAlignment(JTextField.RIGHT);
		tf.setFont(f);
		add(tf);
		
		
		int k=0;
		int x=0;
		int y=100;
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<4;j++)
			{
			    b[k] = new JButton();
				b[k].setSize(100,100);
				b[k].setLocation(x,y);
				b[k].setFont(f);
				b[k].addActionListener(this);
				add(b[k]);
				x = x + 100;
				k++;
			}
			x=0;
			y = y + 100;
		}
		
		b[0].setLabel("BACK");
		b[1].setLabel("DELETE");
		b[2].setLabel("1/x");
		b[3].setLabel("SQRT");
		
		b[4].setLabel("7");
		b[5].setLabel("8");
		b[6].setLabel("9");
		b[7].setLabel("/");
		
		b[8].setLabel("4");
		b[9].setLabel("5");
		b[10].setLabel("6");
		b[11].setLabel("*");
		
		b[12].setLabel("1");
		b[13].setLabel("2");
		b[14].setLabel("3");
		b[15].setLabel("-");
		
		b[16].setLabel("0");
		b[17].setLabel(".");
		b[18].setLabel("=");
		b[19].setLabel("+");
	}
	public void actionPerformed(ActionEvent e){
		JButton bu = (JButton) e.getSource();
		if(e.getSource() == b[0]){
			String sent = tf.getText();
			String back = sent.substring(0,sent.length() -1);
			tf.setText(back);
		}
        else if(e.getSource() == b[1]){
			tf.setText("");
			
		}
		else if(e.getSource() == b[2]){
			String no = tf.getText();
			Double a = Double.parseDouble(no);
			a = 1/a;
			tf.setText(""+a);
		
		}
		else if(e.getSource() == b[3]){
			String sqrt = tf.getText();
			int s = Integer.parseInt(sqrt);
			double squareroot = Math.sqrt(s);
			tf.setText(""+squareroot);
			
		}
		else if(e.getSource() == b[18]){
			String evaluate = tf.getText();
			ScriptEngineManager sem = new ScriptEngineManager();
			ScriptEngine se = sem.getEngineByName("js");
			try{
			
				tf.setText(""+se.eval(evaluate));
			}catch(Exception exc){}
		}
		else{
		String some = tf.getText() + bu.getLabel();
			tf.setText(some);
		}
	}
	
	public static void main(String []a)
	{
		Calculator cal = new Calculator();
	}
	
}
