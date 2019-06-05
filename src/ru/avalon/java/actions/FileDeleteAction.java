
package ru.avalon.java.actions;

import java.io.File;

public class FileDeleteAction implements Action {

    private final String sourceDirName;
    
    public FileDeleteAction(String source) {
        this.sourceDirName = source;
    }
    
    @Override
    public void run() {
            recoursiveDelete(new File(sourceDirName));
    }

    private void recoursiveDelete(File file) {
        if(!file.exists()) return;
        if(file.isDirectory()) {
            for(File f : file.listFiles()) recoursiveDelete(f);
        }
        file.delete();
    }
    
    @Override
    public void close() throws Exception {
                // нет необходимости
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
