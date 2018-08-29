
import java.util.Scanner;

import javax.swing.JFrame;

//Name: Zhuoying Yi
//USC NetID: 8303418105
//CS 455 PA1
//Fall 2017


public class CoinSimViewer {
	
	public static void main(String[] args) {
		System.out.print("Enter number of trials: ");
		Scanner in = new Scanner(System.in);	
		int m = in.nextInt();
		while (m<=0) {
			System.out.println("ERROR: Number entered must be greater than 0.");
			System.out.print("Enter number of trials: ");
			m = in.nextInt();
		}
		JFrame frame = new JFrame();

	      frame.setSize(800, 500);
	      frame.setTitle("CoinSim");
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	      CoinSimComponent component = new CoinSimComponent(m);
	      frame.add(component);

	      frame.setVisible(true);
	      	      
	}

}
