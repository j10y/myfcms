package com.test;

import javax.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;//添加鼠标事件必备引用
import java.awt.event.ActionListener;//添加鼠标事件必备引用
import java.util.ArrayList;

public class TestXiala {
	// extends JPanel implements ActionListener
	static JFrame frame = new JFrame("");
	static JPanel p = new JPanel();
	JButton jb = new JButton("上传");
	JComboBox jc = new JComboBox();// 下拉框组件
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
					// 获取当前目录
					System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
					// 获得选定的文件
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
		// list里面添加默认值
		items.add("image/png");
		items.add("image/gif");
		items.add("image/jpg");
		// 将list里面的内容放到下拉框中，items.toArray()为将list集合强转为数组。
		jc.setModel(new DefaultComboBoxModel(items.toArray()));
		// 添加默认值在下拉框中
		jc.setSelectedIndex(items.indexOf("image/gif"));
		// String[] localValue =
		System.out.println("-----++--" + jc.getSelectedIndex());// 取得所选的下拉框，文件索引
		System.out.println("-----222++--" + jc.getSelectedItem());// 取得下拉框文件内容
		p.add(jb);
		p.add(jc);
		frame.add(p, "Center");
		frame.setSize(320, 240);
		frame.setVisible(true);
	}
}