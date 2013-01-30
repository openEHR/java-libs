package br.com.zilics.archetypes.models.workbench.ui;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.GridBagConstraints;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.ButtonGroup;
import javax.swing.JButton;

import br.com.zilics.archetypes.models.adl.parser.ADLParser;
import br.com.zilics.archetypes.models.am.archetype.Archetype;
import br.com.zilics.archetypes.models.rm.utils.xml.XmlParser;

public class ArchetypesPanel extends JPanel {

	private static final long serialVersionUID = -3079521933240259165L;
	private JSplitPane mainSplitPane = null;
	private JSplitPane rightSplitPane = null;
	private ArchetypeTreeComponent archetypeTreeComponent = null;
	private ObjectInspectorComponent objectInspectorComponent = null;
	private FileListComponent fileListComponent = null;
	private JPanel leftPanel = null;  
	private JPanel rightPanel = null;
	private JSplitPane mainPanel = null;
	private JPanel bottomLeftPanel = null;
	private JLabel jFormatLabel = null;
	private PathEvaluatorPanel pathEvaluatorPanel = null;
	private JRadioButton adlRadioButton = null;
	private JRadioButton xmlRadioButton = null;
	private JButton chooseDirectoryButton = null;
	private JScrollPane objectInspectorScrollPane = null;
	private JScrollPane fileListScrollPane = null;
	private JScrollPane archetypeTreeScrollPane = null;
	
	public ArchetypesPanel() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(700, 500);
        this.setLayout(new BorderLayout());
        this.add(getMainSplitPane(), BorderLayout.CENTER);
			
	}

	private JSplitPane getMainSplitPane() {
		if (mainSplitPane == null) {
			mainSplitPane = new JSplitPane();
			mainSplitPane.setResizeWeight(0.333);
			mainSplitPane.setLeftComponent(getLeftPanel());
			mainSplitPane.setRightComponent(getRightSplitPane());
		}
		return mainSplitPane;
	}
	
	private JSplitPane getRightSplitPane() {
		if (rightSplitPane == null) {
			rightSplitPane = new JSplitPane();
			rightSplitPane.setResizeWeight(0.5);
			rightSplitPane.setLeftComponent(getMainPanel());
			rightSplitPane.setRightComponent(getRightPanel());
		}
		return rightSplitPane;
	}

	
	private JPanel getRightPanel() {
		if (rightPanel == null) {
			rightPanel = new JPanel();
			rightPanel.setLayout(new BorderLayout());
			rightPanel.add(getObjectInspectorScrollPane(), BorderLayout.CENTER);
		}
		return rightPanel;
	}


	
	private JPanel getLeftPanel() {
		if (leftPanel == null) {
			leftPanel = new JPanel();
			leftPanel.setLayout(new BorderLayout());
			leftPanel.add(getFileListScrollPane(), BorderLayout.CENTER);
			leftPanel.add(getBottomLeftPanel(), BorderLayout.SOUTH);
		}
		return leftPanel;
	}

	private JPanel getBottomLeftPanel() {
		if (bottomLeftPanel == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 3;
			gridBagConstraints11.gridy = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 2;
			gridBagConstraints1.gridy = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			bottomLeftPanel = new JPanel();
			bottomLeftPanel.setLayout(new GridBagLayout());
			bottomLeftPanel.add(getJFormatLabel(), null);
			bottomLeftPanel.add(getAdlRadioButton(), gridBagConstraints);
			bottomLeftPanel.add(getXmlRadioButton(), gridBagConstraints1);
			bottomLeftPanel.add(getChooseDirectoryButton(), gridBagConstraints11);
			
			ButtonGroup group = new ButtonGroup();
			group.add(getAdlRadioButton());
			group.add(getXmlRadioButton());
		}
		return bottomLeftPanel;
	}
	
	private JPanel getPathEvaluatorPanel() {
		if (pathEvaluatorPanel == null) {
			pathEvaluatorPanel = new PathEvaluatorPanel();
		}
		return pathEvaluatorPanel;
	}
	
	private JLabel getJFormatLabel() {
		if (jFormatLabel == null) {
			jFormatLabel = new JLabel();
			jFormatLabel.setText("Format");
		}
		return jFormatLabel;
	}

	private JRadioButton getAdlRadioButton() {
		if (adlRadioButton == null) {
			adlRadioButton = new JRadioButton();
			adlRadioButton.setSelected(true);
			adlRadioButton.setText("ADL");
			adlRadioButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ArchetypesPanel.this.getFileListComponent().setFileExtension(".adl");
				}
			});
		}
		return adlRadioButton;
	}

	private JRadioButton getXmlRadioButton() {
		if (xmlRadioButton == null) {
			xmlRadioButton = new JRadioButton();
			xmlRadioButton.setText("XML");
			xmlRadioButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ArchetypesPanel.this.getFileListComponent().setFileExtension(".xml");
				}
			});
		}
		return xmlRadioButton;
	}

	private JButton getChooseDirectoryButton() {
		if (chooseDirectoryButton == null) {
			chooseDirectoryButton = new JButton();
			chooseDirectoryButton.setText("Select Directory");
			chooseDirectoryButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFileChooser fileChooser = new JFileChooser(ArchetypesPanel.this.fileListComponent.getDirectory());
					fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int r = fileChooser.showOpenDialog(ArchetypesPanel.this);
				    if (r == JFileChooser.APPROVE_OPTION) {
				    	getFileListComponent().setDirectory(fileChooser.getSelectedFile());
				    } else if (r == JFileChooser.CANCEL_OPTION) {
				    }
				}
			});
		}
		return chooseDirectoryButton;
	}
	
	
	private JScrollPane getObjectInspectorScrollPane() {
		if (objectInspectorScrollPane == null) {
			objectInspectorScrollPane = new JScrollPane();
			objectInspectorScrollPane.getViewport().add(getObjectInspectorComponent(), null);
		}
		return objectInspectorScrollPane;
	}
	
	private FileListComponent getFileListComponent() {
		if (fileListComponent == null) {
			fileListComponent = new FileListComponent();
			fileListComponent.setDirectory(new File("/home/hnaves/Documents/zilics/snapshot_1.2.0dev1/archetypes/codebase/models/test/src/main/resources/adl"));
			fileListComponent.setFileExtension(".adl");
			fileListComponent.setAutoscrolls(true);
			fileListComponent.addMouseListener(new FileListComponent.DoubleClickMouseListener() {

				public void mouseDoubleClicked(MouseEvent e) {
					Archetype archetype = null;
					if (ArchetypesPanel.this.adlRadioButton.isSelected()) {
						try {
							ADLParser parser = new ADLParser(ArchetypesPanel.this.fileListComponent.selectedFile());
							archetype = parser.parse();
						} catch (Exception ex) {
							ex.printStackTrace();
						}							
					} else {
						try {
							archetype = (Archetype) XmlParser.parseXml(new FileInputStream(ArchetypesPanel.this.fileListComponent.selectedFile()));
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					if (archetype != null) {
						ArchetypesPanel.this.archetypeTreeComponent.setArchetype(archetype);
						ArchetypesPanel.this.objectInspectorComponent.setInspectedObject(archetype);
						ArchetypesPanel.this.pathEvaluatorPanel.setPathEvaluationContext(archetype.getPathEvaluatorContext());
					}
						
				}
				
			});
		}
		return fileListComponent;
	}

	private JScrollPane getFileListScrollPane() {
		if (fileListScrollPane == null) {
			fileListScrollPane = new JScrollPane(getFileListComponent());
		}
		return fileListScrollPane;
	}
	
	private JSplitPane getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new JSplitPane();
			mainPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
			mainPanel.setResizeWeight(0.8);
			mainPanel.setTopComponent(getArchetypeTreeScrollPane());
			mainPanel.setBottomComponent(getPathEvaluatorPanel());
		}
		return mainPanel;
	}
	
	private ArchetypeTreeComponent getArchetypeTreeComponent() {
		if (archetypeTreeComponent == null) {
			archetypeTreeComponent = new ArchetypeTreeComponent();
		}
		return archetypeTreeComponent;
	}

	private JScrollPane getArchetypeTreeScrollPane() {
		if (archetypeTreeScrollPane == null) {
			archetypeTreeScrollPane = new JScrollPane(getArchetypeTreeComponent());
		}
		return archetypeTreeScrollPane;
	}
	
	private ObjectInspectorComponent getObjectInspectorComponent() {
		if (objectInspectorComponent == null) {
			objectInspectorComponent = new ObjectInspectorComponent();
		}
		return objectInspectorComponent;
	}

}
