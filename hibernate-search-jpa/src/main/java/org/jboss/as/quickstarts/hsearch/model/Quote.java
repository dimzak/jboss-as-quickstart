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
package org.jboss.as.quickstarts.hsearch.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;


/*
 * {@ link Indexed} is used from Hsearch to create an index on this entity
 * and {@ link Field} are the fields to be indexed
 */
@Entity
/*
 * To define an Analyzer the steps are:
 * {@ link AnalyzerDef} and the name of your  analyzer
 * {@ link CharFilterDef}  list of charFilters before the tokenization(not covered)
 * {@ link TokenizerDef} tokenizer definition
 * tokenizing the input stream into individual words 
 * {@ link TokenFilterDef} apply changes to words after the tokenizer
 */
@AnalyzerDef(name = "snowballanalyzer",
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
        //part of the Solr Framework
        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
        @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
             @Parameter(name = "language", value = "English")
        })
        })
@Indexed
public class Quote {

    //{@ link DocumentId} is not required since {@ link Id} is present 
    @Id
    @GeneratedValue
    private Long id;

    @Field
    private String author;
    
    /*
     * {@ link Analyzer} applies the snowballanalyzer we created
     * to the field text
     */
    @Field(store = Store.YES)
    @Analyzer(definition = "snowballanalyzer")
    private String text;
    
    /* indexes the Topic entity with the {@ link ManyToMany} association
     * as part of the Quote entity
     */
    @IndexedEmbedded
    @ManyToMany
    private Set<Topic> topics = new HashSet<Topic>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }
}
