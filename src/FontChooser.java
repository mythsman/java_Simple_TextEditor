import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FontChooser extends JDialog {

	private static final long serialVersionUID = 1L;
	private static JDialog dlg;
	private static Font font, rawFont;
	private static JPanel panel;
	private static JComboBox<String> comboBoxFont, comboBoxStyle, comboBoxSize;
	private static JLabel text;
	private static JButton button;

	// ���ⲿ�ṩ�ľ�̬����,������
	public static void showDialog(JFrame parent, String title, boolean modal, Font rawfont) {
		dlg = new JDialog(parent, title, modal);
		rawFont = rawfont;
		font = new Font(rawFont.getFontName(), rawFont.getStyle(), rawFont.getSize());
		setDefault();
		setComboBox();
		dlg.setVisible(true);
	}

	// ���ȡ�õ�����
	public static Font getResFont() {
		return font;
	}

	private static void setDefault() {
		dlg.setSize(550, 280);
		dlg.setTitle("ѡ������");
		dlg.setResizable(false);
		dlg.setLocationRelativeTo(null);
		comboBoxFont = new JComboBox<String>();
		comboBoxStyle = new JComboBox<String>();
		comboBoxSize = new JComboBox<String>();
		panel = new JPanel();
		dlg.setContentPane(panel);
		panel.setLayout(null);

		// �رմ��ڣ��򲻸�������
		dlg.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				font = rawFont;
				dlg.dispose();
			}
		});

		JPanel panelFont = new JPanel();
		panelFont.add(new JLabel("����:"));
		panelFont.add(comboBoxFont);
		panel.add(panelFont);
		panelFont.setBounds(10, 10, 150, 60);

		JPanel panelStyle = new JPanel();
		panelStyle.add(new JLabel("���:"));
		panelStyle.add(comboBoxStyle);
		panel.add(panelStyle);
		panelStyle.setBounds(10, 70, 150, 60);

		JPanel panelSize = new JPanel();
		panelSize.add(new JLabel("��С:"));
		panelSize.add(comboBoxSize);
		panel.add(panelSize);
		panelSize.setBounds(10, 130, 150, 60);

		button = new JButton("ȷ��");
		panel.add(button);
		button.setBounds(20, 190, 140, 30);
		// ��ȷ������رմ��ڣ��������
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dlg.dispose();
			}
		});

		JPanel panelText = new JPanel();
		text = new JLabel();
		text.setText("Hello World!");
		text.setFont(font);
		text.setBorder(BorderFactory.createLoweredBevelBorder());
		panelText.setLayout(new BorderLayout());
		panelText.add(text);

		panelText.setBounds(180, 30, 340, 200);
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setVerticalAlignment(SwingConstants.CENTER);
		panel.add(panelText);

	}

	private static void setComboBox() {
		// ���ϵͳ�ṩ������
		String[] fontnames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		for (int i = 0; i < fontnames.length; i++) {
			comboBoxFont.addItem(fontnames[i]);
		}
		comboBoxFont.setPreferredSize(new Dimension(100, 30));
		comboBoxFont.setSelectedItem(font.getFontName());
		comboBoxFont.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					String s = (String) arg0.getItem();
					font = new Font(s, font.getStyle(), font.getSize());
					text.setFont(font);
				}
			}
		});

		comboBoxStyle.addItem("��ͨ");
		comboBoxStyle.addItem("�Ӵ�");
		comboBoxStyle.addItem("��б");
		comboBoxStyle.addItem("�Ӵ���б");
		if (font.getStyle() == Font.BOLD) {
			comboBoxStyle.setSelectedItem("�Ӵ�");
		} else if (font.getStyle() == Font.PLAIN) {
			comboBoxStyle.setSelectedItem("��ͨ");
		} else if (font.getStyle() == Font.ITALIC) {
			comboBoxStyle.setSelectedItem("��б");
		} else {
			comboBoxStyle.setSelectedItem("�Ӵ���б");
		}

		comboBoxStyle.setPreferredSize(new Dimension(100, 30));
		comboBoxStyle.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					String s = (String) arg0.getItem();
					if (s.equals("��ͨ")) {
						font = new Font(font.getFontName(), Font.PLAIN, font.getSize());
					} else if (s.equals("�Ӵ�")) {
						font = new Font(font.getFontName(), Font.BOLD, font.getSize());
					} else if (s.equals("��б")) {
						font = new Font(font.getFontName(), Font.ITALIC, font.getSize());
					} else {
						font = new Font(font.getFontName(), Font.BOLD | Font.ITALIC, font.getSize());
					}
					text.setFont(font);
				}
			}
		});

		for (int i = 5; i <= 50; i++) {
			comboBoxSize.addItem(i + "px");
		}
		comboBoxSize.setSelectedItem(font.getSize() + "px");
		comboBoxSize.setPreferredSize(new Dimension(100, 30));
		comboBoxSize.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					String s = (String) arg0.getItem();
					s = s.substring(0, s.length() - 2);
					font = new Font(font.getFontName(), font.getStyle(), Integer.parseInt(s));
					text.setFont(font);
				}
			}
		});
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(720, 540);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		FontChooser.showDialog(frame, "title", true, new Font("����", Font.BOLD, 20));
	}
}
