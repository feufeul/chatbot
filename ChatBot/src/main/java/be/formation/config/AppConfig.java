package be.formation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import be.formation.domain.ChatBot;

@Configuration
@EnableJpaRepositories
@PropertySource("classpath:application.properties")
public class AppConfig {
	
	@Value("${chatbot.hostname}")
	private String hostname;
	@Value("${chatbot.port}")
	private int effectivePort;
	@Value("${chatbot.password}")
	private String pass;
	@Value("${chatbot.channelName}")
	private String channelName;
	
	@Bean
	@Scope(value="singleton")
	public ChatBot chatBot() {
		ChatBot bot = new ChatBot();
		
		try {
			// Enable debugging output.
			bot.setVerbose(true);
			//TODO change authentification / name to properties
			bot.connect(hostname,effectivePort, pass);
			bot.joinChannel(channelName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bot;
	}


}
