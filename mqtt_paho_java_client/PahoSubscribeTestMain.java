import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class PahoSubscribeTestMain implements MqttCallback {

	public static void main(String[] args) {
		PahoSubscribeTestMain test = new PahoSubscribeTestMain();
		test.runClient();
	}
	
	public void runClient() {
		String topic = "temp/#";
		int qos = 2;
		//String broker = "tcp://test.mosquitto.org:1883";
		String broker = "tcp://192.168.56.1:1884";
		String clientId = "JavaSampleSub";
		MemoryPersistence persistence = new MemoryPersistence();
		
		try {
			MqttClient client = new MqttClient(broker, clientId, 
					persistence);
			client.setCallback(this);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			
			System.out.println("Connecting to broker: " + broker);
			client.connect(connOpts);
			System.out.println("Connected..");
			
			System.out.println("\nSubscribing a message");
			client.subscribe(topic, qos);
			
			Thread.sleep(300000);
			
			client.disconnect();
			System.out.println("Disconnected");
			System.exit(0);
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		} catch(MqttException me) {
			System.out.println("reason: " + me.getReasonCode());
			System.out.println("msg: " + me.getMessage());
			System.out.println("loc: " + me.getLocalizedMessage());
			System.out.println("cause: " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		}
	}
	
	//@Override
	public void connectionLost(Throwable t) {
		System.out.println("Connection lost!");
	}
	
	//@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("complete");
	}
	
	//@Override
	public void messageArrived(String topic, MqttMessage msg) 
				throws Exception {
		System.out.println("Topic: " + topic);
		System.out.println("Msg: " + new String(msg.getPayload()));
		System.out.println();
	}
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               