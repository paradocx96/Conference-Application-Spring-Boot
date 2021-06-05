//IT19014128
//A.M.W.W.R.L. Wataketiya

package com.rhna.conference.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rhna.conference.dal.model.ResearchPaperModel;
import com.rhna.conference.domain.ResearchPaper;
import com.rhna.conference.domain.ResearchPaperDataAdapter;

@Service
public class ResearchPaperAPI {
	
	private final ResearchPaperDataAdapter  researchPaperDataAdapter;
	
	@Autowired
	public ResearchPaperAPI(ResearchPaperDataAdapter researchPaperDataAdapter) {
		this.researchPaperDataAdapter = researchPaperDataAdapter;
	}
	
	//method for adding a research paper
	public String addResearchPaper ( MultipartFile  multipartFile,
			String username, String email, String title) {
		
		//creating research paper object
		ResearchPaper researchPaper = new ResearchPaper();
		
		researchPaper.setUsername(username);
		researchPaper.setEmail(email);
		researchPaper.setTitle(title);
		researchPaper.setStatus("pending");
		
		//variable to receive the generated id
		String id;
		
		
		try {
			//save the file and related data using the adapter
			id = researchPaperDataAdapter.save(researchPaper, multipartFile);
			return id;  //return the id
		} catch (IOException e) {
			//handle the thrown exception
			System.out.println("Exception in adding research paper in API : " + e);
			e.printStackTrace();
			return "noId";
		}
		
	}
	
	//method for retrieving research paper file
	public HttpEntity<byte[]> getResearchPaperFileById (String id){
		
		//the file is returned as per given id
		return researchPaperDataAdapter.getFileById(id);
	}
	
	//method for retrieving research paper file for given username
	public HttpEntity<byte[]> getResearchPaperByUser( String username){
		
		//the file is returned as per given username
		return researchPaperDataAdapter.getFileByUsername(username);
	}
	
	
	//get research paper details by username
	public ResearchPaper getResearchPaperDetails (String username) {
		
		//instantiate objects
		ResearchPaper researchPaper = new ResearchPaper();
		ResearchPaperModel researchPaperModel = new ResearchPaperModel();
		
		//get the model for username
		researchPaperModel = 
				researchPaperDataAdapter.getResearchPaperModelByUsername(username);
		
		//set the attributes
		researchPaper.setId(researchPaperModel.getId());
		researchPaper.setUsername(username);
		researchPaper.setEmail(researchPaperModel.getEmail());
		researchPaper.setTitle(researchPaperModel.getTitle());
		researchPaper.setStatus(researchPaperModel.getStatus());
		
		//return the object
		return researchPaper;
	}
	
	public List<ResearchPaper> getAllResearchPaperDetails (){
		
		//instantiate objects
		List<ResearchPaper> researchPaperList = new ArrayList<ResearchPaper>();
		List<ResearchPaperModel> researchPaperModelList = new ArrayList<ResearchPaperModel>();
		
		//get the research paper model list through the adapter
		researchPaperModelList = researchPaperDataAdapter.getAllResearchPaperModels();
		
		//loop through the research paper mode list
		for(ResearchPaperModel rpModel : researchPaperModelList) {
			
			//instantiate research paper object
			ResearchPaper researchPaper = new ResearchPaper();
			
			//set the attributes from the model object to research paper object
			researchPaper.setId(rpModel.getId());
			researchPaper.setUsername(rpModel.getUsername());
			researchPaper.setEmail(rpModel.getEmail());
			researchPaper.setTitle(rpModel.getTitle());
			researchPaper.setStatus(rpModel.getStatus());
			
			//add the research paper to the research paper list
			researchPaperList.add(researchPaper);
		}
		
		//return the research paper list
		return researchPaperList;
	}
	
	//update the status of the research paper through the id
	public ResearchPaper updateStatus( String id, String status) {
		//instantiate research paper object
		ResearchPaper researchPaper = new ResearchPaper();
		
		//set the id and status
		researchPaper.setId(id);
		researchPaper.setStatus(status);
		
		//update the status
		researchPaper =  researchPaperDataAdapter.updateStatus(researchPaper);
		
		//return the researchPaper
		return researchPaper;
		
	}
	
	//update the file by id
	public ResearchPaper updateFileById (String id, MultipartFile multipartFile) {
		//instantiate research paper objects
		ResearchPaper researchPaper = new ResearchPaper();
		ResearchPaperModel researchPaperModel = new ResearchPaperModel();
		
		try {
			researchPaperModel =   researchPaperDataAdapter.updateFileById(id, multipartFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		researchPaper.setId(researchPaperModel.getId());
		researchPaper.setEmail(researchPaperModel.getEmail());
		researchPaper.setUsername(researchPaperModel.getUsername());
		researchPaper.setTitle(researchPaperModel.getTitle());
		researchPaper.setStatus(researchPaperModel.getStatus());
		
		return researchPaper;
	}
	
	//delete the research paper based on id
	public String deletePaperById(String id) {
		return researchPaperDataAdapter.deleteById(id);
	}

}
