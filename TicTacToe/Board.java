package TicTacToe;

import java.awt.Graphics;// thu vien do thi
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JOptionPane;
import javax.swing.JPanel;//
import javax.imageio.ImageIO;

//vẽ ranh giới 3*3 
public class Board extends JPanel {// lớp board kế thừa jpanel
    private char board[][];
    private Image imgO, imgX;
    private int n = 3, m = 3;
    private boolean isXturn = true;

    public Board() {
        board = new char[n][m];
        Loadimage();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                // tinh toan toa do nhap chuot
                int row = y / (getHeight() / 3);
                int col = x / (getWidth() / 3);

                if (board[row][col] == '\0') {
                    board[row][col] = isXturn ? 'X' : 'O';
                    repaint();// ve lai bang
                    if (checkWin((isXturn) ? 'X' : 'O')) {
                        showWindialog(isXturn ? 'X' : 'O');
                    }

                }
                isXturn = !isXturn;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int Width = getWidth();// rong
        int Height = getHeight();// cao
        // vẽ 2 đuowngf dọc
        g.drawLine(Width / n, 0, Width / n, Height);
        g.drawLine(2 * Width / n, 0, 2 * Width / n, Height);
        // vẽ 2 đường ngang
        g.drawLine(0, Height / m, Width, Height / m);
        g.drawLine(0, 2 * Height / m, Width, 2 * Height / m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'X')
                    g.drawImage(imgX, j * (Width / m), i * (Height / n), Width / m, Height / n, this);
                else if (board[i][j] == 'O')
                    g.drawImage(imgO, j * (Width / m), i * (Height / n), Width / m, Height / n, this);
            }
        }
    }

    private void Loadimage() {
        try {
            imgX = ImageIO.read(getClass().getResource("X.png"));
            imgO = ImageIO.read(getClass().getResource("O.png"));
        } catch (Exception e) {
            e.printStackTrace(); // TODO: handle exception
        }

    }

    public void showWindialog(char player) {
        int option = JOptionPane.showConfirmDialog(
                Board.this,
                player + "WIN! Do you want reset the game?", "Game over", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            resetBord();
        }
    }

    public void resetBord() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = '\0';
            }
        }
        repaint();// ve lai bang
    }

    private boolean checkWin(char player) {
        // kiem tra hang
        for (int i = 0; i < n; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        // kiem tra cot
        for (int i = 0; i < n; i++) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        // kiem tra duong cheo
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }
}