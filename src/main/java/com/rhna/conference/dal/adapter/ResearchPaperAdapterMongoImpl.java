package com.rhna.conference.dal.adapter;

import java.io.IOException;


import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.rhna.conference.dal.model.ResearchPaperModel;
import com.rhna.conference.dal.repository.ResearchPaperMongoRepository;
import com.rhna.conference.domain.ResearchPaper;
import com.rhna.conference.domain.ResearchPaperDataAdapter;

public class ResearchPaperAdapterMongoImpl implements ResearchPaperDataAdapter {
	
	private ResearchPaperMongoRepository researchPaperMongoRepo;
	
	@Autowired
	public ResearchPaperAdapterMongoImpl(ResearchPaperMongoRepository researchPaperMongoRepo) {
		this.researchPaperMongoRepo = researchPaperMongoRepo;
	}

	@Override
	public String save(ResearchPaper researchPaper, MultipartFile multipartFile) throws IOException {
		
		//instantiate an object
		ResearchPaperModel researchPaperModel = new ResearchPaperModel();
		
		//set research paper data
		researchPaperModel.setTitle(researchPaper.getTitle());
		researchPaperModel.setUsername(researchPaper.getUsername());
		researchPaperModel.setEmail(researchPaper.getEmail());
		researchPaperModel.setStatus(researchPaper.getStatus());
		
		//save the file as binary
		researchPaperModel.setResearchPaper(
				new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes() ));
		
		//insert the object into database
		researchPaperModel = researchPaperMongoRepo.insert(researchPaperModel);
		
		// return the generated id
		return researchPaperModel.getId();
	}

	@Override
	public HttpEntity<byte[]> getFileById(String id) {
		//instantiate object
		ResearchPaperModel researchPaperModel = new ResearchPaperModel();
		
		//find the object of research paper by id
		researchPaperModel = researchPaperMongoRepo.findById(id).get();
		
		//get the research paper as a BSON binary object
		Binary researchPaper = researchPaperModel.getResearchPaper();
		
		//set the title as file name 
		String fileName = researchPaperModel.getTitle();
		
		if(researchPaper != null) {
			HttpHeaders headers = new HttpHeaders();
			
			//set the content type to octet stream
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			
			//set content disposition
			ContentDisposition contentDisposition = ContentDisposition.builder("inline")
					.filename(fileName).build();
			
			//set the content disposition to headers
			headers.setContentDisposition(contentDisposition);
			
			//return the byte array as an http entity
			return new HttpEntity<byte[]>(researchPaper.getData(),headers);
		}
		else {
			//error if the retrieved research paper is null.
			System.out.println("Retrieved research paper document is null.");
			return null;
		}
		
	}

	@Override
	public HttpEntity<byte[]> getFileByUsername(String username) {
		//instantiate object
		ResearchPaperModel researchPaperModel = new ResearchPaperModel();
		
		//get the research paper by username
		researchPaperModel = researchPaperMongoRepo.findByUsername(username);
		
		
		//get the document as BSON a binary object
		Binary researchPaper = researchPaperModel.getResearchPaper();
		
		//set the title as file name
		String fileName = researchPaperModel.getTitle();
		
		if(researchPaper != null) {
			
			HttpHeaders headers = new HttpHeaders();
			
			//set the content type to octet stream
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			
			//set content disposition
			ContentDisposition contentDisposition = ContentDisposition.builder("inline")
					.filename(fileName).build();
			
			//set the content disposition to headers
			headers.setContentDisposition(contentDisposition);
			
			//return the byte array as an http entity
			return new HttpEntity<byte[]>(researchPaper.getData(),headers);
		}
		else {
			//error if the retrieved research paper is null.
			System.out.println("Retrieved research paper document is null.");
			return null;
		}
	}

}
