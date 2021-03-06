package com.codegroup.challengecloud.controllers;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;




//import com.codegroup.challengecloud.model.User;
//import com.codegroup.challengecloud.services.UserService;
import com.codegroup.challengecloud.model.Challenge;
import com.codegroup.challengecloud.model.User;
import com.codegroup.challengecloud.services.ChallengeService;
import com.codegroup.challengecloud.services.SubscriptionService;
import com.codegroup.challengecloud.services.UserService;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;




/* For FreeMarker */
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

/**
 * @author Vladimir Zhdanov
 *         Created 08.03.2015
 */

@Controller
public class ChallengesController {
    private static final Logger log = Logger.getLogger(ChallengesController.class);
    @Resource
    private ChallengeService challengeService;
    @Resource
    private UserService userService;
    @Resource
    SubscriptionService subscriptionService;

    @RequestMapping("/challenges")
    public ModelAndView challengesText() {
    	Map <String,String> map = new HashMap();
    	map.put("message", "Challenges"); 
    	map.put("max_count_html", Integer.toString(challengeService.findAll().size()) );
        //return new ModelAndView("challenges", "message", "Challenges");
        return new ModelAndView("challenges", map);
    }

    /*TODO delete unused test*/
    @RequestMapping(value = "/challengestest", method = RequestMethod.GET)
    public
    @ResponseBody
    String getTime() {

        Random rand = new Random();
        float r = rand.nextFloat() * 100;
        String result = "<br>Next Random # is <b>" + r + "</b>. <br> Generated on <b>" + new Date().toString() + "</b>";
        System.out.println("Debug Message from CrunchifySpringAjaxJQuery Controller.." + new Date().toString());
        return result;
    }

    /**
     * @author Vladimir Zhdanov
     * created on 19.03.2015
     * This method puts values of given challenge into map. Field names you can find in .ftl file.
     * @param Map<String, Object> Map, in witch items are put
     * @param Challenge from that entity information is taken
     */
    private void putChallenge(Map<String, Object> input, Challenge challenge) {

        input.put("challengeId", challenge.getId());
        input.put("challengeName", challenge.getTitle());
        input.put("challengeDescription", challenge.getDescription());
        input.put("imageId", challenge.getImage().getId());
        input.put("challengeDifficulty", challenge.getDifficulty().toString());
        input.put("challengeHashtag", challenge.getHashtag());
    }
    
    @RequestMapping(value = "/challenges-all", method = RequestMethod.GET)
    public
    @ResponseBody
    String getAllChallenges(
            @RequestParam(value = "num", required = true) String num,
            @RequestParam(value = "count", required = true) String count) {
        log.info("getAllChallenges()");
        String code = "<p> Internal Error! </p>";
        int numi = Integer.parseInt(num);
        int counti = Integer.parseInt(count);
        Map<String, Object> input = new HashMap<String, Object>();
        List<Challenge> challenges = challengeService.findAll();
        StringWriter stringWriter;
        Configuration cfg = new Configuration();
        
        cfg.setClassForTemplateLoading(ChallengesController.class, "/");// TODO /templates
        
        try {
            Template template = cfg.getTemplate("challenge-view.ftl");
            stringWriter = new StringWriter();
            try {
        		// i is from 1 to challenges.size()
        		// If we want second element, we should have at least two
                for (int i = numi; ((i < numi + counti) && (i <= challenges.size())) ; i++) {
                    input.clear();
                    putChallenge(input, challenges.get(i-1));// Very important!
                    //input.put("challengeName", "Challenge " + Integer.toString(numi + i));
                    //input.put("challengeDescription", "Challenge Description " + Integer.toString(numi + i));
                    template.process(input, stringWriter);
                }
            } catch (TemplateException e2) {
                log.error("Template Exception.");
            } finally {
                stringWriter.close();
                code = stringWriter.toString();
            }
        } catch (IOException e) {
            log.error("Can't load template: IOException.");
        }
        //log.info("getAllChallenges() returns [" + code + "].");
        return code;
    }
    
    @RequestMapping(value = "/challenges-subscribe", method = RequestMethod.GET)
    public
    @ResponseBody
    String subscribeChallenge(
            @RequestParam(value = "id", required = true) String id) {
        log.info("subscribeChallenge()");
        String code = "<p> Internal Error! </p>";
        User currentUser = userService.getCurrentUser();
        Challenge currentChallenge = challengeService.findById(id);
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        if ((currentChallenge != null) && (currentUser != null)) {
        	subscriptionService.createSubscription(currentUser, currentChallenge, sqlDate);
        	code = "You subscribed the challenge";
        } else {
        	code = "Sorry, but for unknown reason You failed to subscribe the challenge";
        }
        return code;
    }
}