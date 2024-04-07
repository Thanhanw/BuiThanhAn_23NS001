package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URLConnection;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import controller.controller;
import model.fileList;

public class view extends JFrame {
	private StringBuilder fullPath;
	private static JTextArea jTextArea;

	public view() {
		this.init();
		
	}

	private void init() {
		this.setTitle("text editor");
		this.setSize(900, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		Font font= new Font("Arial", Font.BOLD, 20);
		
		File file= new File("D:\\IT");
		DefaultMutableTreeNode root= new DefaultMutableTreeNode("D:\\IT");
		root= fileList.buidTreeFile(root, file);
		JTree tree= new JTree(root);
		
		fullPath= new StringBuilder();
		tree.addTreeSelectionListener(e->{
			fullPath=controller.getFullpath(e);
//			String mimeFile= URLConnection.guessContentTypeFromName(fullPath.toString());
		});
		tree.addMouseListener(new MouseAdapter() {
			private StringBuilder content;

			public void mouseClicked(MouseEvent e) {
				controller.loadFile(e, fullPath);
			};
		});
		
		JScrollPane pane= new JScrollPane(tree);
		
		jTextArea= new JTextArea();
		JScrollPane pane2= new JScrollPane(jTextArea);
		
		JSplitPane jSplitPane= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,pane,pane2);
		jSplitPane.setResizeWeight(0.3);
		
		JButton jButton_luu= new JButton("Lưu");
		jButton_luu.setFont(font);
		jButton_luu.addActionListener(e-> controller.saveFile(fullPath));
		
		this.add(jSplitPane, BorderLayout.CENTER);
		this.add(jButton_luu,BorderLayout.NORTH);
		this.setVisible(true);
	}
	public static void setText(String str){
		jTextArea.setText(str);
	}
	public static String getText(){
		return jTextArea.getText();
	}
}
