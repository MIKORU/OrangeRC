package Document;

import java.io.File;

import javax.swing.JFileChooser;

public class selectFile {
	public File select() {
		// �����ļ�ѡ����
		JFileChooser jf = new JFileChooser();
		//��ʱ��ʾ�ļ���Ŀ¼
		jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		//���������ļ���ѡ���            	
		jf.showOpenDialog(null);
		//����ѡ�е��ļ�            	
		String filePath = jf.getSelectedFile().getPath();

		File fi = new File(filePath);
		return fi;
	}
}
