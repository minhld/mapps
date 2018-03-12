package com.usu.mapps.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.nsoft.campus500.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class LoadImageThread extends AsyncTask<Void,Integer,Void> {
	
	public LoadImageThread(Context _context, ImageView _imageView,
                           String _imagePath){
		String imagePath=fixImagePath(_imagePath);
		
		if (!imagePath.equals(Constant.EMPTY)){
			Picasso.with(_context).
					load(imagePath).
					fit().
					centerCrop().
					//centerInside().
					//placeholder(R.drawable.ic_noimage).
					into(_imageView);
		}else{
			//this.imageView.setImageResource(R.drawable.ic_noimage);
		}
	}

	public LoadImageThread(Context _context, ImageView _imageView,
                           String _imagePath, int _placeHolderId,
                           int... _optionWidth){
		String imagePath=fixImagePath(_imagePath);

		if (!imagePath.equals(Constant.EMPTY)){
			Picasso.with(_context).
					load(imagePath).
					fit().
					centerCrop().
					//centerInside().
					placeholder(_placeHolderId).
					into(_imageView);
		}else{
			//this.imageView.setImageResource(R.drawable.ic_noimage);
		}
	}
	
	public LoadImageThread(Context _context, ImageView _imageView,
                           String _imagePath, int _width){
		final Context context=_context;
		final ImageView imageView=_imageView;
		String imagePath=fixImagePath(_imagePath);
		final int width=_width;
		
		if (!imagePath.equals(Constant.EMPTY)){
			Transformation transformation=new Transformation(){
				@Override
                public Bitmap transform(Bitmap source){
                    double aspectRatio=(double)source.getHeight()/
                    					(double)source.getWidth();
                    int height=(int)(width*aspectRatio);
                    Bitmap result= Bitmap.createScaledBitmap(
                    				source,width,height,false);
                    if (result!=source){
                        // same bitmap is returned if sizes are the same
                        source.recycle();
                    }
                    return result;
                }

                @Override
                public String key() {
                    return "fitWidth()";
                }
            };

			Picasso.with(context).
					load(imagePath).
					fit().
					//centerCrop().
					centerInside().
					transform(transformation).
					//placeholder(R.drawable.ic_noimage).
					into(imageView);
		}else{
			//this.imageView.setImageResource(R.drawable.ic_noimage);
		}
	}
	
	public LoadImageThread(Context _context, ImageView _imageView,
                           String _imagePath, int _width, int _height,
                           boolean usePlaceHolder){
		String imagePath=fixImagePath(_imagePath);
		
		if (!imagePath.equals(Constant.EMPTY)){
			if (usePlaceHolder){
				Picasso.with(_context).
						load(imagePath).
						placeholder(R.drawable.ic_person).
						resize(_width,_height).
						into(_imageView);
			}else{
				Picasso.with(_context).
						load(imagePath).
						//placeholder(R.drawable.ic_person_large).
						resize(_width,_height).
						into(_imageView);
			}
		}else{
			//this.imageView.setImageResource(R.drawable.ic_noimage);
		}
	}
	
	@Override
	protected Void doInBackground(Void... params){
		//this.bmp=NSoftEngine.getThumbFromImage(
		//					this.context,this.imagePath);
		return null;
	}

	@Override
	protected void onPostExecute(Void result){
		/*
		if (!this.imagePath.equals(Constant.EMPTY)){
			Picasso.with(this.context).load(this.imagePath).
			placeholder(R.drawable.ic_noimage).into(this.imageView);
		}else{
			this.imageView.setImageResource(R.drawable.ic_noimage);
		}
		*/
	}
	
	private String fixImagePath(String imagePath){
		if (imagePath==null)
			return Constant.EMPTY;
		
		return (!imagePath.startsWith("file://") && 
						!imagePath.startsWith("http"))?
				"file://"+imagePath:imagePath;

	}
}
