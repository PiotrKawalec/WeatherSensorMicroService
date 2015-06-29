package io.pivotal.sensor;

import io.pivotal.sensor.messaging.GasSmokeReceiver;
import io.pivotal.sensor.messaging.TempHumidityReceiver;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WeatherSensorMicroSensorApplication {

	final static String queueName = "arduino-weather-queue";
	final static String queueNameGas = "arduino-gas-smoke-queue";
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Bean
	Queue queue() {
		return new Queue(queueName, true);
	}
	
	@Bean
	Queue queueGasSmoke() {
		return new Queue(queueNameGas, true);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("arduino-iot-exchange", true, false);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("arduino-weather");
	}

	@Bean
	Binding bindingGas(Queue queueGasSmoke, TopicExchange exchange) {
		return BindingBuilder.bind(queueGasSmoke).to(exchange).with("arduino-gas-smoke");
	}
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}
	
	@Bean
	SimpleMessageListenerContainer containerGas(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapterGas) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueNameGas);
		container.setMessageListener(listenerAdapterGas);
		return container;
	}
	
	@Bean
	TempHumidityReceiver receiver() {
        return new TempHumidityReceiver();
    }
	
	@Bean
	GasSmokeReceiver receiverGas() {
        return new GasSmokeReceiver();
    }

	@Bean
	MessageListenerAdapter listenerAdapter(TempHumidityReceiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}
	
	@Bean
	MessageListenerAdapter listenerAdapterGas(GasSmokeReceiver gasSmokeReceiver) {
		return new MessageListenerAdapter(gasSmokeReceiver, "receiveGasMessage");
	}
	
    public static void main(String[] args) {
        SpringApplication.run(WeatherSensorMicroSensorApplication.class, args);
    }
}
