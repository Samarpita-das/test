package com.teamsankya.javamessagingscript.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSProducerTopic {
	private static final String TOPIC_NAME = "Message";
	private static final String URL = "tcp://localhost:61616";
	public static void main(String[] args) throws JMSException {
		ConnectionFactory factory = new ActiveMQConnectionFactory(URL);
		System.out.println("Creating Connection factory object");

		Connection connection = factory.createConnection();
		connection.start();
		System.out.println("Creating Connection via factory");

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		System.out.println("Creating Session via Connection");

		Destination destination = session.createTopic(TOPIC_NAME);
 		System.out.println("Creating Queue via Session");

		TextMessage message = session.createTextMessage("How you doing!!");

		System.out.println("Creating TextMessage via Session");
		
		MessageProducer producer = session.createProducer(destination);
		System.out.println("Creating MessageProducer via session");
		
		producer.send(message);
		System.out.println("Sending the message the ActiveMQ using Produser");
		connection.close();

	}

}
