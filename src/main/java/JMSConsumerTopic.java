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

public class JMSConsumerTopic implements MessageListener {
	private static final String TOPIC_NAME = "Message";
	private static final String URL = "tcp://localhost:61616";
	
	public static void main(String[] args) throws JMSException 
	{
		ConnectionFactory factory = new ActiveMQConnectionFactory(URL);
		System.out.println("Creating Connection factory object");

		Connection connection = factory.createConnection();
		connection.start();
		System.out.println("Creating Connection via factory");

		Session session = connection.createSession(false, 
		Session.AUTO_ACKNOWLEDGE);
		System.out.println("Creating Session via Connection");
		
		Destination destination = session.createTopic(TOPIC_NAME);
		System.out.println("Creating Queue via Session");
		
		MessageConsumer message =session.createConsumer(destination);
		message.setMessageListener(new JMSConsumerTopic());
		
	}

	@Override
	public void onMessage(Message msg)
	{
		TextMessage textMessage=(TextMessage)msg;
		try {
			System.out.println(textMessage.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
