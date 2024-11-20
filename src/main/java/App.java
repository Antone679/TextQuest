import com.quest.repository.UnitRepository;
import com.quest.repository.UserRepository;
import com.quest.services.UserService;

public class App {
    public static void main(String[] args) {
        UserRepository repository = new UserRepository();
        UserService service = new UserService(repository);

        service.saveUser("Anton", "12345", "anton@mail.com");
    }

}
