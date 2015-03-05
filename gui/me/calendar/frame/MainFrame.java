package me.calendar.frame;



import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import me.calendar.component.CalendarPane;
import me.calendar.component.MainDesktopPane;
import me.calendar.service.ActionDispatcher;
import me.calendar.service.Utility;
	 
	public class MainFrame extends javax.swing.JFrame //����һҕ�����
	{
	 /**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	public MainDesktopPane mainDesktop;//���P�������
	public CalendarPane calendarPane;
	public JButton btn_clear=new JButton(new ImageIcon(System.getProperty("user.dir")+"/source/���.png"));
	public JButton btn_save=new JButton(new ImageIcon(System.getProperty("user.dir")+"/source/����.png"));
	public  JButton btn_query=new JButton(new ImageIcon(System.getProperty("user.dir")+"/source/��ѯ.png"));
	public JLabel lab_sun;
	public JLabel lab_week;
	public JLabel lab_query_year;
	public JLabel lab_query_month;
	public JLabel lab_show_date;
	public JLabel lab_show_test;
	public  JLabel jLabel7;
	public  JLabel lab_show_tip; 
	public  JComboBox<String> cbox_month;
	public  JTextField text_year;
	 
	public  JTextArea area_note;
	
	public JLabel j1 = new JLabel();
	public JButton jb_shelf=new JButton(new ImageIcon(System.getProperty("user.dir")+"/source/���.jpg"));
	public JButton jb_function=new JButton(new ImageIcon(System.getProperty("user.dir")+"/source/����.jpg"));
	public JButton jb_about=new JButton(new ImageIcon(System.getProperty("user.dir")+"/source/����.jpg"));
	public JButton jb_help=new JButton(new ImageIcon(System.getProperty("user.dir")+"/source/����.jpg"));
	public JPanel jp=new JPanel();
	 
	 //����ʽ�Y��
	 
	  public MainFrame()//����ҕ���_ʼ
	  {
	    super();
	    ActionDispatcher.setMainFrame(this);
	    Utility.setMainFrame(this);
	    
	    initGUI();//����GUI����
	  }//����ҕ���Y��
	  
	  
	  
	  private void initGUI()//�a��ҕ�X���������(Graph User Interface���D�λ�ʹ���߽���)
	  {
	    try
	    {
	      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//�O�����ҕ����Ҫ�����О�˜�(�s����С���ŵ�����P�])
	      {
	    	this.setTitle("��������ͼ���");//�O��ҕ��̧�^
	    	
	        mainDesktop = new MainDesktopPane(this);//����һ����
	        
	        mainDesktop.initNow();
	      }
	      getContentPane().add(mainDesktop, BorderLayout.CENTER);
	      pack();

			this.setBounds(200,70,950,650);//��С λ��
			Image i=this.getToolkit().getImage(System.getProperty("user.dir")+"/source/digital_library.png");//logo
			this.setIconImage(i);

			this.setResizable(false);//�����Ե�����С
			//this.setUndecorated(false);//�߿�
			this.setVisible(true);//�ɼ�

			//this.setSize(444, 296);
	    } catch (Exception e) {//����̎��
	      e.printStackTrace();
	    }
	  }
	 
	 //��ԃ���o�����|�l�¼��Y��
	 

}
