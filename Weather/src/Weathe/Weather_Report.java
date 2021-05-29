package Weathe;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonParser;

public class Weather_Report<JSONStr> 
{

	private static final JsonParser JSONStr = null;

	public static void main(String args[])
	{

		Weather_Report app = new Weather_Report();
		try {
			app.demo();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private <WeatherObject> void demo ( ) throws ParseException
	{
		

		JSONParser jsonParser = new JSONParser();
		try
		{
			


			String url1="https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22" ; // 79843 = US postal Zip Code for Marfa, Texas.
			URL url =new URL(url1);
			URLConnection con = url.openConnection();
			BufferedReader reader = new BufferedReader( new InputStreamReader( con.getInputStream() ) );


			
			JSONObject jsonObject = ( JSONObject ) jsonParser.parse( reader );
			System.out.println( "jsonObject = " + jsonObject );
			JSONArray list = ( JSONArray ) jsonObject.get( "list" );
			System.out.println( "list = " + list);
			 while(true)
			 {
				 for ( Object o : list )
				 {
					 Scanner sca = new Scanner(System.in);

					 System.out.println("Choose below options for Getting London location weather data ");
					 System.out.println("1. Get weather");
					 System.out.println("2.Get Wind Speed");
					 System.out.println("3.Get Pressure");
					 System.out.println("0. Exit ");
					 		
					 int a =sca.nextInt();
					 switch(a) 
					 {
					 case 1:

						 try {
							 System.out.println("Enter Date In This format : yyyy-MM-dd HH:mm:ss");
							 sca.nextLine();
							 String current1 =sca.nextLine();
							// System.out.println(current1);
							 
							 DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
							 LocalDateTime dateTime2 = LocalDateTime.parse(current1, formatter1);
								
							 Object matchedobject = getdMatchedobject(list,dateTime2);
							 //System.out.println(matchedobject); // if you want to print that weatherobject based on your date 
							 if(matchedobject==null) {
								 System.out.println("No data with the Specified Date in given Url.");
							 }else {
								 JSONObject forecast1 = ( JSONObject ) matchedobject;
								 JSONObject main1 = ( JSONObject ) forecast1.get( "main" );
								 Object obj1 = main1.get("temp");
								 System.out.println("Temparature : "+obj1);
							 }
					
						 }
						 catch (DateTimeParseException e) {
							// e.printStackTrace();
							 System.out.println("Entered Date is in wrong format");
						 }
						 
						 break;
					 case 2:

						 try {
							 System.out.println("Enter Date In This format : yyyy-MM-dd HH:mm:ss");
							 

							 sca.nextLine();

							 String current2= sca.nextLine();
 
							 DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
							 LocalDateTime dateTime2 = LocalDateTime.parse(current2, formatter1);
							 Object matchedobject = getdMatchedobject(list,dateTime2);
							// System.out.println(matchedobject);// if you want to print that weatherobject based on your date 
							 if(matchedobject==null)
							 {
								 System.out.println("No data with the Specified Date in give Url.");
							 }
							 else
							 {
								 JSONObject forecast2 = ( JSONObject ) matchedobject;
								 JSONObject wind = ( JSONObject ) forecast2.get( "wind" );
								 
								 Object speed= wind.get( "speed" );
								 System.out.println( " wind speed : " + speed );
							 }
						 }
					 
					 catch (DateTimeParseException e) {
						  System.out.println("Entered Date is in wrong format");
						 //e.printStackTrace();
					 }

					 break;
					 case 3:

						 try {
							 System.out.println("Enter Date In This format : yyyy-MM-dd HH:mm:ss");
							 sca.nextLine();
							 
							 String current3= sca.nextLine();

							 DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
							 LocalDateTime dateTime2 = LocalDateTime.parse(current3, formatter1);
							 Object matchedobject = getdMatchedobject(list,dateTime2);
							 //System.out.println(matchedobject);// if you want to print that weatherobject based on your date 
							 if(matchedobject==null)
							 {   
								 System.out.println("No data with the Specified Date in given Url.");
							 }

							 else
							 {
								 
								 JSONObject forecast1 = ( JSONObject ) matchedobject;
								 JSONObject main = ( JSONObject ) forecast1.get( "main" );
								 Double press = ( Double ) main.get( "pressure" );  
								 System.out.println( "pressure : " + press);


							 }
						 }
						 catch (DateTimeParseException e) {
			
							 System.out.println("Entered Date is in wrong format");
							 //e.printStackTrace();
						 }
	                    break;
					 case 0:
						 System.exit(0);
						 //
						 System.out.println( "\n" );
					 }
				 }
			 }
		}

		catch ( FileNotFoundException e )
		{
			e.printStackTrace();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}
	
	public static Object getdMatchedobject(JSONArray list, LocalDateTime date) {
		for ( Object o : list )
		 {
			
			 JSONObject forecast = ( JSONObject ) o;

			 Object dt = forecast.get("dt_txt");
			 String date1 = (String)dt;
			
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			 LocalDateTime dateTime1 = LocalDateTime.parse(date1, formatter);
			 if(date.compareTo(dateTime1)==0) {
				 return o;
			 }
			 
		 }
		return null;
	

					 }

				 }
			 