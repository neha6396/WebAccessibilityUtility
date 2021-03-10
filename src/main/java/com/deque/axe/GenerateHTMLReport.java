package com.deque.axe;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class GenerateHTMLReport {
FileWriter out;
BufferedWriter writer;
//DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
String dateFormat = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date());
Date date = new Date();
//public FileWriter initHTMLReport()
public FileWriter initHTMLReport()
{
try {
out = new FileWriter("./All_Results/Accessibility_Report/" + dateFormat + "_Report.html");
return out;
} catch (IOException e) {
e.printStackTrace();
return null;
}
}
public void startHTML(FileWriter out,String testname) throws IOException
{
System.out.println("FunctionName : 'CreateHtmlReport'");
writer = new BufferedWriter(out);
tableTitle(testname);
}

public void generateDetailViolationReport( String testname,JSONArray violations,String url) throws IOException
{
int violationCount = 0;
String description="";
tableFormat();
for(int i=0;i<violations.length();i++)
{
//violationCount++;
JSONObject rec = violations.getJSONObject(i);
String help = rec.getString("help");
String helpUrl = rec.getString("helpUrl");
String impact = rec.getString("impact");
JSONArray tags = rec.getJSONArray("tags");
String target = null;
description = rec.getString("description");
description = description.replaceAll("<", "&lt;");
description = description.replaceAll(">", "&gt;");
JSONArray nodes = (JSONArray) rec.get("nodes");
for(int j=0;j<nodes.length();j++) {
violationCount++;
String message = null;
List<String> fullMessage = new ArrayList<String>();
JSONObject firstSport = nodes.getJSONObject(j);
JSONArray anyArray = firstSport.getJSONArray("any");
for (int k = 0; k < anyArray.length(); k++) {
JSONObject mess = anyArray.getJSONObject(k);
message = mess.getString("message");
fullMessage.add(message);
List<String> targetMessage = new ArrayList<String>();
JSONObject targetSport = nodes.getJSONObject(j);
JSONArray targetArray = targetSport.getJSONArray("target");
target = targetArray.toString();
targetMessage.add(target);
}
tableURL(url, violationCount);
tableHelp(help);
tableDescription(description);
tableTarget(target);
tableImpact(impact);
tableHelpURL(helpUrl);
tableTags(tags);
tableSolution(message);
}
}
violationCount(violationCount);
}

private void tableFormat() {
try
{
writer.write("<table border=1 cellpadding='0' cellspacing='0' style='border-collapse:collapse/;table-layout:auto;width:889pt'>");
writer.write("<tr height=24 style='height:14.5pt'>"
+ "<th height=19 class=xl67 width=31 style='height:14.5pt;width:23pt'>Sr #</th>");
writer.write("<th class=xl68 width=412 style='border-left:none;width:309pt'>Page / Screen / URL</th>"
+ " <th colspan=2 class=xl73 style='border-left:none'>Violation Details</th>"
+ " </tr> <tr height=39 style='mso-height-source:userset;height:29.25pt'>");
}
catch(Exception e)
{
e.printStackTrace();
}
}

private void violationCount(int violationCount) {
try
{
//writer.write("<p style='font-size:30px;'>Device Name : " + "</p>");
writer.write("<p style='font-size:30px;'>Total violation Count is : " + violationCount + "</p>");
}catch(Exception e)
{
e.printStackTrace();
}
}

private void tableSolution( String message) {
try
{
writer.write("<tr height=39 style='mso-height-source:userset;height:29.25pt'> "
+ "<td height=39 align='center' class=xl69 width=116 style='height:29.25pt;border-top:none; border-left:none;width:87pt'>"
+ "Solution</td> ");
writer.write("<td class=xl69 width=627 style='border-top:none;border-left:none;word-wrap:break-word;width:470pt'>"
+ message+"</td> </tr>");
}catch(Exception e)
{
e.printStackTrace();
}
}

private void tableTarget( String target) {
try
{
writer.write("<tr height=39 style='mso-height-source:userset;height:29.25pt'> "
+ "<td height=39 align='center' class=xl69 width=116 style='height:29.25pt;border-top:none; border-left:none;width:87pt'>"
+ "Target location</td> ");
writer.write("<td class=xl69 width=627 style='border-top:none;border-left:none;word-wrap:break-word;width:470pt'>"
+ target+"</td> </tr>");
}catch(Exception e)
{
e.printStackTrace();
}
}
private void tableTags( JSONArray tags) {
try
{
writer.write(" <tr height=39 style='mso-height-source:userset;height:29.25pt'>"
+ " <td height=39 align='center' class=xl69 width=116 style='height:29.25pt;border-top:none; border-left:none;width:87pt'>"
+ "Tags</td> ");
writer.write("<td class=xl69 width=627 style='border-top:none;border-left:none; word-wrap:break-word;width:470pt'>"
+ tags+"</td> </tr> ");
}catch(Exception e){
e.printStackTrace();
}
}

private void tableHelpURL(String helpUrl) {
try
{
writer.write("<tr height=39 style='mso-height-source:userset;height:29.25pt'> "
+ " <td height=39 align='center' class=xl69 width=116 style='height:29.25pt;border-top:none; "
+ " border-left:none;width:87pt'>Help Link</td>");
writer.write(" <td class=xl70 width=627 style='border-top:none;border-left:none; word-wrap:break-word;width:470pt'>"
+ "<a href="+helpUrl+" target='_parent'><span style='font-weight:700'>"+ helpUrl+"</span></a></td></tr>");
}catch(Exception e)
{
e.printStackTrace();
}
}

private void tableImpact(String impact) {
try
{
writer.write("<tr height=39 style='mso-height-source:userset;height:29.25pt'>"
+ " <td height=39 align='center' class=xl69 width=116 style='height:29.25pt;border-top:none; "
+ "border-left:none;width:87pt'>Impact</td>");
writer.write(" <td class=xl66 width=627 style='border-top:none;border-left:none;width:470pt;;color:red;'>"
+ impact+"</td> </tr>");
}catch(Exception e)
{
e.printStackTrace();
}
}

private void tableDescription( String description) {
try
{
writer.write("<tr height=39 style='mso-height-source:userset;height:29.25pt'>"
+ " <td height=39 class=xl69 align='center' width=116 style='height:29.25pt;border-top:none; border-left:none;width:87pt'>"
+ "Description</td>");
writer.write("<td class=xl69 width=627 style='border-top:none;border-left:none;word-wrap:break-word;width:470pt'>"
+ description+"</td></tr>");
}
catch(Exception e)
{
e.printStackTrace();
}
}

private void tableHelp( String help) {
try {
writer.write("<td class=xl69 align='center' width=116 style='border-top:none;border-left:none;width:87pt'>"
+ " Violation </td>");
writer.write("<td class=xl69 width=627 style='border-top:none;border-left:none;width:470pt'>"
+ help+"</td> </tr>");
} catch (IOException e) {
e.printStackTrace();
}
}

private void tableURL(String url,int i) {
try
{
writer.write("<td rowspan=7 height=234 class=xl72 width=31 style='height:175.5pt; border-top:none;border-left:none;width:23pt'>"+i+"</td>");
writer.write("<td rowspan=7 class=xl74 width=412 style='border-top:none;border-left:none;word-wrap:break-word;width:309pt'>"
+ "<a href="+url+" target='_parent'>"
+ url+"</a></td>");
}
catch(Exception e)
{
e.printStackTrace();
}
}

public void endHTML() throws IOException {
writer.flush();
writer.close();
}
private void tableTitle(String testname) {
try
{
writer.write("<html> <head> <title>"+testname+"</title></head>");
writer.write("<body style='Font-Family: sherif'><table width=100%><th><font color=BLUE size=5 align='center'><h4><br>Accessiblity Automated Test Result" +
"</font></th></table>" +
"</h4>");
writer.write("<table border=0 cellpadding=0 cellspacing=0 width=1186 style='border-collapse: collapse;table-layout:fixed;width:889pt'>"
+ " <col width=31 style='mso-width-source:userset;mso-width-alt:1070;width:23pt'> "
+ "<col width=412 style='mso-width-source:userset;mso-width-alt:14382;width:309pt'>"
+ " <col width=116 style='mso-width-source:userset;mso-width-alt:4049;width:87pt'>"
+ " <col width=627 style='mso-width-source:userset;mso-width-alt:21876;width:470pt'> "
+ "<tr height=25 style='height:18.5pt'> <td colspan=4 height=25 align='center' class=xl75 width=1186 style='height:18.5pt; width:889pt'><font color=BLUE size=4><h4>TestSuite : "
+testname+"</h4></td></tr>");
}
catch(Exception e){
e.printStackTrace();
}
}

}