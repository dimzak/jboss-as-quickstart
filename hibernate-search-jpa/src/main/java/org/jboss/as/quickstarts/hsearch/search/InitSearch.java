package org.jboss.as.quickstarts.hsearch.search;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.hibernate.search.jpa.FullTextEntityManager;

@Singleton
@Startup
public class InitSearch {
	
	@Inject
	@SearchQual
	private FullTextEntityManager ftem;
	
	@PostConstruct
	public void start()	{
		try {
			//remove entities before starting
			ftem.createIndexer().purgeAllOnStart(true).startAndWait();
		} 
		catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

}
