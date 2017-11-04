/*
 * Copyright (c) 2016 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.galacticon;

import android.app.WallpaperManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class PhotoActivity extends AppCompatActivity implements View.OnClickListener {

  private ImageView mPhotoImageView;
  private TextView mDescriptionTextView;
  private Photo mSelectedPhoto;
  private static final String PHOTO_KEY = "PHOTO";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_photo);

    mPhotoImageView = (ImageView) findViewById(R.id.photoImageView);
    mSelectedPhoto = (Photo) getIntent().getSerializableExtra(PHOTO_KEY);
    Picasso.with(this).load(mSelectedPhoto.getUrl()).into(mPhotoImageView);
    mDescriptionTextView = (TextView) findViewById(R.id.photoDescription);
    mPhotoImageView.setOnClickListener(this);

    if (mDescriptionTextView != null) {
      mDescriptionTextView.setText(mSelectedPhoto.getExplanation());
    }
  }

  @Override
  public void onClick(View v) {
    //Resources a = mPhotoImageView.getResources();
    WallpaperManager myWallpaperManager
            = WallpaperManager.getInstance(getApplicationContext());
   // myWallpaperManager.getBuiltInDrawable(mPhotoImageView.getWidth(),mPhotoImageView.getHeight(),true,0.0f,0.0f);
    //myWallpaperManager.getCropAndSetWallpaperIntent();
    try {
      InputStream i = new URL(mSelectedPhoto.getUrl()).openStream();
      myWallpaperManager.setStream(i);
      Toast.makeText(this, "!!!!Wallpaper Updated!!!!", Toast.LENGTH_SHORT).show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
