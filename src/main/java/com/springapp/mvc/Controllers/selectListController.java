package com.springapp.mvc.Controllers;

import com.springapp.mvc.models.SelectListModel;
import edu.byu.oit.core.cmis.CMISInterface.CMISSessionInterface;
import edu.byu.oit.core.cmis.CMISInterface.IObjectID;
import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Controller
public class selectListController {

    private CMISSessionInterface session;

    @RequestMapping(value="/selectFromList", method = RequestMethod.GET)
    public ModelAndView loadPage(@ModelAttribute("SpringWeb")SelectListModel selectModel, ModelMap model){

        System.out.println("This just happened");

        //ESTABLISH the connection
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        session = (CMISSessionInterface)context.getBean("test");
        session.setCredentials("admin", "Iamb0b123");
        session.startSession();

        //POPULATE the dropdown model
        populateDropdown(session, selectModel, model);

        //RETURN Model and View
        return new ModelAndView("selectFromList", "command", selectModel);
    }

    private void populateDropdown(CMISSessionInterface session,SelectListModel selectModel, ModelMap model) {
        IObjectID rootFolderId = session.getObjectIdByPath("/User Homes/abbott/");
        Folder folder = session.getFolder(rootFolderId.toString());
        ItemIterable<CmisObject> items = session.getFolderContents(folder);

        ArrayList<String> folderContents = new ArrayList<String>();
        Map<String, String> folderContentMap = new HashMap<String,String>();

        for(CmisObject item : items){
            if(item.getBaseType().getId().equals("cmis:document")) {
                folderContents.add(item.getName());
                folderContentMap.put(item.getName(), item.getName());
            }
        }

        selectModel.setSelectionList(folderContents);

        //PUT dropdown options into the ModelMap model
        model.addAttribute("selectModel",folderContentMap );
    }

    @RequestMapping(value="/selectFromList", method = RequestMethod.POST)
    public ModelAndView execute(@ModelAttribute("SpringWeb")SelectListModel selectModel, ModelMap model){

        //ESTABLISH the connection
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        session = (CMISSessionInterface)context.getBean("test");
        session.setCredentials("admin", "Iamb0b123");
        session.startSession();

        //CHECK and EDIT information if needed
        boolean editPerformed=false;
        if(performEdit(selectModel)){
            editPerformed=true;

            String pathToFile = selectModel.getSelectedPath();
            Document doc = session.getDocumentByPath(pathToFile);

            if(selectModel.getNewName() != null && !selectModel.getNewName().equals("")){
                session.updateDocumentName(selectModel.getNewName(), doc.getId());

                model.addAttribute("didNameChange", true);
                model.addAttribute("newName", selectModel.getNewName());
                selectModel.setNewName(null);
            }
            if(selectModel.getNewDescription() !=null && !selectModel.getNewDescription().equals("")){
                Map<String, Object> props = new HashMap<String, Object>();
                props.put("cm:description", selectModel.getNewDescription());
                session.updateDocumentMetadataByProperty(doc, props);

                model.addAttribute("didDescriptionChange", true);
                selectModel.setNewDescription(null);
            }
        }


        //DISPLAY submitted results
        sendResultsToView(editPerformed, selectModel, session, model);

        //POPULATE the dropdown model
        populateDropdown(session, selectModel, model);

        //RETURN model and view
        return new ModelAndView("selectFromList", "command", selectModel);
    }

    private void sendResultsToView(boolean editPerformed, SelectListModel selectModel, CMISSessionInterface session, ModelMap model) {
        if(!editPerformed) {
            //DISPLAY submitted result (if no edits were performed)
            String docname = selectModel.getSelected();
            if(docname!=null) {
                IObjectID objectid = session.getObjectIdByPath("/User Homes/abbott/" + docname);
                Document doc = session.getDocument(objectid.toString());
                selectModel.setCurrentDoc(doc);

                //form urls
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

                //ADD attributes to ModelMap model
                model.addAttribute("thumbUrl", thumbUrl);
                model.addAttribute("full", fullUrl);
                model.addAttribute("docName", doc.getName());
                model.addAttribute("docDescription", doc.getProperty("cm:description").getValueAsString());
            }
            else{
                model.addAttribute("noEditsMade", true);
            }
        }
    }

    private boolean performEdit(SelectListModel model ) {
        return ((model.getNewName() != null && !model.getNewName().equals("")) || (model.getNewDescription() != null && !model.getNewDescription().equals("") ));
    }
}
