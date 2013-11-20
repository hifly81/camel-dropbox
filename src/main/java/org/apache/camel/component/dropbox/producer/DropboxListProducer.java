/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.dropbox.producer;

import com.dropbox.core.DbxEntry;
import org.apache.camel.Exchange;
import org.apache.camel.component.dropbox.DropboxConfiguration;
import org.apache.camel.component.dropbox.DropboxEndpoint;
import org.apache.camel.component.dropbox.util.DropboxAPIFacade;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.apache.camel.component.dropbox.util.DropboxConstants.ENTRIES_SIZE;

/**
 * The CamelDropbox producer.
 */
public class DropboxListProducer extends DefaultProducer {
    private static final transient Logger LOG = LoggerFactory.getLogger(DropboxListProducer.class);
    private DropboxEndpoint endpoint;
    private final DropboxConfiguration configuration;

    public DropboxListProducer(DropboxEndpoint endpoint, DropboxConfiguration configuration) {
        super(endpoint);
        this.endpoint = endpoint;
        this.configuration = configuration;
    }

    public void process(Exchange exchange) throws Exception {
        List<DbxEntry> entries = DropboxAPIFacade.getInstance(this.configuration.getClient())
                .list(this.configuration.getRemotePath());
        if(entries!=null && entries.size()!=0) {
            log.info("Entries found: " + entries.size());
            //set info in exchange
            exchange.getIn().setHeader(ENTRIES_SIZE,entries.size());
            exchange.getIn().setBody(entries.size());
        }

    }

}
