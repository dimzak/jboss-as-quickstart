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
package org.jboss.as.quickstarts.hsearch.search;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.hibernate.search.jpa.FullTextEntityManager;

@Singleton
@Startup
public class IndexBuilder {

    @Inject
    @HSearch
    private FullTextEntityManager ftem;
    
    @Inject
    private Logger log;

    @PostConstruct
    public void start() {
        try {
            /*
             * The MassIndexer API is used to create the indexes
             * Optionally it can define the number of threads for the 
             * index and the cache mode
             */
            ftem.createIndexer().purgeAllOnStart(true).startAndWait();
        } 
        catch (InterruptedException e) {
            log.severe("Can not create index");
        }
    }

}
