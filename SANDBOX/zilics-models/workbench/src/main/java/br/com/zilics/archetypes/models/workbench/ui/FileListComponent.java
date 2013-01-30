package br.com.zilics.archetypes.models.workbench.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileFilter;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class FileListComponent extends JList {

	private static final long serialVersionUID = 2043524232005444692L;
	
	private File directory;
	private String fileExtension;

	public FileListComponent() {
		super();
		super.setModel(new FileDirectoryListModel());
		super.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	@Override
	public final void setModel(ListModel model) {
		throw new IllegalArgumentException("Can't change the model");
	}
	
	@Override
	public final void setSelectionMode(int mode) {
		throw new IllegalArgumentException("Can't change the selection mode");
	}

	@Override
	public final void setSelectionModel(ListSelectionModel selectionModel) {
		throw new IllegalArgumentException("Can't change the selection model");
	}

	public File getDirectory() {
		return directory;
	}
	
	public void setDirectory(File directory) {
		this.directory = directory;
		((FileDirectoryListModel) getModel()).broadcastChange();
	}
	
	public String getFileExtension(String fileExtension) {
		return fileExtension;
	}
	
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
		((FileDirectoryListModel) getModel()).broadcastChange();
	}
	
	public File selectedFile() {
		if (getSelectedIndex() == -1) return null;
		File result = new File(directory, (String) getSelectedValue());
		return result;
	}
	
	
	private class FileDirectoryListModel implements ListModel, FileFilter {
		private final Set<ListDataListener> listeners = new HashSet<ListDataListener>(); 
		
		private void broadcastChange() {
			ListDataEvent event = new ListDataEvent(FileListComponent.this, ListDataEvent.CONTENTS_CHANGED, 0, getSize());
			for(ListDataListener l : listeners) {
				l.contentsChanged(event);
			}
		}

		public void addListDataListener(ListDataListener l) {
			listeners.add(l);
		}

		public Object getElementAt(int index) {
			File dir = FileListComponent.this.directory;
			if (dir != null && dir.isDirectory()) {
				File[] files = dir.listFiles(this);
				if (index < files.length)
					return files[index].getName();
			}
			return null;
		}

		public int getSize() {
			File dir = FileListComponent.this.directory;
			if (dir != null && dir.isDirectory()) {
				return dir.listFiles(this).length;
			}
			return 0;
		}

		public void removeListDataListener(ListDataListener l) {
			listeners.remove(l);
		}

		public boolean accept(File pathname) {
			if (!pathname.isFile()) return false;
			if (FileListComponent.this.fileExtension == null) return true;
			return pathname.getName().endsWith(FileListComponent.this.fileExtension);
		}
	}
	
	public static abstract class DoubleClickMouseListener implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2)
				mouseDoubleClicked(e);
		}
		
		public abstract void mouseDoubleClicked(MouseEvent e);
		
		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}		
	}
}
