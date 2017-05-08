package com.rain.app.server.redux.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

class HtmlFormatter extends Formatter {
    // this method is called for every log records
    @Override
	public String format(LogRecord record) {
            StringBuffer buffer = new StringBuffer(1000);
            buffer.append("<tr>\n");

            // colorize any levels >= WARNING in red
            if (record.getLevel().intValue() >= Level.WARNING.intValue()) {
                    buffer.append("\t<td style=\"color:red\">");
                    buffer.append("<b>");
                    buffer.append(record.getLevel());
                    buffer.append("</b>");
            } else if(record.getLevel().intValue() == Level.FINE.intValue()){
	            	buffer.append("\t<td style=\"color:#00E000\">");
	                buffer.append("<b>");
	                buffer.append(record.getLevel());
	                buffer.append("</b>");
            } else {
                    buffer.append("\t<td>");
                    buffer.append(record.getLevel());
            }

            buffer.append("</td>\n");
            buffer.append("\t<td>");
            buffer.append(calcDate(record.getMillis()));
            buffer.append("</td>\n");
            buffer.append("\t<td>");
            buffer.append(formatMessage(record));
            buffer.append("</td>\n");
            buffer.append("</tr>\n");

            return buffer.toString();
    }

    private String calcDate(long millisecs) {
            SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
            Date resultdate = new Date(millisecs);
            return date_format.format(resultdate);
    }

    // this method is called just after the handler using this
    // formatter is created
    @Override
	public String getHead(Handler h) {
        return "<!DOCTYPE html>\n<head>\n<style>\n"
            + "table { width: 1000px; table-layout:fixed; }\n"
            + "th { font:bold 10pt Tahoma; }\n"
            + "td { font:normal 10pt Tahoma; overflow: auto; white-space:pre; border: 1px solid black;}\n"
            + "h1 {font:normal 11pt Tahoma;}\n"
            + "</style>\n"
            + "</head>\n"
            + "<body>\n"
            + "<h1>" + (new Date()) + "</h1>\n"
            + "<table border=\"0\" cellpadding=\"5\" cellspacing=\"3\">\n"
          
            + "<tr align=\"left\">\n"
            + "\t<th style=\"width:10%\">Loglevel</th>\n"
            + "\t<th style=\"width:20%\">Time</th>\n"
            + "\t<th style=\"width:70%\">Log Message</th>\n"
            + "</tr>\n";
      }

    // this method is called just after the handler using this
    // formatter is closed
    @Override
	public String getTail(Handler h) {
            return "</table>\n</body>\n</html>";
    }
}
