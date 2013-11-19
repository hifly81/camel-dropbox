package it.redhat.mw;

import com.dropbox.core.DbxClient;
import org.apache.camel.spi.UriParam;

/**
 * Created with IntelliJ IDEA.
 * User: hifly
 * Date: 11/18/13
 * Time: 10:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class DropboxConfiguration {

    /*
     Dropbox auth
     */

    @UriParam
    private String appKey;
    @UriParam
    private String appSecret;

    //file path on dropbox
    @UriParam
    private String remotepath;

    //reference to dropboxclient
    private DbxClient client;

    public DbxClient getClient() {
        return client;
    }

    public void setClient(DbxClient client) {
        this.client = client;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getRemotepath() {
        return remotepath;
    }

    public void setRemotepath(String remotepath) {
        this.remotepath = remotepath;
    }


}
