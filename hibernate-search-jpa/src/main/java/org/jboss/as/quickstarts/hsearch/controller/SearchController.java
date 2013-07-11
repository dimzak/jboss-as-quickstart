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
package org.jboss.as.quickstarts.hsearch.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.jboss.as.quickstarts.hsearch.dao.QuoteDao;
import org.jboss.as.quickstarts.hsearch.model.Quote;
import org.jboss.as.quickstarts.hsearch.search.SearchQual;

@Named
@RequestScoped
public class SearchController {

    @Inject
    private QuoteDao quoteDao;

    private String searchStr;
    
    private List<Quote> quotes;
    
    @Inject
    @SearchQual
    FullTextEntityManager ftem;
    
    @Produces
    public List<Quote> getQuotes() {
        return quotes;
    }

    
    public void search() {
        if(searchStr.isEmpty()) {
            ListQuotes();
        }
        else {
            QueryBuilder qb = ftem.getSearchFactory().buildQueryBuilder().forEntity(Quote.class).get();
            Query luceneQuery = qb.keyword()
                .onField("author")
                .andField("text")
                .andField("topics.name")
                .matching(searchStr)
                .createQuery();
            FullTextQuery objectQuery = ftem.createFullTextQuery(luceneQuery, Quote.class);
            quotes = (List<Quote>) objectQuery.getResultList();
        }
    }


    //Empty search field
    public void ListQuotes() {
        quotes = quoteDao.list();
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }
    
    
    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }
    
   
}
