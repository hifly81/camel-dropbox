package org.apache.camel.component.dropbox.api;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxWriteMode;
import org.apache.camel.component.dropbox.dto.DropboxCamelResult;
import org.apache.camel.component.dropbox.dto.DropboxFileCamelResult;
import org.apache.camel.component.dropbox.dto.DropboxGenericCamelResult;
import org.apache.camel.component.dropbox.dto.DropboxListCamelResult;

import static org.apache.camel.component.dropbox.util.DropboxConstants.DROPBOX_FILE_SEPARATOR;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created with IntelliJ IDEA.
 * User: hifly
 * Date: 11/20/13
 * Time: 3:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class DropboxAPIFacade {

    private static DropboxAPIFacade instance;
    private static DbxClient client;

    private DropboxAPIFacade(){}

    public static DropboxAPIFacade getInstance(DbxClient client) {
        if (instance == null) {
            instance = new DropboxAPIFacade();
            instance.client = client;
        }
        return instance;
    }

    public DropboxCamelResult putSingleFile(String filePath) throws Exception {
        File inputFile = new File(filePath);
        FileInputStream inputStream = new FileInputStream(inputFile);
        DbxEntry.File uploadedFile = null;
        DropboxCamelResult result = null;
        try {
            uploadedFile =
                    instance.client.uploadFile(DROPBOX_FILE_SEPARATOR+filePath,
                            DbxWriteMode.add(), inputFile.length(), inputStream);
            result = new DropboxFileCamelResult();
            result.setDropboxObj(uploadedFile);
            return result;
        }
        finally {
            inputStream.close();
        }

    }

    public DropboxCamelResult list(String remotePath) throws Exception {
        DropboxCamelResult result = null;
        DbxEntry.WithChildren listing = instance.client.getMetadataWithChildren(remotePath);
        result = new DropboxListCamelResult();
        result.setDropboxObj(listing.children);
        return result;
    }

    public DropboxCamelResult del(String remotePath) throws Exception {
        DropboxCamelResult result = null;
        instance.client.delete(remotePath);
        result = new DropboxGenericCamelResult();
        return result;
    }



}
