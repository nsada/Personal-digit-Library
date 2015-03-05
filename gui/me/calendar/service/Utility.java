package me.calendar.service;

import java.awt.Font;
import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;

import me.calendar.component.CalendarPane;
import me.calendar.frame.MainFrame;

public class Utility {
	static MainFrame mf;
	public static Font en11=new java.awt.Font("Segue",0,11);
	public static Font en12=new java.awt.Font("Leto",0,12);
	public static Font cn11=new java.awt.Font("΢���ź�",0,11);
	public static Font cn12=new java.awt.Font("΢���ź�",0,12);
	
	public static void setMainFrame(MainFrame _mf)
	{
		mf = _mf;
	}
	
	  public static void new_btn()//���®a�����ڰ��o�����_ʼ
	  {
	    mf.area_note.setText("");//���ӛ��
	    int year,month;
	    year = Integer.parseInt(mf.lab_show_date.getText().substring(0,4));//�O�������x�����
	    month = Integer.parseInt(mf.lab_show_date.getText().substring(7,9));//�O�������x�����
	    date_btn_create(year,month);//���Юa�����ڰ��o����
	  }//���®a�����ڰ��o�����Y��
	  
	  public static void date_btn_create(int year,int month)//�a�����ڰ��o
	  {
	

	   
	    mf.mainDesktop.remove(mf.calendarPane);//�Ƴ�����2(���ڰ��o������Ҳ���ǰ����ڰ��o�Ƴ�)
	    mf.calendarPane = new CalendarPane(mf);//�a��һ���µ�����
	    mf.calendarPane.rebuild(year, month);
	    mf.mainDesktop.add(mf.calendarPane);
	    mf.mainDesktop.remove(mf.mainDesktop.lab);//�Ƴ�����2(���ڰ��o������Ҳ���ǰ����ڰ��o�Ƴ�)
	    mf.mainDesktop.add(mf.mainDesktop.lab);

	  }
	  public static  boolean leap_year(int year)//�Д��c�꺯���_ʼ
	  {
	    boolean leap_year;//4�ı���������100�ı����t��횞�4�ı��������c��
	    if (year%4 == 0)
	    {
	      if (year%100 == 0)
	      {
	        if (year%400 == 0)
	          leap_year = true;
	        else
	          leap_year = false;
	      }
	      else
	        leap_year = true;
	    }
	    else
	      leap_year = false;
	    return leap_year;
	  }//�Д��c�꺯���Y��
	  public static int[] getdate()//ȡ��ϵ�y���ں����_ʼ
	  {
	    int[] date_array = new int[3];
	    Calendar ca = new GregorianCalendar();  
	    date_array[0] = ca.get(Calendar.YEAR);//��
	    date_array[1] = ca.get(Calendar.MONTH)+1;//��
	    date_array[2] = ca.get(Calendar.DAY_OF_MONTH);//��
	    return date_array;//�؂���ӆ�������
	  }//ȡ��ϵ�y���ں����Y��
	  
	  public static int dow(int y,int m,int d)//�Д����ڎ�
	  {
	    int[] ww={6, 2, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};//�������w�\��ֵ
	    int w;
	    w=ww[m-1]+y+(y/4)-(y/100)+(y/400);//�c���O��
	    if( ((y%4)==0) && (m<3) )//��ʽ
	    {
	      w--;
	      if((y%100)==0) w++;
	      if((y%400)==0) w--;
	    }
	    return (w+d)%7;//�؂����ڎ�(0�������գ�1������һ�Դ����)
	  }
}
