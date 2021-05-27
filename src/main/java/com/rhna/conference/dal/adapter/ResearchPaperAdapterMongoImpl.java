//IT19014128
//A.M.W.W.R.L. Wataketiya

package com.rhna.conference.dal.adapter;

import java.io.IOException;
import java.util.List;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.rhna.conference.dal.model.ResearchPaperModel;
import com.rhna.conference.dal.repository.ResearchPaperMongoRepository;
import com.rhna.conference.domain.ResearchPaper;
import com.rhna.conference.domain.ResearchPaperDataAdapter;

@Component
public class ResearchPaperAdapterMongoImpl implements ResearchPaperDataAdapter {
	
	private final ResearchPaperMongoRepository researchPaperMongoRepo;
	private final MongoTemplate mongoTemplate;
	
	@Autowired
	public ResearchPaperAdapterMongoImpl(ResearchPaperMongoRepository researchPaperMongoRepo, 
			MongoTemplate mongoTemplate) {
		this.researchPaperMongoRepo = researchPaperMongoRepo;
		this.mongoTemplate = mongoTemplate;
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

	//find research paper model for username
	@Override
	public ResearchPaperModel getResearchPaperModelByUsername(String username) {
		System.out.println("Returning research paper model for username : " +username);
		return researchPaperMongoRepo.findByUsername(username);
	}

	//get all the research paper models
	@Override
	public List<ResearchPaperModel> getAllResearchPaperModels() {
		System.out.println("Returning all models of research papers");
		List<ResearchPaperModel> researchPaperModelList = researchPaperMongoRepo.findAll();
		return researchPaperModelList;
	}

	//update the research paper status
	@Override
	public ResearchPaper updateStatus(ResearchPaper researchPaper) {
		
		//use mongo template
		//find and  modify research paper status by id
		ResearchPaperModel researchPaperModel = 
				mongoTemplate.findAndModify(Query.query(Criteria.where("id").is(researchPaper.getId())),
						new Update().set("status", researchPaper.getStatus()), ResearchPaperModel.class);
		
		//check if the research paper is null
		if(researchPaperModel != null) {
			//set the details
			researchPaper.setTitle(researchPaperModel.getTitle());
			researchPaper.setUsername(researchPaperModel.getUsername());
			researchPaper.setEmail(researchPaperModel.getEmail());
			
			//return the research paper
			return researchPaper;
		}
		else {
			return null;
		}
		
	}

	//deletes a research paper on given id.
	@Override
	public String deleteById(String id) {
		//perform delete
		researchPaperMongoRepo.deleteById(id);
		
		System.out.println("Deleting research paper with id : "+id);
		
		//return the id
		return id;
	}

	
}
