package model;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.swing.tree.DefaultMutableTreeNode;

public class fileList {
	public static DefaultMutableTreeNode buidTreeFile(DefaultMutableTreeNode root, File director) {
		File[] list= director.listFiles();
		if(list != null) {
			Arrays.stream(list).forEach(file -> {
				DefaultMutableTreeNode node= new DefaultMutableTreeNode(file.getName());
				root.add(node);
				if(file.isDirectory()) {
					buidTreeFile(node, file);
				}
			});
		}
		return root;
	}
}
