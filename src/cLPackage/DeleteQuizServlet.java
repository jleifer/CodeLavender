package cLPackage;

import cLPackage.dataStore.MultipleChoices;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by AjaxSurangama on 4/21/2017.
 * DeleteQuizServlet
 * The purpose of this servlet is to allow a user to delete a quiz that they own.
 * This will also delete references to the quiz, such as from user progress.
 */
public class DeleteQuizServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Obtaining data from forms.
        String topicIdString = req.getParameter("topicId");
        String userIdString = req.getParameter("userId");
        String courseIdString = req.getParameter("courseId");
        String moduleIdString = req.getParameter("moduleId");
        String quizIdString = req.getParameter("quizId");
        Long quizId = Long.parseLong(quizIdString);

        //Obtain the quiz from the datastore.
        MultipleChoices quiz = null;
        List<MultipleChoices> quizList = ObjectifyService.ofy().load().type(MultipleChoices.class).list();
        for (int i = 0 ; i < quizList.size(); i++){
            if(quizList.get(i).id.longValue()==quizId.longValue()){
                quiz = quizList.get(i);
            }
        }
        System.out.print("deleting "+quiz.id);
        //delete it
        ObjectifyService.ofy().delete().entity(quiz).now();

        //Return the user to the topic.
        HttpSession session = req.getSession();
        resp.sendRedirect("newTopic?userId="+userIdString+"&courseId="+courseIdString+"&moduleId="+moduleIdString+"&topicId="+topicIdString);

    }
}
