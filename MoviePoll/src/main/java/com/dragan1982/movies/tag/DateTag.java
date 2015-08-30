package com.dragan1982.movies.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class DateTag extends SimpleTagSupport {
	
	// custom text
	private String prefix;
	 
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    
	public void doTag() throws JspException, IOException{
		
		JspWriter writer = getJspContext().getOut();
		
		GregorianCalendar calendar = new GregorianCalendar(TimeZone.getDefault());
		
		String pattern = "dd.MM.yyyy";		
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		
		try {
			if (prefix != null) {
	            writer.print(prefix + " ");
	        }
			writer.print(dateFormat.format(calendar.getTime()));
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	
}
