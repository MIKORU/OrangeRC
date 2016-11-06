package Document;

import java.io.File;

import javax.swing.JFileChooser;

public class selectFile {
	public File select() {
		// 创建文件选择器
		JFileChooser jf = new JFileChooser();
		//打开时显示文件和目录
		jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		//弹出“打开文件”选择框            	
		jf.showOpenDialog(null);
		//返回选中的文件            	
		String filePath = jf.getSelectedFile().getPath();

		File fi = new File(filePath);
		return fi;
	}
}
