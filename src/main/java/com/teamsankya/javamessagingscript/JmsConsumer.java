package com.teamsankya.javamessagingscript;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsConsumer implements MessageListener {
	private static final String QUEUE_NAME = "Message";
	private static final String URL = "tcp://localhost:61616";

	public static void main(String[] args) throws JMSException {
		ConnectionFactory factory = new ActiveMQConnectionFactory(URL);
		System.out.println("Creating Connection factory object");

		Connection connection = factory.createConnection();
		connection.start();
		System.out.println("Creating Connection via factory");

		Session session = connection.createSession(false, 
		Session.AUTO_ACKNOWLEDGE);
		System.out.println("Creating Session via Connection");
		
		Destination destination = session.createQueue(QUEUE_NAME);
		System.out.println("Creating Queue via Session");
		
		MessageConsumer consumer = session.createConsumer(destination);
		System.out.println("Creating Queue using Queue");
		
		Message message = consumer.receive();
		System.out.println("Reciving Message from the Queue");
		
		TextMessage textMessage = (TextMessage)message;
		System.out.println(textMessage.getText());
		
		session.close();
		connection.close();
		}

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		
	}

}
