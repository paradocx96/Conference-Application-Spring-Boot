//IT19014128
//A.M.W.W.R.L. Wataketiya

package com.rhna.conference.domain;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.web.multipart.MultipartFile;

import com.rhna.conference.dal.model.ResearchPaperModel;

public interface ResearchPaperDataAdapter {
	
	//saves a file as a multi part file along with an object containing data
	public String save(ResearchPaper researchPaper, MultipartFile multipartFile) throws IOException;
	//returns byte array for given id
	public HttpEntity<byte[]> getFileById (String id);
	
	public HttpEntity<byte[]> getFileByUsername (String username);
	
	public ResearchPaperModel getResearchPaperModelByUsername (String username);
	
	public List<ResearchPaperModel> getAllResearchPaperModels ();

}
