package com.baesystems.scmWebForms;

import jakarta.inject.Inject;
//import jakarta.ws.rs.BeanParam;
//import jakarta.ws.rs.Consumes;
//import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
//import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
//import static jakarta.ws.rs.core.MediaType.MULTIPART_FORM_DATA;
//import org.jboss.resteasy.reactive.MultipartForm;
//import org.jboss.resteasy.reactive.PartType;
//import org.jboss.resteasy.reactive.RestForm;
//import org.jboss.resteasy.reactive.RestMatrix;
//import org.jboss.resteasy.reactive.RestPath;
//import org.jboss.resteasy.reactive.RestQuery;

import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration.Configuration;

/*
    Remember to go to C:\artemis and type "artemis run" to run server.
    https://www.w3schools.com/css/css3_buttons.asp
*/

/**
 * A simple resource showing the last price.
 */
//@Path("/prices")
@Path("/api/v1")
public class PriceResource {

    String lastMessageType = "JH";
    String lastMessageXML = "";
    String validateMessage = "False";
    
    @Inject
    PriceConsumer consumer;

    @GET
    @Path("last")
    @Produces(MediaType.TEXT_PLAIN)
    public String last() {
        System.out.println("LP11");
        return consumer.getLastPrice();
    }
    
    @GET
    @Path("message")
    public void message(
            @QueryParam("messagename") String messageName,
            @QueryParam("validateCB") String messageValidate,
            @QueryParam("messagexml") String messageXml) {
        lastMessageType = messageName;
        validateMessage = messageValidate;
        lastMessageXML  = messageXml;
        System.out.println("Message Name      ="+messageName);
        System.out.println("Message Validate  ="+messageValidate);
        System.out.println("Message XML       ="+messageXml);
        if ("True".equals(messageValidate)) {
            System.out.println("Validate the message");
        } else {
            System.out.println("Don't validate the message");            
        }
    }

    @GET
    @Path("lastmessagetype")
    @Produces(MediaType.TEXT_PLAIN)
    public String lastMessageType() {
        System.out.println("LMT="+lastMessageType);
        return lastMessageType;
    }

    @GET
    @Path("lastmessagexml")
    @Produces(MediaType.TEXT_PLAIN)
    public String lastMessageXML() {
        System.out.println("LMX="+lastMessageType);
        return lastMessageXML;
    }
    
    
    
    
    
    
    /*
        API for SCMWebForms SendXML used primarily for TestComplete or any other App to send an XML message
        example URL to send a message:
            localhost:8080/api/v1/SendXML?messageName="message Name"&filenameXml="Auto XML Message which could be really big string"
    */
    @GET
    @Path("SendXML")
    public String sendXML(
            @QueryParam("messagename") String messageName,
            @QueryParam("filenamexml") String filenameXml) {
        lastMessageType = messageName;
        lastMessageXML  = filenameXml;
        System.out.println("Message Name ="+messageName);
        System.out.println("Filename XML ="+filenameXml);
        
        return "OK";
    }

    
}
