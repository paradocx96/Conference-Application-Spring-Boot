//IT19014128
//A.M.W.W.R.L. Wataketiya

package com.rhna.conference.api;

import java.io.IOException;

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
	public HttpEntity<byte[]> getResearchPaperFile (String id){
		
		//the file is returned as per given id
		return researchPaperDataAdapter.getFileById(id);
	}
	
	//method for retrieving research paper file for given username
	public HttpEntity<byte[]> getResearchPaperByUser( String username){
		
		//the file is returned as per given username
		return researchPaperDataAdapter.getFileByUsername(username);
	}
	
	//TODO : need a method to extract research paper meta data
	
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

}
