package InterfaceApplication;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import commandLine.DateInvalideException;
import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.Ligue;
import personnel.SauvegardeImpossible;



public class editEmploye {
	
    private Employe employe;
    private GestionPersonnel gestionPersonnel;
    JTextField nom;
    JTextField prenom;
    JTextField mail;
    JPasswordField password;
    JTextField dateArrive;
    JTextField dateDepart;
    private Employe connectedEmploye;
    private Ligue ligue;
    private JFrame employes = new JFrame();

    
	public editEmploye(GestionPersonnel gestionPersonnel, Employe employe, Ligue ligue, Employe connectedEmploye) {
		   this.gestionPersonnel = gestionPersonnel;
		   this.employe = employe;
		   this.ligue = ligue;
		   this.connectedEmploye = connectedEmploye;
	}
	
	
	public void listData()
	{
		frame().setVisible(true);
	}
	
	public JFrame frame()
	{
		employes.getContentPane().setBackground(Color.decode("#0080ff"));
		employes.setSize(700,700);
		employes.setLocationRelativeTo(null);
		employes.setJMenuBar(menuBar());
		employes.setLayout(new GridBagLayout());
		employes.add(panel());
		//titre
		employes.setTitle("édite employé");
   	 	//icon en haut a gauche
   	 	Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");  
   	 	employes.setIconImage(icon); 
        //permet de faire que la personne ne peux pas modifier la taille de la fenettre
   	 	employes.setResizable(false);
		employes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return employes;
	}
	
	 private JMenuBar menuBar()
	 {
		 JMenuBar menubar = new JMenuBar();
		 menubar.setPreferredSize(new Dimension(60,60));
		 JMenu menu = new JMenu("Mon compte");
		 menu.add(menuItem());
		 menu.add(deco());
		 menu.setFont(new Font("Serif", Font.BOLD, 20));
		 menu.setSize(70,70);
		 menu.setForeground(Color.decode("#fafafa"));
		 menubar.add(menu);
		 menubar.setBackground(Color.decode("#9f9f9f"));
		return menubar;
	 }
	 
	 private JMenuItem deco()
	 {
		 JMenuItem itemMenu = new JMenuItem("déconnexion");
		 itemMenu.setFont(new Font("Serif", Font.PLAIN, 20));
		 itemMenu.setBackground(Color.decode("#9f9f9f"));
		 itemMenu.setForeground(Color.decode("#fafafa"));
		 itemMenu.addActionListener(gérerdeco());
		 return itemMenu;
	 }
	 
	 private ActionListener gérerdeco() {
		 return new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
						employes.dispose();
						Connexions account = new Connexions(gestionPersonnel);
						account.signIn();
				}
			};
	 }

	 private JMenuItem menuItem()
	 {
		 JMenuItem itemMenu = new JMenuItem("Gérer mon compte");
		 itemMenu.setFont(new Font("Serif", Font.PLAIN, 20));
		 itemMenu.setBackground(Color.decode("#9f9f9f"));
		 itemMenu.setForeground(Color.decode("#fafafa"));
		 itemMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				    frame().setVisible(false);
					RootData account = new RootData(gestionPersonnel, connectedEmploye);
					account.AccountData();
			}
		});
		 return itemMenu;
	 }
	
	private JPanel panel()
	{
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#cbc0d3"));
		panel.setLayout(new GridLayout(0,2, 25,25));
		panel.setPreferredSize(new Dimension(750,650));	
		//le texte suivie de la zone de texte
		panel.add(nomL());
		panel.add(nameInput());
		panel.add(prenomL());
		panel.add(secondNameInput());
		panel.add(emailL());
		panel.add(mailInput());
		panel.add(passwordL());
		panel.add(passwordInput());
		panel.add(dateArriveeL());
		panel.add(DateArriveInput());
		panel.add(formaDate());
		panel.add(forma());
		panel.add(addEmploye());
		panel.add(cancelAdd());
		return panel;
	}
	
	private JLabel forma()
	{
		JLabel forma = new JLabel("(YYYY-MM-JJ)");
		forma.setFont(new Font("Serif", Font.PLAIN, 22));
		forma.setForeground(Color.decode("#540b0e"));
		 return forma;	
	}
	private JLabel formaDate()
	{
		JLabel formaDate = new JLabel("Format Date : ");
		formaDate.setFont(new Font("Serif", Font.PLAIN, 22));
		formaDate.setForeground(Color.decode("#540b0e"));
		 return formaDate;	
	}
	
	private JLabel nomL()
	{
		JLabel nom = new JLabel("Nom : ");
		nom.setFont(new Font("Serif", Font.PLAIN, 22));
		nom.setForeground(Color.decode("#540b0e"));
		 return nom;	
	}
	
	private JLabel prenomL()
	{
		JLabel prenom = new JLabel("Prenom : ");
		prenom.setFont(new Font("Serif", Font.PLAIN, 22));
		prenom.setForeground(Color.decode("#540b0e"));
		 return prenom;	
	}
	
	private JLabel emailL()
	{
		JLabel emailL = new JLabel("Email : ");
		emailL.setFont(new Font("Serif", Font.PLAIN, 22));
		emailL.setForeground(Color.decode("#540b0e"));
		 return emailL;	
	}
	
	private JLabel passwordL()
	{
		JLabel passwordL = new JLabel("Mot de passe : ");
		passwordL.setFont(new Font("Serif", Font.PLAIN, 22));
		passwordL.setForeground(Color.decode("#540b0e"));
		 return passwordL;	
	}
	
	private JLabel dateArriveeL()
	{
		JLabel dateArriveeL = new JLabel("Date d'arrivée : ");
		dateArriveeL.setFont(new Font("Serif", Font.PLAIN, 22));
		dateArriveeL.setForeground(Color.decode("#540b0e"));
		 return dateArriveeL;	
	}
	
	private JButton addEmploye()
	{
		JButton addbtn = new JButton("Enregistrer");
		addbtn.setBackground(Color.decode("#feeafa"));
		addbtn.setForeground(Color.decode("#540b0e"));
		addbtn.addActionListener(addEmployeAction());
		return addbtn;
	}
	
	private ActionListener addEmployeAction() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String p= new String(password.getPassword());
				Integer mois =0;
				Integer jour =0;	
				if(!dateArrive.getText().isEmpty() && dateArrive.getText().length() ==10) {
				String date =new String(dateArrive.getText());
				 mois = Integer.parseInt(date.substring(5,7));
				 jour =Integer.parseInt(date.substring(8,10));					
				
				 if(mail.getText().contains("@") && !p.equals("") && !nom.getText().equals("") && !prenom.getText().equals("") && mois<=12 && mois>=1 && jour<=31 && jour>=1 && !dateArrive.getText().isEmpty() ) {
				 	 	 employe.setMail(mail.getText());
					 	 employe.setNom(nom.getText());
					 	 employe.setPrenom(prenom.getText());
					 	 employe.setPassword(p);
					 	 try {
							employe.setDateArrivee(LocalDate.parse(dateArrive.getText().substring(0,10)));
						} catch (DateInvalideException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					 	 employe.updateEmploye(connectedEmploye);
						 employes.dispose();
						 listEmployesLigue employespage = new listEmployesLigue(gestionPersonnel, ligue, connectedEmploye,false);
						 employespage.listEmployes();	
				 }
				 }else 
					JOptionPane.showMessageDialog(null, "entrai des donnée valide", "Formulaire", JOptionPane.ERROR_MESSAGE);
			}
		};
	}

	private JButton cancelAdd()
	{
		JButton cancelbtn = new JButton("Annuler");
		cancelbtn.setBackground(Color.decode("#feeafa"));
		cancelbtn.setForeground(Color.decode("#540b0e"));
		cancelbtn.addActionListener(cancelAddActon());
		return cancelbtn;
	}
	
	private ActionListener cancelAddActon() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				  frame().setVisible(false);
				  frame().dispose();
				  listEmployesLigue employespage = new listEmployesLigue(gestionPersonnel, ligue, connectedEmploye,false);
				  employespage.listEmployes();
			}
		};
	}
	
	private JTextField nameInput()
	{
		nom = new JTextField();
		nom.setEditable(true);
		nom.setText(employe.getNom());
		return nom;
	}
	
	private JTextField secondNameInput()
	{
		prenom = new JTextField();
		prenom.setEditable(true);
		prenom.setText(employe.getPrenom());
		return prenom;
	}
	
	
	private  JTextField mailInput()
	{
		mail = new JTextField();
		mail.setEditable(true);
		mail.setText(employe.getMail());
		return mail;
	}
	
	private  JPasswordField passwordInput()
	{
		password = new JPasswordField();
		password.setEditable(true);
		password.setText(employe.getPassword());
		return password;
	}
	
	private  JTextField DateArriveInput()
	{
		dateArrive = new JTextField();
		dateArrive.setEditable(true);
		if(employe.getDateArrivee() != null)
		{
			dateArrive.setText(String.valueOf(employe.getDateArrivee()));
		}
		return dateArrive;
	}
	
	private JTextField DateDepartInput()
	{
		dateDepart = new JTextField();
		dateDepart.setEditable(true);
		if(employe.getDateDepart() != null) {
			dateDepart.setText(String.valueOf(employe.getDateDepart()));
		}
		return dateDepart;
	}
	
	
}
