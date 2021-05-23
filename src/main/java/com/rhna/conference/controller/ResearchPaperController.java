package com.rhna.conference.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rhna.conference.api.ResearchPaperAPI;

@RestController
@RequestMapping("researchpaper")
public class ResearchPaperController {
	
	private ResearchPaperAPI researchPaperAPI;
	
	@Autowired
	public ResearchPaperController(ResearchPaperAPI researchPaperAPI) {
		this.researchPaperAPI = researchPaperAPI;
	}
	
	

}
