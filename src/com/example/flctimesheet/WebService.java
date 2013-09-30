package com.example.flctimesheet;

import java.util.Date;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;



public class WebService {

	private static String URL = "http://ax2012r2a:8101/DynamicsAx/Services/FLCTimeSheetServiceGroup";
	private static String NAMESPACE = "http://tempuri.org";		
	private static String SOAP_ACTION = "http://tempuri.org";

	public static void invokeWS(String srId,Date from,Date to,String description,String webMethName){
		String 			resTxt 			= null;
		SoapObject 		request 		= new SoapObject(NAMESPACE,webMethName);
		PropertyInfo 	timeSheetApi 	= new PropertyInfo(); 
		
		timeSheetApi.setName("_srId");
		timeSheetApi.setValue(srId);
		timeSheetApi.setType(String.class);
		
		timeSheetApi.setName("_from");
		timeSheetApi.setValue(from);
		timeSheetApi.setType(Date.class);
		
		timeSheetApi.setName("_to");
		timeSheetApi.setValue(to);
		timeSheetApi.setType(Date.class);
		
		timeSheetApi.setName("_description");
		timeSheetApi.setValue(description);
		timeSheetApi.setType(String.class);
		
		request.addProperty(timeSheetApi);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		
		try{
			androidHttpTransport.call(SOAP_ACTION+webMethName, envelope);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			resTxt = response.toString();
		}catch(Exception e){
			e.printStackTrace();
			resTxt = "Error occured";
		}
	}
}
