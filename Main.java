/*
 * Semana N°10
 * 
 * Algoritmo para posicionar NReinas sin que se ataquen
 * Algoritmos que encuentra las x soluciones en un tablero de ajedrez
 * Fuerza Bruta (FB) y Backtracking(BT)
 */

class Main {

	static int num_sol_bt = 0;
	static int num_sol_fb = 0;
	
	public static void main(String[] args) {
		// Test 30 items
		int time_test = 10;
		testTimeBT(time_test);
		testTimeFB(time_test);
	}
	
	// Prueba de tiempo Fuerza Bruta
	public static void testTimeFB(int n) {
		System.out.println("Fuerza Bruta - N Reinas");
		for (int i = 2; i <= n; i++) {
			long time = System.nanoTime();
			nQueensFB(i);
			time = System.nanoTime() - time;
			System.out.println("Tamaño: " + i + "\t" + "Tiempo: " + time+ "\t" + "N° Soluciones: " + num_sol_fb);
			num_sol_fb = 0;
		}
	}
	
	// Prueba de tiempo BackTracking
	public static void testTimeBT(int n) {
		System.out.println("BackTracking iterativo - N Reinas");
		for (int i = 2; i <= n; i++) {
			long time = System.nanoTime();
			nQueens(i);
			time = System.nanoTime() - time;
			System.out.println("Tamaño: " + i + "\t" + "Tiempo: " + time + "\t" + "N° Soluciones: " + num_sol_bt);
			num_sol_bt = 0;
		}
	}
	/*
	 * Problema de las N-Reinas Backtracking
	 * Posicionar en un tablero nxn, n reinas
	 * de tal manera que ninguna pueda atacarse
	 */
	public static void nQueens(int n) {

		// Arreglo para la posicion de las reinas
		// Siendo el indice del array su columna
		// y el valor su fila
		int [] chessBoard = new int[n];

		// -1 Significa que no hay una reina ocupando ese espacio
		for (int i = 0; i < chessBoard.length; i++) {
			chessBoard[i] = -1;
		}

		// Algoritmo para posicionar las n reinas
		nQueensIni(0, chessBoard);
	}
	
	public static void nQueensIni(int pos, int [] chessBoard) {

		int column_index = 0;
		int row_index = 0;

		while(column_index < chessBoard.length) {
			
			// Cuando la columna se queda sin solucion retrocede
			if(row_index == chessBoard.length) {
				
				chessBoard[column_index] = -1;
				column_index--;
				
				// Se ha quedado sin solucion, termina
				if(column_index == -1)
					break;
	
				row_index = chessBoard[column_index] + 1;
			}else {
				// Asigna una fila a la columna
				chessBoard[column_index] = row_index;

				// Verifica la asignacion de la fila en la columna
				if(verificar(column_index, chessBoard)) {
					if(!(column_index == chessBoard.length - 1)) {
						row_index = 0;
						column_index++;
					}else {
						// Aqui esta a punto de traspasar el column index, eso significa que hallo la solucion
						num_sol_bt++;
						row_index++;
					}
				}else {
					row_index++;
				}
			}
		}
	}

	// Verifica la posicion de la reinas anteriores
	public static boolean verificar(int pos, int [] chessBoard) {
		// Donde el indice representa la columna y el valor la fila

		// Primeramente los horizontales
		for(int i = pos-1; i >= 0; i--) {
			
			// Verifica horizontalmente
			if(chessBoard[i] == chessBoard[pos])
				return false;

			// Verifica la diagonal superior
			if(chessBoard[i] == chessBoard[pos] - (pos - i))
				return false;

			// Verifica la diagonal inferior
			if(chessBoard[i] == chessBoard[pos] + (pos - i))
				return false;

		}

		return true;
	}

	
	
	// N-reinas fuerza bruta
	public static void nQueensFB(int n) {
		int [] chessBoard = new int [n];
		for (int i = chessBoard.length - 1; i >= 0; i--) {
			
			// Al haberse iterado las soluciones a la derecha es necesario mover a 1
			if(i != chessBoard.length - 1)
				chessBoard[i] = 1;
			
			permutation(i, chessBoard);
		}
	}
	
	// Pos señala la columna en la que se esta iterando
	// Cuando la iteracion llegue a la ultima columna se verifica la permutacion realizada
	private static void permutation(int pos, int [] chessBoard) {
		for(int i = chessBoard[pos]; i < chessBoard.length; i++) {
			// Es el ultimo bloque, el que define si es verificable o no
			
			if(pos == chessBoard.length - 1) {
				if (checkPermutation(chessBoard)) {
					num_sol_fb++;
				}
				
			}else {
				//Continua la permutacion en la siguiente columna
				permutation(pos + 1, chessBoard);
			}
			chessBoard[pos] = chessBoard[pos] + 1;
		}
		
		// Cuando una columna termina de iterarse se reinicia
		chessBoard[pos] = 0;
	}

	private static boolean checkPermutation(int [] chessBoard) {
		for (int i = 1; i < chessBoard.length; i++) {
			if(!verificar(i, chessBoard)) {
				return false;
			}
		}
		return true;
	}
	
	public static void showChessBoard(int [] chessBoard) {
		System.out.println();
		for (int i = 0; i < chessBoard.length; i++) {
			for (int j = 0; j < chessBoard.length; j++)
				if(chessBoard[j] == i)
					System.out.print("|X|");
				else
					System.out.print("| |");
			System.out.println();
		}
	}
	

}