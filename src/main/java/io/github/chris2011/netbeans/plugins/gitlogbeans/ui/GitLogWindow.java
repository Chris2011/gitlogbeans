package io.github.chris2011.netbeans.plugins.gitlogbeans.ui;

import io.github.chris2011.netbeans.plugins.gitlogbeans.LogCreator;
import io.github.chris2011.netbeans.plugins.gitlogbeans.PathUtils;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Properties;
import javax.swing.SwingUtilities;
import org.netbeans.api.project.Project;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.RequestProcessor;
import org.openide.windows.WindowManager;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
    dtd = "-//io.github.chris2011.netbeans.plugins.gitlogbeans.ui//GitLogWindow//EN",
    autostore = false
)
@TopComponent.Description(
    preferredID = "GitLogWindow",
    iconBase = "io/github/chris2011/netbeans/plugins/gitlogbeans/ui/git.png",
    persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "output", openAtStartup = false)
@ActionID(category = "Window", id = "io.github.chris2011.netbeans.plugins.gitlogbeans.ui.GitLogWindow")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
    displayName = "#CTL_GitLogWindowAction",
    preferredID = "GitLogWindow"
)
@Messages({
    "CTL_GitLogWindowAction=GitLogWindow",
    "CTL_GitLogWindow=Git Log",
    "HINT_GitLogWindow=This is a GitLogWindow window"
})
public final class GitLogWindow extends TopComponent implements PropertyChangeListener {
    private final RequestProcessor requestProcessor = new RequestProcessor(GitLogWindow.class);
    private final LogCreator logCreator = new LogCreator();
    private Project project = null;

    public GitLogWindow() {
        initComponents();
        setName(Bundle.CTL_GitLogWindow());
        setToolTipText(Bundle.HINT_GitLogWindow());
        
        // TODO: Hide them for now. Will add them later.
        pnlBranchPaneToolbar.setVisible(false);
        scrlPnlGraphToolbar.setVisible(false);
        pnlBranch.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frame = new javax.swing.JPanel();
        pnlBranch = new javax.swing.JPanel();
        pnlContent = new javax.swing.JPanel();
        scrlPnlGraph = new javax.swing.JScrollPane();
        lblLoading = new javax.swing.JLabel();
        scrlPnlGraphToolbar = new javax.swing.JPanel();
        txtCommitSearch = new javax.swing.JTextField();
        pnlBranchPaneToolbar = new javax.swing.JPanel();
        txtBranchNameSearch = new javax.swing.JTextField();

        javax.swing.GroupLayout pnlBranchLayout = new javax.swing.GroupLayout(pnlBranch);
        pnlBranch.setLayout(pnlBranchLayout);
        pnlBranchLayout.setHorizontalGroup(
            pnlBranchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 167, Short.MAX_VALUE)
        );
        pnlBranchLayout.setVerticalGroup(
            pnlBranchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );

        scrlPnlGraph.setBorder(null);

        lblLoading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/io/github/chris2011/netbeans/plugins/gitlogbeans/ui/spinner24.gif"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(lblLoading, org.openide.util.NbBundle.getMessage(GitLogWindow.class, "GitLogWindow.lblLoading.text")); // NOI18N
        lblLoading.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        scrlPnlGraph.setViewportView(lblLoading);

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrlPnlGraph)
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrlPnlGraph, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        );

        scrlPnlGraphToolbar.setPreferredSize(new java.awt.Dimension(541, 30));

        txtCommitSearch.setText(org.openide.util.NbBundle.getMessage(GitLogWindow.class, "GitLogWindow.txtCommitSearch.text")); // NOI18N

        javax.swing.GroupLayout scrlPnlGraphToolbarLayout = new javax.swing.GroupLayout(scrlPnlGraphToolbar);
        scrlPnlGraphToolbar.setLayout(scrlPnlGraphToolbarLayout);
        scrlPnlGraphToolbarLayout.setHorizontalGroup(
            scrlPnlGraphToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scrlPnlGraphToolbarLayout.createSequentialGroup()
                .addComponent(txtCommitSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 403, Short.MAX_VALUE))
        );
        scrlPnlGraphToolbarLayout.setVerticalGroup(
            scrlPnlGraphToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtCommitSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pnlBranchPaneToolbar.setPreferredSize(new java.awt.Dimension(0, 30));

        txtBranchNameSearch.setText(org.openide.util.NbBundle.getMessage(GitLogWindow.class, "GitLogWindow.txtBranchNameSearch.text")); // NOI18N

        javax.swing.GroupLayout pnlBranchPaneToolbarLayout = new javax.swing.GroupLayout(pnlBranchPaneToolbar);
        pnlBranchPaneToolbar.setLayout(pnlBranchPaneToolbarLayout);
        pnlBranchPaneToolbarLayout.setHorizontalGroup(
            pnlBranchPaneToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtBranchNameSearch)
        );
        pnlBranchPaneToolbarLayout.setVerticalGroup(
            pnlBranchPaneToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtBranchNameSearch)
        );

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlBranch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBranchPaneToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrlPnlGraphToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                    .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrlPnlGraphToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBranchPaneToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBranch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel frame;
    private javax.swing.JLabel lblLoading;
    private javax.swing.JPanel pnlBranch;
    private javax.swing.JPanel pnlBranchPaneToolbar;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JScrollPane scrlPnlGraph;
    private javax.swing.JPanel scrlPnlGraphToolbar;
    private javax.swing.JTextField txtBranchNameSearch;
    private javax.swing.JTextField txtCommitSearch;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        final PropertyChangeListener listenerA = this;
        WindowManager.getDefault().invokeWhenUIReady(() -> {
            // code to be invoked when system UI is ready
            TopComponent.getRegistry().addPropertyChangeListener(listenerA);

            SwingUtilities.invokeLater(() -> {
                try {
                    scrlPnlGraph.getViewport().add(logCreator.createCommitGraphPane(project));
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            });
        });
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
        TopComponent.getRegistry().removePropertyChangeListener(this);
    }

    void writeProperties(Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        scrlPnlGraph.add(lblLoading);
        //run inside EDT, because we are using WindowManager inside
        project = new PathUtils().getProject();
        //run the lenghty GIT processing outside EDT
        requestProcessor.execute(() -> {
            if (project != null) {
                SwingUtilities.invokeLater(() -> {
                    try {
                        setName(String.format("%s: %s", Bundle.CTL_GitLogWindow(), project.getProjectDirectory().getName()));
//                    logCreator.project = project;
                        scrlPnlGraph.getViewport().add(logCreator.createCommitGraphPane(project));

//                        logCreator.commitGraphPane.repaint();
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                });
            }
        });
    }
}