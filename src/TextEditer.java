import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class TextEditer extends JFrame {
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	public JTextArea textArea;
	private JMenuBar menubar;
	private TextEditer editer;
	public Font font;
	private Color fgColor, bgColor;
	private JMenu menuFile, menuEdit;
	private JMenuItem menuFileNew, menuFileOpen, menuFileSave, menuFileExit;
	private JMenuItem menuEditFont, menuEditFgColor, menuEditBgColor;

	public TextEditer() {
		setDefault();
		setMenubar();
		setListener();
	}

	private void setDefault() {
		editer = this;
		this.setBounds(100, 100, 100, 100);
		this.setSize(720, 540);
		this.setLocationRelativeTo(null);// ����
		this.setTitle("�ı��༭��");
		font = new Font("����", Font.PLAIN, 28);
		textArea = new JTextArea();
		textArea.setFont(font);
		fgColor = new Color(0, 0, 0);
		bgColor = new Color(255, 255, 255);
		textArea.setForeground(fgColor);
		textArea.setBackground(bgColor);
		textArea.setLineWrap(true);// �����߽��Զ�����
		scrollPane = new JScrollPane(textArea);// ���������
		this.add(scrollPane);

	}

	private void setMenubar() {
		menubar = new JMenuBar();
		menuFile = new JMenu("�ļ�(F)");
		menuEdit = new JMenu("�༭(E)");
		menubar.add(menuFile);
		menubar.add(menuEdit);

		menuFileNew = new JMenuItem("�½�(N)");
		menuFileOpen = new JMenuItem("��(O)");
		menuFileSave = new JMenuItem("����(S)");
		menuFileExit = new JMenuItem("�˳�(X)");
		menuFile.add(menuFileNew);
		menuFile.add(menuFileOpen);
		menuFile.add(menuFileSave);
		menuFile.add(menuFileExit);
		// ����alt+X��ݼ�
		menuFile.setMnemonic('F');
		menuFileNew.setMnemonic('N');
		menuFileOpen.setMnemonic('O');
		menuFileSave.setMnemonic('S');
		menuFileExit.setMnemonic('X');
		// ����ctrl+X��ݼ�
		menuFileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		menuFileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		menuFileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));

		menuEditFont = new JMenuItem("����(T)");
		menuEditFgColor = new JMenuItem("ǰ��ɫ(F)");
		menuEditBgColor = new JMenuItem("����ɫ(B)");
		menuEdit.add(menuEditFont);
		menuEdit.add(menuEditFgColor);
		menuEdit.add(menuEditBgColor);
		menuEdit.setMnemonic('E');
		menuEditFont.setMnemonic('T');
		menuEditFgColor.setMnemonic('F');
		menuEditBgColor.setMnemonic('B');

		this.setJMenuBar(menubar);
	}

	private void setListener() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// �����ı����壬����֮ǰд��FontChooser��
		menuEditFont.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FontChooser.showDialog(editer, "ѡ������", true, textArea.getFont());
				textArea.setFont(FontChooser.getResFont());
			}
		});

		// �����ı�ǰ��ɫ
		menuEditFgColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(editer, "ѡ��ǰ��ɫ", textArea.getForeground());
				textArea.setForeground(color);
			}
		});

		// �����ı�����ɫ
		menuEditBgColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(editer, "ѡ�񱳾�ɫ", textArea.getBackground());
				textArea.setBackground(color);
			}
		});

		// ����ı���
		menuFileNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});

		// ѡ����ļ�
		menuFileOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(editer);
				File file = chooser.getSelectedFile();
				if (file == null)
					return;
				BufferedReader reader;
				try {
					reader = new BufferedReader(new FileReader(file));
					String line = "";
					String ans = "";
					while ((line = reader.readLine()) != null) {
						ans += line;
					}
					textArea.setText(ans);
					reader.close();
				} catch (IOException e1) {
					System.out.println("File open failure.");
					e1.printStackTrace();
				}
			}
		});

		// �����ļ�
		menuFileSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(editer);
				File file = chooser.getSelectedFile();
				if (file == null)
					return;
				FileWriter writer;
				try {
					writer = new FileWriter(file);
					writer.write(textArea.getText());
					writer.flush();
					writer.close();
				} catch (IOException e1) {
					System.out.println("File open failure.");
					e1.printStackTrace();
				}
			}
		});

		// �˳�
		menuFileExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}

	public static void main(String[] args) {
		TextEditer editer = new TextEditer();
		editer.setVisible(true);
	}

}
