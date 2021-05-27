//IT19014128
//A.M.W.W.R.L. Wataketiya

package com.rhna.conference.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rhna.conference.api.ResearchPaperAPI;
import com.rhna.conference.domain.ResearchPaper;

@RestController
@RequestMapping("researchpaper/")
@CrossOrigin(origins ="*",allowedHeaders = "*",exposedHeaders = "*")
public class ResearchPaperController {
	
	private ResearchPaperAPI researchPaperAPI;
	
	@Autowired
	public ResearchPaperController(ResearchPaperAPI researchPaperAPI) {
		this.researchPaperAPI = researchPaperAPI;
	}
	
	//method for uploading a research paper
	@PostMapping("upload")
	public String uploadFile ( @RequestParam("file") MultipartFile multipartFile, 
			@RequestParam("username") String username, @RequestParam("email") String email,
			@RequestParam("title") String title) {
		
		//store the generated id in string 
		String id = 
				researchPaperAPI.addResearchPaper(multipartFile, username, email, title);
		
		//return the id to the web client.
		return id;
	}
	
	//method to get research paper file for a given username
	@PostMapping(value="downloadByUsername")
	public HttpEntity<byte[]> getResearchPaperFileByUsername (
			@RequestParam("username") String username){
		
		return researchPaperAPI.getResearchPaperByUser(username);
		
	}
	
	//method to get the research paper file for a given id
	@PostMapping(value="downloadById")
	public HttpEntity<byte[] > getResearchPaperById (@RequestParam("id") String id){
		return researchPaperAPI.getResearchPaperFileById(id);
	}
	
	//method to get research paper meta data
	@GetMapping(value="getResearchPaperDetailsByUsername/{username}")
	public ResearchPaper getResearchPaperDetails( @PathVariable String username) {
		
		// get research paper meta data
		return researchPaperAPI.getResearchPaperDetails(username);
		
	}
	
	//method to get all research papers
	@GetMapping(value="getAllResearchpapers")
	public List<ResearchPaper> getAllResearchPapers (){
		
		//get all research papers
		return researchPaperAPI.getAllResearchPaperDetails();
	}
	
	//method for updating the research paper status
	@PutMapping(value="updateStatus")
	public ResearchPaper updateStatus( @RequestParam("id") String id, @RequestParam ("status") String status) {
		
		//update the research paper status
		return researchPaperAPI.updateStatus(id, status);
	}
	
	@DeleteMapping(value="deletePaper/{id}")
	public String deletePaperById ( @PathVariable("id")  String id) {
		
		//delete the paper and return the id
		return researchPaperAPI.deletePaperById(id);
	}
	

}
