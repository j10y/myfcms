package com.test;

import javax.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;//�������¼��ر�����
import java.awt.event.ActionListener;//�������¼��ر�����
import java.util.ArrayList;

public class TestXiala {
	// extends JPanel implements ActionListener
	static JFrame frame = new JFrame("");
	static JPanel p = new JPanel();
	JButton jb = new JButton("�ϴ�");
	JComboBox jc = new JComboBox();// ���������
	JFileChooser chooser;
	String choosertitle;

	public static void main(String s[]) {
		new TestXiala().Show();
	}

	public void Show() {
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result;
				chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle(choosertitle);
				System.out.println("---" + choosertitle);
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				if (chooser.showOpenDialog(p) == JFileChooser.APPROVE_OPTION) {
					// ��ȡ��ǰĿ¼
					System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
					// ���ѡ�����ļ�
					System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
				} else {
					System.out.println("No Selection ");
				}
			}
		});
		// frame.addWindowListener(new WindowAdapter() {
		// public void windowClosing(WindowEvent e) {
		// System.exit(0);
		// }
		// });
		java.util.List items = new ArrayList();
		// list�������Ĭ��ֵ
		items.add("image/png");
		items.add("image/gif");
		items.add("image/jpg");
		// ��list��������ݷŵ��������У�items.toArray()Ϊ��list����ǿתΪ���顣
		jc.setModel(new DefaultComboBoxModel(items.toArray()));
		// ���Ĭ��ֵ����������
		jc.setSelectedIndex(items.indexOf("image/gif"));
		// String[] localValue =
		System.out.println("-----++--" + jc.getSelectedIndex());// ȡ����ѡ���������ļ�����
		System.out.println("-----222++--" + jc.getSelectedItem());// ȡ���������ļ�����
		p.add(jb);
		p.add(jc);
		frame.add(p, "Center");
		frame.setSize(320, 240);
		frame.setVisible(true);
	}
}