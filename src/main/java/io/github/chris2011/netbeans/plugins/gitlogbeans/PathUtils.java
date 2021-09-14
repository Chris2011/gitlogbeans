package io.github.chris2011.netbeans.plugins.gitlogbeans;

import java.util.Set;
import javax.swing.SwingUtilities;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataShadow;
import org.openide.windows.Mode;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author markiewb
 */
public class PathUtils {

    public Project getProject() {
        final FileObject fileObjectOfEditor = getFileObject(getCurrentEditor());
        if (fileObjectOfEditor != null) {
            return FileOwnerQuery.getOwner(fileObjectOfEditor);
        }

        final FileObject fileObjectOfNode = getFileObject(TopComponent.getRegistry().getActivated());
        if (fileObjectOfNode != null) {
            return FileOwnerQuery.getOwner(fileObjectOfNode);
        }

        return null;

    }

    /**
     * Returns the original string if not empty or not null. Else return the
     * given default.
     */
//    private String defaultIfEmpty(String string, String defaultStr) {
//        if (isEmpty(string)) {
//            return defaultStr;
//        }
//        return string;
//    }
    private FileObject getFileObjectWithShadowSupport(DataObject dataObject) {
        if (dataObject instanceof DataShadow) {
            DataShadow dataShadow = (DataShadow) dataObject;
            return dataShadow.getOriginal().getPrimaryFile();
        }
        return dataObject.getPrimaryFile();
    }

    /**
     * Gets the currently opened editor.
     */
    private TopComponent getCurrentEditor() {
        SwingUtilities.invokeLater(new ReturnRunnable<TopComponent>() {
            @Override
            public TopComponent runForResult() {
                Set<? extends Mode> modes = WindowManager.getDefault().getModes();

                for (Mode mode : modes) {
                    if ("editor".equals(mode.getName())) {
                        return mode.getSelectedTopComponent();
                    }
                }

                return null;
            }
        });

        return null;
    }

//    private boolean isEmpty(String string) {
//        return null == string || "".equals(string);
//    }
    private FileObject getFileObject(TopComponent activeTC) {
        if (null == activeTC) {
            return null;
        }

        //fallback hierarchy: Project, then DataObject, then FileObject
        Project project = activeTC.getLookup().lookup(Project.class);
        if (project != null && project.getProjectDirectory() != null) {
            return project.getProjectDirectory();
        }

        DataObject dataObject = activeTC.getLookup().lookup(DataObject.class);
        if (dataObject != null && getFileObjectWithShadowSupport(dataObject) != null) {
            return getFileObjectWithShadowSupport(dataObject);
        }

        FileObject fileObject = activeTC.getLookup().lookup(FileObject.class);
        if (fileObject != null) {
            return fileObject;
        }

        return null;
    }

    private abstract class ReturnRunnable<T> implements Runnable {

        public abstract T runForResult();

        @Override
        public void run() {
            runForResult();
        }
    }
}
