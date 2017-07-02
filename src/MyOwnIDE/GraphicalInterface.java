package MyOwnIDE;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class GraphicalInterface
{
	JFrame jf;
	JPanel jp;
	JScrollPane spCodingArea,spComplingResultArea;
	JTextArea taCodingArea,taComplingResultArea;
	JFileChooser jfc;
	int savedStatus = 0; //0-not saved, 1-saved
	GraphicalInterface(String s)
	{
		System.out.println("Hey");
		jfc = new JFileChooser(".");
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		jf = new JFrame(s);
		jp = new JPanel();
		taCodingArea = new JTextArea();
		
		taComplingResultArea = new JTextArea();
		// {
		// 	public Dimension getPreferredSize()
		// 	{
		// 		return new Dimension(5,150);
		// 	}
		// };
		//tpComplingResultArea.setSize(100,500);
		taComplingResultArea.setEditable(false);
		spCodingArea = new JScrollPane(taCodingArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		spComplingResultArea = new JScrollPane(taComplingResultArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED)
		{
			public Dimension getPreferredSize()
			{
				return new Dimension(5,150);
			}
		};
		BorderLayout bl = new BorderLayout(5,5);
		jp.setLayout(bl);
		spComplingResultArea.setBorder(BorderFactory.createTitledBorder("<HTML><B>Result/Error(s) Window</B></HTML>"));
		// spCodingArea.setLayout(bl);
		// spComplingResultArea.setLayout(bl);
		//BorderLayout bl = new BorderLayout();
		// spCodingArea.add(tpCodingArea);
		// spComplingResultArea.add(tpComplingResultArea);
		jp.add(spCodingArea,BorderLayout.CENTER);
		jp.add(spComplingResultArea,BorderLayout.SOUTH);
		// tpCodingArea.setEnabled(true);
		// tpComplingResultArea.setEnabled(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(jp,BorderLayout.CENTER);
		new MenuGUI(this);
		//jf.setSize(400,400);
		// Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  // 		jf.setSize(screenSize.width, screenSize.height);
		jf.setSize(1000,1000);
		jf.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		//jf.pack();
		//jf.setLayout(bl);
		jf.setVisible(true);

	}
	public static void main(String... args)
	{
		System.out.println("Hey");
		new GraphicalInterface("Java IDE");
	}
}