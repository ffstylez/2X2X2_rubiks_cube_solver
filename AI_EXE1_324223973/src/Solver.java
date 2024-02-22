import java.util.List;
import java.util.Scanner;




public class Solver {

	public static void main(String[] args) {
		System.out.println("enter the colors represintitive letters (W,R,Y,O,G,B) for the cube you want to solve without spaces");
		System.out.println("according to the indexes order shown in the homework file ");
		Scanner scanner = new Scanner(System.in);
		String input= scanner.nextLine().toUpperCase();
		Cube cube = new Cube (input.toCharArray());
		if(cube.getFaces()!=null)
		{
		
		boolean a=true;
		String input3 = null;
		while( a== true)
		{
		System.out.println("type which algorithm you want to solve with dfs/ucs/a*");
		String input2 = scanner.nextLine().toLowerCase();
		if (input2.equals("dfs") || input2.equals("ucs") || input2.equals("a*"))
			{
				a=false;
				input3=input2;
			}	
		}
		switch (input3)
		{
		case "dfs":
			List<String> list1=Cube.reconstructPath(cube.iterativeDeepeningSearch(cube.getFaces()));
			for(String step : list1)
			{
				 System.out.println(step);
			}
			break;
		case"ucs":
			List<String> list2=Cube.reconstructPath(cube.ucs(cube.getFaces()));
			for(String step : list2)
			{
				System.out.println(step);
			}
			break;
		case "a*":
		{
			List<String> list3=Cube.reconstructPath(cube.aStar(cube.getFaces()));
			for(String step : list3)
			{
				System.out.println(step);
			}
			break;
		}
		}
		}
	}
}
