package com.usu.mapps.utils;

import android.content.Context;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.components.SelectMode;
import com.components.SelectMode.FileExplorerListener;
import com.nsoft.campus500.R;
import com.usu.mapps.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileExplorerUtils{
	
	private static final String I_FULL_PATH = "fullPath";
	private static final String I_FILENAME = "fileName";
	private static final String I_TYPE = "fileType";
	private static final int I_TYPE_FOLDER = R.drawable.ic_files;
	private static final int I_TYPE_FILE = R.drawable.ic_epub;
	private static final int I_TYPE_UP = R.drawable.ic_up;

	public static final String EX_PATH = "extraPath";
	public static final String EX_STYLE = "selectStyle";
	public static final String EX_PATH_RESULT = "pathResult";

	private static List<Map<String, Object>> currentFileList =
							new ArrayList<Map<String, Object>>();
	private static String currentPath = "";
	private static SimpleAdapter simpleAdapter = null;

	private static SelectMode selectMode = null;

	@SuppressWarnings("static-access")
	public static void openFileExplorer(
            Context context, ListView fileExplorerList,
            FileSelectedListener fileSelectedListener) {
		
		currentPath= Environment.getExternalStorageDirectory().
										getAbsolutePath();

		selectMode=SelectMode.createSelectMode(context,
											SelectMode.SELECT_FILE);
		selectMode.setFileExplorerListener(new FileExplorerListener() {
			@Override
			public void updateList(File f) {
				FileExplorerUtils.updateCurrentList(f);
			}
		});
		selectMode.setFileSelectedListener(fileSelectedListener);
		
		File f = new File(currentPath);
		simpleAdapter = new SimpleAdapter(context, currentFileList,
				R.layout.file_list_item, new String[] { I_FILENAME,
						I_FULL_PATH, I_TYPE }, new int[] { R.id.fileName,
						R.id.fullPath, R.id.fileType });

		updateCurrentList(f);

		fileExplorerList.setAdapter(simpleAdapter);
		fileExplorerList.setOnItemClickListener(new FileItemClickListener());
		
	}

	static void updateCurrentList(File f) {
		List<Map<String, Object>> newData = getData(f);
		currentPath = f.getAbsolutePath();
		currentFileList.clear();
		currentFileList.addAll(newData);
		simpleAdapter.notifyDataSetChanged();
	}

	static final Comparator<File> sorter = new Comparator<File>() {
		@Override
		public int compare(File lhs, File rhs) {
			// file or folder
			int lhsType = lhs.isDirectory() ? 0 : 1;
			int rhsType = rhs.isDirectory() ? 0 : 1;
			if (lhsType != rhsType) {
				return lhsType - rhsType;
			}
			return lhs.getName().compareToIgnoreCase(rhs.getName());
		}
	};

	static void sortData(File[] files) {
		Arrays.sort(files, sorter);
	}

	static private List<Map<String, Object>> getData(File folder) {
		//List<String> acceptedExts=Arrays.asList(Constant.ACCEPTED_EXT_LIST);
		
		if (!folder.isDirectory()) {
			return Collections.emptyList();
		}

		// selectMode specifies file-filtering rules
		File[] listFiles = folder.listFiles(selectMode);
		sortData(listFiles);

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		// add "Up one level" item
		File parentFolder = folder.getParentFile();
		if (parentFolder != null) {
			Map<String, Object> up = new HashMap<String, Object>();
			up.put(I_FILENAME, "Up");
			up.put(I_TYPE, I_TYPE_UP);
			up.put(I_FULL_PATH, parentFolder.getAbsolutePath());

			result.add(up);
		}

		for (int i = 0; i < listFiles.length; i++) {
			File f = listFiles[i];
			Map<String, Object> item = new HashMap<String, Object>();
			item.put(I_FILENAME, f.getName());
			item.put(I_TYPE, f.isDirectory() ? I_TYPE_FOLDER : I_TYPE_FILE);
			item.put(I_FULL_PATH, f.getAbsolutePath());
			
			result.add(item);
		}
		return result;
	}

	static class FileItemClickListener implements OnItemClickListener {

		@SuppressWarnings("unchecked")
		@Override
		public void onItemClick(AdapterView<?> list, View itemView, int position, long id) {
			Map<String, Object> item = (Map<String, Object>) simpleAdapter
					.getItem(position);
			String path = (String) item.get(I_FULL_PATH);
			File f = new File(path);
			selectMode.onItemClicked(f);
		}
		
	}
	
	/*
	@SuppressWarnings("unchecked")
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Map<String, Object> item = (Map<String, Object>) simpleAdapter
				.getItem(position);

		String path = (String) item.get(I_FULL_PATH);
		File f = new File(path);
		selectMode.onItemClicked(f);

		super.onListItemClick(l, v, position, id);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			File parentFile = new File(currentPath).getParentFile();
			if (parentFile == null) {
				// finita la comedia
				return super.onKeyDown(keyCode, event);
			} else {
				updateCurrentList(parentFile);
			}
			return true;

		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
	 */
	
	static String getCurrentPath() {
		return currentPath;
	}
	
	public interface FileSelectedListener{
		public void fileSelected(String selectedFilePath);
	}
	
	/*
	public void selectResult(File f) {
		int isOkMessage = isOk(f);
		if (isOkMessage == DONT_NOTIFY) {
			// do nothing
		} else if (isOkMessage == ACCEPTABLE) {
			sendResult(f);
		} else {
			sayToUser(R.string.fs_warning, isOkMessage, f.getName());
		}
	}

	private static class OpenFile {
		private List<String> acceptedExts=null;
		
		public OpenFile(Activity activity) {
			acceptedExts=Arrays.asList(Constant.ACCEPTED_EXT_LIST);
		}

		public int isOk(File file) {
			return (file.canRead() && file.isFile()) ? ACCEPTABLE
					: R.string.fs_unacceptable;
		}

		public boolean accept(File pathname) {
			String ext=NSoftUtils.getFileExt(pathname.getName());
			return pathname.isDirectory() ||
					(!pathname.isDirectory() && acceptedExts.contains(ext));
			
		}

		void onItemClickedImpl(File f) {
			if (f.isDirectory()) {
				updateCurrentList(f);
			} else {
				selectResult(f);
			}
		}

	} 
 	*/
}