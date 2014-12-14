package com.example.weixintopdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private TextView text1, text2, text3;
	private ImageView tabLine;
	private ViewPager pager;

	private DisplayMetrics dm = new DisplayMetrics();
	private static int tabWidth;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		tabWidth = dm.widthPixels / 3;
		initView();
	}

	private void initView() {
		text1 = (TextView) findViewById(R.id.text1);
		text1.setOnClickListener(this);
		text2 = (TextView) findViewById(R.id.text2);
		text2.setOnClickListener(this);
		text3 = (TextView) findViewById(R.id.text3);
		text3.setOnClickListener(this);
		tabLine = (ImageView) findViewById(R.id.tabLine);

		pager = (ViewPager) findViewById(R.id.pager);

		pager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
		pager.setOnPageChangeListener(new PageListener());
		
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tab_line);
		Bitmap b = Bitmap.createBitmap(bitmap, 0, 0, tabWidth, 8);//设置tab的宽度和高度
		tabLine.setImageBitmap(b);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text1:
			pager.setCurrentItem(0);
			break;
		case R.id.text2:
			pager.setCurrentItem(1);
			break;
		case R.id.text3:
			pager.setCurrentItem(2);
			break;
		default:
			break;
		}
	}

	/**
	 * 页面适配器
	 */
	public class FragmentAdapter extends FragmentPagerAdapter {

		public FragmentAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			return PageFragment.newInstance(arg0);
		}

		@Override
		public int getCount() {
			return 3;
		}

	}

	/**
	 * 监听
	 */
	public class PageListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// new一个矩阵
			Matrix matrix = new Matrix();
			// 设置激活条的最终位置
			switch (arg0) {
			case 0:
				// 使用set直接设置到那个位置
				matrix.setTranslate(0, 0);
				break;
			case 1:
				matrix.setTranslate(tabWidth, 0);
				break;
			case 2:
				matrix.setTranslate(tabWidth * 2, 0);
				break;
			}

			// 在滑动的过程中，计算出激活条应该要滑动的距离
			float t = (tabWidth) * arg1;

			// 使用post追加数值
			matrix.postTranslate(t, 0);

			tabLine.setImageMatrix(matrix);
		}

		@Override
		public void onPageSelected(int arg0) {
			text1.setTextColor(getResources().getColor(R.color.black));
			text2.setTextColor(getResources().getColor(R.color.black));
			text3.setTextColor(getResources().getColor(R.color.black));
			switch (arg0) {
			case 0:
				text1.setTextColor(getResources().getColor(R.color.green));
				break;
			case 1:
				text2.setTextColor(getResources().getColor(R.color.green));
				break;
			case 2:
				text3.setTextColor(getResources().getColor(R.color.green));
				break;

			default:
				break;
			}
		}
	}
}
