package MyOwnIDE;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuGUI
{

	GraphicalInterface gi;
	ResponseClass response;

	JMenuBar mb;
	//JMenu fileMenu,editMenu,runMenu,windowMenu,helpMenu;
	JMenu mainMenu[] = new JMenu[5];
	String[] menuNames = {"File","Edit","Run","Window","Help"};
	char[] menuMnemonics={'f','e','r','w','h'};
	//for file menu
	// JMenuItem fileNew,fileOpen,fileSave,fileSaveAs,fileExit;
	JMenuItem fileMenuItems[] = new JMenuItem[5];
	String[] fileMenuNames = {"New File","Open File","Save","Save As","Exit"};
	String[] fileMenuIconNames = {"newFile.jpg","open.jpg","save.jpg","saveAs.jpg","exit.jpg"};
	char[] fileMenuMnemonics={'n','o','s','a','x'};
	//for edit menu
	// JMenuItem editUndo,editRedo,editCut,editCopy,editPaste,editDelete,editSelectAll;
	JMenuItem editMenuItems[] = new JMenuItem[7];
	String[] editMenuNames = {"Undo","Redo","Cut","Copy","Paste","Delete Selected","Select All"};
	String[] editMenuIconNames = {"undo.jpg","redo.jpg","cut.jpg","copy.jpg","paste.jpg","delete.jpg","selectAll.jpg"};
	char[] editMenuMnemonics = {'u','r','c','o','p','t','l'};
	//for run menu
	// JMenuItem runComplile,runRun;
	JMenuItem runMenuItems[] = new JMenuItem[2];
	String[] runMenuNames = {"Compile","Run"};
	String[] runMenuIconNames = {"runCompile.jpg","runCompile.jpg"};
	char[] runMenuMnemonics = {'c','r'};
	//for window
	// JMenuItem windowNewWindow;
	JMenuItem windowMenuItems[] = new JMenuItem[1];
	String[] windowMenuNames = {"New Window"};
	String[] windowMenuIconNames = {"newWindow.jpg"};
	char[] windowMenuMnemonics = {'n'};

	MenuGUI(GraphicalInterface gi)
	{
		this.gi = gi;
		mb = new JMenuBar();
		
		response = new ResponseClass(this,gi);
		
		for(int i=0;i<mainMenu.length;i++)
		{
			mainMenu[i] = new JMenu(menuNames[i]);
			mainMenu[i].setMnemonic(menuMnemonics[i]);
			if(menuNames[i]=="File")
			{
				for(int j=0;j<fileMenuItems.length;j++)
				{
					fileMenuItems[j] = new JMenuItem(fileMenuNames[j]);
					fileMenuItems[j].setMnemonic(fileMenuMnemonics[j]);
					fileMenuItems[j].addActionListener(response);
					fileMenuItems[j].setIcon(new ImageIcon(new ImageIcon("F:/DUCAT_JAVA/MyOwnIDE/Images/" + fileMenuIconNames[j]).getImage().getScaledInstance(15,15,Image.SCALE_SMOOTH)));
					if(fileMenuNames[j] == "Open File")
					{
						KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK);
						fileMenuItems[j].setAccelerator(ks);
						mainMenu[i].add(fileMenuItems[j]);
						mainMenu[i].addSeparator();
					}
					else if(fileMenuNames[j] == "Save")
					{
						KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK);
						fileMenuItems[j].setAccelerator(ks);
						mainMenu[i].add(fileMenuItems[j]);
					}
					else if(fileMenuNames[j] == "Save As")
					{
						mainMenu[i].add(fileMenuItems[j]);
						mainMenu[i].addSeparator();
					}
					else mainMenu[i].add(fileMenuItems[j]);
					// mainMenu[i].add(fileMenuItems[j])
				}
			}
			else if(menuNames[i]=="Edit")
			{
				for(int j=0;j<editMenuItems.length;j++)
				{
					if(j==5) System.out.println("This is delete.");
					editMenuItems[j] = new JMenuItem(editMenuNames[j]);
					editMenuItems[j].setMnemonic(editMenuMnemonics[j]);
					editMenuItems[j].addActionListener(response);
					editMenuItems[j].setIcon(new ImageIcon(new ImageIcon("F:/DUCAT_JAVA/MyOwnIDE/Images/" + editMenuIconNames[j]).getImage().getScaledInstance(15,15,Image.SCALE_SMOOTH)));
					if(editMenuNames[j] == "Undo")
					{
						KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK);
						editMenuItems[j].setAccelerator(ks);
						mainMenu[i].add(editMenuItems[j]);
					}
					else if(editMenuNames[j] == "Redo")
					{
						KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_Y,ActionEvent.CTRL_MASK);
						editMenuItems[j].setAccelerator(ks);
						mainMenu[i].add(editMenuItems[j]);
						mainMenu[i].addSeparator();
					}
					else if (editMenuNames[j] == "Cut")
					{
						KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK);
						editMenuItems[j].setAccelerator(ks);
						mainMenu[i].add(editMenuItems[j]);
					}
					else if (editMenuNames[j] == "Copy")
					{
						KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK);
						editMenuItems[j].setAccelerator(ks);
						mainMenu[i].add(editMenuItems[j]);
					}
					else if (editMenuNames[j] == "Paste")
					{
						KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK);
						editMenuItems[j].setAccelerator(ks);
						mainMenu[i].add(editMenuItems[j]);
						mainMenu[i].addSeparator();
					}
					else if (editMenuNames[j] == "Select All")
					{
						KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK);
						editMenuItems[j].setAccelerator(ks);
						mainMenu[i].add(editMenuItems[j]);
					}
					else mainMenu[i].add(editMenuNames[j]);
					if(j==5) System.out.println("This is delete.");

				}
			}
			else if(menuNames[i]=="Run")
			{
				for(int j=0;j<runMenuItems.length;j++)
				{
					runMenuItems[j] = new JMenuItem(runMenuNames[j]);
					runMenuItems[j].setMnemonic(runMenuMnemonics[j]);
					runMenuItems[j].addActionListener(response);
					runMenuItems[j].setIcon(new ImageIcon(new ImageIcon("F:/DUCAT_JAVA/MyOwnIDE/Images/" + runMenuIconNames[j]).getImage().getScaledInstance(15,15,Image.SCALE_SMOOTH)));
					mainMenu[i].add(runMenuItems[j]);
				}
			}
			else if(menuNames[i]=="Window")
			{
				for(int j=0;j<windowMenuItems.length;j++)
				{
					windowMenuItems[j] = new JMenuItem(windowMenuNames[j]);
					windowMenuItems[j].setMnemonic(windowMenuMnemonics[j]);
					windowMenuItems[j].addActionListener(response);
					windowMenuItems[j].setIcon(new ImageIcon(new ImageIcon("F:/DUCAT_JAVA/MyOwnIDE/Images/" + windowMenuIconNames[j]).getImage().getScaledInstance(15,15,Image.SCALE_SMOOTH)));
					mainMenu[i].add(windowMenuItems[j]);
				}
			}

			mb.add(mainMenu[i]);
		}

		gi.jf.setJMenuBar(mb);

	}	
}