
import java.util.Scanner;

//Name: Zhuoying Yi
//USC NetID: 8303418105
//CS 455 PA1
//Fall 2017


public class CoinTossSimulatorTester {

	   public static void main(String[] args) {
		   
		   CoinTossSimulator test = new CoinTossSimulator();
		   
		   System.out.println("After constructor:");
		   test.run(0);
		   System.out.println("Number of trials [exp:0]: "+ test.getNumTrials());
		   System.out.println("Two-head tosses: "+ test.getTwoHeads());
		   System.out.println("Two-tail tosses: "+ test.getTwoTails());
		   System.out.println("One-head one-tail tosses: "+ test.getHeadTails());
		   System.out.println("Tosses add up correctly? "+(test.getNumTrials()==test.getTwoHeads()+test.getTwoTails()+test.getHeadTails()));
		   System.out.println();
		   
		   
		   test.run(1);
		   System.out.println("After run(1):");
		   System.out.println("Number of trials [exp:1]: "+ test.getNumTrials());
		   System.out.println("Two-head tosses: "+ test.getTwoHeads());
		   System.out.println("Two-tail tosses: "+ test.getTwoTails());
		   System.out.println("One-head one-tail tosses: "+ test.getHeadTails());
		   System.out.println("Tosses add up correctly? "+(test.getNumTrials()==test.getTwoHeads()+test.getTwoTails()+test.getHeadTails()));
		   System.out.println();
		   
		   
		   test.run(10);
		   System.out.println("After run(10):");
		   System.out.println("Number of trials [exp:11]: "+ test.getNumTrials());
		   System.out.println("Two-head tosses: "+ test.getTwoHeads());
		   System.out.println("Two-tail tosses: "+ test.getTwoTails());
		   System.out.println("One-head one-tail tosses: "+ test.getHeadTails());
		   System.out.println("Tosses add up correctly? "+(test.getNumTrials()==test.getTwoHeads()+test.getTwoTails()+test.getHeadTails()));
		   System.out.println();
		   
		   
		   test.run(100);
		   System.out.println("After run(100):");
		   System.out.println("Number of trials [exp:111]: "+ test.getNumTrials());
		   System.out.println("Two-head tosses: "+ test.getTwoHeads());
		   System.out.println("Two-tail tosses: "+ test.getTwoTails());
		   System.out.println("One-head one-tail tosses: "+ test.getHeadTails());
		   System.out.println("Tosses add up correctly? "+(test.getNumTrials()==test.getTwoHeads()+test.getTwoTails()+test.getHeadTails()));
		   System.out.println();
		   
		   
		   test.run(1000);
		   System.out.println("After run(1000):");
		   System.out.println("Number of trials [exp:1111]: "+ test.getNumTrials());
		   System.out.println("Two-head tosses: "+ test.getTwoHeads());
		   System.out.println("Two-tail tosses: "+ test.getTwoTails());
		   System.out.println("One-head one-tail tosses: "+ test.getHeadTails());
		   System.out.println("Tosses add up correctly? "+(test.getNumTrials()==test.getTwoHeads()+test.getTwoTails()+test.getHeadTails()));
		   System.out.println();
		   
		   
		   test.run(5000);
		   System.out.println("After run(5000):");
		   System.out.println("Number of trials [exp:6111]: "+ test.getNumTrials());
		   System.out.println("Two-head tosses: "+ test.getTwoHeads());
		   System.out.println("Two-tail tosses: "+ test.getTwoTails());
		   System.out.println("One-head one-tail tosses: "+ test.getHeadTails());
		   System.out.println("Tosses add up correctly? "+(test.getNumTrials()==test.getTwoHeads()+test.getTwoTails()+test.getHeadTails()));
		   System.out.println();
		   
		   
		   test.reset();
		   System.out.println("After reset:");
		   test.run(0);
		   System.out.println("Number of trials [exp:0]: "+ test.getNumTrials());
		   System.out.println("Two-head tosses: "+ test.getTwoHeads());
		   System.out.println("Two-tail tosses: "+ test.getTwoTails());
		   System.out.println("One-head one-tail tosses: "+ test.getHeadTails());
		   System.out.println("Tosses add up correctly? "+(test.getNumTrials()==test.getTwoHeads()+test.getTwoTails()+test.getHeadTails()));
		   System.out.println();
		   
		   
		   test.run(1000);
		   System.out.println("After run(1000):");
		   System.out.println("Number of trials [exp:1000]: "+ test.getNumTrials());
		   System.out.println("Two-head tosses: "+ test.getTwoHeads());
		   System.out.println("Two-tail tosses: "+ test.getTwoTails());
		   System.out.println("One-head one-tail tosses: "+ test.getHeadTails());
		   System.out.println("Tosses add up correctly? "+(test.getNumTrials()==test.getTwoHeads()+test.getTwoTails()+test.getHeadTails()));
		   System.out.println();
		   
		   
		   test.run(5000);
		   System.out.println("After run(5000):");
		   System.out.println("Number of trials [exp:6000]: "+ test.getNumTrials());
		   System.out.println("Two-head tosses: "+ test.getTwoHeads());
		   System.out.println("Two-tail tosses: "+ test.getTwoTails());
		   System.out.println("One-head one-tail tosses: "+ test.getHeadTails());
		   System.out.println("Tosses add up correctly? "+(test.getNumTrials()==test.getTwoHeads()+test.getTwoTails()+test.getHeadTails()));
		   System.out.println();

	}

}
