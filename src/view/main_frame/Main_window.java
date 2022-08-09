package view.main_frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import data_base.CourseChairDB;
import data_base.CourseClassDB;
import data_base.GradeDB;
import data_base.ProfessorDB;
import data_base.StudentDB;

public class Main_window extends JFrame {
	
	private static Main_window instance = null;
	
	public static Main_window getInstance() {
		if (instance == null) {
			instance = new Main_window();
			instance.initGUI();
		}
		return instance;
	}
	
	private ResourceBundle resourceBundle;
	
	private MenuBar menu;
	private ToolBar toolbar;
	private Main_Window_Tabs mw_tabs;
	private StatusBar statusBar;
	
	private Main_window() {
		
		super();

		Locale.setDefault(new Locale("sr", "RS"));
		resourceBundle = ResourceBundle.getBundle("languages.languages", Locale.getDefault());
		
		//ako treba za JOptionPane da se doda
		
		setVisible(true);
	
	}
	
	public void initGUI() {
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int width = dimension.width;
		int height = dimension.height;
		setSize(3*width / 4, 3*height / 4);
		setLocationRelativeTo(null);
		
		setTitle(this.resourceBundle.getString("naslovAplikacije"));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		menu = new MenuBar();
		menu.setBackground(Color.LIGHT_GRAY);
		this.setJMenuBar(menu);
		
		toolbar = new ToolBar();
		toolbar.setBackground(Color.LIGHT_GRAY);
		add(toolbar, BorderLayout.NORTH);
		
		JPanel pan_main_window = new JPanel();
		pan_main_window.setBackground(Color.LIGHT_GRAY);
		
		mw_tabs = Main_Window_Tabs.getInstance();
		pan_main_window.setLayout(new BorderLayout());
		pan_main_window.add(mw_tabs, BorderLayout.CENTER);
		
		add(pan_main_window, BorderLayout.CENTER);
		
		statusBar = new StatusBar(this);
		
		mw_tabs.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				statusBar.changeTitle();
			}
		});
		
		UIManager.put("OptionPane.yesButtonText", resourceBundle.getObject("yesOption"));
		UIManager.put("OptionPane.noButtonText", resourceBundle.getObject("noOption"));
		UIManager.put("OptionPane.okButtonText", resourceBundle.getObject("acceptOption"));
		UIManager.put("OptionPane.cancelButtonText", resourceBundle.getObject("cancelOption"));
		
		setVisible(true);
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public MenuBar getMenu() {
		return menu;
	}

	public ToolBar getToolbar() {
		return toolbar;
	}

	public Main_Window_Tabs getMw_tabs() {
		return mw_tabs;
	}

	public StatusBar getStatusBar() {
		return statusBar;
	}
	
	public void SaveDataBaseState() {
		try {
			StudentDB.getInstance().SaveDataBaseState();
			ProfessorDB.getInstance().SaveDataBaseState();
			CourseClassDB.getInstance().SaveDataBaseState();
			GradeDB.getInstance().SaveDataBaseState();
			CourseChairDB.getInstance().SaveDataBaseState();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void changeLanguage() {
		
		resourceBundle = ResourceBundle.getBundle("languages.languages", Locale.getDefault());
		setTitle(this.resourceBundle.getString("naslovAplikacije"));
		menu.initComponents();
		toolbar.initComponents();
		mw_tabs.initComponenets();
		statusBar.initComponents();
		
		mw_tabs.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				statusBar.changeTitle();
			}
		});
		
		UIManager.put("OptionPane.yesButtonText", resourceBundle.getObject("yesOption"));
		UIManager.put("OptionPane.noButtonText", resourceBundle.getObject("noOption"));
		UIManager.put("OptionPane.okButtonText", resourceBundle.getObject("acceptOption"));
		UIManager.put("OptionPane.cancelButtonText", resourceBundle.getObject("cancelOption"));
		
	}
	
}
