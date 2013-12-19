package com.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author guan
 */
public class FrameDemo extends JFrame {

    JButton loginButton =null;
    JButton exitButton = null;
    JPanel mainPanel =null;
    JDialog dialog =null;

public FrameDemo() {
        loginButton = new JButton("Login");
        exitButton = new JButton("Exit");

        loginButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                    showDialog();  //显示窗口
            }
        });

        exitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0); //关闭窗口
            }
        });

        mainPanel = new JPanel();
        mainPanel.add(loginButton);
        mainPanel.add(exitButton);
        this.add(mainPanel); //将主面板添加到frame中

    }

/**
     * 显示对话框
     */
    private void showDialog(){
//        this.setVisible(false);
         dialog  = new JDialog(this, true);
         dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
         dialog.setSize(300,180);
         dialog.setTitle("DialogTest");
         dialog.add(new JLabel("这个是对话框"));
         dialog.setLocationRelativeTo(this);
         dialog.setVisible(true);  //显示对话框，窗口阻塞，不往下执行，只有等到对话框关闭了才往下执行。

         //判断主窗口是否是隐藏的，如果是隐藏的就显示
         if (!this.isVisible()) {
            this.setVisible(true);
        }
         
    }

    public static void main(String[] args) {
        JFrame frame = new FrameDemo();
        frame.setTitle("JFrame Demo");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}