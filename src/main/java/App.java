import com.quest.repository.UnitRepository;
import com.quest.servlets.WelcomeServlet;

public class App {
    public static void main(String[] args) {
UnitRepository repository = new UnitRepository();
        System.out.println(repository.getQuestions());
    }

}
