/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.hsearch.test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.as.quickstarts.hsearch.Resources;
import org.jboss.as.quickstarts.hsearch.controller.SearchController;
import org.jboss.as.quickstarts.hsearch.dao.QuoteDao;
import org.jboss.as.quickstarts.hsearch.dao.QuoteDaoImpl;
import org.jboss.as.quickstarts.hsearch.model.Quote;
import org.jboss.as.quickstarts.hsearch.model.Topic;
import org.jboss.as.quickstarts.hsearch.search.InitSearch;
import org.jboss.as.quickstarts.hsearch.search.SearchQual;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.Asset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.persistence20.PersistenceDescriptor;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class QuoteSearchIT {
	
	@Deployment
	public static Archive<?> createTestArchive() {
		 String[] deps = {"org.hibernate:hibernate-search"};

		        File[] libs = Maven.resolver().loadPomFromFile("pom.xml").resolve(deps).withTransitivity().asFile();

		     return ShrinkWrap.create(WebArchive.class, QuoteSearchIT.class.getSimpleName() + ".war")
		            .addClasses(SearchController.class, QuoteDao.class, 
	                 Resources.class, QuoteDaoImpl.class, Quote.class, Topic.class, InitSearch.class, SearchQual.class)
	                 //persistence.xml for the test
		            .addAsResource(persistenceXml(), "META-INF/persistence.xml")
		            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
		            .addAsLibraries(libs)
		            //Populate test database 
		            .addAsResource("import.sql")   
		            // Deploy our test datasource
		            .addAsWebInfResource("test-ds.xml");
	}
	
	private static Asset persistenceXml() {
		String persistenceXml = Descriptors.create( PersistenceDescriptor.class )
			.version( "2.0" )
			.createPersistenceUnit()
				.name( "primary" )
				.jtaDataSource( "java:jboss/datasources/HSearchJPAQuickstartTestDS" )
				.getOrCreateProperties()
					.createProperty().name( "hibernate.hbm2ddl.auto" ).value( "create-drop" ).up()
					.createProperty().name( "hibernate.search.default.directory_provider" ).value( "ram" ).up()
				.up().up()
			.exportAsString();
		return new StringAsset( persistenceXml );
	}


    @Inject
    SearchController searchController;


    @Test
    public void testEmptySearchField() throws Exception {
    	searchController.search("NotGonnaFindMe");
    	assertEquals( "QuoteList should be empty", true, searchController.getQuotes().isEmpty());   
    }
    
    @Test
    public void testReturnAllQuotes() throws Exception {
    	searchController.search("");
    	assertEquals("All quotes are three", 3, searchController.getQuotes().size());
    }
    
    @Test
    public void testNormalQuoteSearch() throws Exception {
    	searchController.search("Mark");
    	assertEquals("One quote should be returned", 1, searchController.getQuotes().size());
    }

}
