package me.calendar.service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import me.calendar.frame.MainFrame;

public class ActionDispatcher implements ActionListener {
	 
	  static MainFrame mf;
	  ActionType type;
	  
	  public ActionDispatcher(ActionType at)
	  {
		  type=at;
	  }
	  public void dispatch(ActionEvent e)
	  {
		  switch(type)
		{
			case Pressed:btnActionPerformed(e);break;
			case Clear:clearActionPerformed(e);break;
			case Save:saveActionPerformed(e);break;
			case Query:queryActionPerformed(e);break;
		}
	  }
		@Override
		public void actionPerformed(ActionEvent e) {
			
			dispatch(e);
		}
	private static  void btnActionPerformed(ActionEvent evt)//���ڰ��o�����|�l�¼��_ʼ
	  {
	    mf.area_note.setText("");
	    String year,month,btn_date,filename,read_str;
	    year = mf.lab_show_date.getText().substring(0,4);//ȡ����
	    month = mf.lab_show_date.getText().substring(7,9);//ȡ����
	    btn_date = evt.getActionCommand();//ȡ�ð��°��o����(��)
	    
	    mf.jLabel7.setText(btn_date);
	    DataService ds = new DataService(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(btn_date));
	    
	    if(!ds.exist())
	    {
	    	 mf.lab_show_test.setText("���ն���ƻ�");
		      mf.lab_show_tip.setText("��ѡ��"+year+"��"+month+"��"+btn_date+"��");
	    }
	    else
	    {
	    	String content = ds.getContent();
	    	 mf.area_note.setText(content);
	    	 if(content!=null&&!content.equals(""))
	    	 {	mf.lab_show_test.setText("��������");
		      	mf.lab_show_tip.setText("��ѡ��"+year+"��"+month+"��"+btn_date+"��");
	    	 }
	    	 else
	    	 {
	    		 mf.lab_show_test.setText("���ն���ƻ�");
			      mf.lab_show_tip.setText("��ѡ��"+year+"��"+month+"��"+btn_date+"��");
	    	 }
		      
	    }
	    /*
	    filename = year+month+btn_date;
	    try
	    {
	      FileReader fr = new FileReader(filename+".txt");//�xȡ�x������ӛ�n��
	      BufferedReader bfr = new BufferedReader(fr);//���n���x�����n�^
	      boolean flag=false;//���
	      while((read_str = bfr.readLine())!=null) // ÿ���xȡһ�У�ֱ���n���Y��
	      {
	        if (flag)//�ĵڶ����_ʼÿһ�е�һ��λ�ü������
	          mf.area_note.append("\n");
	        mf.area_note.append(read_str);//����ԓ��ӍϢ
	        flag=true;
	       
	      }
	      mf.lab_show_test.setText("�������");
	      mf.lab_show_tip.setText("��ѡ��"+year+"��"+month+"��"+btn_date+"��");
	      fr.close();
	    }catch(FileNotFoundException e)//����]��ָ����ӛ�n����ӡ�����՟o����(����̎��)
	    {
	      mf.lab_show_test.setText("������������");
	      mf.lab_show_tip.setText("��ѡ��"+year+"��"+month+"��"+btn_date+"��");
	    }catch(IOException e)//����̎��
	    {
	      e.printStackTrace();
	    }
	    */
	  }//���ڰ��o�����|�l�¼��Y��
	  
	  private static  void clearActionPerformed(ActionEvent evt)//������o�����|�l�¼�
	  {
	    mf.area_note.setText("");//���ӛ����
	    String year,month,day,filename;
	    year = mf.lab_show_date.getText().substring(0,4);
	    month = mf.lab_show_date.getText().substring(7,9);
	    day = mf.jLabel7.getText();
	    DataService ds = new DataService(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
	    if(ds.exist())ds.delete();
	    /*
	    filename = year+month+day;
	    File file=new File(filename+".txt");//�h������ӛ�n��
	    file.delete();
	    */
	    Utility.new_btn();//���®a�����o
	    mf.lab_show_test.setText("�ƻ������");//�O�����PӍϢ
	    mf.jLabel7.setText("");
	    mf.lab_show_tip.setText("δѡ������");
	  }
	 
	  private static  void saveActionPerformed(ActionEvent evt)//���水�o�����|�l�¼��_ʼ
	  {
	    String year,month,day,filename,insert_str;
	    year = mf.lab_show_date.getText().substring(0,4);
	    month = mf.lab_show_date.getText().substring(7,9);
	    day = mf.jLabel7.getText();
	    insert_str = mf.area_note.getText();//ӛ����
	    DataService ds = new DataService(year,month,day);
	    
	    
	    if (insert_str.length() != 0 && day.length() != 0)//��ӛ�¿�������������x�����ڄt����ӛ�n��
	    {
	    	 if(ds.save(insert_str))
	    	 { 	mf.lab_show_test.setText("�Ѽ�¼�ƻ�");//�O�����PӍϢ
	    	 Utility.new_btn();
		     	mf.jLabel7.setText("");
		     	mf.lab_show_tip.setText("δѡ������");
	    	 }
	    	 else
	    	 {
	    		 mf.lab_show_test.setText("��¼ʧ��"); 
	    	 }
		     Utility.new_btn();

	    }
	    else//���oӛ���ݻ�o�x������
	    {
	      if (day.length() == 0)
	        mf.lab_show_test.setText("δѡ������");//�O�����PӍϢ
	      else
	        mf.lab_show_test.setText("�����޶���ƻ�");
	    }
	    Utility.new_btn();
	  }//���水�o�����|�l�¼��Y��
	 
	  private static   void queryActionPerformed(ActionEvent evt)//��ԃ���o�����|�l�¼��_ʼ
	  {
	    String syear,smonth;
	    try
	    {
	      mf.area_note.setText("");
	      mf.lab_show_test.setText("��ѯ����");
	      syear = mf.text_year.getText();
	      smonth = String.valueOf(mf.cbox_month.getSelectedIndex() + 1);
	      if (smonth.length() == 1)
	            smonth = "0"+smonth;
	      if (syear == "" || Integer.parseInt(syear)<1582)//��δݔ����ݾ��|�l����(1582����ǰ�����ĕ��^���Y�������ʴ_)
	      {
	        int[] now = new int[3];
	        now = Utility.getdate();
	        syear = String.valueOf(now[0]);//���x�����С�1582��t�A�O�鮔��
	        mf.lab_show_test.setText("��ѡ��1582������");
	      }
	      mf.lab_show_date.setText(syear+" �� "+smonth+" ��");
	      Utility.date_btn_create(Integer.parseInt(syear),Integer.parseInt(smonth));
	     mf.jLabel7.setText("");
	     mf.lab_show_tip.setText("δѡ������");
	    }catch(NumberFormatException e)//����̎���O���鮔�꼰�x����·�
	    {
	      int[] now = new int[3];
	      now = Utility.getdate();
	      syear = String.valueOf(now[0]);
	      smonth = String.valueOf(mf.cbox_month.getSelectedIndex() + 1);
	      if (smonth.length() == 1)
	            smonth = "0"+smonth;
	     mf.lab_show_date.setText(syear+" �� "+smonth+" ��");
	     mf.lab_show_test.setText("��ѡ��1582������");
	      Utility.date_btn_create(Integer.parseInt(syear),Integer.parseInt(smonth));
	     mf.jLabel7.setText("");
	     mf.lab_show_tip.setText("δѡ������");
	    }
	  }

	public static void setMainFrame(MainFrame mf) {
		ActionDispatcher.mf = mf;
	}



}
