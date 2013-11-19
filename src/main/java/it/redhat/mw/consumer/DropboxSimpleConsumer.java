package it.redhat.mw.consumer;

import it.redhat.mw.DropboxConfiguration;
import it.redhat.mw.DropboxEndpoint;
import org.apache.camel.Endpoint;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultConsumer;

/**
 * Created with IntelliJ IDEA.
 * User: hifly
 * Date: 11/18/13
 * Time: 10:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class DropboxSimpleConsumer extends DefaultConsumer {
    private final DropboxEndpoint endpoint;
    private final DropboxConfiguration configuration;

    public DropboxSimpleConsumer(DropboxEndpoint endpoint, Processor processor,DropboxConfiguration configuration) {
        super(endpoint, processor);
        this.endpoint = endpoint;
        this.configuration = configuration;
    }
}
