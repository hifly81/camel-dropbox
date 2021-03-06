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
package org.apache.camel.component.dropbox;

import org.apache.camel.component.dropbox.consumer.DropboxScheduledPollConsumer;
import org.apache.camel.component.dropbox.consumer.DropboxScheduledPollGetConsumer;
import org.apache.camel.component.dropbox.consumer.DropboxScheduledPollSearchConsumer;
import org.apache.camel.component.dropbox.producer.*;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.component.dropbox.util.DropboxException;
import org.apache.camel.component.dropbox.util.DropboxOperation;
import org.apache.camel.impl.DefaultEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.camel.component.dropbox.util.DropboxConstants.POLL_CONSUMER_DELAY;

public class DropboxEndpoint extends DefaultEndpoint {

    private static final transient Logger LOG = LoggerFactory.getLogger(DropboxEndpoint.class);

    private DropboxConfiguration configuration = null;

    public DropboxEndpoint() {
    }

    public DropboxEndpoint(String uri, DropboxComponent component,DropboxConfiguration configuration) {
        super(uri, component);
        this.configuration = configuration;
    }

    public DropboxEndpoint(String endpointUri) {
        super(endpointUri);
    }

    /**
     * Create one of the camel producer available based on the configuration
     * @return the camel producer
     * @throws Exception
     */
    public Producer createProducer() throws Exception {
        LOG.info("resolve producer dropbox endpoint {" + configuration.getOperation().toString() + "}");
        LOG.info("resolve producer dropbox attached client:"+configuration.getClient());
        if(configuration.getOperation() == DropboxOperation.put) {
            return new DropboxPutProducer(this,configuration);
        }
        else if(this.configuration.getOperation() == DropboxOperation.search) {
            return new DropboxSearchProducer(this,configuration);
        }
        else if(this.configuration.getOperation() == DropboxOperation.del) {
            return new DropboxDelProducer(this,configuration);
        }
        else if(this.configuration.getOperation() == DropboxOperation.get) {
            return new DropboxGetProducer(this,configuration);
        }
        else if(this.configuration.getOperation() == DropboxOperation.move) {
            return new DropboxMoveProducer(this,configuration);
        }
        else {
            throw new DropboxException("operation specified is not valid for producer!");
        }
    }

    /**
     * Create one of the camel consumer available based on the configuration
     * @param processor  the given processor
     * @return the camel consumer
     * @throws Exception
     */
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.debug("resolve consumer dropbox endpoint {" + configuration.getOperation().toString() + "}");
        LOG.debug("resolve consumer dropbox attached client:"+configuration.getClient());
        DropboxScheduledPollConsumer consumer = null;
        if(this.configuration.getOperation() == DropboxOperation.search) {
            consumer = new DropboxScheduledPollSearchConsumer(this,processor,configuration);
            consumer.setDelay(POLL_CONSUMER_DELAY);
            return consumer;
        }
        else if(this.configuration.getOperation() == DropboxOperation.get) {
            consumer = new DropboxScheduledPollGetConsumer(this,processor,configuration);
            consumer.setDelay(POLL_CONSUMER_DELAY);
            return consumer;
        }
        else {
            throw new DropboxException("operation specified is not valid for consumer!");
        }
    }

    public boolean isSingleton() {
        return true;
    }
}
