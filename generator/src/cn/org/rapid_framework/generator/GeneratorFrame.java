package cn.org.rapid_framework.generator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * GeneratorFrame.java
 *
 * Created on __DATE__, __TIME__
 */

/**
 * 
 * @author xiacc
 */
public class GeneratorFrame extends JFrame {

	/** Creates new form GeneratorFrame */
	public GeneratorFrame() {
		initComponents();
		new Thread() {

			public void run() {
				String log = null;

				PipedInputStream pipedIS = new PipedInputStream();
				PipedOutputStream pipedOS = new PipedOutputStream();

				try {

					pipedOS.connect(pipedIS);
				} catch (IOException e) {
					System.err.println("连接失败");
					return;

				}
				PrintStream ps = new PrintStream(pipedOS);

				System.setOut(ps);
				System.setErr(ps);
				
				InputStreamReader isr = new InputStreamReader(pipedIS);
				BufferedReader br = new BufferedReader(isr);

				while (true) {
					try {
						logArea.append(br.readLine()+"\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}.start();
	}

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel7 = new javax.swing.JPanel();
		jPanel1 = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		rootDir = new javax.swing.JTextField();
		chooseDir = new javax.swing.JButton();
		jPanel6 = new javax.swing.JPanel();
		jLabel4 = new javax.swing.JLabel();
		outPutDir = new javax.swing.JTextField();
		chooseDir2 = new javax.swing.JButton();
		jPanel4 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		className = new javax.swing.JTextField();
		jPanel5 = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();
		rootDir1 = new javax.swing.JTextField();
		jPanel2 = new javax.swing.JPanel();
		generateButton = new javax.swing.JButton();
		jPanel8 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		logArea = new javax.swing.JTextArea();
		menuBar = new javax.swing.JMenuBar();
		fileMenu = new javax.swing.JMenu();
		openPro = new javax.swing.JMenuItem();
		savePro = new javax.swing.JMenuItem();
		exitMenuItem = new javax.swing.JMenuItem();
		helpMenu = new javax.swing.JMenu();
		contentsMenuItem = new javax.swing.JMenuItem();
		aboutMenuItem = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("CodeGenerator");
		setLocationByPlatform(true);
		setName("CodeGenerator");
		setResizable(false);

		jPanel7.setPreferredSize(new java.awt.Dimension(400, 200));
		jPanel7.setLayout(new java.awt.BorderLayout());

		jPanel1.setLayout(new java.awt.GridLayout(0, 1));

		jPanel3.setMinimumSize(new java.awt.Dimension(0, 0));
		jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		jLabel1.setText("RootDir:");
		jLabel1.setPreferredSize(new java.awt.Dimension(100, 14));
		jPanel3.add(jLabel1);

		rootDir.setColumns(25);
		jPanel3.add(rootDir);

		chooseDir.setText("Choose");
		chooseDir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				chooseDirActionPerformed(evt);
			}
		});
		jPanel3.add(chooseDir);

		jPanel1.add(jPanel3);

		jPanel6.setMinimumSize(new java.awt.Dimension(0, 0));
		jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		jLabel4.setText("OutPutDir:");
		jLabel4.setPreferredSize(new java.awt.Dimension(100, 14));
		jPanel6.add(jLabel4);

		outPutDir.setColumns(25);
		jPanel6.add(outPutDir);

		chooseDir2.setText("Choose");
		chooseDir2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				chooseDir2ActionPerformed(evt);
			}
		});
		jPanel6.add(chooseDir2);

		jPanel1.add(jPanel6);

		jPanel4.setMinimumSize(new java.awt.Dimension(0, 0));
		jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		jLabel2.setText("ClassName:");
		jLabel2.setPreferredSize(new java.awt.Dimension(100, 14));
		jPanel4.add(jLabel2);

		className.setColumns(25);
		jPanel4.add(className);

		jPanel1.add(jPanel4);

		jPanel5.setMinimumSize(new java.awt.Dimension(0, 0));
		jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		jLabel3.setText("BasePackage:");
		jLabel3.setPreferredSize(new java.awt.Dimension(100, 14));
		jPanel5.add(jLabel3);

		rootDir1.setColumns(25);
		jPanel5.add(rootDir1);

		jPanel1.add(jPanel5);

		jPanel7.add(jPanel1, java.awt.BorderLayout.CENTER);

		generateButton.setText("Generate");
		generateButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				generateButtonActionPerformed(evt);
			}
		});
		jPanel2.add(generateButton);

		jPanel7.add(jPanel2, java.awt.BorderLayout.SOUTH);

		getContentPane().add(jPanel7, java.awt.BorderLayout.CENTER);

		jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("log"));
		jPanel8.setPreferredSize(new java.awt.Dimension(600, 180));
		jPanel8.setLayout(new java.awt.BorderLayout());

		logArea.setColumns(20);
		logArea.setEditable(false);
		logArea.setRows(5);
		jScrollPane1.setViewportView(logArea);

		jPanel8.add(jScrollPane1, java.awt.BorderLayout.CENTER);

		getContentPane().add(jPanel8, java.awt.BorderLayout.SOUTH);

		fileMenu.setText("File");

		openPro.setText("OpenProperty");
		openPro.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				openProActionPerformed(evt);
			}
		});
		fileMenu.add(openPro);

		savePro.setText("SaveProperty");
		savePro.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveProActionPerformed(evt);
			}
		});
		fileMenu.add(savePro);

		exitMenuItem.setText("Exit");
		exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exitMenuItemActionPerformed(evt);
			}
		});
		fileMenu.add(exitMenuItem);

		menuBar.add(fileMenu);

		helpMenu.setText("Help");

		contentsMenuItem.setText("Contents");
		helpMenu.add(contentsMenuItem);

		aboutMenuItem.setText("About");
		helpMenu.add(aboutMenuItem);

		menuBar.add(helpMenu);

		setJMenuBar(menuBar);

		pack();
	}// </editor-fold>

	// GEN-END:initComponents

	private void saveProActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Properties", "properties");

		int returnVal = chooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: "
					+ chooser.getSelectedFile().getName());
		}
	}

	private void openProActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Properties", "properties");

		int returnVal = chooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.outPutDir.setText(chooser.getSelectedFile().getPath());
		}
	}

	private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {

		for (int i = 0; i < 100; i++) {
			System.out.println(i);
		}
	}

	private void chooseDir2ActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int returnVal = chooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.outPutDir.setText(chooser.getSelectedFile().getPath());
		}
	}

	private void chooseDirActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int returnVal = chooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.rootDir.setText(chooser.getSelectedFile().getPath());
		}

	}

	private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_exitMenuItemActionPerformed
		System.exit(0);
	}// GEN-LAST:event_exitMenuItemActionPerformed

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GeneratorFrame().setVisible(true);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JMenuItem aboutMenuItem;
	private javax.swing.JButton chooseDir;
	private javax.swing.JButton chooseDir2;
	private javax.swing.JTextField className;
	private javax.swing.JMenuItem contentsMenuItem;
	private javax.swing.JMenuItem exitMenuItem;
	private javax.swing.JMenu fileMenu;
	private javax.swing.JButton generateButton;
	private javax.swing.JMenu helpMenu;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JPanel jPanel8;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea logArea;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JMenuItem openPro;
	private javax.swing.JTextField outPutDir;
	private javax.swing.JTextField rootDir;
	private javax.swing.JTextField rootDir1;
	private javax.swing.JMenuItem savePro;
	// End of variables declaration//GEN-END:variables

}