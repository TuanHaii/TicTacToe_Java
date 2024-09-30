package TicTacToe;

import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javafx.scene.layout.Background;

//dùng để tạo cửa sổ cho ứng dụng 
import javax.swing.*;

public class Box {
    private final class ActionListenerImplementation implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
        }
    }

    private Board board;
    private JFrame jFrame;
    private JButton startButton;
    private JButton resetButton;
    private JLabel timerLabel;
    private Timer timer;// đếm thời gian
    private int timeElapsed;// thời gian đã trôi qua
    private boolean isXturn;

    public Box() {
        jFrame = new JFrame("Tic-Tac-Toe");// tạo cửa sổ game
        board = new Board();
        JPanel jPanel = new JPanel();
        // Tạo các nút bấm
        startButton = new JButton("Start");
        resetButton = new JButton("Reset");
        timerLabel = new JLabel("time: 0s");

        // phan cach cac nut bam bang JSeparator
        JSeparator jSeparator = new JSeparator();
        jSeparator.setOrientation(JSeparator.VERTICAL);// dat theo chieu ngang
        jPanel.add(jSeparator);

        // đặt layout cho jframe
        jFrame.setLayout(new BorderLayout());
        jFrame.add(board, BorderLayout.CENTER);

        // tạo panel cho các nút và bộ đếm thời gian
        jPanel.add(startButton);
        jPanel.add(resetButton);
        jPanel.add(timerLabel);

        // Thêm jpanel va jseparator phía dưới jframe
        jFrame.add(jPanel, BorderLayout.SOUTH);
        jFrame.add(jSeparator);

        // Cấu hình nút start
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isXturn = askPlayer();
                startTimer();
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        jFrame.add(board);

        jFrame.setSize(600, 600);// kích thước cửa sổ với cao, rộng
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);// đóng cửa sổ khi người dùng nhấn đóng
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);// mở cửa sổ
    }

    private boolean askPlayer() {
        int result = JOptionPane.showOptionDialog(jFrame,
                "Choose your marker:",
                "Tic Tac Toe",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[] { "X", "O" },
                "X");
        return (result == JOptionPane.YES_OPTION); // true nếu chọn "X", false nếu chọn "O"
    }

    private void startTimer() {
        // reset thoi gian da troi
        timeElapsed = 0;
        timerLabel.setText("Time: 0s");
        // tao 1 timer de cap nhat thoi gian
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeElapsed++;
                timerLabel.setText("Time: " + timeElapsed + "s");
            }
        });
        timer.start();
    }

    private void resetGame() {
        if (timer != null) {
            timer.stop();
            timeElapsed = 0;
            timerLabel.setText("Time: 0s");
            board.resetBord();
        }
    }
}
