package com.springapp.mvc.Controllers;

import com.springapp.mvc.models.videoModel;
import edu.byu.oit.core.cmis.CMISInterface.CMISSessionInterface;
import edu.byu.oit.core.cmis.CMISInterface.IObjectID;
import org.apache.chemistry.opencmis.client.api.Document;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class videoController {

    CMISSessionInterface session;

    @RequestMapping(value = "/videoDemo", method = RequestMethod.GET)
    public ModelAndView loadPage(@ModelAttribute("SpringWeb")videoModel video, ModelMap model){

        //ESTABLISH a connection
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        session = (CMISSessionInterface)context.getBean("test");
        session.setCredentials("admin", "Iamb0b123");
        session.startSession();

        //SET video url
        IObjectID vidId = session.getObjectIdByPath("/User Homes/abbott/VaughanWilliamsTallisFantasia.mp4");
        Document doc = session.getDocument(vidId.toString());
        String videoUrl = session.getAlfrescoDocumentUrl(doc);

        //PUT video Url into the ModelMap
        System.out.println("Video URL: " + videoUrl);
        model.addAttribute("video", videoUrl);

        return new ModelAndView("videoDemo", "command", new videoModel());
    }
}
