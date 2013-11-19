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

import org.apache.camel.component.dropbox.consumer.DropboxSimplePollConsumer;
import org.apache.camel.component.dropbox.producer.DropboxSimpleProducer;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.UriParam;

/**
 * Represents a Camel Dropbox endpoint.
 */
public class DropboxEndpoint extends DefaultEndpoint {

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

    public Producer createProducer() throws Exception {
        return new DropboxSimpleProducer(this,configuration);
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        return new DropboxSimplePollConsumer(this, processor,configuration);
    }

    public boolean isSingleton() {
        return true;
    }
}
