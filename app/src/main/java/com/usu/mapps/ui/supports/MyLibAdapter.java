package com.usu.mapps.ui.supports;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nsoft.campus500.R;
import com.nsoft.nsoftview.business.BookDeleter;
import com.nsoft.nsoftview.business.XPubUI;
import com.nsoft.nsoftview.objects.BookItem;
import com.nsoft.nsoftview.utils.Constant;
import com.nsoft.nsoftview.utils.LoadImageThread;
import com.nsoft.nsoftview.utils.NSoftEngine;
import com.nsoft.nsoftview.utils.NSoftSettings;
import com.nsoft.nsoftview.utils.NSoftUtils;
import com.nsoft.nsoftview.utils.NSoftViewHolder;

import java.util.Date;
import java.util.List;

public class MyLibAdapter extends ArrayAdapter<BookItem> {
	private final int MAX_DESC_CHARS=80;
	
	private final LayoutInflater mLayoutInflater;
	private final Context context;
	private List<BookItem> bookList;
	private int listStyle;
	
	public MyLibAdapter(Context ctx, List<BookItem> list, int listStyle){
		super(ctx,R.layout.mylib_book_item,list);
		this.context=ctx;
		this.bookList=list;
		this.listStyle=listStyle;
		this.mLayoutInflater= LayoutInflater.from(context);
	}
	
	@Override
	public BookItem getItem(int position) {
		return this.bookList.get(position);
	}
	
	@Override
	public int getCount() {
		return this.bookList.size();
	}
	
	@Override
	public int getPosition(BookItem item) {
		return this.bookList.indexOf(item);
	}
	
	@SuppressLint("UseValueOf")
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		BookItem book=this.bookList.get(position);
		NSoftViewHolder vh;
		
		if (convertView==null || convertView.getTag()==null){
			convertView=mLayoutInflater.inflate(
					listStyle==NSoftSettings.STYLE_GRID_VIEW?
							R.layout.mylib_book_item:
							R.layout.mylib_book_item_liststyle,
					parent,false);
			vh=new NSoftViewHolder();
			
			// book cover
			vh.mBookImage=(ImageView)convertView.findViewById(
									R.id.mylibBookImage);
			
			// setup book title
			vh.mTitleText=(TextView)convertView.findViewById(
										R.id.mylibBookTitle);
			// setup book author name
			vh.mAuthorNameText=(TextView)convertView.findViewById(
										R.id.mylibBookAuthor);
			// description
			vh.mDescText=(TextView)convertView.findViewById(
									R.id.mylibBookDescription);
			// create date
			vh.mCreateDate=(TextView)convertView.findViewById(
									R.id.mylibCreateDate);
			// view count
			vh.mLikeCountText=(TextView)convertView.findViewById(
									R.id.mylibViewCount);

			// delete marker
			vh.mAuthorImage=(ImageView)convertView.findViewById(
									R.id.mylibMarkDeleteButton);
			convertView.setTag(vh);
		}else{
			vh=(NSoftViewHolder)convertView.getTag();
		}
		
		
		int[] metrics=NSoftUtils.getViewMetrics(this.context);
		
		// book cover resize
		vh.mBookImage.setOnClickListener(new BookClickListener(book,vh));
		vh.mBookImage.setOnLongClickListener(new LongClickListener(book));

		vh.mBookImage.getLayoutParams().width=metrics[1];
		vh.mBookImage.getLayoutParams().height=metrics[2];
		new LoadImageThread(this.context,vh.mBookImage,
				book.getBookCover()).execute();

		// title
		vh.mTitleText.setText(book.getTitle());

		if (listStyle==NSoftSettings.STYLE_LIST_VIEW){
			// author
			vh.mAuthorNameText.setText(
				!book.getAuthorName().equals(Constant.EMPTY)?
					book.getAuthorName():
					this.context.getString(R.string.author_unknown_name));
			
			// description
			String desc=book.getDescription();
			if (desc.length()>MAX_DESC_CHARS){
				desc=desc.substring(0,MAX_DESC_CHARS)+Constant.ETC;
			}
			vh.mDescText.setText(desc);
			
			// create date
			String importDate=Constant.simpleDateFormat.format(
									new Date(book.getBookUniqueId()));
			vh.mCreateDate.setText(importDate);
			
			// view count
			vh.mLikeCountText.setText(book.getVisitedCount()+Constant.SPACE+
							this.context.getString(
									R.string.bookstand_view_visit_count));
		}
		
		// update the visibility of DELETE marker
		Object markDeleteObj=vh.mAuthorImage.getTag();
		if (XPubUI.deleteStateFlag && markDeleteObj!=null){
			int visibility=(Integer)markDeleteObj;
			vh.mAuthorImage.setVisibility(visibility);
		}else{
			vh.mAuthorImage.setVisibility(View.GONE);
		}
		
		return convertView;
	}
	
	
	private class BookClickListener implements View.OnClickListener{
		BookItem bookItem;
		NSoftViewHolder viewHolder;
		
		@SuppressLint("UseValueOf")
		public BookClickListener(BookItem _book,NSoftViewHolder _holder){
			this.bookItem=_book;
			this.viewHolder=_holder;
		}
		
		@Override
		public void onClick(View v) {
			if (XPubUI.deleteStateFlag){
				// in DELETE mode
				if (this.viewHolder.mAuthorImage.getVisibility()== View.GONE){
					this.viewHolder.mAuthorImage.setVisibility(View.VISIBLE);
					this.viewHolder.mAuthorImage.setTag(View.VISIBLE);
					XPubUI.getDeleteList().add(this.bookItem.getFolderName());
				}else{
					this.viewHolder.mAuthorImage.setVisibility(View.GONE);
					this.viewHolder.mAuthorImage.setTag(View.GONE);
					XPubUI.getDeleteList().remove(this.bookItem.getFolderName());
				}
			}else{
				// in NORMAL mode
				// increase count and save
				new AsyncTask<Void, Integer, Void>() {
					@Override
					protected Void doInBackground(Void... params) {
						int currentCount=bookItem.getVisitedCount();
						bookItem.setVisitedCount(currentCount+1);
						try{
							NSoftEngine.saveBookListToXml();
						}catch(Exception e){}
						return null;
					}
				}.execute();
				
	//			if (XPubUI.getRecentBooksAdapter()!=null){
	//				if (XPubUI.getRecentBooksAdapter().
	//										getPosition(this.bookItem)<0){
	//					XPubUI.getRecentBooksAdapter().add(this.bookItem);
	//				}
	//				XPubUI.getRecentBooksAdapter().notifyDataSetChanged();
	//			}
				
				// open book view
				XPubUI.openBookView(context,bookItem);
			}
		}
	}
	
	@SuppressLint("UseValueOf")
	private class LongClickListener implements View.OnLongClickListener {
		BookItem bookItem;
		
		public LongClickListener(BookItem _book){
			this.bookItem=_book;
		}
		
		@Override
		public boolean onLongClick(View v) {
			if (XPubUI.deleteStateFlag){
				// if in DELETE mode, just silently leave
				return false;
			}
			
			AlertDialog.Builder alert=new AlertDialog.Builder(
									MyLibAdapter.this.context);
			alert.setIcon(android.R.drawable.ic_dialog_alert);
			alert.setTitle(R.string.dialog_confirm).
				setMessage(R.string.mylib_delete_confirm).
				setPositiveButton(R.string.dialog_yes,
					new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							try{
								new BookDeleter(
									MyLibAdapter.this.context,
									MyLibAdapter.this,
									LongClickListener.this.bookItem.getFolderName()).
								execute();
							}catch(Exception e){
								Log.v("MyLibBookAdapter.deleteBook()",
									e.getClass()+": "+e.getMessage());
							}
						}
				}).
				setNegativeButton(R.string.dialog_no,
					new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(
								DialogInterface dialog,
								int which){
							dialog.cancel();
						}
					}).
				create().show();
			
			return true;
		}
		
	}
	
}
