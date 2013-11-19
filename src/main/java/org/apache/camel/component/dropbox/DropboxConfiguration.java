package org.apache.camel.component.dropbox;

import com.dropbox.core.*;
import org.apache.camel.spi.UriParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Locale;

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

    private String appKey;
    private String appSecret;
    private String accessToken;

    //file path on dropbox
    private String remotepath;

    //reference to dropboxclient
    private DbxClient client;

    public DbxClient getClient() {
        return client;
    }

    public void setClient(DbxClient client) {
        this.client = client;
    }

    public DbxClient createClient() {
        /*TODO clientIdentifier
        according to the dropbox API doc:
        If you're the author a higher-level library on top of the basic SDK,
        and the "Photo Edit" Android app is using your library to access Dropbox,
        you should append your library's name and version to form the full identifier.
        For example, if your library is called "File Picker",
        you might set this field to: "PhotoEditAndroid/2.4 FilePicker/0.1-beta"
         */
        String clientIdentifier = "camel-dropbox/1.0";

        DbxAppInfo appInfo = new DbxAppInfo(appKey, appSecret);
        DbxRequestConfig config =
                new DbxRequestConfig(clientIdentifier, Locale.getDefault().toString());
        //TODO need that an access token has been already granted for this app
        DbxClient client = new DbxClient(config, accessToken);
        return client;

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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRemotepath() {
        return remotepath;
    }

    public void setRemotepath(String remotepath) {
        this.remotepath = remotepath;
    }

}
