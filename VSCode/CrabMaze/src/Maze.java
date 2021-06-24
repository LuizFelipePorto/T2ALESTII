//Arthur.M@edu.pucrs.br && Luiz.Porto01@edu.pucrs.br
//Trabalho Alest II

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Maze {

	private static int sizeX;
	private static int sizeY;
	private static Point crab;
	private static Point wayOut;
	private static String maze[][];

	public Maze(String path) {

		ArrayList<String> lines = new ArrayList<String>();

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();
			while (line != null) {
				lines.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		maze = parse(lines);
	}

	private static String[][] parse(ArrayList<String> lines) {

		sizeX = lines.get(0).length();
		sizeY = lines.size();

		maze = new String[sizeY][sizeX];

		for (int i = 0; i < lines.size(); i++) {

			String line = lines.get(i);

			// separa ela em um array usando o " " como par�metro
			String[] split = line.split("");

			for (int j = 0; j < sizeX; j++) {
				
				// guarda o conte�do do array dentro da matrix
				maze[i][j] = split[j];

				// verifica aonde esta o caranguejo
				if (split[j].equals("C")) {
					crab = new Point(i, j);
				}

				// verifica aonde esta a saida
				if (split[j].equals("S")) {
					wayOut = new Point(i, j);
				}
			}
		}
		return maze;
	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	};

	static class queueNode {
		Point pt; // coordenadas da celula
		int dist; // distancia da celula ate a saida

		public queueNode(Point pt, int dist) {
			this.pt = pt;
			this.dist = dist;
		}
	};
	
	// verifica se dada c�lula � v�lida ou n�o 
	static boolean isValid(int row, int col) {
		// retorna verdadeiro se o numero da fileira e o da coluna esta no escopo
		return (row >= 0) && (row < sizeY) && (col >= 0) && (col < sizeX);
	}

	// Esses arrays servem como refer�ncia das 8 dire��es que o caranguejo pode tomar
	static int rowNum[] = { -2, -1, 0, 1, 0, -1, 2, 1 };
	static int colNum[] = { 0, 1, -2, 1, 2, -1, 0, -1 };
	
	//fun��o para achar o caminho mais curto entre o caranguejo e a sa�da
	public int BFS() {
		boolean[][] visited = new boolean[sizeX][sizeY];

		// Marca a c�lula inicial como visitada
		visited[crab.x][crab.y] = true;

		// Cria uma fila para o Breadth First Search
		Queue<queueNode> q = new LinkedList<>();

		// Distancia da c�lula inicial � 0
		queueNode s = new queueNode(crab, 0);
		q.add(s); // Enqueue source cell

		// Faz um Breadth First Search(BFS) a partir da c�lula inicial (carangueijo)
		while (!q.isEmpty()) {

			queueNode curr = q.peek();
			Point pt = curr.pt;

			//Se chegamos na posi��o do S, chegamos ao destino!
			if (pt.x == wayOut.x && pt.y == wayOut.y) {
				return curr.dist;
			}
			// Se n�o desenfileiramos a c�lula frontal da fila e enfileiramos as c�lulas adjacentes
			q.remove();

			for (int i = 0; i < 8; i++) {
				int row = pt.x + rowNum[i];
				int col = pt.y + colNum[i];

				// se a c�lula adjacente for v�lida e ainda n�o foi visitada, enfileira-la.
				if ((isValid(col, row) && maze[col][row].equals(".") && visited[row][col] != true)
						|| (row == wayOut.x && col == wayOut.y)) {
					// marca a c�lula como visitada e enfileira ela
					visited[row][col] = true;
					queueNode Adjcell = new queueNode(new Point(row, col), curr.dist + 1);
					q.add(Adjcell);

				}
			}
		}

		// retorna 1 caso n�o encontre resultado
		return -1;
	}
}
