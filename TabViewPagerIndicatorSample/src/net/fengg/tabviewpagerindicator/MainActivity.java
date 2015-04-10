package net.fengg.tabviewpagerindicator;

import net.fengg.tabviewpagerindicator.lib.ContentItem;
import net.fengg.tabviewpagerindicator.lib.SimpleViewPagerIndicator;
import net.fengg.tabviewpagerindicator.lib.TabSlidingView;
import net.fengg.tabviewpagerindicator.lib.TabSlidingView.TabContentProvider;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.LinearLayout;


public class MainActivity extends FragmentActivity {
	private TabSlidingView mIndicator;

//	private SimpleViewPagerIndicator mIndicator;
	
	private MyPagerAdapter mAdapter;
	
	private ViewPager mViewPager;
	
	private final int mImageViewArray[] = { R.drawable.tab_home,R.drawable.tab_convenience, R.drawable.tab_rentalhouse, R.drawable.tab_my};
	
	private final String[] mTitles = { "主页","省钱", "便民", "圈子"};
	
	private TabFragment[] mFragments;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		initDatas();
		setTabsValue();
	}

	private void setTabsValue() {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		// 设置Tab是自动填充满屏幕的
		mIndicator.setShouldExpand(true);
		// 设置Tab的分割线是透明的
//		mIndicator.setDividerColor(Color.TRANSPARENT);
		// 设置Tab底部线的高度
		mIndicator.setUnderlineHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 1, dm));
		// 设置Tab Indicator的高度
		mIndicator.setIndicatorHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 2, dm));
		// 设置Tab标题文字的大小
		mIndicator.setTextSize((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_SP, 15, dm));
		// 设置Tab Indicator的颜色
		mIndicator.setIndicatorColor(Color.parseColor("#45c01a"));
		mIndicator.setUnderlineColor(Color.parseColor("#45c01a"));
		// 设置选中Tab文字的颜色 (这是我自定义的一个方法)
//		mIndicator.setSelectedTextColor(Color.parseColor("#45c01a"));
		// 取消点击Tab时的背景色
		mIndicator.setTabBackground(0);
		mIndicator.setTitileIconDirection(LinearLayout.VERTICAL);
		mIndicator.setIconAbove(true);
		//设置指示在上部
		mIndicator.setIndicatorBelow(true);
	}
	
	private void initDatas()
	{
		mFragments = new TabFragment[mTitles.length];
		//如果布局中已添加，则此处不需设置
//		mIndicator.setTitles(mTitles);
		
		for (int i = 0; i < mTitles.length; i++)
		{
			mFragments[i] = (TabFragment) TabFragment.newInstance(mTitles[i]);
		}
		
		mAdapter=new MyPagerAdapter(getSupportFragmentManager());
		
		mViewPager.setAdapter(mAdapter);
		
		mIndicator.setViewPager(mViewPager);
//		mIndicator.setViewPager(mViewPager, 0);
	}

	private void initViews()
	{
		mIndicator = (TabSlidingView) findViewById(R.id.id_indicator);
//		mIndicator = (SimpleViewPagerIndicator) findViewById(R.id.id_indicator);
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
	}
	
	public class MyPagerAdapter extends FragmentPagerAdapter implements TabContentProvider{

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return mTitles[position];
		}

		@Override
		public int getCount() {
			return mTitles.length;
		}

		@Override
		public Fragment getItem(int position) {
			return mFragments[position];
		}

		@Override
		public ContentItem getTabContent(int position) {
			ContentItem item = new ContentItem();
			item.setTitle(mTitles[position]);
			item.setIconRes(mImageViewArray[position]);
			return item;
			/*switch (position) {
			case 0:
				return titles[0];
			case 1:
				return titles[1];
			case 2:
				return mImageViewArray[0];
			case 3:
				return mImageViewArray[1];
			default:
				return null;
			}*/
		}
	}
}