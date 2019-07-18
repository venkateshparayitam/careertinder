package com.softwaregiants.careertinder.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softwaregiants.careertinder.R;
import com.softwaregiants.careertinder.callback.ACTION_PERFORMED;
import com.softwaregiants.careertinder.callback.BaseListener;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends BaseActivity {

	TextView TVNext;
	Context mContext;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_onboarding);
		initViews();
	}
	BaseListener baseListener = new BaseListener() {
		@Override
		public void callback(ACTION_PERFORMED action, int pos, Object... args) {
			TVNext.setVisibility(View.VISIBLE);
		}
	};

	@SuppressWarnings("ConstantConditions")
	private void initViews() {
		mContext = this;
		TVNext = findViewById(R.id.TVNext);
		TVNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(mContext, CreateCandidateProfileActivity.class));
				finish();
			}
		});
		HomeAdapter adapter = new HomeAdapter(baseListener);
		adapter.setData(createPageList());

		final ViewPager pager = findViewById(R.id.viewPager);
		pager.setAdapter(adapter);
		pager.setOnPageChangeListener(onPageChangeListener);
	}

	ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
		@Override
		public void onPageScrolled(int i, float v, int i1) {

		}

		@Override
		public void onPageSelected(int i) {
			if ( i == createPageList().size() - 1 ) {
				TVNext.setVisibility(View.VISIBLE);
			}
		}

		@Override
		public void onPageScrollStateChanged(int i) {

		}
	};

	@NonNull
	private List<View> createPageList() {
		List<View> pageList = new ArrayList<>();
		pageList.add(createPageView(R.color.gplus_color_4));
		pageList.add(createPageView(R.color.gplus_color_3));
		pageList.add(createPageView(R.color.gplus_color_2));
		pageList.add(createPageView(R.color.gplus_color_1));

		return pageList;
	}

	@NonNull
	private View createPageView(int color) {
		View view = new View(this);
		view.setBackgroundColor(getResources().getColor(color));

		return view;
	}

	class HomeAdapter extends PagerAdapter {
		private List<View> viewList;
		BaseListener baseListener;

		HomeAdapter(BaseListener baseListener) {
			this.viewList = new ArrayList<>();
			this.baseListener = baseListener;
		}

		@Override
		public Object instantiateItem(ViewGroup collection, int position) {
			View view = viewList.get(position);
			collection.addView(view);
			return view;
		}

		@Override
		public void destroyItem(ViewGroup collection, int position, Object view) {
			collection.removeView((View) view);
		}

		@Override
		public int getCount() {
			return viewList.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

		void setData(@Nullable List<View> list) {
			this.viewList.clear();
			if (list != null && !list.isEmpty()) {
				this.viewList.addAll(list);
			}

			notifyDataSetChanged();
		}

		@NonNull
		List<View> getData() {
			if (viewList == null) {
				viewList = new ArrayList<>();
			}

			return viewList;
		}


	};
}
