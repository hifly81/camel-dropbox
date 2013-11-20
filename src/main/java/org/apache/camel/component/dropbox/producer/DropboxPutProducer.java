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
import org.apache.camel.component.dropbox.DropboxConfiguration;
import org.apache.camel.component.dropbox.DropboxEndpoint;
import org.apache.camel.Exchange;
import org.apache.camel.component.dropbox.util.DropboxAPIFacade;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.camel.component.dropbox.util.DropboxConstants.UPLOADED_FILE;

public class DropboxPutProducer extends DropboxProducer {
    private static final transient Logger LOG = LoggerFactory.getLogger(DropboxPutProducer.class);

    public DropboxPutProducer(DropboxEndpoint endpoint, DropboxConfiguration configuration) {
        super(endpoint,configuration);
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        DbxEntry.File uploadedFile = DropboxAPIFacade.getInstance(this.configuration.getClient())
                .putSingleFile(this.configuration.getFilePath());
        log.info("Uploaded: " + uploadedFile.toString());
        //set info in exchange
        exchange.getIn().setHeader(UPLOADED_FILE,uploadedFile.toString());
        exchange.getIn().setBody(uploadedFile.toString());

    }

}
