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
	
	private final String[] mTitles = { "��ҳ","ʡǮ", "����", "Ȧ��"};
	
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
		// ����Tab���Զ��������Ļ��
		mIndicator.setShouldExpand(true);
		// ����Tab�ķָ�����͸����
//		mIndicator.setDividerColor(Color.TRANSPARENT);
		// ����Tab�ײ��ߵĸ߶�
		mIndicator.setUnderlineHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 1, dm));
		// ����Tab Indicator�ĸ߶�
		mIndicator.setIndicatorHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 2, dm));
		// ����Tab�������ֵĴ�С
		mIndicator.setTextSize((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_SP, 15, dm));
		// ����Tab Indicator����ɫ
		mIndicator.setIndicatorColor(Color.parseColor("#45c01a"));
		mIndicator.setUnderlineColor(Color.parseColor("#45c01a"));
		// ����ѡ��Tab���ֵ���ɫ (�������Զ����һ������)
//		mIndicator.setSelectedTextColor(Color.parseColor("#45c01a"));
		// ȡ�����Tabʱ�ı���ɫ
		mIndicator.setTabBackground(0);
		mIndicator.setTitileIconDirection(LinearLayout.VERTICAL);
		mIndicator.setIconAbove(true);
		//����ָʾ���ϲ�
		mIndicator.setIndicatorBelow(true);
	}
	
	private void initDatas()
	{
		mFragments = new TabFragment[mTitles.length];
		//�������������ӣ���˴���������
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