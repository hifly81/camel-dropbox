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

import org.apache.camel.component.dropbox.DropboxConfiguration;
import org.apache.camel.component.dropbox.DropboxEndpoint;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The CamelDropbox producer.
 */
public class DropboxSimpleProducer extends DefaultProducer {
    private static final transient Logger LOG = LoggerFactory.getLogger(DropboxSimpleProducer.class);
    private DropboxEndpoint endpoint;
    private final DropboxConfiguration configuration;

    public DropboxSimpleProducer(DropboxEndpoint endpoint,DropboxConfiguration configuration) {
        super(endpoint);
        this.endpoint = endpoint;
        this.configuration = configuration;
    }

    public void process(Exchange exchange) throws Exception {
        exchange.getIn().setHeader("MyHeader","123");
        String body = (String) exchange.getIn().getBody();
        exchange.getIn().setBody(body + "and the Teacher is crazy !");
    }

}
