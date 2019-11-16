package af.asr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

	static TestData testData = new TestData();

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
		System.out.print("TestData:   "+ testData.message);
	}


}
