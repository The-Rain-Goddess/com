package com.rain.checkers;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CheckersApp extends Application {

//public variables
	public static final int TILE_SIZE = 100;
	public static final int WIDTH = 8;
	public static final int HEIGHT = 8;

	private Group tileGroup = new Group();
	private Group pieceGroup = new Group();

	private Tile[][] board = new Tile[WIDTH][HEIGHT]; // the acutal game board

///creation
	private Parent createContent(){
		Pane root = new Pane();
		root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
		root.getChildren().addAll(tileGroup, pieceGroup);

		//this sets up the board and creats the pieces
		for( int y = 0; y < HEIGHT; y++){
			for(int x = 0; x < WIDTH; x++){
				Tile tile = new Tile((x + y) % 2 == 0, x, y);
				board[x][y] = tile;

				tileGroup.getChildren().add(tile);

				Piece piece = null;

				if(y <= 2 && (x + y) % 2 != 0){
					piece = makePiece(PieceType.RED, x, y);
				}

				if(y >= 5 && (x + y) % 2 != 0){
					piece = makePiece(PieceType.WHITE, x, y);
				}

				if(piece != null){
					tile.setPiece(piece);
					pieceGroup.getChildren().add(piece);
				}
			}
		} // end of baord creation

		return root;
	}

//determines weather what result a move will give
	private MoveResult tryMove(Piece piece, int newX, int newY){
		if(board[newX][newY].hasPiece() || (newX + newY) % 2 == 0){
			return new MoveResult(MoveType.NONE);
		}

		int x0 = toBoard(piece.getOldX());
		int y0 = toBoard(piece.getOldY());

		if(Math.abs(newX - x0) == 1 && newY - y0 == piece.getType().moveDir){
			return new MoveResult(MoveType.NORMAL);
		} else if(Math.abs(newX - x0) == 2 && newY - y0 == piece.getType().moveDir * 2){
			int x1 = x0 + (newX - x0) / 2;
			int y1 = y0 + (newY - y0) / 2;

			if(board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()){
				return new MoveResult(MoveType.KILL, board[x1][y1].getPiece());
			}
		}
		return new MoveResult(MoveType.NONE);
	}

//changes board position to pixel position
	private int toBoard(double pixel){
		return (int) (pixel + TILE_SIZE / 2) /TILE_SIZE;
	}

//creates each piece and handles mouse release
	private Piece makePiece(PieceType type, int x, int y){
		Piece piece = new Piece(type, x , y);

		piece.setOnMouseReleased(e -> {
			int newX = toBoard(piece.getLayoutX());
			int newY = toBoard(piece.getLayoutY());

			MoveResult result = tryMove(piece, newX, newY);

			int x0 = toBoard(piece.getOldX());
			int y0 = toBoard(piece.getOldY());

			switch(result.getType()) {
				case NONE:
					piece.abortMove();
					break;
				case NORMAL:
					piece.move(newX, newY);
					board[x0][y0].setPiece(null);
					board[newX][newY].setPiece(piece);
					break;
				case KILL:
					piece.move(newX, newY);
					board[x0][y0].setPiece(null);
					board[newX][newY].setPiece(piece);

					Piece otherPiece = result.getPiece();
					board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
					pieceGroup.getChildren().remove(otherPiece);
					break;
			}
		});	return piece;
	}

//main methods
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(createContent());
		primaryStage.setTitle("CheckersApp");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args)
	{launch(args);}


}
