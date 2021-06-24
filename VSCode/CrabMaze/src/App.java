//Arthur.M@edu.pucrs.br && Luiz.Porto01@edu.pucrs.br
//Trabalho Alest II
import java.util.*;

public class App {

	public static void main(String[] args) {

		int[] arrayDist = new int[12];
		arrayDist[0] = new Maze("./50_50.txt").BFS();
		arrayDist[1] = new Maze("./75_75.txt").BFS();
		arrayDist[2] = new Maze("./75_75_1.txt").BFS();
		arrayDist[3] = new Maze("./75_75_2.txt").BFS();
		arrayDist[4] = new Maze("./75_75_3.txt").BFS();
		arrayDist[5] = new Maze("./75_75_4.txt").BFS();
		arrayDist[6] = new Maze("./75_75_5.txt").BFS();
		arrayDist[7] = new Maze("./100_50.txt").BFS();
		arrayDist[8] = new Maze("./100_100.txt").BFS();
		arrayDist[9] = new Maze("./250_250.txt").BFS();
		arrayDist[10] = new Maze("./640_480.txt").BFS();
		arrayDist[11] = new Maze("./1000_1000.txt").BFS();

		for (int i = 0; i < 12; i++) {
			int dist = arrayDist[i];
			if (dist != -1) {
				System.out.println("O menor caminho do caranguejo no "+ (i+1) +"º labirinto é de " + dist + " movimentos");
			} else {
				System.out.println("O caranguejo não conseguiu sair do " + (i+1) +"º labirinto!");
			}
		}
	}
}