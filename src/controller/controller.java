package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URLConnection;
import java.util.Arrays;

import javax.swing.JPopupMenu.Separator;
import javax.swing.event.TreeSelectionEvent;

import view.view;

public class controller {
	private static StringBuilder content;
	
	public static StringBuilder getFullpath(TreeSelectionEvent event) {
		StringBuilder fullPath= new StringBuilder();
		Object[]nodes=event.getPath().getPath();
		Arrays.stream(nodes).forEach(node ->{
			fullPath.append(node);
			fullPath.append(File.separator);
		});
		fullPath.deleteCharAt(fullPath.length()-1);
		return fullPath;
	}
	public static void loadFile(MouseEvent e, StringBuilder fullPath) {
		if(e.getClickCount()==2) {
			String mime= URLConnection.guessContentTypeFromName(fullPath.toString());
			boolean co=false;
			if(mime!=null&&mime.startsWith("text")) {
				co=true;
			}

			File file= new File(fullPath.toString());
			String extension=getFileExtension(file);
			
				if(extension.compareTo("cpp")==0||extension=="html"||co) {
					File filetest= new File(fullPath.toString());
					if(filetest.isFile()) {
						try(BufferedReader read= new BufferedReader(new FileReader(fullPath.toString()))){
							content= new StringBuilder();
							String line;
							while((line=read.readLine())!=null) {
								content.append(line);
								content.append("\n");
								view.setText(content.toString());
							}
						}catch(Exception exception) {
							exception.printStackTrace();
						}
					}
				}else {
					view.setText("File không mở được");
					
				}
		}
		
	}
	public static void saveFile(StringBuilder fullPath) {
		try (BufferedWriter write= new BufferedWriter(new FileWriter(fullPath.toString()))){
			write.write(view.getText());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	public static String getFileExtension(File file) {
		String extension="";
		if(file!=null) {
			String name=file.getName();
			int lastdot= name.lastIndexOf('.');
			if(lastdot>0 && lastdot<name.length()-1) {
				extension=name.substring(lastdot+1);
			}
		}
		return extension;
	}
}
