package me.calendar.component;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;

import me.calendar.frame.MainFrame;
import me.calendar.service.DataService;
import me.calendar.service.ActionDispatcher;
import me.calendar.service.ActionType;
import me.calendar.service.Utility;

public class CalendarPane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MainFrame mf;
	JButton btn[];
	Color bg=new java.awt.Color(215,217,218);
	public CalendarPane(MainFrame _mf)
	{
		super();
		mf=_mf;
		setLayout(null);
		setBounds(644, 245, 197, 180);
		setBackground(bg);
	}
	
	public void rebuild(int year,int month)
	{    
		int y=0,x=0,x_add=0,y_add=0,week_add=0,date_acc=0,week_of_day=0;
    String smonth,sday,filename;
    smonth=""+month;
    if(smonth.length()==1)smonth="0"+smonth;
	    switch(month)//�O���·��씵
	    {
	      case 1://����31��
	      case 3:
	      case 5:
	      case 7:
	      case 8:
	      case 10:
	      case 12:
	        date_acc = 31;
	        break;
	       
	      case 4://С��30��
	      case 6:
	      case 9:
	      case 11:
	        date_acc = 30;
	        break;
	       
	      case 2:
	        if (Utility.leap_year(year))
	          date_acc = 29;
	        else
	          date_acc = 28;
	    }
	   
	    week_of_day = Utility.dow(year,month,1);//�������ں���(ȡ�î��µ�һ������ڎ�)
	    week_add=27*week_of_day;

	    btn = new JButton[date_acc];//�������ڰ��o���
	    for (int i=0;i<date_acc;i++)//�������ڰ��o��л�Ȧ
	    {
	      btn[i] = new JButton();//�����������ڰ��o
	      mf.calendarPane.add(btn[i]);//�ӵ�����2��

	      btn[i].setText(String.valueOf(i+1));//�O�����o���֞�����
	      if ((i-week_of_day>0 && (i+week_of_day)%7==0) || ((i+week_of_day)%7==0 && i != 0))
	      {//�O�����µ�һ�����ڰ��oλ��
	        x=0;//X�S����
	        x_add=0;//��һ�����o����(X�S)��ֵ
	        y++;//Y�S����
	        y_add+=0;//�Q������(Y�S)��ֵ
	        week_add=0;//���µ�һ�հ��o���˼�ֵ
	      }//�����O�����o��С����ֵ(X����ʼ10+�ڎׂ����o*�M�򌒶�25+�g��+���µ�һ�����ڎ׼�ֵ)
	      btn[i].setBounds(x*27+x_add+week_add, y*30+y_add, 27, 30);//(Y��ڎׂ����o*�߶�20+�Q�м�ֵ)���o����25�ߞ�20
	      btn[i].setFont(new java.awt.Font("Leto",1,12));//�O�����w��С����ʽ
	      btn[i].setForeground(new java.awt.Color(39, 158,218));
	      btn[i].setBorder(null);
	      btn[i].setBorder(BorderFactory.createTitledBorder(""));//�O�����o���ֲ��Ԅ��{����С
	      int[] now = new int[3];
	      now = Utility.getdate();//ȡ�î�������
	      if (year == now[0] && month == now[1] && i+1 == now[2])
	        btn[i].setBackground(new java.awt.Color(255,255,255));//���鮔��t�O�����o���ɫ
	      else
	        btn[i].setBackground(new java.awt.Color(215,217,218));//�����Ǯ���t�O�����o����ɫ
	      /* 
	      sday = i+1+"";
	      filename = year+smonth+sday;//ӛ�n�����Q(��+��+��.txt)
	      File file=new File(filename+".txt");//�����n�����
	      */
	      if (DataService.exist(year, month, i+1))//��������ӛ�n���t�O�����o���w�ɫ���ɫ
	        btn[i].setForeground(new java.awt.Color(255,255,255));
	     
	      btn[i].addActionListener(new ActionDispatcher(ActionType.Pressed));
	      x++;//��һ�����oX���˼ә�
	      x_add+=0;//��һ�����o�g��X���˼ә�
	    }
	}
}
