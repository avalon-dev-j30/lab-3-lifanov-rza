
package ru.avalon.java.actions;

import java.io.File;

public class FolderCreateAction implements Action {
    private final String sourceDirName;
    private final String folderName;
    
    public FolderCreateAction(String source, String fName) {
        this.sourceDirName = source;
        this.folderName = fName;
    }

    @Override
    public void run() {
            File folder = new File(sourceDirName + "/" + folderName);
            if (!folder.exists()) folder.mkdir();
            else System.out.println("The folder has exist");
    }

    @Override
    public void close() throws Exception {
                    // нет необходимости
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
