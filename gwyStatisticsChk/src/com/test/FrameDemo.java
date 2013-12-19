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
                    showDialog();  //��ʾ����
            }
        });

        exitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0); //�رմ���
            }
        });

        mainPanel = new JPanel();
        mainPanel.add(loginButton);
        mainPanel.add(exitButton);
        this.add(mainPanel); //���������ӵ�frame��

    }

/**
     * ��ʾ�Ի���
     */
    private void showDialog(){
//        this.setVisible(false);
         dialog  = new JDialog(this, true);
         dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
         dialog.setSize(300,180);
         dialog.setTitle("DialogTest");
         dialog.add(new JLabel("����ǶԻ���"));
         dialog.setLocationRelativeTo(this);
         dialog.setVisible(true);  //��ʾ�Ի��򣬴���������������ִ�У�ֻ�еȵ��Ի���ر��˲�����ִ�С�

         //�ж��������Ƿ������صģ���������صľ���ʾ
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