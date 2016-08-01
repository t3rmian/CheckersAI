/* 
 * Copyright 2015 Damian Terlecki.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package checkers.model.checker;

import checkers.model.Board;
import checkers.model.Move;
import checkers.model.Turn;
import checkers.model.ai.EvaluatorConfig;
import java.awt.Image;
import java.awt.Point;
import java.util.List;
import javax.swing.ImageIcon;

public class RedKing extends RedPawn {

    private static ImageIcon image;

    public static void loadImage(int width, int height) {
        image = new ImageIcon(new ImageIcon(RedKing.class.getResource("/images/redKing.png")).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    public RedKing(Point point) {
        super(point);
    }

    @Override
    public List<Move> generateForcedMoves(Board board) {
        List<Move> moves = super.generateForcedMoves(board);
        Move move = upRightCapture(board);
        if (move != null) {
            moves.add(move);
        }
        move = upLeftCapture(board);
        if (move != null) {
            moves.add(move);
        }
        return moves;
    }

    @Override
    public List<Move> generateNonForcedMoves(Board board) {
        List<Move> moves = super.generateNonForcedMoves(board);
        Move move = upLeft(board);
        if (move != null) {
            moves.add(move);
        }
        move = upRight(board);
        if (move != null) {
            moves.add(move);
        }
        return moves;
    }

    public Move upLeft(Board board) {
        Move upLeft = null;
        if (this.coordinates.y > 0 && this.coordinates.x > 0
                && !board.checkers[this.coordinates.y - 1][this.coordinates.x - 1].isCollidable()) {
            upLeft = new Move(new Point(coordinates), new Point(this.coordinates.x - 1, this.coordinates.y - 1));
        }
        return upLeft;
    }

    public Move upRight(Board board) {
        Move upRight = null;
        if (this.coordinates.y > 0 && this.coordinates.x < board.getLength() - 1
                && !board.checkers[this.coordinates.y - 1][this.coordinates.x + 1].isCollidable()) {
            upRight = new Move(new Point(coordinates), new Point(this.coordinates.x + 1, this.coordinates.y - 1));
        }
        return upRight;
    }

    public Move upLeftCapture(Board board) {
        Move upLeftCapture = null;

        if (this.coordinates.y > 1 && this.coordinates.x > 1
                && isEnemy(board.checkers[this.coordinates.y - 1][this.coordinates.x - 1])
                && !board.checkers[this.coordinates.y - 2][this.coordinates.x - 2].isCollidable()) {
            upLeftCapture = new Move(new Point(coordinates), new Point(this.coordinates.x - 2, this.coordinates.y - 2));
        }
        return upLeftCapture;
    }

    public Move upRightCapture(Board board) {
        Move upRightCapture = null;

        if (this.coordinates.y > 1 && this.coordinates.x < board.getLength() - 2
                && isEnemy(board.checkers[this.coordinates.y - 1][this.coordinates.x + 1])
                && !board.checkers[this.coordinates.y - 2][this.coordinates.x + 2].isCollidable()) {
            upRightCapture = new Move(new Point(coordinates), new Point(this.coordinates.x + 2, this.coordinates.y - 2));
        }
        return upRightCapture;
    }

    @Override
    public String toString() {
        return (char) 27 + "[31;40m" + "K " + (char) 27 + "[0;39;49m";
    }

    @Override
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public Checker clone() {
        return new RedKing(new Point(coordinates));
    }

    @Override
    public Checker tryPromoting(Board board) {
        return this;
    }

    @Override
    public int getValue(EvaluatorConfig config, Turn turn) {
        return (turn == RedPawn.SIDE) ? config.getRedKing() : -config.getRedKing();
    }

}
