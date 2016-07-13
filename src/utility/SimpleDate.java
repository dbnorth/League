package utility;

import java.util.Calendar;
import java.util.GregorianCalendar;

@SuppressWarnings("serial")
public class SimpleDate extends GregorianCalendar {

	public SimpleDate(String date)
	{
		String[] ed;
		ed = date.split("/");
		this.set(Integer.parseInt(ed[2])+2000,
				Integer.parseInt(ed[0])-1,
				Integer.parseInt(ed[1]),
			       0,
			       0);
	}
	
	public void setDate(String date)
	{
		String[] ed;
		ed = date.split("/");
		this.set(Integer.parseInt(ed[2])+2000,
				Integer.parseInt(ed[0])-1,
				Integer.parseInt(ed[1]),
			       0,
			       0);
	}
	
	public String toString()
	
	{
		return   get(Calendar.WEEK_OF_MONTH)+"/"+get(Calendar.DATE)+"/"+get(Calendar.MONTH);

	}
	
	
	
}
