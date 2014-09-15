package com.lib.mirrorlib;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.view.View;

public class Utils {

	/**
	 * 根据一个视图来创建其bitmap
	 * @param view
	 * @return Bitmap
	 */
	public static Bitmap createBitmapByView(View view) {
		Bitmap bitmap;
		Canvas ca = new Canvas();
		bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
		ca.setBitmap(bitmap);
		view.draw(ca);
		ca.setBitmap(null);
		return bitmap;
	}

	/**
	 * 
	 * @param originalBitmap
	 * @param reflectPersent
	 * @return Bitmap
	 */
	public static Bitmap getMirrorBitmap(Bitmap originalBitmap, float reflectPersent) {
		int width = originalBitmap.getWidth();
		int height = originalBitmap.getHeight();
		int reflectHight = (int) (height * reflectPersent);

		Matrix matrix = new Matrix();
		matrix.preScale(1, -1); // mirror the picture
		Bitmap reflectionBitmap = Bitmap.createBitmap(originalBitmap, 0, height - reflectHight, width, reflectHight, matrix, false);
		Canvas canvas = new Canvas(reflectionBitmap);
		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0, 0, 0, reflectionBitmap.getHeight(), 0x70ffffff, 0x00ffffff, TileMode.MIRROR);
		paint.setShader(shader);
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));

		canvas.drawRect(0, 0, width, reflectionBitmap.getHeight(), paint);
		return reflectionBitmap;
	}
}
