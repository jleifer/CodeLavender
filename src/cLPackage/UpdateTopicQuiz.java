/**
 * Created by Yifang on 2/11/2017.
 */
package cLPackage;

import cLPackage.dataStore.MultipleChoices;
import cLPackage.dataStore.Topic;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdateTopicQuiz extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userIdString =req.getParameter("userId");
        String courseIdString =req.getParameter("courseId");
        String moduleIdString= req.getParameter("moduleId");
        String topicIdIdString= req.getParameter("topicId");

        String topic_name =req.getParameter("topic_name");
        String topic_description =req.getParameter("topic_description");

        Long userId = Long.parseLong(userIdString);
        Long courseId = Long.parseLong(courseIdString);
        Long moduleId = Long.parseLong(moduleIdString);
        Long topicId = Long.parseLong(topicIdIdString);
        //get the module object
        Topic topic = null;
        List<Topic> topicList = ObjectifyService.ofy().load().type(Topic.class).list();
        for (int i = 0 ; i < topicList.size(); i++){
            if(topicList.get(i).id.longValue()==topicId.longValue()){
                topic = topicList.get(i);
            }
        }
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        //update module with new attributes
        com.google.appengine.api.datastore.Key moduleKey = KeyFactory.createKey("Module", moduleId);
        Entity Topic = new Entity("Topic",topic.id,moduleKey);
        Topic.setIndexedProperty("name",topic_name);
        Topic.setIndexedProperty("content",topic_description);
        Topic.setIndexedProperty("hasTest",0);
        ObjectifyService.ofy().delete().entity(topic).now();
        datastore.put(Topic);
        //delete it

        //update quizes
        String quiz_total_num =req.getParameter("quiz_total_num");
        ArrayList<String> quiz_id_list = new ArrayList<String>();

        for(int i = 0; i<Integer.parseInt(quiz_total_num); i++){
            quiz_id_list.add(req.getParameter("quiz_id_"+i));
            Long mcId = Long.parseLong(quiz_id_list.get(i));
            MultipleChoices mc = null;
            List<MultipleChoices> mcList = ObjectifyService.ofy().load().type(MultipleChoices.class).list();
            for (int k = 0 ; k < mcList.size(); k++){
                if(mcList.get(k).id.longValue()==mcId.longValue()){
                    mc = mcList.get(k);
                }
            }
            //update module with new attributes
            com.google.appengine.api.datastore.Key TopicKey = KeyFactory.createKey("Topic", topicId);
            Entity MC = new Entity("MultipleChoices",mc.id,TopicKey);
            String quizDescription = req.getParameter("quizDescription_"+i);
            MC.setIndexedProperty("questionText",quizDescription);
            MC.setIndexedProperty("optionNumber",mc.getOptionNumber());


            int ans = Character.getNumericValue(req.getParameter("quizAns_"+i).charAt(1));
            MC.setIndexedProperty("answer",ans);
            String ooptions[]=new String[mc.getOptionNumber()];
            for(int k = 0 ; k<mc.getOptionNumber();k++){
                System.out.print("OptioNssss "+req.getParameter("quizOption_"+i+"_"+k));
                ooptions[k]= new String(req.getParameter("quizOption_"+i+"_"+k));
            }
            //if(mc.getOptionNumber()>2)
            //MC.setIndexedProperty("options",ooptions);
            ObjectifyService.ofy().delete().entity(mc).now();
            datastore.put(MC);
            //delete it and replace it with a new one
        }

        HttpSession session = req.getSession();
        session.setAttribute("userId",userId.longValue());
        session.setAttribute("courseId",courseId.longValue());
        resp.sendRedirect("newTopic?userId="+userId.longValue()+"&courseId="+courseId.longValue()
                +"&moduleId="+moduleId+"&topicId="+topicId);
    }
}