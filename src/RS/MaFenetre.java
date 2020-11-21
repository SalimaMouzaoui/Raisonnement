package RS;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.TextArea;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JRadioButton;

public class MaFenetre extends JFrame{
	
	JPanel pan;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	JMenuBar menuBar;
	JMenu mnNewMenu;
	JMenuItem mntmNewMenuItem;
	JMenuItem mntmNewMenuItem_1;
	JMenuItem mntmNewMenuItem_2;
	JLabel lblNewLabel;
	JLabel lblNewLabel_1;
	JLabel lblNewLabel_2;
	TextArea textArea;
	JButton btnNewButton;
	JButton btnNewButton_1;
	Vector ensRelation;
	private JButton btnNewButton_2;
	LinkedList v1;
	LinkedList v2;
	Relation rel;
    String element;
    String RM1M2;
    private TextArea textArea_1;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JMenuItem mntmNewMenuItem_3;
	
	void fonction1() 
    {
		ensRelation.addElement(new Relation(textField_1.getText(),textField_3.getText(),textField_2.getText()));
		textArea_1.appendText(textField_1.getText()+" "+textField_2.getText()+" "+textField_3.getText()+"\n");
		textField_1.setText("");
    	textField_2.setText("");
    	textField_3.setText("");    	
    }
    
    void fonction2() 
    {
    	textField_1.setText("");
    	textField_2.setText("");
    	textField_3.setText("");
    	textArea.appendText("**********************************\n");  
    	while (!v1.isEmpty()) {
            v1.removeFirst();
        }
    	while (!v2.isEmpty()) {
            v2.removeFirst();
        }
    	for (int k=0;k<ensRelation.size();k++)
    	{
    		Relation rel2=(Relation)ensRelation.elementAt(k);
    		rel2.initPoint.marque=null;	
           	rel2.endPoint.marque=null;	
        }
    }
    
    void fonction3() 
    {
    	v1.addLast(textField_1.getText());
    	for (int j=0;j<ensRelation.size();j++)
        {
        	rel=(Relation)ensRelation.elementAt(j);
        	if (rel.initPoint.nom.equals(textField_1.getText())==true)
        	{
        		rel.initPoint.marque="M1";
        	} 
        	if (rel.endPoint.nom.equals(textField_1.getText())==true)
        	{
        		rel.endPoint.marque="M1";
        	}        	
        }
    	v2.addLast(textField_3.getText());
    	for (int j=0;j<ensRelation.size();j++)
        {
        	rel=(Relation)ensRelation.elementAt(j);
        	if (rel.initPoint.nom.equals(textField_3.getText())==true )
        	{
        		rel.initPoint.marque="M2";
        	} 
        	if (rel.endPoint.nom.equals(textField_3.getText())==true )
        	{
        		rel.endPoint.marque="M2";
        	}        	
        }
    	RM1M2=textField_2.getText();
    	boolean arret1=false;
        boolean arret2=false;
        int n;
        Relation rel2;
        while (arret1==false || arret2==false)
        { 
        	n=v1.size();
        	arret1=true;
        	for (int i=0;i<n;i++)
        	{
        		element=(String)v1.getFirst();
        		for (int j=0;j<ensRelation.size();j++)
                {
                	rel=(Relation)ensRelation.elementAt(j);
                	if (rel.endPoint.nom.equals(element)==true && rel.relation.equals("is-a"))
                	{
                		arret1=false;
                		v1.addLast(rel.initPoint.nom);
                    	for (int k=0;k<ensRelation.size();k++)
                    	{
                    		rel2=(Relation)ensRelation.elementAt(k);
                    		if (rel2.initPoint.nom.equals(rel.initPoint.nom)==true)
                           	{
                    				rel2.initPoint.marque="M1";	
                           	}
                    		if (rel2.endPoint.nom.equals(rel.initPoint.nom)==true)
                           	{
                    				rel2.endPoint.marque="M1";	
                           	}
                		}
                   	}                	       	
                }
        		v1.removeFirst();
             }
        	    
        	 n=v2.size();
        	 arret2=true;
        	 for (int i=0;i<n;i++)
        	 {
        		element=(String)v2.getFirst();
        		for (int j=0;j<ensRelation.size();j++)
                {
        			rel=(Relation)ensRelation.elementAt(j);
                	if (rel.endPoint.nom.equals(element)==true && rel.relation.equals("is-a"))
                	{
                		arret2=false;
                		v2.addLast(rel.initPoint.nom);
                    	for (int k=0;k<ensRelation.size();k++)
                    	{
                    		rel2=(Relation)ensRelation.elementAt(k);
                    		if (rel2.initPoint.nom.equals(rel.initPoint.nom)==true)
                           	{
                    				rel2.initPoint.marque="M2";	
                           	}
                    		if (rel2.endPoint.nom.equals(rel.initPoint.nom)==true)
                           	{
                    				rel2.endPoint.marque="M2";	
                           	}
                		}
                   	}                 	       	
                }
        		v2.removeFirst();
        	 }        	
        }
        boolean trouve=false;
        for (int i=0;i<ensRelation.size();i++)
        {
        	rel=(Relation)ensRelation.elementAt(i);
        	if (rel.initPoint.marque!=null && rel.initPoint.marque.equals("M1") && rel.endPoint.marque!=null && rel.endPoint.marque.equals("M2") && rel.relation.equals(RM1M2))
        	{
        		textArea.appendText(rel.initPoint.nom+" "+rel.relation+" "+rel.endPoint.nom+"\n");
        		trouve=true;
            }        	      	
        }
        if (trouve==false)
        	textArea.appendText("Manque de connaissances\n");
    }
    
    void fonction4() throws IOException 
    {
    	JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(getParent());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        BufferedReader br = new BufferedReader(new FileReader((new File(chooser.getSelectedFile().getName()))));
    	String str = br.readLine();
    	while (str!=null)
		{ 		    
		    String temp[] = str.split(" ");
		    ensRelation.addElement(new Relation(temp[0],temp[2],temp[1]));
		    textArea_1.appendText(""+temp[0]+" "+temp[1]+" "+temp[2]+"\n");
		    str = br.readLine();
		}
        }
    }
    
    void fonction5() throws IOException 
    {
    	String out="output.txt";
		File f = new File (out);
		FileWriter fw = new FileWriter (f);  
		fw.write(textArea.getText()+"");
		fw.close();
    }
    
    void fonction6()
    {
    	ensRelation.removeAllElements();
    	while (!v1.isEmpty()) {
            v1.removeFirst();
        }
    	while (!v2.isEmpty()) {
            v2.removeFirst();
        }
    	textField_1.setText("");
    	textField_2.setText("");
    	textField_3.setText("");
    	textArea.appendText("**********************************\n"); 
    	textArea_1.appendText("*************************************\n"); 
   }
    
    
    void fonction7() 
    {
    	v1.addLast(textField_1.getText());
    	for (int j=0;j<ensRelation.size();j++)
        {
        	rel=(Relation)ensRelation.elementAt(j);
        	if (rel.initPoint.nom.equals(textField_1.getText())==true)
        	{
        		rel.initPoint.marque="M1";
        	} 
        	if (rel.endPoint.nom.equals(textField_1.getText())==true)
        	{
        		rel.endPoint.marque="M1";
        	}        	
        }
    	v2.addLast(textField_3.getText());
    	for (int j=0;j<ensRelation.size();j++)
        {
        	rel=(Relation)ensRelation.elementAt(j);
        	if (rel.initPoint.nom.equals(textField_3.getText())==true )
        	{
        		rel.initPoint.marque="M2";
        	} 
        	if (rel.endPoint.nom.equals(textField_3.getText())==true )
        	{
        		rel.endPoint.marque="M2";
        	}        	
        }
    	RM1M2=textField_2.getText();
    	boolean arret1=false;
        boolean arret2=false;
        boolean trouve=false;
        int n;
        Relation rel2;
        while ((arret1==false || arret2==false) && trouve==false)
        { 
        	n=v1.size();
        	arret1=true;
        	for (int i=0;i<n;i++)
        	{
        		element=(String)v1.getFirst();
        		for (int j=0;j<ensRelation.size();j++)
                {
                	rel=(Relation)ensRelation.elementAt(j);
                	if (rel.endPoint.nom.equals(element)==true && rel.relation.equals("is-a"))
                	{
                		arret1=false;
                		v1.addLast(rel.initPoint.nom);
                    	for (int k=0;k<ensRelation.size();k++)
                    	{
                    		rel2=(Relation)ensRelation.elementAt(k);
                    		if (rel2.initPoint.nom.equals(rel.initPoint.nom)==true)
                           	{
                    				rel2.initPoint.marque="M1";	
                           	}
                    		if (rel2.endPoint.nom.equals(rel.initPoint.nom)==true)
                           	{
                    				rel2.endPoint.marque="M1";	
                           	}
                		}
                   	}                	       	
                }
        		v1.removeFirst();
             }
        	    
        	 n=v2.size();
        	 arret2=true;
        	 for (int i=0;i<n;i++)
        	 {
        		element=(String)v2.getFirst();
        		for (int j=0;j<ensRelation.size();j++)
                {
        			rel=(Relation)ensRelation.elementAt(j);
                	if (rel.endPoint.nom.equals(element)==true && rel.relation.equals("is-a"))
                	{
                		arret2=false;
                		v2.addLast(rel.initPoint.nom);
                    	for (int k=0;k<ensRelation.size();k++)
                    	{
                    		rel2=(Relation)ensRelation.elementAt(k);
                    		if (rel2.initPoint.nom.equals(rel.initPoint.nom)==true)
                           	{
                    				rel2.initPoint.marque="M2";	
                           	}
                    		if (rel2.endPoint.nom.equals(rel.initPoint.nom)==true)
                           	{
                    				rel2.endPoint.marque="M2";	
                           	}
                		}
                   	}                 	       	
                }
        		v2.removeFirst();
        	 }  
        	 for (int i=0;i<ensRelation.size() && trouve==false;i++)
             {
             	rel=(Relation)ensRelation.elementAt(i);
             	if (rel.initPoint.marque!=null && rel.initPoint.marque.equals("M1") && rel.endPoint.marque!=null && rel.endPoint.marque.equals("M2") && rel.relation.equals(RM1M2))
             	{
             		textArea.appendText(rel.initPoint.nom+" "+rel.relation+" "+rel.endPoint.nom+"\n");
             		trouve=true;
                 }        	      	
             }
        }
        
        if (trouve==false)
        	textArea.appendText("Manque de connaissances\n");
    }
    
    MaFenetre()
	{
		ensRelation=new Vector();
		v1=new LinkedList();
		v2=new LinkedList();
		resize(624,527);
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Fichier");
		mnNewMenu.setFont(new Font("Constantia", Font.PLAIN, 12));
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("Ouvrir");
		mntmNewMenuItem.setFont(new Font("Constantia", Font.PLAIN, 12));
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				try {
					fonction4();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			} 
		});
		
		mntmNewMenuItem_1 = new JMenuItem("Sauvegarder");
		mntmNewMenuItem_1.setFont(new Font("Constantia", Font.PLAIN, 12));
		mnNewMenu.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				try {
					fonction5();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			} 
		});
		
		mntmNewMenuItem_3 = new JMenuItem("Reset");
		mntmNewMenuItem_3.setFont(new Font("Constantia", Font.PLAIN, 12));
		mnNewMenu.add(mntmNewMenuItem_3);
		mntmNewMenuItem_3.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				 fonction6();
			} 
		});
		
		mntmNewMenuItem_2 = new JMenuItem("Quitter");
		mntmNewMenuItem_2.setFont(new Font("Constantia", Font.PLAIN, 12));
		mnNewMenu.add(mntmNewMenuItem_2);
		mntmNewMenuItem_2.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				dispose(); 
			} 
		});
		getContentPane().setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setBounds(100, 82, 114, 38);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(224, 82, 130, 38);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(364, 82, 149, 38);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		lblNewLabel = new JLabel("Source");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
		lblNewLabel.setBounds(112, 47, 114, 24);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Relation");
		lblNewLabel_1.setFont(new Font("Constantia", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(211, 47, 146, 24);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Destinataire");
		lblNewLabel_2.setFont(new Font("Constantia", Font.PLAIN, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(386, 47, 113, 24);
		getContentPane().add(lblNewLabel_2);
		
		textArea = new TextArea();
		textArea.setBounds(303, 218, 294, 204);
		getContentPane().add(textArea);
		
		btnNewButton = new JButton("Sauver");
		btnNewButton.setFont(new Font("Constantia", Font.PLAIN, 15));
		btnNewButton.setBounds(100, 135, 84, 31);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				fonction1(); 
			} 
		}); 
		
		btnNewButton_1 = new JButton("Effacer");
		btnNewButton_1.setFont(new Font("Constantia", Font.PLAIN, 15));
		btnNewButton_1.setBounds(194, 135, 93, 31);
		getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				fonction2(); 
			} 
		}); 
		
		btnNewButton_2 = new JButton("Inf\u00E9rer tous");
		btnNewButton_2.setFont(new Font("Constantia", Font.PLAIN, 15));
		btnNewButton_2.setBounds(297, 135, 114, 31);
		getContentPane().add(btnNewButton_2);
		
		textArea_1 = new TextArea();
		textArea_1.setBounds(10, 218, 287, 204);
		getContentPane().add(textArea_1);
		
		lblNewLabel_3 = new JLabel("Les \u00E9l\u00E9ments du r\u00E9seau");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Constantia", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(10, 188, 287, 24);
		getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Connaissances inf\u00E9r\u00E9es");
		lblNewLabel_4.setFont(new Font("Constantia", Font.PLAIN, 15));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(303, 188, 294, 24);
		getContentPane().add(lblNewLabel_4);
		
		JButton btnNewButton_3 = new JButton("V\u00E9rifier");
		btnNewButton_3.setFont(new Font("Constantia", Font.PLAIN, 15));
		btnNewButton_3.setBounds(421, 135, 92, 30);
		getContentPane().add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				fonction7(); 
			} 
		}); 
		btnNewButton_2.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				fonction3(); 
			} 
		});
    }
	
	class windowEvents extends java.awt.event.WindowAdapter {
        public void windowClosing(java.awt.event.WindowEvent event) {
               dispose();
        }
    }
}
