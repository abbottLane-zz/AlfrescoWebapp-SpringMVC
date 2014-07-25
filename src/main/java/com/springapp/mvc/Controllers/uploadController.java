package com.springapp.mvc.Controllers;

import com.springapp.mvc.models.uploadRequestModel;
import edu.byu.oit.core.cmis.CMISInterface.CMISSessionInterface;
import edu.byu.oit.core.cmis.CMISInterface.IObjectID;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class uploadController {
    private CMISSessionInterface session;

    @RequestMapping(value="/upload", method = RequestMethod.GET)
    public ModelAndView uploadView() {
        return new ModelAndView("upload", "command", new uploadRequestModel());
    }

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public ModelAndView execute(@ModelAttribute("SpringWeb")uploadRequestModel uploadModel, ModelMap model){

        //ESTABLISH the connection
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        session = (CMISSessionInterface)context.getBean("test");
        session.setCredentials("admin", "Iamb0b123");
        session.startSession();

        //UPLOAD the file
        IObjectID folderId = session.getObjectIdByPath("/User Homes/abbott/");
        String fileName = extractFilename(uploadModel.getUploadPath());

        ContentStream cs = session.createDocument(fileName,uploadModel.getUploadPath());
        Document doc = session.uploadDocument(folderId.toString(),fileName,cs,null, "1.0", uploadModel.getDescription()); //"cmis:document" was where null is

        //FORM thumb/doc urls based on docMimeType:
        String thumbUrl;
        if(session.doesAlfrescoThumbnailExist(doc)) {
            thumbUrl = session.getAlfrescoThumbnailUrl(doc);
        }
        else { // allow for the use of custom thumbnail images
            if (doc.getContentStreamMimeType().contains("text")) {
                thumbUrl = "/resources/additionalResources/customThumbImages/docThumb.png";
            } else if (doc.getContentStreamMimeType().contains("audio")) {
                thumbUrl = "/resources/additionalResources/customThumbImages/musicThumb.png";
            } else if (doc.getContentStreamMimeType().contains("video")) {
                thumbUrl = " /resources/additionalResources/customThumbImages/videoThumb.png";
            } else {
                thumbUrl = "/resources/additionalResources/customThumbImages/docThumb.png";
            }
        }
        String fullUrl = session.getAlfrescoDocumentUrl(doc);

        /////LOAD relevant data into the ModelMap model
        model.addAttribute("docName", doc.getName());
        model.addAttribute("thumbUrl", thumbUrl);
        model.addAttribute("fullUrl", fullUrl);

        //Return the model and view
        return new ModelAndView("upload", "command", new uploadRequestModel());
    }

    private String extractFilename(String filePath) {

        String[] split = filePath.split("/");
        String filename = split[split.length-1];

        return filename;
    }


}

