import org.springframework.web.client.RestClient;

public class PasswordClient {
	RestClient restClient;
	String baseURI = "http://cs-hydra.centre.edu:9000/";
	
	public int requestPassword(String name) {
		String requestURI = "http://cs-hydra.centre.edu:9000/request/";
		String fullURI = requestURI + name;

        String result = this.restClient.get().uri(fullURI).retrieve().body(String.class);
        int password = Integer.parseInt(result);
        return password;
	}
	
	public String authenticate(String name, int password) {
		String authURI = "http://cs-hydra.centre.edu:9000/auth/";
		String fullURI = authURI + name + "/" + password;
		
		String result = this.restClient.get().uri(fullURI).retrieve().body(String.class);
		return result;
	}
	
	public void login(String name) {
		int password = this.requestPassword(name);
		String result = this.authenticate(name, password);
		System.out.println(result);
	}

	public PasswordClient() {
		super();
		this.restClient = RestClient.create();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PasswordClient client = new PasswordClient();
		
		client.login("bobby");
        
	}

}
