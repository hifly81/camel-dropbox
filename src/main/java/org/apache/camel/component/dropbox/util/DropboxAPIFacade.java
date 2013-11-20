package org.apache.camel.component.dropbox.util;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxWriteMode;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import static org.apache.camel.component.dropbox.util.DropboxConstants.UPLOADED_FILE;

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

    public DbxEntry.File putSingleFile(String filePath) throws Exception {
        File inputFile = new File(filePath);
        FileInputStream inputStream = new FileInputStream(inputFile);
        DbxEntry.File uploadedFile = null;
        try {
            uploadedFile =
                    instance.client.uploadFile("/"+filePath,
                            DbxWriteMode.add(), inputFile.length(), inputStream);
            return uploadedFile;
        }
        finally {
            inputStream.close();
        }

    }

    public List<DbxEntry> list(String remotePath) throws Exception {
        DbxEntry.WithChildren listing = instance.client.getMetadataWithChildren(remotePath);
        return listing.children;
    }

}
