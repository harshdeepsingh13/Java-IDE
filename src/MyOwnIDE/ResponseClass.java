package MyOwnIDE;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.undo.UndoManager;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class ResponseClass implements ActionListener
{
	MenuGUI menuObject;
	GraphicalInterface guiObject;
	File code;
	Runtime runtime;
	String filename;
        UndoManager undoManager;
	
	ResponseClass(MenuGUI menuObject, GraphicalInterface guiObject)
	{
		this.menuObject = menuObject;
		this.guiObject = guiObject;
		runtime = Runtime.getRuntime();
                undoManager = new UndoManager();
                guiObject.taCodingArea.getDocument().addUndoableEditListener(undoManager);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
                
		if(e.getActionCommand().equals("New File"))
		{
			new GraphicalInterface("Java IDE");
		}
		else if(e.getActionCommand().equals("Open File"))
		{
			guiObject.jfc.setDialogType(JFileChooser.OPEN_DIALOG);
			guiObject.jfc.updateUI();
			guiObject.jfc.showOpenDialog(guiObject.jf);
			File openIt = guiObject.jfc.getSelectedFile();
			code=openIt;
			try {
				FileReader fr = new FileReader(openIt);
				BufferedReader br = new BufferedReader(fr);
				String str = "";
				while(true)
				{
					String temp = br.readLine();
					if(temp!=null)
					{
						str+=temp; str+="\n";
					}
					else break;
					
				}
				
				guiObject.savedStatus=1;
				guiObject.taCodingArea.setText(str);
				System.out.println("Done Loading");
//				JOptionPane op = new JOptionPane("The file is loaded successfully.", JOptionPane.DEFAULT_OPTION, JOptionPane.OK_OPTION);
//				op.createDialog(guiObject.jf, "Done!");
				
				JOptionPane.showMessageDialog(guiObject.jf, "The file is loaded successfully!!\n", "Done!!", JOptionPane.DEFAULT_OPTION);
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		else if(e.getActionCommand().equals("Save"))
		{
			System.out.println("Save");
			if(guiObject.savedStatus == 0)
			{
				System.out.println("not Saved");
				saveAs();
				//guiObject.savedStatus = 1;
			}
			else
			{
				System.out.println("Saved before");
				try {
					FileWriter fw = new FileWriter(code);
					PrintWriter pw = new PrintWriter(fw);
					pw.println(guiObject.taCodingArea.getText());
					pw.flush();
					pw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(e.getActionCommand().equals("Save As"))
		{
			System.out.println("Save As");
			saveAs();
			//guiObject.savedStatus=1;
		}
		else if(e.getActionCommand().equals("Exit"))
		{
			System.out.println("Hey this is Exit");
			System.exit(0);
		}
		else if(e.getActionCommand().equals("Undo"))
		{
                    if(undoManager.canUndo())
                        undoManager.undo();
		}
		else if(e.getActionCommand().equals("Redo"))
		{
			if(undoManager.canRedo())
                            undoManager.redo();
		}
		else if(e.getActionCommand().equals("Cut"))
		{
			guiObject.taCodingArea.cut();
		}
		else if(e.getActionCommand().equals("Copy"))
		{
				guiObject.taCodingArea.copy();
		}
		else if(e.getActionCommand().equals("Paste"))
		{
			guiObject.taCodingArea.paste();
		}
		else if(e.getActionCommand().equals("Delete"))
		{
			System.out.println("This is delete.");
			guiObject.taCodingArea.setText(guiObject.taCodingArea.getText().replace(guiObject.taCodingArea.getSelectedText(),""));
			
		}
		else if(e.getActionCommand().equals("Select All"))
		{
			guiObject.taCodingArea.selectAll();			
		}
		else if(e.getActionCommand().equals("Run"))
		{
			run();
		}
		else if(e.getActionCommand().equals("Compile"))
		{
                    compile();
//			String output="",runtimeError="";
//			compile();
//			System.out.println("Save");
//			if(guiObject.savedStatus == 0)
//			{
//				System.out.println("not Saved");
//				saveAs();
//				//guiObject.savedStatus = 1;
//			}
//			else
//			{
//				BufferedReader outputBuffer = new BufferedReader(new InputStreamReader(error.getInputStream()));
//				BufferedReader runtimeErrorBuffer = new BufferedReader(new InputStreamReader(error.getErrorStream()));
//				
//				JavaCompiler comp = ToolProvider.getSystemJavaCompiler();
//				int i = comp.run(System.in,outputBuffer,runtimeErrorBuffer,code.getAbsolutePath());
//				
//				//output
//				while(true)
//				{
//					String temp = outputBuffer.readLine();
//					if(temp != null)
//					{
//						output+=temp;
//						output+="\n";
//					}else break;
//				}
//				//runtimeError
//				while(true)
//				{
//					String temp = runtimeErrorBuffer.readLine();
//					if(temp != null)
//					{
//						runtimeError+=temp;
//						runtimeError+="\n";
//					}else break;
//				}
//				
//			}
//			guiObject.taComplingResultArea.setText(output + "\n" + runtimeError);
		}
		else if(e.getActionCommand().equals("New Window"))
		{
			new GraphicalInterface("Java IDE");
		}
	}
	
	void run()
	{
		int flag=compile(); //if 0 then proceed else stop
		if(flag!=0) return; 
		
		String runtimeError="", output="";
		
		try {
			String s[] = code.getName().split("\\.");
			System.out.println(s[0]);
			Process error = runtime.exec("java "+ s[0]);
			BufferedReader outputBuffer = new BufferedReader(new InputStreamReader(error.getInputStream()));
			BufferedReader runtimeErrorBuffer = new BufferedReader(new InputStreamReader(error.getErrorStream()));
			
			//output
			while(true)
			{
				String temp = outputBuffer.readLine();
				if(temp != null)
				{
					output+=temp;
					output+="\n";
				}else break;
			}
			//runtimeError
			while(true)
			{
				String temp = runtimeErrorBuffer.readLine();
				if(temp != null)
				{
					runtimeError+=temp;
					runtimeError+="\n";
				}else break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		guiObject.taComplingResultArea.setText(output + "\n" + runtimeError);
		
	}
	int compile()
	{
		System.out.println("Save");
		if(guiObject.savedStatus == 0)
		{
			System.out.println("not Saved");
			saveAs();
			//guiObject.savedStatus = 1;
		}
		else
		{
			System.out.println("Saved before");
			try {
				FileWriter fw = new FileWriter(code);
				PrintWriter pw = new PrintWriter(fw);
				pw.println(guiObject.taCodingArea.getText());
				pw.flush();
				pw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		int flag=0; String compiletimeError = "";
		try {
			Process error = runtime.exec("javac -d . "+ "\"" + code.getAbsolutePath() + "\"");
			BufferedReader compiletimeErrorBuffer = new BufferedReader(new InputStreamReader(error.getErrorStream()));
			while(true)
			{
				String temp = compiletimeErrorBuffer.readLine();
				if(temp!=null)
				{
					flag=1;
					compiletimeError+=temp;
					compiletimeError+="\n";
				}
				else break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(compiletimeError.equals(""))
			compiletimeError="Compilation Successfull\n";
		
		guiObject.taComplingResultArea.setText(compiletimeError);
		
		return flag;
	}
	void saveAs()
	{
		
		/*
		 * chooser.setDialogType(JFileChooser.SAVE_DIALOG);
//set a default filename (this is where you default extension first comes in)
 chooser.setSelectedFile(new File("myfile.xml"));
//Set an extension filter, so the user sees other XML files
 chooser.setFileFilter(new FileNameExtensionFilter("xml file","xml"));
 if(chooser.showSaveDialog(this) == jFileChooser.APPROVE_OPTION)
{
   String filename = chooser.getSelectedFile().toString();
   if (!filename .endsWith(".xml"))
        filename += ".xml";

   //DO something with filename

 }
		 */
		
		guiObject.jfc.setDialogType(JFileChooser.SAVE_DIALOG);
		//set a default filename (this is where you default extension first comes in)
		 guiObject.jfc.setSelectedFile(new File("*.java"));
		//Set an extension filter, so the user sees other XML files
		 guiObject.jfc.setFileFilter(new FileNameExtensionFilter("java file",".java"));
		 guiObject.jfc.updateUI();
		 
		 int x = guiObject.jfc.showSaveDialog(guiObject.jf);
		 if(x == JFileChooser.APPROVE_OPTION)
		{
		   filename = guiObject.jfc.getSelectedFile().toString();
		   System.out.println(filename);
		   if (!filename.endsWith(".java"))
		        filename += ".java";
		   //DO something with filename
		   
		   File code = new File(filename);
		   this.code = code;
		   FileWriter fw;
		try {
			 fw = new FileWriter(code);
			 PrintWriter pw = new PrintWriter(fw);
			 pw.println(guiObject.taCodingArea.getText());
			 pw.flush();
			 pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
			guiObject.savedStatus=1;
		   
		 }
		 
		 
		 
	}
}
