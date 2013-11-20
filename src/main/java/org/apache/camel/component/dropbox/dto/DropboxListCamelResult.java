package org.apache.camel.component.dropbox.dto;

import com.dropbox.core.DbxEntry;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.camel.component.dropbox.util.DropboxConstants.ENTRIES_SIZE;
import static org.apache.camel.component.dropbox.util.DropboxConstants.ENTRIES;

/**
 * Created with IntelliJ IDEA.
 * User: hifly
 * Date: 11/20/13
 * Time: 7:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class DropboxListCamelResult extends DropboxCamelResult {

    private static final transient Logger LOG = LoggerFactory.getLogger(DropboxListCamelResult.class);

    @Override
    public void populateExchange(Exchange exchange) {
        if(this.dropboxObj!=null) {
            List<DbxEntry> entries = (List<DbxEntry>)this.dropboxObj;
            if(entries.size()>0) {
                LOG.info("Entries found: " + entries.size());
                //set info in exchange
                exchange.getIn().setHeader(ENTRIES_SIZE,entries.size());
                exchange.getIn().setBody(entries.size());
                Map<String,String> paths = new HashMap<String,String>(entries.size());
                for(DbxEntry entry:entries) {
                    paths.put(entry.path,entry.name);
                    LOG.info("Entry: " + entry.path+"-"+entry.name);
                }
                exchange.getIn().setHeader(ENTRIES,paths);

            }
        }
    }
}
