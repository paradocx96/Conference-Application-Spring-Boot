package com.rhna.conference.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.multipart.MultipartFile;

import com.rhna.conference.domain.ResearchPaper;
import com.rhna.conference.domain.ResearchPaperDataAdapter;

public class ResearchPaperAPI {
	
	private final ResearchPaperDataAdapter  researchPaperDataAdapter;
	
	@Autowired
	public ResearchPaperAPI(ResearchPaperDataAdapter researchPaperDataAdapter) {
		this.researchPaperDataAdapter = researchPaperDataAdapter;
	}
	
	//method for adding a research paper
	public String addResearchPaper (ResearchPaper researchPaper, MultipartFile  multipartFile  ) {
		
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

}
