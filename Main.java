class Main {

	public static void main(String[] args) {
		nQueens(4);
	}

	/*
	 * Problema de las N-Reinas
	 * Posicionar en un tablero nxn, n reinas
	 * de tal manera que ninguna pueda atacarse
	 * 
	 */
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
					row_index = 0;
					column_index++;
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
			// Si las columnas anteriores cuentan con la misma fila no se puede colocar ahi la reina
			if(chessBoard[i] == chessBoard[pos])
				return false;

			if(chessBoard[i] == chessBoard[pos] - (pos - i))
				return false;

			if(chessBoard[i] == chessBoard[pos] + (pos - i))
				return false;

		}

		return true;
	}

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

		// Muestra las posiciones de cada reina
		for (int i = 0; i < chessBoard.length; i++) {
			System.out.print(chessBoard[i]);  
			if(i != chessBoard.length - 1) {
				System.out.print(", ");
			}
		}
		
		// Muestra las reinas posicionadas en el tablero
		showChessBoard(chessBoard);
	}
	
	public static void showChessBoard(int [] chessBoard) {
		System.out.println();
		for (int i = 0; i < chessBoard.length; i++) {
			for (int j = 0; j < chessBoard.length; j++)
				if(chessBoard[j] == i)
					System.out.print("|X|");
				else
					System.out.print("|-|");
			System.out.println();
		}
	}
}