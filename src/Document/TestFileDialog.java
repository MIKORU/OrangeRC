//package Document;
//
//import java.awt.FileDialog;
//import java.awt.event.*;
//import java.io.*;
//import java.io.File;
//import java.io.FileReader;
//
//public class TestFileDialog {
//	private FileDialog filedialog_open;
//	private String fileopen = null, filename = null;// 用于存放打开文件地址 和文件名
//	private File file1; // 文件字节流对象
//	private FileReader file_reader;//文件字符流对象
//	private BufferedReader in;//文件行读取 写入对象
//	private StringBuffer text = new StringBuffer();
//
//	HaffmanFrame haffman= null;// 需要改动的地方（改成要调用的类）
//	TestFileDialog(HaffmanFrame hf) {//相应的改一下类名HaffmanFrame
//		haffman = hf;
//		filedialog_open = new FileDialog(haffman, "打开文件对话框", FileDialog.LOAD);
//		// 打开文件对话框适配器
//		filedialog_open.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				filedialog_open.setVisible(false);
//			}
//		});
//	}
//
//	public void open() {
//		String s = ""; 
//		filedialog_open.setVisible(true);
//		fileopen = filedialog_open.getDirectory();// 返回文件对话框中显示的文件所属的目录
//		filename = filedialog_open.getFile();// 返回当前文件对话框中显示的文件名的字符串表示
//		// 如果不存在就返回NULL
//		if (filename != null)// 判断打开的文件是否存在
//		{
//			try {
//				file1 = new File(fileopen,filename );
//				file_reader = new FileReader(file1);
//				in = new BufferedReader(file_reader);//每次读取一行
//				while ((s = in.readLine()) != null)
//					text.append(s + '\n');
//				in.close();
//				file_reader.close();
//			} catch (IOException e2) {
//				System.out.println("不能打开文件！");
//			}
//		}
//	}
//	//返回得到的文本字符串
//	public String getText() {
//		return new String(text);
//	}
//}
//
