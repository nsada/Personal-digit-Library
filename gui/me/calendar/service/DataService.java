package me.calendar.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataService {
	int year,month,day;
	String fileName;
	File file;
	public DataService(int _year,int _month,int _day)
	{
		year=_year;month=_month;day=_day;
		String smonth=month+"";
		if(smonth.length()==1)smonth="0"+smonth;
		fileName = year+smonth+day;
		file=new File(fileName+".txt");
	}
	
	public DataService(String _year,String _month,String _day)
	{
		
		this(Integer.parseInt(_year),Integer.parseInt(_month),Integer.parseInt(_day));
		/*
		year=_year;month=_month;day=_day;
		String filename,smonth=month+"";
		if(smonth.length()==1)smonth="0"+smonth;
		filename = year+smonth+day;
		file=new File(fileName+".txt");
		*/
	}
	public static boolean exist(int _year,int _month,int _day)
	{
		
		String filename;
		String smonth=_month+"";
		if(smonth.length()==1)smonth="0"+smonth;
		 filename = _year+smonth+_day;
		File file=new File(filename+".txt");
		return file.exists();
	}
	public boolean exist()
	{
		return file.exists();
	}
	public String getContent()
	{
		if(!file.exists())return null;
		String read_str;
		StringBuffer sb = new StringBuffer();
		FileReader fr;
		 BufferedReader bfr;
		try {
			fr = new FileReader(file);
			 bfr= new BufferedReader(fr);//���n���x�����n�^
		      boolean flag=false;//���
		      while((read_str = bfr.readLine())!=null) // ÿ���xȡһ�У�ֱ���n���Y��
		      {
		        if (flag)//�ĵڶ����_ʼÿһ�е�һ��λ�ü������
		          sb.append("\n");
		        sb.append(read_str);//����ԓ��ӍϢ
		        flag=true;
		       
		      }
		      fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}//�xȡ�x������ӛ�n��
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	    if(sb.toString().equals(""))return null;
		return sb.toString();
	}
	
	public boolean save(String str)
	{
		 FileWriter fw;
		 BufferedWriter bfw;//���þ��n�^����
		try {
			fw = new FileWriter(file);
			bfw=new BufferedWriter(fw);
			 bfw.write(str); //��Textarea���݌��뾏�n�^�e
		        bfw.flush();//�����n�^�Y�ό����n��
		        fw.close();//�P�]�n��
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}//���Ùn������
	     return true;
	       
	}
	public boolean delete()
	{
		return file.delete();
	}

}
