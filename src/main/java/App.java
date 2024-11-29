import com.quest.entity.Difficulty;
import com.quest.entity.Quest;
import com.quest.repository.UnitRepository;
import com.quest.repository.UserRepository;
import com.quest.services.UserService;
import com.quest.utils.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {
    public static void main(String[] args) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = null;

       try{
          transaction = session.beginTransaction();
           Quest quest = new Quest();
           quest.setName("Кораблекрушение");
           quest.setDescription("Квест о потерпевшем кораблекрушение");
           quest.setSuccess("Вы прошли все испытания!");
           quest.setFailure("Вы проиграли");
           quest.setDifficulty(Difficulty.EASY); // Предполагается, что Difficulty — это перечисление

           // Сохранение объекта, что вызовет установку createdAt
           session.save(quest);

           transaction.commit();
       } catch (Exception e){
           if (transaction != null) transaction.rollback();
           e.printStackTrace();
       } finally {
           session.close();
       }
    }

}
